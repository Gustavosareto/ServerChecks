@echo off
REM ====================================
REM ServerChecks - Build Script
REM ====================================

echo.
echo ====================================
echo   ServerChecks Build Script
echo ====================================
echo.

REM Verificar se Maven está instalado
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo [ERRO] Maven não encontrado!
    echo Por favor, instale Maven: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

echo [1/4] Limpando builds anteriores...
call mvn clean
if %ERRORLEVEL% NEQ 0 (
    echo [ERRO] Falha ao limpar projeto!
    pause
    exit /b 1
)

echo.
echo [2/4] Compilando codigo fonte...
call mvn compile
if %ERRORLEVEL% NEQ 0 (
    echo [ERRO] Falha na compilacao!
    pause
    exit /b 1
)

echo.
echo [3/4] Gerando arquivo JAR...
call mvn package
if %ERRORLEVEL% NEQ 0 (
    echo [ERRO] Falha ao gerar JAR!
    pause
    exit /b 1
)

echo.
echo [4/4] Verificando arquivo gerado...
if exist "target\ServerChecks-1.0.0.jar" (
    echo.
    echo ====================================
    echo   BUILD CONCLUIDO COM SUCESSO!
    echo ====================================
    echo.
    echo Arquivo gerado: target\ServerChecks-1.0.0.jar
    echo.
    echo Para instalar:
    echo 1. Copie o JAR para a pasta plugins/ do servidor
    echo 2. Certifique-se que Vault esta instalado
    echo 3. Reinicie o servidor
    echo.
) else (
    echo [ERRO] Arquivo JAR não foi gerado!
    pause
    exit /b 1
)

pause
