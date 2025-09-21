import React, { createContext, useState, useEffect } from 'react';
import { jwtDecode } from 'jwt-decode';
import api from '../services/api';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(() => localStorage.getItem('token'));
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Esta função é executada quando o componente é montado
    // para verificar se já existe um token válido no localStorage.
    if (token) {
      try {
        const decoded = jwtDecode(token);
        // Validação simples de expiração (opcional mas recomendado)
        if (decoded.exp * 1000 > Date.now()) {
          setUser({ email: decoded.sub /* Adicione outros campos se o token os tiver */ });
        } else {
          // Token expirado
          localStorage.removeItem('token');
        }
      } catch (error) {
        console.error("Token inválido:", error);
        localStorage.removeItem('token');
      }
    }
    setLoading(false); // Finaliza o carregamento inicial
  }, [token]);

  const login = async (email, senha) => {
    try {
      const response = await api.post('/auth/login', { email, senha });
      const { token: newToken } = response.data;
      
      localStorage.setItem('token', newToken);
      const decoded = jwtDecode(newToken);
      setUser({ email: decoded.sub });
      setToken(newToken);
    } catch (error) {
      console.error("Falha no login", error);
      throw error; // Propaga o erro para ser tratado no formulário
    }
  };

  const logout = () => {
    localStorage.removeItem('token');
    setUser(null);
    setToken(null);
  };

  // Não renderiza a aplicação enquanto a verificação inicial do token não terminar
  if (loading) {
    return <div>Carregando...</div>;
  }

  return (
    <AuthContext.Provider value={{ isAuthenticated: !!user, user, token, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthContext;