package br.com.loja.dal;

import java.sql.*;


public class ModuloConexao {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/login";
        String user = "root";
        String password = "Prof@dm1n";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}