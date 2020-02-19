package com.indrasol.essbase.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.indrasol.essbase.pojo.Credentials;
import com.indrasol.essbase.service.LoginService;

@RestController
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "login";
	}
	
	@RequestMapping(value="essconnect", method=RequestMethod.POST)
public String loginUser(@ModelAttribute("credentials") Credentials credentials, ModelMap modelMap) throws IOException {
	System.out.println("credentials :" + credentials.getUrl()+":"+credentials.getUsername()+":"+credentials.getPassword() +":"+credentials.getOlapServer());
		loginService.connectEssBase(credentials);
	//	modelMap.addAllAttributes(List<IEssDimension> dimensionList, credentials.getDimensionList());
  return "login";
}
	
	/*
	 * @RequestMapping(value="essconnect1", method=RequestMethod.POST) public String
	 * loginUser(@RequestParam("userName")String
	 * name, @RequestParam("password")String password , @RequestParam("url")String
	 * url) {
	 * 
	 * loginService.connectEssBase(name,password,url); return "login"; }
	 */
	
	
}
