package com.hackathon.ehealthcareproject.securityConfig;//package com.serethewind.Arkticles.securityConfig;
//
//import com.serethewind.Arkticles.utils.SecurityConstants;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtGenerator {
//
//    public String generateToken(Authentication authentication){
//        String username = authentication.getName(); //username is gotten from the authentication object
//        Date currentDate = new Date();
//        Date expirationDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
//
//        return Jwts.builder().
//                setSubject(username)
//                .setIssuedAt(currentDate)
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS256, SecurityConstants.JWT_SECRET)
//                .compact();
//
//    }
//
//    public String getUsernameFromJwt(String token){
//        Claims claims = Jwts.parserBuilder().setSigningKey(SecurityConstants.JWT_SECRET).build().parseClaimsJws(token).getBody();
//        return claims.getSubject();
//    }
//
//    public boolean validateToken(String token){
//        try {
//            Jwts.parserBuilder().setSigningKey(SecurityConstants.JWT_SECRET).build().parseClaimsJws(token).getBody();
//            return true;
//        } catch (Exception e){
//            throw new AuthenticationCredentialsNotFoundException("token is invalid or expired");
//        }
//    }
//}
