import { LexicalResult } from './../types/lexical'

const Types = new Map<string, string>([
    ["EPSILON", "Epsilon"],
    ["DOLAR", "Dolar"],
    ["RESERVED_WORD", "Palavra reservada"],
    ["ID_INT", "identificador"],
    ["ID_FLOAT", "identificador"],
    ["ID_STRING", "identificador"],
    ["ID_BOOL", "identificador"],
    ["ID_COMPOSTO", "identificaodr"],
    ["INT", "constante int"],
    ["FLOAT", "constante float"],
    ["STRING", "constante string"],
    ["SPECIAL_SYMBOL", "símbolo especial"]
])

export const formatData = (items: LexicalResult): LexicalResult => items
    .map(s => ({ ...s, type: Types.get(s.type) || ""}))
    .filter(({ type }) => type)
