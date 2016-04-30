package database;


import model.Club;
import model.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseWrapper {

    private Connection connection;
    private DatabaseDriver dbDriver;

    public DatabaseWrapper(){

        connection = dbDriver.getConnection();
        dbDriver = new DatabaseDriver();
    }

    public boolean authenticateMember(Member member) {

        boolean isAuthenticated = false;
        ResultSet results;
        PreparedStatement ps;

        try {
            ps = connection.prepareStatement(dbDriver.authenticateMember());
            ps.setString(1, member.getId());
            ps.setString(2, member.getPassword());
            results = ps.executeQuery();

            //isAuthenticated only true if member found
            isAuthenticated = results.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
            dbDriver.close();
        }
        return isAuthenticated;
    }

    public Member getMember(String id) {

        ResultSet results;
        PreparedStatement ps;
        Member member = null;

        try {
            ps = connection.prepareStatement(dbDriver.getMember());
            ps.setString(1, id);
            results = ps.executeQuery();

            while (results.next()){

                member = new Member();
                member.setId(results.getString("id"));
                member.setId(results.getString("first_name"));
                member.setId(results.getString("last_name"));
                member.setId(results.getString("handicap"));
                member.setId(results.getString("address"));
                //get clubs from private method
                member.setClubs(getMemberships(id));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            dbDriver.close();
        }
        return member;
    }

    private ArrayList<String> getMemberships(String id) {

        ResultSet results;
        PreparedStatement ps;
        ArrayList<String> memberships = null;

        try {
            ps = connection.prepareStatement(dbDriver.getMemberships());
            ps.setString(1, id);
            results = ps.executeQuery();

            while (results.next()){

                memberships = new ArrayList<>();
                memberships.add(results.getString("club_name"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            dbDriver.close();
        }
        return memberships;
    }
}
