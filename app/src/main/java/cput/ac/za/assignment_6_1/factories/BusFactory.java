package cput.ac.za.assignment_6_1.factories;

import cput.ac.za.assignment_6_1.domain.BusClass;

/**
 * Created by mgijma on 2016/04/03.
 */
public class BusFactory {
    public static BusClass getBussclass(String tickets, String type, String one_way_or_return) {
        BusClass myClass = new BusClass.Builder()
                .ticket(tickets)
                .type(type)
                .oneWayOrReturn(one_way_or_return)
                .build();

        return myClass;
    }
}
