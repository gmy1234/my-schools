package excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @version 1.0
 * @Description:
 * @Author 6
 * @Date 2021/10/6 10:28
 */
public class ExcelListener extends AnalysisEventListener<DemoDate> {

    // 一行一行读取的excel的内容
    @Override
    public void invoke(DemoDate demoDate, AnalysisContext analysisContext) {

        System.out.println("((((("+demoDate);
    }

    // 读取表头
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("))))"+headMap);
    }
    //读取完之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
