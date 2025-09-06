package com.example.aula.dto;

import jakarta.validation.constraints.*;

public class UsuarioDTO
{
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Email(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    public UsuarioDTO()
    {
        
    }

    public UsuarioDTO(@NotBlank(message = "O nome é obrigatório") String nome, String email, @NotBlank(message = "A senha é obrigatória") String senha)
    {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    

}
