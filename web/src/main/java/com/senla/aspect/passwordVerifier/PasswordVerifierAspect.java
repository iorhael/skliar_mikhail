package com.senla.aspect.passwordVerifier;

import com.senla.service.passwordVerifier.PasswordVerifierService;
import com.senla.util.CustomSpringExpressionLanguageParser;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
public class PasswordVerifierAspect {

    private final PasswordVerifierService verifierService;

    @Before("@annotation(VerifyPassword)")
    public void verifyPassword(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        VerifyPassword verifyPasswordAnnotation = method.getAnnotation(VerifyPassword.class);

        String password = (String) CustomSpringExpressionLanguageParser.getDynamicValue(
                signature.getParameterNames(),
                joinPoint.getArgs(),
                verifyPasswordAnnotation.password()
        );

        verifierService.verifyPassword(password);
    }
}
