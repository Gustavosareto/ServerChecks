# 💰 Exemplos de Formatação de Valores - ServerChecks

Este documento mostra exemplos práticos de como os valores são formatados no plugin.

## 🎯 Formato Compacto (Padrão)

O formato compacto é ativado por padrão e torna os valores muito mais legíveis.

### Configuração
```yaml
cheque:
  compact-format: true
  currency-symbol: "$"
```

### Exemplos de Valores

| Valor Numérico | Formatado | Descrição |
|----------------|-----------|-----------|
| `1` | `$1` | Unidades |
| `50` | `$50` | Dezenas |
| `100` | `$100` | Centenas |
| `500` | `$500` | Centenas |
| `999` | `$999` | Abaixo de mil |
| `1000` | `$1k` | Mil exato |
| `1500` | `$1.5k` | Mil e quinhentos |
| `2000` | `$2k` | Dois mil |
| `5000` | `$5k` | Cinco mil |
| `10000` | `$10k` | Dez mil |
| `15500` | `$15.5k` | Quinze mil e quinhentos |
| `50000` | `$50k` | Cinquenta mil |
| `99000` | `$99k` | Noventa e nove mil |
| `100000` | `$100k` | Cem mil |
| `200000` | `$200k` | Duzentos mil |
| `500000` | `$500k` | Quinhentos mil |
| `999000` | `$999k` | Novecentos e noventa e nove mil |
| `1000000` | `$1M` | Um milhão |
| `1500000` | `$1.5M` | Um milhão e meio |
| `2000000` | `$2M` | Dois milhões |
| `5000000` | `$5M` | Cinco milhões |
| `5500000` | `$5.5M` | Cinco milhões e meio |
| `10000000` | `$10M` | Dez milhões |
| `50000000` | `$50M` | Cinquenta milhões |
| `100000000` | `$100M` | Cem milhões |
| `1000000000` | `$1000M` | Um bilhão |

### Exemplos em Comandos

**Formato Numérico Tradicional:**
```
/cheque criar 1000
→ Cria cheque de "$1k"

/cheque criar 50000 Pagamento do evento
→ Cria cheque de "$50k"

/cheque criar 1500000 Parabéns!
→ Cria cheque de "$1.5M"

/cheque criar 200000
→ Cria cheque de "$200k"
```

**✨ NOVO: Formato Compacto nos Comandos!**

Agora você pode digitar valores usando k e M:

```
/cheque criar 1k
→ Mesmo que /cheque criar 1000
→ Cria cheque de "$1k"

/cheque criar 50k
→ Mesmo que /cheque criar 50000
→ Cria cheque de "$50k"

/cheque criar 1.5k
→ Mesmo que /cheque criar 1500
→ Cria cheque de "$1.5k"

/cheque criar 200k Prêmio do evento
→ Mesmo que /cheque criar 200000 Prêmio do evento
→ Cria cheque de "$200k"

/cheque criar 1M
→ Mesmo que /cheque criar 1000000
→ Cria cheque de "$1M"

/cheque criar 2.5M
→ Mesmo que /cheque criar 2500000
→ Cria cheque de "$2.5M"

/cheque criar 5.5M Parabéns!
→ Mesmo que /cheque criar 5500000 Parabéns!
→ Cria cheque de "$5.5M"
```

**Todos esses formatos são aceitos:**
- `1k`, `1K` → 1.000
- `5.5k`, `5,5k` → 5.500
- `50k`, `50K` → 50.000
- `1m`, `1M` → 1.000.000
- `2.5m`, `2,5M` → 2.500.000

### Exemplos de Mensagens

```
✅ Você criou um cheque de $10k!
✅ Você resgatou um cheque de $200k!
⚠ O valor está 150x acima da média da economia ($50k)!
```

### Informações de Economia

```
========== Economia do Servidor ==========
Média de saldo: $75k
Total circulante: $5.5M
Jogadores analisados: 50
========================================
```

---

## ⌨️ Entrada de Valores Compactos

### Como Funciona

O plugin aceita valores tanto no formato numérico tradicional quanto no formato compacto!

### Tabela de Conversão Automática

