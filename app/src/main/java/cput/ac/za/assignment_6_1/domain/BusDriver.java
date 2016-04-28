package cput.ac.za.assignment_6_1.domain;
/**
 * Created by mgijima on 2016/04/03.
 */

public class BusDriver{

    private Long id;
    private String emp_num;
    private String name;
    private String surname;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmp_Num() {
        return emp_num;
    }
    private BusDriver() {

    }

    private BusDriver(Builder builder) {
        this.id =builder.id;
        this.emp_num = builder.emp_num;
        this.name= builder.name;
        this.surname = builder.surname;

    }

    public static class Builder{
        private Long id;
        private String emp_num;
        private String name;
        private String surname;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder empNum(String empnum){
            this.emp_num = empnum;
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

        public Builder copy(BusDriver value) {

            this.id = value.id;
            this.name = value.name;
            this.surname = value.surname;
            this.emp_num = value.emp_num;
            return this;
        }

        public BusDriver build() {
            return new BusDriver(this);
        }
    }


}
