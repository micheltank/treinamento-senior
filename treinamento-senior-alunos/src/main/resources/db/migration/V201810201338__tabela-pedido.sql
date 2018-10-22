CREATE SEQUENCE pedido_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE pedido (
   id bigint NOT NULL DEFAULT nextval('pedido_id_seq'::regclass),
   data timestamp without time zone NOT NULL,
   cliente_id bigint NOT NULL,
   CONSTRAINT pedido_pkey PRIMARY KEY (id),
   CONSTRAINT cliente_fkey FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);