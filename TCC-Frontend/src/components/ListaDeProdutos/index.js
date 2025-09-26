import { useState, useEffect } from "react";
// 1. Importe o 'api' em vez do 'axios'
import api from "../../services/api"; 
import './styles.css';

function ListaDeProdutos() {
    const [produtos, setProdutos] = useState([]);

    useEffect(() => {
        const carregarProdutos = async () => {
            try {
                // 2. Use o 'api.get'. O token será adicionado automaticamente.
                const response = await api.get('/produtos');
                setProdutos(response.data);
            } catch (error) {
                // Se o token for inválido ou expirar, a API retornará um erro 401 ou 403.
                // O PrivateRoute já deve ter prevenido o acesso, mas isso é uma segurança extra.
                console.error('Erro ao buscar produtos: ', error);
                alert('Sua sessão expirou. Por favor, faça login novamente.');
                setProdutos([]);
            }
        }
        carregarProdutos();
    }, []);

    return (
        <ul id="listarTodos" className="listar-todos">
            {produtos.length === 0 ? (
                <li>Nenhum produto encontrado.</li>
            ) : (
                produtos.map(produto => (
                    <li key={produto.id}>
                        <strong>Nome: </strong> {produto.nome}<br />
                        <strong>Descricao: </strong> {produto.descricao}<br />
                        <strong>Preco: </strong> {produto.preco}<br />
                        <strong>Categoria: </strong> {produto.categoria}<br />
                        <strong>Imagem: </strong><br />
                        <img src={produto.url} alt={produto.nome} style={{ maxWidth: '200px', maxHeight: '200px' }} /><br />
                        <strong>Disponibilidade: </strong> {produto.disponibilidade}<br />
                    </li>
                ))
            )}
        </ul>
    );
}

export default ListaDeProdutos;