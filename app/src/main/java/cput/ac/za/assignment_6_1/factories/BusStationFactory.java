package cput.ac.za.assignment_6_1.factories;
import cput.ac.za.assignment_6_1.domain.BusStation;

/**
 * Created by mgijma on 2016/04/07.
 */
public class BusStationFactory {

    public static BusStation getStation(String busStationCode, String name, String city, String code) {
         BusStation busStation = new BusStation.Builder()
                 .busStationCode(busStationCode)
                .Name(name)
                .City(city)
                .Code(code)
                .build();

        return busStation;
    }
}
