package cn.cangli.mapper;

import cn.cangli.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("select id, name, create_time, update_time from dept order by create_time")
    List<Dept> findAll();

    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    @Update("update dept set name=#{name},update_time=#{updateTime}  where id=#{id}")
    void update(Dept dept);

    //INSERT INTO 表名 (列1, 列2, ..., 列N)
    //VALUES (值1, 值2, ..., 值N);
    @Insert("insert into dept(id,name,create_time,update_time) value (null, #{name}, #{createTime}, #{updateTime})")
    void add(Dept dept);
}
