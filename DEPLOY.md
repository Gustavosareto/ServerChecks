# 🚀 Guia de Compilação e Deploy - ServerChecks

Este guia mostra como compilar, testar e fazer deploy do plugin ServerChecks.

## 📋 Pré-requisitos

### Software Necessário

1. **Java Development Kit (JDK) 8 ou superior**
   - Download: https://adoptium.net/
   - Verifique: `java -version`

2. **Apache Maven 3.6+**
   - Download: https://maven.apache.org/download.cgi
   - Verifique: `mvn -version`

3. **Git** (opcional, para controle de versão)
   - Download: https://git-scm.com/

### Dependências do Servidor

- **Spigot/Paper 1.8.8**
- **Vault** (plugin)
- **Plugin de Economia** (EssentialsX, NextEconomy, CMI, etc.)

---

## 🔨 Compilação

### Método 1: Compilação Rápida

```bash
# Navegue até o diretório do projeto
cd EconomiaMedia

# Compile com Maven
mvn clean package

# O arquivo JAR estará em:
# target/ServerChecks-1.0.0.jar
```

### Método 2: Compilação com Testes (quando implementados)

```bash
mvn clean install
```

### Método 3: Compilação Sem Testes

```bash
mvn clean package -DskipTests
```

---

## 📦 Estrutura de Saída

Após compilar, você terá:

```
target/
├── ServerChecks-1.0.0.jar          # Plugin final
├── original-ServerChecks-1.0.0.jar # Versão sem shade
├── classes/                         # Classes compiladas
└── maven-archiver/                  # Metadados Maven
```

**Arquivo principal**: `target/ServerChecks-1.0.0.jar`

---

## 🎯 Deploy em Servidor Local

### Passo 1: Preparar Servidor

```bash
# Crie uma pasta para teste
mkdir servidor-teste
cd servidor-teste

# Baixe Spigot 1.8.8 (ou use BuildTools)
# Coloque spigot-1.8.8.jar aqui
```

### Passo 2: Instalar Dependências

```bash
# Crie pasta de plugins
mkdir plugins

# Adicione Vault
# Baixe de: https://www.spigotmc.org/resources/vault.34315/
# Coloque em: plugins/Vault.jar

# Adicione plugin de economia (exemplo: EssentialsX)
# Baixe de: https://essentialsx.net/downloads.html
# Coloque em: plugins/EssentialsX.jar
```

### Passo 3: Adicionar ServerChecks

```bash
# Copie o plugin compilado
cp ../target/ServerChecks-1.0.0.jar plugins/

# Estrutura final:
# servidor-teste/
# ├── spigot-1.8.8.jar
# └── plugins/
#     ├── Vault.jar
#     ├── EssentialsX.jar
#     └── ServerChecks-1.0.0.jar
```

### Passo 4: Configurar Servidor

Crie `eula.txt`:
```
eula=true
```

Crie `server.properties`:
```
online-mode=false
max-players=10
gamemode=creative
```

### Passo 5: Iniciar Servidor

```bash
java -Xms1G -Xmx2G -jar spigot-1.8.8.jar nogui
```

### Passo 6: Verificar Carregamento

No console, procure por:
```
[ServerChecks] Vault detectado! Sistema de economia carregado.
[ServerChecks] ServerChecks v1.0.0 habilitado com sucesso!
```

---

## 🌐 Deploy em Servidor de Produção

### Via FTP/SFTP

```bash
# Usando FileZilla, WinSCP, ou similar:
1. Conecte ao servidor
2. Navegue até /plugins/
3. Faça upload de ServerChecks-1.0.0.jar
4. Reinicie o servidor ou use /reload
```

### Via SSH/SCP

```bash
# Copiar via SCP
scp target/ServerChecks-1.0.0.jar usuario@servidor:/caminho/para/plugins/

# Conectar via SSH
ssh usuario@servidor

# Reiniciar servidor
cd /caminho/para/servidor
screen -r minecraft
stop
# Aguarde parar, então inicie novamente
java -jar spigot.jar nogui
```

### Via Painel de Controle

Se usar **Pterodactyl**, **Multicraft**, etc.:

1. Acesse o painel web
2. Vá em "Files" ou "Arquivos"
3. Navegue até `/plugins/`
4. Faça upload de `ServerChecks-1.0.0.jar`
5. Reinicie o servidor pelo painel

---

## ⚙️ Configuração Pós-Deploy

### 1. Verificar Carregamento

```bash
# No console do servidor
plugins

# Deve aparecer:
# Plugins (X): ... ServerChecks ...
```

### 2. Verificar Vault

```bash
# No jogo
/plugins

# Verifique se Vault está verde
# Verifique se ServerChecks está verde
```

### 3. Configurar Plugin

```bash
# Edite config.yml
nano plugins/ServerChecks/config.yml

# Ou via FTP/Painel
# Ajuste conforme necessário:
# - Intervalo de análise
# - Valores mínimo/máximo
# - Mensagens
# - Taxa de criação
```

### 4. Recarregar Configuração

```bash
# No servidor
reload confirm

# Ou reinicie completamente
stop
# Inicie novamente
```

---

## 🧪 Validação do Deploy

### Checklist de Validação

