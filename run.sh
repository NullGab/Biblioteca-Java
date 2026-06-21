#!/bin/bash

echo "⚙️  Iniciando a compilação do sistema..."

mkdir -p bin

find biblioteca -name "*.java" | xargs javac -d bin

if [ $? -eq 0 ]; then
    echo "Compilação concluída com sucesso! Iniciando a interface gráfica..."
    echo "------------------------------------------------------"
    java -cp bin biblioteca.Main
else
    echo "Erro na compilação. Verifique o terminal para mais detalhes."
fi
