package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDao {

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users (username, email, password) VALUES (?, ?, ?);";

    private static final String READ_USER_QUERY =
            "SELECT * FROM users WHERE id = ?;";

    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?;";

    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?;";

    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public User read (int userId) throws SQLException {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
             ResultSet resultSet = statement.executeQuery();
             User userFromDb = null;

            while (resultSet.next()) {
                userFromDb = new User(resultSet.getString("username"),resultSet.getString("email"), resultSet.getString("password") );
                userFromDb.setId(resultSet.getInt(1));
            }
            return userFromDb;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void update(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setString(4, String.valueOf(user.getId()));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }



    public void delete(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


}
