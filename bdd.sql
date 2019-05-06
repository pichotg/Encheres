-- Script de création de la base de données ENCHERES
--   type :      SQL Server 2012
--


CREATE DATABASE ENCHERES
GO
USE ENCHERES
GO

CREATE TABLE CATEGORIES (
    no_categorie   INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(30) NOT NULL
)

ALTER TABLE CATEGORIES ADD constraint categorie_pk PRIMARY KEY (no_categorie)

CREATE TABLE ENCHERES (
    no_utilisateur   INTEGER NOT NULL,
    no_article       INTEGER NOT NULL,
    date_enchere     SMALLDATETIME NOT NULL,
    montant_enchere  INTEGER NOT NULL

)

ALTER TABLE ENCHERES ADD constraint enchere_pk PRIMARY KEY (no_utilisateur, no_article)

CREATE TABLE RETRAITS (
    no_article       INTEGER NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL
)

ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY  (no_article)

CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(20) NOT NULL,
    telephone        VARCHAR(15),
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(10) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(30) NOT NULL,
    credit           INTEGER NOT NULL,
    administrateur   bit NOT NULL,
	etat_utilisateur bit NOT NULL
)

ALTER TABLE UTILISATEURS ADD constraint utilisateur_pk PRIMARY KEY (no_utilisateur)


CREATE TABLE ARTICLES_VENDUS (
    no_article                    INTEGER IDENTITY(1,1) NOT NULL,
    nom_article                   VARCHAR(30) NOT NULL,
    etat_vente					  VARCHAR(5) NOT NULL, 
    description                   VARCHAR(300) NOT NULL,
    date_debut_encheres           SMALLDATETIME NOT NULL,
    date_fin_encheres             SMALLDATETIME NOT NULL,
    prix_initial                  INTEGER,
    prix_vente                    INTEGER,
    no_utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL,
	path_image					  VARCHAR (300)
)

ALTER TABLE ARTICLES_VENDUS ADD constraint articles_vendus_pk PRIMARY KEY (no_article)


ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
ON DELETE CASCADE 
    ON UPDATE no action 

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_articles_vendus_fk FOREIGN KEY ( no_article )
        REFERENCES ARTICLES_VENDUS ( no_article )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE RETRAITS
    ADD CONSTRAINT retraits_articles_vendus_fk FOREIGN KEY ( no_article )
        REFERENCES ARTICLES_VENDUS ( no_article )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( no_categorie )
        REFERENCES categories ( no_categorie )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY ( no_utilisateur )
        REFERENCES utilisateurs ( no_utilisateur )
ON DELETE CASCADE
    ON UPDATE no action


	
INSERT INTO CATEGORIES VALUES('Informatique');
INSERT INTO CATEGORIES VALUES('Ameublement');
INSERT INTO CATEGORIES VALUES('Vêtement');
INSERT INTO CATEGORIES VALUES('Sport&Loisirs');

INSERT INTO UTILISATEURS VALUES('admin','admin','admin','admin@eni.fr','0606060606','rue de l admin','79000','Niort','mdp',5000,1,1);
INSERT INTO UTILISATEURS VALUES('test1','test1','test1','test1@eni.fr','0606060606','rue de test1','79000','Niort','mdp',100,0,1);
INSERT INTO UTILISATEURS VALUES('test2','test2','test2','test2@eni.fr','0606060606','rue de test2','79000','Niort','mdp',200,0,1);
INSERT INTO UTILISATEURS VALUES('test3','test3','test3','test3@eni.fr','0606060606','rue de test3','79000','Niort','mdp',300,0,1);
INSERT INTO UTILISATEURS VALUES('test4','test4','test4','test4@eni.fr','0606060606','rue de test4','79000','Niort','mdp',400,0,1);

