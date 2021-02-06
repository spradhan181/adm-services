package com.iter.adm.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MiscDAO implements IMiscDAO{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String purgeOTP() {
		String sql = "update user_login_details set otp = " + null ;
		jdbcTemplate.execute(sql);
		return "Success";
	}

}
