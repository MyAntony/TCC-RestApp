package com.example.restapp.config;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.restapp.entity.Usuario;
import com.example.restapp.repository.UsuarioRepository;

@Component
public class AuditorAwareImpl implements AuditorAware<Usuario>
{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public @org.springframework.lang.NonNull Optional<Usuario> getCurrentAuditor()
    {
        try
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // Se não houver autenticação ou for anônimo, retorna vazio
            if (authentication == null || !authentication.isAuthenticated() 
                || authentication.getPrincipal().equals("anonymousUser"))
                {
                return Optional.empty();
            }

            // username/email do JWT
            String email = authentication.getName();

            // Busca no banco o usuário completo
            return usuarioRepository.findByEmail(email);

        } catch (Exception e)
        {
            // Garante que a auditoria não quebre em nenhum caso
            return Optional.empty();
        }
    }
}
