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

create sequence refCategorie;
create table Categorie (
    id integer default(nextval('refCategorie')) primary key,
    nom varchar
);

create sequence refVitesse;
create table Vitesse (
    id integer default(nextval('refVitesse')) primary key,
    nom varchar
);

create sequence refCarburant;
create table Carburant (
    id integer default(nextval('refCarburant')) primary key,
    nom varchar
);

create sequence refMarque;
create table Marque (
    id integer default(nextval('refMarque')) primary key,
    nom varchar
);

create sequence refModele;
create table Modele (
    id integer default(nextval('refModele')) primary key,
    idMarque integer references Marque (id),
    nom varchar
);

create sequence refProduit;
create table Produit (
    id integer default(nextval('refDetailProduit')) primary key,
    idMarque integer references Marque (id),
    idModele integer references Modele (id),
    matricule varchar not null,
    idVitesse integer references Vitesse (id),
    idCategorie integer references Categorie (id),
    idCarburant integer references Carburant (id),
    kilometrage float,
    anneeSortie integer not null,
    couleur varchar not null,
    nbrPlace integer not null,
    prix numeric(12,2) not null
);

create sequence refPhotoProduit;
create table PhotoProduit (
    id integer default(nextval('refPhotoProduit')) primary key,
    idProduit references Produit (id),
    img varchar not null
);

create sequence refAnnonce;
create table Annonce (
    id integer default(nextval('refAnnonce')) primary key,
    idProduit integer references Produit (id),
    idUtilisateur integer references Utilisateur (id),
    dateHeure timestamp default now(),
    description varchar,
    status integer default -1,
    etat integer default -1,
    favoris integer default -1
);


