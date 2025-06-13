package cn.cangli.service;

import cn.cangli.mapper.EmpExprMapper;
import cn.cangli.mapper.EmpMapper;
import cn.cangli.pojo.*;
import cn.cangli.service.impl.EmpServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpService implements EmpServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(EmpService.class);

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    @Override 
    public PageResult<Emp> pageList(EmpQuery empQuery) {
        //1.设置分页参数
        PageHelper.startPage(empQuery.getPage(),empQuery.getPageSize());
        //2.调用Mapper接口方法
        List<Emp> emps = empMapper.pageList(empQuery);
        Page<Emp> p = (Page<Emp>) emps;
        //3.解析并封装结果
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }
                            //rollbackFor={异常数组}
    @Override               //默认运行时异常才会回滚
    @Transactional(rollbackFor = {Exception.class}) //开启事物
    public void save(Emp emp) {
        try {
            log.info("开始保存员工信息: {}", emp);
            //1.保存用户信息
            emp.setEntryDate(LocalDate.now());
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.save(emp);
            log.info("员工基本信息保存成功，生成的ID: {}", emp.getId());

            //2.保存用户经历
            List<EmpExpr> children = emp.getChildren();
            log.info("员工经历信息: {}", children);

            if(children != null && !children.isEmpty()){
                log.info("开始保存员工经历信息，经历数量: {}", children.size());
                for (EmpExpr child : children) {
                    child.setEmpId(emp.getId());
                    log.info("设置经历关联的员工ID: {}, 经历信息: {}", child.getEmpId(), child);
                }
                log.info("调用 insertBatch 方法，参数: {}", children);
                empExprMapper.insertBatch(children);
                log.info("员工经历信息保存成功");

            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
            empLogService.insertLog(empLog);
        }

    }

    @Override
    public void delete(List<Integer> ids) {
        //删除员工
        empMapper.deleteByIds(ids);

        //删除经历
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public PageResult<Emp> checkPhone(String phone) {
        Long i = Long.valueOf(empMapper.checkPhone(phone));
        return new PageResult<>(i,null);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update(Emp emp) {
        try {
            log.info("开始更新员工信息: {}", emp);
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.update(emp);
            log.info("员工信息更新成功");

            // Update employee experience if provided
            List<EmpExpr> children = emp.getChildren();
            if (children != null && !children.isEmpty()) {
                // Delete existing experience
                empExprMapper.deleteByEmpId(emp.getId());
                // Insert new experience
                for (EmpExpr child : children) {
                    child.setEmpId(emp.getId());
                }
                empExprMapper.insertBatch(children);
                log.info("员工经历信息更新成功");
            }
        } finally {
            // Record operation log
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "更新员工" + emp);
            empLogService.insertLog(empLog);
        }
    }

    @Override
    public Emp getByEmpId(Integer id) {
        return empMapper.getByEmpId(id);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getByEmpId(id);
    }
}
