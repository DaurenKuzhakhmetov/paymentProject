package testpay.configs.enumRequestConfig;


import org.springframework.core.convert.converter.Converter;
import testpay.enums.Intent;

public class MyCustomEnumConverter implements Converter<String, Intent> {
    @Override
    public Intent convert(String source) {
        try {
            return Intent.valueOf(source);
        } catch(Exception e) {
            return null; // or SortEnum.asc
        }
    }
}