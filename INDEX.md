# 📚 Índice de Documentação - ServerChecks

Bem-vindo à documentação completa do **ServerChecks v1.0.0**!

Este arquivo serve como índice central para navegar por toda a documentação do projeto.

---

## 🎯 Por Onde Começar?

### 👤 Sou um Usuário Final
```
1. Leia → QUICKSTART.md (5 minutos)
2. Instale → DEPLOY.md (seção de instalação)
3. Use → README.md (comandos básicos)
```

### 🔧 Sou um Administrador de Servidor
```
1. Leia → README.md (visão geral)
2. Compile → DEPLOY.md (guia completo)
3. Configure → config-example.yml
4. Teste → TESTING.md
5. Monitore → DEPLOY.md (seção de troubleshooting)
```

### 👨‍💻 Sou um Desenvolvedor
```
1. Entenda → STRUCTURE.md (arquitetura)
2. Explore → Código fonte (src/main/java)
3. Compile → pom.xml
4. Contribua → CHANGELOG.md (próximas versões)
```

### 📊 Quero uma Visão Geral
```
Leia → OVERVIEW.md (resumo completo)
```

---

## 📖 Guia de Documentação

### 🚀 Início Rápido

| Arquivo | Descrição | Quando Usar |
|---------|-----------|-------------|
| **[QUICKSTART.md](QUICKSTART.md)** | Guia rápido de 5 minutos | Primeira vez usando o plugin |
| **[README.md](README.md)** | Documentação principal completa | Referência geral |
| **[OVERVIEW.md](OVERVIEW.md)** | Visão geral visual do projeto | Entender o que é o plugin |

---

### 🏗️ Instalação e Deploy

| Arquivo | Descrição | Quando Usar |
|---------|-----------|-------------|
| **[DEPLOY.md](DEPLOY.md)** | Guia completo de compilação e instalação | Compilar e instalar o plugin |
| **[pom.xml](pom.xml)** | Configuração Maven | Build automático |
| **[build.bat](build.bat)** | Script de build Windows | Compilar no Windows |
| **[build.sh](build.sh)** | Script de build Linux/Mac | Compilar no Linux/Mac |

---

### ⚙️ Configuração

| Arquivo | Descrição | Quando Usar |
|---------|-----------|-------------|
| **[config.yml](src/main/resources/config.yml)** | Configuração padrão | Auto-gerado no servidor |
| **[config-example.yml](config-example.yml)** | Exemplos comentados | Personalizar o plugin |
| **[FORMAT_EXAMPLES.md](FORMAT_EXAMPLES.md)** | Exemplos de formatação de valores | Entender formatos (1k, 200k, 1M) |
| **[plugin.yml](src/main/resources/plugin.yml)** | Metadados do plugin | Referência técnica |

---

### 🧪 Testes e Qualidade

| Arquivo | Descrição | Quando Usar |
|---------|-----------|-------------|
| **[TESTING.md](TESTING.md)** | Guia completo de testes (17+ casos) | Validar funcionamento |
| **[TEST_COMMANDS.md](TEST_COMMANDS.md)** | Comandos prontos para testar | Testar rapidamente |

---

### 🏛️ Arquitetura e Código

| Arquivo | Descrição | Quando Usar |
|---------|-----------|-------------|
| **[STRUCTURE.md](STRUCTURE.md)** | Estrutura completa do projeto | Entender arquitetura |
| **[ServerChecks.java](src/main/java/com/serverchecks/ServerChecks.java)** | Classe principal | Entender inicialização |
| **[ChequeCommand.java](src/main/java/com/serverchecks/commands/ChequeCommand.java)** | Handler de comandos | Ver lógica de comandos |
| **[ChequeManager.java](src/main/java/com/serverchecks/managers/ChequeManager.java)** | Gerenciador de cheques | Ver criação/resgate |
| **[EconomyAnalyzer.java](src/main/java/com/serverchecks/economy/EconomyAnalyzer.java)** | Análise econômica | Ver análise assíncrona |
| **[ChequeInteractListener.java](src/main/java/com/serverchecks/listeners/ChequeInteractListener.java)** | Eventos | Ver resgate por clique |

---

### 📝 Histórico e Mudanças

| Arquivo | Descrição | Quando Usar |
|---------|-----------|-------------|
| **[CHANGELOG.md](CHANGELOG.md)** | Histórico de versões | Ver mudanças e planejamento |

---

### 🔧 Outros Arquivos

| Arquivo | Descrição | Quando Usar |
|---------|-----------|-------------|
| **[.gitignore](.gitignore)** | Arquivos ignorados pelo Git | Controle de versão |

---

## 📊 Mapa Mental da Documentação

