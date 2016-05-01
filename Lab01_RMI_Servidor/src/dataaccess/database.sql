-- Database: sistdis_lab01

-- DROP DATABASE sistdis_lab01;

CREATE DATABASE sistdis_lab01
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'es_CL.UTF-8'
       LC_CTYPE = 'es_CL.UTF-8'
       CONNECTION LIMIT = -1;

COMMENT ON DATABASE sistdis_lab01
  IS 'Base de datos de prueba para probar la arquitectura Cliente-Servidor';

-- Table: finanza

-- DROP TABLE finanza;

CREATE TABLE finanza
(
  id integer NOT NULL,
  nombrepersona character varying(100) NOT NULL,
  fchmovimiento date NOT NULL,
  saldomovimiento numeric(12,3) NOT NULL,
  CONSTRAINT finanza_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE finanza
  OWNER TO postgres;


-- Table: login

-- DROP TABLE login;

CREATE TABLE login
(
  id integer NOT NULL,
  username character varying(25) NOT NULL,
  password character varying(500) NOT NULL,
  rol character varying(25) DEFAULT NULL::character varying,
  nombrecompleto character varying(100),
  CONSTRAINT login_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE login
  OWNER TO postgres;



-- Table: recursohumano

-- DROP TABLE recursohumano;

CREATE TABLE recursohumano
(
  id integer NOT NULL,
  nombrepersona character varying(100) NOT NULL,
  fchnacimiento date NOT NULL,
  direccion character varying(100) NOT NULL,
  comuna character varying(50) NOT NULL,
  region character varying(50) NOT NULL,
  email character varying(100),
  telefono character varying(25),
  sexo character varying(10) NOT NULL,
  fchcontrato date NOT NULL,
  departamento character varying(50) NOT NULL,
  CONSTRAINT recursohumano_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE recursohumano
  OWNER TO postgres;

