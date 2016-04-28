package cput.ac.za.assignment_6_1.factories;

import cput.ac.za.assignment_6_1.domain.Ticket;

/**
 * Created by mgijma on 2016/04/07.
 */
public class TicketFactory {

    public static Ticket getTicket(String ticket, String Date, String Busno, String Seatno, String Status, String Checkingstatus) {
        Ticket myTicket = new Ticket.Builder()
                .ticketid(ticket)
                .Date(Date)
                .BusNo(Busno)
                .SeatNo(Seatno)
                .Status(Status)
                .CheckingStatus(Checkingstatus)
                .build();

        return myTicket;
    }
}