```
📚 DOCUMENTAÇÃO SERVERCHECKS
│
├── 🎯 INÍCIO
│   ├── QUICKSTART.md ─────────▶ Comece aqui! (5 min)
│   ├── README.md ─────────────▶ Docs completa
│   └── OVERVIEW.md ───────────▶ Visão geral
│
├── 🚀 INSTALAÇÃO
│   ├── DEPLOY.md ─────────────▶ Guia completo
│   ├── pom.xml ───────────────▶ Maven config
│   ├── build.bat ─────────────▶ Build Windows
│   └── build.sh ──────────────▶ Build Linux
│
├── ⚙️ CONFIGURAÇÃO
│   ├── config.yml ────────────▶ Config padrão
│   ├── config-example.yml ────▶ Exemplos
│   └── plugin.yml ────────────▶ Metadados
│
├── 🧪 TESTES
│   ├── TESTING.md ────────────▶ Guia de testes
│   └── TEST_COMMANDS.md ──────▶ Comandos prontos
│
├── 🏗️ CÓDIGO
│   ├── STRUCTURE.md ──────────▶ Arquitetura
│   └── src/main/java/ ────────▶ Código fonte
│       ├── ServerChecks.java
│       ├── commands/
│       ├── managers/
│       ├── economy/
│       └── listeners/
│
└── 📝 HISTÓRICO
    └── CHANGELOG.md ──────────▶ Versões
```

---

## 🎓 Trilhas de Aprendizado

### Trilha 1: Usuário Rápido (10 minutos)
```
1. QUICKSTART.md (5 min)
   └─ Instalação básica
   
2. Testes práticos (5 min)
   └─ /cheque criar 1000
   └─ Resgatar cheque
   └─ /cheque info
```

### Trilha 2: Administrador Completo (30 minutos)
```
1. OVERVIEW.md (5 min)
   └─ Entender o plugin
   
2. DEPLOY.md (10 min)
   └─ Compilar e instalar
   
3. config-example.yml (5 min)
   └─ Personalizar configurações
   
4. TESTING.md (10 min)
   └─ Validar funcionamento
```

### Trilha 3: Desenvolvedor (1-2 horas)
```
1. README.md (10 min)
   └─ Entender funcionalidades
   
2. STRUCTURE.md (15 min)
   └─ Compreender arquitetura
   
3. Código Fonte (1 hora)
   └─ Ler e entender implementação
   
4. CHANGELOG.md (5 min)
   └─ Ver próximas features
   
5. Contribuir
   └─ Fork e PR
```

---

## 🔍 Busca Rápida por Tópico

### Comandos
- **Lista de comandos**: `README.md` → seção "Comandos"
- **Exemplos de uso**: `TEST_COMMANDS.md`
- **Implementação**: `ChequeCommand.java`

### Configuração
- **Opções disponíveis**: `config-example.yml`
- **Mensagens customizáveis**: `config.yml` → seção "messages"
- **Limites de valor**: `config.yml` → seção "cheque"

### Segurança
- **Anti-falsificação**: `README.md` → seção "Segurança"
- **Implementação**: `ChequeManager.java` → método `isValidCheque()`
- **Testes**: `TESTING.md` → Teste 6

### Economia
- **Análise econômica**: `README.md` → seção "Sistema de Economia"
- **Implementação**: `EconomyAnalyzer.java`
- **Configuração**: `config.yml` → seção "economy-scan"

### Performance
- **Otimizações**: `OVERVIEW.md` → seção "Performance"
- **Testes de stress**: `TESTING.md` → Testes 16-17
- **Configuração**: `config.yml` → `interval-minutes`

### Instalação
- **Guia completo**: `DEPLOY.md`
- **Início rápido**: `QUICKSTART.md`
- **Problemas comuns**: `DEPLOY.md` → seção "Troubleshooting"

### API/Código
- **Arquitetura**: `STRUCTURE.md`
- **Classes principais**: `src/main/java/com/serverchecks/`
- **Integração Vault**: `ServerChecks.java` → `setupEconomy()`

---

## 📝 Formatos de Arquivo

### Markdown (.md)
```
✅ README.md
✅ QUICKSTART.md
✅ DEPLOY.md
✅ TESTING.md
✅ STRUCTURE.md
✅ CHANGELOG.md
✅ OVERVIEW.md
✅ FORMAT_EXAMPLES.md
✅ INDEX.md (este arquivo)
```

### YAML (.yml)
```
✅ config.yml
✅ config-example.yml
✅ plugin.yml
```

### Java (.java)
```
✅ ServerChecks.java
✅ ChequeCommand.java
✅ ChequeManager.java
✅ EconomyAnalyzer.java
✅ ChequeInteractListener.java
```

### Scripts
```
✅ build.bat (Windows Batch)
✅ build.sh (Shell Script)
```

### Build
```
✅ pom.xml (Maven)
✅ .gitignore (Git)
```

---

## 🎯 Respostas Rápidas

