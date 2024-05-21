CREATE TABLE IF NOT EXISTS products (
    id integer NOT NULL,
    name character varying(128) NOT NULL,
    price numeric
);

CREATE TABLE IF NOT EXISTS markets (
    id integer NOT NULL,
    name character varying(128) NOT NULL,
    address character varying(128) NOT NULL
);