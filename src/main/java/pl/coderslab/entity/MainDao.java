package pl.coderslab.entity;

public class MainDao {
    public static void main(String[] args) {

   /* User test = new User("tester", "test@gmail.com", "test123");
    UserDao testDao = new UserDao();
    testDao.create(test);*/

    User test2 = new User("tester2", "tes2@yahoo.com", "trololo");
    UserDao test2Dao = new UserDao();
    test2Dao.create(test2);

    }
}
