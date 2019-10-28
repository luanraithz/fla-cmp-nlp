package main.br.com.joyC.gaals;

public interface ScannerConstants
{

    int[] TOKEN_STATE = {-2,  0, 32, -1,  0, -1, 23, 24, 42, 39, 27, 40, 29, 41, 18, 26, 25, 35, 28, 37, -1, -1, -1, -1, -1, 21, 22,  2, -1, 34, 20, 30, -2, -1, 36, 33, 38, -1, -1, -1, -1, -1, -1, 31, -2, 19, 16, 17, 14, 13, 15,  0, 16, -1, 17, -1, 14, -1, 13, -1, 15, -1, -1, 16, -1, 17, -1, 14, -1, 13, -1, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

    int[] SPECIAL_CASES_INDEXES =
        { 0, 0, 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };

    String[] SPECIAL_CASES_KEYS =
        {  "false", "if", "input", "isFalseDo", "isTrueDo", "main", "output", "true", "types", "while" };

    int[] SPECIAL_CASES_VALUES =
        {  12, 11, 3, 4, 5, 6, 7, 8, 9, 10 };

    String[] SCANNER_ERROR =
    {
        "Caractere não esperado",
        "",
        "",
        "Erro identificando string",
        "",
        "Erro identificando \"&&\"",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
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
        "",
        "",
        "Erro identificando \"||\"",
        "",
        "",
        "",
        "Erro identificando <ignorar>",
        "Erro identificando float",
        "",
        "",
        "",
        "Erro identificando idBool",
        "Erro identificando idComposto",
        "Erro identificando idFloat",
        "Erro identificando idInt",
        "Erro identificando idString",
        "Erro identificando palavraReservada",
        "",
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
