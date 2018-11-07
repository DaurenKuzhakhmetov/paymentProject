package testpay.model.paymentModels;

public class RequestObjects {
    private Transaction transaction;
    private Payer payer;

    public RequestObjects() {
    }

    public RequestObjects(Transaction transaction, Payer payer) {
        this.transaction = transaction;
        this.payer = payer;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    @Override
    public String toString() {
        return "RequestObjects{" +
                "transaction=" + transaction +
                ", payer=" + payer +
                '}';
    }
}
