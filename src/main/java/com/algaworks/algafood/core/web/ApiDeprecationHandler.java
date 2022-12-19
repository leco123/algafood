package com.algaworks.algafood.core.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Depreciar versão da API via Header
 */
@Component
public class ApiDeprecationHandler extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getRequestURI().startsWith("/v1/")) {
            response.addHeader("X-AlgaFood-Deprecated",
                    "Essa versão da API está depreciada e deixará de existir a partir de 15/10/2023, " +
                    "use a última versão da API.");
        }

        return true;
    }
}
