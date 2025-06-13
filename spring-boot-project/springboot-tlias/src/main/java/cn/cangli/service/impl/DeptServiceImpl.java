package cn.cangli.service.impl;

import cn.cangli.pojo.Dept;

import java.util.List;

public interface DeptServiceImpl {
    List<Dept> findAll();

    void delete(Integer id);

    void add(Dept dept);

    void update(Dept dept);
}
