INSERT INTO users(seq,name,email,passwd) VALUES (null,'bmsung','bmsung@gmail.com','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4');
INSERT INTO users(seq,name,email,passwd) VALUES (null,'bbomi','bbomi@gmail.com','fe2592b42a727e977f055947385b709cc82b16b9a87f88c6abf3900d65d0cdc3');
INSERT INTO users(seq,name,email,passwd) VALUES (null,'spring','spring@gmail.com','310ced37200b1a0dae25edb263fe52c491f6e467268acab0ffec06666e2ed959');

INSERT INTO auctions(seq,user_seq,title,contents,reserve_price,end_time) VALUES (null,1,'auction1','bmsung first auction',1000,'2021-11-10 13:00:00');
INSERT INTO auctions(seq,user_seq,title,contents,reserve_price,end_time) VALUES (null,2,'auction2','bbomi first auction',2000,'2021-11-7 13:00:00');
INSERT INTO auctions(seq,user_seq,title,contents,reserve_price,end_time) VALUES (null,2,'auction3','bbomi second auction',3000,'2021-11-11 15:10:00');

INSERT INTO biddings(seq, user_seq,auction_seq,price) values (null,1,2,3000);
INSERT INTO biddings(seq, user_seq,auction_seq,price) values (null,3,2,7000);
INSERT INTO biddings(seq, user_seq,auction_seq,price) values (null,2,1,5000);