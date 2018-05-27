package airport.model;

import javafx.scene.image.ImageView;


public class Airplane {
    
    protected String flightNumber;
    protected boolean hasEmergency;
    protected String emergency;
    protected TerminalPosition terminal;
    protected ImageView image;

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public TerminalPosition getTerminal() {
        return terminal;
    }

    public void setTerminal(TerminalPosition terminal) {
        this.terminal = terminal;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
    
    
}


