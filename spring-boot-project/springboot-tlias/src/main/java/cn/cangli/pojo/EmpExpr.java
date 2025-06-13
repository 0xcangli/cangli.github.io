package cn.cangli.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpExpr {
    private String id;
    private LocalDate begin;
    private LocalDate end;
    private String company;
    private String job;

    private Long empId;
}
