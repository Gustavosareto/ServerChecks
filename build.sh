#!/bin/bash
# ====================================
# ServerChecks - Build Script (Linux/Mac)
# ====================================

echo ""
echo "===================================="
echo "  ServerChecks Build Script"
echo "===================================="
echo ""

# Verificar se Maven está instalado
if ! command -v mvn &> /dev/null; then
    echo "[ERRO] Maven não encontrado!"
    echo "Por favor, instale Maven: https://maven.apache.org/download.cgi"
    exit 1
fi

echo "[1/4] Limpando builds anteriores..."
mvn clean
if [ $? -ne 0 ]; then
    echo "[ERRO] Falha ao limpar projeto!"
    exit 1
fi

echo ""
echo "[2/4] Compilando código fonte..."
mvn compile
if [ $? -ne 0 ]; then
    echo "[ERRO] Falha na compilação!"
    exit 1
fi

echo ""
echo "[3/4] Gerando arquivo JAR..."
mvn package
if [ $? -ne 0 ]; then
    echo "[ERRO] Falha ao gerar JAR!"
    exit 1
fi

echo ""
echo "[4/4] Verificando arquivo gerado..."
if [ -f "target/ServerChecks-1.0.0.jar" ]; then
    echo ""
    echo "===================================="
    echo "  BUILD CONCLUÍDO COM SUCESSO!"
    echo "===================================="
    echo ""
    echo "Arquivo gerado: target/ServerChecks-1.0.0.jar"
    echo ""
    echo "Para instalar:"
    echo "1. Copie o JAR para a pasta plugins/ do servidor"
    echo "2. Certifique-se que Vault está instalado"
    echo "3. Reinicie o servidor"
    echo ""
else
    echo "[ERRO] Arquivo JAR não foi gerado!"
    exit 1
fi
