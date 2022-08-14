package edu.lawrence.scheduling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SchedulePane extends Pane {
    Room[] rooms;
    private ArrayList<Reservation> reservations;
    private int nextReservation;
    private int RoomNumber;
    private Reservation dragging;
    
    // To do: add member variables here needed for dragging reservations
    
    public SchedulePane() {
        rooms = new Room[5];
        reservations = new ArrayList<Reservation>();
        for(int n = 0;n < 5;n++) {
            rooms[n] = new Room(n+1);
            this.getChildren().add(rooms[n].getShape());
        }
        
        // Set up the hour labels and the room labels
        double x = Reservation.SPACING + 5;
        for(int n = 7; n < 23; n++) {
            Text t = new Text(x,Reservation.SPACING - 5,Integer.toString(n));
            x += Reservation.SPACING;
            this.getChildren().add(t);
        }
        for(int n = 1;n <= 5;n++) {
            Text t = new Text(10,n*Reservation.SPACING + 30,Integer.toString(n));
            this.getChildren().add(t);
        }
        
        readReservations();
        nextReservation = 0;
        
        this.setOnKeyPressed(e -> keyPress(e));
        this.setOnMousePressed(e -> startDrag(e));
        this.setOnMouseDragged(e -> dragPiece(e));
        this.setOnMouseReleased(e -> endDrag(e));
        // To do: set up code here to respond to mouse events
    }
    
    public void keyPress(KeyEvent e) {
        if(e.getCode() == KeyCode.ENTER && nextReservation < reservations.size()) {
            Reservation r = reservations.get(nextReservation);
            nextReservation++;
            rooms[4].claimReservation(r);
            this.getChildren().add(r.getShape());
        }
    }
    
    public void startDrag(MouseEvent evt) {
        double x = evt.getX();
        double y = evt.getY();
        for (Room room : rooms) {
            if (room.containsReservation(x, y)){
                RoomNumber = room.getRoom();
                for (Reservation r: reservations){
                  if(r.containsPoint(x,y)) {
                      dragging = r;
                      room.reclaimReservation(dragging);
                      break;
                  } 
                }  
            }
        }  
    }

    public void dragPiece(MouseEvent evt) {
        if (dragging != null) {
            double x = evt.getX();
            double y = evt.getY();
            dragging.setY(y);
        }
    }

    public void endDrag(MouseEvent evt) {
        if (dragging != null) {
            double x = evt.getX();
            double y = evt.getY();
            if(y>240 || y<40){
                rooms[RoomNumber-1].claimReservation(dragging);
            }
            for(Room room: rooms){
                if(room.containsPoint(x, y)){
                    room.claimReservation(dragging);
                break;
                }
                }
        }
    }


    
    public void readReservations() {
        
        File theFile = new File("requests.txt");
    if(theFile.exists()) {
        Scanner input = null;
        try {
            input = new Scanner(new FileInputStream(theFile));
        } catch(Exception ex) {
            System.out.println("Exception when trying to open file:"+ex.getMessage());
        }
    
    while(input.hasNext()){
        int id = input.nextInt();
        int start = input.nextInt();
        int end = input.nextInt();
        Reservation r = new Reservation(id,start,end);
        reservations.add(r);  
    }
    }
    }
    
    public void saveReservations() {
        try {
            PrintWriter writer = new PrintWriter(new File("reservations.txt"));
            for(int n = 0;n < 5;n++) {
                rooms[n].saveReservations(writer);
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
