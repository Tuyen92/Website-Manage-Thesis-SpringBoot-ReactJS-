/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.WebKhoaLuan.config;

import java.util.Date;
import io.jsonwebtoken.*;

/**
 *
 * @author ADMIN
 */
public class jwtTokenProvider {
    private final String SECRET = "AAA";
    private final int EXPIRATION = 604800000;
    
    public String generateToken(CustomUserDetail userDetail){
        Date date = new Date();
        Date expire = new Date(date.getTime() + EXPIRATION);
        return Jwts.builder().setSubject(userDetail.getUsername()).setIssuedAt(date)
                .setExpiration(expire).signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }
    
    public String getUserId(String token){
        Claims c = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return c.getSubject();
    }
    
    public boolean validateToken(String authToken){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
