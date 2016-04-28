

package cput.ac.za.assignment_6_1.factories;

import cput.ac.za.assignment_6_1.domain.BusDetails;;

/**
 * Created by mgijima on 2016/04/07.
 */
public class BusDetailsFactory {

    public static BusDetails getDetails(String BusNo, String BusType, String NoOfSeats) {
        BusDetails details = new BusDetails.Builder()
                .Busno(BusNo)
                .Bustype(BusType)
                .Noofseats(NoOfSeats)
                .build();

        return details;
    }
}
