package cput.ac.za.assignment_6_1.factories;
import cput.ac.za.assignment_6_1.domain.User;

/**
 * Created by mgijma on 2016/04/06.
 */
public class UserFatory {

    public static User getUser(String userid, String password, String usertype) {
        User myUser = new User.Builder()
                .Userid(userid)
                .Password(password)
                .UserType(usertype)
                .build();

        return myUser;
    }
}
