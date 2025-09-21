// src\components\ListaDePratos\index.js

import { useState, useEffect } from "react";
import axios from "axios";
import './styles.css'

function ListaDePratos()
{
    const [pratos, setPratos] = useState([])

    useEffect(() =>
        {
        const carregarPratos = async () =>
            {
            try
            {
                const response = await axios.get('http://localhost:8080/pratos')
                setPratos(response.data)
            } catch (error)
            {
                alert('Erro ao buscar pratos: ', error)
                setPratos([])
            }
        }
        carregarPratos()
    }, [])

    return (
        <ul id="listarTodos" className="listar-todos">
            {pratos.length === 0 ? (
                <li>Nenhum prato encontrado.</li>
            ) : (
                pratos.map(prato => {
                    console.log(prato.url); // Adicione esta linha
                    return (
                        <li key={prato.id}>
                            <strong>Nome: </strong> {prato.nome}<br />
                            <strong>Descricao: </strong> {prato.descricao}<br />
                            <strong>Preco: </strong> {prato.preco}<br />
                            <strong>Categoria: </strong> {prato.categoria}<br />
                            <strong>Imagem: </strong><br />
                            <img src={prato.url} alt={prato.nome} style={{maxWidth: '200px', maxHeight: '200px'}} /><br />
                            <strong>Disponibilidade: </strong> {prato.disponibilidade}<br />
                        </li>
                    )
                })
            )}
        </ul>
    )
   
}

export default ListaDePratos