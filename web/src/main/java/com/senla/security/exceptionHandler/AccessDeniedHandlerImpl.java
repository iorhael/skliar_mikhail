package com.senla.security.exceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.controller.dto.ResponseInfoDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    private static final String JSON_CONTENT_TYPE = "application/json";

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        ResponseInfoDto error = ResponseInfoDto.builder()
                .message(accessDeniedException.getMessage())
                .build();

        response.setContentType(JSON_CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(objectMapper.writeValueAsString(error));
    }
}
