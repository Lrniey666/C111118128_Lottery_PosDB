package models;

import java.util.List;
import java.util.TreeMap;

public class ReadCategoryProductFromDB {

    //dictionary  (key, value or content)
    //Map --> dict  {'key':'value'}
    public static TreeMap<String, Product> readProduct() {
        //read_product_from_file(); //從檔案或資料庫讀入產品菜單資訊

        //***********產生資料DAO來使用
        ProductDAO productDao = new ProductDAO();
        //***************從檔案或資料庫讀入產品菜單資訊
        List<Product> products = productDao.getAllProducts();

        //放所有產品  產品編號  產品物件Product
        TreeMap<String, Product> product_dict = new TreeMap();

        //準備產品的字典 從資料庫中讀入
        //放入product_dict中點選產品與顯示產品比較方便
        for (Product product : products) {
            //System.out.println(product.getCategory());
            product_dict.put(product.getProduct_id(), product);
        }

        return product_dict;

    }
    /*
    public static TreeMap<String, SaleOrder> readSaleOrders() {
        SaleOrderDAO saleOrderDao = new SaleOrderDAO();
        List<SaleOrder> saleOrders = saleOrderDao.getAllSaleOrders();

        TreeMap<String, SaleOrder> saleOrderDict = new TreeMap<>();
        for (SaleOrder saleOrder : saleOrders) {
            saleOrderDict.put(saleOrder.getOrderNum(), saleOrder);
        }

        return saleOrderDict;
    }
    */
    
    /*
    public static TreeMap<Integer, OrderDetail> readOrderDetails() {
        OrderDetailDAO orderDetailDao = new OrderDetailDAO();
        List<OrderDetail> orderDetails = orderDetailDao.getAllOrderDetails();

        TreeMap<Integer, OrderDetail> orderDetailDict = new TreeMap<>();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetailDict.put(orderDetail.getId(), orderDetail);
        }

        return orderDetailDict;
    }
    */

    public static void main(String[] args) {

        System.out.println(ReadCategoryProductFromDB.readProduct());
         /*System.out.println(ReadCategoryProductFromDB.readSaleOrders());
        System.out.println(ReadCategoryProductFromDB.readOrderDetails());
        */

    }

}
