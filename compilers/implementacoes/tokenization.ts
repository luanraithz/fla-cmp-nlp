import { curry } from 'ramda'

export enum Classes {
    SpecialSymbol = 'SpecialSymbol',
    ReservedWord = 'ReservedWord',
    Identifier = 'Identifier',
    ConstantInteger = 'ConstantInteger',
    ConstantString = 'ConstantString',
    LineBreak = 'LineBreak',
    Whitespace = 'Whitespace',
    Tab = 'Tab',
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
    ignoreFormatCharacters: boolean,
    str: string,
): TokenList => {
    const tokens: TokenList = []
    let current = str
    let currentLine = 1
    const getTokenForLang = getToken(lang, ignoreFormatCharacters)
    while(current) {
        const t = getTokenForLang(current, currentLine)
        if (typeof t === 'string') {
            // ignored
            current = current.replace(t, '')
        } else if (t instanceof Error) {
            throw t
        } else {
            const [token, recongnized] = t
            if (token.class === Classes.LineBreak) {
                currentLine++
            }
            tokens.push(token)
            current = current.replace(recongnized, '')
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
    ignored: boolean,
    str: string,
    currentLine: number,
): [Token, string] | Error | string => {
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
    } else if ((match = str.match(/\n/))) {
        if (ignored) return match[0]
        tokenClass = Classes.LineBreak
    } else if ((match = str.match(/\t/))) {
        if (ignored) return match[0]
        tokenClass = Classes.Tab
    } else if ((match = str.match(/\s/))) {
        if (ignored) return match[0]
        tokenClass = Classes.Whitespace
    }

    if (match) {
        return [{
            'class': tokenClass,
            lexem: match[0],
            line: currentLine
        }, match[0]]
    }

    return new Error(`Unexpected token at the start of "${str}" at line ${currentLine}`)
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
    WordOfSpecialSymbol: WordOf(Classes.SpecialSymbol),
    WordOfTab: WordOf(Classes.Tab, '\t'),
    WordOfWhitespace: WordOf(Classes.Whitespace, ' '),
    WordOfLineBreak: WordOf(Classes.LineBreak, '\n')
}
