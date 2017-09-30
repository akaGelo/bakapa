package ru.vyukov.bakapa.controller.domain;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * Backup agent
 * 
 * @author gelo
 *
 */
@Data
@Document(collection = "agent")
public class Agent implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = -5472269654731046147L;

	public final static List<GrantedAuthority> agentDefaultAuthority = Collections
			.singletonList(new SimpleGrantedAuthority("ROLE_AGENT"));

	@NotNull
	@NotEmpty
	private String agetntId;

	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	private LocalDateTime createDate;

	private String note;

	public static Agent newAgent(String agentId) {
		Objects.requireNonNull(agentId);
		Agent agent = new Agent();
		agent.agetntId = agentId;
		agent.password = UUID.randomUUID().toString();
		agent.createDate = LocalDateTime.now();
		return agent;
	}

	// -------------------------------------------------

	@Override
	public void eraseCredentials() {
		password = null;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return agentDefaultAuthority;
	}

	@Override
	public String getUsername() {
		return getAgetntId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	public static String getDefaultAgentRole() {
		return agentDefaultAuthority.get(0).getAuthority();
	}

}
