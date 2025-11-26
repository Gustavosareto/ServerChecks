# 🎮 ServerChecks - Sistema de Cheques para Minecraft 1.8.8

![Minecraft](https://img.shields.io/badge/Minecraft-1.8.8-green)
![Java](https://img.shields.io/badge/Java-8-orange)
![Spigot](https://img.shields.io/badge/Spigot-1.8.8-blue)
![Version](https://img.shields.io/badge/version-1.0.0-blue)
![Status](https://img.shields.io/badge/status-stable-brightgreen)

> **Sistema completo de cheques bancários com análise econômica integrada para servidores Minecraft 1.8.8 usando Spigot/Paper.**

---

## 📋 Índice

- [Características](#-características)
- [Instalação Rápida](#-instalação-rápida)
- [Comandos](#-comandos)
- [Permissões](#-permissões)
- [Configuração](#️-configuração)
- [Segurança](#️-segurança)
- [Sistema de Economia](#-sistema-de-economia)
- [Documentação Completa](#-documentação-completa)
- [Compilação](#️-compilação)

---

## ⚡ Instalação Rápida

```bash
# 1. Clone ou baixe o projeto
# 2. Compile
mvn clean package
# ou use: build.bat (Windows) / build.sh (Linux)

# 3. Instale
# Copie target/ServerChecks-1.0.0.jar para plugins/
# Certifique-se que Vault está instalado
# Reinicie o servidor

# 4. Use
/cheque criar 1000
```

📖 **[Guia Completo de Instalação](DEPLOY.md)** | ⚡ **[Início Rápido (5min)](QUICKSTART.md)**

---

Sistema completo de cheques com análise econômica integrada para servidores Minecraft 1.8.8 usando Spigot/Paper.

## 📋 Características

### ✨ Funcionalidades Principais

- **Sistema de Cheques Completo**
  - Criação de cheques com valores personalizados
  - Mensagens opcionais nos cheques
  - Design customizável (nome, lore, formato)
  - Resgate simples (clique direito)

- **Anti-Falsificação**
  - Assinatura interna invisível em cada cheque
  - Validação rigorosa de autenticidade
  - Impossível duplicar ou falsificar cheques

- **Análise Econômica Dinâmica**
  - Cálculo automático da média de saldo dos jogadores
  - Monitoramento do total circulante no servidor
  - Avisos para cheques com valores muito altos
  - Atualização assíncrona configurável

- **Integração com Vault**
  - Compatível com qualquer plugin de economia via Vault
  - NextEconomy, EssentialsX Economy, CMI Economy, etc.

## 🔧 Requisitos

- **Servidor**: Spigot/Paper 1.8.8
- **Java**: 8 ou superior
- **Dependências**: 
  - Vault (obrigatório)
  - Qualquer plugin de economia compatível com Vault

## 📥 Instalação

1. **Baixe o plugin** ou compile com Maven:
   ```bash
   mvn clean package
   ```

2. **Instale as dependências**:
   - Baixe e instale [Vault](https://www.spigotmc.org/resources/vault.34315/)
   - Instale um plugin de economia (EssentialsX, NextEconomy, etc.)

3. **Adicione o JAR**:
   - Coloque `ServerChecks-1.0.0.jar` na pasta `plugins/`

4. **Reinicie o servidor**

5. **Configure** (opcional):
   - Edite `plugins/ServerChecks/config.yml`

## 🎮 Comandos

| Comando | Descrição | Permissão |
|---------|-----------|-----------|
| `/cheque criar <valor> [mensagem]` | Cria um novo cheque | `serverchecks.criar` |
| `/cheque info` | Mostra informações da economia | `serverchecks.info` |

**Aliases**: `/check`, `/cheques`

### Exemplos de Uso

```
/cheque criar 10000
/cheque criar 50000 Presente para você!
/cheque criar 1000000 Parabéns pelo evento
/cheque info
```

**💡 Dica:** Você também pode usar formato compacto nos comandos!
```
/cheque criar 1k           # Mesmo que 1000
/cheque criar 50k          # Mesmo que 50000
/cheque criar 1.5k         # Mesmo que 1500
/cheque criar 200k         # Mesmo que 200000
/cheque criar 1M           # Mesmo que 1000000
/cheque criar 2.5M         # Mesmo que 2500000
```

## 🔐 Permissões

| Permissão | Descrição | Padrão |
|-----------|-----------|--------|
| `serverchecks.use` | Permite usar comandos básicos | `true` |
| `serverchecks.criar` | Permite criar cheques | `true` |
| `serverchecks.resgatar` | Permite resgatar cheques | `true` |
| `serverchecks.info` | Permite ver informações da economia | `true` |
| `serverchecks.admin` | Acesso administrativo completo | `op` |

## ⚙️ Configuração

### config.yml Principal

```yaml
# Análise econômica
economy-scan:
  enabled: true                    # Ativar/desativar scanner
  interval-minutes: 5              # Intervalo de atualização
  warn-threshold-percentage: 10000 # Avisar se cheque > 100x média
  include-offline-players: true    # Incluir jogadores offline

# Configurações dos cheques
cheque:
  material: PAPER                  # Material do item
  compact-format: true             # Formato compacto (1k, 200k, 1M)
  currency-symbol: "$"             # Símbolo da moeda
  value-format: "$%,.2f"           # Formato tradicional (se compact-format = false)
  creation-fee: 0                  # Taxa de criação (%)
  minimum-value: 1.0               # Valor mínimo
  maximum-value: -1                # Valor máximo (-1 = sem limite)
```

### Formatação de Valores

O plugin suporta dois modos de formatação:

**Formato Compacto** (padrão - `compact-format: true`):
- `500` → `$500`
- `1000` → `$1k`
- `1500` → `$1.5k`
- `50000` → `$50k`
- `200000` → `$200k`
- `1000000` → `$1M`
- `5500000` → `$5.5M`

**Formato Tradicional** (`compact-format: false`):
- `1000` → `$1,000.00`
- `200000` → `$200,000.00`

### Personalização Visual

O arquivo `config.yml` permite total personalização:
- Nome do cheque
- Linhas da lore
- Mensagens do sistema
- Cores e formatação

## 🛡️ Segurança

### Sistema Anti-Falsificação

O plugin utiliza múltiplas camadas de segurança:

1. **Assinatura Invisível**: Cada cheque contém uma assinatura única invisível
2. **Validação de NBT**: Verifica autenticidade através de dados internos
3. **Encoding de Valor**: Valor armazenado de forma codificada na lore

**Cheques falsificados não funcionam!** O sistema detecta e bloqueia automaticamente.

## 📊 Sistema de Economia

### Análise Automática

O plugin monitora a economia do servidor:

- **Média de Saldo**: Calcula o saldo médio de todos os jogadores
- **Total Circulante**: Soma de toda a economia do servidor
- **Avisos Inteligentes**: Alerta quando valores são muito altos

### Avisos de Segurança

Quando um jogador tenta criar um cheque muito acima da média:

```
⚠ AVISO: O valor está 150x acima da média da economia ($50.000)!
Deseja mesmo criar este cheque? Clique novamente em 10 segundos.
```

## 🎨 Como Usar

### Criando um Cheque

1. Execute `/cheque criar <valor> [mensagem]`
2. O valor será debitado automaticamente
3. Você receberá um papel (cheque) no inventário
4. Se o valor for muito alto, confirme em 10 segundos

### Resgatando um Cheque

1. Segure o cheque na mão
2. Clique com **botão direito**
3. O valor será creditado automaticamente
4. O cheque será consumido

## 🔄 Integração API

### Para Desenvolvedores

```java
// Obter instância
ServerChecks plugin = ServerChecks.getInstance();

// Criar cheque programaticamente
ItemStack cheque = plugin.getChequeManager()
    .createCheque(player, 10000.0, "Mensagem");

// Validar cheque
boolean isValid = plugin.getChequeManager()
    .isValidCheque(itemStack);

// Obter dados econômicos
double average = plugin.getEconomyAnalyzer().getAverage();
double total = plugin.getEconomyAnalyzer().getTotal();
```

## 🏗️ Estrutura do Projeto

```
ServerChecks/
├── src/main/java/com/serverchecks/
│   ├── ServerChecks.java              # Classe principal
│   ├── commands/
│   │   └── ChequeCommand.java         # Handler de comandos
│   ├── managers/
│   │   └── ChequeManager.java         # Gerenciador de cheques
│   ├── economy/
│   │   └── EconomyAnalyzer.java       # Análise econômica
│   └── listeners/
│       └── ChequeInteractListener.java # Listener de interação
├── src/main/resources/
│   ├── plugin.yml                     # Metadados do plugin
│   └── config.yml                     # Configuração
└── pom.xml                            # Configuração Maven
```

## 🔨 Compilação

### Pré-requisitos

- Java 8 JDK
- Maven 3.6+

### Compilar

```bash
# Clone o repositório (se aplicável)
git clone <url-do-repositorio>
cd ServerChecks

# Compile com Maven
mvn clean package

# O JAR estará em target/ServerChecks-1.0.0.jar
```

## 🐛 Problemas Conhecidos

- Em servidores com muitos jogadores offline, a análise inicial pode demorar
  - **Solução**: Configure `include-offline-players: false` se necessário

## 📝 Changelog

### v1.0.0 (Inicial)
- ✅ Sistema completo de criação de cheques
- ✅ Resgate por clique direito
- ✅ Anti-falsificação com assinatura invisível
- ✅ Análise econômica assíncrona
- ✅ Avisos para valores altos
- ✅ Integração Vault
- ✅ Mensagens e visual totalmente customizáveis

## 📄 Licença

Este projeto está sob licença livre para uso pessoal e comercial.

## 🤝 Suporte

Para dúvidas, sugestões ou reportar bugs:
- Abra uma issue no repositório
- Entre em contato com o desenvolvedor

## 👨‍💻 Desenvolvedor

Desenvolvido com ❤️ para a comunidade Minecraft

---

**Nota**: Este plugin foi desenvolvido para Minecraft 1.8.8 usando Java 8 e segue as melhores práticas de desenvolvimento Spigot/Bukkit.
