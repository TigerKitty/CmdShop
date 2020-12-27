import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class CmdShop {
    public static void main(String[] args) throws ClassNotFoundException {
        //File file = new File("D:\\IntelliJ IDEA 2019.2.4\\CmdShop\\src\\users.xlsx");
        InputStream in = Class.forName("CmdShop").getResourceAsStream("users.xlsx");
        ReadExcel readExcel = new ReadExcel();
        User[] users = readExcel.readExcel(in);

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.next();
        System.out.println("请输入密码");
        String password = sc.next();

        for(int i=0; i<users.length; i++) {
            System.out.println(users[i].getUsername());
            System.out.println(users[i].getPassword());
            if (users[i].getUsername().equals(username) && users[i].getPassword().equals(password)) {
                System.out.println("登录成功");
            }
        }
    }
}
