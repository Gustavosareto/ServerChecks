# 🎯 ServerChecks - Visão Geral do Projeto

```
┌─────────────────────────────────────────────────────────────────────────┐
│                                                                         │
│                        📦 SERVERCHECKS v1.0.0                          │
│                                                                         │
│            Sistema de Cheques com Análise Econômica                    │
│                   para Minecraft 1.8.8 (Spigot/Paper)                  │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘
```

## 🎮 O que é?

Plugin completo de **cheques bancários** para Minecraft que permite:
- 💵 Criar cheques físicos com valores monetários
- 🔐 Sistema anti-falsificação avançado
- 📊 Análise automática da economia do servidor
- ⚡ Performance otimizada (async)
- 🎨 Totalmente customizável

---

## ⭐ Destaques

### ✨ Funcionalidades Principais

```
🎫 CHEQUES
├─ Criar cheques com /cheque criar <valor> [mensagem]
├─ Resgatar clicando com botão direito
├─ Mensagens personalizadas
└─ Design customizável (nome, lore, cores)

🔒 SEGURANÇA
├─ Assinatura invisível única
├─ Valor codificado na lore
├─ Impossível falsificar manualmente
└─ Validação em 3 camadas

📈 ECONOMIA
├─ Análise automática de saldo médio
├─ Cálculo de total circulante
├─ Avisos para valores suspeitos
└─ Atualização assíncrona (sem lag)

🔧 INTEGRAÇÃO
├─ Vault API (universal)
├─ EssentialsX Economy
├─ NextEconomy
└─ Qualquer economia via Vault
```

---

## 📊 Estatísticas Técnicas

```
╔════════════════════════════════════════╗
║  INFORMAÇÕES TÉCNICAS                  ║
╠════════════════════════════════════════╣
║  Linguagem     │ Java 8                ║
║  Build Tool    │ Maven 3.6+            ║
║  API           │ Spigot 1.8.8          ║
║  Dependências  │ Vault                 ║
║  Classes Java  │ 5 arquivos            ║
║  Linhas        │ ~1000+ LOC            ║
║  Documentação  │ 7 arquivos MD         ║
║  Tamanho JAR   │ ~50KB (estimado)      ║
╚════════════════════════════════════════╝
```

---

## 🏗️ Arquitetura

```
┌─────────────────────────────────────────────────────────┐
│                    SERVERCHECKS                          │
├─────────────────────────────────────────────────────────┤
│                                                          │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐ │
│  │   Commands   │  │   Managers   │  │   Economy    │ │
│  │              │  │              │  │              │ │
│  │ ChequeCmd    │  │ ChequeManager│  │ EconomyAnalyz│ │
│  │              │  │              │  │              │ │
│  │ /cheque criar│  │ • create()   │  │ • scan()     │ │
│  │ /cheque info │  │ • validate() │  │ • getAvg()   │ │
│  │              │  │ • redeem()   │  │ • getTotal() │ │
│  └──────────────┘  └──────────────┘  └──────────────┘ │
│                                                          │
│  ┌──────────────────────────────────────────────────┐  │
│  │              Listeners                           │  │
│  │                                                  │  │
│  │  ChequeInteractListener                         │  │
│  │  • PlayerInteractEvent (right-click)           │  │
│  └──────────────────────────────────────────────────┘  │
│                                                          │
├─────────────────────────────────────────────────────────┤
│                      VAULT API                           │
├─────────────────────────────────────────────────────────┤
│              Plugin de Economia (EssentialsX, etc)       │
└─────────────────────────────────────────────────────────┘
```

---

## 🔄 Fluxo de Operação

### 💰 Criar Cheque

```
┌─────────┐      ┌──────────┐      ┌───────────┐      ┌─────────┐
│ Jogador │─────▶│ Comando  │─────▶│  Manager  │─────▶│  Vault  │
└─────────┘      └──────────┘      └───────────┘      └─────────┘
     │                │                   │                  │
     │  /cheque criar │                   │                  │
     │  10000         │                   │                  │
     │                │  Valida valor     │                  │
     │                │  Verifica economia│                  │
     │                │                   │  Has balance?    │
     │                │                   │◀─────────────────┤
     │                │                   │  YES             │
     │                │                   │                  │
     │                │                   │  Withdraw $10k   │
     │                │                   │─────────────────▶│
     │                │                   │                  │
     │                │  Cria ItemStack   │                  │
     │                │  com assinatura   │                  │
     │                │                   │                  │
     │◀───────────────────────────────────┤                  │
     │  Cheque criado!                    │                  │
     │                                    │                  │
```