INSERT INTO ARTICLES_VENDUS VALUES('bureau','vec','bureau de base','29/04/2019 19:45:45','30/05/2019 19:45:20',100,null,1,2,'');
INSERT INTO ARTICLES_VENDUS VALUES('tablette','vec','tablette de base','29/04/2019 19:45:45','30/05/2019 19:45:20',100,null,2,1,'' );
INSERT INTO ARTICLES_VENDUS VALUES('pull','vnd','pull de base','30/05/2019 19:45:45','30/06/2019 19:45:20',100,null,3,3,'' );
INSERT INTO ARTICLES_VENDUS VALUES('ballon','vec','ballon de base','28/04/2019 19:45:45','30/05/2019 19:45:20',100,,4,4,'' );
INSERT INTO ARTICLES_VENDUS VALUES('skate','vet','skate de base','29/04/2019 19:45:45','30/04/2019 19:45:20',100,300,1,4,'' );
INSERT INTO ARTICLES_VENDUS VALUES('tee-shirt','vet','tee-shirt de base','29/04/2019 19:45:45','30/04/2019 19:45:20',100,150,1,3,'' );
INSERT INTO ARTICLES_VENDUS VALUES('chaise','vet','chaise de base','29/04/2019 19:45:45','30/04/2019 19:45:20',100,400,1,2,'' );
INSERT INTO ARTICLES_VENDUS VALUES('carte graphique','vet','carte graphique de base','29/04/2019 19:45:45','30/04/2019 19:45:20',100,250,1,1,'' );
INSERT INTO ARTICLES_VENDUS VALUES('velo','vec','velo de base','29/04/2019 19:45:45','30/05/2019 19:45:20',100,null,2,4,'');
INSERT INTO ARTICLES_VENDUS VALUES('batte','vec','batte de base','29/04/2019 19:45:45','30/05/2019 19:45:20',100,null,3,4,'');
INSERT INTO ARTICLES_VENDUS VALUES('string','vec','string de base','29/04/2019 19:45:45','30/05/2019 19:45:20',100,null,4,3,'');
INSERT INTO ARTICLES_VENDUS VALUES('souris','vec','souris de base','29/04/2019 19:45:45','30/05/2019 19:45:20',100,null,5,1,'');
INSERT INTO ARTICLES_VENDUS VALUES('lampe','vec','lampe de base','29/04/2019 19:45:45','30/05/2019 19:45:20',100,null,2,2,'');


INSERT INTO ENCHERES VALUES(4,1,'29/04/2019 21:45:45',170);
INSERT INTO ENCHERES VALUES(1,2,'29/04/2019 20:45:45',160);
INSERT INTO ENCHERES VALUES(1,3,'29/04/2019 20:45:45',150);
INSERT INTO ENCHERES VALUES(2,3,'29/04/2019 20:55:45',170);
INSERT INTO ENCHERES VALUES(2,4,'29/04/2019 20:45:45',170);
INSERT INTO ENCHERES VALUES(1,4,'29/04/2019 21:45:45',195);
INSERT INTO ENCHERES VALUES(2,5,'30/04/2019 20:45:45',300);
INSERT INTO ENCHERES VALUES(3,5,'29/04/2019 20:45:45',170);
INSERT INTO ENCHERES VALUES(5,5,'29/04/2019 21:45:45',180);
INSERT INTO ENCHERES VALUES(3,6,'30/04/2019 10:45:45',150);
INSERT INTO ENCHERES VALUES(2,6,'29/04/2019 20:45:45',140);
INSERT INTO ENCHERES VALUES(3,7,'29/04/2019 20:45:45',400);
INSERT INTO ENCHERES VALUES(4,8,'29/04/2019 20:45:45',250);
INSERT INTO ENCHERES VALUES(3,9,'30/04/2019 20:45:45',210);
INSERT INTO ENCHERES VALUES(1,9,'29/04/2019 20:45:45',190);
INSERT INTO ENCHERES VALUES(1,10,'30/04/2019 20:45:45',150);
INSERT INTO ENCHERES VALUES(2,10,'29/04/2019 20:55:45',140);
INSERT INTO ENCHERES VALUES(4,10,'30/04/2019 21:45:45',160);
INSERT INTO ENCHERES VALUES(2,11,'30/04/2019 21:45:45',130);
INSERT INTO ENCHERES VALUES(3,11,'30/04/2019 22:45:45',135);
INSERT INTO ENCHERES VALUES(1,11,'30/04/2019 23:45:45',140);
INSERT INTO ENCHERES VALUES(2,12,'30/04/2019 20:55:45',150);
INSERT INTO ENCHERES VALUES(1,12,'30/04/2019 20:45:45',140);



INSERT INTO RETRAITS VALUES(1,'rue de l admin','79000','Niort');
INSERT INTO RETRAITS VALUES(2,'rue de test1','79000','Niort');
INSERT INTO RETRAITS VALUES(3,'rue de test2','79000','Niort');
INSERT INTO RETRAITS VALUES(4,'rue de test3','79000','Niort');
INSERT INTO RETRAITS VALUES(5,'rue de l admin','79000','Niort');

