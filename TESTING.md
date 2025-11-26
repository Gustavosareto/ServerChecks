# Guia de Testes - ServerChecks

Este documento descreve como testar todas as funcionalidades do plugin ServerChecks.

## 🧪 Preparação do Ambiente de Testes

### 1. Servidor de Teste

1. Configure um servidor Spigot/Paper 1.8.8
2. Instale **Vault** na pasta plugins/
3. Instale um plugin de economia (recomendado: **EssentialsX**)
4. Compile e adicione **ServerChecks-1.0.0.jar**
5. Inicie o servidor

### 2. Verificação de Carregamento

Após iniciar o servidor, verifique no console:

```
[ServerChecks] Vault detectado! Sistema de economia carregado.
[ServerChecks] Análise econômica iniciada! Intervalo: 5 minutos.
[ServerChecks] ServerChecks v1.0.0 habilitado com sucesso!
```

## 📋 Casos de Teste

### Teste 1: Criação Básica de Cheque

**Objetivo**: Verificar criação de cheque com valor válido

```
1. Entre no servidor
2. Execute: /money set <seu_nome> 100000
3. Execute: /cheque criar 5000
4. Verifique se recebeu um papel no inventário
5. Verifique seu saldo: /money
   - Deve ter diminuído 5000
```

**Resultado Esperado**:
- ✅ Cheque criado com sucesso
- ✅ Valor debitado da conta
- ✅ Item de papel recebido
- ✅ Nome do item: "Cheque de $5.000,00"

---

### Teste 2: Criação com Mensagem

**Objetivo**: Criar cheque com mensagem personalizada

```
1. Execute: /cheque criar 1000 Parabéns pelo evento!
2. Verifique a lore do cheque
```

**Resultado Esperado**:
- ✅ Cheque criado com mensagem na lore
- ✅ Mensagem aparece formatada

---

### Teste 3: Saldo Insuficiente

**Objetivo**: Testar validação de saldo

```
1. Defina saldo baixo: /money set <seu_nome> 100
2. Tente criar cheque: /cheque criar 10000
```

**Resultado Esperado**:
- ❌ Mensagem de erro: "Você não tem saldo suficiente!"
- ✅ Nenhum cheque criado

---

### Teste 4: Valor Inválido

**Objetivo**: Testar validação de valores

```
1. Execute: /cheque criar -500
2. Execute: /cheque criar abc
3. Execute: /cheque criar 0
```

**Resultado Esperado**:
- ❌ Mensagem: "Valor inválido!"
- ✅ Nenhum cheque criado

---

### Teste 5: Resgate de Cheque

**Objetivo**: Verificar resgate de cheque válido

```
1. Crie um cheque: /cheque criar 2000
2. Anote seu saldo atual: /money
3. Segure o cheque na mão
4. Clique com BOTÃO DIREITO no ar
5. Verifique seu saldo novamente
```

**Resultado Esperado**:
- ✅ Mensagem: "Você resgatou um cheque de $2.000,00!"
- ✅ Saldo aumentou 2000
- ✅ Cheque removido do inventário

---

### Teste 6: Anti-Falsificação

**Objetivo**: Verificar proteção contra cheques falsos

```
1. Pegue um papel normal
2. Renomeie na bigorna: "Cheque de $999999"
3. Clique com botão direito segurando o papel
```

**Resultado Esperado**:
- ❌ Nada acontece OU mensagem: "Este não é um cheque válido!"
- ✅ Nenhum valor creditado

---

### Teste 7: Informações da Economia

**Objetivo**: Verificar análise econômica

```
1. Execute: /cheque info
```

**Resultado Esperado**:
```
========== Economia do Servidor ==========
Média de saldo: $XX,XXX.XX
Total circulante: $XXX,XXX.XX
Jogadores analisados: X
========================================
```

---

### Teste 8: Aviso de Valor Alto

**Objetivo**: Testar sistema de avisos

```
1. Configure economia baixa: /money set <seu_nome> 1000000
2. Aguarde 1 minuto (para análise econômica)
3. Execute: /cheque criar 500000
```

**Resultado Esperado**:
- ⚠️ Mensagem de aviso sobre valor alto
- ✅ Solicitação de confirmação
- ✅ Execute o comando novamente em 10s para confirmar

---

### Teste 9: Confirmação de Valor Alto

**Objetivo**: Testar sistema de confirmação

```
1. Execute: /cheque criar 999999
2. Aguarde a mensagem de aviso
3. Execute NOVAMENTE: /cheque criar 999999 (dentro de 10s)
```

**Resultado Esperado**:
- ✅ Primeira vez: Aviso exibido
- ✅ Segunda vez (em 10s): Cheque criado
- ✅ Após 10s: Confirmação expirada, precisa confirmar novamente

