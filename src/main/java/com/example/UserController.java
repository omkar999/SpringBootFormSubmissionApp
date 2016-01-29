package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@RequestMapping("addUser")
	public String addUser(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname) {
		// user table will be created. Remove this if the table  already
		// exists in the mysql database
		jdbcTemplate.execute("create table user (firstname varchar(20), lastname varchar(20))");

		// inserts firstname and lastname into database table "user"
		jdbcTemplate.update("insert into  user values (? , ?)", firstname, lastname);

		return "forward:/success.html";
	}

}
