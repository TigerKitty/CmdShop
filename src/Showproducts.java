import java.io.InputStream;

public class Showproducts {
    private Product[] products = null;
    public void showProducts() throws ClassNotFoundException{
        InputStream in = Class.forName("CmdShop").getResourceAsStream("/products.xlsx");
        ReadproductsExcel readproductsExcel = new ReadproductsExcel();
        products = readproductsExcel.readAllProducts(in);
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
    public Product[] getProducts(){
        return products;
    }
}
