import java.io.InputStream;
import java.util.Scanner;

public class CmdShop {
    public static void main(String[] args) throws ClassNotFoundException {
        //File file = new File("D:\\IntelliJ IDEA 2019.2.4\\CmdShop\\src\\users.xlsx");
        InputStream in = Class.forName("CmdShop").getResourceAsStream("/users.xlsx");
        InputStream inPro = Class.forName("CmdShop").getResourceAsStream("/products.xlsx");
        ReadusersExcel readExcel = new ReadusersExcel();
        ReadproductsExcel readproductsExcel = new ReadproductsExcel();
        Product[] products = readproductsExcel.readAllProducts(inPro);
        User[] users = readExcel.readExcel(in);
        Order order = new Order();
        Scanner sc = new Scanner(System.in);
        boolean bool = true;
        int con = 0;
        while (bool) {
            System.out.println("请输入用户名");
            String username = sc.next();
            System.out.println("请输入密码");
            String password = sc.next();

            for (int i = 0; i < users.length; i++) {
                //System.out.println(users[i].getUsername());
                //System.out.println(users[i].getPassword());
                if (users[i].getUsername().equals(username) && users[i].getPassword().equals(password)) {
                    System.out.println("登录成功");
                    bool = false;
                    Product[] carts = new Product[5];
                    while (true) {
                        System.out.println("购物请回复 1 ");
                        System.out.println("查看购物车请回复 2 ");
                        System.out.println("结账请回复 3 ");
                        System.out.println("结束购物请回复 4 ");
                        System.out.println("请输入商品id添加商品至购物车; 回复0结束添加");
                        int id = sc.nextInt();

                        if (id == 1) {
                            showProducts(products);
                            System.out.println("回复商品ID添加商品至购物车");
                            String ID = sc.next();
                            inPro = null;
                            inPro = Class.forName("CmdShop").getResourceAsStream("/products.xlsx");
                            Product product = readproductsExcel.readProductsById(ID, inPro);
                            buyProduct(carts, product, con);
                            con++;
                            System.out.println("con="+con);
                        }

                        if (id == 2) {
                            showProducts(carts);
                        }

                        if (id == 3) {

                        }

                        if (id == 4) {
                            break;
                        }
                    }
                }else {
                    System.out.println("登录失败");
                }
            }
        }
    }

    public static void showProducts(Product[] products) {
        try {//获取空指针异常
            if (products == null || products.length==0) {
                System.out.println("无商品");
            } else {
                for (Product product : products) {
                    if(product != null) {
                        System.out.print(product.getId());
                        System.out.print("\t" + product.getName());
                        System.out.print("\t" + product.getPrice());
                        System.out.println("\t" + product.getDesc());
                    }
                }
            }
        }catch (Exception e){
            System.out.println("无商品");
        }
    }

    public static void buyProduct(Product[] products,Product product,int con){
        if (product != null) {
            if (con < products.length) {
                products[con] = product;
                System.out.println("商品" + product.getName() + "已添加");
            } else {
                System.out.println("购物车已满");
            }
        }
    }
}
