package br.com.joyC.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.joyC.impl.models.ResponsePayload;

@RestController
public class HttpHandler {

	@RequestMapping(method = RequestMethod.GET, path = "/greeting")
	public String getLexical() {
		return null;
	}

}
