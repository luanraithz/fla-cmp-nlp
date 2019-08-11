export enum Classes {
    SpecialSymbol = 'SpecialSymbol',
    ReservedWord = 'ReservedWord',
    Identifier = 'Identifier',
    ConstantInteger = 'ConstantInteger',
    ConstantString = 'ConstantString'
}

enum SpecialSymbols {
    ';',
    ':',
    '.',
    ':=',
    '+',
    '-',
    '*',
    '/',
    '(',
    ')',
}


enum ReservedWords {
    'begin',
    'end',
    'int',
    'program',
    'var',
    'writeln'
}

type ReservedWordToken = {
    'class': Classes.ReservedWord,
    'lexem': ReservedWords,
    'line': number
}

type SymbolToken = {
    'class': Classes.SpecialSymbol,
    'lexem': SpecialSymbols,
    'line': number
}

type ConstantIntegerToken = {
    'class': Classes.ConstantInteger,
    'lexem': number,
    'line': number
}

type ConstantStringToken = {
    'class': Classes.ConstantString,
    'lexem': string,
    'line': number
}

type IdentifierToken = {
    'class': Classes.Identifier,
    'lexem': string,
    'line': number
}

type Token = ReservedWordToken
    | SymbolToken
    | IdentifierToken
    | ConstantIntegerToken
    | ConstantStringToken

type TokenList = Token[]

const getEnumKeys = s => `(${Object.keys(s)
    .filter(s => isNaN(Number(s)))
    .join('|')})`

const specialSymbolStr = '^[;|:|\.|:=|\\+|\\-|\\*|/|\\(|\\)]'

const specialSymbolRegexp = new RegExp(specialSymbolStr)

const reservedWordRegexp = new RegExp(`^(${getEnumKeys(ReservedWords)}(?![a-zA-z0-9]))`)

const identifierRegexp = new RegExp('^([a-zA-z][a-zA-Z0-9]*(?![a-zA-z0-9]))')

const stringConstantRegxp = new RegExp('^".*"')

const integerConstantRegex = new RegExp('^[0-9]+')

export const tokenize = (str: string): TokenList => {
    const tokens: TokenList = []
    let current = str
    let currentLine = 1
    while(current) {
        if (/^\n/.test(current)) {
            currentLine++;
            current = current.replace(/^\n/, '')
        } else if (/^\s/.test(current)) {
            current = current.replace(/^\s/, '')
        } else {
            const t = getToken(current, currentLine)
            if (t instanceof Error) {
                throw t
            } else {
                const [token, recongnized] = t
                tokens.push(token)
                current = current.replace(recongnized, '')
            }
        }
    }

    return tokens
}

const getToken = (str: string, currentLine: number): [Token, string] | Error => {
    let match
    let tokenClass
    if ((match = str.match(specialSymbolRegexp))) {
        tokenClass = Classes.SpecialSymbol
    } else if ((match = str.match(reservedWordRegexp))) {
        tokenClass = Classes.ReservedWord
    } else if ((match = str.match(identifierRegexp))) {
        tokenClass = Classes.Identifier
    } else if ((match = str.match(stringConstantRegxp))) {
        tokenClass = Classes.ConstantString
    } else if ((match = str.match(integerConstantRegex))) {
        tokenClass = Classes.ConstantInteger
    }
    if (match) {
        return [{
            'class': tokenClass,
            lexem: match[0],
            line: currentLine
        }, match[0]]
    }

    return new Error(`Unexpected token at the start of ${str}`)
}
