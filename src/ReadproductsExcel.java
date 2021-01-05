import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class ReadproductsExcel {
    /*
    readExcel是什么方法？成员方法
     */
    public Product[] readAllProducts(InputStream in) {
        Product[] products = null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);
            products = new Product[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                Product product = new Product();
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                            XSSFCell cell = row.getCell(k);
                            if (cell == null)
                                continue;
                            if (k == 0) {
                                product.setId(this.getValue(cell));
                            } else if (k == 1) {
                                product.setName(this.getValue(cell));
                            } else if (k == 2) {
                                product.setPrice(Float.valueOf(this.getValue(cell)));
                            } else if (k == 3) {
                                product.setDesc(this.getValue(cell));
                    }
                    products[j-1]=product;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product readProductsById(String id,InputStream in) {
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                Product product = new Product();
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setId(this.getValue(cell));
                    } else if (k == 1) {
                        product.setName(this.getValue(cell));
                    } else if (k == 2) {
                        product.setPrice(Float.valueOf(this.getValue(cell)));
                    } else if (k == 3) {
                        product.setDesc(this.getValue(cell));
                    }
                }
                if(product.getId().equals(id)){
                    return product;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public Object[] readProductsMoney(InputStream in,Product[] products) {
        Object[] orderProducts = new Product[products.length+1];
        int i = 0;
        float m = 0;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);
            while(i < products.length) {//注意最后一个先不加所以用products.length
                for (int j = 1; j <= xs.getLastRowNum(); j++) {//行
                    XSSFRow row = xs.getRow(j);
                    Product product = new Product();
                    XSSFCell cell = row.getCell(2);//第三列
                    if (this.getValue(cell) != products[i].getName()) continue;//比对
                    product.setId(this.getValue(cell));
                    product.setName(this.getValue(cell));
                    product.setPrice(Float.valueOf(this.getValue(cell)));
                    product.setDesc(this.getValue(cell));
                    m += product.getPrice();//加钱
                    orderProducts[i] = product;//加入数组
                    i++;
                }
            }//循环结束要把“钱”加入数组
            orderProducts[i] = m;//要注意最后一个是“float”
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderProducts;
    }*/

    private String getValue(XSSFCell cell) {
        String value;
        CellType type = cell.getCellType();

        switch (type) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case NUMERIC:
                DecimalFormat df = new DecimalFormat("#");
                value = df.format(cell.getNumericCellValue());
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case ERROR:
                value = "非法字符";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}