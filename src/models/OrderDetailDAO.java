package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {

    private Connection conn;

    public List<OrderDetail> getAllOrderDetails() {

        conn = DBConnection.getConnection();
        String query = "select * from order_detail";
        List<OrderDetail> orderDetailList = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rset = ps.executeQuery();

            while (rset.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(rset.getInt("id"));
                orderDetail.setOrderNum(rset.getString("order_num"));
                orderDetail.setProductId(rset.getString("product_id"));
                orderDetail.setQuantity(rset.getInt("quantity"));
                orderDetail.setProductPrice(rset.getInt("product_price"));
                orderDetail.setProductName(rset.getString("product_name"));
                orderDetailList.add(orderDetail);
            }
            
        } catch (SQLException ex) {
            System.out.println("getAllOrderDetails exception: " + ex.toString());
        }

        return orderDetailList;
    }
}
