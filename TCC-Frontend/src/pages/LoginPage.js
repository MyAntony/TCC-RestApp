import React from 'react';
import LoginForm from '../components/auth/LoginForm'; // Importando o formulário

const LoginPage = () => {
  // Estilos simples para centralizar o formulário na página
  const pageStyles = {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100vh',
    textAlign: 'center'
  };

  return (
    <div style={pageStyles}>
      <h1>Login do Sistema</h1>
      <p>Por favor, insira suas credenciais para continuar.</p>
      <LoginForm />
    </div>
  );
};

// A linha mais importante! Garanta que a exportação é 'default'.
export default LoginPage;