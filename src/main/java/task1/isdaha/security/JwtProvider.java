package task1.isdaha.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import task1.isdaha.entity.User;
import task1.isdaha.exception.CustomException;
import task1.isdaha.repository.UserRepository;
import task1.isdaha.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Component

@RequiredArgsConstructor
public class JwtProvider {
    final private UserRepository userRepository;

    @Value("${jwt.secret.key}")
    String secretKey;

    @Value("${jwt.expiry.date}")
    long expiryDate;
    private final AuthService userDetailsService;



    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("roles", user.getAuthorities());
        Date now = new Date();
        Date validity = new Date(now.getTime() + expiryDate);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }


    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Expired or invalid JWT token");
        }
    }


}
