package sigmasoftware.downloader;

public class UserInfo {

    private String name;
    private String surname;
    private String country;
    private String city;
    private String phoneNumber;
    private String age;
    private String nationality;

    public static class Builder {
        private UserInfo userInfo;

        public Builder() {
            userInfo = new UserInfo();
        }

        public UserInfo build(){
            return userInfo;
        }

        public Builder name(String name) {
            userInfo.name = name;
            return this;
        }

        public Builder surname(String surname) {
            userInfo.surname = surname;
            return this;
        }

        public Builder country(String country) {
            userInfo.country = country;
            return this;
        }

        public Builder city(String city) {
            userInfo.city = city;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            userInfo.phoneNumber = phoneNumber;
            return this;
        }

        public Builder age(String age) {
            userInfo.age = age;
            return this;
        }

        public Builder nationality(String nationality) {
            userInfo.nationality = nationality;
            return this;
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }
}
