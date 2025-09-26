// src\components\MensagemFeedback\index.js

import './styles.css'

function MensagemFeedback({ mensagem, tipo, visivel, onclose })
{
    if (!visivel)
        {
        return null
    }

    return (
        <div 
            id='mensagem' 
            className={`mensagem ${tipo} visivel`}
            onClick={onclose}
        >
            {mensagem}
        </div>
    )
}

export default MensagemFeedback