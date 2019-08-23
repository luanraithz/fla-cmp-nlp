
test('basic test', () => {
    const nameRegExp = /^[A-Z][a-z]+$/

    expect(nameRegExp.test('Luan')).toBe(true)
    expect(nameRegExp.test('LUan')).toBe(false)
    expect(nameRegExp.test('lUan')).toBe(false)
    expect(nameRegExp.test('Luan ')).toBe(false)

    const nameSeparedBySpace =  /^[A-Z][a-z]+(\s[A-Z][a-z]+)*$/

    expect(nameSeparedBySpace.test('Luan')).toBe(true)
    expect(nameSeparedBySpace.test('LUan')).toBe(false)
    expect(nameSeparedBySpace.test('lUan')).toBe(false)
    expect(nameSeparedBySpace.test('Luan ')).toBe(false)

    expect(nameSeparedBySpace.test('Luan Raithz')).toBe(true)
    expect(nameSeparedBySpace.test('LUan RAithz')).toBe(false)
    expect(nameSeparedBySpace.test('LUan Raithz ')).toBe(false)
    expect(nameSeparedBySpace.test('Luan Raithz Machado')).toBe(true)
    expect(nameSeparedBySpace.test('LUan Raithz MAchado')).toBe(false)
    expect(nameSeparedBySpace.test('LUan Raithz Machado ')).toBe(false)

    const nameSeparedBySpaceOrConnective = /^[A-Z][a-z]+((\s(de|das|da|do|dos))?\s[A-Z][a-z]+)*$/

    expect(nameSeparedBySpaceOrConnective.test('Luan')).toBe(true)
    expect(nameSeparedBySpaceOrConnective.test('LUan')).toBe(false)
    expect(nameSeparedBySpaceOrConnective.test('lUan')).toBe(false)
    expect(nameSeparedBySpaceOrConnective.test('Luan ')).toBe(false)
    expect(nameSeparedBySpaceOrConnective.test('Luan Raithz')).toBe(true)
    expect(nameSeparedBySpaceOrConnective.test('LUan RAithz')).toBe(false)
    expect(nameSeparedBySpaceOrConnective.test('LUan Raithz ')).toBe(false)
    expect(nameSeparedBySpaceOrConnective.test('Luan Raithz Machado')).toBe(true)
    expect(nameSeparedBySpaceOrConnective.test('LUan Raithz MAchado')).toBe(false)
    expect(nameSeparedBySpaceOrConnective.test('LUan Raithz Machado ')).toBe(false)

    expect(nameSeparedBySpaceOrConnective.test('Luan Raithz Machado')).toBe(true)
    expect(nameSeparedBySpaceOrConnective.test('LUan Raithz MAchado')).toBe(false)
    expect(nameSeparedBySpaceOrConnective.test('Luan  Raithz Machado ')).toBe(false)

    expect(nameSeparedBySpaceOrConnective.test('Luan dos Raithz Machado')).toBe(true)
    expect(nameSeparedBySpaceOrConnective.test('LUan du Raithz MAchado')).toBe(false)
    expect(nameSeparedBySpaceOrConnective.test('Luan  Raithz Machado ')).toBe(false)
    expect(nameSeparedBySpaceOrConnective.test('Luan de Raithz de Machado')).toBe(true)
    expect(nameSeparedBySpaceOrConnective.test('Luan Raithz Machado ')).toBe(false)
})