### 🎫 Resgatar Cheque

```
┌─────────┐      ┌──────────┐      ┌───────────┐      ┌─────────┐
│ Jogador │─────▶│ Listener │─────▶│  Manager  │─────▶│  Vault  │
└─────────┘      └──────────┘      └───────────┘      └─────────┘
     │                │                   │                  │
  Clique            Detecta              Valida           Deposita
  direito           evento            assinatura         $10.000
     │                │                   │                  │
     │  Right-click   │                   │                  │
     │  com cheque    │                   │                  │
     │                │  onInteract()     │                  │
     │                │                   │  isValid()?      │
     │                │                   │  ✓ SIM           │
     │                │                   │                  │
     │                │                   │  getValue()      │
     │                │                   │  = $10.000       │
     │                │                   │                  │
     │                │                   │  Deposit $10k    │
     │                │                   │─────────────────▶│
     │                │                   │                  │
     │◀───────────────────────────────────┤                  │
     │  +$10.000 creditado!               │                  │
     │  Cheque removido                   │                  │
     │                                    │                  │
```

---

## 🎨 Customização Visual

### Exemplo de Cheque Padrão

```
┌─────────────────────────────────────┐
│  📄 Cheque de $10.000,00            │
├─────────────────────────────────────┤
│  ━━━━━━━━━━━━━━━━━━                 │
│  Valor: $10.000,00                  │
│  Emitido por: JogadorX              │
│  Data: 26/11/2025 14:30             │
│  Mensagem: Pagamento do evento      │
│  ━━━━━━━━━━━━━━━━━━                 │
│  Clique com botão direito p/ resg.  │
│                                     │
│  [Assinatura invisível: ✓]          │
│  [Valor codificado: ✓]              │
└─────────────────────────────────────┘
```

### Customizável Via Config

```yaml
cheque-format:
  display-name: "&6&lCheque de %value%"
  lore:
    - "&7━━━━━━━━━━━━━━━━━━"
    - "&eValor: &a%value%"
    - "&eEmitido por: &f%player%"
    # ... personalize tudo!
```

---

## 📦 Arquivos do Projeto

```
📁 EconomiaMedia/
│
├── 📊 CÓDIGO FONTE
│   ├── ServerChecks.java          # Classe principal
│   ├── ChequeCommand.java         # Handler de comandos
│   ├── ChequeManager.java         # Lógica de cheques
│   ├── EconomyAnalyzer.java       # Análise econômica
│   └── ChequeInteractListener.java # Eventos
│
├── 📝 DOCUMENTAÇÃO
│   ├── README.md                  # Docs completa
│   ├── QUICKSTART.md              # Início rápido
│   ├── TESTING.md                 # Guia de testes
│   ├── DEPLOY.md                  # Instalação
│   ├── STRUCTURE.md               # Arquitetura
│   ├── CHANGELOG.md               # Histórico
│   └── OVERVIEW.md                # Este arquivo!
│
├── ⚙️ CONFIGURAÇÃO
│   ├── pom.xml                    # Maven
│   ├── plugin.yml                 # Metadados
│   ├── config.yml                 # Config padrão
│   └── config-example.yml         # Exemplos
│
└── 🚀 SCRIPTS
    ├── build.bat                  # Build Windows
    └── build.sh                   # Build Linux/Mac
```

---

## 🚀 Como Usar

### 1️⃣ Compilar

```bash
# Windows
build.bat

# Linux/Mac
./build.sh
```

### 2️⃣ Instalar

```
1. Copiar ServerChecks-1.0.0.jar → plugins/
2. Ter Vault instalado
3. Ter plugin de economia
4. Reiniciar servidor
```

### 3️⃣ Usar

```
/cheque criar 1000          # Cria cheque
/cheque criar 5000 Presente # Com mensagem
/cheque info                # Ver economia
```

---

## 🎯 Casos de Uso

### 💼 Casos de Uso Reais

```
🏆 EVENTOS E PRÊMIOS
└─ Admin cria cheques de prêmio
   └─ Distribui aos vencedores
      └─ Jogadores resgatam quando quiserem

💰 TRANSFERÊNCIAS SEGURAS
└─ Jogador A cria cheque
   └─ Entrega ao Jogador B (drop, trade, etc)
      └─ Jogador B resgata

🏪 COMÉRCIO ENTRE PLAYERS
└─ Loja de jogadores aceita cheques
   └─ Cliente paga com cheque
      └─ Lojista resgata depois

💳 SALÁRIO AUTOMATIZADO
└─ Sistema de clãs/factions
   └─ Líder distribui salários via cheques
      └─ Membros resgatam

🎁 PRESENTES E DOAÇÕES
└─ VIP doa valor alto
   └─ Cria cheque com mensagem personalizada
      └─ Presenteia novato
```

