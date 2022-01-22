package com.alkemy.ong.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

  private static final String SECRET_KEY = "somosmas";
  private static final String BEARER_TOKEN = "Bearer %s";
  private static final String AUTHORITIES = "authorities";
  private static final String BEARER_PART = "Bearer ";
  private static final String EMPTY = "";

  public String extractUsername(String authorizationHeader) {
    return extractClaim(getToken(authorizationHeader), Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public Claims extractAllClaims(String token) {
    return Jwts.parser()
        .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
        .parseClaimsJws(token)
        .getBody();
  }

  private boolean isTokenExpired(String authorizationHeader) {
    return extractExpiration(getToken(authorizationHeader)).before(new Date());
  }

  private String createToken(String subject, String role) {
    List<GrantedAuthority> grantedAuthorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList(role);

    String token = Jwts.builder()
        .claim(AUTHORITIES,
            grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8)).compact();
    return String.format(BEARER_TOKEN, token);
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private String getToken(String authorizationHeader) {
    return authorizationHeader.replace(BEARER_PART, EMPTY);
  }

}
