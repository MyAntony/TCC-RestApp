FROM node:16-alpine

WORKDIR /app

# Copia apenas os arquivos de dependências primeiro (melhora o cache)
COPY package*.json ./

# Instala dependências
RUN npm install

# Copia o restante do código
COPY . .

# Expõe a porta usada pelo React (modo dev)
EXPOSE 3000

# Comando de inicialização
CMD ["npm", "start"]
