# 📁 Estrutura do Projeto ServerChecks

```
EconomiaMedia/
│
├── 📄 pom.xml                          # Configuração Maven do projeto
├── 📄 README.md                        # Documentação principal
├── 📄 CHANGELOG.md                     # Histórico de versões
├── 📄 TESTING.md                       # Guia completo de testes
├── 📄 DEPLOY.md                        # Guia de compilação e deploy
├── 📄 config-example.yml               # Exemplo de configuração comentado
├── 📄 .gitignore                       # Arquivos ignorados pelo Git
├── 📄 build.bat                        # Script de build (Windows)
├── 📄 build.sh                         # Script de build (Linux/Mac)
│
├── 📂 src/
│   └── 📂 main/
│       ├── 📂 java/
│       │   └── 📂 com/
│       │       └── 📂 serverchecks/
│       │           │
│       │           ├── 📄 ServerChecks.java              # ⚙️ Classe principal do plugin
│       │           │   ├── Inicialização do plugin
│       │           │   ├── Integração com Vault
│       │           │   ├── Registro de comandos e listeners
│       │           │   ├── Gerenciamento de configuração
│       │           │   └── Utilitários de formatação
│       │           │
│       │           ├── 📂 commands/
│       │           │   └── 📄 ChequeCommand.java         # 💬 Handler de comandos
│       │           │       ├── /cheque criar <valor> [msg]
│       │           │       ├── /cheque info
│       │           │       ├── Sistema de confirmação
│       │           │       └── Validações de entrada
│       │           │
│       │           ├── 📂 managers/
│       │           │   └── 📄 ChequeManager.java         # 🎫 Gerenciador de cheques
│       │           │       ├── Criação de cheques
│       │           │       ├── Validação de autenticidade
│       │           │       ├── Resgate de cheques
│       │           │       ├── Sistema anti-falsificação
│       │           │       ├── Encoding/Decoding de valores
│       │           │       └── Formatação de ItemStack
│       │           │
│       │           ├── 📂 economy/
│       │           │   └── 📄 EconomyAnalyzer.java       # 📊 Analisador de economia
│       │           │       ├── Cálculo de média de saldo
│       │           │       ├── Total de dinheiro circulante
│       │           │       ├── Análise assíncrona
│       │           │       ├── Sistema de avisos
│       │           │       └── Cache de dados econômicos
│       │           │
│       │           └── 📂 listeners/
│       │               └── 📄 ChequeInteractListener.java # 👆 Listener de interações
│       │                   ├── Detecta clique direito
│       │                   ├── Valida cheques
│       │                   └── Processa resgates
│       │
│       └── 📂 resources/
│           ├── 📄 plugin.yml                # 🔧 Metadados do plugin
│           │   ├── Nome, versão, autor
│           │   ├── Comandos e aliases
│           │   ├── Permissões
│           │   └── Dependências (Vault)
│           │
│           └── 📄 config.yml                # ⚙️ Configuração padrão
│               ├── Configurações de economia
│               ├── Configurações de cheques
│               ├── Mensagens customizáveis
│               └── Formato visual dos cheques
│
└── 📂 target/                              # 🎯 Arquivos compilados (gerado pelo Maven)
    ├── 📄 ServerChecks-1.0.0.jar           # ✅ Plugin final (USE ESTE!)
    ├── 📄 original-ServerChecks-1.0.0.jar  # Versão sem shade
    └── 📂 classes/                          # Classes compiladas

```

---

## 📊 Estatísticas do Projeto

### Arquivos Java
- **Total**: 5 classes Java
- **Linhas de código**: ~1000+ linhas (estimado)
- **Pacotes**: 4 (main, commands, managers, economy, listeners)

### Documentação
- **README.md**: Documentação completa do plugin
- **TESTING.md**: 17+ casos de teste detalhados
- **DEPLOY.md**: Guia completo de instalação
- **CHANGELOG.md**: Histórico de versões
- **config-example.yml**: Configuração comentada

### Scripts
- **build.bat**: Compilação automatizada (Windows)
- **build.sh**: Compilação automatizada (Linux/Mac)

---

## 🔧 Componentes Principais

### 1. ServerChecks.java (Main Class)
**Responsabilidades:**
- Inicialização e shutdown do plugin
- Integração com Vault Economy
- Registro de comandos e eventos
- Gerenciamento de configuração
- Utilitários de formatação

**Métodos principais:**
- `onEnable()` - Carrega plugin
- `onDisable()` - Descarrega plugin
- `setupEconomy()` - Configura Vault
- `getMessage()` - Obtém mensagens
- `formatMoney()` - Formata valores

---

### 2. ChequeCommand.java (Command Handler)
**Responsabilidades:**
- Processar comando `/cheque`
- Validar argumentos e permissões
- Sistema de confirmação para valores altos
- Exibir informações econômicas

**Sub-comandos:**
- `criar <valor> [msg]` - Cria cheque
- `info` - Mostra economia

