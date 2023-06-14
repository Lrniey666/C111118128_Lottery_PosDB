package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ProductDAO {

    //private Connection conn = DBConnection.getConnection();
    private Connection conn;

    // 取得所有產品
    public List<Product> getAllProducts() {

        conn = DBConnection.getConnection();
        String query = "select * from product";
        List<Product> product_list = new ArrayList<>();

        try {
            PreparedStatement ps
                    = conn.prepareStatement(query);
            ResultSet rset = ps.executeQuery();

            while (rset.next()) {
                Product product = new Product();
                product.setProduct_id(rset.getString("product_id"));
                product.setCategory(rset.getString("category"));
                product.setName(rset.getString("name"));
                product.setPrice(rset.getInt("price"));
                product.setPhoto(rset.getString("photo"));
                product.setDescription(rset.getString("description"));
                product_list.add(product);

                //不要斷線，一直會用到，使用持續連線的方式
                //conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("getAllproducts異常:" + ex.toString());
        }

        return product_list;
    }

    //選擇特定字串"孫大毛"或是"孫%"或是"%毛"
    public List<Product> findByNameContaining(String name_str) {
        conn = DBConnection.getConnection();
        boolean success = false;
        List<Product> product_list = new ArrayList();
        //String query = String.format("select * from student where name like '%s%s'", name_str, "%");
        //String query = String.format("select * from student where name like '%s'", name_str);
        String query = "select * from product where name like ?"; //%果%
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, "%" + name_str + "%");  // String.format("%s%s%s","%",name_str, "%")
            ResultSet rset = state.executeQuery();
            while (rset.next()) {
                Product product = new Product();
                product.setProduct_id(rset.getString("product_id"));
                product.setCategory(rset.getString("category"));
                product.setName(rset.getString("name"));
                product.setPrice(rset.getInt("price"));
                product.setPhoto(rset.getString("photo"));
                product.setDescription(rset.getString("description"));
                product_list.add(product);
            }
        } catch (SQLException ex) {
            System.out.println("資料庫selectByName操作異常:" + ex.toString());
        }
        return product_list;
    }

    //查詢價格低於多少錢的產品
   public List<Product> findByPriceLessThanEqual(int price) {
        conn = DBConnection.getConnection();
        List<Product> product_list = new ArrayList();
        String query = "select * from product where price <= ?";
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setInt(1, price);
            ResultSet rset = state.executeQuery();
            while (rset.next()) {
                Product product = new Product();
                product.setProduct_id(rset.getString("product_id"));
                product.setCategory(rset.getString("category"));
                product.setName(rset.getString("name"));
                product.setPrice(rset.getInt("price"));
                product.setPhoto(rset.getString("photo"));
                product.setDescription(rset.getString("description"));
                product_list.add(product);
            }
        } catch (SQLException ex) {
            System.out.println("資料庫selectByPrice作異常:" + ex.toString());
        }
        return product_list;
    }

  
   //查詢過濾filter 某個大類別的產品
   public List<Product> findByCate(String cate) {
        conn = DBConnection.getConnection();
        List<Product> product_list = new ArrayList();
        String query = "select * from product where category = ?";
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, cate);
            ResultSet rset = state.executeQuery();
            while (rset.next()) {
                Product product = new Product();
                product.setProduct_id(rset.getString("product_id"));
                product.setCategory(rset.getString("category"));
                product.setName(rset.getString("name"));
                product.setPrice(rset.getInt("price"));
                product.setPhoto(rset.getString("photo"));
                product.setDescription(rset.getString("description"));
                product_list.add(product);
            }
        } catch (SQLException ex) {
            System.out.println("資料庫selectByCate異常:" + ex.toString());
        }
        return product_list;
    }
    
   
    //選擇某個product_id
    public Product findById(String id) {
        conn = DBConnection.getConnection();
        boolean success = false;
        String query = "select * from product where product_id = ?";
        //String query = String.format("select * from student where student_id = '%s'", id);
        Product product = new Product();
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, id);
            ResultSet rset = state.executeQuery();

            while (rset.next()) {
                success = true;
                product.setProduct_id(rset.getString("product_id"));
                product.setCategory(rset.getString("category"));
                product.setName(rset.getString("name"));
                product.setPrice(rset.getInt("price"));
                product.setPhoto(rset.getString("photo"));
                product.setDescription(rset.getString("description"));
            }
        } catch (SQLException ex) {
            System.out.println("資料庫selectByID操作異常:" + ex.toString());
        }

        if (success) {
            return product;
        } else {
            return null;
        }

    }

  
    //新增一項產品 
    public boolean insert(Product product) {
        conn = DBConnection.getConnection();
        String query = "insert into product(product_id,name,category,price,photo,description) VALUES (?,?,?,?,?,?)";
        boolean success = false;
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, product.getProduct_id());
            state.setString(2, product.getName());
            state.setString(3, product.getCategory());
            state.setInt(4, product.getPrice());
            state.setString(5, product.getPhoto());
            state.setString(6, product.getDescription());

            state.execute();
            //state.executeUpdate();
            success = true;
            System.out.println("新增成功");
        } catch (SQLException ex) {
            System.out.println("insert異常:" + ex.toString());
        }
        return success;
    } 
    
    //刪除某項產品
    public boolean delete(String id) {
        conn = DBConnection.getConnection();
        String query = "delete from product where product_id =?";
        boolean sucess = false;
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, id);
            //statement.execute();
            sucess = statement.executeUpdate() > 0;
            if (sucess) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("Record not found.");
            }
        } catch (SQLException ex) {
            System.out.println("delete異常:\n" + ex.toString());
        }
        return sucess;
    }
    // 更新某項產品
    public void update(Product product) {
        conn = DBConnection.getConnection();
        String query = "update product set name=?, category=?, price=?, photo= ?, description=? where product_id = ?";
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(6, product.getProduct_id());
            state.setString(1, product.getName());
            state.setString(2, product.getCategory());
            state.setInt(3, product.getPrice());
            state.setString(4, product.getPhoto());
            state.setString(5, product.getDescription());
            
            state.executeUpdate();
            System.out.println("更新成功");
        } catch (SQLException ex) {
            System.out.println("update異常:" + ex.toString());
        }
    }
    

    public TreeMap<String, Product> readProductByCategoryToTreeMap(String cate) {
        //read_product_from_file(); //從檔案或資料庫讀入產品菜單資訊

        //***********產生資料DAO來使用
        //ProductDAO productDao = new ProductDAO();
        //***************從檔案或資料庫讀入產品菜單資訊
        List<Product> products = this.findByCate(cate);
        //List<Product> products = productDao.getAllProducts();

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
    
    //dictionary  (key, value or content)
    //Map --> dict  {'key':'value'}
    public TreeMap<String, Product> readAllProductToTreeMap() {
        //read_product_from_file(); //從檔案或資料庫讀入產品菜單資訊

        //***********產生資料DAO來使用
        //ProductDAO productDao = new ProductDAO();
        //***************從檔案或資料庫讀入產品菜單資訊
        List<Product> products = this.getAllProducts();
        //List<Product> products = productDao.getAllProducts();

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
    
    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();
        
        //dao.insert(new Product("p-c-101","咖啡","拿鐵", 120, "lataphoto.jpt","好喝的拿鐵咖啡"));
        //dao.delete("p-c-101");
        dao.update(  new Product("p-c-101","咖啡","拿鐵", 125, "lataphoto.jpt","好喝的拿鐵咖啡")   );
        
//        List<Product> product_list = dao.getAllProducts();
//        for (Product product : product_list) {
//            System.out.println(product);
//        }

        List<Product> product_list = dao.findByCate("茶飲");
        //List<Product> product_list = dao.findByPriceLessThanEqual(80);
        //List<Product> product_list = dao.findByNameContaining("果");
        for (Product product : product_list) {
            System.out.println(product);
        }
    }

}
