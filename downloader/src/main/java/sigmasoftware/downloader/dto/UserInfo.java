package sigmasoftware.downloader.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserInfo {

    @Id
    private String name;
    private String surname;
    private String role;
    private String password;
    private String country;
    private String city;

    @Column(name = "phone_number")
    private String phoneNumber;
    private Integer age;
    private String nationality;

    public static class Builder {
        private final UserInfo userInfo;

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
}
