package airport.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import airport.model.*;
import java.util.Iterator;
import javafx.geometry.Insets;

public class LandingQueueWindow {
    
    public LandingQueueWindow(Stage mainStage, AirportSimulator parent) {
        StackPane secondaryLayout = new StackPane();
        
        Scene secondScene = new Scene(secondaryLayout, 230, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Landing Queue");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(mainStage.getX() + 500);
        newWindow.setY(mainStage.getY() + 200);
        newWindow.setMinHeight(400);
        
        LandingList landing = parent.getLandingList();
        
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(20, 20, 20, 20));
   
        
        Iterator<Airplane> iter = landing.iterator();
        while(iter.hasNext()) {
            Airplane a = iter.next();
            Label fn = new Label(a.getFlightNumber());
            vbox.getChildren().add(fn);
        }
        
        secondaryLayout.getChildren().add(vbox);
        
       
        newWindow.show();
        
        newWindow.setOnCloseRequest(e -> {
            parent.landingQueueWindowClosed();

            e.consume();
            newWindow.close();

        });
    }

    
}
