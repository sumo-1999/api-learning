package com.revising.apidev.configuration;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@WebFilter("/*") // Applies to all requests
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // Log request details
        System.out.println("Incoming Request: " + httpRequest.getMethod() + " " + httpRequest.getRequestURI());

        // Wrap the response to capture the output
        CustomHttpServletResponseWrapper wrappedResponse = new CustomHttpServletResponseWrapper(httpServletResponse);

        // Proceed with the filter chain
        chain.doFilter(request, wrappedResponse);

        // Capture the response body
        byte[] responseBody = wrappedResponse.getCapturedResponse();
        String responseBodyString = new String(responseBody, StandardCharsets.UTF_8);

        // Log response details
        System.out.println("Outgoing Response: " + responseBodyString);

        // Write the response back to the client
        response.getOutputStream().write(responseBody);
    }
}

