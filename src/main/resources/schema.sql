DROP TABLE IF EXISTS bidders CASCADE;
DROP TABLE IF EXISTS auctions CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- 사용자 데이터
CREATE TABLE users
(
    seq                 bigint          NOT NULL AUTO_INCREMENT,                -- 사용자 PK
    name                varchar(10)     NOT NULL,                               -- 이름
    email               varchar(50)     NOT NULL,                               -- 로그인 이메일
    passwd              varchar(80)     NOT NULL,                               -- 비밀번호
    create_at           datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP(),   -- 생성일시
    PRIMARY KEY (seq),
    CONSTRAINT unq_user_email UNIQUE(email)
);

-- 경매 데이터
CREATE TABLE auctions
(
    seq                 bigint          NOT NULL AUTO_INCREMENT,                -- 경매 PK
    user_seq            bigint          NOT NULL,                               -- 판매자 PK
    title               varchar(300)    NOT NULL,                               -- 경매 이름
    contents            varchar(500)    NOT NULL,                               -- 경매 내용
    reserve_price       int             NOT NULL DEFAULT 0,                     -- 최저 경매 가격
    start_time          datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP(),   -- 경매 시작일시(생성일시)
    end_time            datetime        NOT NULL,                               -- 경매 종료일시
    PRIMARY KEY (seq),
    CONSTRAINT fk_auction_to_user FOREIGN KEY (user_seq) REFERENCES users (seq) ON DELETE RESTRICT ON UPDATE RESTRICT
);

-- 입찰 데이터
-- 해당 테이블의 Row 데이터는 `user_seq` 에 해당하는 사용자가 `auction_seq` 가 가리키는 경매에 입찰했음을 의미
CREATE TABLE biddings
(
    seq                 bigint          NOT NULL AUTO_INCREMENT,                -- 입찰 PK
    user_seq            bigint          NOT NULL,                               -- 입찰자 PK
    auction_seq         bigint          NOT NULL,                               -- 경매 PK
    price               bigint          NOT NULL DEFAULT 0,                     -- 입찰가
    create_at           datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP(),   -- 생성일시
    PRIMARY KEY (seq),
    CONSTRAINT unq_bidding_user_auction UNIQUE (user_seq, auction_seq),
    CONSTRAINT fk_bidding_to_user FOREIGN KEY (user_seq) REFERENCES users (seq) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_bidding_to_auction FOREIGN KEY (auction_seq) REFERENCES auctions (seq) ON DELETE RESTRICT ON UPDATE RESTRICT
);