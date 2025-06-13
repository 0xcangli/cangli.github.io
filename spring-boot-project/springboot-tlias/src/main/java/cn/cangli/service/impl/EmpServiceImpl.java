package cn.cangli.service.impl;

import cn.cangli.pojo.Emp;
import cn.cangli.pojo.EmpQuery;
import cn.cangli.pojo.PageResult;

import java.util.List;

public interface EmpServiceImpl {
    PageResult<Emp> pageList(EmpQuery empQuery);

    void save(Emp emp);

    void delete(List<Integer> id);

    PageResult<Emp> checkPhone(String phone);

    void update(Emp emp);

    Emp getByEmpId(Integer id);

    Emp getById(Integer id);
}
