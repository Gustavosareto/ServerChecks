# 🎮 Comandos de Teste - ServerChecks

Este arquivo contém comandos prontos para copiar e colar no servidor para testar o plugin.

---

## 🚀 Setup Inicial

```bash
# 1. Dar dinheiro inicial para teste
/eco give SeuNick 1000000

# 2. Verificar saldo
/money

# 3. Verificar se plugin está carregado
/plugins
```

---

## 💰 Testes de Criação de Cheques

### Teste Básico
```bash
# Criar cheque simples de $1000
/cheque criar 1000
```

### Com Mensagem Personalizada
```bash
# Criar cheque com mensagem
/cheque criar 5000 Parabéns pelo evento!

# Mensagem longa
/cheque criar 10000 Este é um prêmio especial para você por ter vencido o torneio de PvP!

# Emoji e cores (se suportado)
/cheque criar 2500 Presente especial :)
```

### Valores Diversos
```bash
# Valor baixo
/cheque criar 1

# Valor médio
/cheque criar 50000

# Valor alto
/cheque criar 1000000

# Valor decimal
/cheque criar 1234.56

# Com vírgula (será convertido para ponto)
/cheque criar 1234,56
```

---

## 📊 Testes de Informações

```bash
# Ver economia do servidor
/cheque info

# Usando alias
/check info

# Usando alias alternativo
/cheques info
```

---

## ❌ Testes de Validação (Devem Falhar)

### Valores Inválidos
```bash
# Valor negativo (DEVE FALHAR)
/cheque criar -100

# Valor zero (DEVE FALHAR)
/cheque criar 0

# Texto em vez de número (DEVE FALHAR)
/cheque criar abc

# Valor vazio (DEVE FALHAR)
/cheque criar
```

### Saldo Insuficiente
```bash
# Zerar saldo primeiro
/eco take SeuNick 999999999

# Tentar criar cheque maior que saldo (DEVE FALHAR)
/cheque criar 1000000

# Restaurar saldo
/eco give SeuNick 1000000
```

---

## 🔐 Testes de Segurança

### Teste Anti-Falsificação
```bash
# 1. Crie um cheque legítimo
/cheque criar 100

# 2. Resgate (deve funcionar)
# Clique direito com o cheque

# 3. Pegue um papel normal
/give SeuNick paper 1

# 4. Renomeie na bigorna: "Cheque de $999999"

# 5. Tente resgatar (NÃO DEVE FUNCIONAR)
# Clique direito com o papel falso
```

---

## ⚠️ Testes de Avisos Econômicos

```bash
# 1. Criar economia baixa artificialmente
/eco take @a 999999999

# 2. Dar apenas $1000 para você
/eco give SeuNick 1000

# 3. Aguardar 1-2 minutos (análise econômica)

# 4. Tentar criar cheque muito alto (DEVE AVISAR)
/cheque criar 500000

# 5. Confirmar (executar comando novamente em 10s)
/cheque criar 500000
```

---

## 🎯 Testes de Casos de Uso Reais

### Cenário 1: Transferência entre Jogadores

```bash
# JOGADOR A (quem vai dar o dinheiro):
/cheque criar 10000 Pagamento da venda

# Jogador A joga o cheque no chão (Q)
# ou usa /give JogadorB paper ... (se admin)

# JOGADOR B (quem vai receber):
# Pega o cheque
# Clica com botão direito
# Verifica saldo: /money
```

### Cenário 2: Premiação de Evento

```bash
# ADMIN cria cheques de prêmio:
/cheque criar 50000 1º Lugar - Torneio PvP
/cheque criar 30000 2º Lugar - Torneio PvP
/cheque criar 20000 3º Lugar - Torneio PvP

# Distribuir para os vencedores
# Vencedores resgatam quando quiserem
```

### Cenário 3: Salário de Facção

```bash
# Líder da facção cria salários:
/cheque criar 5000 Salário Semanal - Membro
/cheque criar 5000 Salário Semanal - Membro
/cheque criar 10000 Salário Semanal - Oficial
/cheque criar 10000 Salário Semanal - Oficial

# Distribui para os membros
```

---

## 🧪 Testes de Limites (Configure primeiro)

### Configurar Limites no config.yml
```yaml
cheque:
  minimum-value: 100
  maximum-value: 100000
```

### Testar Mínimo
```bash
# Recarregar config
/reload

# Tentar criar abaixo do mínimo (DEVE FALHAR)
/cheque criar 50

# Criar no mínimo (DEVE FUNCIONAR)
/cheque criar 100

# Criar acima do mínimo (DEVE FUNCIONAR)
/cheque criar 150
```

### Testar Máximo
```bash
# Tentar criar acima do máximo (DEVE FALHAR)
/cheque criar 200000

# Criar no máximo (DEVE FUNCIONAR)
/cheque criar 100000

# Criar abaixo do máximo (DEVE FUNCIONAR)
/cheque criar 99999
```

---

## 💸 Testes de Taxa de Criação

### Configurar Taxa no config.yml
```yaml
cheque:
  creation-fee: 5  # 5% de taxa
```

### Testar Taxa
```bash
# Recarregar config
/reload

# Verificar saldo antes
/money

# Criar cheque de $10.000
/cheque criar 10000
# Deve debitar: $10.000 + $500 (5%) = $10.500

# Verificar saldo depois
/money
# Diferença deve ser $10.500
```

---

## 🔄 Testes de Múltiplos Cheques