### "Como instalo?"
→ **[QUICKSTART.md](QUICKSTART.md)** ou **[DEPLOY.md](DEPLOY.md)**

### "Como compilo?"
→ **[DEPLOY.md](DEPLOY.md)** seção "Compilação"

### "Como configuro?"
→ **[config-example.yml](config-example.yml)**

### "Como testo?"
→ **[TESTING.md](TESTING.md)** ou **[TEST_COMMANDS.md](TEST_COMMANDS.md)**

### "Como funciona?"
→ **[OVERVIEW.md](OVERVIEW.md)** ou **[STRUCTURE.md](STRUCTURE.md)**

### "Onde vejo comandos?"
→ **[README.md](README.md)** seção "Comandos"

### "Como contribuo?"
→ **[CHANGELOG.md](CHANGELOG.md)** seção "Como Contribuir"

### "Quais são as permissões?"
→ **[README.md](README.md)** seção "Permissões"

### "Tem bugs conhecidos?"
→ **[CHANGELOG.md](CHANGELOG.md)** seção "Problemas Conhecidos"

### "É seguro?"
→ **[README.md](README.md)** seção "Segurança"

---

## 📊 Estatísticas da Documentação

```
╔════════════════════════════════════════╗
║  DOCUMENTAÇÃO                          ║
╠════════════════════════════════════════╣
║  Arquivos Markdown    │ 9              ║
║  Arquivos YAML        │ 3              ║
║  Arquivos Java        │ 5              ║
║  Scripts              │ 2              ║
║  Arquivos Build       │ 2              ║
║  ────────────────────────────────────  ║
║  Total                │ 21+ arquivos   ║
║  ────────────────────────────────────  ║
║  Linhas de Código     │ ~1000+         ║
║  Linhas de Docs       │ ~3000+         ║
║  Páginas Estimadas    │ ~50+           ║
╚════════════════════════════════════════╝
```

---

## 🏆 Completude da Documentação

```
✅ Instalação         100% coberta
✅ Configuração       100% coberta
✅ Uso                100% coberto
✅ Testes             100% cobertos
✅ API/Código         100% coberto
✅ Troubleshooting    100% coberto
✅ Exemplos           100% cobertos
✅ Arquitetura        100% coberta
```

---

## 🎓 Níveis de Conhecimento

### Iniciante 🌱
```
Leia: QUICKSTART.md, README.md (básico)
Tempo: 15-30 minutos
Resultado: Usar o plugin com sucesso
```

### Intermediário 🌿
```
Leia: Tudo acima + DEPLOY.md + config-example.yml
Tempo: 1-2 horas
Resultado: Instalar, configurar e personalizar
```

### Avançado 🌳
```
Leia: Tudo acima + STRUCTURE.md + código fonte
Tempo: 3-4 horas
Resultado: Entender completamente e modificar
```

### Expert 🎯
```
Leia: Toda documentação + código completo
Tempo: 1 dia
Resultado: Contribuir com novas features
```

---

## 🔗 Links Úteis

### Documentação
- [README Principal](README.md)
- [Guia Rápido](QUICKSTART.md)
- [Instalação](DEPLOY.md)
- [Testes](TESTING.md)

### Configuração
- [Config Exemplo](config-example.yml)
- [Config Padrão](src/main/resources/config.yml)

### Código
- [Estrutura](STRUCTURE.md)
- [Código Fonte](src/main/java/com/serverchecks/)

### Histórico
- [Changelog](CHANGELOG.md)

---

## 📞 Suporte

```
📖 Primeiro: Leia a documentação relevante acima
🔍 Segundo: Veja DEPLOY.md → Troubleshooting
🧪 Terceiro: Execute testes do TESTING.md
💬 Quarto: Abra uma issue (se ainda precisar)
```

---

## ✅ Checklist de Documentação Lida

Use para acompanhar seu progresso:

### Essencial
- [ ] INDEX.md (este arquivo)
- [ ] QUICKSTART.md
- [ ] README.md

### Instalação
- [ ] DEPLOY.md
- [ ] build.bat ou build.sh

### Configuração
- [ ] config-example.yml

### Testes
- [ ] TESTING.md
- [ ] TEST_COMMANDS.md

### Avançado
- [ ] OVERVIEW.md
- [ ] STRUCTURE.md
- [ ] CHANGELOG.md
- [ ] Código fonte completo

---

```
┌─────────────────────────────────────────────────────────────────────────┐
│                                                                         │
│                  📚 Bem-vindo à Documentação Completa                   │
│                         do ServerChecks v1.0.0!                         │
│                                                                         │
│                  Escolha sua trilha e comece a explorar!                │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘
```

**Última Atualização:** 26/11/2025  
**Versão do Plugin:** 1.0.0  
**Compatibilidade:** Minecraft 1.8.8 (Spigot/Paper)

---

**💡 Dica:** Adicione este arquivo aos favoritos para referência rápida!
