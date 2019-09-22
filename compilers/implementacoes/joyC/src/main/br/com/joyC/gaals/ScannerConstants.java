package main.br.com.joyC.gaals;

public interface ScannerConstants
{

    int[] TOKEN_STATE = {-2,  0, 19, -1,  0, -1, 19, 19, 16, 19, 19, 19, -1, -1, -1, -1, -1,  2, -1, 18, -2, -1, -1, -1, -1, -1, -1, -1, -2, 17, 14, 15, 12, 11, 13,  0, 14, -1, 15, -1, 12, -1, 11, -1, 13, -1, -1, 14, -1, 15, -1, 12, -1, 11, -1, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

    int[] SPECIAL_CASES_INDEXES =
        { 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 };

    String[] SPECIAL_CASES_KEYS =
        {  "input", "isFalseDo", "isTrueDo", "main", "output", "true", "types", "while" };

    int[] SPECIAL_CASES_VALUES =
        {  3, 4, 5, 6, 7, 8, 9, 10 };

    String[] SCANNER_ERROR =
    {
        "Caractere não esperado",
        "",
        "",
        "Erro identificando string",
        "",
        "Erro identificando simboloEspecial",
        "",
        "",
        "",
        "",
        "",
        "",
        "Erro identificando idBool",
        "Erro identificando idComposto",
        "Erro identificando idFloat",
        "Erro identificando idInt",
        "Erro identificando idString",
        "",
        "Erro identificando simboloEspecial",
        "",
        "Erro identificando <ignorar>",
        "Erro identificando float",
        "Erro identificando idBool",
        "Erro identificando idComposto",
        "Erro identificando idFloat",
        "Erro identificando idInt",
        "Erro identificando idString",
        "Erro identificando palavraReservada",
        "Erro identificando <ignorar>",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "Erro identificando idBool",
        "",
        "Erro identificando idComposto",
        "",
        "Erro identificando idFloat",
        "",
        "Erro identificando idInt",
        "",
        "Erro identificando idString",
        "Erro identificando idBool",
        "",
        "Erro identificando idComposto",
        "",
        "Erro identificando idFloat",
        "",
        "Erro identificando idInt",
        "",
        "Erro identificando idString",
        "",
        "Erro identificando idBool",
        "Erro identificando idComposto",
        "Erro identificando idFloat",
        "Erro identificando idInt",
        "Erro identificando idString",
        "Erro identificando idBool",
        "Erro identificando idComposto",
        "Erro identificando idFloat",
        "Erro identificando idInt",
        "Erro identificando idString"
    };

}
