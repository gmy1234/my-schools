package excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Description:
 * @Author 6
 * @Date 2021/10/6 10:13
 */
public class TestExcel {

    public static void main(String[] args) {

//        // 实现Excel 的写操作  sheng 生成一个 Excel 文件
//
//        //1.写入地址和名次
//        String filename = "T:\\a.xlsx";
//
//        //2.调用EasyExcel 的写方法
//        EasyExcel.write(filename, DemoDate.class).sheet("学生的列表").doWrite(getData());
        String filename = "T:\\a.xlsx";
        EasyExcel.read(filename, DemoDate.class, new ExcelListener()).sheet().doRead();

    }


    private static List<DemoDate> getData(){

        final ArrayList<DemoDate> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoDate data = new DemoDate();
            data.setName("gmy"+i);
            data.setSno(i);
            list.add(data);

        }
        return list;
    }



}
