package testpay.exceptions;

public class ExceptionEntity {
    private String code;
    private String description ;
      public ExceptionEntity(String code,String description){
          super();
          this.code = code;
          this.description = description;
      }
      public ExceptionEntity(){}

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }
}
