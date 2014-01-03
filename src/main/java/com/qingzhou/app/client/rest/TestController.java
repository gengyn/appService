package com.qingzhou.app.client.rest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.qingzhou.app.core.controller.BaseController;

@Controller
@RequestMapping("/service")
public class TestController extends BaseController {

	@RequestMapping(value = "/test/{signature}/{timestamp}/{nonce}/{echostr}", method = RequestMethod.GET)
	public @ResponseBody
	String welcome(@PathVariable String signature,
			@PathVariable String timestamp, @PathVariable String nonce,
			@PathVariable String echostr) {

		return echostr;
	}

}
