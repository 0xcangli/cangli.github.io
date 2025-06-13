package cn.cangli.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//员工日志实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpLog {
    Integer id; //id主键
    LocalDateTime operateTime; //操作时间
    String info; //日志信息
}
