package com.auction.api.repository.auction;

import com.auction.api.model.auction.Auction;
import com.auction.api.model.commons.Id;
import com.auction.api.model.user.User;

import java.util.List;
import java.util.Optional;

public interface AuctionRepository {

    Auction insert(Auction auction);

    void update(Auction auction);

    Optional<Auction> findById(Id<Auction, Long> auctionId, Id<User, Long> auctioneerId, Id<User, Long> longinUserId);

    List<Auction> findByAll(Id<User, Long> loginUserId);
}
