package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderDAO {

    private Connection conn;

    public String getMaxOrderNum() {
        conn = DBConnection.getConnection();
        String maxVal = null;

        String query = "SELECT Max(order_num) as `max_id` FROM `sale_order`";
        //String query = "SELECT Max(order_num) as `max_id` FROM `sale_order`";
        //String query = "SELECT Max(order_num) as `max_id` FROM `sale_order` LIMIT 1";
        try {
            PreparedStatement state = conn.prepareStatement(query);
            ResultSet rset = state.executeQuery();
            while (rset.next()) {
                maxVal = rset.getString("max_id");
            }
        } catch (SQLException ex) {
            System.out.println("資料庫getMaxOrderNum操作異常:" + ex.toString());
        }
        return maxVal;
    }

    public boolean insertCart(Order cart) {
        //String order_num =  getMaxOrderNum();
        conn = DBConnection.getConnection();
        String query = "insert into `sale_order`(order_num,total_price,"
                + "customer_name,customer_phone, customer_address) "
                + "VALUES (?, ?, ?, ?, ?)";
        boolean success = false;
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, cart.getOrder_num());
            state.setInt(2, cart.getTotal_price());
            state.setString(3, cart.getCustomer_name());
            state.setString(4, cart.getCustomer_phtone());
            state.setString(5, cart.getCustomer_address());
            state.execute();
            success = true;
            System.out.println("insert cart成功!" );
        } catch (SQLException ex) {
            System.out.println("insert異常:" + ex.toString());
        }
        return success;
    }

    //新增訂單明細 應該寫在OrderDetailDAO.java比較好
    public boolean insertOrderDetailItem(OrderDetail item) {
        //String order_num =  getMaxOrderNum();
        conn = DBConnection.getConnection();

        String query = "INSERT INTO `order_detail` (`order_num`, `product_id`, `quantity`, product_price, product_name) VALUES (?, ?, ?, ?, ?)";
        boolean success = false;
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, item.getOrder_num());
            state.setString(2, item.getProduct_id());
            state.setInt(3, item.getQuantity());
            state.setInt(4, item.getProduct_price()); //optional
            state.setString(5, item.getProduct_name());//optional
            state.execute();
            success = true;
            System.out.println("insert order detail成功!" );
        } catch (SQLException ex) {
            System.out.println("insert異常:" + ex.toString());
        }
        return success;
    }

    public static void main(String[] args) {

        
        OrderDAO orddao = new OrderDAO();
        
        // get maximum order number
        System.out.println(orddao.getMaxOrderNum());

        
        /* //逐一測試********
        //測試1--------------------
        //訂單編號
        String ordNum = "ord-501";

        //新增訂單物件
        Order cart = new Order();
        
        cart.setOrder_num(ordNum);
        cart.setTotal_price(250);
        cart.setCustomer_name("李大同");
        
        orddao.insertCart(cart);

        //新增訂單細節 
        OrderDetail new_item = new OrderDetail();
        
        new_item.setProduct_id("p-j-103");
        new_item.setOrder_num(ordNum);
        new_item.setQuantity(2);

        //新增一筆果汁
        orddao.insertOrderDetailItem(new_item);
        
        */  //逐一測試

         // /* //逐一測試********
        //測試2----------------------
        //結帳存檔  private void checkSave() {}
        
        //ObservableList    order_list有新增或刪除都會處動table的更新，也就是發生任何改變時都被通知
        ObservableList<OrderDetail> order_list;

        order_list = FXCollections.observableArrayList(
                new OrderDetail("p-j-110", "葡萄汁", 80, 3),
                new OrderDetail("p-j-105", "番茄汁", 70, 1)
        );
        //append_order_to_csv(); //將這一筆訂單附加儲存到檔案或資料庫
        //這裡要取得不重複的order_num編號
        String order_num = orddao.getMaxOrderNum();

        if (order_num == null) {
            order_num = "ord-100";
        }

        System.out.println(order_num);
        System.out.println(order_num.split("-")[1]);

        //將現有訂單編號加上1
        int serial_num = Integer.parseInt(order_num.split("-")[1]) + 1;
        System.out.println(serial_num);

        //每家公司都有其訂單或產品的編號系統，這裡用ord-xxx表之
        String new_order_num = "ord-" + serial_num;

        //int sum = checkTotal();
        int total = 0;
        //將所有的訂單顯示在表格   計算總金額
        for (OrderDetail od : order_list) {
            //加總
            total += od.getProduct_price() * od.getQuantity();
        }

        //Cart crt = new Cart(new_order_num, "2021-05-01", 123, userName);
        Order crt = new Order();
        crt.setOrder_num(new_order_num);
        crt.setTotal_price(total);
        crt.setCustomer_name("路人甲");
        crt.setCustomer_phtone("無電話");
        crt.setCustomer_address("無地址");

        //寫入一筆訂單道資料庫
        orddao.insertCart(crt);

        //逐筆寫入訂單明細
        for (int i = 0; i < order_list.size(); i++) {
            OrderDetail item = new OrderDetail();
            item.setOrder_num(new_order_num); //設定訂單編號
            item.setProduct_id(order_list.get(i).getProduct_id()); //設定產品編號
            item.setQuantity(order_list.get(i).getQuantity());//設定訂購數量 多少杯
            item.setProduct_price(order_list.get(i).getProduct_price()); //產品單價 建議不要這個欄位 不符合正規化
            item.setProduct_name(order_list.get(i).getProduct_name());//產品名稱 建議不要這個欄位 不符合正規化

            orddao.insertOrderDetailItem(item);

        } //for
        
       // */  //逐一測試
    }

}
