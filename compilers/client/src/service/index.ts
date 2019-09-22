import axios from 'axios'
import { LexicalError, LexicalResult } from './../types/lexical'

const BASE = "http://localhost:8080"

function compileLexical (content: string): Promise<{ error?: LexicalError, result?: LexicalResult }> {
    return axios.post(`${BASE}/lexical`, { content }).then(({ data }) => data)
}

export {
    compileLexical
}
