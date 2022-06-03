INSERT INTO ROLE (name) VALUES ('ADMIN');
INSERT INTO ROLE (name) VALUES ('CUSTOMER');
INSERT INTO USER (email,password,name,phone_number,user_type,user_status,is_deleted,created_at)
VALUES ('emirhan@gmail.com','$2a$10$ReWLWNLk6iSPWZ1z6tVv1efkk0qgONxAoZaD6BJ8I80qe0ykfLqma',
        'Emirhan Ay','05365785476','ADMIN','ACTIVE','false','2022-06-01 23:27:03.534');
INSERT INTO USER_ROLE (user_id,role_id) VALUES ('1','1');

INSERT INTO USER (email,password,name,phone_number,user_type,user_status,is_deleted,created_at)
VALUES ('koton@gmail.com','$2a$10$ReWLWNLk6iSPWZ1z6tVv1efkk0qgONxAoZaD6BJ8I80qe0ykfLqma',
        'Koton','05365783376','CUSTOMER','ACTIVE','false','2022-06-01 23:27:03.534');
INSERT INTO CUSTOMER (user_id,identity_no,monthly_earning) VALUES (2,11653882324,500000);
INSERT INTO ACCOUNT (iban,balance,currency,customer_id,account_type) VALUES ('TR100906673340009901154555'
                                                                            ,'15000','TRY','1','CORPORATE');
INSERT INTO USER_ROLE (user_id,role_id) VALUES ('2','1');
INSERT INTO CARD (card_no,expiration_date,cvv,card_type,customer_id) VALUES ('1900458325679396','2032-06-02','738','BANK','1');
INSERT INTO BANK_CARD (CARD_ID) VALUES ('1');
INSERT INTO DRAWING_ACCOUNT (ACCOUNT_ID,BANK_CARD_ID) VALUES ('1','1')