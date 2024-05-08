package com.swSecurity.swSecurity.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // key for encrypt token
    private static final String SECRET_KEY = "89d1eb6380886095a009eb246cdf2f5bd54b988f578ef2da760fb074938923e4";
    private static final long TOKEN_VALIDITY_MS = 1000 * 60 * 60 * 24 * 7; // 7 days

    // extract one claim
    public String extractEmail(String token) {
        // subject = email
        return extractClaim(token, Claims::getSubject);
    }

    // extract all data in payload (call function and apply it)
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // implement of extract all data in payload...
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey()) // key to decryption payload
                .build()
                .parseClaimsJws(token)
                .getBody();  // get all claims
    }

    // generate token from user details only not by claims...
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // generate token from claims and user details
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims) // claims what will be in token
                .setSubject(userDetails.getUsername()) // user details that will be in token
                .setIssuedAt(new Date(System.currentTimeMillis())) // date from create token
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_MS)) // how long token be not expired (valid)
                .signWith(getSignKey(), SignatureAlgorithm.HS256) // key to decrypt
                .compact(); // apply it
    }

    // validate token is token belong to this user!
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userEmail = extractEmail(token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // check token expiration
    // compare token expiration date with todayDate if expiration date before todayDate return true indicating that the token has expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // create decryption key
    private Key getSignKey() {
        // decode encryption key and enter it to algo hmac.. to encrypt it
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        // key for decryption token
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
