package com.example.kursrabot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class DB {
    private final String HOST = "10.207.144.159";
    private final String PORT = "3306";
    private final String DB_NAME = "user090_db1";
    private final String LOGIN = "user090_user1";
    private final String PASS = "m_Az6che";

    private Connection dbConn = null;
    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME+"?characterEncoding=UTF8";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }



    public int getUser(String log,String pass) throws SQLException, ClassNotFoundException {
        String sql = "SELECT rols_idrols , count(*) as n FROM users where users_fullname = ? and users_code=? group by rols_idrols";

        PreparedStatement statement = getDbConnection().prepareStatement(sql);
        statement.setString(1, log);
        statement.setString(2, pass);
        ResultSet res = statement.executeQuery();

        int role=0;
        while(res.next()) {
            role = res.getInt("rols_idrols");
        }
        return role;
    }




    public ArrayList<Secdata> getSection() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM section";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<Secdata> stud = new ArrayList<>();
        while(res.next())
            stud.add(new Secdata(res.getString("address") ,res.getString("price") , res.getString("name")));

        return stud;
    }



    public String getDeskOfTour(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT desk FROM tournament where idtournament = "+id +";";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        String desk = null;
        while(res.next())
            desk = res.getString("desk");
        return desk;

    }




    public ArrayList<Tourdata> getTournirs() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM tournament";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<Tourdata> stud = new ArrayList<>();
        while(res.next())
            stud.add(new Tourdata(res.getInt("idtournament"),res.getString("stage_date") , res.getString("points_scored")));

        return stud;
    }

    public ArrayList<String> getRaiting(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT users.users_fullname, tournament_has_users.ochki FROM users JOIN tournament_has_users ON users.idusers = tournament_has_users.users_idusers JOIN tournament ON tournament_has_users.tournament_idtournament = tournament.idtournament WHERE tournament.idtournament = "+id+";";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        ArrayList<String> uch = new ArrayList<>();
        while(res.next()) {
            String fullname = res.getString("users.users_fullname");
            int ochki = res.getInt("tournament_has_users.ochki");
            uch.add(fullname + " набранные очки - " + ochki);
        }
        return uch;
    }

    public ArrayList<String> getTour() throws SQLException, ClassNotFoundException {
        String sql = "SELECT desk FROM tournament";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        ArrayList<String> uch = new ArrayList<>();
        while(res.next())
            uch.add(res.getString("desk"));
        return uch;
    }




    public ObservableList<String> getData(String selected) throws   SQLException, ClassNotFoundException {
        String sql = "SELECT users.users_fullname, tournament_has_users.ochki \n" +
                "FROM users \n" +
                "JOIN tournament_has_users ON users.idusers = tournament_has_users.users_idusers \n" +
                "JOIN tournament ON tournament_has_users.tournament_idtournament = tournament.idtournament\n" +
                "WHERE tournament.desk = \""+ selected +"\"\n" +
                "ORDER BY tournament_has_users.ochki DESC;";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        ObservableList<String> uch = FXCollections.observableArrayList();;
        while(res.next()) {
            String full = res.getString("users.users_fullname");
            int och = res.getInt("tournament_has_users.ochki");
            uch.add(full + "набранные очки - " + och);
        }
        return uch;


    }
    public ArrayList<String> getnewTour() throws SQLException, ClassNotFoundException {
        String sql = "SELECT desk FROM new_tour";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        ArrayList<String> uch = new ArrayList<>();
        while(res.next())
            uch.add(res.getString("desk"));
        return uch;
    }




    void insertnewTour( String desk) throws   SQLException, ClassNotFoundException{
        String sql = "INSERT INTO `sportdata`.`new_tour`(`data`,`desk`)VALUES(now(),?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, desk);
        prSt.executeUpdate();
    }
    void insertnewTour_us( int idnewtour, int idus) throws   SQLException, ClassNotFoundException{
        String sql = "INSERT INTO `sportdata`.`user_newtour`(`idnewtour`,`iduser`)VALUES(?,?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, idnewtour);
        prSt.setInt(2, idus);
        prSt.executeUpdate();
    }
    public int getidbyuser(String fio) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idusers FROM users where users_fullname = \""+fio+"\";";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        int id = 0;
        while(res.next())
            id = res.getInt("idusers");
        return id;
    }

    public int getidbynewtour(String desk) throws SQLException, ClassNotFoundException {
        String sql = "SELECT id FROM new_tour where desk = \""+desk+"\";";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        int id = 0;
        while(res.next())
            id = res.getInt("id");
        return id;
    }


    public int getidsekcii(String name , int price) throws SQLException, ClassNotFoundException {
        String sql = "SELECT idsection FROM sportdata.section where name = \""+name+"\" and price = "+price+";";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        int id = 0;
        while(res.next())
            id = res.getInt("idsection");
        return id;
    }


    void Updatedeskfortour(String newdesk, String olddesk) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE new_tour set desk =\""+newdesk+"\"where desk = \""+olddesk+"\";";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.executeUpdate();


    }


    public ArrayList<String> getUsersByTourDesk(String desk) throws SQLException, ClassNotFoundException {
        String sql = "SELECT users.users_fullname FROM sportdata.user_newtour INNER JOIN sportdata.users ON sportdata.user_newtour.iduser = sportdata.users.idusers WHERE sportdata.user_newtour.idnewtour IN (SELECT id FROM sportdata.new_tour WHERE desk = ?)";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);
        statement.setString(1, desk);

        ResultSet res = statement.executeQuery();

        ArrayList<String> users = new ArrayList<>();
        while (res.next()) {
            users.add(res.getString("users.users_fullname"));
        }

        return users;
    }




    public ArrayList<String> getNewUcastniki() throws   SQLException, ClassNotFoundException{
        String sql = "SELECT users_fullname FROM users where rols_idrols = 1";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        ArrayList<String> uch = new ArrayList<>();
        while(res.next())
            uch.add(res.getString("users_fullname"));
        return uch;
    }


    public ArrayList<String> getAddedUcastniki(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT users_fullname FROM users INNER JOIN user_newtour ON users.idusers = user_newtour.iduser WHERE user_newtour.idnewtour = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet res = preparedStatement.executeQuery();
        ArrayList<String> uch = new ArrayList<>();
        while(res.next())
            uch.add(res.getString("users_fullname"));
        return uch;
    }


    public void deleteFromTour(int userId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM user_newtour WHERE iduser = ?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        preparedStatement.executeUpdate();
    }


    public ArrayList<String> getSectionUsers(int sectionId) throws SQLException, ClassNotFoundException {
        String sql = "{call GetSectionUsers(?)}";
        CallableStatement statement = getDbConnection().prepareCall(sql);
        statement.setInt(1, sectionId);
        ResultSet res = statement.executeQuery();
        ArrayList<String> users = new ArrayList<>();
        while(res.next()) {
             users.add(res.getString("users_fullname"));
        }
        return users;
    }

    public ArrayList<String> getAllTourDesks() throws SQLException, ClassNotFoundException {
        String sql = "SELECT desk FROM new_tour";
        PreparedStatement statement = getDbConnection().prepareStatement(sql);

        ResultSet res = statement.executeQuery();

        ArrayList<String> desks = new ArrayList<>();
        while(res.next()) {
            desks.add(res.getString("desk"));
        }

        return desks;
    }





}
