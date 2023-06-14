/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab9_about;

import java.util.TreeMap;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.ReadCategoryProductFromDB;
import javafx.application.Application;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Project_Introduction extends Application {

public VBox get_root_pane() {
        
        Label label5 = new Label("專案介紹");
        
        label5.setPrefSize(300, 100);
        label5.setAlignment(Pos.CENTER);
        label5.setStyle("-fx-font-weight: bold;");
        label5.setStyle("-fx-font-size:48px;");
        label5.setTextAlignment(TextAlignment.CENTER);
        label5.getStyleClass().setAll("label", "lb3");
        
        
        TextArea display = new TextArea();      
        display.appendText("注意事項：\n" +
                        "1.請先將C111118128_Lottery_PosDB內的”Lottery_PosDB_用資料庫sql檔(SQL匯出).sql”於SQL內執行，完成資料庫的建置。\n" +
                        "2. port：3306\n" +
                        "3. USER = \"mis\";\n" +
                        "  PWD = \"mis123\";\n" +
                        "\n" +
                        "特色：\n" +
                        "1.這個pos系統能賣多種刮刮樂，從100的到2000的都有許多種類。\n" +
                        "2.可讀取SQL資料庫來導入商品資訊\n" +
                        "3.可於程式內修改SQL資料庫的商品資訊\n" +
                        "4.有結帳功能及一鍵刪除列表功能\n" +
                        "5.採用橫式畫面設計，更能支援pos機、電腦螢幕及平板。\n" +
                        "6.加大圖片、按鈕及文字，點擊更便利也防止眼花。\n" +
                        "7.連結.css採用台彩特色配色\n" +
                        "");        
        display.setPrefSize(1450, 600);
        display.setStyle("-fx-font-size:30px;");
        display.setEditable(false);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(label5);
        root.getChildren().add(display);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getStylesheets().add("/css/bootstrap3.css");
        return root;
    }

    @Override
    public void start(Stage stage) {
        VBox root = get_root_pane();
        Scene scene = new Scene(root,1550,800);
        stage.setTitle("圖片來源");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
