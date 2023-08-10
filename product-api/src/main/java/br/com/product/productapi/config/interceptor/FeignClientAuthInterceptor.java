package br.com.product.productapi.config.interceptor;

import br.com.product.productapi.config.exception.ValidationException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static br.com.product.productapi.config.RequestUtil.getCurrentRequest;

public class FeignClientAuthInterceptor implements RequestInterceptor {


    private static final String AUTHORIZATION = "Authorization";
    private static final String TRANSACTION_ID = "transactionid";


    @Override
    public void apply(RequestTemplate template) {
        var currentRequest = getCurrentRequest();
        template.header(AUTHORIZATION, currentRequest.getHeader(AUTHORIZATION))
                .header(TRANSACTION_ID, currentRequest.getHeader(TRANSACTION_ID));
    }


}
