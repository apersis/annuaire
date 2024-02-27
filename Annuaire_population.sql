DROP database blocagro;

CREATE database blocagro;

USE blocagro;

CREATE TABLE services (
    id_service INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(255) NOT NULL
);

CREATE TABLE sites (
    id_site INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    ville VARCHAR(255) NOT NULL
);


CREATE TABLE salaries (
    id_salarie INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    prenom VARCHAR(255) NOT NULL,
    nom VARCHAR(255) NOT NULL,
    tel_fixe VARCHAR(255) NOT NULL,
    tel_portable VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    id_site INT NOT NULL,
    id_service INT NOT NULL,
    FOREIGN KEY (id_site) REFERENCES sites(id_site),
    FOREIGN KEY (id_service) REFERENCES services(id_service)
);


ALTER TABLE services AUTO_INCREMENT = 1;
ALTER TABLE sites AUTO_INCREMENT = 1;
ALTER TABLE salaries AUTO_INCREMENT = 1;


insert into services(libelle) values
('comptabilité'),
('production'),
('accueil'),
('informatique'),
('commercial');

insert into sites(type, ville) values
('administratif', 'Paris'),
('production', 'Nantes'),
('production', 'Toulouse'),
('production', 'Nice'),
('production', 'Lille');


insert into salaries(nom, prenom, tel_fixe, tel_portable, email, id_site, id_service) values
('Dupont', 'Jean', '0236545895', '0677985841', 'dupont.jean@agrofrais.fr', 1, 1),
('Martin', 'Marie', '0263689541', '0685963214', 'martin.marie@agrofrais.fr', 2, 2),
('Dubois', 'Pierre', '0278963215', '0647853120', 'dubois.pierre@agrofrais.fr', 3, 3),
('Leroy', 'Sophie', '0285456987', '0645362105', 'leroy.sophie@agrofrais.fr', 4, 2),
('Moreau', 'Pauline', '0245963235','0602233135', 'moreau.pauline@agrofrais.fr', 5, 2),
('Lefevre', 'Thomas', '0225698741', '0632101568', 'lefevre.thomas@agrofrais.fr', 4, 2),
('Simon', 'Emilie', '0278989963', '0674532102', 'simon.emilie@agrofrais.fr', 1, 4),
('Laurent', 'Antoine', '0296321457', '0674536201', 'laurent.antoine@agrofrais.fr', 3, 2),
('Girard', 'Charlotte', '0278969321', '0678996312', 'girard.charlotte@agrofrais.fr', 5, 2),
('Rousseau', 'Lucas', '0211121517', '0614589632', 'rousseau.lucas@agrofrais.fr', 2, 2),
('Petit', 'Manon', '0225367672', '0696874100', 'petit.manon@agrofrais.fr', 1, 5),
('Garcia', 'Julien', '0236698774', '0614101233', 'garcia.julien@agrofrais.fr', 5, 2),
('Martinez', 'Emma', '0214456875', '0614121011', 'martinez.emma@agrofrais.fr', 4, 3),
('Fernandez', 'Hugo', '0232333639', '0688896114', 'fernandez.hugo@agrofrais.fr', 2, 3),
('Bertrand', 'Camille', '0214151320', '0612141563', 'bertrand.camille@agrofrais.fr', 3, 2),
('Dubois', 'Nicolas', '0214151899', '0620232526', 'dubois.nicolas@agrofrais.fr', 1, 5),
('Renault', 'Clara', '0221111486', '0610123115', 'renault.clara@agrofrais.fr', 1, 3),
('Michel', 'Nathan', '0215174663', '0625252420', 'michel.nathan@agrofrais.fr', 5, 3),
('Leroux', 'Léa', '0214986622', '0620126810', 'leroux.lea@agrofrais.fr', 2, 2),
('Bernard', 'Enzo', '0202368210', '0620159781', 'bernard.enzo@agrofrais.fr', 3, 2);
