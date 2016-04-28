package cput.ac.za.assignment_6_1.domain;

/**
 * Created by mgijma on 2016/04/07.
 */

public class Ticket {

    private Long id;
    private String TicketId;
    private String Date;
    private String BusNo;
    private String SeatNo;
    private String Status;
    private String CheckingStatus;

    public Long getId() {
        return id;
    }

    public String getDate() {
        return Date;
    }

    public String getTicketId() {
        return TicketId;
    }

    public String getBusNo() {
        return BusNo;
    }

    public String getSeatNo() {
        return SeatNo;
    }

    public String getStatus() {
        return Status;
    }

    public String getCheckingStatus() {
        return CheckingStatus;
    }

    private Ticket() {

    }

    private Ticket(Builder builder) {
        this.id = builder.id;
        this.TicketId = builder.TicketId;
        this.Date = builder.Date;
        this.BusNo = builder.BusNo;
        this.SeatNo = builder.SeatNo;
        this.Status = builder.Status;
        this.CheckingStatus = builder.CheckingStatus;

    }

    public static class Builder{

        private Long id;
        private String TicketId;
        private String Date;
        private String BusNo;
        private String SeatNo;
        private String Status;
        private String CheckingStatus;


        public Builder id(Long id){
            this.id = id;
            return this;
        }
        public Builder ticketid(String ticketId){
            this.TicketId = ticketId;
            return this;
        }
        public Builder Date(String date){
            this.Date = date;
            return this;
        }

        public Builder BusNo(String busNo){
            this.BusNo = busNo;
            return this;
        }

        public Builder SeatNo(String seatNo){
            this.SeatNo = seatNo;
            return this;
        }

        public Builder Status(String status){
            this.Status = status;
            return this;
        }

        public Builder CheckingStatus(String checkingStatus){
            this.CheckingStatus = checkingStatus;
            return this;
        }

        public Builder copy(Ticket value) {
            this.id = value.id;
            this.TicketId = value.TicketId;
            this.Date = value.Date;
            this.BusNo = value.BusNo;
            this.SeatNo = value.SeatNo;
            this.Status = value.Status;
            this.CheckingStatus = value.CheckingStatus;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }
}
