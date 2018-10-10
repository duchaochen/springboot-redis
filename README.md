#redis使用
    
    1.引入springboot的redis启动器
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        
    2.在application.yml全局配置文件中配置以下主机地址
         spring.redis.host=192.168.25.144
         
    3.redis中的操作对象
        StringRedisTemplate保存string类型的对象
        代码如下：
            stringRedisTemplate.opsForValue().append("adu-01","张三");
            System.out.println(stringRedisTemplate.opsForValue().get("adu-01"));
            
        自定义RedisTemplate<Object,Employee>操作实体类型对象
            1.在配置类中重新写个保存和获取的序列化方式方法
                @Bean
                public RedisTemplate<Object,Employee> myRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
            
                    RedisTemplate<Object,Employee> redisTemplate = new RedisTemplate<>();
                    redisTemplate.setConnectionFactory(redisConnectionFactory);
                    Jackson2JsonRedisSerializer<Employee> redisSerializer = new Jackson2JsonRedisSerializer<>(Employee.class);
                    redisTemplate.setDefaultSerializer(redisSerializer);
                    return redisTemplate;
                }
                
            2.然后调用方法
            Employee employee = employeeService.getById(1);//获取一个实体对象
            myRedisTemplate.opsForValue().set("emp",employee);
            Employee emp = myRedisTemplate.opsForValue().get("emp");
            System.out.println(emp);
        