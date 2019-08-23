# Lista 2

## 1

### A) identificador => letra | dígito

Identificador recebe uma letra ou um dígito

### B) identificador => letra dígito

Identificador recebe uma letra e um dígito

### C) identificador => letra letra letra letra

Identificador recebe quatro letras

### D) identificador => letra+

Identificador recebe uma ou mais letras

### E) identificador => letra+ dígito*

Identificador recebe uma ou mais letras e zero ou mais dígitos

### F) identificador => letra letra? letra? letra?

Identificador recebe de uma até quatro letras

### G) identificador => letra (letra?)³

Identificador recebe de uma até quatro letras

### H) identificador => letra (letra³)?

Identificador recebe uma ou quatro letras

## 2

### a

#### 1) Nome (ou sobrenome): inicia com maiúsculas seguido por uma ou mais letras minúsculas

nomea1 = [A-Z][a-z]+


#### 2) Nome seguido por um ou mais sobrenomes, separados uns dos outros por espaço em branco

nomea2 [A-Z][a-z]+(\s[A-Z][a-z]+)+

#### 3) Nome seguido por um ou mais sobrenomes, separados uns dos outros por espaço em branco ou por connectivos (das, da, de, do dos)

nomea3 = [A-Z][a-z]+((\s(de|das|da|do|dos))?\s[A-Z][a-z]+)+

### b) de 0,00 até 10,00

notab = ([0-9],[0-9]²) | 10,00

### c) de
        0,00 - 10,00
        0 - 10
        0,0 - 10,0

notac = [0-9](,[0-9][0-9]?)? | 10(,00?)?

### d) CPF

cpf = [0-9]³\.[0-9]³\.[0-9]³-[0-9]²

### e)
automato = (+|-)?[0-9]+((\.[0-9]+)?E(+|-)?[0-9]+)?

### f
dia = 3(0|1) | (1|2|0)[0-9]
mes = [0-9] | 1(0|1|2)
ano = [0-9]4
data = dia/mes/ano

