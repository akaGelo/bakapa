package ru.vyukov.bakapa.controller.config.utils;

import org.springframework.core.MethodParameter;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.support.PrincipalMethodArgumentResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.vyukov.bakapa.controller.domain.agent.Agent;

import java.security.Principal;
import java.util.Objects;

/**
 * Standard implementation {@link PrincipalMethodArgumentResolver} return Authentication.
 * This resolver return UserDetails implementation
 */
public class AuthenticationAgentArgumentResolver
        implements HandlerMethodArgumentResolver, org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver {


    @Override
    public Object resolveArgument(MethodParameter parameter, Message<?> message) throws Exception {
        Principal authentication = SimpMessageHeaderAccessor.getUser(message.getHeaders());

        return extractAgent(authentication);
    }

    public static Agent extractAgent(Principal principal) {
        Objects.requireNonNull(principal);
        if (principal instanceof Authentication) {
            return (Agent) ((Authentication) principal).getPrincipal();
        }
        throw new ClassCastException("Principal not implement Authentication");
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        return extractAgent(authentication);
    }


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType();
        AuthenticationAgent annotation = parameter.getParameterAnnotation(AuthenticationAgent.class);
        return null != annotation;
    }

}