package testpay.service.paymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import crypto.cryptoAlgo.GeneratorSignature;
import crypto.cryptoModels.NotificationMessage;
import testpay.enums.Intent;
import testpay.exceptions.InvalidRequest;
import testpay.model.paymentModels.CreatedResponse;
import testpay.enums.State;
import testpay.model.paymentModels.Payer;
import testpay.model.paymentModels.Transaction;


import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

@Service
public class PaymentServiceImpl implements PaymentService {
    private AsyncRestTemplate restTemplate = new AsyncRestTemplate();
    private GeneratorSignature generatorSignature = new GeneratorSignature();
    private NotificationMessage notificationMessage = new NotificationMessage();

    @Autowired
    private StringUtils stringUtils;

    @Override
    public CreatedResponse createPayment(Transaction transaction, Payer payer, String notification_url, Intent intent) {
        String id = generateId();
        String time = createTime();

        if(stringUtils.correctEmail(payer.getEmail())
            && stringUtils.correctValue(transaction.getAmount().getValue())
            && stringUtils.correctUrl(notification_url)
            && stringUtils.correctCurrency(transaction.getAmount().getCurrency())
            && stringUtils.correctIntent(intent)) {

          CreatedResponse createdResponse = new CreatedResponse(id, time, State.created);
          notificationMessage.setCurrency(transaction.getAmount().getCurrency());
          notificationMessage.setAmount(transaction.getAmount().getValue());
          notificationMessage.setId(id);
          notificationMessage.setExternal_id(transaction.getExternal_id());
          notificationMessage.setStatus(State.approved);
          notificationMessage.setSha2sig(generatorSignature.generateSign(concateSHA2sig()));

          return createdResponse;
      }else {
          notificationMessage.setCurrency(transaction.getAmount().getCurrency());
          notificationMessage.setAmount(transaction.getAmount().getValue());
          notificationMessage.setId(id);
          notificationMessage.setExternal_id(transaction.getExternal_id());
          notificationMessage.setStatus(State.failed);
          notificationMessage.setSha2sig(generatorSignature.generateSign(concateSHA2sig()));
          return null;
      }
    }

    //3 days equals  259 200 000 ms
    //25 attempts
    //7*(2)^25 ~ 230 000 000ms
    @Retryable(maxAttempts = 25,value = RuntimeException.class,backoff = @Backoff(delay=7,multiplier = 2))
    @Override
     //ListenableFuture<ResponseEntity<Object>>
    public void sendToWebhook(String notification_url) {
        restTemplate.postForEntity(notification_url,new HttpEntity<>(notificationMessage),Object.class);
    }


    @Recover()
    public ResponseEntity<Object> recover(){
        return new ResponseEntity<Object>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }





    private String generateId(){
        int leftLimit = 97; // equals 'a'
        int rightLimit = 122; // equals 'z'
        int stringSize  = 16;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(stringSize);
        for (int i = 0; i < stringSize; i++) {
            boolean number = random.nextBoolean();
            if(number) {
                buffer.append(random.nextInt(9));
            }else {
                int randomLimitedInt = leftLimit + (int)
                        (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
        }
        String generatedId = buffer.toString();

         return generatedId;
    }
    private String createTime() {
    /* see : https://gist.github.com/kristopherjohnson/6124652 */
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String time = dateFormat.format(date);
        return time;
    }


    @Value("${my.secretword}")
    private String mySecret;


    private String concateSHA2sig(){
        StringBuilder sha2 = new StringBuilder();
        sha2.append(notificationMessage.getCurrency());
        sha2.append(notificationMessage.getAmount());
        sha2.append(generatorSignature.generateSign(getASCII()));//SHA2 UPPER
        sha2.append(notificationMessage.getId());
        sha2.append(notificationMessage.getExternal_id());
        sha2.append(notificationMessage.getStatus());
        return String.valueOf(sha2);
    }

    private String getASCII(){
        StringBuilder sb = new StringBuilder();
        for (char c : mySecret.toCharArray())
            sb.append((int)c);

        BigInteger asciiCode = new BigInteger(sb.toString());
        return String.valueOf(asciiCode);
    }
}
