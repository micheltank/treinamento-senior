CREATE SEQUENCE pedido_item_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE pedido_item (
   id bigint NOT NULL DEFAULT nextval('pedido_item_id_seq'::regclass),
   pedido_id bigint NOT NULL,
   produto varchar(255) NOT NULL,
   CONSTRAINT pedido_fkey FOREIGN KEY (pedido_id) REFERENCES pedido(id),
   CONSTRAINT pedido_item_pkey PRIMARY KEY (id)
);