package lab7_pos_integration_menubar;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lab5_pos_order_entry_app_db.LotteryPosOrderDBApp;
import lab6_pos_product_maintenance_app.LotteryPosProductMaintenance;
import javafx.geometry.Orientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lab8_order_analysis.LotteryPosOrderAnalysis;
import lab9_about.Image_Source;
import lab9_about.Project_Author;
import lab9_about.Project_Introduction;


//https://www.tutorialspoint.com/how-to-create-a-tabpane-in-javafx
//https://jenkov.com/tutorials/javafx/tabpane.html
//https://www.geeksforgeeks.org/javafx-tabpane-class/
//https://www.geeksforgeeks.org/javafx-menubar-and-menu/
public class LotteryPosTabPaneMenu extends Application {

    public void start(Stage stage) {

        HBox order_system = new LotteryPosOrderDBApp().get_root_pane();
        HBox product_maintenance = new LotteryPosProductMaintenance().get_root_pane();
        HBox analysis =new LotteryPosOrderAnalysis().get_root_pane();
        VBox image_source = new Image_Source().get_root_pane();
        HBox project_author=new Project_Author().get_root_pane();
        VBox project_introduction = new Project_Introduction().get_root_pane();
        
        order_system.getStylesheets().add("/css/myVBox_HBox.css");
        product_maintenance.getStylesheets().add("/css/myVBox_HBox.css");
        analysis.getStylesheets().add("/css/myVBox_HBox.css");
        
        image_source.getStylesheets().add("/css/myVBox_HBox.css");
        project_author.getStylesheets().add("/css/myVBox_HBox.css");
        project_introduction.getStylesheets().add("/css/myVBox_HBox.css");
                

        //
        TabPane tabPane = new TabPane();

        Tab tab_customerOrderEntry = new Tab("客戶交易輸入", order_system);
        
        Tab tab_productMaintenance = new Tab("產品新增刪除維護", product_maintenance);
        //Tab tab_productMaintenance = new Tab("產品新增刪除維護", new Label("產品新增刪除維護"));
        Tab tab_dailyOrderAnalysis = new Tab("每日訂單分析", analysis);
        
        
        Tab tab_ImageSource = new Tab("圖片來源", image_source);
        
        Tab tab_ProjectAuthor = new Tab("專案作者", project_author);
        
        Tab tab_ProjectIntroduction = new Tab("專案簡介", project_introduction);

        tabPane.getTabs().add(tab_customerOrderEntry);
       

        //tabPane.getTabs().add(tab_productMaintenance);
        //tabPane.getTabs().add(tab_dailyOrderAnalysis);

        // create a menu
        Menu menu = new Menu("選擇功能");
        Menu menuAbout = new Menu("關於");

        // create menuitems
        MenuItem menuitem_customerOrderEntry = new MenuItem("客戶交易輸入");
        MenuItem menuitem_productMaintenance = new MenuItem("產品新增刪除維護");
        MenuItem menuitem_dailyOrderAnalysis = new MenuItem("每日訂單分析");
        
        MenuItem menuitem_imageSource = new MenuItem("圖片來源");
        MenuItem menuitem_projectAuthor = new MenuItem("專案作者");
        MenuItem menuitem_projectIntroduction = new MenuItem("專案簡介");
        

        // add menu items to menu
        menu.getItems().add(menuitem_customerOrderEntry);
        menu.getItems().add(menuitem_productMaintenance);
        menu.getItems().add(menuitem_dailyOrderAnalysis);
        
        // add menu items to menuAbout
        menuAbout.getItems().add(menuitem_imageSource);
        menuAbout.getItems().add(menuitem_projectAuthor);
        menuAbout.getItems().add(menuitem_projectIntroduction);

        // create events for menu items
        // action event
        EventHandler<ActionEvent> envent_forMenuItem = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                //取得使用者選擇哪一個選項
                MenuItem fromTab = (MenuItem) e.getSource();

                //判斷要tabPane顯示哪一個子App
                if (fromTab.equals(menuitem_customerOrderEntry)) {

                    if (!tabPane.getTabs().contains(tab_customerOrderEntry)) {
                        tabPane.getTabs().add(tab_customerOrderEntry);
                        tabPane.getSelectionModel().select(tab_customerOrderEntry); //作用在此tab
                    }
                } else if (fromTab.equals(menuitem_productMaintenance)) {

                    if (!tabPane.getTabs().contains(tab_productMaintenance)) {
                        tabPane.getTabs().add(tab_productMaintenance);
                        tabPane.getSelectionModel().select(tab_productMaintenance);
                    }

                } else if (fromTab.equals(menuitem_dailyOrderAnalysis)) {
                    if (!tabPane.getTabs().contains(tab_dailyOrderAnalysis)) {
                        tabPane.getTabs().add(tab_dailyOrderAnalysis);
                        tabPane.getSelectionModel().select(tab_dailyOrderAnalysis);
                    }
                }else if (fromTab.equals(menuitem_imageSource)) {
            if (!tabPane.getTabs().contains(tab_ImageSource)) {
                tabPane.getTabs().add(tab_ImageSource);
                tabPane.getSelectionModel().select(tab_ImageSource);
            }
        } else if (fromTab.equals(menuitem_projectAuthor)) {
            if (!tabPane.getTabs().contains(tab_ProjectAuthor)) {
                tabPane.getTabs().add(tab_ProjectAuthor);
                tabPane.getSelectionModel().select(tab_ProjectAuthor);
            }
        } else if (fromTab.equals(menuitem_projectIntroduction)) {
            if (!tabPane.getTabs().contains(tab_ProjectIntroduction)) {
                tabPane.getTabs().add(tab_ProjectIntroduction);
                tabPane.getSelectionModel().select(tab_ProjectIntroduction);
            }
        }
        //印出目前的tab名稱觀察看看
        System.out.println(tabPane.getTabs());
        }
        }; // 注意這裡要有一個分號!!

        
        // add envent
        menuitem_customerOrderEntry.setOnAction(envent_forMenuItem);
        menuitem_productMaintenance.setOnAction(envent_forMenuItem);
        menuitem_dailyOrderAnalysis.setOnAction(envent_forMenuItem);
        
        // add envent
        menuitem_imageSource.setOnAction(envent_forMenuItem);
        menuitem_projectAuthor.setOnAction(envent_forMenuItem);
        menuitem_projectIntroduction.setOnAction(envent_forMenuItem);

        // create a menubar
        MenuBar menubar = new MenuBar();

        // add menu to menubar
        menubar.getMenus().add(menu);
        menubar.getMenus().add(menuAbout);

        // create a VBox as root
        VBox root = new VBox();
        root.getChildren().add(menubar);
        root.getChildren().add(tabPane);
        

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/menustyle.css");
        stage.setScene(scene);
        // 在適當的地方加載您的圖片
       // 讀取圖片
        Image image = new Image(getClass().getResourceAsStream("/imgs/TL.png"));

        // 更改整個應用程序窗口的圖標
        stage.getIcons().add(image);

        stage.setTitle("只能賣刮刮樂的台灣彩卷POS系統");

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
