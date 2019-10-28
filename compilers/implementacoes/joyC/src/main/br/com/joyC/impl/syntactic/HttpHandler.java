package main.br.com.joyC.impl.syntactic;

import main.br.com.joyC.gaals.LexicalError;
import main.br.com.joyC.gaals.SemanticError;
import main.br.com.joyC.gaals.SyntaticError;
import main.br.com.joyC.impl.utils.LexicalErrorParser;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HttpHandler {

    @RequestMapping(name = "Syntatic Parser", method = RequestMethod.POST, value="/syntatic", consumes = { "application/json" }, produces = { "application/json" })
    @CrossOrigin(origins = { "http://cmp.luan.raithz.com", "http://localhost:3001" })
    public Map<String, Object> parse(@RequestBody Map<String, String> payload) {
        var content = payload.get("content");
        var result = new HashMap<String, Object>();

        try {
            var res = Parser.parse(content);
            result.put("result", res);
        } catch (SyntaticError syntaticError) {
            var err = new HashMap<String, Object>();
            err.put("message", MessageTranslator.translate(syntaticError, content));
            result.put("error", err);
        } catch (SemanticError semanticError) {
            var error = new HashMap<String, Object>();
            error.put("type", "SEMANTIC");
            error.put("message", "NOT IMPLEMENTED YET");
            result.put("error", error);
            semanticError.printStackTrace();
        } catch (LexicalError lexicalError) {
            var err = LexicalErrorParser.toException(content, lexicalError);
            var error = new HashMap<String, Object>();
            result.put("line", err.getLine());
            error.put("position", err.getPosition());
            error.put("lexeme", err.getLexeme());
            error.put("message", err.getMessage());
            error.put("type", "LEXIC");
            result.put("error", error);
        }

        return result;
    }
}
