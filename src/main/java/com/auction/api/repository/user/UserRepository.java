package com.auction.api.repository.user;

import com.auction.api.model.commons.Id;
import com.auction.api.model.user.Email;
import com.auction.api.model.user.User;

import java.util.Optional;

public interface UserRepository {

    User insert(User user);

    void update(User user);

    Optional<User> findById(Id<User, Long> userId);

    Optional<User> findByEmail(Email email);

    // TODO 유저의 입찰 목록을 가져오는 기능 구현하기
    // List<Bidding> findAllBidding(Id<User, Long> userId);

    // List<Id<Bidding, Long>> findBiddingIds(Id<Bidding, Long> userId);
}
