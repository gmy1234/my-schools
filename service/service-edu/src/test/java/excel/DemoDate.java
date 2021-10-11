package excel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @version 1.0
 * @Description:
 * @Author 6
 * @Date 2021/10/6 10:10
 */
public class DemoDate {

    //设置表头的内容
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生的姓名", index = 1)
    private String name;



    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DemoDate{" +
                "sno=" + sno +
                ", name='" + name + '\'' +
                '}';
    }
}
