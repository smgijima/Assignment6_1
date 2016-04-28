package cput.ac.za.assignment_6_1.domain;

import java.io.Serializable;
/**
 * Created by mgijma on 2016/04/03.
 */

public class Area implements Serializable {

    private Long id;
    private String code;
    private String adress;

    public String getCode() {
        return code;
    }

    public String getAdress() {
        return adress;
    }

    public Long getId() {
        return id;
    }

    private Area() {

    }

    private Area(Builder builder) {
        this.id = builder.id;
        this.code = builder.code;
        this.adress = builder.adress;

    }

    public static class Builder{
        private Long id;
        private String code;
        private String adress;

        public Builder id(Long id){
            this.id = id;
            return this;

        }

        public Builder code(String code){
            this.code = code;
            return this;

        }
        public Builder adress(String adress){

            this.adress = adress;
            return this;

        }

        public Builder copy(Area value) {
            this.id = value.id;
            this.code = value.code;
            this.adress = value.adress;
            return this;
        }

        public Area build() {
            return new Area(this);
        }
    }
}
