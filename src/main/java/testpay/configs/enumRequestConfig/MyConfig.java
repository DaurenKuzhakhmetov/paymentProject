package testpay.configs.enumRequestConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//https://stackoverflow.com/questions/39774427/springs-requestparam-with-enum
@Configuration
public class MyConfig extends WebMvcConfigurationSupport {
    @Override
    public FormattingConversionService mvcConversionService() {
        FormattingConversionService f = super.mvcConversionService();
        f.addConverter(new MyCustomEnumConverter());
        return f;
    }
}