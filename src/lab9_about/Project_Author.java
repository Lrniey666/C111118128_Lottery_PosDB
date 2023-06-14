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

public class Project_Author extends Application {
    
public HBox get_root_pane(){
        Label label4 = new Label("專案製作者：\n張任沂");
        label4.setPrefSize(450, 250);
        label4.setAlignment(Pos.CENTER);
        label4.setStyle("-fx-font-weight: bold;");
        label4.setStyle("-fx-font-size:48px;");
        label4.setTextAlignment(TextAlignment.CENTER);
        label4.getStyleClass().setAll("label", "lb3");
        
        Label label6 = new Label("project maker：\nCHANG JEN I");
        label6.setPrefSize(450, 250);
        label6.setAlignment(Pos.CENTER);
        label6.setStyle("-fx-font-weight: bold;");
        label6.setStyle("-fx-font-size:48px;");
        label6.setTextAlignment(TextAlignment.CENTER);
        label6.getStyleClass().setAll("label", "lb4");
        
        HBox root=new HBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(label4);
        root.getChildren().add(label6);
        root.getStylesheets().add("/css/bootstrap3.css");      
        return root;    
    }

    @Override
    public void start(Stage stage) {
    // 取得root pane
        HBox root = get_root_pane();
        
        //塞入布景
        Scene scene = new Scene(root,1550,800);
        stage.setTitle("訂單分析");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
