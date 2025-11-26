# 🔄 Como Atualizar o GitHub

## Comandos Rápidos (Copy/Paste)

```powershell
# 1. Ver o que mudou
git status

# 2. Adicionar todas as mudanças
git add .

# 3. Fazer commit com descrição
git commit -m "Descrição da atualização"

# 4. Enviar para o GitHub
git push
```

---

## 📝 Exemplo Completo

**Você fez mudanças no código e quer enviar:**

```powershell
cd "c:\Users\gusta\OneDrive\Documentos\EconomiaMedia"

git add .

git commit -m "Feature: Adicionado comando /cheque auto baseado na economia"

git push
```

---

## 💡 Dicas de Mensagens de Commit

**Boas mensagens:**
```powershell
git commit -m "Feature: Comando /cheque auto com multiplicadores dinâmicos"
git commit -m "Fix: Corrigido bug na validação de cheques"
git commit -m "Update: Melhorada formatação de valores"
git commit -m "Docs: Atualizado README com novos comandos"
git commit -m "Refactor: Reorganizado código do ChequeManager"
```

**Prefixos úteis:**
- `Feature:` - Nova funcionalidade
- `Fix:` - Correção de bug
- `Update:` - Atualização/melhoria
- `Docs:` - Documentação
- `Refactor:` - Refatoração de código
- `Performance:` - Melhoria de performance
- `Style:` - Mudanças de formatação

---

## 🏷️ Criar Versões (Releases)

Quando terminar uma versão importante:

```powershell
# Fazer commit normalmente
git add .
git commit -m "Release: v1.1.0 - Comando auto e suporte a console"

# Criar tag da versão
git tag -a v1.1.0 -m "Versão 1.1.0 - Cheques automáticos"

# Enviar com a tag
git push origin main --tags
```

Depois vá no GitHub:
1. **Releases** → **Create a new release**
2. Escolha a tag **v1.1.0**
3. Adicione descrição e o arquivo `.jar`
4. Publique!

---

## ⚠️ Resolver Conflitos

Se aparecer erro ao dar `git push`:

```powershell
# Baixar mudanças do GitHub primeiro
git pull

# Depois enviar
git push
```

---

## 📊 Ver Histórico

```powershell
# Ver últimos commits
git log --oneline

# Ver mudanças específicas
git diff

# Ver status atual
git status
```

---

## 🔙 Desfazer Mudanças (Cuidado!)

```powershell
# Desfazer mudanças não commitadas
git restore arquivo.java

# Desfazer último commit (mantém mudanças)
git reset --soft HEAD~1

# Desfazer último commit (apaga mudanças - CUIDADO!)
git reset --hard HEAD~1
```

---

## ✅ Fluxo Completo Recomendado

```powershell
# 1. Ver o que mudou
git status

# 2. Adicionar arquivos
git add .

# 3. Commit descritivo
git commit -m "Feature: Nova funcionalidade X"

# 4. Enviar
git push

# 5. Verificar no GitHub
# Acesse: https://github.com/Gustavosareto/ServerChecks
```

---

**Pronto! Suas mudanças estarão no GitHub! 🚀**
