package lab5_pos_order_entry_app_db;

import java.util.TreeMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import models.Order;
import models.OrderDAO;
import models.OrderDetail;
import models.Product;
import models.ProductDAO;

import models.ReadCategoryProductFromDB;

public class DrinkPosOrderDBApp extends Application {

    //***********產生資料DAO來使用訂單輸入資料庫之功能
    private OrderDAO orderDao = new OrderDAO();

    //取得產品字典
    private ProductDAO prodcutDao = new ProductDAO();

    //3.宣告全域變數)並取得三種菜單的磁磚窗格，隨時被取用。
    private TilePane two_k = getProductCategoryMenu("2k");
    private TilePane one_k = getProductCategoryMenu("1k");
    private TilePane five_h = getProductCategoryMenu("500");
    private TilePane two_h = getProductCategoryMenu("200");
    private TilePane one_h = getProductCategoryMenu("100");


    //4.宣告一個容器(全域變數) menuContainerPane，裝不同種類的菜單，菜單類別選擇按鈕被按下，立即置放該種類的菜單。
    private VBox menuContainerPane = new VBox();

    //ObservableList    order_list有新增或刪除都會處動table的更新，也就是發生任何改變時都被通知
    private ObservableList<OrderDetail> order_list;

    //private TreeMap<String, Product> product_dict = prodcutDao.readProduct();
    private TreeMap<String, Product> product_dict = ReadCategoryProductFromDB.readProduct();

    //顯示訂單詳情表格
    private TableView table;

    //顯示訂單詳情白板
    private TextArea display = new TextArea();

    //結帳存檔
    private void checkSave() {

        display.setText("結帳中，列印發票...\n");

        //append_order_to_csv(); //將這一筆訂單附加儲存到檔案或資料庫
        //這裡要取得不重複的order_num編號
        String order_num = orderDao.getMaxOrderNum();

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
        crt.setCustomer_name("無姓名");
        crt.setCustomer_phtone("無電話");
        crt.setCustomer_address("無地址");

        //寫入一筆訂單道資料庫
        orderDao.insertCart(crt);

        //逐筆寫入訂單明細
        for (int i = 0; i < order_list.size(); i++) {
            OrderDetail item = new OrderDetail();
            item.setOrder_num(new_order_num); //設定訂單編號
            item.setProduct_id(order_list.get(i).getProduct_id()); //設定產品編號
            item.setQuantity(order_list.get(i).getQuantity());//設定訂購數量 多少杯
            item.setProduct_price(order_list.get(i).getProduct_price()); //產品單價 建議不要這個欄位 不符合正規化
            item.setProduct_name(order_list.get(i).getProduct_name());//產品名稱 建議不要這個欄位 不符合正規化

            orderDao.insertOrderDetailItem(item);
        }

        order_list.clear();
    }

