package com.example.HWSpring2.repository;

import com.example.HWSpring2.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {
    @Autowired
    private final JdbcTemplate jdbc;
    @Autowired
    private final QuaryBiblio biblio;

    public List<User> findAll(){
        String sql = biblio.getFindAll();
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
        String sql = biblio.getSave();
        jdbc.update(sql, user.getFirstName(), user.getLastName());

        return user;
    }

    public void deleteById(int id){
        String sql = biblio.getDeleteById();
        jdbc.update(sql, id);
    }

    public void updateById(User user){
        String sql = biblio.getUpdateById();

        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
    }

    public User getOne(int id){
        String sql = biblio.getGetOne();
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
