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
import com.sun.javafx.tk.ImageLoader;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;


public class AirportSimulator extends Application {

    private Stage mainStage;
    private LandingRequestWindow landReqWindow;
    private LandingQueueWindow landQueueWindow;
    private LandingList landingList;
    private TerminalsList terminalList;
    private Group group;
    
    @Override
    public void start(Stage stage) {
        mainStage = stage;
        landReqWindow = null;
        landQueueWindow = null;
        
        landingList = new LandingList();
        terminalList = new TerminalsList();
        
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
    
        Pane canvas = new Pane();
        group = new Group();
        canvas.getChildren().add(group);

        root.setCenter(canvas);
        
        BackgroundImage myBI= new BackgroundImage(new Image("airport.jpg",1000,600,false,true),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
        canvas.setBackground(new Background(myBI));
        
        Scene scene = new Scene(root, 1000, 600, Color.GHOSTWHITE);
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
            TerminalPosition terminal = terminalList.getEmptyTerminal();
            
            if(terminal != null && landingList.size() > 0) {
                Airplane plane = landingList.pop();
            
                Path path = new Path();
                path.getElements().add(new MoveTo(10,120));
                path.getElements().add(new LineTo(910, 120));
                path.getElements().add(new CubicCurveTo(910, 120, 980, 240, 910, 240));
                path.getElements().add(new LineTo(terminal.getPosX(), terminal.getPosY()));
                path.setOpacity(0.3);

                ImageView selectedImage = new ImageView();   
                //Image image1 = new Image("file:a.jpg");
                Image image1 = new Image(AirportSimulator.class.getResourceAsStream("a.jpg"));


                selectedImage.setImage(image1);

                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.seconds(8.0));
                pathTransition.setDelay(Duration.seconds(.5));
                pathTransition.setPath(path);
                pathTransition.setNode(selectedImage);
                pathTransition.setOrientation(PathTransition.OrientationType.NONE);
                pathTransition.setCycleCount(0);
                pathTransition.setAutoReverse(false);

                group.getChildren().addAll(selectedImage);
                group.getChildren().add(path);
                
                terminal.setEmpty(false);

                ParallelTransition parallelTransition =
                 new ParallelTransition(pathTransition);
                // selectedImage.setRotate(selectedImage.getRotate() + 45);
                parallelTransition.play();  
                
                group.getChildren().remove(path);
            }
        }
        
    }
    
    class TakeOffAction implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            System.out.println("TakeOff clicked");
        }
        
    }
    
}
