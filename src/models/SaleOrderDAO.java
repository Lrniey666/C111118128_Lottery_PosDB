package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleOrderDAO {

    private Connection conn;

    public List<SaleOrder> getAllSaleOrders() {

        conn = DBConnection.getConnection();
        String query = "select * from sale_order";
        List<SaleOrder> saleOrderList = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rset = ps.executeQuery();

            while (rset.next()) {
                SaleOrder saleOrder = new SaleOrder();
                saleOrder.setOrderNum(rset.getString("order_num"));
                saleOrder.setOrderDate(rset.getTimestamp("order_date"));
                saleOrder.setTotalPrice(rset.getDouble("total_price"));
                saleOrder.setCustomerName(rset.getString("customer_name"));
                saleOrder.setCustomerAddress(rset.getString("customer_address"));
                saleOrder.setCustomerPhone(rset.getString("customer_phone"));
                saleOrderList.add(saleOrder);
            }
        } catch (SQLException ex) {
            System.out.println("getAllSaleOrders exception: " + ex.toString());
        }

        return saleOrderList;
    }
}
