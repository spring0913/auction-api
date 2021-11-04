package com.auction.api.controller.auction;

import com.auction.api.model.auction.Auction;
import com.auction.api.model.commons.Id;
import com.auction.api.model.user.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

public class AuctionRequest {

    private String title;

    private String contents;

    private int reservePrice;

    private LocalDateTime endTime;

    public AuctionRequest() {}

    public Auction newAuction(Id<User, Long> userId, String auctioneer) {
        return new Auction(userId, title, contents, reservePrice, auctioneer, endTime);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(int reservePrice) {
        this.reservePrice = reservePrice;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ToStringStyle.SHORT_PREFIX_STYLE)
            .append("title", title)
            .append("contents", contents)
            .append("reservePrice", reservePrice)
            .append("endTime", endTime)
            .toString();
    }
}
