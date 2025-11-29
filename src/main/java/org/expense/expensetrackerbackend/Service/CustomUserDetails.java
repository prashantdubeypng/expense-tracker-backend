package org.expense.expensetrackerbackend.Service;

import org.expense.expensetrackerbackend.entity.User;
import org.expense.expensetrackerbackend.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends User implements UserDetails {
    private String password;
    private String username;
    Collection<? extends GrantedAuthority> Authorities;
    public CustomUserDetails(User use){
        this.password = use.getPassword();
        this.username = use.getUsername();
        List<GrantedAuthority> auth = new ArrayList<>();
        for(UserRole role : use.getRoles()){
            auth.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }
        this.Authorities = auth;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Authorities;
    }
    @Override
    public String getPassword(){
        return this.password;
    }
    @Override
    public String getUsername(){
        return this.username;
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
    public  boolean isCredentialsNonExpired() {
        return true;
    }
@Override
    public boolean isEnabled() {
        return true;
    }
}
