package br.com.product.productapi.config.interceptor;

import br.com.product.productapi.modules.jwt.service.JwtService;
import feign.Request;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        if (isOption(request)) {
          return true;
        }
        var authorization = request.getHeader(AUTHORIZATION);
        service.validateAuthorization(authorization);
        return true;
    }

    private boolean isOption(HttpServletRequest request) {
        return HttpMethod.OPTIONS.name().equals(request.getMethod());
    }

}
