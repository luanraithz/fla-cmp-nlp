export type LexicalError = {
    lexeme: string,
    line: Number,
    position: Number,
    message: string
}

export type Result = {
    position: number,
    lexeme: string,
    type: string
}

export type LexicalResult = Array<Result>
