import axios from 'axios'
import { LexicalError, LexicalResult } from './../types/lexical'
import { SyntacticError } from './../types/syntactic'

const BASE = "http://localhost:8080"

function compileLexical (content: string): Promise<{ error?: LexicalError, result?: LexicalResult }> {
    return axios.post(`${BASE}/lexical`, { content }).then(({ data }) => data)
}

function compileSyntactic (content: string): Promise<{ error?: SyntacticError, result?: string}> {
    return axios.post(`${BASE}/syntactic`, { content }).then(({ data }) => data)
}

export {
    compileLexical,
    compileSyntactic
}
