# Expressões regulares

## Especificação

| Expressão regular | Linguagem especificada |
| ------------------|:----------------------:|
|  Ø                | {}                     |
|  ε                | {ε}      |
| `a` ou `(a)` | {a}      |
| `b` | {b}      |
| (`União`) a|b | {a,b}      |
| (`Concatenação`) ab | {ab}      |
| (`Concatenação sucessiva`) a* | {ε, a, aa, aaa, ...}      |


### Resumo de operadores
    * `*` - Repete a expressão zero ou mais vezes
    * `+` - Repete a expressão uma ou mais vezes
    * `?` - Repete a expressão uma vez ou não acontence
    * `nª` - Repete a expressão `n` `a` vezes


### Prioridade de operador
    1. `()`
    2. Concatenação sucessiva
    3. `*` `+` `?` `bª`
    4. Concatenação
    5. União


## Conceitos básicos

Conjunto - Coleção de elementos não repetidos

Pertinência - Quando um elemento está em um conjunto.

    C = {1, 2, 3, 4}
    1 ∈ C e 5 ∉ C.

Inclusão - Conjunto contido em outro.

    C = { 1, 2, 3, 4}
    N = {1, 2, 3, 4, 5}
    C ⊂ N e N ⊃ C

Símbolo - É um elemento atômico usado em definições de linguagens. Ex:
    Letras: a, b, c, ..., A, B, C, ...
    Dígitos: 0, 1, ..., 9
    Símbolos: +, -, *, /, ...


Alfabeto - Conjunto finio não vazio de símbolos.

    Σ = { a, b}
    Σ = { 0, 1}

Palavra - Sequência finita de símbolos justapostos tomados de algum alfabeto Σ.

Palavra vazia - Palava com nenhum símbolo. (ε)

Tamanho - Número de símbolos que compoem uma palava.

    |ε| = 0
    |a| = 1

Fechamento reflexivo - Denotado por Σ*, é o conjunto de todas as palavras que podem ser formados
com os símbolos de Σ, `inclusive a palavra vazia`.

Fechamento transitivo - Denotado por Σ+, o mesmo que reflexivo, mas sem a `palavra vazia`

Linguagem - É o conjunto de todas as palavras que podem ser formadas com os símbolos
de um alfabeto, incluindo a palavra vazia.