    //define your components methods-->get or prepare containers
    // 產品菜單磁磚窗格，置放你需要用到的菜單
     public TilePane getProductCategoryMenu(String category) {

        //取得產品清單
        TreeMap<String, Product> product_dict = ReadCategoryProductFromDB.readProduct();
        //磁磚窗格
        TilePane category_menu = new TilePane(); //
        category_menu.setVgap(20);  //垂直間隙
        category_menu.setHgap(20);
        //設定一個 row有4個columns，放不下就放到下一個row
        category_menu.setPrefColumns(3);

        //將產品清單內容一一置放入產品菜單磁磚窗格
        for (String item_id : product_dict.keySet()) {

            if (product_dict.get(item_id).getCategory().equals(category)) {
                //定義新增一筆按鈕
                Button btn = new Button();

                //width, height 按鈕外框的大小，你要自行調整，讓它美觀。沒有設定外框會大小不一不好看
                btn.setPrefSize(240, 180);
                //btn.setText(product_dict.get(item_id).getName()); //不要顯示文字，顯示圖片就好

                //按鈕元件顯示圖片Creating a graphic (image)
                Image img = new Image("imgs/" + product_dict.get(item_id).getPhoto()); //讀出圖片
                ImageView imgview = new ImageView(img);//圖片顯示物件
                imgview.setFitHeight(180); //設定圖片高度，你要自行調整，讓它美觀
                imgview.setFitWidth(240); //設定圖片高度，你要自行調整，讓它美觀
                imgview.setPreserveRatio(true); //圖片的寬高比維持

                //Setting a graphic to the button
                btn.setGraphic(imgview); //按鈕元件顯示圖片
                category_menu.getChildren().add(btn);  //放入菜單磁磚窗格

                //定義按鈕事件-->點選一次，就加入購物車，再點選一次，數量要+1
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //新增一筆訂單到order_list (ArrayList)
                        //order_list.add(new Order("p-109", "新增的果汁", 30, 1));
                        addToCart(item_id);
                        System.out.println(product_dict.get(item_id).getName());
                    }
                });
            }
        }
        return category_menu;
    }

    //1.多一個窗格(可以用磁磚窗格最方便)置放菜單類別選擇按鈕，置放於主視窗的最上方區域。
    public TilePane getMenuSelectionContainer() {

        //定義"果汁類"按鈕
        Button btn_two_k = new Button();
        btn_two_k.setText("兩千塊");
        btn_two_k.setPrefSize(170, 100);
        btn_two_k.setMaxHeight(100);
        btn_two_k.setMinHeight(100);
        btn_two_k.setStyle("-fx-font-weight: bold;");
        btn_two_k.setStyle("-fx-font-size: 30px;");  
        btn_two_k.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menuContainerPane.getChildren().clear();//先刪除原有的窗格再加入新的類別窗格
                menuContainerPane.getChildren().add(two_k);
                
            }           
        });
        //定義"茶飲類"按鈕
        Button btn_one_k = new Button("一千塊");
        btn_one_k.setPrefSize(170, 100);
        btn_one_k.setMaxHeight(100);
        btn_one_k.setMinHeight(100);
        btn_one_k.setStyle("-fx-font-weight: bold;");
        btn_one_k.setStyle("-fx-font-size:30px;");
        btn_one_k.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                menuContainerPane.getChildren().clear();//先刪除原有的窗格再加入新的類別窗格
                menuContainerPane.getChildren().add(one_k);
            }
        });
        //定義"咖啡類"按鈕
        Button btn_five_h = new Button("五百塊");
        btn_five_h.setPrefSize(170, 100);
        btn_five_h.setMaxHeight(100);
        btn_five_h.setMinHeight(100);
        btn_five_h.setStyle("-fx-font-weight: bold;");
        btn_five_h.setStyle("-fx-font-size:30px;");
        btn_five_h.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                menuContainerPane.getChildren().clear();//先刪除原有的窗格再加入新的類別窗格
                menuContainerPane.getChildren().add(five_h);
            }
        });
        
        Button btn_two_h = new Button("兩百塊");
        btn_two_h.setPrefSize(170, 100);
        btn_two_h.setMaxHeight(100);
        btn_two_h.setMinHeight(100);
        btn_two_h.setStyle("-fx-font-weight: bold;");
        btn_two_h.setStyle("-fx-font-size:30px;");
        btn_two_h.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                menuContainerPane.getChildren().clear();//先刪除原有的窗格再加入新的類別窗格
                menuContainerPane.getChildren().add(two_h);
            }
        });
        
         Button btn_one_h = new Button("一百塊");
        btn_one_h.setPrefSize(170, 100);
        btn_one_h.setMaxHeight(100);
        btn_one_h.setMinHeight(100);
        btn_one_h.setStyle("-fx-font-weight: bold;");
        btn_one_h.setStyle("-fx-font-size:30px;");
        btn_one_h.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                menuContainerPane.getChildren().clear();//先刪除原有的窗格再加入新的類別窗格
                menuContainerPane.getChildren().add(one_h);
            }
        });

        //使用磁磚窗格，放置前面三個按鈕
        TilePane conntainerCategoryMenuBtn = new TilePane();
        //conntainerCategoryMenuBtn.setAlignment(Pos.CENTER_LEFT);
        //conntainerCategoryMenuBtn.setPrefColumns(6); //
        conntainerCategoryMenuBtn.setVgap(10);
        conntainerCategoryMenuBtn.setHgap(10);

        conntainerCategoryMenuBtn.getChildren().add(btn_two_k);
        conntainerCategoryMenuBtn.getChildren().add(btn_one_k);
        conntainerCategoryMenuBtn.getChildren().add(btn_five_h);
        conntainerCategoryMenuBtn.getChildren().add(btn_two_h);
        conntainerCategoryMenuBtn.getChildren().add(btn_one_h);



        //root.getChildren().add(categoryMenuBtnTile);
        return conntainerCategoryMenuBtn;
    }

    //初始化所有元件與事件並將所有元件放入root
    public void initializeOrderTable() {

        //訂單陣列串列初始化FXCollections類別有很多靜態方法可以操作ObservableList的"訂單陣列串列"
        order_list = FXCollections.observableArrayList();
        //前面是空的串列，若已知有兩筆訂單可以這樣設定

        /*
        order_list = FXCollections.observableArrayList(
                new Order("p-101", "葡萄汁", 80, 3),
                new Order("p-102", "番茄汁", 70, 1)
        );        */
        //也可以這樣加入一筆訂單
        //order_list.add(new Order("p-103", "西瓜汁", 80, 3));
        //表格初始化
        table = new TableView();
        table.setEditable(true); //表格允許修改
        table.setPrefHeight(600);
        //表格內置放的資料來自於order_list，依據置放順序顯示
        table.setItems(order_list);

        //table也可以這樣放入訂單
        //table.getItems().add(new Order("p-104", "奇異果汁", 50, 2));
        //定義第一個欄位column"品名"，其值來自於Order物件的某個String變數
        TableColumn<OrderDetail, String> order_item_name = new TableColumn("品名");
        //置放哪個變數值?指定這個欄位 對應到Order的"name"實例變數值
        order_item_name.setCellValueFactory(new PropertyValueFactory("product_name"));

        order_item_name.setPrefWidth(100); //設定欄位寬度
        order_item_name.setMinWidth(100);

        TableColumn<OrderDetail, Integer> order_item_price = new TableColumn("價格");
        order_item_price.setCellValueFactory(new PropertyValueFactory("product_price"));
        order_item_price.setMinWidth(50);

        TableColumn order_item_qty = new TableColumn("數量");
        order_item_qty.setCellValueFactory(new PropertyValueFactory("quantity"));
        //這個欄位值內容可以被修改，因為qty是整數，因此須將整數轉為字串，才能異動Order物件，否則會報錯!
        order_item_qty.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        order_item_qty.setMinWidth(50);
        
         TableColumn<OrderDetail, Integer> totalPriceColumn = new TableColumn<>("總價");
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        totalPriceColumn.setMinWidth(50);
        

        //把3個欄位加入table中
        table.getColumns().addAll(order_item_name, order_item_price, order_item_qty,totalPriceColumn);

        //表格最後一欄是空白，不要顯示!
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        //定義數量欄位被修改後進行那些動作
        order_item_qty.setOnEditCommit(new EventHandler<CellEditEvent>() {
            @Override
            public void handle(CellEditEvent event) {

                int row_num = event.getTablePosition().getRow();//哪一筆被修改
                int new_val = (Integer) event.getNewValue(); //改成甚麼數值 需要將物件轉為整數
                OrderDetail target = (OrderDetail) event.getTableView().getItems().get(row_num); //取得該筆果汁傳參考呼叫
                //修改成新的數值 該筆訂單存放於order_list
                target.setQuantity(new_val);

                System.out.println(order_list.get(row_num).getQuantity()); //顯示修改後的數值
                 table.refresh();
                 table.setStyle("-fx-font-size: 14px;");
                checkTotal();// 在這裡調用 checkTotal() 方法以更新顯示的總金額
                

         // 更新表格，以便總價列顯示新的值
            }
        });
    }

    //表格新增項目刪除項目之操作區塊
   public TilePane getOrderOperationContainer() {
        

        //定義刪除一筆按鈕
        Button btnDelete = new Button("刪除一筆");
         btnDelete.setPrefSize(150, 100);
        btnDelete.setMaxHeight(100);
        btnDelete.setMinHeight(100);
        btnDelete.setStyle("-fx-font-size: 24px;"); 
       btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //從table中取得目前被選到的項目
                Object selectedItem = table.getSelectionModel().getSelectedItem();
                //從表格table中或是從order_list刪除這一筆，擇一進行
                //table.getItems().remove(selectedItem);
                order_list.remove(selectedItem);
                checkTotal();
            }
        });
       
       //定義刪除全部按鈕
        Button btndel = new Button("刪除全部");
         btndel.setPrefSize(150, 100);
        btndel.setMaxHeight(100);
        btndel.setMinHeight(100);
        btndel.setStyle("-fx-font-size: 24px;"); 
   btndel.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        // 刪除table中的所有資料
        table.getItems().clear();
    }
});


        //定義結帳按鈕
        Button btnCheck = new Button("結帳");
         btnCheck.setPrefSize(150, 100);
        btnCheck.setMaxHeight(100);
        btnCheck.setMinHeight(100);
        btnCheck.setStyle("-fx-font-size: 24px;"); 
      btnCheck.setOnAction(new EventHandler<ActionEvent>() {
    private boolean isCheckedOut = false;

    @Override
    public void handle(ActionEvent e) {
        if (!isCheckedOut) {
            btnCheck.setText("結帳完成");
            int total = 0;
            for (OrderDetail od : order_list) {
                total += od.getProduct_price() * od.getQuantity();
            }
            display.setText(String.format("請結帳，共 %d 元", total));

     

            isCheckedOut = true;
        } else {
            btnCheck.setText("結帳");
            display.setText("");

            // 啟用 getMenuSelectionContainer() 的所有按鈕
            for (Node node : getMenuSelectionContainer().getChildren()) {
                if (node instanceof Button) {
                    ((Button) node).setDisable(false);
                }
            }

            // 清空 table 中的資料
            table.getItems().clear();
            isCheckedOut = false;
            display.setText(String.format("結帳成功"));
        }
    }
});




        //放置任務功能按鈕
        TilePane operationBtnTile = new TilePane();
        //operationBtnTile.setAlignment(Pos.CENTER_LEFT);
        //operationBtnTile.setPrefColumns(6);
        operationBtnTile.setVgap(10);
        operationBtnTile.setHgap(10);

      
        operationBtnTile.getChildren().add(btnDelete);
        operationBtnTile.getChildren().add(btndel);
        operationBtnTile.getChildren().add(btnCheck);
        

        return operationBtnTile;



    }

    //計算總金額
    public void checkTotal() {
        double total = 0;
        //將所有的訂單顯示在表格   計算總金額
        for (OrderDetail od : order_list) {
            //加總
            total += od.getProduct_price() * od.getQuantity();
        }

        //顯示總金額於display
        String totalmsg = String.format("%s %d元\n", "總金額:", Math.round(total));
        display.setText(totalmsg);
    }

    //加入購物車 檢查是否有重複的飲料
    public void addToCart(String item_id) {

        boolean duplication = false;
        for (int i = 0; i < order_list.size(); i++) {
            if (order_list.get(i).getProduct_id().equals(item_id)) {
                int qty = order_list.get(i).getQuantity() + 1; //拿出qty並+1
                order_list.get(i).setQuantity(qty);
                duplication = true;
                table.refresh();
                checkTotal();
                System.out.println(item_id + "此筆已經加入購物車，數量+1");
                break;
            }
        }
        if (!duplication) { //若是新項目則新增這一筆
            OrderDetail new_ord = new OrderDetail(
                    item_id,
                    product_dict.get(item_id).getName(),
                    product_dict.get(item_id).getPrice(),
                    1);
            order_list.add(new_ord);
            System.out.println(item_id);

            checkTotal(); //更新數量
        }
    }

    //根容器 所有的元件都放在裡面container VBox
    //取得跟容器的方法
    public HBox get_root_pane() {
        //根容器 所有的元件都放在裡面container，
        //最後再放進布景中scene，布景再放進舞台中stage

        HBox root1=new HBox();
        VBox root2=new VBox();
        VBox root3=new VBox();
        HBox root = new HBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getStylesheets().add("/css/bootstrap3.css");
         
        root1.setPrefSize(200, 1000);
        root1.getStylesheets().add("/css/bootstrap3.css");
        root2.setPrefSize(800, 1000);
        root2.setSpacing(10);
        root2.setPadding(new Insets(10, 10, 10, 10));
        root2.getStylesheets().add("/css/bootstrap3.css");
        root3.setPrefSize(550, 1000);
         root3.setSpacing(10);
        root3.setPadding(new Insets(10, 10, 10, 10));
        root3.getStylesheets().add("/css/bootstrap3.css");


     

        //塞入菜單選擇區塊
        root1.getChildren().add(getMenuSelectionContainer());
        

        //取得菜單磁磚窗格並放入根容器
        // 塞入菜單區塊 預設為果汁類
        menuContainerPane.getChildren().add(two_k);
        root2.getChildren().add(menuContainerPane);
        

        //塞入增加表格刪除項目操作之容器
        root3.getChildren().add(getOrderOperationContainer());
        

        //塞入表格
        initializeOrderTable(); //表格要初始化
        root3.getChildren().add(table);
        //塞入白板display
        display.setPrefColumnCount(10);
        display.setStyle("-fx-font-size: 24px;"); 
        root3.getChildren().add(display);
        
        root.getChildren().addAll(root1,root2,root3);

        return root;
    }

    @Override
    public void start(Stage stage) throws Exception {

        //取得 root pane
        HBox root = get_root_pane();
        //塞入布景
        Scene scene = new Scene(root,1550,900);
        scene.getStylesheets().add("/css/bootstrap3.css");
String backgroundColor = "#faf325"; // 設置您希望的顏色（這是黃色）
    scene.setFill(Color.web(backgroundColor));
        stage.setTitle("只能賣刮刮樂的台灣彩券POS機");
        stage.setScene(scene);
        stage.show();
        System.out.println("執行start()");
    }

    public static void main(String[] args) {
        launch(args);
        //System.out.println("執行main()的launch()之後");
    }

}
