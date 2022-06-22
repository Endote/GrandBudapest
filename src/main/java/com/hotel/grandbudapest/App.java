package com.hotel.grandbudapest;

import com.hotel.grandbudapest.controller.UseCaseController;
import com.hotel.grandbudapest.model.Reservation;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class App extends JFrame {
    List<Reservation> reservations;
    UseCaseController controller;

    JPanel box1 = new JPanel();
    JPanel box2 = new JPanel();
    JPanel box3 = new JPanel();
    ReservationEditPanel rep = new ReservationEditPanel();

    public App(UseCaseController ucc) throws InstantiationError, IllegalAccessError{
        // Assign controller
        controller = ucc;

        WindowPanel wPanel = new WindowPanel();
        ReservationPanel rPanel = new ReservationPanel(ucc);
        rPanel.setVisible(false);

        this.setSize(new Dimension(800, 400));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Hotel Manager");

        // Initialize GUI elements
        JButton b1 = new JButton("Order a Service");
        JButton b2 = new JButton("Manage my Reservation");
        JButton b3 = new JButton("Manage my Information");
        JButton b4 = new JButton("Leave an Opinion");
        box1.add(b1);
        box1.add(b2);
        box1.add(b3);
        box1.add(b4);
        box1.setVisible(true);

        wPanel.setLayout(new BorderLayout());
        box1.setLayout(new GridLayout(4,1) );
        b2.addActionListener(
                (e)-> {
                    this.manageReservation(rPanel);
                    rep.removeAll();
                    this.revalidate();
                    this.repaint();
                }
        );
        rPanel.setLayout(new BoxLayout(rPanel, BoxLayout.Y_AXIS));
        this.add(wPanel,BorderLayout.NORTH);
        wPanel.add(box1, BorderLayout.NORTH);
        wPanel.add(rPanel, BorderLayout.SOUTH);
        rPanel.add(box3);
        rPanel.add(box2);

        this.revalidate();
        this.repaint();
    }

    public void manageReservation(ReservationPanel panel){
        if(!panel.reservationToggle()) {

            panel.setVisible(false);
            box2.setVisible(false);
            return;

        } else {
            if(box3.getComponentCount() > 0) box3.removeAll();

            panel.setVisible(true);
            box2.setVisible(true);

            // We assume that we are logged in as guest ID 9999
            reservations = controller.getReservations(9999L);

            // List reservations in JList
            Object[] listen = new Object[reservations.size()];
            for (int i = 0; i < reservations.size(); i++) {
                listen[i] = reservations.get(i).getId()+")"
                        +" hotel: "+reservations.get(i).getRoom().getOwner().getName()
                        +" room:"+reservations.get(i).getRoom().getRoomNumber()
                        +" dates: "+reservations.get(i).getStartDate()+" - "
                        + reservations.get(i).getEndDate();
            }

            JList list = new JList(listen);
            list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            list.setLayoutOrientation(JList.VERTICAL_WRAP);
            list.setVisibleRowCount(-1);

            ListSelectionListener res = this::manageMore;

            list.addListSelectionListener(res);


            JScrollPane listScroller = new JScrollPane(list);
            listScroller.setPreferredSize(new Dimension(780, 80));

            box3.add(listScroller);

            this.revalidate();
            this.repaint();
        }
    }

    public void manageMore(ListSelectionEvent e){

        reservations = controller.getReservations(9999L);

        rep.setLayout(new BoxLayout(rep, BoxLayout.Y_AXIS));

        rep.removeAll();
        rep.add(new Label("Reservation"));

        // Get selected reservation object
        Reservation r = reservations.get(e.getLastIndex());

        // Display reservation details
            JPanel j = new JPanel();
            j.setLayout(new BoxLayout(j, BoxLayout.Y_AXIS));
            // Date management box
            JPanel dates = new JPanel();
            j.setAlignmentX(1);
            j.setAlignmentY(2);
            dates.add(new Label("Reservation Dates"));
            TextField t1 = new TextField(r.getStartDate().toString());
            TextField t2 = new TextField(r.getEndDate().toString());
            dates.add(t1);
            dates.add(t2);
            // Room management box
            JPanel room = new JPanel();
            TextField t3 = new TextField(Integer.toString(r.getRoom().getRoomNumber()));
            room.setLayout(new FlowLayout());
            room.add(new Label("Reserved Room # in "+r.getRoom().getOwner().getName()));
            room.add(t3);


        JButton valid = new JButton();
            if(!r.getEndDate().isBefore(LocalDate.now())){
                valid.setText("Edit");
                valid.addActionListener(
                        (x) -> {
                            try {
                                int tmp = Integer.parseInt(t3.getText());
                                if(controller.changeDetails(r, LocalDate.parse(t1.getText()), LocalDate.parse(t2.getText()), tmp))
                                {
                                    valid.setText("Success");
                                } else
                                {
                                    valid.setText("Failure");
                                }
                            } catch (DateTimeParseException | NumberFormatException exe){
                                valid.setText("Failure");
                            }
                        }
                );
            }else{
                valid.setText("X");
            }
            room.add(valid);
            rep.add(dates);
            rep.add(room);

            box2.add(rep);
            this.revalidate();
            this.repaint();
        }


    }
class WindowPanel extends JPanel {}

class ReservationPanel extends JPanel {

    private UseCaseController controller;
    private enum state{No, Yes};
    private state State = state.No;

    boolean reservationToggle(){
        if (State == state.No) {
            this.revalidate();
            this.repaint();
            State = state.Yes;
            return true;
        }
        this.revalidate();
        this.repaint();
        State = state.No;
        return false;
    }

    public ReservationPanel(UseCaseController ucc){
        this.controller = ucc;
    }

}
class ReservationEditPanel extends JPanel {}
