import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class AboutOrder {
    public AboutOrder(){
    }
    /*public void POICreateOrderExcel() throws IOException {
        String[] title = {"用户","商品","购买数量","商品总价","实付款","下单时间"};
        //创建HSSF工作薄,文档对象HSSFWorkbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个Sheet页，HSSFSheet 表单对象,需要几个sheet页自己看着修改即可
        HSSFSheet sheet = workbook.createSheet("sheet");
        //创建第一行（一般是表头）
        HSSFRow row0 = sheet.createRow(0);
        //创建列，HSSFCell列
        HSSFCell cell = null;
        //设置表头，循环上面的数组，希望朋友不要忘记数组是length,集合是size();
        for (int i = 0; i <title.length ; i++) {
            cell=row0.createCell(i);
            cell.setCellValue(title[i]);
        }
        //保存到本地
        File file = new File("E:/");
        FileOutputStream outputStream = new FileOutputStream(file);
        //将Excel写入输出流中
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }*/

    public void AddOrderToExcle(Order order,int n) throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream("E:\\Order.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row =sheet.createRow(n);//新建第n行
        XSSFCell cell = row.createCell(0);//新建一个单元格
        for(int i=0; i<6; i++){//6是订单表格的列数
            if(i==0){
                //System.out.println("name="+order.getUser().getUsername());
                cell.setCellValue(order.getUser().getUsername());
            }
            if(i==1){
                cell=row.createCell(1);
                cell.setCellValue(order.getProduct().getName());
            }
            if(i==2){
                cell=row.createCell(2);
                cell.setCellValue(order.getAmmount());
            }
            if(i==3){
                cell=row.createCell(3);
                cell.setCellValue(order.getTotalCost());
            }
            if(i==4){
                cell=row.createCell(4);
                cell.setCellValue(order.getReceipts());
            }
            if(i==5){
                cell=row.createCell(5);
                cell.setCellValue(order.getOrderDate());
            }
        }
        //保存
        FileOutputStream outputStream = new FileOutputStream("E:\\Order.xlsx");
        //将Excel写入输出流中
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        System.out.println("订单上传成功");
    }
    public void clearOrder(){
            try{

                FileInputStream is = new FileInputStream("E:\\Order.xlsx");

                XSSFWorkbook workbook = new XSSFWorkbook(is);

                XSSFSheet sheet = workbook.getSheetAt(0);
                int n = 1;
                XSSFRow row = sheet.getRow(n);
                while(row != null){
                    sheet.removeRow(row);
                    FileOutputStream os = new FileOutputStream("E:\\Order.xlsx");
                    workbook.write(os);
                    is.close();
                    os.close();
                    row = sheet.getRow(++n);
                    System.out.println("n="+n);
                }
                System.out.println("置空成功！");

            } catch(Exception e) {

                e.printStackTrace();

            }
    }
}