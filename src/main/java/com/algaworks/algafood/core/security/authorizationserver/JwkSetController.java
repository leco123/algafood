package com.algaworks.algafood.core.security.authorizationserver;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("")
public class JwkSetController {

    @Autowired
    private JWKSet jwkSet;

    @GetMapping("/.well-known/jwks.json")
    public Map<String,Object> keys(){
        System.out.printf("");
        return this.jwkSet.toJSONObject();
    }
}
