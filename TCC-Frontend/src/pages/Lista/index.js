// src\pages\Lista\index.js

import ListaDeProdutos from '../../components/ListaDeProdutos'
import { useNavigate } from 'react-router-dom'
import './styles.css'

function PaginaListaProdutos()
{
    const navigate = useNavigate()
   
    return (
        <div className='pagina-lista-produtos'>
            <div className='container'>
                <h2>Lista de produtos</h2>
                <ListaDeProdutos />
                <button onClick={() => navigate('/cadastro')} className='link-voltar'>
                    Cadastrar produtos
                </button>
            </div>
        </div>
    )
}

export default PaginaListaProdutos