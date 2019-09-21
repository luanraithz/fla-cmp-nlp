package br.com.joyC.impl.lexical;

import br.com.joyC.impl.lexical.models.Output;
import br.com.joyC.impl.lexical.models.ResponseToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class LexicalHttpHandler {

    @RequestMapping(name = "Lexical Parser", method = RequestMethod.POST, value="/lexical", consumes = { "application/json" }, produces = { "application/json" })
    public ArrayList<Output> parse(@RequestBody Map<String, String> payload) {
        var content = payload.get("content");
        return LexicalParser.parse(content);
    }
}
