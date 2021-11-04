package com.auction.api.service.auction;

import com.auction.api.model.auction.Auction;
import com.auction.api.repository.auction.AuctionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @Transactional
    public Auction open(Auction auction){
        return auctionRepository.insert(auction);
    }
}
