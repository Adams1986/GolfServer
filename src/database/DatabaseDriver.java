package database;

import model.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDriver {

    private static String sqlUrl = "jdbc:mysql://"+ Config.getHost()+":"+Config.getPort()+"/"+Config.getDbname();
    private static String sqlUser = Config.getUsername();
    private static String sqlPassword = Config.getPassword();

    private Connection connection = null;

    /**
     * Konstruktør. Init forbindelsen. Bruges i wrapperen når object af driver laves.
     */
    public DatabaseDriver()
    {
        try {

            connection = DriverManager.getConnection(sqlUrl, sqlUser, sqlPassword);
        }
        catch (SQLException e) {

            e.printStackTrace();
            System.exit(1);
        }
    }

    public Connection getConnection() {
        return connection;
    }


    public void close() {

        try{
            connection.close();
        }
        catch(SQLException e) {

            e.printStackTrace();
        }
    }

    public String getMember(){

        return "SELECT * FROM members WHERE id = ?";
    }

    public String authenticateMember(){

        return "SELECT * FROM members WHERE id = ?  && password = ?";
    }

    public String getMemberships(){

        return "SELECT club_name FROM clubs INNER JOIN memberships ON club_id = clubs.id WHERE member_id = ?";
    }
}
