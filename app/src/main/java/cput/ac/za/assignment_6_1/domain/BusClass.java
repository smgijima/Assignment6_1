package cput.ac.za.assignment_6_1.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Created by mgijima on 2016/04/03.
 */

public class BusClass {

    private Long id ;
    private String tickets;
    private String type;
    private String one_or_return;

    public Long getId() {
        return id;
    }

    public String getTickets() {
        return tickets;
    }

    public String getType() {
        return type;
    }

    public String getOne_or_return() {
        return one_or_return;
    }
    private BusClass() {

    }

    private BusClass(Builder builder) {
        this.id = builder.id;
        this.tickets = builder.tickets;
        this.type = builder.type;
        this.one_or_return= builder.one_or_return;
    }

    public static class Builder{
        private Long id;
        private String tickets;
        private String type;
        private String one_or_return;


        public Builder id(Long id)
        {
            this.id = id;
            return this;
        }

        public Builder ticket(String ticket){
            this.tickets = ticket;
        return this;
        }
        public Builder type(String type){
            this.type = type;
            return this;

        }
        public Builder oneWayOrReturn(String one_or_return){
            this.one_or_return = one_or_return;

            return this;
        }

        public Builder copy(BusClass value) {
            this.id = value.id;
            this.tickets = value.tickets;
            this.type = value.type;
            this.one_or_return = value.one_or_return;
            return this;
        }

        public BusClass build() {
            return new BusClass(this);
        }
    }
}
