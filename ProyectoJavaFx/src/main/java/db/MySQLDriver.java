package db;

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

    public int runUpdate(String query) {
        try {
            this.statement = this.connection.createStatement();
            int result = this.statement.executeUpdate(query);
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void close() {
        try {
            this.connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MySQLDriver driver = new MySQLDriver();
    }
}