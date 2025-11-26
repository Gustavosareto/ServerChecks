# Changelog - ServerChecks

Todas as mudanças notáveis neste projeto serão documentadas neste arquivo.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/1.0.0/),
e este projeto adere ao [Semantic Versioning](https://semver.org/).

## [Unreleased]

### Planejado
- Sistema de histórico de transações
- Comando /cheque enviar <jogador> <valor> (transferência direta)
- Integração com PlaceholderAPI
- Suporte a MySQL para armazenamento de histórico
- Sistema de cheques nominais (somente destinatário pode resgatar)
- Comando /cheque cancelar (estornar cheque não resgatado)
- Sistema de limites por jogador (máximo X cheques por dia)
- GUI para gerenciamento de cheques

---

## [1.0.0] - 2025-11-26

### Lançamento Inicial

#### ✨ Adicionado

**Formatação de Valores Melhorada**
- Sistema de formatação compacta (1k, 200k, 1M, etc)
- **Entrada de valores compactos nos comandos** (digite `1k` ao invés de `1000`)
- Suporte a `k` (milhares) e `M` (milhões) na entrada
- Aceita vírgula ou ponto como separador decimal
- Case-insensitive (1k, 1K, 1m, 1M todos funcionam)
- Formatação inteligente com precisão variável
- Suporte a símbolos de moeda personalizados
- Opção de alternar entre formato compacto e tradicional
- Arquivo FORMAT_EXAMPLES.md com exemplos detalhados
- Regras de arredondamento automático baseadas no valor

**Sistema de Cheques**
- Criação de cheques com comando `/cheque criar <valor> [mensagem]`
- Resgate de cheques por clique direito
- Suporte a mensagens personalizadas nos cheques
- Design customizável via config.yml (nome, lore, cores)
- Formatação de valores monetários configurável (compacto ou tradicional)

**Segurança e Anti-Falsificação**
- Assinatura interna invisível em cada cheque
- Validação rigorosa de autenticidade
- Proteção contra cheques duplicados ou editados manualmente
- Encoding seguro de valores na lore

**Sistema de Economia Dinâmica**
- Cálculo automático de média de saldo dos jogadores
- Monitoramento de total circulante no servidor
- Análise assíncrona (não causa lag)
- Intervalo de atualização configurável
- Suporte a jogadores online e offline
- Avisos automáticos para valores muito altos
- Sistema de confirmação para cheques acima da média

**Comandos**
- `/cheque criar <valor> [mensagem]` - Cria um novo cheque
- `/cheque info` - Exibe informações da economia do servidor
- Aliases: `/check`, `/cheques`

**Permissões**
- `serverchecks.use` - Usar comandos básicos
- `serverchecks.criar` - Criar cheques
- `serverchecks.resgatar` - Resgatar cheques
- `serverchecks.info` - Ver informações econômicas
- `serverchecks.admin` - Acesso administrativo completo

**Integração**
- Vault API para compatibilidade universal com economias
- Suporte a EssentialsX, NextEconomy, CMI, e outros via Vault
- Sistema de hooks automático

**Configuração**
- Arquivo config.yml completo e comentado
- Mensagens totalmente customizáveis
- Suporte a códigos de cor (&)
- Valores mínimo e máximo configuráveis
- Taxa de criação opcional
- Múltiplas opções de análise econômica

**Validações**
- Verificação de saldo antes de criar cheque
- Validação de valores (mínimo, máximo, numéricos)
- Verificação de inventário cheio
- Sistema de permissões completo
- Proteção contra valores inválidos (NaN, Infinity)

**Performance**
- Análise econômica assíncrona (não trava servidor)
- Otimizado para servidores grandes
- Opção de desabilitar análise offline
- Cache de dados econômicos

**Documentação**
- README.md completo
- TESTING.md com guia de testes
- DEPLOY.md com instruções de instalação
- config-example.yml com exemplos
- Scripts de build (Windows e Linux)

#### 🔧 Técnico

**Arquitetura**
- Java 8 compatível
- Maven como build tool
- Estrutura modular e organizada
- Seguindo boas práticas de desenvolvimento Bukkit/Spigot

**Dependências**
- Spigot API 1.8.8-R0.1-SNAPSHOT
- Vault API 1.7

**Estrutura de Classes**
```
com.serverchecks
├── ServerChecks.java (Main)
├── commands/
│   └── ChequeCommand.java
├── managers/
│   └── ChequeManager.java
├── economy/
│   └── EconomyAnalyzer.java
└── listeners/
    └── ChequeInteractListener.java
```

---

## Tipos de Mudanças

- `Adicionado` - Para novas funcionalidades
- `Modificado` - Para mudanças em funcionalidades existentes
- `Descontinuado` - Para funcionalidades que serão removidas
- `Removido` - Para funcionalidades removidas
- `Corrigido` - Para correção de bugs
- `Segurança` - Para correções de vulnerabilidades

---

## Versionamento

O projeto segue [Semantic Versioning](https://semver.org/):

- **MAJOR** (X.0.0): Mudanças incompatíveis na API
- **MINOR** (0.X.0): Novas funcionalidades retrocompatíveis
- **PATCH** (0.0.X): Correções de bugs retrocompatíveis

Exemplo:
- `1.0.0` - Lançamento inicial
- `1.1.0` - Adição de nova funcionalidade (ex: GUI)
- `1.1.1` - Correção de bug
- `2.0.0` - Mudança que quebra compatibilidade

---

## Como Contribuir

Para sugerir mudanças ou reportar bugs:

1. Abra uma issue descrevendo a mudança/bug
2. Aguarde discussão e aprovação
3. Faça um fork do projeto
4. Crie uma branch: `git checkout -b feature/nova-funcionalidade`
5. Commit suas mudanças: `git commit -m 'Adiciona nova funcionalidade'`
6. Push para a branch: `git push origin feature/nova-funcionalidade`
7. Abra um Pull Request

---

## Notas de Versões Futuras

### Possíveis Melhorias v1.1.0
- [ ] Sistema de logs de transações
- [ ] Comando de transferência direta
- [ ] Integração PlaceholderAPI
- [ ] Cheques nominais
- [ ] GUI de gerenciamento

### Possíveis Melhorias v1.2.0
- [ ] Suporte a MySQL
- [ ] Sistema de limites por jogador
- [ ] Estatísticas avançadas
- [ ] API pública para desenvolvedores

### Possíveis Melhorias v2.0.0
- [ ] Suporte a versões modernas (1.20+)
- [ ] Sistema de assinaturas digitais
- [ ] Multi-currency support
- [ ] Web dashboard

---

**Legenda de Status**
- ✅ Implementado
- 🚧 Em desenvolvimento
- 📋 Planejado
- ❌ Cancelado
- 🔄 Revisão necessária
