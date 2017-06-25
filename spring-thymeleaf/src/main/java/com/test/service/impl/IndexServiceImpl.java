package com.test.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.entity.User;
import com.test.service.IndexService;

@Service
public class IndexServiceImpl extends JdbcTemplate implements IndexService {
	
	@Autowired
	public IndexServiceImpl(DataSource dataSource) {
		
		super(dataSource);
		
	}
	
	@Transactional(transactionManager="txManager")
	public List<User> findAll() {
		String sql = "select id,username from t_user";
		return this.query(sql, new Object[]{}, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				User user = new User(rs.getInt("id"), rs.getString("username"));
				System.out.println(user);
				return user;
			}
			
			
			
		});
	}

}
