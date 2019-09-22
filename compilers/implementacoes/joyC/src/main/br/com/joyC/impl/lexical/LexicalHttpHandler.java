package main.br.com.joyC.impl.lexical;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LexicalHttpHandler {

    @RequestMapping(name = "Lexical Parser", method = RequestMethod.POST, value="/lexical", consumes = { "application/json" }, produces = { "application/json" })
    @CrossOrigin(origins = { "http://cmp.luan.raithz.com", "http://localhost:3001" })
    public Map<String, Object> parse(@RequestBody Map<String, String> payload) {
        var content = payload.get("content");
        var result = new HashMap<String, Object>();
        try {
            result.put("result", LexicalParser.parse(content));
        } catch (LexicalContentError err) {
            var error = new HashMap<String, Object>();
            error.put("line", err.getLine());
            error.put("position", err.getPosition());
            error.put("lexeme", err.getLexeme());
            error.put("message", err.getMessage());
            result.put("error", error);
        }
        return result;
    }
}
