# ⚡ Início Rápido - ServerChecks

Guia rápido para colocar o plugin funcionando em 5 minutos!

## 🚀 Instalação Rápida

### 1️⃣ Compilar o Plugin

**Windows:**
```batch
build.bat
```

**Linux/Mac:**
```bash
chmod +x build.sh
./build.sh
```

**Resultado:** `target/ServerChecks-1.0.0.jar`

---

### 2️⃣ Instalar no Servidor

1. **Pare o servidor**
2. **Copie** `ServerChecks-1.0.0.jar` para a pasta `plugins/`
3. **Certifique-se** que **Vault** está instalado
4. **Certifique-se** que um **plugin de economia** está instalado (EssentialsX, etc.)
5. **Inicie o servidor**

---

### 3️⃣ Verificar Instalação

No console, procure por:
```
[ServerChecks] Vault detectado! Sistema de economia carregado.
[ServerChecks] ServerChecks v1.0.0 habilitado com sucesso!
```

No jogo:
```
/plugins
```
**ServerChecks** deve aparecer em **VERDE** ✅

---

## 🎮 Primeiros Passos

### Criar seu Primeiro Cheque

1. **Dê dinheiro para você:**
   ```
   /money set <seu_nick> 100000
   ```

2. **Crie um cheque:**
   ```
   /cheque criar 5000
   ```

3. **Verifique o item no inventário**
   - Deve ser um papel chamado "Cheque de **$5k**"
   - Note o formato compacto: 5000 = $5k

4. **Resgate o cheque:**
   - Segure o papel na mão
   - Clique com **botão direito**
   - Você receberá o valor de volta!

### Testar Diferentes Valores

```
/cheque criar 1000      # Cria cheque de $1k
/cheque criar 50000     # Cria cheque de $50k
/cheque criar 200000    # Cria cheque de $200k
/cheque criar 1500000   # Cria cheque de $1.5M
```

---

### Ver Economia do Servidor

```
/cheque info
```

**Saída esperada:**
```
========== Economia do Servidor ==========
Média de saldo: $50.000,00
Total circulante: $500.000,00
Jogadores analisados: 10
========================================
```

---

## 📋 Comandos Essenciais

| Comando | O que faz |
|---------|-----------|
| `/cheque criar 1000` | Cria cheque de $1.000 |
| `/cheque criar 1k` | Cria cheque de $1k (mesmo que 1000) |
| `/cheque criar 50k` | Cria cheque de $50k (mesmo que 50000) |
| `/cheque criar 1M` | Cria cheque de $1M (mesmo que 1000000) |
| `/cheque criar 5000 Presente!` | Cria cheque com mensagem |
| `/cheque info` | Mostra economia do servidor |
| `/check criar 100` | Mesmo que /cheque (alias) |

**💡 Dica:** Use `k` para milhares e `M` para milhões nos comandos!

---

## 🎨 Personalizar Mensagens

### Editar Cores e Textos

1. **Pare o servidor**
2. **Edite** `plugins/ServerChecks/config.yml`
3. **Modifique** a seção `messages:`

**Exemplo:**
```yaml
messages:
  prefix: "&8[&b$Cheques&8]&r"  # Muda cor do prefixo
  cheque-created: "&d✨ Cheque de %value% criado!"
```

4. **Salve o arquivo**
5. **Inicie o servidor** ou use `/reload`

---

## ⚙️ Configurações Úteis

### Limitar Valores de Cheques

```yaml
cheque:
  minimum-value: 100      # Mínimo $100
  maximum-value: 1000000  # Máximo $1.000.000
```

### Adicionar Taxa de Criação

```yaml
cheque:
  creation-fee: 5  # 5% de taxa
  # Criar cheque de $1000 custará $1050
```

### Ajustar Análise Econômica

```yaml
economy-scan:
  enabled: true
  interval-minutes: 10  # Analisar a cada 10 minutos
  include-offline-players: false  # Mais rápido
```

---

## 🛡️ Testar Anti-Falsificação

### Teste 1: Cheque Legítimo
```
1. /cheque criar 100
2. Clique direito
3. ✅ Deve resgatar normalmente
```

### Teste 2: Cheque Falso
```
1. Pegue um papel normal
2. Renomeie na bigorna: "Cheque de $999999"
3. Clique direito
4. ❌ Não funciona! (Sistema anti-falsificação ativo)
```

