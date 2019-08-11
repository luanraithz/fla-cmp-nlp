import { curry } from 'rambda'

export enum Classes {
    SpecialSymbol = 'SpecialSymbol',
    ReservedWord = 'ReservedWord',
    Identifier = 'Identifier',
    ConstantInteger = 'ConstantInteger',
    ConstantString = 'ConstantString'
}

type Token = {
    'class': Classes,
    'lexem': string,
    'line': number
}

type TokenList = Token[]

type LanguageSpec = {
    integerConstantRegexp: RegExp,
    reservedWordRegexp: RegExp,
    identifierRegexp: RegExp,
    stringConstantRegxp: RegExp,
    specialSymbolRegexp: RegExp,
}

export const tokenize = curry((
    lang: LanguageSpec,
    str: string
): TokenList => {
    const tokens: TokenList = []
    let current = str
    let currentLine = 1
    const getTokenForLang = getToken(lang)
    while(current) {
        if (/^\n/.test(current)) {
            currentLine++;
            current = current.replace(/^\n/, '')
        } else if (/^\s/.test(current)) {
            current = current.replace(/^\s/, '')
        } else {
            const t = getTokenForLang(current, currentLine)
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
})

const getToken = curry((
    {
        specialSymbolRegexp,
        identifierRegexp,
        reservedWordRegexp,
        stringConstantRegxp,
        integerConstantRegexp
    }: LanguageSpec,
    str: string,
    currentLine: number
): [Token, string] | Error => {
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
    } else if ((match = str.match(integerConstantRegexp))) {
        tokenClass = Classes.ConstantInteger
    }

    if (match) {
        return [{
            'class': tokenClass,
            lexem: match[0],
            line: currentLine
        }, match[0]]
    }

    return new Error(`Unexpected token at the start of "${str}"`)
})

const WordOf = curry((
    lexemClass: Classes,
    lexem: string,
    line: number
) => ({
    "class": lexemClass,
    lexem,
    line
}))

export const Helpers = {
    WordOfReservedWord: WordOf(Classes.ReservedWord),
    WordOfConstantInteger: WordOf(Classes.ConstantInteger),
    WordOfIdentifier: WordOf(Classes.Identifier),
    WordOfConstantString: WordOf(Classes.ConstantString),
    WordOfSpecialSymbol: WordOf(Classes.SpecialSymbol)
}
