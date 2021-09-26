-- MySQL

-- CREATE TABLE IF NOT EXISTS public.users (
--     id int NOT NULL AUTO_INCREMENT,
--     email varchar(150),
--     first_name varchar(50),
--     last_name varchar(50),
--     password varchar(100),
--     role varchar(15),
--     status varchar(15)
-- ) ;
--
-- CREATE TABLE IF NOT EXISTS public.posts (
--     id int NOT NULL AUTO_INCREMENT,
--     title varchar(150),
--     anons varchar(100),
--     full_text varchar(250)
-- ) ;
--
-- INSERT INTO users (email, first_name, last_name, password, role, status)
-- VALUES
--     ('admin@mail.ru', 'Mike', 'Jones', 'admin', 'ROLE_ADMIN', 'ACTIVE'),
--     ('user@mail.ru', 'Ann', 'Parrow', 'user', 'ROLE_USER', 'ACTIVE');



-- PostgreSQL

CREATE TABLE IF NOT EXISTS posts (
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    title character varying(150) COLLATE pg_catalog."default",
    anons character varying(100) COLLATE pg_catalog."default",
    full_text character varying(250) COLLATE pg_catalog."default"
);

CREATE TABLE IF NOT EXISTS users (
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    email character varying(150) COLLATE pg_catalog."default" NOT null,
    first_name character varying(50) COLLATE pg_catalog."default",
    last_name character varying(50) COLLATE pg_catalog."default",
    password character varying(100) COLLATE pg_catalog."default" NOT null,
    role character varying(15) COLLATE pg_catalog."default",
    status character varying(15) COLLATE pg_catalog."default"
);

INSERT INTO users (email, first_name, last_name, password, role, status)
VALUES
    ('admin@mail.ru', 'Mike', 'Jones', 'admin', 'ROLE_ADMIN', 'ACTIVE'),
    ('user@mail.ru', 'Ann', 'Parrow', 'user', 'ROLE_USER', 'ACTIVE');

INSERT INTO posts (title, anons, full_text)
VALUES
    ('Google is a bankrupt', 'Several things which caused bankruptcy', 'A lot of thing now happened with Google. According to research...'),
    ('Animals', 'Chocolatier Protecting Madagascar’s Lemurs Eyes Carbon Credits', 'Beyond Good, a maker of premium chocolate in Madagascar, aims to start selling carbon....');