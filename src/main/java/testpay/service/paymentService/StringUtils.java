package testpay.service.paymentService;

import org.springframework.stereotype.Component;
import testpay.enums.Intent;

import java.util.Currency;

@Component
public class StringUtils{

    public boolean correctEmail(String email){
        try {
            if (email.contains("@") && email.contains(".com")) {
                return true;
            } else
                return false;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean correctUrl(String notification_url){
        try {
            if (notification_url.startsWith("https://") || notification_url.startsWith("http://")) {
                return true;
            } else return false;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean correctValue(String value){
        try {
            if (value.substring(value.indexOf("."), value.length()).length() != 3 ||
                    value.length() > 10 || value.startsWith(".")) {
                return false;
            }
            double number = 0;
            try {
                number = Double.parseDouble(value);
            } catch (Exception ex) {
                return false;
            }
            return true;
        }catch (Exception ex){
            return false;
        }
    }


    public boolean correctCurrency(String currency){
        try {
            Currency curr = Currency.getInstance(currency);
            return true;
        }catch (Exception ex)
        {
            return false;
        }
    }
    public boolean correctIntent(Intent intent){
      try {
          if (intent.equals(Intent.order)) {
              return true;
          } else {
              return false;
          }
      }catch (Exception ex){
          return false;
      }
    }
}
