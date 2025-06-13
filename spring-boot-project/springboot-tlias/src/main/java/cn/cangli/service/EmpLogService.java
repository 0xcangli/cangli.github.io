package cn.cangli.service;

import cn.cangli.mapper.EmpLogMapper;
import cn.cangli.pojo.EmpLog;
import cn.cangli.service.impl.EmpLogServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogService implements EmpLogServiceImpl {
    @Autowired
    private EmpLogMapper empLogMapper;

    @Override                    //不管有没有事物都新建一个新事物
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = {Exception.class})
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
