import axios from 'axios'
import { LexicalError, LexicalResult } from './../types/lexical'
import { SyntacticError } from './../types/syntactic'

const BASE = "https://api.compilers.luan.raithz.com"

function compileLexical (content: string): Promise<{ error?: LexicalError, result?: LexicalResult }> {
    return axios.post(`${BASE}/lexical`, { content }).then(({ data }) => data)
}

function compileSyntactic (content: string): Promise<{ error?: SyntacticError, result?: string}> {
    return axios.post(`${BASE}/syntactic`, { content }).then(({ data }) => data)
}

function compileSemantic (content: string): Promise<{ error?: any, result: string }> {
    return axios.post(`${BASE}/semantic`, { content }).then(({ data }) => data)
}

export {
    compileLexical,
    compileSyntactic,
    compileSemantic
}
