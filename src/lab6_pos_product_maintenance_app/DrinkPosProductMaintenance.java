package lab6_pos_product_maintenance_app;

import java.util.ArrayList;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import models.Order;
import models.OrderDAO;
import models.OrderDetail;
import models.Product;
import models.ProductDAO;
import javafx.geometry.Orientation;
import models.ReadCategoryProductFromDB;
import javafx.geometry.Orientation;


public class DrinkPosProductMaintenance extends Application {

    //***********產生資料DAO來使用
    private ProductDAO productDao = new ProductDAO();

    //ObservableList    product_list有新增或刪除都會處動table的更新，也就是發生任何改變時都被通知
    //先放入一個空的ArrayList
    private ObservableList<Product> product_list = FXCollections.observableList(new ArrayList());

    //顯示產品內容表格，大家都會用到，全域變數。實例變數
    private TableView table;

    //顯示訂單詳情白板
    private TextArea display = new TextArea();

    //一個窗格(用磁磚窗格最方便)置放產品過濾與選擇按鈕，置放於主視窗的最上方區域。
    private TilePane getProductCategoryContainer() {

        //定義檢索全部按鈕
        Button btnQueryAll = new Button("瀏覽全部");
        btnQueryAll.setPrefSize(170, 100);
        btnQueryAll.setMaxHeight(100);
        btnQueryAll.setMinHeight(100);
        btnQueryAll.setStyle("-fx-font-size: 30px;"); 
        btnQueryAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ObservableList    order_list有新增或刪除都會處動table的更新，也就是發生任何改變時都被通知
                product_list = FXCollections.observableList(productDao.getAllProducts());
                table.setItems(product_list);
                System.out.println(product_list);
            }
        });
        //定義"2000"按鈕
        Button btn2k = new Button("2000");
        btn2k.setPrefSize(170, 100);
        btn2k.setMaxHeight(100);
        btn2k.setMinHeight(100);
        btn2k.setStyle("-fx-font-size: 30px;");
        btn2k.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ObservableList    order_list有新增或刪除都會處動table的更新，也就是發生任何改變時都被通知
                product_list = FXCollections.observableList(productDao.findByCate("2k"));
                table.setItems(product_list);
                System.out.println(product_list);
            }
        });
        //定義"1000"按鈕
        Button btn1k = new Button("1000");
        btn1k.setPrefSize(170, 100);
        btn1k.setMaxHeight(100);
        btn1k.setMinHeight(100);
        btn1k.setStyle("-fx-font-size: 30px;");
        btn1k.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                product_list = FXCollections.observableList(productDao.findByCate("1k"));
                //表格內置放的資料來自於product_list，依據置放順序顯示
                table.setItems(product_list);
                System.out.println(product_list);
            }
        });
        //定義"500"按鈕
        Button btn500 = new Button("500");
        btn500.setPrefSize(170, 100);
        btn500.setMaxHeight(100);
        btn500.setMinHeight(100);
        btn500.setStyle("-fx-font-size: 30px;");
        btn500.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                product_list = FXCollections.observableList(productDao.findByCate("500"));
                table.setItems(product_list);
                System.out.println(product_list);
            }
        });
        //定義"200"按鈕
        Button btn200 = new Button("200");
        btn200.setPrefSize(170, 100);
        btn200.setMaxHeight(100);
        btn200.setMinHeight(100);
        btn200.setStyle("-fx-font-size: 30px;");
        btn200.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                product_list = FXCollections.observableList(productDao.findByCate("200"));
                table.setItems(product_list);
                System.out.println(product_list);
            }
        });
        
        //定義"100"按鈕
        Button btn100 = new Button("100");
        btn100.setPrefSize(170, 100);
        btn100.setMaxHeight(100);
        btn100.setMinHeight(100);
        btn100.setStyle("-fx-font-size: 30px;");
        btn100.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                product_list = FXCollections.observableList(productDao.findByCate("100"));
                table.setItems(product_list);
                System.out.println(product_list);
            }
        });

        //使用容器視窗放置前面數個按鈕
        TilePane conntainerProductCategory = new TilePane();
        //conntainerProductCategory.setStyle("-fx-background-color: #D7FFEE;");
        conntainerProductCategory.getStylesheets().add("css/myButton.css");
        conntainerProductCategory.setVgap(10);
        conntainerProductCategory.setHgap(10);
        //設定TilePane為垂直排列
        conntainerProductCategory.setOrientation(Orientation.VERTICAL);
        conntainerProductCategory.getChildren().add(btnQueryAll);
        conntainerProductCategory.getChildren().add(btn2k);
        conntainerProductCategory.getChildren().add(btn1k);
        conntainerProductCategory.getChildren().add(btn500);
        conntainerProductCategory.getChildren().add(btn200);
        conntainerProductCategory.getChildren().add(btn100);
        
        return conntainerProductCategory;
    }

    //一個窗格(用磁磚窗格最方便)置放產品過濾與選擇按鈕，置放於主視窗的最上方區域。
    private HBox getProductSelectionContainer() {

        //搜尋某一筆
        Button btnFindById = new Button("搜尋某一筆");
        btnFindById.setPrefSize(170, 100);
        btnFindById.setMaxHeight(100);
        btnFindById.setMinHeight(100);
        btnFindById.setStyle("-fx-font-size: 24px;");
        btnFindById.getStyleClass().setAll("button", "info");
        TextField productIdField = new TextField("p-2k-101");
        productIdField.setPrefSize(200, 100);
        productIdField.setMaxHeight(100);
        productIdField.setMinHeight(100);
        productIdField.setStyle("-fx-font-size: 30px;");
        btnFindById.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Product prod = productDao.findById(productIdField.getText());
                product_list.clear();
                product_list.add(prod);
                //table.setItems(product_list);
                System.out.println(product_list);
            }
        });
        //搜尋某一筆
        Button btnFindByName = new Button("搜尋名稱");
         btnFindByName.setPrefSize(170, 100);
        btnFindByName.setMaxHeight(100);
        btnFindByName.setMinHeight(100);
        btnFindByName.setStyle("-fx-font-size: 24px;");
        btnFindByName.getStyleClass().setAll("button", "info");
        TextField nameField = new TextField("2000");
        nameField.setPrefSize(300, 100);
        nameField.setMaxHeight(100);
        nameField.setMinHeight(100);
        nameField.setStyle("-fx-font-size: 30px;");
        btnFindByName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                product_list = FXCollections.observableList(productDao.findByNameContaining(nameField.getText()));
                //table內容須設定為新的product_list, 因為product_list是新的
                table.setItems(product_list);
                System.out.println(product_list);
            }
        });

        //使用容器放置前面數個按鈕
        HBox conntainerProductSelection = new HBox();
        conntainerProductSelection.setAlignment(Pos.CENTER);
        conntainerProductSelection.setStyle("-fx-background-color: #D7FFEE;");
        conntainerProductSelection.setSpacing(10);
        conntainerProductSelection.getChildren().addAll(btnFindById, productIdField);
        conntainerProductSelection.getChildren().addAll(btnFindByName, nameField);
        
        return conntainerProductSelection;
    }

    //初始化產品表格
    private void initializeProductTable() {

        //表格初始化
        table = new TableView();
        table.setEditable(true); //表格允許修改
        table.setPrefHeight(500);
        table.setStyle("-fx-font-size: 16px;");
        //表格最後一欄是空白，不要顯示!
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //定義數個欄位，用迴圈比較厲害
        TableColumn col;
        String[] columnNames = {"product_id", "category", "name", "price", "photo", "description"};
        String[] columnChineseNames = {"產品編號", "類別", "名稱", "價格", "照片連結", "產品描述"};
        for (int i = 0; i < columnNames.length; i++) {
            //價格欄位是整數，需做特別處理:將字串轉換為整數
            if (columnNames[i].equals("price")) {
                //將欄位Header表頭名稱定義"產品編號", "類別"等
                col = new TableColumn<Product, Integer>(columnChineseNames[i]);
                //col.setMinWidth(80);
                //將欄位內容名稱定義為"product_id", "category"等，
                //此名稱必須與Product物件的欄位變數一樣(不然會找不到對應)
                col.setCellValueFactory(new PropertyValueFactory<>(columnNames[i]));
                //欄位內容將字串轉換為整數
                col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                
            } else {
                col = new TableColumn<Product, String>(columnChineseNames[i]);
                //col.setMinWidth(80);
                col.setCellValueFactory(new PropertyValueFactory<>(columnNames[i]));
                col.setCellFactory(TextFieldTableCell.forTableColumn()); //
            }

            //定義數量欄位被修改後進行那些動作，EventHandler事件
            col.setOnEditCommit(new EventHandler<CellEditEvent>() {
                @Override
                public void handle(CellEditEvent event) {
                    
                    int row_num = event.getTablePosition().getRow();//哪一筆row被修改
                    int col_num = event.getTablePosition().getColumn();//哪一筆column被修改

                    //int new_val = event.getNewValue(); //改成甚麼數值 需要將物件轉為整數
                    Product target = (Product) event.getTableView().getItems().get(row_num); //取得該筆果汁傳參考呼叫
                    System.out.println(target);
                    //修改成新的數值 該筆產品物件存放於product_list
                    //看col_num就知道修改的是價格還是類別?
                    switch (col_num) {
                        case 0:
                            //改成甚麼 event物件傳過來修改的值是一般物件格式Object，必須視情況轉為String或Integer
                            target.setProduct_id((String) event.getNewValue());
                            break;
                        case 1:
                            target.setCategory((String) event.getNewValue());
                            break;
                        case 2:
                            target.setName((String) event.getNewValue());
                            break;
                        case 3:
                            //價格
                            //改成甚麼數值 需要將物件轉為整數
                            target.setPrice((Integer) event.getNewValue());
                            break;
                        case 4:
                            target.setPhoto((String) event.getNewValue());
                            break;
                        case 5:
                            target.setDescription((String) event.getNewValue());
                            break;
                    }
                }
            }); //setOnEditCommit

            table.getColumns().add(col);
        } // for loop

        //表格內置放的資料來自於product_list，依據置放順序顯示產品
        table.setItems(product_list);
    }

    //表格新增項目刪除項目之操作區塊
    private VBox getProductOperationContainer() {

        //定義新增一筆按鈕
        Button btnBlank = new Button("新增空白一筆");
        btnBlank.setPrefSize(300, 100);
        btnBlank.setMaxHeight(100);
        btnBlank.setMinHeight(100);
        btnBlank.setStyle("-fx-font-size: 30px;");

        btnBlank.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //新增一筆範例
                product_list.add(new Product("p-2k-101", "2k", "2000的刮刮樂", 2000, "照片檔名", "描述說明"));
                
            }
        });

        //定義新增這一筆到資料庫按鈕
        Button btnInsert = new Button("新增這一筆到資料庫");
        btnInsert.setWrapText(true);
        btnInsert.setPrefSize(300, 100);
        btnInsert.setMaxHeight(100);
        btnInsert.setMinHeight(100);
        btnInsert.setStyle("-fx-font-size: 28px;");
        btnInsert.getStyleClass().setAll("button", "danger");
        btnInsert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //從table中取得目前被選到的項目，並將其轉為Product物件，再送到資料庫
                Product prod = (Product) table.getSelectionModel().getSelectedItem();
                productDao.insert(prod);
            }
        });

        //定義更新修改的這一筆
        Button btnUpdate = new Button("更新修改的這一筆");
        btnUpdate.setWrapText(true);
        btnUpdate.setPrefSize(300, 100);
        btnUpdate.setMaxHeight(100);
        btnUpdate.setMinHeight(100);
        btnUpdate.setStyle("-fx-font-size: 30px;");
        btnUpdate.getStyleClass().setAll("button", "danger");
        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Product prod = (Product) table.getSelectionModel().getSelectedItem();
                System.out.println(prod);
                productDao.update(prod);
            }
        });
        //定義刪除一筆按鈕
        Button btnDelete = new Button("刪除這一筆");
        btnDelete.setPrefSize(300, 100);
        btnDelete.setMaxHeight(100);
        btnDelete.setMinHeight(100);
        btnDelete.setStyle("-fx-font-size: 30px;");
        btnDelete.getStyleClass().setAll("button", "danger");
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //從table中取得目前被選到的項目
                Product selectedItem = (Product) table.getSelectionModel().getSelectedItem();
                //從表格table中或product_list刪除這一筆
                //table.getItems().remove(selectedItem);
                product_list.remove(selectedItem);

                //從資料庫刪除這一筆
                productDao.delete(selectedItem.getProduct_id());
            }
        });

        //放置前述任務功能按鈕
        VBox containerProductOperation = new VBox();
        
        containerProductOperation.setStyle("-fx-background-color: #D2E9FF;");
        containerProductOperation.setSpacing(10);
        
        containerProductOperation.getChildren().add(btnBlank);
        containerProductOperation.getChildren().add(btnInsert);
        containerProductOperation.getChildren().add(btnUpdate);
        containerProductOperation.getChildren().add(btnDelete);
        
        return containerProductOperation;
    }

    //所有元件與事件並將所有元件放入root
    public HBox get_root_pane() {
        //根容器 所有的元件都放在裡面container，
        //最後再放進布景中scene，布景再放進舞台中stage
        HBox root = new HBox();
        VBox root2=new  VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getStylesheets().add("/css/bootstrap3.css");
        //root.setStyle("-fx-background-color: #EFFFD7;"); 
        root2.setSpacing(10);
        root2.setPadding(new Insets(10, 10, 10, 10));
        root2.getStylesheets().add("/css/bootstrap3.css");

        //塞入產品類別過濾區塊
        TilePane productCategoryTile = getProductCategoryContainer();
        root.getChildren().add(productCategoryTile);
        
        //塞入增加表格刪除項目操作之容器
        root.getChildren().add(getProductOperationContainer());

        //塞入產品選擇區塊
        HBox productSelectionTile = getProductSelectionContainer();
        root2.getChildren().add(productSelectionTile);
        

        

        //塞入表格
        initializeProductTable(); //表格初始化
        root2.getChildren().add(table);
        //塞入白板display
        //display.setPrefColumnCount(10);
        display.setPrefSize(400, 200);
        display.setMaxHeight(200);
        display.setMinHeight(200);
        display.setStyle("-fx-font-size: 30px;");
        display.setPromptText("空白...");
        display.setPrefRowCount(3);
        root2.getChildren().add(display);
        root.getChildren().add(root2);
        
        
        return root;
    }
    
    @Override
    public void start(Stage stage) throws Exception {

        // 取得root pane
        HBox root = get_root_pane();
        //塞入布景
        Scene scene = new Scene(root,1550,800);
        stage.setTitle("產品資料維護");
        stage.setScene(scene);
        stage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
