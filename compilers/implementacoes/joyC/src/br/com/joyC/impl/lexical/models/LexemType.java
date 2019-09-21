package br.com.joyC.impl.lexical.models;

import org.apache.tomcat.jni.Error;

import java.util.ArrayList;
import java.util.Arrays;

public enum LexemType {
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
	t_com(12, "COMMENT_LINE");

	private Integer value;
	private String desc;

	public Integer getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

	LexemType(Integer i, String desc) {
		this.value = i;
		this.desc = desc;
	}

	static public LexemType fromInt(Integer i) {
		for (LexemType lexem: LexemType.values()) {
			if (lexem.getValue().equals(i)) return lexem;
		}
		throw new RuntimeException("Lexem not found");
	}
}
