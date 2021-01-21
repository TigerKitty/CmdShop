import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CmdShop {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        //File file = new File("D:\\IntelliJ IDEA 2019.2.4\\CmdShop\\src\\users.xlsx");
        InputStream in = Class.forName("CmdShop").getResourceAsStream("/users.xlsx");
        InputStream inPro = Class.forName("CmdShop").getResourceAsStream("/products.xlsx");
        ReadusersExcel readExcel = new ReadusersExcel();
        AboutOrder aboutOrder = new AboutOrder();
        ReadproductsExcel readproductsExcel = new ReadproductsExcel();
        Product[] products = readproductsExcel.readAllProducts(inPro);
        User[] users = readExcel.readExcel(in);
        Order order = new Order();
        Scanner sc = new Scanner(System.in);
        boolean bool = true;
        int con = 0;
        int n = 1;//用于指向Order中的第n行
        while (bool) {
            System.out.println("请输入用户名");
            String username = sc.next();
            System.out.println("请输入密码");
            String password = sc.next();

            aboutOrder.clearOrder();//置空Order表单!!!!!!!!!!

            for (int i = 0; i < users.length; i++) {
                //System.out.println(users[i].getUsername());
                //System.out.println(users[i].getPassword());
                if (users[i].getUsername().equals(username) && users[i].getPassword().equals(password)) {
                    System.out.println("登录成功");
                    bool = false;
                    Product[] carts = new Product[5];
                    while (true) {
                        System.out.println("购物请按 1 ");
                        System.out.println("查看购物车请按 2 ");
                        System.out.println("结账请按 3 ");
                        System.out.println("退出请按 4 ");
                        int id = sc.nextInt();

                        if (id == 1) {
                            showProducts(products);
                            System.out.println("回复商品ID添加商品至购物车");
                            String ID = sc.next();
                            buyProduct(carts, ID, con);
                            con++;
                            System.out.println("con=" + con);
                        }

                        if (id == 2) {
                            showProducts(carts);
                        }

                        if (id == 3) {
                            int[] helper = new int[carts.length];
                            int j = 0;
                            float totalMoney = 0;
                            while (carts[j] != null) {
                                int ammounts = getAmmounts(helper, carts, j);
                                if (ammounts != 0) {//只要至少有一个就进行添加
                                    order = createOrder(carts[j], users[i], ammounts);
                                    totalMoney += ammounts * carts[j].getPrice();
                                    aboutOrder.AddOrderToExcle(order, n);
                                    n++;
                                }
                                j++;
                            }
                            if(checkout(totalMoney)){
                                //结账成功
                                carts = new Product[5];//购物车置空
                                if(continueBuy()){
                                    //购物继续
                                }else{
                                    //退出购物
                                    break;
                                }
                            }else{
                                //结账失败
                                System.out.println("购买继续！");
                            }
                        }
                        if (id == 4) {
                            break;
                        }
                    }
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }

    public static void showProducts(Product[] products) {
        try {//获取空指针异常
            if (products == null || products.length == 0 || products[0] == null) {
                System.out.println("无商品");
            } else {
                for (Product product : products) {
                    if (product != null) {
                        System.out.print(product.getId());
                        System.out.print("\t" + product.getName());
                        System.out.print("\t" + product.getPrice());
                        System.out.println("\t" + product.getDesc());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("无商品");
        }
    }

    public static void buyProduct(Product[] products, String ID, int con) throws ClassNotFoundException {
        InputStream inPro = Class.forName("CmdShop").getResourceAsStream("/products.xlsx");
        ReadproductsExcel readproductsExcel = new ReadproductsExcel();
        Product product = readproductsExcel.readProductsById(ID, inPro);
        if (product != null) {
            if (con < products.length) {
                products[con] = product;
                System.out.println("商品" + product.getName() + "已添加！");
            } else {
                System.out.println("购物车已满");
            }
        }
    }

    public static Order createOrder(Product product, User user, int ammounts) {
        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setAmmount(ammounts);
        order.setTotalCost(product.getPrice());
        order.setReceipts(product.getPrice());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        order.setOrderDate(df.format(new Date()));
        return order;
    }

    public static int getAmmounts(int[] helper, Product[] carts, int i) {
        int con = 0;//用来计数
        if (helper[i] != 1) {//也就是没有被添加过
            con = 1;//当前这个商品生效
            helper[i] = 1;
            String proName = new String();
            if (carts[i] != null) {
                proName = carts[i].getName();
            }
            int j = i + 1;
            while (carts[j] != null) {
                if (proName.equals(carts[j].getName())) {//只要商品相同就可以添加了，不用再判断helper了
                    con++;
                    helper[j] = 1;//改变helper的对应值
                }
                j++;
            }
        }
        return con;
    }

    public static boolean checkout(float f) {
        System.out.println("确定要进行结账吗？");
        System.out.println("回复1确定，回复0返回");
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        boolean flag = false;
        while (true) {
            if (a == 0) {
                break;
            } else if (a == 1) {
                if(f==0){
                    System.out.println("无商品");
                }else {
                    System.out.println("totalMoney=" + f);
                    System.out.println("结账成功！！！");
                }
                flag = true;
                break;
            } else {
                System.out.println("请输入正确的数字!");
            }
        }
        return flag;
    }
    public static boolean continueBuy(){
        System.out.println("是否继续购买？");
        System.out.println("回复1继续，回复0退出");
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        boolean flag = false;
        while (true) {
            if (a == 0) {
                break;
            } else if (a == 1) {
                System.out.println("购物继续！！！");
                flag = true;
                break;
            } else {
                System.out.println("请输入正确的数字!");
            }
        }
        return flag;
    }
}