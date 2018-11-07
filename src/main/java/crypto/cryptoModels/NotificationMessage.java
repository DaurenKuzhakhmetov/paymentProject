package crypto.cryptoModels;

import testpay.enums.State;


public class NotificationMessage {
    private String currency;
    private String amount;
    private String id;
    private String external_id;
    private State status;
    private String sha2sig;

    public NotificationMessage() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }

    public String getSha2sig() {
        return sha2sig;
    }

    public void setSha2sig(String sha2sig) {
        this.sha2sig = sha2sig;
    }

    @Override
    public String toString() {
        return "NotificationMessage{" +
                "currency='" + currency + '\'' +
                ", amount='" + amount + '\'' +
                ", id='" + id + '\'' +
                ", external_id='" + external_id + '\'' +
                ", status=" + status +
                ", sha2sig='" + sha2sig + '\'' +
                '}';
    }
}
