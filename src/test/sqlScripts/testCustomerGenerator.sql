DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
    id character varying(10) PRIMARY KEY,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    phone_number character varying(100) NOT NULL,
    email character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    street character varying(255) NOT NULL,
    hash_password character varying(255) NOT NULL
);

INSERT INTO customers VALUES
  ('x%[>j!X#', 'Adam', 'Nowak', '+48695609770', 'alpaka@mlamamail.com', 'Cracow', 'Rakowicka 21', '12745634'),
  ('JgsMz0d1', 'Szymon', 'Kowalski', '+48694869284', 'mail2@mail.com', 'Cracow', 'ulica 12-121', '32155726'),
  ('ve55[R<W', 'John', 'Lama', '+48690437501', 'mail3@diffrentmail.com', 'Dehli', 'inna ulica 7', '243663543'),
  ('M+MIQP<f', 'Mike', 'Huntingthon', '+48690800209', 'mail4@mail.com', 'Nagoja', 'yetAnotherStreet 5', '2354624')
