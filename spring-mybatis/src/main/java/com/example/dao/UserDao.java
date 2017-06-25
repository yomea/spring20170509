package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.entity.Person;
import com.example.entity.User;

public interface UserDao {

	@Select("select id,username from t_user")
	@ResultType(User.class)
	List<User> findAll();
	
	@Select("select id, name, age from t_person")
	@Results(value={
			@Result(property="id", column="id", javaType=Integer.class),
			@Result(property="username", column="name", javaType=String.class),
			@Result(property="age", column="age", javaType=Integer.class)
			})
	List<Person> findPerson();
	
	@Insert("insert into t_person values(null, #{username}, #{age})")
	void savePerson(Person person);

}
