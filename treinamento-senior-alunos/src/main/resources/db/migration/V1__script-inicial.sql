CREATE SEQUENCE cliente_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE cliente (
   id bigint NOT NULL DEFAULT nextval('cliente_id_seq'::regclass),
   nome character varying (255) NOT NULL,
   CONSTRAINT cliente_pkey PRIMARY KEY (id)
);