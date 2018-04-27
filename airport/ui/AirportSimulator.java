package airport.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class AirportSimulator extends Application {


    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        root.setTop(menuBar);
        Menu fileMenu = new Menu("Operation");
        
        MenuItem landRequestItem = new MenuItem("Land request");
        landRequestItem.setOnAction(new LandRequestAction());
        
        MenuItem landItem = new MenuItem("Land");
        landItem.setOnAction(new LandAction());
        
        MenuItem takeOffItem = new MenuItem("Take Off");
        takeOffItem.setOnAction(new TakeOffAction());
        
        fileMenu.getItems().addAll(landRequestItem, landItem, takeOffItem);
        
        menuBar.getMenus().addAll(fileMenu);
    
        
        Group group = new Group();
        root.setCenter(group);
        Scene scene = new Scene(root, 700, 600, Color.GHOSTWHITE);
        stage.setScene(scene);
        stage.setTitle("Airport simulator");
        stage.show();
        
        
       
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    class LandRequestAction implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            System.out.println("Landing request clicked");
        }
        
    }
    
    class LandAction implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            System.out.println("Land clicked");
        }
        
    }
    
    class TakeOffAction implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            System.out.println("TakeOff clicked");
        }
        
    }
    
}
