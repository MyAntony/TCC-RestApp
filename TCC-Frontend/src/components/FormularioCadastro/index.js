import { useState, useEffect } from "react"; // 👈 adicione useEffect aqui
import './styles.css'
import { useNavigate } from "react-router-dom";
import useMensagem from '../../hooks/useMensagem'
import MensagemFeedback from '../MensagemFeedback'
import logo from '../../assets/images/logo.png'
import api from '../../services/api'; 
import { useAuth } from '../../hooks/useAuth';

function FormularioCadastro() {
    const [nome, setNome] = useState('');
    const [descricao, setDescricao] = useState('');
    const [preco, setPreco] = useState('');
    const [categoria, setCategoria] = useState('');
    const [categorias, setCategorias] = useState([]); // 👈 novo estado
    const [disponibilidade, setDisponibilidade] = useState('');
    const [url, setUrl] = useState('');
    const navigate = useNavigate();
    const { exibirMensagem, mensagem, tipoMensagem, visivel, fecharMensagem } = useMensagem();
    const { isAuthenticated } = useAuth(); 

    // 👇 useEffect entra logo depois dos useStates
    useEffect(() => {
        const carregarCategorias = async () => {
            try {
                const response = await api.get('/produtos/categoria-produtos');
                console.log("Categorias carregadas:", response.data);
                setCategorias(response.data);
            } catch (error) {
                console.error("Erro ao carregar categorias:", error);
                exibirMensagem("Não foi possível carregar as categorias.", "erro");
            }
        };
        carregarCategorias();
    }, []); // executa apenas uma vez ao montar o componente

    const cadastrarProduto = async () => {
        if (!isAuthenticated) {
            exibirMensagem("Você precisa estar logado para cadastrar um produto.", "erro");
            navigate("/login");
            return;
        }

        try {
            const response = await api.post('/produtos/produto', { nome, descricao, preco, categoria, disponibilidade, url });
            
            exibirMensagem(response.data.message || 'Produto cadastrado com sucesso!', 'sucesso');
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
                <input type="text" id="nome" placeholder="Nome" value={nome} onChange={(e) => setNome(e.target.value)} required />

                <select id="categoria" value={categoria} onChange={(e) => setCategoria(e.target.value)} required >
                    <option value="">Selecione a categoria</option>
                    {categorias.map(cat => (
                        <option key={cat.id} value={cat.id}>{cat.nomeCategoriaProdutos}</option>
                    ))}
                </select>

                <input type="text" id="descricao" placeholder="Descrição" value={descricao} onChange={(e) => setDescricao(e.target.value)} required />
                <input type="number" id="preco" placeholder="Preço" value={preco} onChange={(e) => setPreco(e.target.value)} required />
                <input type="text" id="url" placeholder="URL da Imagem" value={url} onChange={(e) => setUrl(e.target.value)} required />

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
