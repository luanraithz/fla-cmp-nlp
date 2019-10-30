package main.br.com.joyC.impl.lexic.models;

public enum LexemeType {
	EPSILON(0, ""),
	DOLLAR (1, "SE"),
	t_palavraReservada(2, ""),
	t_input(3, ""),
	t_isFalseDo(4, ""),
	t_isTrueDo(5, ""),
	t_main(6, ""),
	t_output(7, ""),
	t_true(8, ""),
	t_types(9, ""),
	t_while(10, ""),
	t_if(11, ""),
	t_false(12, ""),
	t_idInt(13, ""),
	t_idFloat(14, ""),
	t_idString(15, ""),
	t_idBool(16, ""),
	t_idComposto(17, ""),
	t_int(18, ""),
	t_float(19, ""),
	t_string(20, ""),
	t_TOKEN_21(21, "["),
	t_TOKEN_22(22,"]"),
	t_TOKEN_23(23,"("),
	t_TOKEN_24(24,")"),
	t_TOKEN_25(25,";"),
	t_TOKEN_26(26,":"),
	t_TOKEN_27(27,","),
	t_TOKEN_28(28,"="),
	t_TOKEN_29(29,"."),
	t_TOKEN_30(30,"&&"),
	t_TOKEN_31(31,"||"),
	t_TOKEN_32(32,"!"),
	t_TOKEN_33(33,"=="),
	t_TOKEN_34(34,"!="),
	t_TOKEN_35(35,"<"),
	t_TOKEN_36(36,"<="),
	t_TOKEN_37(37,">"),
	t_TOKEN_38(38,">="),
	t_TOKEN_39(39,"+"),
	t_TOKEN_40(40,"-"),
	t_TOKEN_41(41,"/"),
	t_TOKEN_42(42,"*");
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
		throw new RuntimeException("No lexeme found");
	}
}
