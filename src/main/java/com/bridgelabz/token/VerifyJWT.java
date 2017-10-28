package com.bridgelabz.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class VerifyJWT {
	 private static final Logger LOG = LoggerFactory.getLogger(VerifyJWT.class);
	
	public static final String  key="toDoToken";
	public static int verifyAccessToken(String accessToken ) {
		try {
			JwtParser parser = Jwts.parser();
			Claims claims = parser.setSigningKey(key).parseClaimsJws(accessToken).getBody();
			LOG.info("claims"+claims);
			LOG.info(claims.getIssuer());
			return Integer.parseInt(claims.getIssuer());
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

}
