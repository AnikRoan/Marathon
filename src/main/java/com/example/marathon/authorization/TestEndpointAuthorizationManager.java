package com.example.marathon.authorization;

import org.springframework.stereotype.Component;



@Component
public class TestEndpointAuthorizationManager {
    public static boolean authoriz(String smth){
        return !Type.A.equals(smth);
    }

//    public static AuthorizationDecision authoriz(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext request)
//    {
//        authenticationSupplier.get();
//        return new AuthorizationDecision(true);
//    }

    enum Type {
        A, B, C, D

    }
}
