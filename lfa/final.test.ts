import { validate, Result, Output, split } from './client/src/final'

test('Paper test', () => {
    const expected: Array<Output> = [
        { line: 1, result: Result.InvalidWord,   content: '0#0',       path: ['q0', 'q1q8', 'q3', 'q4'] },
        { line: 1, result: Result.ValidWord,     content: '1#000',     path: ['q0', 'q0q7', 'q11', 'q12', 'q13', 'q12'] },
        { line: 2, result: Result.ValidWord,     content: '0100#00',   path: ['q0', 'q1q8', 'q1q6q9', 'q2q10', 'q1q8', 'q3', 'q4', 'q5'] },
        { line: 3, result: Result.InvalidWord,   content: '00%11',     path: ['q0', 'q1q8', 'q2q10', 'qError']},
        { line: 3, result: Result.InvalidSymbol, content: '+',         path: ['q0', 'qError']},
        { line: 3, result: Result.SpecialSymbol, content: ';',         path: ['q0', 'qSymbol']},
        { line: 3, result: Result.SpecialSymbol, content: '.',         path: ['q0', 'qSymbol']},
        { line: 5, result: Result.InvalidSymbol, content: 'teste001#', path: ['q0', 'qError']},
        { line: 6, result: Result.ValidWord,     content: '10110#000', path: ['q0', 'q0q7', 'q1q8', 'q1q6q9', 'q1q6q9', 'q2q10', 'q11', 'q12', 'q13', 'q12']}
    ]

    expect(validate
        (`0#0 1#000
            0100#00
        00%11   +   ;.

        teste001#
            10110#000
        `))
        .toStrictEqual(expected)


})

test('Invalid words', () => {
    expect(validate(`#`)).toStrictEqual([{ line: 1, result: Result.InvalidWord, content: '#', path: ['q0'] }])
    expect(validate('1')).toStrictEqual([{ line: 1, result: Result.InvalidWord, content: '1', path: ['q0', 'q0q7'] }])
    expect(validate('0')).toStrictEqual([{ line: 1, result: Result.InvalidWord, content: '0', path: ['q0', 'q1q8'] }])
})

test('Special symbols', () => {
    expect(validate(`;`)).toStrictEqual([{ line: 1, result: Result.SpecialSymbol, content: ';', path: ['q0', 'qSymbol'] }])
    expect(validate(',')).toStrictEqual([{ line: 1, result: Result.SpecialSymbol, content: ',', path: ['q0', 'qSymbol'] }])
    expect(validate('.')).toStrictEqual([{ line: 1, result: Result.SpecialSymbol, content: '.', path: ['q0', 'qSymbol'] }])

    expect(validate(`;.,`)).toStrictEqual([
        { line: 1, result: Result.SpecialSymbol, content: ';', path: ['q0', 'qSymbol']},
        { line: 1, result: Result.SpecialSymbol, content: '.', path: ['q0', 'qSymbol'] },
        { line: 1, result: Result.SpecialSymbol, content: ',', path: ['q0', 'qSymbol'] }]
    )
})

test('Invalid symbols', () => {
    expect(validate(`*`)).toStrictEqual([{ line: 1, result: Result.InvalidSymbol, content: '*', path: ['q0', 'qError'] }])
    expect(validate(')')).toStrictEqual([{ line: 1, result: Result.InvalidSymbol, content: ')', path: ['q0', 'qError'] }])
    expect(validate('&')).toStrictEqual([{ line: 1, result: Result.InvalidSymbol, content: '&', path: ['q0', 'qError'] }])

})
