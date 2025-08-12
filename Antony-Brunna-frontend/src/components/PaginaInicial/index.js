// src\components\PaginaInicial\index.js

import { useNavigate } from "react-router-dom";
import './styles.css'
import logo from '../../assets/images/logo.png'

function PaginaInicial() {
    const navigate = useNavigate()

    return (
        <div className='pagina-inicial'>
            <img src={logo} alt="Logo da empresa" />
            <h2>Cadastro de pratos</h2>
            <h2>Bem-vindo!</h2>
            <button onClick={() => navigate('/cadastro')}>Cadastrar Prato</button>
            <button onClick={() => navigate('/pratos')}>Ver Pratos Cadastrados</button>
        </div>
    )
}

export default PaginaInicial