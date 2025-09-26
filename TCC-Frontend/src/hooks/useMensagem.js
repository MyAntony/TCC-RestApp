import { useState, useCallback } from 'react';

function useMensagem()
{
    const [mensagem, setMensagem] = useState('')
    const [tipoMensagem, setTipoMensagem] = useState('')
    const [visivel, setVisivel] = useState('')

    const exibirMensagem = useCallback((texto, tipo = 'sucesso') =>
    {
        setMensagem(texto)
        setTipoMensagem(tipo)
        setVisivel(true)
        setTimeout(() => setVisivel(false), 5000)
    }, [])

    const fecharMensagem = useCallback(() =>
    {
        setVisivel(false)
    }, [])

    return { mensagem, tipoMensagem, visivel, exibirMensagem, fecharMensagem }
}

export default useMensagem