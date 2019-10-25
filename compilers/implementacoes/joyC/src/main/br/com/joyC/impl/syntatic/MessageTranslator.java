package main.br.com.joyC.impl.syntatic;

import main.br.com.joyC.gaals.SyntaticError;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;


public class MessageTranslator {
    static private String parseExpressionError(SyntaticError err, String entry) {
        var errorKey = entry.charAt(err.getPosition());
        return MessageFormat.format("encontrado {0} esperada <expressao>", String.valueOf(errorKey));
    }
    private static final Map<String, BiFunction<SyntaticError, String, String>> messages = new HashMap<>();
    static {
        messages.put("", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado fim de programa", (SyntaticError err, String entry) -> "Esperado fim de arquivo");
        messages.put("Era esperado palavraReservada", (SyntaticError err, String entry) -> "Era esperado palavraReservada");
        messages.put("Era esperado input", (SyntaticError err, String entry) -> "Era esperado input");
        messages.put("Era esperado isFalseDo", (SyntaticError err, String entry) -> "Era esperado isFalseDo");
        messages.put("Era esperado isTrueDo", (SyntaticError err, String entry) -> "Era esperado isTrueDo");
        messages.put("Era esperado main", (SyntaticError err, String entry) -> "Era esperado main");
        messages.put("Era esperado output", (SyntaticError err, String entry) -> "Era esperado output");
        messages.put("Era esperado true", (SyntaticError err, String entry) -> "Era esperado true");
        messages.put("Era esperado types", (SyntaticError err, String entry) -> "Era esperado types");
        messages.put("Era esperado while", (SyntaticError err, String entry) -> "Era esperado while");
        messages.put("Era esperado if", (SyntaticError err, String entry) -> "Era esperado if");
        messages.put("Era esperado false", (SyntaticError err, String entry) -> "Era esperado false");
        messages.put("Era esperado idInt", (SyntaticError err, String entry) -> "Era esperado idInt");
        messages.put("Era esperado idFloat", (SyntaticError err, String entry) -> "Era esperado idFloat");
        messages.put("Era esperado idString", (SyntaticError err, String entry) -> "Era esperado idString");
        messages.put("Era esperado idBool", (SyntaticError err, String entry) -> "Era esperado idBool");
        messages.put("Era esperado idComposto", (SyntaticError err, String entry) -> "Era esperado idComposto");
        messages.put("Era esperado int", (SyntaticError err, String entry) -> "Era esperado int");
        messages.put("Era esperado float", (SyntaticError err, String entry) -> "Era esperado float");
        messages.put("Era esperado string", (SyntaticError err, String entry) -> "Era esperado string");
        messages.put("Era esperado \"[\"", (SyntaticError err, String entry) -> "Era esperado \"[\"");
        messages.put("Era esperado \"]\"", (SyntaticError err, String entry) -> "Era esperado \"]\"");
        messages.put("Era esperado \"(\"", (SyntaticError err, String entry) -> "Era esperado \"(\"");
        messages.put("Era esperado \")\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \";\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \":\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \",\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \"=\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \".\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \"&&\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \"||\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \"!\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \"==\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \"!=\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \"<\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \"<=\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \">\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \">=\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \"+\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \"-\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \"/\"", (SyntaticError err, String entry) -> "");
        messages.put("Era esperado \"*\"", (SyntaticError err, String entry) -> "");
        messages.put("<lang> inválido", (SyntaticError err, String entry) -> "encontrado fim de arquivo esperado main types");
        messages.put("<opt_tipos> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<tipos> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<types_dec> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<types_dec1> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<type_dec> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<lista_de_campos> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<lista_de_campos1> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<campo> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<opt_var_decs> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<variaveis> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<variavel_dec> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<variavel_dec1> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<id_do_tipo_simples> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<var_type> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<opt_arr_size> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<id_do_tipo_composto> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<arr_types> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<lista_de_comandos> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<lista_de_comandos1> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<comando> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<atribuicao> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<identificador> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<variavel_uso> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<opt_expr> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<opt_field> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<entrada_de_dados> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<lista_de_identificadores> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<lista_de_identificadores1> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<true_statement> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<false_statement> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<saida_de_dados> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<lista_expressoes> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<lista_expressoes1> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<comando_selecao> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<optional_false> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<statement> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<comando_repeticao> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<expressao> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<expressao1> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<elemento> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<relacional> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<relacional1> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<operador_relacional> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<aritmetica> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<aritmetica1> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<termo> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<termo1> inválido", (SyntaticError err, String entry) -> "");
        messages.put("<fator> inválido", MessageTranslator::parseExpressionError);
    }

    public static String translate(SyntaticError err, String entry) {
        return messages.get(err.getMessage()).apply(err, entry);
    }
}
