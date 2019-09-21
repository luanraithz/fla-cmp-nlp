package main.br.com.joyC.impl.lexical.models;

public enum LexemeType {
	EPSILON(0, "EPSILON"),
	DOLLAR(1, "DOLAR"),
	t_palavraReservada(2, "RESERVED_WORD"),
	t_idInt(3, "ID_INT"),
	t_idFloat(4, "ID_FLOAT"),
	t_idString(5, "ID_STRING"),
	t_idBool(6, "ID_BOOL"),
	t_idComposto(7, "ID_COMPOSTO"),
	t_int(8, "INT"),
	t_float(9, "FLOAT"),
	t_string(10, "STRING"),
	t_comBloco(11, "COMMENT_BLOCK"),
	t_com(12, "COMMENT_LINE"),
	t_simboloEspecial(13, "SPECIAL_SYMBOL");

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
