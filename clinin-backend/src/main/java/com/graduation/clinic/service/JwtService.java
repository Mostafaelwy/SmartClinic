package com.graduation.clinic.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.graduation.clinic.entity.UsersBaseEntity;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.repos.BaseUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtService {
	
	private static final String SecretKey="1e4afa1c8f07b7155ab6b7906c70fe6d62f5846e04798176f350800dd6dc03c1af6b81f2182fb8ca2c4aa9f37372e82ab625fb4d8b5ef1dba8accdd5a8da0ddca9e8249ecc6b2da5b964d258e8bf017a82fd06ae902b6ce268b08fbff983225b3e7511fcd0c3583c6c0abe51bf769638cd44423d50d030a842db2bf08fae47c62e773639a75ce9f820e717f2e18969c0727462cdff4be876356e103a6368e22cdd109fd250af1b28d51a000c7089506f2a81335929d31d1b8963c4a2c791b185b54e7b5bc8b70b4896930a733dd6680c9f331df4d3d4a26bcc8366fd38ef5dd2ca882027e64ecb2ee9489af291934a467769ed26eff0939583fe780dc2456a0c";

	
	private final BaseUserRepo baseUserRepo;
	
	public JwtService(BaseUserRepo baseUserRepo) {
		this.baseUserRepo = baseUserRepo;
	}
	// generate token within extra claims
	public String GenerateToken(UserDetails userDetails) {
		return GenerateToken(new HashMap<>(),userDetails);
	}
	//generate token with extra claims
	public String GenerateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setClaims(createExtraClaims(userDetails))
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*24*60*60))
				.signWith(getSigningKey(),SignatureAlgorithm.HS256)
				.compact();
	}


	private Map<String, Object> createExtraClaims(UserDetails userDetails) {
		Map<String, Object> newCalims = new HashMap<>();
		newCalims.put("roles", userDetails.getAuthorities());
		UsersBaseEntity user = baseUserRepo.findByUserName(userDetails.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));
		newCalims.put("firstName", user.getFirstName());
		newCalims.put("lastName", user.getSecondName());
		return newCalims;
	}
	public String extractUserName(String jwt) {
		return extractClaim(jwt,Claims::getSubject);
	}
	
	public <T> T extractClaim(String jwt,Function<Claims,T> claimResolver){
		Claims claims=extractAllClaims(jwt);
		return claimResolver.apply(claims);
	}
	
	public Claims extractAllClaims(String jwt) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(jwt)
				.getBody();
			
	}
	
	public Key getSigningKey() {
		byte[] keybytes=Decoders.BASE64.decode(SecretKey);
		return Keys.hmacShaKeyFor(keybytes);
	}
	
	public Date extractExpiration(String jwt) {
		return extractClaim(jwt, Claims::getExpiration);
	}
	public boolean istokenExpired(String jwt) {
		return (extractExpiration(jwt).before(new Date()));
	}
	
	public boolean isTokenValid(UserDetails userDetails,String jwt) {
		String userName=extractUserName(jwt);
		return (userName.equals(userDetails.getUsername())&& !istokenExpired(jwt));
	}

}
