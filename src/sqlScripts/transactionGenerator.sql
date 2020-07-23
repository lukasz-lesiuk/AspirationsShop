DROP TABLE IF EXISTS transaction_details;
DROP TABLE IF EXISTS transaction_product;


CREATE TABLE transaction_details (
    transactionID character varying(255) NOT NULL PRIMARY KEY,
    customerID character varying(255) NOT NULL,
    transaction_date date NOT NULL
);

CREATE TABLE transaction_product (
    ID SERIAL PRIMARY KEY,
    transactionID character varying(255) NOT NULL FOREIGN KEY,
    productID character varying(255) NOT NULL,
    cost integer NOT NULL,
    quantity integer NOT NULL,
);

INSERT INTO transaction_details VALUES
(),
();

INSERT INTO transaction_product VALUES
(),
();
