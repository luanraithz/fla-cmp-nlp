import { flatten, pipe, curry } from 'ramda'

type State = { name: string, paths: Array<Path>, final?: boolean, error?: boolean }
type Path = { expression: string, to: State }

const specialSymbol = '.,;'
const alphabet = '#10'

let errorState: State;
errorState = { name: "qError", paths: [], error: true }

let specialSymbolState: State;
specialSymbolState = { name: "qSymbol", paths: [{ expression: '', to: errorState }] }
specialSymbolState.paths[0].to = specialSymbolState

function getGraph() {

    const q0: State = { name: 'q0', paths: [] }
    const q1q8: State = { name: 'q1q8', paths: [] }
    const q0q7: State = { name: 'q0q7', paths: [] }
    const q2q10: State = { name: 'q2q10', paths: [] }
    const q1q6q9: State = { name: 'q1q6q9', paths: [] }
    const q2q7: State = { name: 'q2q7', paths: [] }
    const q3: State = { name: 'q3', paths: [] }
    const q11: State = { name: 'q11', paths: [] }
    const q4: State = { name: 'q4', paths: [] }
    const q5: State = { name: 'q5', paths: [], final: true }
    const q12: State = { name: 'q12', paths: [], final: true }
    const q13: State = { name: 'q13', paths: [] }

    q0.paths.push({ expression: '0', to: q1q8 })
    q0.paths.push({ expression: '1', to: q0q7 })
    q0.paths.push({ expression: specialSymbol, to: specialSymbolState })
    q0.paths.push({ expression: '.', to: errorState })

    q1q8.paths.push({ expression: '#', to: q3 })
    q1q8.paths.push({ expression: '0', to: q2q10 })
    q1q8.paths.push({ expression: '1', to: q1q6q9 })

    q0q7.paths.push({ expression: '#', to: q11 })
    q0q7.paths.push({ expression: '0', to: q1q8 })
    q0q7.paths.push({ expression: '1', to: q0q7 })

    q2q10.paths.push({ expression: '#', to: q11 })
    q2q10.paths.push({ expression: '0', to: q1q8 })
    q2q10.paths.push({ expression: '1', to: q2q7 })

    q1q6q9.paths.push({ expression: '#', to: q3 })
    q1q6q9.paths.push({ expression: '0', to: q2q10 })
    q1q6q9.paths.push({ expression: '1', to: q1q6q9 })

    q2q7.paths.push({ expression: '#', to: q11 })
    q2q7.paths.push({ expression: '0', to: q1q8 })
    q2q7.paths.push({ expression: '1', to: q2q7 })

    q3.paths.push({ expression: '0', to: q4 })

    q11.paths.push({ expression: '0', to: q12 })

    q4.paths.push({ expression: '0', to: q5 })

    q5.paths.push({ expression: '0', to: q4 })

    q12.paths.push({ expression: '0', to: q13 })

    q13.paths.push({ expression: '0', to: q12 })

    return q0
}

export enum Result {
    InvalidWord =  'Invalid Word',
    ValidWord = 'Valid Word',
    InvalidSymbol = 'Invalid Symbol',
    SpecialSymbol = 'Special Symbol'
}

export type Output = {
    line: number,
    result: Result,
    path: Array<String>,
    content: string
}


type SplitOutput = {
    line: number,
    content: string
}

const splitByLine = (str: string): Array<string> => str.split('\n')

export const split = pipe(splitByLine,
    (str: Array<string>): Array<SplitOutput> =>
        flatten(str.map((s, i) =>
            flatten(s.split(' ').map(w => {
                    const rsf: Array<SplitOutput> = []
                    let current = ""
                    function add(content: string) {
                        if (content) rsf.push({ content, line: i + 1 })
                    }
                    w.split('').forEach(t => {
                        if (specialSymbol.includes(t)) {
                            add(current)
                            add(t)
                            current = ''
                        } else if(t) {
                            current += t.trim()
                        }
                    })
                    add(current)
                    return rsf
                }))
        )).filter(Boolean))



const namesOf = (states: Array<State>): Array<String> => states.map(p => p.name)

const run = curry((graph: State, str: Array<SplitOutput>): Array<Output> =>
    str.map(({ content, line }) => {
        const runNextState = (currentState: State, [sym, ...value]: string, pathSoFar: Array<State>): Output => {
            const resultOf = (result: Result): Output => ({ line, content, result, path: namesOf([...pathSoFar, currentState]) })

            if (currentState.error)
                return resultOf(pathSoFar.length > 1 ? Result.InvalidWord: Result.InvalidSymbol )
            else if (!sym)
                return resultOf(currentState.final ? Result.ValidWord : ( currentState === specialSymbolState ?  Result.SpecialSymbol : Result.InvalidWord ))

            let next = currentState.paths.find((p: Path) => p.expression.includes(sym))
            if(!next) {
                if (alphabet.includes(sym))
                    return resultOf(Result.InvalidWord)
                else
                    next = { to: errorState, expression: '' }
            }

            const { to } = next

            return runNextState(to, value.join(''), pathSoFar.concat([currentState]))
        }

        return runNextState(graph, content, [])
    }))

export const validate = pipe(split, run(getGraph()))
