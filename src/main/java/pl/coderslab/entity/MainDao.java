package pl.coderslab.entity;

import java.sql.SQLException;

public class MainDao {
    public static void main(String[] args) {

        //create
   /* User test = new User("tester", "test@gmail.com", "test123");
    UserDao testDao = new UserDao();
    testDao.create(test);*/

        //read
  /*  UserDao readUserDao = new UserDao();
    UserDao readUserDao2 = new UserDao();
    UserDao readUserDao3 = new UserDao();

        try {
            User readUser = readUserDao.read(1);
            System.out.println(readUser.toString());


        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        //update

      /*  UserDao userUpdateDao = new UserDao();
        try {
            User userToUpdate = userUpdateDao.read(5);
            userToUpdate.setUserName("barnaby");
            userToUpdate.setEmail("barnaby@yahoo.com");
            userToUpdate.setPassword("new2398hfs!");
            userUpdateDao.update(userToUpdate);

        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        // delete
/*
        UserDao userToDeleteDao = new UserDao();
        userToDeleteDao.delete(4);*/

    }
}
