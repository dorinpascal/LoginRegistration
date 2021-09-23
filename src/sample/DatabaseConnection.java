package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection databaseLink;


    public Connection getConnection() {
        String databaseName = "account";
        String databaseUser = "root";
        String databasePassword="455946Dp2301";
        String url = "jdbc:mysql://127.0.0.1/" + databaseName;

        try
        {
          Class.forName("com.mysql.cj.jdbc.Driver");
          databaseLink= DriverManager.getConnection(url,databaseUser,databasePassword);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {

        }

        return databaseLink;
    }

}
