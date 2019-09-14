1. Expecificar o diagrama de sintaxe e BNF

##

<declaracao_variaveis> ::= var <variaveis>

<variaveis> := <lista_id> : <tipo> ; | <lista_id> : <tipo> ; <variaveis>


<lista_id> := id | id, <lista_id>

<tipo> := chat | float | int

## 

<entrada> ::= read( <lista_id> )

<lista_id> = 

## 3
<statement> ::= if <expression> then <lista_comando> <optional_else> end

<optional_else> ::= E | else <lista_comando>

<lista_comando> ::= <comando> | <comando> <lista_comando>


