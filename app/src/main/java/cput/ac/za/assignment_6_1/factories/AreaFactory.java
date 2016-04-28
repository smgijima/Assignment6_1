package cput.ac.za.assignment_6_1.factories;
import cput.ac.za.assignment_6_1.domain.Area;

/**
 * Created by mgijima on 2016/04/03.
 */
public class AreaFactory {

    public static Area getArea(String code, String adress) {
        return new Area.Builder()
                .code(code)
                .adress(adress)
                .build();


    }
}
/*

public class PersonContactFactory {
    public static PersonContact getContact(String contact, String value) {
        return new PersonContact.Builder()
                .state(DomainState.ACTIVE.name())
                .contactTypeId(contact)
                .contactValue(value)
                .date(new Date())
                .status(DomainState.ACTIVE.name())
                .build();
    }

}


 */