```bash
# 1. Plugin carregado
/plugins
# ✅ ServerChecks deve estar verde

# 2. Comandos funcionando
/cheque
# ✅ Deve mostrar ajuda

# 3. Economia integrada
/cheque info
# ✅ Deve mostrar dados econômicos

# 4. Criar cheque de teste
/money set <seu_nick> 10000
/cheque criar 100
# ✅ Deve criar cheque

# 5. Resgatar cheque
# Clique direito com o cheque
# ✅ Deve resgatar
```

---

## 🔄 Atualização do Plugin

### Para Atualizar de v1.0.0 para Versão Futura

```bash
# 1. Backup da configuração
cp plugins/ServerChecks/config.yml ~/backup-config.yml

# 2. Remover versão antiga
rm plugins/ServerChecks-1.0.0.jar

# 3. Adicionar nova versão
cp ServerChecks-1.1.0.jar plugins/

# 4. Reiniciar servidor
# A configuração será preservada

# 5. Verificar changelog
# Ajuste config.yml se houver novas opções
```

---

## 🐛 Troubleshooting

### Problema: Plugin não carrega

**Sintoma**: ServerChecks não aparece em `/plugins`

**Soluções**:
```bash
# 1. Verificar logs
cat logs/latest.log | grep ServerChecks

# 2. Verificar Vault
plugins
# Vault deve estar instalado e carregado

# 3. Verificar Java
java -version
# Deve ser Java 8+

# 4. Verificar arquivo JAR
ls -lh plugins/ServerChecks*.jar
# Arquivo deve ter tamanho razoável (não 0 bytes)
```

### Problema: Vault não encontrado

**Sintoma**: `Vault não encontrado! Desabilitando plugin...`

**Soluções**:
```bash
# 1. Instalar Vault
# Baixe de: https://www.spigotmc.org/resources/vault.34315/

# 2. Verificar ordem de carregamento
# Adicione em plugin.yml (já incluído):
# depend: [Vault]

# 3. Reiniciar servidor
stop
# Inicie novamente
```

### Problema: Economia não funciona

**Sintoma**: Erro ao criar cheque ou verificar saldo

**Soluções**:
```bash
# 1. Verificar plugin de economia
plugins
# Deve ter EssentialsX, NextEconomy, ou similar

# 2. Testar economia manualmente
/money
/balance

# 3. Verificar integração Vault
# Vault deve detectar o plugin de economia

# 4. Reinstalar ordem correta:
# 1º Vault
# 2º Plugin de Economia
# 3º ServerChecks
```

---

## 📊 Monitoramento em Produção

### Logs Importantes

```bash
# Verificar erros
grep ERROR logs/latest.log

# Verificar análise econômica
grep "Análise econômica" logs/latest.log

# Verificar uso de cheques
grep "cheque" logs/latest.log
```

### Performance

```bash
# Verificar TPS
/tps

# Se TPS baixo após instalar:
# 1. Desabilite análise offline:
#    include-offline-players: false
# 2. Aumente intervalo:
#    interval-minutes: 10
```

---

## 🔒 Segurança em Produção

### Permissões Recomendadas

```yaml
# permissions.yml ou LuckPerms

# Jogadores normais
default:
  permissions:
    - serverchecks.criar
    - serverchecks.resgatar
    - serverchecks.info

# VIP/Premium (sem limites)
vip:
  permissions:
    - serverchecks.*

# Admin completo
admin:
  permissions:
    - serverchecks.admin
```

### Limites Recomendados

```yaml
# config.yml para produção

cheque:
  minimum-value: 100        # Evitar spam
  maximum-value: 1000000    # Prevenir economia inflada
  creation-fee: 1           # Taxa de 1% para desincentivar abuso

economy-scan:
  interval-minutes: 10      # Reduzir carga
  include-offline-players: false  # Performance
```

---

## 📈 Métricas e Estatísticas

### Dados a Monitorar

1. **Cheques Criados**: Log de /cheque criar
2. **Valores Totais**: Somar valores dos cheques
3. **Economia Média**: Acompanhar /cheque info
4. **Avisos Disparados**: Cheques acima da média

### Exemplo de Script de Análise

```bash
#!/bin/bash
# analyze_checks.sh

# Contar cheques criados hoje
grep "cheque criado" logs/latest.log | wc -l

# Somar valores (requer processamento de logs)
grep "Você criou um cheque" logs/latest.log | \
  grep -oP '\$[\d,]+\.\d+' | \
  tr -d '$,' | \
  awk '{sum+=$1} END {print "Total: $"sum}'
```

---

## ✅ Checklist Final de Deploy

- [ ] Java 8+ instalado
- [ ] Maven instalado (para compilar)
- [ ] Projeto compilado com sucesso
- [ ] Vault instalado no servidor
- [ ] Plugin de economia instalado
- [ ] ServerChecks.jar copiado para /plugins/
- [ ] Servidor reiniciado
- [ ] Plugin aparece verde em /plugins
- [ ] Comando /cheque funciona
- [ ] Economia integrada (/cheque info)
- [ ] Teste de criação de cheque OK
- [ ] Teste de resgate de cheque OK
- [ ] Configuração ajustada (opcional)
- [ ] Permissões configuradas
- [ ] Logs verificados
- [ ] Performance OK (TPS normal)

---

## 🎓 Conclusão

Seguindo este guia, você terá o ServerChecks compilado, testado e funcionando perfeitamente em seu servidor Minecraft 1.8.8!

Para dúvidas ou suporte, consulte:
- README.md (documentação completa)
- TESTING.md (guia de testes)
- Logs do servidor (troubleshooting)

**Bom jogo!** 🎮
