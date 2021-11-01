package com.auction.api.repository.auction;

import com.auction.api.model.auction.Auction;
import com.auction.api.model.commons.Id;
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
public class JdbcAuctionRepository implements AuctionRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAuctionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Auction insert(Auction auction) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO auctions(seq,user_seq,title,contents,reserve_price,start_time,end_time) VALUES (null,?,?,?,?,?,?)", new String[]{"seq"});
            ps.setLong(1, auction.getUserId().value());
            ps.setString(2, auction.getTitle());
            ps.setString(3, auction.getContents());
            ps.setInt(4, auction.getReservePrice());
            ps.setTimestamp(5, Timestamp.valueOf(auction.getStartTime()));
            ps.setTimestamp(6, Timestamp.valueOf(auction.getEndTime()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long generatedSeq = (key != null)? key.longValue() : -1;
        return new Auction.Builder(auction)
            .seq(generatedSeq)
            .build();
    }

    @Override
    public void update(Auction auction) {
        jdbcTemplate.update(
            "UPDATE auctions SET title=?,contents=?,reserve_price=?,end_time=?",
            auction.getTitle(),
            auction.getContents(),
            auction.getReservePrice(),
            auction.getEndTime()
        );
    }

    @Override
    public Optional<Auction> findById(Id<Auction, Long> auctionId, Id<User, Long> auctioneerId, Id<User, Long> longinUserId) {
        List<Auction> results = jdbcTemplate.query(
            "SELECT " +
                "a.*,u.name,ifnull(SELECT MAX(price) FROM biddings WHERE auction_seq=?,0) as highestPrice,ifnull(b.price,0) as bidPriceOfMe " +
            "FROM " +
                "auctions a JOIN users u ON a.user_seq=u.seq LEFT OUTER JOIN biddings b ON a.seq=b.auction_seq AND b.user_seq=? " +
            "WHERE " +
                "a.seq=?",
            mapper,
            auctioneerId,
            longinUserId,
            auctioneerId
        );
        return ofNullable(results.isEmpty()? null : results.get(0));
    }

    @Override
    public List<Auction> findByAll(Id<User, Long> loginUserId) {
        List<Auction> results = jdbcTemplate.query(
            "SELECT " +
                "a.*,u.name," +
                "ifnull(select MAX(price) from biddings where auction_seq=a.seq,0) as highestPrice, " +
                "ifnull(b.price,0) as bidPriceOfMe " +
            "FROM " +
                "auctions a JOIN users u ON a.user_seq=u.seq LEFT OUTER JOIN biddings b ON a.seq=b.auction_seq AND b.user_seq=? " +
            "ORDER BY " +
                "a.seq",
            mapper,
            loginUserId
        );
        return null;
    }

    static RowMapper<Auction> mapper = (rs, rowNum) -> new Auction.Builder()
            .seq(rs.getLong("seq"))
            .userId(Id.of(User.class, rs.getLong("user_seq")))
            .title(rs.getString("title"))
            .contents(rs.getString("contents"))
            .reservePrice(rs.getInt("reserve_price"))
            .startTime(rs.getTimestamp("start_time").toLocalDateTime())
            .endTime(rs.getTimestamp("end_time").toLocalDateTime())
            .build();
}
