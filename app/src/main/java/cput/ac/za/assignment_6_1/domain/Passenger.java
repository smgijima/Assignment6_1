package cput.ac.za.assignment_6_1.domain;
/**
 * Created by mgijima on 2016/04/03.
 */

public class Passenger {

        private Long id;
        private String name;
        private String surname;
        private String age;

    public Long getId() {
        return id;
    }

    public String getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }
        private Passenger() {

        }

        private Passenger(Builder builder) {

            this.id = builder.id;
            this.name = builder.name;
            this.surname = builder.surname;
            this.age = builder.age;
        }

        public static class Builder{

            private Long id;
            private String name;
            private String surname;
            private String age;



            public Builder id(Long value){
                this.id = value;
                return this;
            }
            public Builder age(String value){
                this.age = value;
                return this;
            }
            public Builder name(String name){
                this.name = name;
                return this;


            }
            public Builder surname(String surname){
                this.surname = surname;
                return this;
            }

            public Builder copy(Passenger value) {
                this.id = value.id;
                this.name = value.name;
                this.surname = value.surname;
                this.age = value.age;
                return this;
            }

            public Passenger build() {
                return new Passenger(this);
            }
        }


    }

