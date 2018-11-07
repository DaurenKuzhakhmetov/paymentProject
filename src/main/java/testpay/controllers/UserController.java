package testpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import testpay.exceptions.InvalidRequest;
import testpay.model.paymentModels.CreatedResponse;
import testpay.model.paymentModels.Payer;
import testpay.model.paymentModels.RequestObjects;
import testpay.model.paymentModels.Transaction;
import testpay.enums.Intent;

import testpay.service.paymentService.PaymentService;
import webhook.WebhookService;


@RestController
@RequestMapping("/api.testpay.com")
public class UserController {
    @Autowired
    private WebhookService webhookService;
    @Autowired
    private PaymentService paymentService;


    @RequestMapping(value = "/payments/payment", method = RequestMethod.POST,produces = "application/json")
    public @ResponseBody CreatedResponse createPayment(@RequestParam("access_token")String token,
                                                       @RequestParam("intent")  Intent intent,
                                                       @RequestParam("notification_url") String notification_url,
                                                       @RequestBody RequestObjects requestObjects)
    {

        Transaction transaction = requestObjects.getTransaction();
        Payer payer = requestObjects.getPayer();
        CreatedResponse createdResponse = paymentService.createPayment(transaction,payer,notification_url,intent);
        if(createdResponse==null){
               throw new InvalidRequest();
        }
        paymentService.sendToWebhook(notification_url); // call webhook
        return createdResponse;
    }
}