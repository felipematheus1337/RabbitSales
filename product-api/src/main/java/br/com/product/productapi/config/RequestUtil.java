package br.com.product.productapi.config;

import br.com.product.productapi.config.exception.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil {


    public static HttpServletRequest getCurrentRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes())
                    .getRequest();

        } catch(Exception e) {
            e.printStackTrace();
            throw new ValidationException("The current request could not be proccessed");
        }
    }
}
