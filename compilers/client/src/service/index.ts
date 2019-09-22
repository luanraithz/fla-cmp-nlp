import axios from 'axios'

type LexicalError = {
    lexeme: string,
    line: Number,
    position: Number,
    message: string
}

type Result = {
    position: number,
    lexeme: string,
    type: string
}

const BASE = "http://localhost:8080"

export const compileLexical =
    (content: string): Promise<{ error?: LexicalError, result?: Array<Result> }> => axios.post(`${BASE}/lexical`, { content }).then(({ data }) => data)
