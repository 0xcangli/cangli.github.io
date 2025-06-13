package cn.cangli.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String phone;
    private String job;
    private Double salary;
    private String image;
    private LocalDate entryDate;
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


    //部门
    private String dept;
    //经历
    private List<EmpExpr> children;
}
