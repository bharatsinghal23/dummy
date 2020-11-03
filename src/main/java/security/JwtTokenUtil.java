package security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {


	private static final long serialVersionUID = 1L;

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.token.validity}")
	private String jwtTokenValidity;

	private static final long JWT_TOKEN_VALIDITY = 18000; // 18000 seconds = 5 hours

	private transient LoadingCache<String, String> jwtTokenCacheBucket;

	public JwtTokenUtil()
	{
		super();
		jwtTokenCacheBucket = CacheBuilder.newBuilder()
				.expireAfterWrite(JWT_TOKEN_VALIDITY, TimeUnit.SECONDS)
				.build(new CacheLoader<String, String>() {
					public String load(String s) throws Exception {
						return null;
					}
				});
	}

	/* retrieve username from jwt token */
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	/* retrieve expiration date from jwt token */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	/* for retrieving any information from token we will need the secret key */
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	/* check if the token has expired */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/* check user is logged out or not*/
	private Boolean isUserSessionIsActive(String username, String token) {
		try {
			return jwtTokenCacheBucket.get(username).equals(token);
		} catch (Exception e) {
			return false;
		}
	}

	/* generate token for user */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		String jwtToken = doGenerateToken(claims, userDetails.getUsername());
		/* Store Token in Guava cache */
		jwtTokenCacheBucket.put(userDetails.getUsername(), jwtToken);
		return jwtToken;
	}

	/* while creating the token - 
	 * 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID 
	 * 2. Sign the JWT using the HS512 algorithm and secret key. 
	 * 3. According to JWS Compact
	 * 
	 * Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-
	 * 41#section-3.1) compaction of the JWT to a URL-safe string
	 */
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtTokenValidity) * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/* validate token */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) && isUserSessionIsActive(username, token));
	}
	
	/* Remove JWT token from the cache after logout */
	public String clearJWTTokenFromCache(String key) {
		jwtTokenCacheBucket.invalidate(key);
		return "SUCCESSFULLY_LOGOUT";
	}
}