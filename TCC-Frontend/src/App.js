import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import PaginaCadastro from './pages/Cadastro';
import PaginaListaPratos from './pages/Lista';
import PaginaInicial from './components/PaginaInicial';
import './App.css'; // Para estilos globais, se necessário

function App()
{
  return (
    <Router>
      <Routes>
        <Route path="/" element={<PaginaInicial />} />
        <Route path="/cadastro" element={<PaginaCadastro />} />
        <Route path="/pratos" element={<PaginaListaPratos />} />
      </Routes>
    </Router>
  );
}

export default App