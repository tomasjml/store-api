package com.dialexa.storeapi.middleware;

import com.dialexa.storeapi.entities.interfaces.CustomInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.io.BufferedReader;

@Slf4j
@Component
public class InOutLogMiddleware implements CustomInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;

            // Log query parameters
            var queryParams = request.getParameterMap();
            log.info("ENTER {}.{} with query parameters: {}", method.getBeanType().getSimpleName(), method.getMethod().getName(), queryParams);

            // Log form parameters (for POST/PUT requests)
            if ("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod())) {
                var formParams = request.getParameterMap();
                log.info("Form parameters: {}", formParams);
            }

            // Log request body for POST/PUT requests
            if ("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod())) {
                StringBuilder body = new StringBuilder();
                String line;
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null) {
                    body.append(line).append(System.lineSeparator());
                }
                log.info("Request body: {}", body.toString().trim());
            }
        }
        return true; // Continue the request
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            log.info("EXIT {}.{}", method.getBeanType().getSimpleName(), method.getMethod().getName());
        }
    }
}
