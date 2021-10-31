package com.auction.api.model.auction;

import com.auction.api.model.commons.Id;
import com.auction.api.model.user.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class Auction {

    private final Long seq;

    // 경매 생성 유저
    private final Id<User, Long> userId;

    private final String title;

    private String contents;

    private final int reservePrice;

    // 경매 최고가
    private int highestPrice;

    private int bidPriceOfMe;

    private final LocalDateTime startTime;

    private final LocalDateTime endTime;

    public Auction(Id<User, Long> userId, String title, LocalDateTime endTime){
        this(null, userId, title, null, 0, 0, 0, null, endTime);
    }

    public Auction(Long seq, Id<User, Long> userId, String title, String contents, int reservePrice, int highestPrice, int bidPriceOfMe, LocalDateTime startTime, LocalDateTime endTime) {
        checkArgument(userId != null, "userId must be provided.");
        checkArgument(isNotEmpty(title), "title must be provided.");
        checkArgument(title.length() >= 3 && title.length() <= 300,
                "auction title length must be between 3 and 300 characters.");
        checkArgument(reservePrice >= 0, "reserve price must be greater or equal to 0.");
        checkArgument(highestPrice >= reservePrice, "highest price must be greater than reserve price.");
        checkArgument(bidPriceOfMe >= reservePrice, "bid price must be greater than reserve price.");
        checkArgument(endTime.isAfter(startTime), "end time must be later than start time.");

        this.seq = seq;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.reservePrice = reservePrice;
        this.highestPrice = highestPrice;
        this.bidPriceOfMe = bidPriceOfMe;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int updateHighestPrice(int bidPrice){
        if(bidPrice > highestPrice) {
            highestPrice = bidPrice;
        }
        return highestPrice;
    }

    public Long getSeq() {
        return seq;
    }

    public Id<User, Long> getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public int getReservePrice() {
        return reservePrice;
    }

    public int getHighestPrice() {
        return highestPrice;
    }

    public int getBidPriceOfMe() {
        return bidPriceOfMe;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return Objects.equals(seq, auction.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("seq", seq)
            .append("userId", userId)
            .append("title", title)
            .append("contents", contents)
            .append("reservePrice", reservePrice)
            .append("highestPrice", highestPrice)
            .append("bidPriceOfMe", bidPriceOfMe)
            .append("startTime", startTime)
            .append("endTime", endTime)
            .toString();
    }

    static public class Builder{
        private Long seq;
        private Id<User, Long> userId;
        private String title;
        private String contents;
        private int reservePrice;
        private int highestPrice;
        private int bidPriceOfMe;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public Builder(){
        }

        public Builder(Auction auction) {
            this.seq = auction.seq;
            this.userId = auction.userId;
            this.title = auction.title;
            this.contents = auction.contents;
            this.reservePrice = auction.reservePrice;
            this.highestPrice = auction.highestPrice;
            this.bidPriceOfMe = auction.bidPriceOfMe;
            this.startTime = auction.startTime;
            this.endTime = auction.endTime;
        }

        public Builder seq(Long seq){
            this.seq = seq;
            return this;
        }

        public Builder userId(Id<User, Long> userId){
            this.userId = userId;
            return this;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder contents(String contents){
            this.contents = contents;
            return this;
        }

        public Builder reservePrice(int reservePrice){
            this.reservePrice = reservePrice;
            return this;
        }

        public Builder highestPrice(int highestPrice){
            this.highestPrice = highestPrice;
            return this;
        }

        public Builder bidPriceOfMe(int bidPriceOfMe){
            this.bidPriceOfMe = bidPriceOfMe;
            return this;
        }

        public Builder startTime(LocalDateTime startTime){
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalDateTime startTime){
            this.endTime = endTime;
            return this;
        }

        public Auction build(){
            return new Auction(seq, userId, title, contents, reservePrice, highestPrice, bidPriceOfMe, startTime, endTime);
        }
    }
}
