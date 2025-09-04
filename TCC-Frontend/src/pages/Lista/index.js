// src\pages\Lista\index.js

import ListaDePratos from '../../components/ListaDePratos'
import { useNavigate } from 'react-router-dom'
import './styles.css'

function PaginaListaPratos()
{
    const navigate = useNavigate()
   
    return (
        <div className='pagina-lista-pratos'>
            <div className='container'>
                <h2>Lista de pratos</h2>
                <ListaDePratos />
                <button onClick={() => navigate('/cadastro')} className='link-voltar'>
                    Cadastrar pratos
                </button>
            </div>
        </div>
    )
}

export default PaginaListaPratos