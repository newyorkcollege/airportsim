package airport.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import airport.model.Airplane;

public class AirplaneImageView extends ImageView {
    
    protected Airplane plane;
    
    public AirplaneImageView(Airplane p) {
        plane = p;
    }

    public Airplane getPlane() {
        return plane;
    }
    
    
}

