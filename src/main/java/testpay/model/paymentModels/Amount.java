package testpay.model.paymentModels;

public class Amount {
    private String value;


    private String currency;
     public Amount(){}

     public Amount(String value,String currency){
         this.currency = currency;
         this.value = value;
     }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "value='" + value + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
