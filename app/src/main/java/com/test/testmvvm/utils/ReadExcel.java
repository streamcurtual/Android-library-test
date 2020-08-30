package com.test.testmvvm.utils;

import android.util.Log;
import android.widget.Toast;

import com.test.testmvvm.App;
import com.test.testmvvm.beans.Activities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 读取Excel
 */
public class ReadExcel {

    private static String TAG = ReadExcel.class.getSimpleName();

    public static ArrayList<Activities> readExcel(String filePath) {
        // 字符列表
        ArrayList<Activities> list = new ArrayList<>();
        // 文件路径

        // 输入流
        InputStream is = null;
        // Excel工作簿
        Workbook workbook = null;

        Log.i(TAG, "readExcel: " + filePath);

        try {
            // 加载Excel文件
            is = new FileInputStream(filePath);
            // 获取workbook
            workbook = Workbook.getWorkbook(is);
        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(App.getContext(),"读取表格失败,请确保该路径下存在文件", Toast.LENGTH_SHORT).show();
            return null;
        }

        // 获取sheet, 如果你的workbook里有多个sheet可以利用workbook.getSheets()方法来得到所有的
        Sheet sheet = Objects.requireNonNull(workbook).getSheet(0);// 这里只取得第一个sheet的值，默认从0开始
        System.out.println(sheet.getColumns());// 查看sheet的列
        System.out.println(sheet.getRows());// 查看sheet的行

        Cell cell = null;// 单个单元格
        // 开始循环，取得cell里的内容，按具体类型来取
        // 这里只取String类型
        for (int j = 0; j < sheet.getRows(); j++) {
            StringBuilder sb = new StringBuilder();
            Activities activities = new Activities();
            for (int i = 0; i < sheet.getColumns(); i++) {
                // 列,行
                cell = sheet.getCell(i, j);
                sb.append(cell.getContents());// 获取单元格内容
                sb.append(",");// 将单元格的每行内容用逗号隔开
                switch (i) {
                    case 0:
                        activities.setName(cell.getContents());
                        continue;
                    case 1:
                        activities.setTime(cell.getContents());
                        continue;
                    case 2:
                        activities.setIntro(cell.getContents());
                        continue;
                    case 3:
                        activities.setImg(cell.getContents());
                }
            }
            list.add(activities);//将每行的字符串用一个String类型的集合保存。
        }

        workbook.close();// 关闭工作簿
        return list;
    }
}
