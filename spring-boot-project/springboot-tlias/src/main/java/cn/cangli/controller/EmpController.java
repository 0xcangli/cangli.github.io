package cn.cangli.controller;

import cn.cangli.pojo.Emp;
import cn.cangli.pojo.EmpQuery;
import cn.cangli.pojo.PageResult;
import cn.cangli.pojo.Result;
import cn.cangli.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/emps")
@RestController
public class EmpController {
    private static final Logger log = LoggerFactory.getLogger(EmpController.class);
    @Autowired
    private EmpService empService;


    @GetMapping
    public Result getEmps(EmpQuery empQuery){

//        System.out.println("deptId的值为"+deptId);
//        System.out.println("开始时间: "+startTime+"-结束时间:"+endTime);
        PageResult<Emp> empPageResult = empService.pageList(empQuery);

        return Result.success(empPageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp newEmployee) {
        System.out.println(newEmployee.getChildren());
        empService.save(newEmployee);
        return Result.success();
    }

    @PutMapping
    public Result edit(@RequestBody Emp emp) {
        empService.update(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestBody List<Integer> ids) {
        log.info("接收到参数: {}",ids);
        empService.delete(ids);
        return Result.success();
    }


    @GetMapping("check-phone")
    public Result checkPhone(@RequestParam("phone") String phone) {
        PageResult<Emp> emps =  empService.checkPhone(phone);
        return Result.success(emps);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

}
