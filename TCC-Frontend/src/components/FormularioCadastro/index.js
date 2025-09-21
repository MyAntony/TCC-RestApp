import { useState } from "react";
import './styles.css'
import { useNavigate } from "react-router-dom";
import useMensagem from '../../hooks/useMensagem'
import MensagemFeedback from '../MensagemFeedback'
import logo from '../../assets/images/logo.png'
// 1. Importe o 'api' em vez do 'axios'
import api from '../../services/api'; 
// 2. Importe o hook de autenticação
import { useAuth } from '../../hooks/useAuth';

function FormularioCadastro() {
    const [nome, setNome] = useState('');
    const [descricao, setDescricao] = useState('');
    const [preco, setPreco] = useState('');
    const [categoria, setCategoria] = useState('');
    const [disponibilidade, setDisponibilidade] = useState('');
    const [url, setUrl] = useState('');
    const navigate = useNavigate();
    const { exibirMensagem, mensagem, tipoMensagem, visivel, fecharMensagem } = useMensagem();
    
    // 3. Obtenha o estado de autenticação do contexto
    const { isAuthenticated } = useAuth(); 

    const cadastrarProduto = async () => {
        // 4. A verificação agora é muito mais simples
        if (!isAuthenticated) {
            exibirMensagem("Você precisa estar logado para cadastrar um produto.", "erro");
            navigate("/login");
            return;
        }

        try {
            // 5. Use o 'api.post'. Não é mais necessário passar o header de autorização.
            //    O interceptor do Axios em api.js faz isso automaticamente!
            const response = await api.post('/produtos', { nome, descricao, preco, categoria, disponibilidade, url });
            
            exibirMensagem(response.data.message || 'Produto cadastrado com sucesso!', 'sucesso');
            // Limpa o formulário
            setNome('');
            setDescricao('');
            setPreco('');
            setCategoria('');
            setUrl('');
            setDisponibilidade('');
        } catch (error) {
            let erroMsg = 'Erro ao conectar ao servidor.';
            if (error.response && error.response.data) {
                erroMsg = error.response.data.message;
                if (error.response.data.errors) {
                    erroMsg += ' ' + Object.values(error.response.data.errors).join(', ');
                }
            }
            exibirMensagem(erroMsg, 'erro');
        }
    }

    return (
        <div className="container">
            <img src={logo} alt="Logo da empresa" />
            <h2>Cadastro de produtos</h2>
            <form onSubmit={(e) => { e.preventDefault(); cadastrarProduto() }}>
                {/* O restante do seu formulário permanece exatamente o mesmo */}
                <input type="text" id="nome" placeholder="Nome" value={nome} onChange={(e) => setNome(e.target.value)} required />
                <select id="categoria" value={categoria} onChange={(e) => setCategoria(e.target.value)} required >
                    <option value="">Selecione a categoria</option>
                    <option value="ENTRADA">Entrada</option>
                    <option value="SOBREMESA">Sobremesa</option>
                    <option value="PRATO_PRINCIPAL">Prato Principal</option>
                    <option value="BEBIDA">Bebida</option>
                </select>
                <input type="text" id="descricao" placeholder="Descricao" value={descricao} onChange={(e) => setDescricao(e.target.value)} required />
                <input type="number" id="preco" placeholder="Preco" value={preco} onChange={(e) => setPreco(e.target.value)} required />
                <input type="text" id="url" placeholder="Url da Imagem" value={url} onChange={(e) => setUrl(e.target.value)} required />
                <select id="disponibilidade" value={disponibilidade} onChange={(e) => setDisponibilidade(e.target.value)} required >
                    <option value="">Selecione a disponibilidade</option>
                    <option value="EM_ESTOQUE">Em Estoque</option>
                    <option value="ESGOTADO">Esgotado</option>
                </select>
                <button type="submit">Cadastrar</button>
            </form>

            <button onClick={() => navigate('/produtos')} className="link-produtos">
                Ver produtos cadastrados
            </button>

            <MensagemFeedback
                mensagem={mensagem}
                tipo={tipoMensagem}
                visivel={visivel}
                onclose={fecharMensagem}
            />
        </div>
    )
}

export default FormularioCadastro;