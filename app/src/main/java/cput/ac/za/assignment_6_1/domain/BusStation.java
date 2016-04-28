package cput.ac.za.assignment_6_1.domain;


/**
 * Created by mgijima on 2016/04/07.
 */

public class BusStation {

    private Long id;
    private String BusStationCode;
    private String Name;
    private String City;
    private String Code;

    public Long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getCode() {
        return Code;
    }

    public String getCity() {
        return City;
    }

    public String getBusStationCode() {
        return BusStationCode;
    }


    private BusStation() {

    }

    private BusStation(Builder builder) {
        this.id = builder.id;
        this.BusStationCode = builder.BusStationCode;
        this.Name = builder.Name;
        this.City = builder.City;
        this.Code = builder.Code;
    }

    public static class Builder{

        private Long id;
        private String BusStationCode;
        private String Name;
        private String City;
        private String Code;;

        public Builder id(Long id){
              this.id = id;
            return this;

        }

        public Builder busStationCode(String busStationCode){
            this.BusStationCode = busStationCode;
            return this;

        }
        public Builder Name(String name){
            this.Name = name;
            return this;
        }

        public Builder City(String city){
            this.City = city;
            return this;
        }

        public Builder Code(String code){
            this.Code = code;
            return this;
        }

        public Builder copy(BusStation value) {
            this.id = value.id;
            this.BusStationCode = value.BusStationCode;
            this.Name = value.Name;
            this.City = value.City;
            this.Code = value.Code;
            return this;
        }

        public BusStation build() {
            return new BusStation(this);
        }
    }

}
