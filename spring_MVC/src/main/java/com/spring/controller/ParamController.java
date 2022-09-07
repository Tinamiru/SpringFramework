package com.spring.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.command.ParamCommand;

@Controller
public class ParamController {

	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public String param(HttpServletRequest request, HttpServletResponse response) {
		String url = null;

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		System.out.println(id);
		System.out.println(pwd);

		return url;
	}

	@RequestMapping(value = "/param2", method = RequestMethod.GET)
	public String param2(String id, String pwd, int num) {
		String url = null;

		System.out.println(id);
		System.out.println(pwd);
		System.out.println(num);

		return url;
	}
	
	@RequestMapping(value = "/param3", method = RequestMethod.GET)
	public String param3(@RequestParam(value = "id",defaultValue = "mama") String idd, String pwd, int num) {
		String url = null;
		
		System.out.println(idd);
		System.out.println(pwd);
		System.out.println(num);
		
		return url;
	}
	
	@RequestMapping(value = "/param4", method = RequestMethod.GET)
	public String param4(ParamCommand cmd) {
		String url = null;
		
		System.out.println(cmd);
		
		return url;
	}
	
	@RequestMapping(value = "/forward",method = RequestMethod.GET)
	public String forward() {
		String url="page";
		return url;
	}
}
