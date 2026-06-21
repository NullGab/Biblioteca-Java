@echo off
echo Iniciando a compilacao do sistema...

if not exist bin mkdir bin

dir /s /B biblioteca\*.java > sources.txt

javac -d bin @sources.txt

if %errorlevel% equ 0 (
    echo Compilacao concluida com sucesso! Iniciando a interface grafica...
    echo ------------------------------------------------------
    java -cp bin biblioteca.Main
) else (
    echo Erro na compilacao. Verifique o terminal para mais detalhes.
)

del sources.txt

pause
