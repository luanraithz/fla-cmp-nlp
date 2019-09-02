# LFA - Client

Código fonte do cliente do trabalho de linguagens formais (Furb 2019 concentrado).

> Disponível em [http://www.lfa.luan.raithz.com](http://www.lfa.luan.raithz.com)

## Notas

* A função que processa o grafo (junto com a definição do mesmo) pode ser encontrado [aqui](src/final.ts#L120).
* Ao encontrar algum símbolo especial no estado inicial, é redirectionado pra um estado de `symbol`.
* Ao encontrar algum símbolo inválido no estado inicial, é redirectionado pra um estado de `error`.

### Definições de tipos
```typescript
type State = { name: string, paths: Array<Path>, final?: boolean, error?: boolean }
type Path = { expression: string, to: State }
```
Onde:

`State` são os nós do grafo, contendo nome, um array de caminhos disponíveis e duas flags, uma pra indicar se é erro e outra se é final

> A de `erro` serve apenas para parar a execução da função quando encontra-lo, logo o caminho percorrido fica com apenas um estado de `error`

`Path` são as arestas, que indicam o `State` destino e a condição que precisa ser respeitada.

## Rodando o projeto
```
yarn install
```

## Teste
O arquivo de teste pode ser contrado [aqui](../final.test.ts) (Ainda tem poucos testes).

Para rodar deve ser executado na root do [projeto](../../../..):
```
yarn
yarn test
```

## Deploy
Ao commitar na master o domínio já é atualizado com a versão mais recente.

### Iniciar o server
```
yarn start
```

### Build
```
yarn run build
```



