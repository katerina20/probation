package sigmasoftware.downloader.services;

import io.vavr.control.Try;
import sigmasoftware.downloader.dto.UserInfo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@ApplicationScoped
public class UserService {

    @Inject
    DataSource dataSource;

    @Inject
    EntityManager entityManager;

    public UserInfo getFullName(String name) {
        ResultSet resultSet = Try
                .of(() -> {
                    Connection connection = dataSource.getConnection();
                    Statement statement = connection.createStatement();
                    return statement.executeQuery("SELECT * FROM users_fullnames LIMIT 1");
                })
                .onFailure(throwable -> System.out.println(throwable.getMessage()))
                .get();

        return Try.of(() -> {
            resultSet.next();
            return new UserInfo.Builder()
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .build();
        })
                  .onFailure(throwable -> System.out.println(throwable.getMessage()))
                  .get();
    }

    @Transactional
    public UserInfo update(UserInfo userInfo) {
        userInfo = new UserInfo.Builder().name("Bob").surname("Bobster").password("bob_pass").role("ADMIN").build();
        UserInfo merge = entityManager.merge(userInfo);
        return merge;
    }
}
