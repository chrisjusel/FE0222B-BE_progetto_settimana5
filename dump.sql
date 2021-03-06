toc.dat                                                                                             0000600 0004000 0002000 00000006012 14262026713 0014441 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        PGDMP       3                    z           multedb    14.4    14.4     ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false         ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false         ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false         ?           1262    16444    multedb    DATABASE     c   CREATE DATABASE multedb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Italian_Italy.1252';
    DROP DATABASE multedb;
                postgres    false         ?            1259    16486 
   infrazione    TABLE     ?   CREATE TABLE multe.infrazione (
    id integer NOT NULL,
    data date NOT NULL,
    tipo character varying(80) NOT NULL,
    importo double precision NOT NULL,
    id_auto integer NOT NULL
);
    DROP TABLE multe.infrazione;
       multe         heap    postgres    false         ?            1259    16485    infrazione_id_seq    SEQUENCE     ?   CREATE SEQUENCE multe.infrazione_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE multe.infrazione_id_seq;
       multe          postgres    false    216         ?           0    0    infrazione_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE multe.infrazione_id_seq OWNED BY multe.infrazione.id;
          multe          postgres    false    215         g           2604    16489    infrazione id    DEFAULT     l   ALTER TABLE ONLY multe.infrazione ALTER COLUMN id SET DEFAULT nextval('multe.infrazione_id_seq'::regclass);
 ;   ALTER TABLE multe.infrazione ALTER COLUMN id DROP DEFAULT;
       multe          postgres    false    215    216    216         ?          0    16486 
   infrazione 
   TABLE DATA           E   COPY multe.infrazione (id, data, tipo, importo, id_auto) FROM stdin;
    multe          postgres    false    216       3319.dat ?           0    0    infrazione_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('multe.infrazione_id_seq', 12, true);
          multe          postgres    false    215         i           2606    16491    infrazione infrazione_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY multe.infrazione
    ADD CONSTRAINT infrazione_pkey PRIMARY KEY (id);
 C   ALTER TABLE ONLY multe.infrazione DROP CONSTRAINT infrazione_pkey;
       multe            postgres    false    216         j           2606    16492    infrazione fk_infrazione_auto    FK CONSTRAINT     y   ALTER TABLE ONLY multe.infrazione
    ADD CONSTRAINT fk_infrazione_auto FOREIGN KEY (id_auto) REFERENCES multe.auto(id);
 F   ALTER TABLE ONLY multe.infrazione DROP CONSTRAINT fk_infrazione_auto;
       multe          postgres    false    216                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              3319.dat                                                                                            0000600 0004000 0002000 00000000375 14262026713 0014261 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        6	2022-08-03	Divieto di sosta	32.5	2
7	2022-08-03	Divieto di sosta	32.5	2
9	2022-08-03	Divieto di sosta	32.5	2
10	2022-05-30	Eccesso di velocità	122.5	4
11	2022-05-03	Ha investito una signora	100	4
12	2022-02-01	Parcheggio sul marciapiede	21.23	6
\.


                                                                                                                                                                                                                                                                   restore.sql                                                                                         0000600 0004000 0002000 00000005751 14262026713 0015377 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        --
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4
-- Dumped by pg_dump version 14.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE multedb;
--
-- Name: multedb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE multedb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Italian_Italy.1252';


ALTER DATABASE multedb OWNER TO postgres;

\connect multedb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: infrazione; Type: TABLE; Schema: multe; Owner: postgres
--

CREATE TABLE multe.infrazione (
    id integer NOT NULL,
    data date NOT NULL,
    tipo character varying(80) NOT NULL,
    importo double precision NOT NULL,
    id_auto integer NOT NULL
);


ALTER TABLE multe.infrazione OWNER TO postgres;

--
-- Name: infrazione_id_seq; Type: SEQUENCE; Schema: multe; Owner: postgres
--

CREATE SEQUENCE multe.infrazione_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE multe.infrazione_id_seq OWNER TO postgres;

--
-- Name: infrazione_id_seq; Type: SEQUENCE OWNED BY; Schema: multe; Owner: postgres
--

ALTER SEQUENCE multe.infrazione_id_seq OWNED BY multe.infrazione.id;


--
-- Name: infrazione id; Type: DEFAULT; Schema: multe; Owner: postgres
--

ALTER TABLE ONLY multe.infrazione ALTER COLUMN id SET DEFAULT nextval('multe.infrazione_id_seq'::regclass);


--
-- Data for Name: infrazione; Type: TABLE DATA; Schema: multe; Owner: postgres
--

COPY multe.infrazione (id, data, tipo, importo, id_auto) FROM stdin;
\.
COPY multe.infrazione (id, data, tipo, importo, id_auto) FROM '$$PATH$$/3319.dat';

--
-- Name: infrazione_id_seq; Type: SEQUENCE SET; Schema: multe; Owner: postgres
--

SELECT pg_catalog.setval('multe.infrazione_id_seq', 12, true);


--
-- Name: infrazione infrazione_pkey; Type: CONSTRAINT; Schema: multe; Owner: postgres
--

ALTER TABLE ONLY multe.infrazione
    ADD CONSTRAINT infrazione_pkey PRIMARY KEY (id);


--
-- Name: infrazione fk_infrazione_auto; Type: FK CONSTRAINT; Schema: multe; Owner: postgres
--

ALTER TABLE ONLY multe.infrazione
    ADD CONSTRAINT fk_infrazione_auto FOREIGN KEY (id_auto) REFERENCES multe.auto(id);


--
-- PostgreSQL database dump complete
--

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       