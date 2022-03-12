package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDao {

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users (username, email, password) VALUES (?, ?, ?);";

    private static final String READ_USER_QUERY =
            "SELECT * FROM users WHERE id = ?;";

    private static final String UPDATE_USER_QUERY =
            "UPDATE USERS SET username = ?, email = ?, password = ? WHERE id = ?;";

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



    public static void printData(Connection conn, String query, String... columnNames) throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
             ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                for (String columnName : columnNames) {
                    System.out.print(resultSet.getString(columnName) + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static int update(Connection conn, String query, String... params) {
        try (PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }



    public static void remove(Connection conn, String tableName, int id) {
        try (PreparedStatement statement =
                     conn.prepareStatement(DELETE_USER_QUERY.replace("tableName", tableName));) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }




}