| Você digita | Plugin interpreta | Cheque criado |
|-------------|-------------------|---------------|
| `1k` ou `1K` | 1.000 | $1k |
| `5k` | 5.000 | $5k |
| `10k` | 10.000 | $10k |
| `50k` | 50.000 | $50k |
| `100k` | 100.000 | $100k |
| `200k` | 200.000 | $200k |
| `500k` | 500.000 | $500k |
| `1m` ou `1M` | 1.000.000 | $1M |
| `5m` ou `5M` | 5.000.000 | $5M |
| `10M` | 10.000.000 | $10M |

### Com Decimais

| Você digita | Plugin interpreta | Cheque criado |
|-------------|-------------------|---------------|
| `1.5k` | 1.500 | $1.5k |
| `2.5k` | 2.500 | $2.5k |
| `5.75k` | 5.750 | $5.75k |
| `10.5k` | 10.500 | $10.5k |
| `50.5k` | 50.500 | $50.5k |
| `1.5M` | 1.500.000 | $1.5M |
| `2.5M` | 2.500.000 | $2.5M |
| `5.75M` | 5.750.000 | $5.75M |

### Alternativas Aceitas

O plugin é flexível e aceita várias formas:

```
Vírgula ou ponto como decimal:
✅ 1.5k  → 1500
✅ 1,5k  → 1500
✅ 2.5M  → 2500000
✅ 2,5M  → 2500000

Maiúsculas ou minúsculas:
✅ 1k    → 1000
✅ 1K    → 1000
✅ 1m    → 1000000
✅ 1M    → 1000000

Espaços (serão ignorados):
✅ 1k    → 1000
✅ 1 k   → 1000 (espaços removidos)
```

### Exemplos Práticos

```bash
# Criar cheque de mil
/cheque criar 1k
# Resultado: Você criou um cheque de $1k!

# Criar cheque de dez mil com mensagem
/cheque criar 10k Obrigado pela ajuda!
# Resultado: Você criou um cheque de $10k!

# Criar cheque de meio milhão
/cheque criar 500k
# Resultado: Você criou um cheque de $500k!

# Criar cheque de um milhão e meio
/cheque criar 1.5M
# Resultado: Você criou um cheque de $1.5M!

# Todas essas formas funcionam igual:
/cheque criar 50k
/cheque criar 50K
/cheque criar 50.0k
/cheque criar 50000
# Todas criam cheque de $50k
```

### Comparação: Tradicional vs Compacto

```
┌─────────────────────────────────────────────────────────┐
│           FORMATO TRADICIONAL                           │
├─────────────────────────────────────────────────────────┤
│  /cheque criar 1000                                     │
│  /cheque criar 50000                                    │
│  /cheque criar 1000000                                  │
│  /cheque criar 2500000                                  │
└─────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────┐
│           FORMATO COMPACTO ✨ NOVO!                     │
├─────────────────────────────────────────────────────────┤
│  /cheque criar 1k          ← Mais rápido!               │
│  /cheque criar 50k         ← Menos dígitos!             │
│  /cheque criar 1M          ← Mais fácil!                │
│  /cheque criar 2.5M        ← Intuitivo!                 │
└─────────────────────────────────────────────────────────┘
```

### Vantagens do Formato Compacto

✅ **Mais rápido de digitar**
- `1M` vs `1000000` (2 caracteres vs 7)

✅ **Menos propenso a erros**
- Contar zeros é difícil: 1000000 ou 10000000?
- Com formato compacto: 1M ou 10M (claro!)

✅ **Mais legível**
- `/cheque criar 50k` é instantaneamente compreensível
- `/cheque criar 50000` precisa contar zeros

✅ **Consistente com a exibição**
- Você digita: `1k`
- Plugin mostra: `$1k`
- Tudo igual! ✨

### Casos de Uso Reais

**Admin distribuindo prêmios:**
```bash
# Antes (tradicional)
/cheque criar 100000
/cheque criar 100000
/cheque criar 100000

# Agora (compacto) ✨
/cheque criar 100k
/cheque criar 100k
/cheque criar 100k
```

**Jogador fazendo transferência grande:**
```bash
# Antes
/cheque criar 5000000 Pagamento da ilha

# Agora ✨
/cheque criar 5M Pagamento da ilha
```

