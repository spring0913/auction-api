package com.auction.api.repository.user;

import com.auction.api.model.commons.Id;
import com.auction.api.model.user.Email;
import com.auction.api.model.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
public class JdbcUserRepository implements  UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public User insert(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users(seq,name,email,passwd,create_at) VALUES (null,?,?,?,?)", new String[]{"seq"});
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail().getAddress());
            ps.setString(3, user.getPassword());
            ps.setTimestamp(4, Timestamp.valueOf(user.getCreateAt()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long generatedSeq = (key != null)? key.longValue() : -1;
        return new User.Builder(user)
            .seq(generatedSeq)
            .build();
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
          "UPDATE users SET name=?,passwd=? WHERE seq=?",
          user.getName(),
          user.getPassword(),
          user.getSeq()
        );
    }

    @Override
    public Optional<User> findById(Id<User, Long> userId) {
        List<User> results = jdbcTemplate.query(
            "SELECT * FROM users WHERE seq=?",
            mapper,
            userId.value()
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        List<User> results = jdbcTemplate.query(
            "SELECT * FROM users WHERE email=?",
            mapper,
            email.getAddress()
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    static RowMapper<User> mapper = (rs, rowNum) -> new User.Builder()
            .seq(rs.getLong("seq"))
            .name(rs.getString("name"))
            .email(new Email(rs.getString("email")))
            .password(rs.getString("passwd"))
            .createAt(rs.getTimestamp("create_at").toLocalDateTime())
            .build();
}
