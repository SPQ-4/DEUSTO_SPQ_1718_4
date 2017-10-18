package SPQ_1718_4.Proyecto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLDriver {
    private Connection connection;
    private Statement statement;

    public MySQLDriver() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://panenka-dev.c8sznodk2hny.eu-west-2.rds.amazonaws.com",
                    "panenka_master", "spqpanenka");

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet runQuery(String query) {
        try {
            this.statement = this.connection.createStatement();
            ResultSet result = this.statement.executeQuery(query);
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        MySQLDriver driver = new MySQLDriver();
    }
}