package com.lessonSpring.quickstar.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

// Аунтификация пользователя по ключу в заголовке
@RequiredArgsConstructor
public class ApiKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private String principalRequestHeader;

    public ApiKeyAuthFilter(String principalRequestHeader) {
        this.principalRequestHeader = principalRequestHeader;
    }

// getPreAuthenticatedPrincipal:
// Этот метод переопределяет метод из AbstractPreAuthenticatedProcessingFilter и используется для получения имени пользователя из запроса.
// Он извлекает значение заголовка запроса, указанного в поле principalRequestHeader, и возвращает его как имя пользователя.
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(principalRequestHeader);
    }

// Get user password
    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return null;
    }
}

// Как это работает:
// Когда запрос обрабатывается этим фильтром,
// метод getPreAuthenticatedPrincipal вызывается для извлечения имени пользователя из заголовка запроса.
// Затем вызывается метод getPreAuthenticatedCredentials, который возвращает null.
// Эти значения передаются в аутентификационную систему, которая проверяет, является ли имя пользователя действительным и имеет ли он соответствующие разрешения.
// Если проверка проходит успешно, запрос разрешается, в противном случае он отклоняется.