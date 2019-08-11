# Compilador conceitual

## Analisador léxico

Função: Ler o programa fonte caracter a caracter, identificando `tokens`
e classificando-os em categorias (palavras reservadas, símbolos especiais,
identificadores, constantes).

Saída: Lista de tokens (lexema, classe, linha).

## Analisador sintático

Função: Agrupar tokens em estruturas sintáticas de acordo com a
gramática especificada, construindo `árvore sintática` correspondente.

> Especificação é feito com gramática regular

Saída: Árvore sintática (árvore de derivação)

## Analisador semântico

Função: `verificar` se as estruturas sintáticas estão semanticamente corretas,
`extraindo` informações do programa fonte que permitam a geração de código intermediário.

Saída: Tabela de símbolos (identificador, categoria)

## Gerador de código intermediário

Função: `gerar` um código independente da máquina alvo, facilitando o processo
de tradução de código fonte para o código objeto.

Tipos:
    Represetanções gráficas (árvore sintática abstrata, grafo de sintaxe);
    Representações textuais (código de 3 endereços, notações polonesa - maquina de pilha);

Saída: Código intermediário

