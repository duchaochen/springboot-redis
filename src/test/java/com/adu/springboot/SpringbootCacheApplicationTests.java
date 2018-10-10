package com.adu.springboot;

import com.adu.springboot.bean.Employee;
import com.adu.springboot.mapper.EmployeeMapper;
import com.adu.springboot.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
	public void test01() {
//		stringRedisTemplate.opsForValue().append("adu-01","张三");
//		System.out.println(stringRedisTemplate.opsForValue().get("adu-01"));
		Employee employee = employeeService.getById(1);
		myRedisTemplate.opsForValue().set("emp",employee);
		Employee emp = myRedisTemplate.opsForValue().get("emp");
		System.out.println(emp);
	}

	@Test
	public void contextLoads() {
		System.out.println(employeeService);
		Employee employee = employeeService.getById(1);
		System.out.println(employee);
	}

}
