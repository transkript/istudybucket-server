package com.feljtech.istudybucket.service.security;

import com.feljtech.istudybucket.entity.User;
import com.feljtech.istudybucket.excetion.IstudybucketException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class JwtProvider {
    private KeyStore keyStore;
    private final String SECRET = "isb-237-bucketeers";

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/isb.jsk");
            keyStore.load(resourceAsStream, SECRET.toCharArray()); // TODO change secret
        } catch (CertificateException | KeyStoreException | NoSuchAlgorithmException | IOException e) {
            throw new IstudybucketException("Error occured while loading keystore");
        }
    }

    public String generateJwt(Authentication authentication) {
        org.springframework.security.core.userdetails.User principalUser = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principalUser.getUsername())
                .signWith(SignatureAlgorithm.HS512, getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            System.out.println(keyStore.getKey("isb", SECRET.toCharArray()));
            return (PrivateKey) keyStore.getKey("isb", SECRET.toCharArray()); // TODO change secret
        } catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
            throw new IstudybucketException("Error occured while retrieving public key from keystore");
        }
    }
}
