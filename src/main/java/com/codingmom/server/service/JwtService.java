package com.codingmom.server.service;

import com.codingmom.server.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private String key = "ZheldAKa";

    public String createJWT(User user) {
        System.out.println("Start building JWT");

        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("id", user.getId());
        payloads.put("name", user.getName());
        payloads.put("k_id", user.getK_id());
        payloads.put("k_img_url", user.getK_img_url());
        payloads.put("type", user.getType());

        String accessToken = Base64.getEncoder().encodeToString(key.getBytes());

        return  Jwts.builder()
                    .setHeader(headers)
                    .setClaims(payloads)
                    .signWith(SignatureAlgorithm.HS256, accessToken)
                    .compact();
    }

    public Map<String, Object> checkJWT(String jwt) throws UnsupportedEncodingException {
        Map<String, Object> claimMap = null;
        try {
            Claims claims = Jwts.parser().setSigningKey(key.getBytes("UTF-8")).parseClaimsJws(jwt).getBody();
            claimMap = claims;
        } catch (ExpiredJwtException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return claimMap;
    }
}
