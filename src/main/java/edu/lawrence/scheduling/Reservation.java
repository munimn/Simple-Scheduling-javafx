package edu.lawrence.scheduling;

import java.io.PrintWriter;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Reservation {
    private Rectangle rect;
    private int id, start, end;
    public static final int SPACING = 40;
    
    public Reservation(int id,int start,int end) {
        this.id = id;
        this.start = start;
        this.end = end;
        rect = new Rectangle((start-7+1) * SPACING,0,(end-start)*SPACING,SPACING);
        rect.setFill(Color.RED);
    }
    
    public Shape getShape() { return rect; }
    
    public double getY() { return rect.getY(); }
    public void setY(double y) { rect.setY(y); }
    
    public boolean containsPoint(double x,double y) { return rect.contains(x, y); }
    
    public void save(PrintWriter writer,int roomNumber) {
        writer.println(id + " " + roomNumber + " " + start + " " + end);
    }
}
