package sigmasoftware.downloader.dto;

public class UserInfo {

    private String name;
    private String surname;
    private String role;
    private String password;
    private String country;
    private String city;
    private String phoneNumber;
    private int age;
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

        public Builder role(String role) {
            userInfo.role = role;
            return this;
        }

        public Builder password(String password) {
            userInfo.password = password;
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

        public Builder age(int age) {
            userInfo.age = age;
            return this;
        }

        public Builder nationality(String nationality) {
            userInfo.nationality = nationality;
            return this;
        }
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
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

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }
}
