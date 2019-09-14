package br.com.joyC.impl;

import java.io.StringReader;
import java.util.ArrayList;

import br.com.joyC.gaals.LexicalError;
import br.com.joyC.gaals.Lexico;
import br.com.joyC.gaals.Token;

public class Main {

	public static void main(String[] args) {
		System.out.print(lexical("F_teste"));
	}
	
	static private ArrayList<Token> lexical(String content) {
		return lexical(new StringReader(content));
	}
	
	static private ArrayList<Token> lexical(StringReader reader) {
		var lex = new Lexico();
		
		lex.setInput(reader);
		ArrayList<Token> entries = new ArrayList<Token>();
		try {
		    Token t = null;
		    while ( (t = lex.nextToken()) != null )
		    {
		        System.out.println(t.getLexeme() + " " + t.getPosition() + " " + t.getId());
		        entries.add(t);
		    }
		} catch (LexicalError e ) {
		    System.err.println(e.getMessage() + "e" + e.getPosition());
		}

		return entries;
	}

}
