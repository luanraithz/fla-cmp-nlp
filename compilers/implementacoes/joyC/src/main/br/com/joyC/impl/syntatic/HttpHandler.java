package main.br.com.joyC.impl.syntatic;

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

        result.put("result", null);

        return result;
    }
}
