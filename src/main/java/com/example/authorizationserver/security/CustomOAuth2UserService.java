package com.example.authorizationserver.security;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;


/**
 * Logs in or registers a user after OAuth2 SignIn/Up
 */

public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	private CustomUserDetailsService userDetailsService;

	public CustomOAuth2UserService(CustomUserDetailsService futureDAOUserDetailsService){
		this.userDetailsService = futureDAOUserDetailsService;
	}


	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		OAuth2User oath2User = super.loadUser(userRequest);
		return buildPrincipal(oath2User);
	}

	/**
	 * Builds the security principal from the given userReqest.
	 * Registers the user if not already reqistered
	 */
	public CustomUserDetails buildPrincipal(OAuth2User oath2User) {

		Map<String, Object> attributes = oath2User.getAttributes();
		String email = getOAuth2Email(attributes);

		CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(email);



		return user;
	}

	public String getOAuth2Email(Map<String, Object> attributes) {

		return (String) attributes.get(StandardClaimNames.EMAIL);
	}


}