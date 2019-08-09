# Gramáticas

## Gramáticas regulares

### Definições

Vt - Símbolos terminais {a, b}, {0, 1} letras minúsculas, digitos, outros símbolos.

Vn - Símbolos não terminais (A, B, C, ... Z)

S - Símbolo não terminal inicial ( primeira letra maiúscula )

P - Regras de produção ( regras sintáticas )

### Forma de escrever gramáticas regulares

A -> w | wB | ε ou A -> w | Bw | ε

Onde `w` é um ou mais símbolos terminais

A e B são símbolos terminais


### Exemplo de gramática regular

S -> 0S | 1R

R -> 1R | 0T | 0 | ε

T -> 0T | 0R | 1R


### Especificar sintaxe ( usando gramática )

Especificar uma gramática regular para a sintaxe:

`read (<Lista identificadores>)`

Onde <Lista identificadores> pode conter um ou mais identificadores (`id`) separados um do outro por vírgula.

```
read(nome);
read(nome, idade);
```
S -> read(L

L -> id); | id,L

`id` = \w+


## Linguagens livres de contexto

Compreendem um universo mais amplo de linguagens, se comparado com linguagens regulares

`Formalismo`: Autômatos de pilha, gramáticas livres de contexto.

`Algoritmos (reconhecimento/geração)`: São relativamente simples e eficientes
(templo de reconhecimento é proporcional ao dobro do tamanho da entrada).

`Aplicações`: Especificações e reconhecimento da estrutura sintática de linguagens de programação
( parênteses balanceados, construções bloco-estruturadas, precedência e associatividade de operadores,
entre outras), geradores de analisadores sintáticos, estruturação formal e análise computacional.

### Definição
`G = (Vn, Vt, P, S)`

Pode ser processada com:

Derivação (`=>`): Substituição de uma palavra ou parte dela por outra de acordo com as regras de produção
da gramática, partido do `símbolo inicial -> Palavra`.

Redução (`<=`): Mesmo que derivação, mas partindo da `palavra -> símbolo inicial`.
