package forReference;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


//https://www.tutorialspoint.com/how-to-create-a-tabpane-in-javafx
//https://jenkov.com/tutorials/javafx/tabpane.html
//https://www.geeksforgeeks.org/javafx-tabpane-class/
//https://www.geeksforgeeks.org/javafx-menubar-and-menu/

public class MenuAndTabPaneExample extends Application {

    public void start(Stage stage) {


        //
        TabPane tabPane = new TabPane();

        Tab tab_customerOrderEntry = new Tab("客戶交易輸入", new Button("OK"));
        Tab tab_productMaintenance = new Tab("產品新增刪除維護", new Button("OK2"));
        //Tab tab_productMaintenance = new Tab("產品新增刪除維護", new Label("產品新增刪除維護"));
        Tab tab_dailyOrderAnalysis = new Tab("每日訂單分析", new Label("訂單分析"));

        tabPane.getTabs().add(tab_customerOrderEntry);
        tabPane.getTabs().add(tab_productMaintenance);
        tabPane.getTabs().add(tab_dailyOrderAnalysis);

        // create a menu
        Menu menu = new Menu("選擇功能");
        Menu menuAbout = new Menu("關於");

        // create menuitems
        MenuItem menuitem_customerOrderEntry = new MenuItem("客戶交易輸入");
        MenuItem menuitem_productMaintenance = new MenuItem("產品新增刪除維護");
        MenuItem menuitem_dailyOrderAnalysis = new MenuItem("每日訂單分析");

        // add menu items to menu
        menu.getItems().add(menuitem_customerOrderEntry);
        menu.getItems().add(menuitem_productMaintenance);
        menu.getItems().add(menuitem_dailyOrderAnalysis);

        EventHandler<ActionEvent> my_envent_forMenuItem2 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        }; 
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
                }
                //印出目前的tab名稱觀察看看
                System.out.println(tabPane.getTabs());
            }
        }; //  注意這裡要有一個分號!!

//        menuitem_customerOrderEntry.setOnAction( new EventHandler<ActionEvent>()   {
//                public void handle(ActionEvent e) {
//        }
//        } );
        // add envent
        menuitem_customerOrderEntry.setOnAction(envent_forMenuItem);
        menuitem_productMaintenance.setOnAction(envent_forMenuItem);
        menuitem_dailyOrderAnalysis.setOnAction(envent_forMenuItem);

        // create a menubar
        MenuBar menubar = new MenuBar();

        // add menu to menubar
        menubar.getMenus().add(menu);
        menubar.getMenus().add(menuAbout);

        // create a VBox as root
        VBox root = new VBox();
        root.getChildren().add(menubar);
        root.getChildren().add(tabPane);

        Scene scene = new Scene(root, 500, 300);

        stage.setScene(scene);
        stage.setTitle("高科飲品POS系統");

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
