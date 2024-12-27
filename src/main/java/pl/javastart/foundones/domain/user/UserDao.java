package pl.javastart.foundones.domain.user;

import pl.javastart.foundones.common.BaseDao;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserDao extends BaseDao {

    public void save(User user) {
        saveUser(user);
        saveUserRole(user);
    }

    private void saveUser(User user){
        final String sql = "INSERT INTO user (username, email, registration_date, password) VALUES (?, ?, ?, ?)";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setObject(3, user.getRegistrationDate());
            statement.setObject(4, user.getPassword());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveUserRole(User user){
        final String sql = "INSERT INTO user_role (username) VALUES (?)";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findByUsername(String username) {
        final String sql = "SELECT * FROM user WHERE username = ?";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(mapRow(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findById(int id) {
        final String sql = """
                SELECT 
                    id, username, email, registration_date, password
                FROM
                    user
                WHERE 
                    id = ?
                """;
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(mapRow(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name =  resultSet.getString("username");
        String email = resultSet.getString("email");
        LocalDateTime registrationDate = resultSet.getObject("registration_date", LocalDateTime.class);
        String password = resultSet.getString("password");
        return new User(id, name, email, registrationDate, password);
    }

}
