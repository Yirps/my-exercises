package io.codeforall.bootcamp.javabank.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcUserService {

    private Connection dbConnection;

    public int count() {

        int result = 0;

        // create a new statement
        Statement statement = dbConnection.createStatement();

        // create a query
        String query = "SELECT COUNT(*) FROM user";

        // execute the query
        ResultSet resultSet = statement.executeQuery(query);

        // get the results
        if (resultSet.next()) {
            result = resultSet.getInt(1);
        }

        return result;
    }
}
