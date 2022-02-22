package com.alkemy.ong.integration.common;

import com.alkemy.ong.config.segurity.RoleType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public class SecurityTestConfig {

  private static final String SECRET_KEY = "somosmas";
  private static final String BEARER_TOKEN = "Bearer %s";
  private static final String AUTHORITIES = "authorities";

  public static String createToken(String subject, RoleType role) {
    String token = Jwts.builder()
        .claim(AUTHORITIES, getGrantedAuthorities(role))
        .setSubject(subject)
        .setIssuedAt(today())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8)).compact();
    return includeBearer(token);
  }

  private static String includeBearer(String token) {
    return String.format(BEARER_TOKEN, token);
  }

  private static List<String> convertToString(List<GrantedAuthority> grantedAuthorities) {
    return grantedAuthorities.stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());
  }

  private static List<String> getGrantedAuthorities(RoleType role) {
    List<GrantedAuthority> grantedAuthorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList(role.getFullRoleName());
    return convertToString(grantedAuthorities);
  }

  public static String createExpiredToken(String subject, RoleType role) {
    String token = Jwts.builder()
        .claim(AUTHORITIES, getGrantedAuthorities(role))
        .setSubject(subject)
        .setIssuedAt(today())
        .setExpiration(new Date(today().getTime() - 10))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8)).compact();
    return includeBearer(token);
  }

  private static Date today() {
    return new Date(System.currentTimeMillis());
  }

}