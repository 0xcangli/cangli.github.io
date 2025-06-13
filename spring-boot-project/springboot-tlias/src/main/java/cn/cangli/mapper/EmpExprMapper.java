package cn.cangli.mapper;

import cn.cangli.pojo.EmpExpr;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    void insertBatch(List<EmpExpr> empExprs);

    @Insert("insert into emp_expr(emp_id,begin,end,company,job) values(#{empId},#{begin},#{end},#{company},#{job})")
    void insert(EmpExpr empExpr);

    @Delete("delete from emp_expr where emp_id = #{empId}")
    void deleteByEmpId(Long empId);

    void deleteByEmpIds(List<Integer> empIds);
}
