import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import { AuthProvider } from './contexts/AuthContext';
import PrivateRoute from './routes/PrivateRoute';

// Suas páginas e componentes
import LoginPage from './pages/LoginPage'; // Sua nova página de login
import FormularioCadastro from './components/FormularioCadastro'; // Sua página inicial
import ListaDeProdutos from './components/ListaDeProdutos'; // Sua página de produtos

import './App.css'; 

function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          {/* ROTA PÚBLICA */}
          {/* A única página que não precisa de login é a de login. */}
          <Route path="/login" element={<LoginPage />} />

          {/* ROTAS PRIVADAS */}
          {/* Agrupamos aqui todas as páginas que exigem autenticação. */}
          <Route element={<PrivateRoute />}>
            <Route path="/" element={<FormularioCadastro />} />
            <Route path="/produtos" element={<ListaDeProdutos />} />
            {/* Adicione outras rotas privadas aqui se necessário */}
          </Route>

          {/* Rota para páginas não encontradas */}
          <Route path="*" element={<h1>404 - Página Não Encontrada</h1>} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;