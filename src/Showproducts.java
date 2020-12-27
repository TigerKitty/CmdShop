import java.io.InputStream;

public class Showproducts {
    Product[] products = null;
    public Showproducts(){
    }

    public void showProducts() throws ClassNotFoundException{
        InputStream in = Class.forName("CmdShop").getResourceAsStream("/products.xlsx");
        ReadproductsExcel readproductsExcel = new ReadproductsExcel();
        products = readproductsExcel.readExcel(in);
        if(products==null){
            System.out.println("无商品");
        }else {
            for(Product product: products){
                System.out.print(product.getId()+" ");
                System.out.print(product.getName()+" ");
                System.out.print(product.getPrice()+" ");
                System.out.println(product.getDesc());
            }
        }
    }
}
