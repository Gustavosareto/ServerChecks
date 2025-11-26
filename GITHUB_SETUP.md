# 🚀 Guia de Upload para GitHub - ServerChecks

Este guia mostra como enviar o projeto ServerChecks para o GitHub.

## 📋 Pré-requisitos

- ✅ Git instalado (você já tem!)
- ⚠️ Conta no GitHub (criar em https://github.com se não tiver)

---

## 🔧 Passo 1: Configurar Git (Primeira Vez)

Execute estes comandos no PowerShell (substitua com seus dados):

```powershell
# Configure seu nome
git config --global user.name "Seu Nome"

# Configure seu email (use o mesmo do GitHub)
git config --global user.email "seu-email@exemplo.com"

# Verificar configuração
git config --global --list
```

**Exemplo:**
```powershell
git config --global user.name "Gustavo Silva"
git config --global user.email "gustavo@email.com"
```

---

## 🌐 Passo 2: Criar Repositório no GitHub

### Opção A: Via Interface Web (Recomendado)

1. **Acesse:** https://github.com/new
2. **Nome do repositório:** `ServerChecks` (ou outro nome)
3. **Descrição:** `Sistema de cheques para Minecraft 1.8.8 com análise econômica`
4. **Visibilidade:** 
   - ✅ **Public** (recomendado - qualquer um pode ver)
   - 🔒 **Private** (apenas você)
5. **NÃO marque:**
   - ❌ Add README
   - ❌ Add .gitignore
   - ❌ Add license
   
   (Já temos esses arquivos!)

6. **Clique:** `Create repository`

7. **Copie a URL** que aparece (exemplo):
   ```
   https://github.com/seu-usuario/ServerChecks.git
   ```

---

## 📤 Passo 3: Fazer o Primeiro Commit

No PowerShell, na pasta do projeto:

```powershell
# Verificar status
git status

# Adicionar todos os arquivos (se ainda não fez)
git add .

# Fazer commit
git commit -m "Initial commit: ServerChecks v1.0.0 - Sistema de cheques completo"

# Renomear branch para main (padrão do GitHub)
git branch -M main

# Adicionar repositório remoto (substitua com SUA URL)
git remote add origin https://github.com/SEU-USUARIO/ServerChecks.git

# Fazer push
git push -u origin main
```

### ⚠️ Autenticação

O GitHub pedirá autenticação. Use um **Personal Access Token**:

1. Vá em: https://github.com/settings/tokens
2. Clique: `Generate new token` → `Generate new token (classic)`
3. Nome: `ServerChecks Upload`
4. Expiration: `No expiration` ou escolha um período
5. Marque: ✅ **repo** (todas as opções de repo)
6. Clique: `Generate token`
7. **COPIE O TOKEN** (não vai aparecer novamente!)
8. Quando o Git pedir senha, **cole o token**

**Ou use GitHub CLI** (mais fácil):
```powershell
# Instalar GitHub CLI
winget install GitHub.cli

# Fazer login
gh auth login

# Seguir instruções na tela
```

---

## 🎯 Comandos Completos (Copy/Paste)

**Configure suas credenciais primeiro:**
```powershell
git config --global user.name "SEU NOME"
git config --global user.email "SEU-EMAIL@exemplo.com"
```

**Depois execute:**
```powershell
# Navegar até o projeto (se não estiver lá)
cd "c:\Users\gusta\OneDrive\Documentos\EconomiaMedia"

# Adicionar arquivos
git add .

# Commit
git commit -m "Initial commit: ServerChecks v1.0.0"

# Renomear branch
git branch -M main

# Adicionar remoto (MUDE A URL!)
git remote add origin https://github.com/SEU-USUARIO/ServerChecks.git

# Push
git push -u origin main
```

---

## 🔄 Atualizações Futuras

Depois do primeiro upload, para enviar mudanças:

```powershell
# Ver o que mudou
git status

# Adicionar mudanças
git add .

# Commit com mensagem descritiva
git commit -m "Descrição das mudanças"

# Enviar
git push
```

**Exemplos de mensagens:**
```powershell
git commit -m "Fix: Corrigido bug na validação de cheques"
git commit -m "Feature: Adicionado comando /cheque cancelar"
git commit -m "Docs: Atualizado README com novos exemplos"
git commit -m "Update: Melhorado sistema de formatação"
```

---

## 🎨 Melhorar README no GitHub

Depois do upload, o GitHub exibirá automaticamente o `README.md`.

Para adicionar badges (aqueles ícones bonitinhos):

```markdown
![Minecraft](https://img.shields.io/badge/Minecraft-1.8.8-green)
![Java](https://img.shields.io/badge/Java-8-orange)
![License](https://img.shields.io/badge/license-MIT-blue)
![Version](https://img.shields.io/badge/version-1.0.0-blue)
```

---

## 📁 Estrutura que Será Enviada

```
ServerChecks/
├── .gitignore              ✅ Já configurado
├── README.md               ✅ Documentação principal
├── CHANGELOG.md            ✅ Histórico
├── LICENSE                 ⚠️ Você pode adicionar
├── pom.xml                 ✅ Maven
├── src/                    ✅ Código fonte
├── docs/                   ✅ Documentação
└── ... (todos os arquivos)
```

---

## 📝 Adicionar Licença (Opcional)

Crie um arquivo `LICENSE` com a licença de sua escolha:

**MIT License (Recomendada - Livre):**
```
MIT License

Copyright (c) 2025 Seu Nome

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

Ou escolha em: https://choosealicense.com/

---

## ❓ Problemas Comuns

### Problema: "fatal: unable to auto-detect email address"
**Solução:** Configure email e nome (Passo 1)

### Problema: "Authentication failed"
**Solução:** Use Personal Access Token em vez de senha

### Problema: "remote origin already exists"
**Solução:** 
```powershell
git remote remove origin
git remote add origin https://github.com/SEU-USUARIO/ServerChecks.git
```

### Problema: "src refspec main does not match any"
**Solução:** Você esqueceu de fazer commit primeiro
```powershell
git add .
git commit -m "Initial commit"
```

---

## ✅ Checklist Final

Antes de fazer o push, verifique:

- [ ] Git configurado (user.name e user.email)
- [ ] Repositório criado no GitHub
- [ ] URL do repositório copiada
- [ ] Arquivos adicionados (`git add .`)
- [ ] Commit feito (`git commit`)
- [ ] Remote configurado (`git remote add origin`)
- [ ] Token de acesso pronto (ou GitHub CLI configurado)

---

## 🎉 Depois do Upload

Seu repositório estará em:
```
https://github.com/SEU-USUARIO/ServerChecks
```

Você poderá:
- ✅ Compartilhar o link
- ✅ Permitir que outros baixem
- ✅ Aceitar contribuições (Pull Requests)
- ✅ Criar Releases
- ✅ Adicionar GitHub Actions (CI/CD)

---

## 🚀 Próximos Passos (Opcional)

### Criar Release no GitHub

1. Vá no repositório
2. Clique em `Releases` → `Create a new release`
3. Tag: `v1.0.0`
4. Title: `ServerChecks v1.0.0`
5. Descrição: Copie do CHANGELOG.md
6. Anexar: `ServerChecks-1.0.0.jar` (depois de compilar)
7. Publicar!

### Adicionar GitHub Actions (Build Automático)

Crie `.github/workflows/build.yml`:

```yaml
name: Build

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 8
      uses: actions/setup-java@v3
      with:
        java-version: '8'
        distribution: 'adopt'
    - name: Build with Maven
      run: mvn clean package
    - name: Upload artifact
      uses: actions/upload-artifact@v3
      with:
        name: ServerChecks
        path: target/ServerChecks-*.jar
```

---

**Boa sorte com seu projeto! 🎮✨**

Se tiver dúvidas, consulte:
- https://docs.github.com/pt/get-started
- https://git-scm.com/doc
