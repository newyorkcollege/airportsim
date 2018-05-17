package airport.model;

public class TerminalPosition {
    
    private int posX;
    private int posY;
    private boolean empty;
    
    public TerminalPosition(int x, int y) {
        posX = x;
        posY = y;
        empty = true;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    
    
    
}
