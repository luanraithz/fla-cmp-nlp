package br.com.joyC.impl.models;

enum LexemType {
	EPSILON(0),
	DOLLAR(1),
	t_palavraReservada(2),
	t_idInt(3),
	t_idFloat(4),
	t_idString(5),
	t_idBool(6),
	t_idComposto(7),
	t_int(8),
	t_float(9),
	t_string(10),
	t_comBloco(11),
	t_com(12);
	
	private Integer value;
	
	public Integer getValue() {
		return this.value;
	}

	private LexemType(Integer i) {
		this.value = i;
	}
}

public class Output {
	Integer line;
	String lexeme;
	LexemType type;
}
