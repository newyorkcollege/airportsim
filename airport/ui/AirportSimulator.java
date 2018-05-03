package airport.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import airport.model.*;


public class AirportSimulator extends Application {

    private Stage mainStage;
    private LandingRequestWindow landReqWindow;
    private LandingQueueWindow landQueueWindow;
    private LandingList landingList;
    
    @Override
    public void start(Stage stage) {
        mainStage = stage;
        landReqWindow = null;
        landQueueWindow = null;
        
        landingList = new LandingList();
        
        BorderPane root = new BorderPane();
        
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        root.setTop(menuBar);
        Menu fileMenu = new Menu("Operation");
        
        MenuItem landRequestItem = new MenuItem("Land request");
        landRequestItem.setOnAction(new LandRequestAction());
        
        MenuItem landQItem = new MenuItem("Landing Queue");
        landQItem.setOnAction(new LandQueueAction());
        
        MenuItem landItem = new MenuItem("Land");
        landItem.setOnAction(new LandAction());
        
        MenuItem takeOffItem = new MenuItem("Take Off");
        takeOffItem.setOnAction(new TakeOffAction());
        
        fileMenu.getItems().addAll(landRequestItem, landQItem, landItem, takeOffItem);
        
        menuBar.getMenus().addAll(fileMenu);
    
        
        Group group = new Group();
        root.setCenter(group);
        Scene scene = new Scene(root, 700, 600, Color.GHOSTWHITE);
        stage.setScene(scene);
        stage.setTitle("Airport simulator");
        stage.show();
        
        
       
    }

    public LandingList getLandingList() {
        return landingList;
    }
    
    
    
    public void landingRequestWindowClosed() {
        landReqWindow = null;
    }
    
    public void landingQueueWindowClosed() {
        landQueueWindow = null;
    }
    
    public void addFlight(String flightNo, String planeType) {
        Airplane plane = null;
        if(planeType == "Airbus320") {
            plane = new Airbus320();
        }
        else if(planeType == "Boeing747") {
            plane = new Boeing747();
        }
        
        plane.setFlightNumber(flightNo);
        
        landingList.add(plane);
        System.out.println("Flight added");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    class LandRequestAction implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            System.out.println("Landing request clicked");
            if(landReqWindow == null) {
                landReqWindow = new LandingRequestWindow(mainStage, AirportSimulator.this);
            }
        }
        
    }
    
    class LandQueueAction implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            landQueueWindow = new LandingQueueWindow(mainStage, AirportSimulator.this);
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
