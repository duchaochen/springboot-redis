package com.adu.springboot.mapper;

import com.adu.springboot.bean.Employee;
import org.apache.ibatis.annotations.*;

public interface EmployeeMapper {

    @Select("SELECT * FROM `employee` Where id=#{id}")
    Employee getById(Integer id);

    @Update("UPDATE `employee` SET `lastName` = #{lastName},`email` = #{email},`gender` = #{gender}," +
            "`d_id` = #{dId} WHERE `id` = #{id} ")
    void updateEmp(Employee employee);

    @Delete("DELETE FROM `employee` WHERE `id` = #{id}")
    void deleteEmp(Integer id);

    @Insert("INSERT INTO `employee` (`lastName`,`email`,`gender`,`d_id`)VALUES(#{lastName},#{email},#{gender},#{dId)")
    void insertEmp(Employee employee);
}
