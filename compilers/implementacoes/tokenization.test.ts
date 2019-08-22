import { tokenize, Classes, Helpers } from './tokenization'

const langSpec = {
    specialSymbolRegexp: /^[;|:|\.|:=|\+|\-|\*|/|\(|\)]/,
    reservedWordRegexp: /^((begin|end|int|program|var|writeln)(?![a-zA-z0-9]))/,
    identifierRegexp: /^([a-zA-z][a-zA-Z0-9]*(?![a-zA-z0-9]))/,
    stringConstantRegxp: /^".*"/,
    integerConstantRegexp: /^[0-9]+/
}

const langTokenizer = tokenize(langSpec, true)

test('basic test', () => {


    expect(langTokenizer(' ')).toEqual([])

    expect(langTokenizer(`



    `)).toEqual([])
    expect(langTokenizer(`
    


    `)).toEqual([])
    expect(langTokenizer('writeln')).toEqual([Helpers.WordOfReservedWord('writeln', 1)])
    expect(langTokenizer('writelnn')).toEqual([Helpers.WordOfIdentifier('writelnn', 1)])
    expect(langTokenizer('writeln1')).toEqual([Helpers.WordOfIdentifier('writeln1', 1)])
    expect(langTokenizer('-')).toEqual([Helpers.WordOfSpecialSymbol('-', 1)])

    expect(langTokenizer(`
        writeln(a)
    `))
    .toEqual([
        Helpers.WordOfReservedWord('writeln', 2),
        Helpers.WordOfSpecialSymbol('(', 2),
        Helpers.WordOfIdentifier('a', 2),
        Helpers.WordOfSpecialSymbol(')', 2)
    ])


    expect(langTokenizer(
    `
        writeln(1)
    `))
    .toEqual([
        Helpers.WordOfReservedWord('writeln', 2),
        Helpers.WordOfSpecialSymbol('(', 2),
        Helpers.WordOfConstantInteger("1", 2),
        Helpers.WordOfSpecialSymbol(')', 2)
    ])

    expect(() => langTokenizer('@')).toThrowError('Unexpected token at the start of "@" at line 1')
})

test('constant string', () => {
    expect(langTokenizer(`
        writeln("a")
    `))
    .toEqual([
        Helpers.WordOfReservedWord('writeln', 2),
        Helpers.WordOfSpecialSymbol('(', 2),
        Helpers.WordOfConstantString('"a"', 2),
        Helpers.WordOfSpecialSymbol(')', 2)
    ])

})
