package com.example.test;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Config{
    Connection dbConnection;
    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                +dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(String Mail, String Password, String Name, String Phone, String Bonus)
    {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_MAIL + "," +
                Const.USERS_PASSWORD + "," + Const.USERS_NAME + "," +
                Const.USERS_PHONE + "," + Const.USERS_BONUS + ")" +
        "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement prST = getDbConnection().prepareStatement(insert);
            prST.setString(1, Mail);
            prST.setString(2, Password);
            prST.setString(3, Name);
            prST.setString(4, Phone);
            prST.setString(5, Bonus);
            prST.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    class Task extends AsyncTask<Void, Void, Void> {
        String records="", error="";



        @Override
        protected Void doInBackground(Void... voids) {
            try{
                String url= "jdbc:mysql://89.113.138.46:3306/Sanork?enabledTLSProtocols=TLSv1.2";
                String username="root";
                String password="8567950a";
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                try(Connection conn=DriverManager.getConnection(url,username,password)){

                    System.out.println("Connection to Store DB succesfull!");
                }
            }
            catch(Exception ex){
                System.out.println("Connection failed...");

                System.out.println(ex);
            }
            return null;
        }
    }
    public ResultSet getUser(User user){
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_MAIL + "=? AND " + Const.USERS_PASSWORD + "=?";

        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getName());
            prSt.setString(2, user.getPassword());
            prSt.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return resSet;
    }
}
