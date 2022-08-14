package edu.lawrence.scheduling;

import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Room {
    private Rectangle rect;
    private int number;
    private ArrayList<Reservation> reservations;
    
    public Room(int roomNumber) {
        this.number = roomNumber;
        this.reservations = new ArrayList<Reservation>();
        
        rect = new Rectangle(Reservation.SPACING,roomNumber*Reservation.SPACING,
                            (23-7)*Reservation.SPACING,Reservation.SPACING);
        rect.setStroke(Color.BLACK);
        rect.setFill(Color.WHITE);
    }
    
    public Shape getShape() { return rect; }
    
    public void claimReservation(Reservation r) {
        reservations.add(r);
        r.setY(rect.getY());
    }
    public void reclaimReservation(Reservation r) {
        reservations.remove(r);
    }
    
    
    // To do: add methods needed for dragging operations
    public boolean containsPoint(double x,double y) { return rect.contains(x, y); }
    public int getRoom(){
        return number;
    }
    public boolean containsReservation(double x, double y){
        for(Reservation r: reservations){
            if(r.containsPoint(x, y))
                return true;
        }
        return false;   
    }
    public void saveReservations(PrintWriter writer) {
        for(Reservation r : reservations) {
            r.save(writer,number);
        }
    }
}
