package cn.cangli.mapper;

import cn.cangli.pojo.Emp;
import cn.cangli.pojo.EmpQuery;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Many;

import java.util.List;

@Mapper
public interface EmpMapper {


    List<Emp> pageList(EmpQuery empQuery);

//    @Select("select count(*) from emp")
//    Long Count();
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into " +
            "emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "value (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void save(Emp emp);

    //修改员工
    @Update("update emp set username=#{username}, name=#{name}, gender=#{gender}, phone=#{phone}, " +
            "job=#{job}, salary=#{salary}, image=#{image}, entry_date=#{entryDate}, " +
            "dept_id=#{deptId}, update_time=#{updateTime} where id=#{id}")
    int update(Emp emp);

    //删除员工
    void deleteByIds(List<Integer> id);

    //确认手机号
    @Select("select count(*) from emp where phone=#{phone}")
    Integer checkPhone(String phone);

    @Select("select " +
            "id, username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time " +
            "from emp where id=#{id}")
    Emp getByEmpId(Integer id);
}
