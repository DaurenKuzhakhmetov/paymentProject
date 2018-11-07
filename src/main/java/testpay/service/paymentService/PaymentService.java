package testpay.service.paymentService;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import testpay.enums.Intent;
import testpay.model.paymentModels.CreatedResponse;
import testpay.model.paymentModels.Payer;
import testpay.model.paymentModels.Transaction;

public interface PaymentService {
    CreatedResponse createPayment(Transaction transaction, Payer payer,String notification_url,Intent intent);
    void sendToWebhook(String notification_url);
}

