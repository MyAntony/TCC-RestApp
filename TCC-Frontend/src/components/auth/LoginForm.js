import React, { useState } from 'react';
import { useAuth } from '../../hooks/useAuth'; // Nosso hook customizado
import { useNavigate } from 'react-router-dom'; // Para redirecionar após o login

const LoginForm = () => {
  // Hooks
  const { login } = useAuth();
  const navigate = useNavigate();

  // Estados do componente
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [error, setError] = useState(''); // Para armazenar mensagens de erro da API
  const [isLoading, setIsLoading] = useState(false); // Para mostrar um feedback de carregamento

  // Função para lidar com a submissão do formulário
  const handleSubmit = async (event) => {
    event.preventDefault(); // Impede o recarregamento padrão da página
    
    // Validação simples
    if (!email || !senha) {
      setError('Por favor, preencha todos os campos.');
      return;
    }

    setIsLoading(true); // Inicia o carregamento
    setError(''); // Limpa erros anteriores

    try {
      // Chama a função de login do nosso AuthContext
      await login(email, senha);
      
      // Se o login for bem-sucedido, redireciona para a página de admin
      navigate('/admin'); 

    } catch (err) {
      // Se o login falhar, o erro lançado no AuthContext é capturado aqui
      setError('Email ou senha inválidos. Tente novamente.');
      console.error(err); // Loga o erro real no console para depuração
    } finally {
      setIsLoading(false); // Finaliza o carregamento, independentemente do resultado
    }
  };

  // Estilos simples para o formulário (você pode substituir por CSS)
  const styles = {
    form: {
      display: 'flex',
      flexDirection: 'column',
      gap: '15px',
      maxWidth: '300px',
      margin: '0 auto',
    },
    input: {
      padding: '10px',
      fontSize: '16px',
      borderRadius: '5px',
      border: '1px solid #ccc',
    },
    button: {
      padding: '10px',
      fontSize: '16px',
      borderRadius: '5px',
      border: 'none',
      backgroundColor: '#007bff',
      color: 'white',
      cursor: 'pointer',
    },
    error: {
      color: 'red',
      textAlign: 'center',
    },
    loading: {
      textAlign: 'center',
    }
  };

  return (
    <form onSubmit={handleSubmit} style={styles.form}>
      {/* Exibe a mensagem de erro, se houver */}
      {error && <p style={styles.error}>{error}</p>}
      
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        style={styles.input}
        disabled={isLoading} // Desabilita o campo durante o carregamento
      />
      <input
        type="password"
        placeholder="Senha"
        value={senha}
        onChange={(e) => setSenha(e.target.value)}
        style={styles.input}
        disabled={isLoading}
      />
      <button type="submit" style={styles.button} disabled={isLoading}>
        {/* Muda o texto do botão durante o carregamento */}
        {isLoading ? 'Entrando...' : 'Entrar'}
      </button>
    </form>
  );
};

export default LoginForm;