### Criar Vários Cheques Rapidamente
```bash
/cheque criar 100
/cheque criar 200
/cheque criar 300
/cheque criar 400
/cheque criar 500

# Verificar inventário (deve ter 5 cheques)
# Resgatar todos clicando direito
# Verificar saldo final
```

### Teste de Performance
```bash
# Criar 10 cheques
/cheque criar 1000
/cheque criar 1000
/cheque criar 1000
/cheque criar 1000
/cheque criar 1000
/cheque criar 1000
/cheque criar 1000
/cheque criar 1000
/cheque criar 1000
/cheque criar 1000

# Verificar TPS
/tps

# Resgatar todos
# Verificar TPS novamente
```

---

## 🎨 Testes de Personalização

### Mensagens em Diferentes Idiomas (config.yml)

```yaml
# Português
messages:
  cheque-created: "&aVocê criou um cheque de &2%value%&a!"

# Inglês
messages:
  cheque-created: "&aYou created a check of &2%value%&a!"

# Espanhol
messages:
  cheque-created: "&aCreaste un cheque de &2%value%&a!"
```

### Testar Cada Idioma
```bash
# Recarregar após cada mudança
/reload

# Criar cheque para ver mensagem
/cheque criar 1000
```

---

## 📋 Testes de Permissões

### Remover Permissões
```bash
# Remover permissão de criar (LuckPerms)
/lp user SeuNick permission unset serverchecks.criar

# Tentar criar (DEVE FALHAR)
/cheque criar 100

# Recolocar permissão
/lp user SeuNick permission set serverchecks.criar true
```

### Remover Permissão de Resgatar
```bash
# Criar um cheque primeiro
/cheque criar 100

# Remover permissão de resgatar
/lp user SeuNick permission unset serverchecks.resgatar

# Tentar resgatar (DEVE FALHAR)
# Clique direito no cheque

# Recolocar permissão
/lp user SeuNick permission set serverchecks.resgatar true
```

---

## 🔍 Testes de Console

### Comandos que Devem Falhar no Console
```bash
# No console do servidor (não no jogo):
cheque criar 1000
# DEVE RETORNAR: "Este comando só pode ser usado por jogadores!"

cheque info
# DEVE RETORNAR: "Este comando só pode ser usado por jogadores!"
```

---

## 📊 Verificação de Logs

### Após Cada Teste, Verifique:
```bash
# No console ou logs/latest.log procure por:
[ServerChecks] Análise econômica concluída: ...
[ServerChecks] ...

# Não deve haver:
[ServerChecks] ERROR ...
[ServerChecks] Exception ...
```

---

## ✅ Checklist de Testes Completo

Marque conforme testa:

### Básico
- [ ] Criar cheque simples
- [ ] Criar cheque com mensagem
- [ ] Resgatar cheque
- [ ] Comando /cheque info

### Validações
- [ ] Valor negativo (falha)
- [ ] Valor zero (falha)
- [ ] Valor inválido (falha)
- [ ] Saldo insuficiente (falha)

### Segurança
- [ ] Cheque falso não funciona
- [ ] Apenas criador pode criar
- [ ] Qualquer um pode resgatar

### Economia
- [ ] Análise econômica funciona
- [ ] Avisos para valores altos
- [ ] Sistema de confirmação

### Limites
- [ ] Valor mínimo respeitado
- [ ] Valor máximo respeitado
- [ ] Taxa de criação aplicada

### Performance
- [ ] Múltiplos cheques sem lag
- [ ] TPS normal após operações
- [ ] Análise assíncrona não trava

### Permissões
- [ ] Sem permissão não cria
- [ ] Sem permissão não resgata
- [ ] Sem permissão não vê info

### Aliases
- [ ] /check funciona
- [ ] /cheques funciona

---

## 🎯 Comandos de Demonstração (Para Vídeos/Screenshots)

### Setup Demo
```bash
# 1. Preparar jogador
/eco give JogadorDemo 1000000
/gamemode creative JogadorDemo

# 2. Criar cheques demonstrativos
/cheque criar 50000 Prêmio do Evento de Construção
/cheque criar 25000 Bônus de Participação
/cheque criar 10000 Presente de Boas-Vindas!

# 3. Ver economia
/cheque info
```

### Reset Demo
```bash
# Limpar tudo
/clear JogadorDemo
/eco set JogadorDemo 100000
```

---

## 🚀 Script de Teste Automatizado (Para Admins)

```bash
# Cole todos esses comandos de uma vez (se o servidor suportar)
eco give TestUser 1000000
cheque criar 100
cheque criar 500 Teste
cheque criar 1000 Teste com mensagem
cheque criar 5000
cheque criar 10000
cheque info
money
```

---

## 📝 Notas de Teste

### Anotar Durante Testes:

```
Data: ___/___/______
Versão do Plugin: 1.0.0
Versão do Servidor: _______________
Vault: _______________
Economia: _______________

Testes Realizados:
[ ] Criação de cheques
[ ] Resgate de cheques
[ ] Anti-falsificação
[ ] Análise econômica
[ ] Limites e validações
[ ] Permissões

Bugs Encontrados:
_________________________________
_________________________________
_________________________________

Performance (TPS):
Antes: ______
Durante: ______
Depois: ______

Observações:
_________________________________
_________________________________
_________________________________
```

---

**Versão:** 1.0.0  
**Última Atualização:** 26/11/2025  
**Compatibilidade:** Minecraft 1.8.8 (Spigot/Paper)

🧪 **Bons testes!**