---

## 🔐 Segurança

### Sistema de 3 Camadas

```
┌─────────────────────────────────────────┐
│  CAMADA 1: Assinatura Invisível         │
│  ────────────────────────────────────── │
│  §0§0§1§2§3§4§5§6§7§8§9CHECK           │
│  • Invisível no jogo                    │
│  • Impossível replicar manualmente      │
└─────────────────────────────────────────┘

┌─────────────────────────────────────────┐
│  CAMADA 2: Valor Codificado             │
│  ────────────────────────────────────── │
│  §0§0VALUE:10000.0                      │
│  • Escondido na lore                    │
│  • Parser interno                       │
└─────────────────────────────────────────┘

┌─────────────────────────────────────────┐
│  CAMADA 3: Validação Rigorosa           │
│  ────────────────────────────────────── │
│  • Verifica lore completa               │
│  • Valida formato                       │
│  • Decode seguro de valor               │
└─────────────────────────────────────────┘

✅ RESULTADO: Cheques 100% seguros!
```

---

## 📈 Performance

### Otimizações Implementadas

```
⚡ ANÁLISE ASSÍNCRONA
├─ Economia escaneada em background
├─ Não trava o servidor (async)
└─ Intervalo configurável

💾 CACHE INTELIGENTE
├─ Dados econômicos em memória
├─ Atualização periódica
└─ Acesso rápido

🎯 VALIDAÇÕES EFICIENTES
├─ Verificação rápida de assinatura
├─ Early-return em validações
└─ Sem queries desnecessárias

🔧 CONFIGURAÇÃO FLEXÍVEL
├─ Desabilitar jogadores offline
├─ Ajustar intervalo de scan
└─ Otimizar para seu servidor
```

---

## 🌟 Diferenciais

### Por que usar ServerChecks?

```
✅ COMPLETO
   └─ Tudo que você precisa em um só plugin

✅ SEGURO
   └─ Anti-falsificação de 3 camadas

✅ INTELIGENTE
   └─ Análise econômica automática

✅ PERFORMÁTICO
   └─ Otimizado, sem lag

✅ CUSTOMIZÁVEL
   └─ Configure tudo via YAML

✅ COMPATÍVEL
   └─ Funciona com qualquer economia (Vault)

✅ DOCUMENTADO
   └─ 7 arquivos de documentação

✅ PRONTO PARA USO
   └─ Compilar e usar em minutos
```

---

## 🎓 Aprenda Mais

```
📖 DOCUMENTAÇÃO COMPLETA
   └─ README.md

⚡ COMEÇAR RÁPIDO
   └─ QUICKSTART.md

🧪 TESTAR TUDO
   └─ TESTING.md

🚀 INSTALAR E DEPLOYAR
   └─ DEPLOY.md

🏗️ ENTENDER ARQUITETURA
   └─ STRUCTURE.md

📝 HISTÓRICO DE VERSÕES
   └─ CHANGELOG.md
```

---

## 💡 Contribuir

```
1. Fork o projeto
2. Crie uma branch (feature/nova-funcionalidade)
3. Commit suas mudanças
4. Push para a branch
5. Abra um Pull Request
```

---

## 📞 Informações

```
╔════════════════════════════════════════╗
║  Nome      │ ServerChecks              ║
║  Versão    │ 1.0.0                     ║
║  Autor     │ ServerChecks Team         ║
║  Licença   │ Livre                     ║
║  Minecraft │ 1.8.8 (Spigot/Paper)      ║
║  Java      │ 8+                        ║
║  Vault     │ 1.7                       ║
╚════════════════════════════════════════╝
```

---

## 🎉 Conclusão

**ServerChecks** é um plugin profissional, completo e pronto para uso que adiciona um sistema de cheques bancários moderno ao seu servidor Minecraft 1.8.8.

Com segurança anti-falsificação, análise econômica inteligente e total customização, é a solução perfeita para servidores que querem um sistema de transferências físicas de dinheiro!

---

```
┌─────────────────────────────────────────────────────────────────────────┐
│                                                                         │
│                   🎮 Pronto para transformar                            │
│                   a economia do seu servidor?                           │
│                                                                         │
│                        COMPILE • INSTALE • USE                          │
│                                                                         │
│                          🚀 ServerChecks v1.0.0                         │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘
```

**Desenvolvido com ❤️ para a comunidade Minecraft**