**Features:**
- Confirmação temporária (10s)
- Validação de valores
- Avisos personalizados

---

### 3. ChequeManager.java (Core Logic)
**Responsabilidades:**
- Criar cheques (ItemStack)
- Validar autenticidade
- Resgatar cheques
- Anti-falsificação

**Segurança:**
- Assinatura invisível: `§0§0§1§2§3§4§5§6§7§8§9CHECK`
- Valor codificado: `§0§0VALUE:<valor>`
- Validação de lore

**Métodos principais:**
- `createCheque()` - Cria item
- `isValidCheque()` - Valida
- `getChequeValue()` - Extrai valor
- `redeemCheque()` - Resgata

---

### 4. EconomyAnalyzer.java (Economy Scanner)
**Responsabilidades:**
- Analisar economia do servidor
- Calcular médias e totais
- Executar análises assíncronas
- Fornecer avisos inteligentes

**Dados calculados:**
- `averageMoney` - Média de saldo
- `totalMoney` - Total circulante
- `playersAnalyzed` - Jogadores contados

**Métodos principais:**
- `startScan()` - Inicia análise periódica
- `performScan()` - Executa análise
- `getAverage()` - Retorna média
- `shouldWarn()` - Verifica se deve avisar

---

### 5. ChequeInteractListener.java (Event Handler)
**Responsabilidades:**
- Detectar cliques com cheque
- Validar permissões
- Chamar resgate

**Eventos:**
- `PlayerInteractEvent` - Clique direito

---

## 🎯 Fluxo de Dados

### Criação de Cheque
```
Jogador executa comando
    ↓
ChequeCommand valida argumentos
    ↓
Verifica saldo (Vault)
    ↓
EconomyAnalyzer verifica se deve avisar
    ↓ (se OK)
ChequeManager.createCheque()
    ↓
Debita valor (Vault)
    ↓
Cria ItemStack com assinatura
    ↓
Retorna cheque ao jogador
```

### Resgate de Cheque
```
Jogador clica com cheque
    ↓
ChequeInteractListener detecta
    ↓
Valida permissão
    ↓
ChequeManager.isValidCheque()
    ↓ (se válido)
ChequeManager.redeemCheque()
    ↓
Extrai valor codificado
    ↓
Deposita no saldo (Vault)
    ↓
Remove cheque do inventário
    ↓
Mensagem de sucesso
```

### Análise Econômica
```
Plugin inicializa
    ↓
EconomyAnalyzer.startScan()
    ↓
Task assíncrona repetida
    ↓
performScan() a cada X minutos
    ↓
Itera sobre jogadores
    ↓
Calcula média e total (Vault)
    ↓
Atualiza cache
    ↓
Log de conclusão
```

---

## 🔐 Sistema de Segurança

### Anti-Falsificação (3 Camadas)

**Camada 1: Assinatura Invisível**
```java
String SIGNATURE_KEY = "§0§0§1§2§3§4§5§6§7§8§9CHECK";
// Invisível no jogo, impossível de replicar manualmente
```

**Camada 2: Valor Codificado**
```java
String encodedValue = "§0§0VALUE:" + valor;
// Armazenado na lore de forma invisível
```

**Camada 3: Validação Rigorosa**
```java
boolean isValid = item.hasLore() && 
                  lore.contains(SIGNATURE_KEY) &&
                  canDecodeValue(lore);
```

---

## 📝 Configuração

### config.yml Padrão
- ✅ Análise econômica ativada
- ✅ Intervalo de 5 minutos
- ✅ Aviso para 100x a média
- ✅ Inclui jogadores offline
- ✅ Material: PAPER
- ✅ Sem taxa de criação
- ✅ Sem limites de valor

### Personalização
Tudo é customizável:
- Mensagens e cores
- Limites de valores
- Taxas de criação
- Formato visual
- Intervalo de análise

---

## 🚀 Como Compilar

### Windows
```batch
build.bat
```

### Linux/Mac
```bash
chmod +x build.sh
./build.sh
```

### Manual
```bash
mvn clean package
```

**Output:** `target/ServerChecks-1.0.0.jar`

---

## 📦 Dependências

### Runtime (Servidor)
- ✅ Spigot/Paper 1.8.8
- ✅ Vault
- ✅ Plugin de Economia (via Vault)

### Build (Desenvolvimento)
- ✅ Java 8 JDK
- ✅ Maven 3.6+

---

## 🎓 Arquitetura

**Padrão:** MVC adaptado para Bukkit
- **Model**: ChequeManager, EconomyAnalyzer
- **View**: Mensagens, ItemStack visual
- **Controller**: ChequeCommand, Listeners

**Princípios:**
- ✅ Separação de responsabilidades
- ✅ Código limpo e documentado
- ✅ Modularidade
- ✅ Performance otimizada
- ✅ Segurança por design

---

**Total de arquivos:** 15+
**Linguagens:** Java, YAML, Markdown, Batch, Shell
**Compatibilidade:** Minecraft 1.8.8 (Spigot/Paper)
