package testpay.model.paymentModels;

import testpay.enums.State;

public class CreatedResponse {
    private String id;
    private String create_time;
    private State state;

    public CreatedResponse(){}
       public CreatedResponse(String id, String create_time, State state){
            this.create_time = create_time;
            this.id = id;
            this.state = state;
       }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
