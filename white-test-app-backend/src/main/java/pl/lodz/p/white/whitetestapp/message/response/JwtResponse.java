package pl.lodz.p.white.whitetestapp.message.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
		this.token = accessToken;
		this.username = username;
		this.authorities = authorities;
	}

	public String getToken() {
		return token;
	}

	public JwtResponse setToken(String token) {
		this.token = token;
		return this;
	}

	public String getType() {
		return type;
	}

	public JwtResponse setType(String type) {
		this.type = type;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public JwtResponse setUsername(String username) {
		this.username = username;
		return this;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public JwtResponse setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
		return this;
	}
}
