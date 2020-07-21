DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    phone_number character varying(100) NOT NULL,
    email character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    string character varying(255) NOT NULL
);

INSERT INTO customers VALUES
  (1, 'Brandy', 'Winters', '+48695609770 ', 'bw@mail.com', 'Cracow', 'Rakowicka 21'),
  (2, 'Ksawery', 'Moran', '+48694869284 ', 'km@mail.com', 'Cracow', 'ulica 12-121'),
  (3, 'Kory', 'Vazquez', '+48690437501 ', 'kvazq122e@diffrentmail.com', 'Warsaw', 'inna ulica 7'),
  (4, 'Asa', 'Henderson', ' +48690800209 ', 'asa.h@mail.com', 'Sopot', 'yetAnotherStreet 5'),
  (5, 'Maximus', 'Avery', '+48696855235 ', 'lama121@lama.com', 'Warsaw', 'afaeFEFAFE 13'),
  (6, 'Borys', 'Pemberton', '+48695841632 ', 'borys.p@mail.com', 'Warsaw', 'long street 1'),
  (7, 'Piotr', 'Rojas', '+4270948546 ', '4awfagege@othermail.com', 'Aarhus', 'longship docks 12'),
  (8, 'Kenneth', 'Collier', ' +4277173889 ', 'kc@mail.com', 'Copenhagen', 'last aviable street 3'),
  (9, 'Rachelle', 'Savage', ' +4276544119 ', 'saavage@mail.com', 'Copenhagen', 'tokenStreet 21/3'),
  (10, 'Dafydd', 'Pritchard', '+4273547703 ', 'dpd@mail.com', 'Espjerg', 'Street1 13')