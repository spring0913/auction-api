package com.auction.api.controller.auction;

import com.auction.api.model.auction.Auction;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class AuctionDto {

    private Long seq;

    private String auctioneer;

    private String title;

    private String contents;

    private int reservePrice;

    private int highestPrice;

    private int bidPriceOfMe;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public AuctionDto(Auction source) {
        copyProperties(source, this);
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getAuctioneer() {
        return auctioneer;
    }

    public void setAuctioneer(String auctioneer) {
        this.auctioneer = auctioneer;
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

    public int getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(int highestPrice) {
        this.highestPrice = highestPrice;
    }

    public int getBidPriceOfMe() {
        return bidPriceOfMe;
    }

    public void setBidPriceOfMe(int bidPriceOfMe) {
        this.bidPriceOfMe = bidPriceOfMe;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
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
            .append("seq", seq)
            .append("auctioneer", auctioneer)
            .append("title", title)
            .append("contents", contents)
            .append("reservePrice", reservePrice)
            .append("highestPrice", highestPrice)
            .append("bidPriceOfMe", bidPriceOfMe)
            .append("startTime", startTime)
            .append("endTime", endTime)
            .toString();
    }
}
