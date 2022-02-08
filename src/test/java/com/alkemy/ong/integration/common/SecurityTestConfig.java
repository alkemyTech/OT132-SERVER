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
    List<GrantedAuthority> grantedAuthorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList(role.getFullRoleName());

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

}