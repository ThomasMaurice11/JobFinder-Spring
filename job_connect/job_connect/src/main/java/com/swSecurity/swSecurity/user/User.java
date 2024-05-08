package com.swSecurity.swSecurity.user;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.swSecurity.swSecurity.model.entity.Jobs;
import com.swSecurity.swSecurity.model.entity.Proposals;
import com.swSecurity.swSecurity.model.entity.SavedJobs;

import java.util.Collection;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")

//interface user details that related to user once created....
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    private Integer ID;
  
    

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

      @OneToMany(mappedBy = "employer")
    private List<Jobs> job;

    @OneToMany(mappedBy = "senderId")
    private List<Proposals> sender;

    @OneToMany(mappedBy = "receiverId")
    private List<Proposals> receiver;

    @OneToMany(mappedBy = "jobSeeker")
    private List<SavedJobs> savedJob;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
    
   
    public void setID(Integer iD) {
        ID = iD;
    }

    public Integer getID() {
        return ID;
    }

}
