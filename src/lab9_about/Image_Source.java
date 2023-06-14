
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
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Image_Source extends Application {

    public VBox get_root_pane() {
        
        Label label5 = new Label("圖片來源");
        
        label5.setPrefSize(300, 100);
        label5.setAlignment(Pos.CENTER);
        label5.setStyle("-fx-font-weight: bold;");
        label5.setStyle("-fx-font-size:48px;");
        label5.setTextAlignment(TextAlignment.CENTER);
        label5.getStyleClass().setAll("label", "lb3");
        
        
        TextArea display = new TextArea();

        try {
            File file = new File("src/source/source.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                display.appendText(scanner.nextLine() + "\n");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            display.setText("Error: Could not read file");
        }
        display.setPrefSize(1450, 600);
        display.setStyle("-fx-font-size:18px;");
        display.setEditable(false);

        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(label5);
        root.getChildren().add(display);
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