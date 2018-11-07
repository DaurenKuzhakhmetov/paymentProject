package webhook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import crypto.cryptoModels.NotificationMessage;

@RestController
public class WebhookController {
   @Autowired
   WebhookService webhookService;

    @RequestMapping(value = "lolbek",method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> eventController(
            @RequestBody NotificationMessage message) throws InterruptedException {
        return webhookService.getResult(message);
    }
}
