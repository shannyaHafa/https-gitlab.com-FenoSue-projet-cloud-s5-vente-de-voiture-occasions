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
    id varchar default('User'||nextval('refUtilisateur')) primary key,
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
    utilisateur varchar references Utilisateur (id),
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
    id varchar default('ref00'||nextval('refProduit')) primary key,
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
    id varchar default('Photo'||nextval('refPhotoProduit')) primary key,
    idProduit varchar references Produit (id),
    img varchar not null
);

create sequence refAnnonce;
create table Annonce (
    id varchar default('Annonce'||nextval('refAnnonce')) primary key,
    idProduit varchar references Produit (id),
    idUtilisateur varchar references Utilisateur (id),
    dateHeure timestamp default now(),
    description varchar,
    statut integer default -1,
    dateStatut timestamp default null,
    etat integer default -1,
    favoris integer default -1
);

create view AnnonceNonValider as
select a.*, m.nom as marque, m2.nom as modele, v.nom as boitevitesse, c.nom as categorie, c2.nom as typecarburant, p.kilometrage as kilometrage, 
p.anneesortie as anneesortie, p.couleur as couleur, p.nbrplace as nbrplace, p.prix as prix
from annonce a join produit p on a.idproduit = p.id join marque m on p.idmarque = m.id join modele m2 on p.idmodele = m2.id join vitesse v on p.idvitesse = v.id 
join categorie c on p.idcategorie = c.id join carburant c2 on p.idcarburant = c2.id where etat=-1;

create view AnnonceValider as
select a.*, m.nom as marque, m2.nom as modele, v.nom as boitevitesse, c.nom as categorie, c2.nom as typecarburant, p.kilometrage as kilometrage, 
p.anneesortie as anneesortie, p.couleur as couleur, p.nbrplace as nbrplace, p.prix as prix
from annonce a join produit p on a.idproduit = p.id join marque m on p.idmarque = m.id join modele m2 on p.idmodele = m2.id join vitesse v on p.idvitesse = v.id 
join categorie c on p.idcategorie = c.id join carburant c2 on p.idcarburant = c2.id where etat=1;

create view ListeAnnonce as
select * from Annoncevalider where statut=-1;

create view HistoriqueAnnonce as
select * from annoncevalider where statut=1;

create view AnnonceFavoris as
select * from listeannonce where favoris=1 and statut=-1;