---

### Teste 10: Inventário Cheio

**Objetivo**: Testar validação de inventário

```
1. Encha completamente o inventário
2. Execute: /cheque criar 100
```

**Resultado Esperado**:
- ❌ Mensagem: "Seu inventário está cheio!"
- ✅ Valor NÃO debitado
- ✅ Nenhum cheque criado

---

### Teste 11: Permissões

**Objetivo**: Testar sistema de permissões

```
1. Remova permissão: /lp user <seu_nome> permission unset serverchecks.criar
2. Tente: /cheque criar 100
3. Recoloque: /lp user <seu_nome> permission set serverchecks.criar
```

**Resultado Esperado**:
- ❌ Sem permissão: "Você não tem permissão para isso!"
- ✅ Com permissão: Funciona normalmente

---

### Teste 12: Aliases de Comandos

**Objetivo**: Verificar aliases funcionando

```
1. Execute: /check criar 100
2. Execute: /cheques info
```

**Resultado Esperado**:
- ✅ Todos os aliases funcionam igual ao /cheque

---

### Teste 13: Valor Mínimo/Máximo

**Objetivo**: Testar limites configurados

```
1. No config.yml, defina:
   minimum-value: 100
   maximum-value: 10000
2. Recarregue: /reload
3. Teste: /cheque criar 50
4. Teste: /cheque criar 20000
```

**Resultado Esperado**:
- ❌ Valor < mínimo: Mensagem de erro
- ❌ Valor > máximo: Mensagem de erro
- ✅ Valor entre limites: Funciona

---

### Teste 14: Taxa de Criação

**Objetivo**: Testar taxa de criação de cheques

```
1. No config.yml, defina: creation-fee: 5 (5%)
2. Recarregue: /reload
3. Anote saldo: /money
4. Execute: /cheque criar 1000
5. Verifique saldo novamente
```

**Resultado Esperado**:
- ✅ Debitado: 1000 + (1000 * 5%) = 1050
- ✅ Cheque de valor 1000 criado

---

### Teste 15: Análise Assíncrona

**Objetivo**: Verificar análise em background

```
1. Entre no servidor
2. Monitore o console
3. Aguarde o intervalo configurado (padrão: 5 minutos)
```

**Resultado Esperado**:
- ✅ Mensagem no console: "Análise econômica concluída..."
- ✅ Sem lag perceptível
- ✅ Valores atualizados em /cheque info

---

## 🔍 Testes de Estresse

### Teste 16: Múltiplos Cheques

```
1. Crie 10 cheques rapidamente
2. Resgate todos
3. Verifique saldo final
```

**Resultado Esperado**:
- ✅ Todos os cheques funcionam
- ✅ Saldo correto

---

### Teste 17: Jogadores Simultâneos

```
1. Entre com 2+ contas
2. Cada uma cria cheques
3. Troque cheques entre jogadores
4. Resgate cheques de outros jogadores
```

**Resultado Esperado**:
- ✅ Cheques funcionam entre jogadores
- ✅ Qualquer um pode resgatar qualquer cheque

---

## 📊 Checklist de Testes Completo

- [ ] Criação básica de cheque
- [ ] Criação com mensagem
- [ ] Validação de saldo insuficiente
- [ ] Validação de valores inválidos
- [ ] Resgate de cheque válido
- [ ] Anti-falsificação
- [ ] Comando /cheque info
- [ ] Sistema de avisos
- [ ] Sistema de confirmação
- [ ] Inventário cheio
- [ ] Sistema de permissões
- [ ] Aliases de comandos
- [ ] Valores mínimo/máximo
- [ ] Taxa de criação
- [ ] Análise assíncrona
- [ ] Múltiplos cheques
- [ ] Jogadores simultâneos

---

## 🐛 Como Reportar Bugs

Ao encontrar um bug, forneça:

1. **Versão do servidor**: Spigot/Paper versão
2. **Versão do plugin**: ServerChecks v1.0.0
3. **Plugins instalados**: Lista completa
4. **Passos para reproduzir**: Detalhado
5. **Comportamento esperado**: O que deveria acontecer
6. **Comportamento atual**: O que está acontecendo
7. **Logs/Erros**: Console e/ou stacktrace

---

## ✅ Conclusão

Ao completar todos os testes, o plugin deve:
- ✅ Criar cheques corretamente
- ✅ Resgatar cheques sem erros
- ✅ Bloquear cheques falsos
- ✅ Analisar economia automaticamente
- ✅ Respeitar todas as configurações
- ✅ Funcionar sem lag ou travamentos
