package org.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class KillController {

	@RequestMapping(value = "/kill", method = RequestMethod.GET)
	public void kill() {
		System.exit(-1);
	}	
}
