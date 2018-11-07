package testpay.model.paymentModels;

public class Payer {
    private String email;


    public Payer(){}
    public Payer(String email){
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Payer{" +
                "email='" + email + '\'' +
                '}';
    }
}
