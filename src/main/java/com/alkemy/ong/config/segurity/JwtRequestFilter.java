package com.alkemy.ong.config.segurity;

import com.alkemy.ong.common.JwtUtils;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private static final String AUTHORITIES = "authorities";
  private static final Object WITHOUT_CREDENTIALS = null;

  @Autowired
  private JwtUtils jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (jwtUtil.isTokenSet(authorizationHeader)) {
      authentication(authorizationHeader);
    } else {
      SecurityContextHolder.clearContext();
    }

    filterChain.doFilter(request, response);
  }

  private void authentication(String authorizationHeader) {
    Claims claims = jwtUtil.extractAllClaims(authorizationHeader);
    List<String> authorities = (List) claims.get(AUTHORITIES);
    if (authorities != null) {
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
          claims.getSubject(),
          WITHOUT_CREDENTIALS,
          getGrantedAuthorities(authorities));
      SecurityContextHolder.getContext().setAuthentication(auth);
    } else {
      SecurityContextHolder.clearContext();
    }
  }

  private List<SimpleGrantedAuthority> getGrantedAuthorities(List<String> authorities) {
    return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }

}
