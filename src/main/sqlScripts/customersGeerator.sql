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
  ('x%[>j!X#', 'Brandy', 'Winters', '+48695609770 ', 'bw@mail.com', 'Cracow', 'Rakowicka 21', '135527348657'),
  ('JgsMz0d1', 'Ksawery', 'Moran', '+48694869284 ', 'km@mail.com', 'Cracow', 'ulica 12-121', '2467547'),
  ('ve55[R<W', 'Kory', 'Vazquez', '+48690437501 ', 'kvazq122e@diffrentmail.com', 'Warsaw', 'inna ulica 7', '132537'),
  ('M+MIQP<f', 'Asa', 'Henderson', ' +48690800209 ', 'asa.h@mail.com', 'Sopot', 'yetAnotherStreet 5', '6735342'),
  (')+e)CWq!', 'Maximus', 'Avery', '+48696855235 ', 'lama121@lama.com', 'Warsaw', 'afaeFEFAFE 13', '2465684759'),
  ('XtPra1XX', 'Borys', 'Pemberton', '+48695841632 ', 'borys.p@mail.com', 'Warsaw', 'long street 1', '7986532'),
  ('{+9Zz~][', 'Piotr', 'Rojas', '+4270948546 ', '4awfagege@othermail.com', 'Aarhus', 'longship docks 12', '74876533'),
  ('}hLNoEYu', 'Kenneth', 'Collier', ' +4277173889 ', 'kc@mail.com', 'Copenhagen', 'last aviable street 3', '25876534'),
  ('?HGTLmu$', 'Rachelle', 'Savage', ' +4276544119 ', 'saavage@mail.com', 'Copenhagen', 'TStreet 21/3', '8467634'),
  ('FqzX/73~', 'Dafydd', 'Pritchard', '+4273547703 ', 'dpd@mail.com', 'Espjerg', 'Street1 13', '675342')