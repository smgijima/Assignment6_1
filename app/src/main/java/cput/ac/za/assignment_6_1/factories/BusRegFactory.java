package cput.ac.za.assignment_6_1.factories;
import cput.ac.za.assignment_6_1.domain.BusRegistration;

/**
 * Created by mgijma on 2016/04/07.
 */
public class BusRegFactory {


    public static BusRegistration getReg(String regNum, String date) {
        BusRegistration myReg = new BusRegistration.Builder()
                .busRegNumber(regNum)
                .date(date)
                .build();

        return myReg;
    }
}
