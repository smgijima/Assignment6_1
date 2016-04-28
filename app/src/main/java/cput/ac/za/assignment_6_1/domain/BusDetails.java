package cput.ac.za.assignment_6_1.domain;

/**
 * Created by mgijima on 2016/04/07.
 */

public class BusDetails {

    private Long id;
    private String BusNo;
    private String BusType;
    private String NoOfSeats;


    public Long getId() {
        return id;
    }

    public String getBusNo() {
        return BusNo;

    }

    public String getBusType() {
        return BusType;
    }

    public String getNoOfSeats() {
        return NoOfSeats;
    }

    private BusDetails() {

    }

    private BusDetails(Builder builder) {
        this.BusNo = builder.BusNo;
        this.BusType = builder.BusType;
        this.NoOfSeats = builder.NoOfSeats;

    }

    public static class Builder{

        private Long id;
        private String BusNo;
        private String BusType;
        private String NoOfSeats;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder Busno(String busNo){
            this.BusNo = busNo;
            return this;
        }
        public Builder Bustype(String busType){
            this.BusType = busType;
            return this;
        }

        public Builder Noofseats(String noOfSeats){
            this.NoOfSeats = noOfSeats;
            return this;
        }


        public Builder copy(BusDetails value) {

            this.id = value.id;
            this.BusNo = value.BusNo;
            this.BusType = value.BusType;
            this.NoOfSeats = value.NoOfSeats;
            return this;
        }

        public BusDetails build() {
            return new BusDetails(this);
        }
    }
}
