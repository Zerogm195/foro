package com.zuro.foro.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zuro.foro.autenticacion.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            String token = JWT.create()
                    .withIssuer("zuro_foro")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechadeExpiracion())
                    .sign(algorithm);
            System.out.println("Token generado: " + token); // Agregar log
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }


    public String getSubject(String token){

        if (token == null){
            throw new RuntimeException();

        }

        DecodedJWT verifier= null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("zuro_foro")
                    .build()
                    .verify(token);
            return verifier.getSubject();

        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
        }
        if (verifier == null){

            throw new RuntimeException("Verifier invalido");

        }
        return verifier.getSubject();
    }

    private Instant generarFechadeExpiracion(){

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));

    }

}


