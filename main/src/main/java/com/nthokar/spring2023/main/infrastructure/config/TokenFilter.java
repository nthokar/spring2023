package com.nthokar.spring2023.main.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.xml.stream.FactoryConfigurationError;
import java.io.IOException;

@Component
@Order(1)
@Slf4j
public class TokenFilter extends OncePerRequestFilter {



    @Value("${auth.provider:http://localhost:9000}")
    private String authProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        if (authProvider.isEmpty() || authProvider.isBlank()) {
            log.error("auth provider is invalid");
            return;
        }

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header.isEmpty() || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        var restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("token", header);

        HttpEntity<MultiValueMap<String, String>> requestRest = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        var validateResponse = restTemplate.postForEntity(authProvider + "/validate", requestRest, String.class);
        if (validateResponse.getStatusCode().value() != 200){
            log.warn("invalid response from auth provider: " + validateResponse.getStatusCode().value());
            return;
        }
        var body = validateResponse.getBody();
        var context = SecurityContextHolder.getContext();
        context.setAuthentication(new CustomAuthentication(body));

        chain.doFilter(request, response);
    }
}