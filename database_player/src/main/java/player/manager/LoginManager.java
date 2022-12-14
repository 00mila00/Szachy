package player.manager;

import dao.UserDao;
import player.User;
import player.password.PasswordHashing;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * Class responsible for logging
 */
public class LoginManager {

    private static User loggedUser;

    /**
     * Login user
     * @param login login of user that want to login
     * @param password password of user that want to login
     */
    public static void login(String login, String password) throws SQLException {
        UserDao userDao = new UserDao();
        User user = userDao.findByLogin(login);
        if(user == null || !Arrays.equals(PasswordHashing.hashPassword(password), user.getPassword())) {
            throw new IllegalArgumentException("Login or password incorrect");
        }
        setLoggedUser(user);
    }

    public static void updateLoggedUser() {
        try(UserDao userDao = new UserDao()) {
            userDao.update(loggedUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Logout current user
     */
    public static void logout() {
        setLoggedUser(null);
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        LoginManager.loggedUser = loggedUser;
    }
}
