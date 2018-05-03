package airport.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LandingRequestWindow {
    
    private TextField flightNo;
    private ComboBox planeType;
    
    public LandingRequestWindow(Stage mainStage, AirportSimulator parent) {
        StackPane secondaryLayout = new StackPane();
        
        Scene secondScene = new Scene(secondaryLayout, 230, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(mainStage.getX() + 500);
        newWindow.setY(mainStage.getY());
        newWindow.setMinHeight(230);
        
        Label label1 = new Label("Flignt:");
        flightNo = new TextField ();
        VBox vbox = new VBox();
        vbox.setSpacing(8);
        vbox.setPadding(new Insets(10, 50, 50, 50));
        vbox.getChildren().addAll(label1, flightNo);
        secondaryLayout.getChildren().add(vbox);
        
        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(flightNo.getText().length() == 0 || 
                        planeType.getValue().toString().length() == 0) {
                    System.out.println("Wrong data");
                }
                else {
                    parent.addFlight(flightNo.getText(), 
                            planeType.getValue().toString());
                    parent.landingRequestWindowClosed();
                    newWindow.close();
                }
            }
        });
        
        Label label2 = new Label("Type:");
        planeType = new ComboBox();
        planeType.getItems().addAll(
            "Airbus320",
            "Boeing747"
        );
        vbox.getChildren().addAll(label2, planeType);
        vbox.getChildren().addAll(submit);
        
        newWindow.show();
        
        newWindow.setOnCloseRequest(e -> {
            parent.landingRequestWindowClosed();
            System.out.println(flightNo.getText());
            e.consume();
            newWindow.close();

        });
    }

}
