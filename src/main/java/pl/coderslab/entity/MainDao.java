package pl.coderslab.entity;

import java.sql.SQLException;

public class MainDao {
    public static void main(String[] args) {

   /* User test = new User("tester", "test@gmail.com", "test123");
    UserDao testDao = new UserDao();
    testDao.create(test);*/

   /* User test2 = new User("tester2", "tes2@yahoo.com", "trololo");
    UserDao test2Dao = new UserDao();
    test2Dao.create(test2);*/

    UserDao readUserDao = new UserDao();
    UserDao readUserDao2 = new UserDao();
    UserDao readUserDao3 = new UserDao();

        try {
            User readUser = readUserDao.read(1);
            System.out.println(readUser.toString());

            User readUser2 = readUserDao2.read(2);
            System.out.println(readUser2.toString());

            User readUser3 = readUserDao3.read(3);
            System.out.println(readUser3.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
