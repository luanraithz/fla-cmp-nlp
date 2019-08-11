import { tokenize, Classes } from './tokenization'

test('basic test', () => {
    expect(tokenize(' ')).toEqual([])
    expect(tokenize('writeln')).toEqual([
        {
            "class": Classes.ReservedWord,
            "lexem": "writeln",
            line: 1
        }
    ])
    expect(tokenize('writelnn')).toEqual([
        {
            "class": Classes.Identifier,
            "lexem": "writelnn",
            line: 1
        }
    ])

    expect(tokenize('writeln1')).toEqual([
        {
            "class": Classes.Identifier,
            "lexem": "writeln1",
            line: 1
        }
    ])
    expect(tokenize('writeln0')).toEqual([
        {
            "class": Classes.Identifier,
            "lexem": "writeln0",
            line: 1
        }
    ])
    expect(tokenize('-')).toEqual([
        {
            "class": Classes.SpecialSymbol,
            "lexem": "-",
            line: 1
        }
    ])

    const script = `
        writeln(a)
    `
    expect(tokenize(script)).toEqual([
        {
            "class": Classes.ReservedWord,
            "lexem": "writeln",
            line: 2
        },
        {
            "class": Classes.SpecialSymbol,
            "lexem": "(",
            line: 2
        },
        {
            "class": Classes.Identifier,
            "lexem": "a",
            line: 2
        },
        {
            "class": Classes.SpecialSymbol,
            "lexem": ")",
            line: 2
        },
    ])

    const script2 = `
        writeln("a")
    `
    expect(tokenize(script2)).toEqual([
        {
            "class": Classes.ReservedWord,
            "lexem": "writeln",
            line: 2
        },
        {
            "class": Classes.SpecialSymbol,
            "lexem": "(",
            line: 2
        },
        {
            "class": Classes.ConstantString,
            "lexem": "\"a\"",
            line: 2
        },
        {
            "class": Classes.SpecialSymbol,
            "lexem": ")",
            line: 2
        },
    ])

    const script3 = `
        writeln(1)
    `
    expect(tokenize(script3)).toEqual([
        {
            "class": Classes.ReservedWord,
            "lexem": "writeln",
            line: 2
        },
        {
            "class": Classes.SpecialSymbol,
            "lexem": "(",
            line: 2
        },
        {
            "class": Classes.ConstantInteger,
            "lexem": "1",
            line: 2
        },
        {
            "class": Classes.SpecialSymbol,
            "lexem": ")",
            line: 2
        },
    ])
})
