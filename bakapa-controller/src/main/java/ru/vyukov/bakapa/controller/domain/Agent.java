package ru.vyukov.bakapa.controller.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import ru.vyukov.bakapa.controller.domain.View.Full;
import ru.vyukov.bakapa.controller.domain.View.Summary;

/**
 * Backup agent
 *
 * @author gelo
 */
@Data
@AllArgsConstructor
@Document(collection = "agent")
public class Agent implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = -5472269654731046147L;

    public final static List<GrantedAuthority> agentDefaultAuthority = Collections
            .singletonList(new SimpleGrantedAuthority("ROLE_AGENT"));


    /**
     * View for new Agent
     */
    public interface Credentials extends Full {  };




    @Id
    @NotNull
    @NotEmpty
    @JsonView({Full.class, Summary.class})
    private String agentId;

    @NotNull
    @NotEmpty
    @JsonView(Credentials.class)
    private String password;

    @NotNull
    @JsonView({Full.class, Summary.class})
    private LocalDateTime createDate;

    @JsonView({Full.class, Summary.class})
    private String note;

    public static Agent newAgent(String agentId) {
        Objects.requireNonNull(agentId);
        String password = UUID.randomUUID().toString();
        Agent agent = new Agent(agentId,password,LocalDateTime.now(),null);
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
        return getAgentId();
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

    /**
     * For tests
     * @param agentId
     * @return
     */
    public static Agent demo(String agentId) {

        return new Agent(agentId, agentId,LocalDateTime.now(),"for tests");
    }
}
