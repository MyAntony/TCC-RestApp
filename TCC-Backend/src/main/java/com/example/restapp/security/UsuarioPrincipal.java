package com.example.restapp.security;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.restapp.model.Usuario;

public class UsuarioPrincipal implements UserDetails
{

    private final Usuario usuario;

    public UsuarioPrincipal(Usuario usuario)
    {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getCargo().name()));
    }

    @Override
    public String getPassword()
    {
        return usuario.getSenha();
    }

    @Override
    public String getUsername()
    {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
