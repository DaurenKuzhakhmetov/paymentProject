package testpay.model.paymentModels;

public class Transaction {
    private Amount amount;
    private String external_id;
    private String description;

    public Transaction(){}

    public Transaction(Amount amount,String external_id,String description){
        this.amount = amount;
        this.description = description;
        this.external_id = external_id;
    }
    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", external_id='" + external_id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
