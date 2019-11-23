package main.br.com.joyC.impl.semantic;

import main.br.com.joyC.gaals.LexicalError;
import main.br.com.joyC.gaals.SemanticError;
import main.br.com.joyC.gaals.SyntaticError;
import main.br.com.joyC.impl.syntactic.MessageTranslator;
import main.br.com.joyC.impl.utils.LexicalErrorParser;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SemanticHttpHandler {

    @RequestMapping(name = "Semantic Parser", method = RequestMethod.POST, value="/semantic", consumes = { "application/json" }, produces = { "application/json" })
    @CrossOrigin(origins = { "http://semantic.cmp.luan.raithz.com","http://syntactic.cmp.luan.raithz.com", "http://cmp.luan.raithz.com", "http://localhost:3001" })
    public Map<String, Object> parse(@RequestBody Map<String, String> payload) {
        var content = payload.get("content");
        var result = new HashMap<String, Object>();
        var error = new HashMap<String, Object>();

        try {
            var res = Parser.parse(content);
            result.put("result", res);
            result.put("type", "SYNTACTIC");
        } catch (SyntaticError syntaticError) {
            error.put("message", MessageTranslator.translate(syntaticError, content));
            error.put("type", "SYNTACTIC");
        } catch (SemanticError semanticError) {
            error.put("type", "SEMANTIC");
            error.put("message", "NOT IMPLEMENTED YET");
            result.put("error", error);
            semanticError.printStackTrace();
        } catch (LexicalError lexicalError) {
            var err = LexicalErrorParser.toException(content, lexicalError);
            result.put("line", err.getLine());
            error.put("position", err.getPosition());
            error.put("lexeme", err.getLexeme());
            error.put("message", err.getMessage());
            error.put("type", "LEXIC");
        }
        if (!error.isEmpty()) {
            result.put("error", error);
        }

        return result;
    }
}
