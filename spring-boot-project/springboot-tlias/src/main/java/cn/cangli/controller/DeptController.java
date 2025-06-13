package cn.cangli.controller;

import cn.cangli.pojo.Dept;
import cn.cangli.pojo.Result;
import cn.cangli.service.impl.DeptServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/depts")
@RestController
public class DeptController {
    private static Logger logger = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    DeptServiceImpl deptService;


    //列出
    @GetMapping
    public Result list(){
        logger.info("查询部门列表");
        List<Dept> all = deptService.findAll();
        return Result.success(all);
    }

    //删除指定部门
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        logger.info("根据id删除部门, id={}",id);
        deptService.delete(id);
        return Result.success();
    }

    //修改指定部门
    @PutMapping
    public Result update(@RequestBody Dept dept){
        logger.info("修改部门, dept={}",dept);
        deptService.update(dept);
        return Result.success();
    }

    //新增部门
    @PostMapping
    public Result add(@RequestBody Dept dept){
        logger.info("新增部门, dept={}",dept);
        deptService.add(dept);
        return Result.success();
    }

}
