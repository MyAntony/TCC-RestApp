# 📚 Guia de Branches e Commits — RestApp

Este guia define o padrão que vamos utilizar no projeto **RestApp** para manter o histórico de commits e branches **organizado e claro**.

---

## Padrão de Branches

### Estrutura

### Tipos principais
- **feat/** → nova funcionalidade  
   `feat/crud-clientes-backend`  
   `feat/endereco`  
   `feat/contas-a-pagar`

- **fix/** → correção de bug  
   `fix/valida-cpf-cliente`  
   `fix/erro-salvar-fornecedor`

- **refactor/** → refatoração de código (sem mudar funcionalidade)  
   `refactor/service-cliente`  
   `refactor/mapeamento-endereco`

- **chore/** → manutenção, configs e tarefas que não afetam a lógica do app  
   `chore/atualiza-dependencias`  
   `chore/config-docker`

- **hotfix/** → correção urgente em produção  
   `hotfix/corrige-autenticacao`

###  Boas práticas
1. Use sempre **kebab-case** (palavras separadas por `-`).  
   → `feat/contas-a-pagar` ✅  
   → `feat_contas_a_pagar` ❌  
   → `featContasAPagar` ❌  

2. Seja **curto e claro**, mas mantenha contexto.  
   → `feat/crud-clientes` é melhor que `feat/clientes`.

3. Criar nova branch a partir da `main`:
git checkout main
git pull origin main
git checkout -b feat/contas-a-pagar
