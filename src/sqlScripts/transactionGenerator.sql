DROP TABLE IF EXISTS transaction_details;
DROP TABLE IF EXISTS transaction_product;


CREATE TABLE transaction_details (
    transactionID character varying(255) NOT NULL,
    customerID character varying(255) NOT NULL,
    transaction_date date NOT NULL,

    PRIMARY KEY (transactionID)
);


INSERT INTO transaction_details VALUES
('11111111', '22222222', '2020-07-15'),
('33333333', '44444444', '2020-07-16');


CREATE TABLE transaction_product (
    ID SERIAL,
    transactionID character varying(255) NOT NULL,
    productID character varying(255) NOT NULL,
    prize integer NOT NULL,
    quantity integer NOT NULL,

    PRIMARY KEY (ID),
    FOREIGN KEY (transactionID) REFERENCES transaction_details(transactionID)
);

INSERT INTO transaction_product VALUES
(1, '11111111', 'aaaaaaaa', 15, 1),
(2, '11111111', 'bbbbbbbb', 20, 3),
(3, '33333333', 'aaaaaaaa', 15, 2),
(4, '33333333', 'bbbbbbbb', 20, 2),
(5, '33333333', 'cccccccc', 10, 2);
