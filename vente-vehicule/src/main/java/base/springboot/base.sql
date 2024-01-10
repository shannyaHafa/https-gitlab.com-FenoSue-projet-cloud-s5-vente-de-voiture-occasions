/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  USER
 * Created: 17 oct. 2023
 */

create database VenteVehicule;
alter database VenteVehicule owner to projet;

create sequence refUtilisateur;
create table Utilisateur (
    id integer default(nextval('refUtilisateur')) primary key,
    login varchar unique not null,
    pwd varchar not null,
    roles varchar not null
);
insert into Utilisateur (login,pwd,roles) values
('Soa','1234','admin'),
('Solo','solo','user'),
('Maria','1234','user');

create sequence refToken;
create table Token (
    id integer default(nextval('refToken')) primary key,
    utilisateur integer references Utilisateur (id),
    token varchar unique,
    dateExpiration date
);
