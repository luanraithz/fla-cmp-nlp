# Tratamento / Diagnóstico de erros de compilação

## Erros léxicos

Erro na escrita de tokens ( aka Ortigáfico ), em geral são do tipo:
    * SÍMBOLO INVÁLIDO
    * CONSTANTE LITERAL/STRING INVÁLIDA OU NÃO FINALIZADA
    * COMENTÁRIO INVÁLIDO OU NÃO FINALIZADO

Mensagem: linha ( ou posição ) do erro, "diagnóstico"

## Erros sintáticos

Erro na ordem dos tokens (aka gramatical)

Mensagem: Linha ( ou posição ), símbolo encontrado ( no programa ), os símbolo(s) esperados (conforme gramática)

## Erros semânticos

Erro no significado das estruturas sintáticas, em geral, na declaração/ uso de identificadores, na compatibilidade de tipos, entre outros.

Mensagem: linha ( ou posição ) do erro, "diagnóstico"
