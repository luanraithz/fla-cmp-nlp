package main.br.com.joyC.impl.lexical.models;

public enum LexemeType {
	EPSILON(0, "EPSILON"),
	DOLLAR(1, "DOLAR"),
	t_palavraReservada(2, "RESERVED_WORD"),
	t_input(3, "RESERVED_WORD"),
	t_isFalseDo(4, "RESERVED_WORD"),
	t_isTrueDo(5, "RESERVED_WORD"),
	t_main(6, "RESERVED_WORD"),
	t_output(7, "RESERVED_WORD"),
	t_true(8, "RESERVED_WORD"),
	t_types(9, "RESERVED_WORD"),
	t_while(10, "RESERVED_WORD"),
	t_idInt(11, "ID_INT"),
	t_idFloat(12, "ID_FLOAT"),
	t_idString(13, "ID_STRING"),
	t_idBool(14, "ID_BOOL"),
	t_idComposto(15, "ID_COMPOSTO"),
	t_int(16, "INT"),
	t_float(17, "FLOAT"),
	t_string(18, "STRING"),
	t_simboloEspecial(19, "SPECIAL_SYMBOL");

	private Integer value;
	private String desc;

	public Integer getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	LexemeType(Integer i, String desc) {
		this.value = i;
		this.desc = desc;
	}

	static public LexemeType fromInt(Integer i) {
		for (LexemeType lexeme: LexemeType.values()) {
			if (lexeme.getValue().equals(i)) return lexeme;
		}
		throw new RuntimeException("Lexeme not found");
	}
}
