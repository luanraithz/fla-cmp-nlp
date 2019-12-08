package main.br.com.joyC.impl.lexic.models;

import java.util.ArrayList;
import java.util.Arrays;

public enum LexemeType {
	EPSILON(0, ""),
	DOLLAR (1, "SE"),
	t_palavraReservada(2, "", true),
	t_input(3, "", true),
	t_isFalseDo(4, "", true),
	t_isTrueDo(5, "", true),
	t_main(6, "", true),
	t_output(7, "", true),
	t_true(8, "", true),
	t_types(9, "", true),
	t_while(10, "", true),
	t_if(11, "", true),
	t_false(12, "", true),
	t_idInt(13, "", true),
	t_idFloat(14, "", true),
	t_idString(15, "", true),
	t_idBool(16, "", true),
	t_idComposto(17, "", true),
	t_int(18, "", true),
	t_float(19, "", true),
	t_string(20, "", true),
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
	public Boolean isWord;

	public Integer getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	LexemeType(Integer i, String desc) {
		this(i, desc, false);
	}

	LexemeType(Integer i, String desc, Boolean isWord) {
		this.value = i;
		this.desc = desc;
		this.isWord = isWord;
	}

	static public Boolean isSpecialSymbol(String s) {
		return !s.matches("[0-9a-zA-z_]");
	}

	static public LexemeType fromInt(Integer i) {
		for (LexemeType lexeme: LexemeType.values()) {
			if (lexeme.getValue().equals(i)) return lexeme;
		}
		throw new RuntimeException("No lexeme found");
	}
}
