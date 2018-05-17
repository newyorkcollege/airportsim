package airport.model;

import java.util.Iterator;
import java.util.LinkedList;


public class TerminalsList extends LinkedList<TerminalPosition>{
    
    public TerminalsList() {
        add(new TerminalPosition(500,430));
        add(new TerminalPosition(530,430));
    }
    
    public TerminalPosition getEmptyTerminal() {
        Iterator<TerminalPosition> it = this.iterator();
        while(it.hasNext()) {
            TerminalPosition p = it.next();
            if(p.isEmpty()) {
                return p;
            }
        }
        return null;
    }
}
