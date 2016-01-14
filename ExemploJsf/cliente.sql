CREATE TABLE cliente
(
  cliente_id serial NOT NULL,
  cliente_nome character varying(100) NOT NULL,
  cliente_endereco character varying(100) NOT NULL,
  cliente_telefone character varying(15) NOT NULL,
  CONSTRAINT cliente_pk PRIMARY KEY (cliente_id)
)