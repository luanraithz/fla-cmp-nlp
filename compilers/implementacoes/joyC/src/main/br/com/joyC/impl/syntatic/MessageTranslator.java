package main.br.com.joyC.impl.syntatic;

import main.br.com.joyC.gaals.SyntaticError;
import main.br.com.joyC.impl.utils.LineCounter;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;


public class MessageTranslator {
    static final String SIMPLE_ID_MESSAGE = "identificador (de int, float, bool ou string)";
    static private BiFunction<SyntaticError, String, String> genericFormattedBuilder(String message) {
        return (SyntaticError err, String entry) -> genericErrorFormatted(err, entry, message);
    }

    static private String genericErrorFormatted(SyntaticError err, String entry, String format) {
        var position = err.getPosition();
        var errorKey = entry.charAt(position);
        var lineCount = LineCounter.count(entry.substring(0, position + 1));
        return MessageFormat.format(format, lineCount, String.valueOf(errorKey));
    }

    static private String parseExpressionError(SyntaticError err, String entry) {
        return genericErrorFormatted(err, entry, "Erro na linha {0} - encontrado {1} esperada expressão");
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
        messages.put("Era esperado \")\"", (SyntaticError err, String entry) -> "Era esperado \")\"");
        messages.put("Era esperado \";\"", (SyntaticError err, String entry) -> "Era esperado \";\"");
        messages.put("Era esperado \":\"", (SyntaticError err, String entry) -> "Era esperado \":\"");
        messages.put("Era esperado \",\"", (SyntaticError err, String entry) -> "Era esperado \",\"");
        messages.put("Era esperado \"=\"", (SyntaticError err, String entry) -> "Era esperado \"=\"");
        messages.put("Era esperado \".\"", (SyntaticError err, String entry) -> "Era esperado \".\"");
        messages.put("Era esperado \"&&\"", (SyntaticError err, String entry) -> "Era esperado \"&&\"");
        messages.put("Era esperado \"||\"", (SyntaticError err, String entry) -> "Era esperado \"||\"");
        messages.put("Era esperado \"!\"", (SyntaticError err, String entry) -> "Era esperado \"!\"");
        messages.put("Era esperado \"==\"", (SyntaticError err, String entry) -> "Era esperado \"==\"");
        messages.put("Era esperado \"!=\"", (SyntaticError err, String entry) -> "Era esperado \"!=\"");
        messages.put("Era esperado \"<\"", (SyntaticError err, String entry) -> "Era esperado \"<\"");
        messages.put("Era esperado \"<=\"", (SyntaticError err, String entry) -> "Era esperado \"<=\"");
        messages.put("Era esperado \">\"", (SyntaticError err, String entry) -> "Era esperado \">\"");
        messages.put("Era esperado \">=\"", (SyntaticError err, String entry) -> "Era esperado \">=\"");
        messages.put("Era esperado \"+\"", (SyntaticError err, String entry) -> "Era esperado \"+\"");
        messages.put("Era esperado \"-\"", (SyntaticError err, String entry) -> "Era esperado \"-\"");
        messages.put("Era esperado \"/\"", (SyntaticError err, String entry) -> "Era esperado \"/\"");
        messages.put("Era esperado \"*\"", (SyntaticError err, String entry) -> "Era esperado \"*\"");
        messages.put("<lang> inválido", (SyntaticError err, String entry) -> {
            if (StringUtils.isEmpty(entry)) {
                return "Erro na linha 1 - encontrado fim de arquivo esperado main types";
            }

            return genericErrorFormatted(err, entry, "Erro na linha {0} - encontrado {1} esperado main types");
        });
        messages.put("<opt_tipos> inválido", genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado main types"));
        messages.put("<tipos> inválido", genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado types"));
        messages.put("<types_dec> inválido", genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado idComposto"));
        messages.put("<types_dec1> inválido", genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado idComposto ]"));
        messages.put("<type_dec> inválido", genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado idComposto"));
        messages.put("<lista_de_campos> inválido", genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado " + SIMPLE_ID_MESSAGE));
        messages.put("<lista_de_campos1> inválido", genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado ] ="));
        messages.put("<campo> inválido", genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado " + SIMPLE_ID_MESSAGE));
        messages.put("<opt_var_decs> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado identificador if while output input :")
        );
        messages.put("<variaveis> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado :")
        );
        messages.put("<variavel_dec> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado identificador")
        );
        messages.put("<variavel_dec1> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado ; ,")
        );
        messages.put("<id_do_tipo_simples> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado " + SIMPLE_ID_MESSAGE)
        );
        messages.put("<var_type> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado " + SIMPLE_ID_MESSAGE)
        );
        messages.put("<opt_arr_size> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado [ ; =")
        );
        messages.put("<id_do_tipo_composto> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado idComposto")
        );
        messages.put("<arr_types> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado idComposto int")
        );
        messages.put("<lista_de_comandos> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado input output while if identificador")
        );
        messages.put("<lista_de_comandos1> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado input output while if identificador ]")
        );
        messages.put("<comando> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado input output while if identificador")
        );
        messages.put("<atribuicao> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado identificador")
        );
        messages.put("<identificador> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado identificador")
        );
        messages.put("<variavel_uso> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado identificador")
        );
        messages.put("<opt_expr> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado símbolo")
        );
        messages.put("<opt_field> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado símbolo")
        );
        messages.put("<entrada_de_dados> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado input")
        );
        messages.put("<lista_de_identificadores> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado indentificador")
        );
        messages.put("<lista_de_identificadores1> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado , )")
        );
        messages.put("<true_statement> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado isTrueDo")
        );
        messages.put("<false_statement> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado isFalseDo")
        );
        messages.put("<saida_de_dados> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado output")
        );
        messages.put("<lista_expressoes> inválido", MessageTranslator::parseExpressionError );
        messages.put("<lista_expressoes1> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado ) ;")
        );
        messages.put("<comando_selecao> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado if")
        );
        messages.put("<optional_false> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado isFalseDo ;")
        );
        messages.put("<statement> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado isFalseDo isTrueDo")
        );
        messages.put("<comando_repeticao> inválido",
                genericFormattedBuilder("Erro na linha {0} - encontrado {1} esperado while")
        );
        messages.put("<expressao> inválido", MessageTranslator::parseExpressionError);
        messages.put("<expressao1> inválido", MessageTranslator::parseExpressionError);
        messages.put("<elemento> inválido", MessageTranslator::parseExpressionError);
        messages.put("<relacional> inválido", MessageTranslator::parseExpressionError);
        messages.put("<relacional1> inválido", MessageTranslator::parseExpressionError);
        messages.put("<operador_relacional> inválido", MessageTranslator::parseExpressionError);
        messages.put("<aritmetica> inválido", MessageTranslator::parseExpressionError);
        messages.put("<aritmetica1> inválido", MessageTranslator::parseExpressionError);
        messages.put("<termo> inválido", MessageTranslator::parseExpressionError);
        messages.put("<termo1> inválido", MessageTranslator::parseExpressionError);
        messages.put("<fator> inválido", MessageTranslator::parseExpressionError);
    }

    public static String translate(SyntaticError err, String entry) {
        return messages.get(err.getMessage()).apply(err, entry);
    }
}
