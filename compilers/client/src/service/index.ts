import axios from 'axios'
import { LexicalError, LexicalResult } from './../types/lexical'
import { SyntaticError } from './../types/syntatic'

const BASE = "http://localhost:8080"

function compileLexical (content: string): Promise<{ error?: LexicalError, result?: LexicalResult }> {
    return axios.post(`${BASE}/lexical`, { content }).then(({ data }) => data)
}

function compileSyntatic (content: string): Promise<{ error?: SyntaticError, result?: string}> {
    return axios.post(`${BASE}/syntatic`, { content }).then(({ data }) => data)
}

export {
    compileLexical,
    compileSyntatic
}
