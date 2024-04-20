package com.example.HWSpring2.repository;

import com.example.HWSpring2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll(){
        String sql = "SELECT * FROM userTable";
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));

            return rowObject;
        };


        return jdbc.query(sql, userRowMapper);
    }

    public User save(User user){
        String sql = "INSERT INTO userTable(firstName,lastName) VALUES ( ? , ? )";
        jdbc.update(sql, user.getFirstName(), user.getLastName());

        return user;
    }

    public void deleteById(int id){
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }

    public void updateById(User user){
        String sql = "UPDATE userTable SET firstName=?, lastName=? WHERE id=?;";

        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
    }

    public User getOne(int id){
        String sql = "SELECT * FROM userTable WHERE id = :id";
        sql = sql.replaceAll(":id", String.valueOf(id));

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));

            return rowObject;
        };

        List<User> temp = jdbc.query(sql, userRowMapper);

        return !temp.isEmpty() ? temp.get(0) : null;
    }
}
