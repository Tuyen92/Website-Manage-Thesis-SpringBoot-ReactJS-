/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.WebKhoaLuan.config;

import com.demo.WebKhoaLuan.model.Nguoidung;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/**
 *
 * @author ADMIN
 */

public class CustomUserDetail implements UserDetails{
    Nguoidung nguoidung;
    
    public List<GrantedAuthority> authorities;

    public CustomUserDetail(Nguoidung nguoidung) {
        this.nguoidung = nguoidung;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return nguoidung.getPassword();
    }

    @Override
    public String getUsername() {
        return nguoidung.getUsername();
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
    
}
