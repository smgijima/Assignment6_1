package cput.ac.za.assignment_6_1.domain;

/**
 * Created by mgijma on 2016/04/07.
 */

public class BusRegistration {

    private Long id;
    private String busRegNumber;
    private String date;

    public Long getId() {
        return id;
    }

    public String getBusRegNumber() {
        return busRegNumber;
    }

    public String getDate() {
        return date;
    }


    private BusRegistration() {

    }

    private BusRegistration(Builder builder) {
        this.id = builder.id;
        this.busRegNumber = builder.busRegNumber;
        this.date = builder.date;
    }

    public static class Builder{

        private Long id;
        private String busRegNumber;
        private String date;

        public Builder id(Long id ) {
            this.id = id;
            return this;
        }

        public Builder busRegNumber(String busRegNumber){
            this.busRegNumber = busRegNumber;
            return this;

        }
        public Builder date(String date){
            this.date = date;
            return this;
        }

        public Builder copy(BusRegistration value) {
            this.id = value.id;
            this.busRegNumber = value.busRegNumber;
            this.date = value.date;
            return this;
        }

        public BusRegistration build() {
            return new BusRegistration(this);
        }
    }
}
