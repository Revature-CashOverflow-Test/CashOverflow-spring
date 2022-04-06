package com.revature.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Class to handle JWT functionality
 * 
 * @author Tyler Rondeau, Luis Estevez, Luis Rivera
 *
 */
//@Component
@Service
public class JwtUtil implements Serializable {

	private static final long serialVersionUID = -1916638954380206609L;

	public static final int JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	/**
	 * retrieve username from jwt token
	 * 
	 * @param token - jwt to decode
	 * @return - String username decoded
	 */
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	/**
	 * retrieve expiration date from jwt token
	 * 
	 * @param token - token to decode
	 * @return - Date expiration date of given token
	 */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	/**
	 * Class to apply a given function to a token
	 * 
	 * @param <T>            - Generic for return type of callback function
	 * @param token          - Token to execute on
	 * @param claimsResolver - callback function to execute
	 * @return - Return of callback function
	 */
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * get all claims using the secret key
	 * 
	 * @param token - token to get the key from
	 * @return - Claims (all tokens of the given key)
	 */
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	/**
	 * check if the token has expired
	 * 
	 * @param token - token to check
	 * @return - boolean representing whether it is expired or not
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * generate token for user
	 * 
	 * @param userDetails - User to generate token for
	 * @return - String token generated
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	/**
	 * Actually create a token
	 * 
	 * @param claims  - Map of claims to make the token off of
	 * @param subject - subject to make token
	 * @return - String token created
	 *
	 *         Specifics : while creating the token - 1. Define claims of the token,
	 *         like Issuer, Expiration, Subject, and the ID 2. Sign the JWT using
	 *         the HS512 algorithm and secret key. 3. According to JWS Compact
	 *         Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	 *         compaction of the JWT to a URL-safe string
	 */
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/**
	 * validate token
	 * 
	 * @param token       - Token to validate
	 * @param userDetails - User to validate
	 * @return - Boolean representing success or failure of validation
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}