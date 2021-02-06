package com.iter.adm;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class Application {
	@Autowired
	EmailConfig emailConfig;

	/**
	 * This is main method of adm services
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public JdbcTemplate getJDBCTemplate(DataSource datasource) {
		return new JdbcTemplate(datasource);
	}

	@Bean
	public JavaMailSenderImpl getJavaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(emailConfig.getHost());
		javaMailSender.setPort(emailConfig.getPort());
		javaMailSender.setUsername(emailConfig.getUserName());
		javaMailSender.setPassword(emailConfig.getPassword());
		return javaMailSender;
	}

}
