package cput.ac.za.assignment_6_1.domain;



/**
 * Created by mgijma on 2016/04/06.
 */
public class User {

    private Long id;
    private String UserID;
    private String Password;
    private String UserType;

    public Long getId() {
        return id;
    }

    public String getUserID(){
        return UserID;
    }

    public String getPassword(){
        return Password;
    }

    public String getUserType() {
        return UserType;
    }

    private User() {

    }

    private User(Builder builder) {
        this.id = builder.id;
        this.UserID = builder.UserID;
        this.Password = builder.Password;
        this.UserType = builder.UserType;

    }

    public static class Builder{

        private Long id;
        private String UserID;
        private String Password;
        private String UserType;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder Userid(String userId){
            this.UserID = userId;
            return this;

        }
        public Builder Password(String password){
            this.Password = password;
            return this;
        }

        public Builder UserType(String usertype){
            this.UserType = usertype;
            return this;
        }


        public Builder copy(User value) {
            this.id = value.id;
            this.UserID = value.UserID;
            this.Password = value.Password;
            this.UserType = value.UserType;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