**Criando vários cheques diferentes:**
```bash
/cheque criar 10k Ajudante
/cheque criar 50k Moderador
/cheque criar 100k Admin
/cheque criar 1M Dono
```

---

## 📊 Formato Tradicional

Se preferir o formato tradicional com separadores de milhar.

### Configuração
```yaml
cheque:
  compact-format: false
  value-format: "$%,.2f"
```

### Exemplos de Valores

| Valor Numérico | Formatado | Descrição |
|----------------|-----------|-----------|
| `1` | `$1.00` | Um |
| `100` | `$100.00` | Cem |
| `1000` | `$1,000.00` | Mil |
| `10000` | `$10,000.00` | Dez mil |
| `50000` | `$50,000.00` | Cinquenta mil |
| `200000` | `$200,000.00` | Duzentos mil |
| `1000000` | `$1,000,000.00` | Um milhão |
| `5500000` | `$5,500,000.00` | Cinco milhões e meio |

### Exemplos em Comandos

```
/cheque criar 1000
→ Cria cheque de "$1,000.00"

/cheque criar 200000
→ Cria cheque de "$200,000.00"
```

---

## 🌍 Símbolos de Moeda Personalizados

Você pode personalizar o símbolo da moeda!

### Exemplo: Real Brasileiro
```yaml
cheque:
  compact-format: true
  currency-symbol: "R$"
```

**Resultado:**
- `1000` → `R$1k`
- `200000` → `R$200k`
- `1500000` → `R$1.5M`

### Exemplo: Euro
```yaml
cheque:
  compact-format: true
  currency-symbol: "€"
```

**Resultado:**
- `1000` → `€1k`
- `200000` → `€200k`

### Exemplo: Libra
```yaml
cheque:
  compact-format: true
  currency-symbol: "£"
```

**Resultado:**
- `1000` → `£1k`
- `200000` → `£200k`

### Exemplo: Moedas/Coins
```yaml
cheque:
  compact-format: true
  currency-symbol: ""  # Vazio
```

**Resultado (sem símbolo):**
- `1000` → `1k`
- `200000` → `200k`

Depois você pode personalizar as mensagens:
```yaml
messages:
  cheque-created: "&aVocê criou um cheque de &2%value% coins&a!"
```

**Resultado final:**
- `1000` → "Você criou um cheque de **1k coins**!"

---

## 🎨 Comparação Visual

### Formato Compacto vs Tradicional

```
┌──────────────────────────────────────────────────────────┐
│                    CHEQUE COMPACTO                       │
├──────────────────────────────────────────────────────────┤
│  📄 Cheque de $50k                                       │
│  ━━━━━━━━━━━━━━━━━━                                      │
│  Valor: $50k                                             │
│  Emitido por: JogadorX                                   │
│  Data: 26/11/2025 15:30                                  │
│  ━━━━━━━━━━━━━━━━━━                                      │
└──────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────────┐
│                  CHEQUE TRADICIONAL                      │
├──────────────────────────────────────────────────────────┤
│  📄 Cheque de $50,000.00                                 │
│  ━━━━━━━━━━━━━━━━━━                                      │
│  Valor: $50,000.00                                       │
│  Emitido por: JogadorX                                   │
│  Data: 26/11/2025 15:30                                  │
│  ━━━━━━━━━━━━━━━━━━                                      │
└──────────────────────────────────────────────────────────┘
```

**Qual é melhor?**
- ✅ **Compacto**: Mais limpo, fácil de ler, moderno
- ✅ **Tradicional**: Preciso, formal, detalhado

---

## 📱 Precisão da Formatação Compacta

### Regras de Arredondamento

**Milhares (k):**
- `1.0k` a `9.99k` → 2 casas decimais (`1.50k`, `5.75k`)
- `10.0k` a `99.9k` → 1 casa decimal (`15.5k`, `50.0k`)
- `100k` a `999k` → Sem decimais (`100k`, `500k`)

**Milhões (M):**
- `1.00M` a `9.99M` → 2 casas decimais (`1.50M`, `5.75M`)
- `10.0M` a `99.9M` → 1 casa decimal (`15.5M`, `50.0M`)
- `100M+` → Sem decimais (`100M`, `500M`)

