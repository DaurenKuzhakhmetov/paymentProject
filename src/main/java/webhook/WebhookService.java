package webhook;

import crypto.cryptoAlgo.GeneratorSignature;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import crypto.cryptoModels.NotificationMessage;

import java.math.BigInteger;
import java.util.logging.Logger;


@Service
public class WebhookService{

     private  NotificationMessage notificationMessage;
     private GeneratorSignature generatorSignature = new GeneratorSignature();


      public ResponseEntity<String> getResult(NotificationMessage message) throws InterruptedException {
          Thread.sleep(5000);
          notificationMessage = message;
          if(message.getSha2sig().equals(generatorSignature.generateSign(concateSHA2sig()))){
              System.out.println("signatures are equal");  // i know about logger :)


              return new ResponseEntity<>("ok",HttpStatus.OK);
          }else{
              return new ResponseEntity<String>("not ok",HttpStatus.INTERNAL_SERVER_ERROR);
          }
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
