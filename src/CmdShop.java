import java.io.InputStream;
import java.util.Scanner;

public class CmdShop {
    public static void main(String[] args) throws ClassNotFoundException {
        //File file = new File("D:\\IntelliJ IDEA 2019.2.4\\CmdShop\\src\\users.xlsx");
        InputStream in = Class.forName("CmdShop").getResourceAsStream("/users.xlsx");
        InputStream inPro = Class.forName("CmdShop").getResourceAsStream("/products.xlsx");
        ReadusersExcel readExcel = new ReadusersExcel();
        User[] users = readExcel.readExcel(in);
        Scanner sc = new Scanner(System.in);
        boolean bool = true;
        while(bool) {
            System.out.println("请输入用户名");
            String username = sc.next();
            System.out.println("请输入密码");
            String password = sc.next();

            for (int i = 0; i < users.length; i++) {
                System.out.println(users[i].getUsername());
                System.out.println(users[i].getPassword());
                if (users[i].getUsername().equals(username) && users[i].getPassword().equals(password)) {
                    System.out.println("登录成功");
                    bool = false;
                    //打印商品信息
                    Product[] products = null;
                    ReadproductsExcel readproductsExcel = new ReadproductsExcel();
                    products = readproductsExcel.readAllProducts(inPro);
                    if(products==null){
                        System.out.println("无商品");
                    }else {
                        for (Product product : products) {
                            System.out.print(product.getId());
                            System.out.print("\t" + product.getName());
                            System.out.print("\t" + product.getPrice());
                            System.out.println("\t" + product.getDesc());
                        }
                        Product[] products1 = new Product[5];
                        int con = 0;
                        boolean flag = true;
                        while (flag) {
                            System.out.println("请输入商品id添加商品至购物车; 回复0结束添加");
                            String id = sc.next();
                            if (id.equals("0")){
                                System.out.println("购物结束");
                                break;
                            }
                            inPro = null;
                            inPro = Class.forName("CmdShop").getResourceAsStream("/products.xlsx");
                            Product product = readproductsExcel.readProductsById(id, inPro);
                            if (product != null) {
                                products1[con] = product;
                                System.out.println("商品" + product.getName() + "已添加");
                                System.out.println("购物车已经有:");
                                for (int j=0; j<=con; j++){
                                    System.out.println(products1[j].getName());
                                }
                                con++;
                            }
                        }
                    }
                    break;
                }else {
                    System.out.println("登录失败");
                }
            }
        }
    }
}
