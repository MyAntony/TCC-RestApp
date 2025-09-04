import { useNavigate, useLocation } from 'react-router-dom';
import './styles.css';

function BarraBotoes() {
  const navigate = useNavigate();
  const location = useLocation();

  return (
    <div className="barra-botoes">
      <button type="button" onClick={() => navigate('/')} disabled={location.pathname === '/'}>
        Início
      </button>
      <button type="button" onClick={() => navigate('/pratos')} disabled={location.pathname === '/pratos'}>
        Ver pratos cadastrados
      </button>
      <button type="button" onClick={() => navigate('/cadastro')} disabled={location.pathname === '/cadastro'}>
        Cadastrar prato
      </button>
    </div>
  );
}

export default BarraBotoes;
