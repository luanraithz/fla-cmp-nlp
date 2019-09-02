# LFA - Client

Código fonte do cliente do trabalho de linguagens formais (Furb 2019 concentrado).

> Disponível em [http://www.lfa.luan.raithz.com](http://www.lfa.luan.raithz.com)
    * A função que processa o grafo (junto com a definição do mesmo) pode ser encontrado [aqui](src/final.ts)

## Notas

Ao encontrar algum símbolo especial no estado inicial, é redirectionado pra um estado de `symbol`.
Ao encontrar algum símbolo inválido no estado inicial, é redirectionado pra um estado de `error`.

### Definições de tipos
```typescript
type State = { name: string, paths: Array<Path>, final?: boolean, error?: boolean }
type Path = { expression: string, to: State }
```
Onde:
`State` são os nós do grafo, contendo um array de caminhos a disponíveis, e duas flags, uma pra indicar se é erro e outra se é final
> A de `erro` serve apenas para parar a execução da função quando encontra-lo, logo o caminho percorrido fica com apenas um estado de `error`

`Path` são as arestas, que indicam o `State` destino e a condição que precisa ser respeitada.

## Rodando o projeto
```
yarn install
```

### Iniciar o server
```
yarn start
```

### Build
```
yarn run build
```