---

## 🔧 Problemas Comuns

### ❌ Plugin não carrega

**Problema:** ServerChecks não aparece em `/plugins`

**Solução:**
1. Verifique se **Vault** está instalado
2. Verifique se há plugin de **economia** (EssentialsX, etc.)
3. Confira os logs: `logs/latest.log`

---

### ❌ "Vault não encontrado"

**Problema:** Mensagem no console sobre Vault

**Solução:**
1. Baixe [Vault](https://www.spigotmc.org/resources/vault.34315/)
2. Coloque em `plugins/Vault.jar`
3. Reinicie o servidor

---

### ❌ Cheque não funciona

**Problema:** Clica direito e nada acontece

**Solução:**
1. Verifique se tem permissão `serverchecks.resgatar`
2. Confira se é um cheque legítimo (criado pelo plugin)
3. Veja se há erros no console

---

### ❌ Economia não detectada

**Problema:** Erro ao criar cheque

**Solução:**
1. Instale plugin de economia (EssentialsX recomendado)
2. Configure a economia: `/eco give <nick> 1000`
3. Teste: `/balance`

---

## 📊 Exemplo de Uso Real

### Cenário: Recompensar Jogador

**Admin quer dar $50.000 a um jogador:**

```
# Opção 1: Dar dinheiro direto
/eco give JogadorX 50000

# Opção 2: Criar cheque e dar ao jogador
/cheque criar 50000 Parabéns pela conquista!
# (Depois dê o item ao jogador)
```

### Cenário: Transferência entre Jogadores

**JogadorA quer pagar JogadorB:**

```
# JogadorA:
/cheque criar 10000 Pagamento combinado

# JogadorA joga o cheque no chão ou dá ao JogadorB

# JogadorB:
# Pega o cheque e clica direito
# ✅ Recebe $10.000
```

---

## 🎯 Checklist de Sucesso

Marque quando completar:

- [ ] Plugin compilado
- [ ] Vault instalado
- [ ] Economia instalada
- [ ] ServerChecks instalado
- [ ] Plugin carrega (verde em /plugins)
- [ ] Comando /cheque funciona
- [ ] Criou um cheque de teste
- [ ] Resgatou o cheque
- [ ] Testou anti-falsificação
- [ ] Viu /cheque info
- [ ] Configurou mensagens (opcional)
- [ ] Ajustou limites (opcional)

**Todos marcados?** 🎉 **Parabéns! Está tudo funcionando!**

---

## 📚 Próximos Passos

Agora que está funcionando:

1. **Leia** [README.md](README.md) - Documentação completa
2. **Teste** seguindo [TESTING.md](TESTING.md)
3. **Personalize** usando [config-example.yml](config-example.yml)
4. **Compartilhe** com seus jogadores!

---

## 💡 Dicas Pro

### Dica 1: Comando Rápido
```
# Alias curto
/check criar 1000
```

### Dica 2: Economia Estável
```yaml
# Evite inflação
cheque:
  maximum-value: 1000000
  creation-fee: 2  # Taxa de 2%
```

### Dica 3: Performance
```yaml
# Servidor grande?
economy-scan:
  interval-minutes: 15
  include-offline-players: false
```

### Dica 4: Mensagens Personalizadas
```yaml
# Deixe no seu estilo!
messages:
  prefix: "&6[&e💰 Cheques&6]&r"
  cheque-created: "&a✓ Cheque de %value% criado com sucesso!"
```

---

## 🆘 Suporte

**Encontrou problema?**
1. Verifique [DEPLOY.md](DEPLOY.md) - Troubleshooting
2. Confira `logs/latest.log`
3. Teste seguindo [TESTING.md](TESTING.md)

**Tudo funcionando?**
🎉 **Aproveite o ServerChecks!**

---

## ⏱️ Tempo Estimado

- ⚡ **Instalação básica:** 2-3 minutos
- ⚙️ **Configuração inicial:** 5-10 minutos
- 🎨 **Personalização completa:** 15-30 minutos

---

**Versão:** 1.0.0  
**Compatibilidade:** Minecraft 1.8.8 (Spigot/Paper)  
**Dependências:** Vault + Economia

🚀 **Bom jogo!**
