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
	t_if(9, "RESERVED_WORD"),
	t_false(10, "RESERVED_WORD"),
	t_types(11, "RESERVED_WORD"),
	t_while(12, "RESERVED_WORD"),
	t_idInt(13, "ID_INT"),
	t_idFloat(14, "ID_FLOAT"),
	t_idString(15, "ID_STRING"),
	t_idBool(16, "ID_BOOL"),
	t_idComposto(17, "ID_COMPOSTO"),
	t_int(18, "INT"),
	t_float(19, "FLOAT"),
	t_string(20, "STRING"),
	t_simboloEspecial(21, "SPECIAL_SYMBOL");

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
