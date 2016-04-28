package cput.ac.za.assignment_6_1.factories;

import cput.ac.za.assignment_6_1.domain.BusDriver;

/**
 * Created by mgijma on 2016/04/03.
 */
public class BusDriverFactory {

        public static BusDriver getBusDriver(String emp_num, String name, String surname) {
            BusDriver myClass = new BusDriver.Builder()
                    .empNum(emp_num)
                    .name(name)
                    .surname(surname)
                    .build();

            return myClass;
        }
    }
