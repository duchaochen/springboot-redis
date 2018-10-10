package com.adu.springboot;

import com.adu.springboot.bean.Employee;
import com.adu.springboot.service.EmployeeService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCacheApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 操作字符串的
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 操作对象的
	 */
	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private RedisTemplate<Object,Employee> myRedisTemplate;
	@Test
	public void test01() throws JsonProcessingException {
//		stringRedisTemplate.opsForValue().append("adu-01","张三");
//		System.out.println(stringRedisTemplate.opsForValue().get("adu-01"));
		Employee employee = employeeService.getById(1);
		myRedisTemplate.opsForValue().set("emp",employee);
		Employee emp = myRedisTemplate.opsForValue().get("emp");
		System.out.println(emp);
	}

	@Test
	public void test02() throws IOException {
		Employee employee = employeeService.getById(1);
		Jackson2JsonEncoder jsonEncoder = new  Jackson2JsonEncoder();
		ObjectMapper objectMapper = jsonEncoder.getObjectMapper();

		//序列化的时候序列对象的所有属性
		objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
		//取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


		String s = objectMapper.writeValueAsString(employee);
		stringRedisTemplate.opsForValue().set("employee:id:"+employee.getId(),s);

		String employeeJosn = stringRedisTemplate.opsForValue().get("employee:id:" + employee.getId());


		Jackson2JsonDecoder decoder = new  Jackson2JsonDecoder();
		ObjectMapper objectMapper1 = decoder.getObjectMapper();
		Employee employee1 = objectMapper1.readValue(employeeJosn, Employee.class);
		System.out.println(employee1);
	}

	@Test
	public void contextLoads() {
		System.out.println(employeeService);
		Employee employee = employeeService.getById(1);
		System.out.println(employee);
	}
}