### Exemplos Detalhados

```
$1,250     → $1.25k  (2 decimais)
$5,750     → $5.75k  (2 decimais)
$10,500    → $10.5k  (1 decimal)
$50,000    → $50k    (sem decimais)
$100,000   → $100k   (sem decimais)
$1,250,000 → $1.25M  (2 decimais)
$5,750,000 → $5.75M  (2 decimais)
$10,500,000→ $10.5M  (1 decimal)
$50,000,000→ $50M    (sem decimais)
```

---

## 🧪 Testando Formatações

### Comandos de Teste - Formato Tradicional

```bash
# Testar valores pequenos
/cheque criar 1
/cheque criar 50
/cheque criar 500

# Testar milhares
/cheque criar 1000
/cheque criar 1500
/cheque criar 50000
/cheque criar 200000

# Testar milhões
/cheque criar 1000000
/cheque criar 1500000
/cheque criar 5500000
/cheque criar 50000000
```

### Comandos de Teste - Formato Compacto ✨

```bash
# Testar milhares com 'k'
/cheque criar 1k        # = 1000
/cheque criar 1.5k      # = 1500
/cheque criar 5k        # = 5000
/cheque criar 50k       # = 50000
/cheque criar 200k      # = 200000
/cheque criar 999k      # = 999000

# Testar milhões com 'M'
/cheque criar 1M        # = 1000000
/cheque criar 1.5M      # = 1500000
/cheque criar 5.5M      # = 5500000
/cheque criar 50M       # = 50000000

# Com mensagens
/cheque criar 10k Prêmio do evento
/cheque criar 100k Parabéns!
/cheque criar 1M Grande conquista!
```

### Verificar Economia

```bash
/cheque info

# Saída esperada (formato compacto):
========== Economia do Servidor ==========
Média de saldo: $125k
Total circulante: $2.5M
Jogadores analisados: 20
========================================
```

---

## ⚙️ Mudando entre Formatos

### Ativar Formato Compacto

1. Edite `plugins/ServerChecks/config.yml`
2. Configure:
   ```yaml
   cheque:
     compact-format: true
     currency-symbol: "$"
   ```
3. Recarregue: `/reload` ou reinicie o servidor

### Ativar Formato Tradicional

1. Edite `plugins/ServerChecks/config.yml`
2. Configure:
   ```yaml
   cheque:
     compact-format: false
     value-format: "$%,.2f"
   ```
3. Recarregue: `/reload` ou reinicie o servidor

---

## 💡 Recomendações

### Para Servidores de Sobrevivência
```yaml
cheque:
  compact-format: true
  currency-symbol: "$"
```
✅ Valores grandes são comuns, formato compacto facilita leitura

### Para Servidores Roleplay
```yaml
cheque:
  compact-format: false
  value-format: "R$ %,.2f"
```
✅ Formato formal e detalhado para imersão

### Para Servidores Skyblock
```yaml
cheque:
  compact-format: true
  currency-symbol: ""
```
Mensagens:
```yaml
messages:
  cheque-created: "&aVocê criou um cheque de &2%value% coins&a!"
```
✅ Economias com valores enormes, "1M coins" é mais claro

---

## 📊 Tabela de Referência Rápida

| Configuração | 1000 | 50000 | 1000000 | Uso |
|--------------|------|-------|---------|-----|
| Compacto + $ | $1k | $50k | $1M | Padrão |
| Compacto + R$ | R$1k | R$50k | R$1M | Brasil |
| Compacto + € | €1k | €50k | €1M | Europa |
| Compacto sem símbolo | 1k | 50k | 1M | Coins |
| Tradicional | $1,000.00 | $50,000.00 | $1,000,000.00 | Formal |

---

## ✅ Conclusão

O formato compacto torna os valores muito mais legíveis e modernos, especialmente para economias com valores altos. É a opção recomendada para a maioria dos servidores!

**Exemplos reais de uso:**
- Evento com prêmio de $500k fica muito mais claro que $500,000.00
- Economia média de $75k é instantaneamente compreensível
- Cheques de $1.5M são compactos e elegantes

🎮 **Experimente e escolha o formato que melhor se adapta ao seu servidor!**
