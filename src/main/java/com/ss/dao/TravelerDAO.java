package com.ss.dao;

import com.ss.entity.Employee;
import com.ss.entity.Traveler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TravelerDAO extends BaseDAO<Traveler> {
    public TravelerDAO(Connection conn) {
        super(conn);
    }

    @Override
    public List<Traveler> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<Traveler> travelers = new ArrayList<>();
        while (rs.next()) {
            Traveler e = new Traveler();
            e.setId(rs.getInt("id"));
            e.setRoleId(rs.getInt("role_id"));
            e.setGivenName(rs.getString("given_name"));
            e.setFamilyName(rs.getString("family_name"));
            e.setUserName(rs.getString("username"));
            e.setEmail(rs.getString("email"));
            e.setPhone(rs.getString("phone"));
            travelers.add(e);
        }
        return travelers;
    }

    public Integer addTraveler(Traveler t) throws ClassNotFoundException, SQLException {
        return saveWithPK("INSERT INTO user (role_id, given_name, family_name, username," +
                        " email, password, phone) VALUES (?, ?, ?, ?, ?, ?, ?)",
                new Object[] { t.getRoleId(), t.getGivenName(),
                        t.getFamilyName(), t.getUserName(), t.getEmail(),
                        t.getPassword(), t.getPhone()});
    }

    public Integer updateTraveler(Traveler e) throws ClassNotFoundException, SQLException {
        return saveWithPK("UPDATE user SET given_name = ?, family_name = ?, username = ?," +
                        " email = ?, password = ?, phone = ? where id = ?",
                new Object[] { e.getGivenName(),
                        e.getFamilyName(), e.getUserName(), e.getEmail(),
                        e.getPassword(), e.getPhone(), e.getId() });
    }

    public void deleteTraveler(Traveler e) throws ClassNotFoundException, SQLException {
        save("DELETE FROM user where id = ?",
                new Object[] { e.getId() });
    }

    public List<Traveler> getAllTravelers() throws ClassNotFoundException, SQLException {
        return read("select * from user where role_id = 2", null);
    }
}
