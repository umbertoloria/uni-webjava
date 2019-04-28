create table categorie (
    id   int auto_increment
        primary key,
    nome varchar(50) not null
);

create table utenti (
    id       int auto_increment
        primary key,
    email    varchar(40)                                          not null,
    password varchar(40)                                          not null,
    nome     varchar(40)                                          not null,
    tipo     enum ('Normale', 'Amministratore') default 'Normale' not null,
    constraint email
        unique (email)
);

create table sottocategorie (
    id        int auto_increment
        primary key,
    categoria int         not null,
    nome      varchar(50) not null,
    constraint nome
        unique (nome),
    constraint sottocategorie_ibfk_1
        foreign key (categoria) references categorie (id)
            on update cascade
);

create index categoria
    on sottocategorie (categoria);

create table ordini (
    id           int auto_increment
        primary key,
    utente       int          not null,
    destinazione varchar(100) not null,
    constraint ordini_ibfk_1
        foreign key (utente) references utenti (id)
            on update cascade
);

create index utente
    on ordini (utente);

create table produttori (
    id   int auto_increment
        primary key,
    nome varchar(20) not null
);

create table prodotti (
    id             int auto_increment
        primary key,
    sottocategoria int           not null,
    nome           varchar(50)   not null,
    produttore     int           not null,
    prezzo         decimal(6, 2) not null,
    immagine       varchar(500)  not null,
    descrizione    text          not null,
    constraint prodotti_ibfk_1
        foreign key (produttore) references produttori (id)
            on update cascade,
    constraint prodotti_ibfk_2
        foreign key (sottocategoria) references sottocategorie (id)
            on update cascade
);

create index produttore
    on prodotti (produttore);

create index sottocategoria
    on prodotti (sottocategoria);

create table ordine_has_prodotto (
    ordine   int           not null,
    prodotto int           not null,
    prezzo   decimal(6, 2) not null,
    quantita int           not null,
    primary key (ordine, prodotto),
    constraint ordine_has_prodotto_ibfk_1
        foreign key (ordine) references ordini (id)
            on update cascade,
    constraint ordine_has_prodotto_ibfk_2
        foreign key (prodotto) references prodotti (id)
            on update cascade
);

create index prodotto
    on ordine_has_prodotto (prodotto);

INSERT INTO ecommerce.categorie (id, nome)
VALUES (1, 'Chitarra');
INSERT INTO ecommerce.categorie (id, nome)
VALUES (2, 'Basso');
INSERT INTO ecommerce.categorie (id, nome)
VALUES (3, 'Batteria');
INSERT INTO ecommerce.categorie (id, nome)
VALUES (4, 'Pianoforte');
INSERT INTO ecommerce.categorie (id, nome)
VALUES (5, 'Tastiera');
INSERT INTO ecommerce.categorie (id, nome)
VALUES (6, 'Microfono');

INSERT INTO ecommerce.produttori (id, nome)
VALUES (1, 'Sonor');
INSERT INTO ecommerce.produttori (id, nome)
VALUES (2, 'Fender');
INSERT INTO ecommerce.produttori (id, nome)
VALUES (3, 'EKO');
INSERT INTO ecommerce.produttori (id, nome)
VALUES (4, 'Datestore');
INSERT INTO ecommerce.produttori (id, nome)
VALUES (5, 'Ibanez');
INSERT INTO ecommerce.produttori (id, nome)
VALUES (6, 'Kala U-BASS');
INSERT INTO ecommerce.produttori (id, nome)
VALUES (7, 'Sire');
INSERT INTO ecommerce.produttori (id, nome)
VALUES (8, 'Beat');
INSERT INTO ecommerce.produttori (id, nome)
VALUES (9, 'Alesis');
INSERT INTO ecommerce.produttori (id, nome)
VALUES (10, 'Artesia');
INSERT INTO ecommerce.produttori (id, nome)
VALUES (11, 'Ringway');
INSERT INTO ecommerce.produttori (id, nome)
VALUES (12, 'Casio');
INSERT INTO ecommerce.produttori (id, nome)
VALUES (13, 'Proel');
INSERT INTO ecommerce.produttori (id, nome)
VALUES (14, 'Behringer');

INSERT INTO ecommerce.sottocategorie (id, categoria, nome)
VALUES (1, 1, 'Chitarra Classica');
INSERT INTO ecommerce.sottocategorie (id, categoria, nome)
VALUES (2, 1, 'Chitarra Acustica');
INSERT INTO ecommerce.sottocategorie (id, categoria, nome)
VALUES (3, 1, 'Chitarra Elettrica');
INSERT INTO ecommerce.sottocategorie (id, categoria, nome)
VALUES (4, 2, 'Basso Elettrico');
INSERT INTO ecommerce.sottocategorie (id, categoria, nome)
VALUES (5, 2, 'Basso Acustico');
INSERT INTO ecommerce.sottocategorie (id, categoria, nome)
VALUES (6, 2, 'Basso Mancino');
INSERT INTO ecommerce.sottocategorie (id, categoria, nome)
VALUES (7, 3, 'Batteria Acustica');
INSERT INTO ecommerce.sottocategorie (id, categoria, nome)
VALUES (8, 3, 'Batteria Elettrica');
INSERT INTO ecommerce.sottocategorie (id, categoria, nome)
VALUES (9, 4, 'Pianoforte da palco');
INSERT INTO ecommerce.sottocategorie (id, categoria, nome)
VALUES (10, 4, 'Pianoforte digitale');
INSERT INTO ecommerce.sottocategorie (id, categoria, nome)
VALUES (11, 5, 'Arranger');
INSERT INTO ecommerce.sottocategorie (id, categoria, nome)
VALUES (12, 6, 'Microfono vocale');

INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (1, 7, 'Sonor 507', 1, 550.00, 'https://d1aeri3ty3izns.cloudfront.net/media/9/9349/1200/preview.jpg',
        'Ottima batteria per cominciare!');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (2, 3, 'Stratocaster', 2, 900.00,
        'http://www.strumentimusicaliceccaroni.com/689-thickbox_default/chitarra-elettrica-fender-stratocaster-roadhouse-dlx-strat-bsb.jpg',
        'Chitarra assolutamente epica.');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (3, 1, 'CS5 Natural', 3, 49.00,
        'https://www.strumentimusicali.net/images/product/600x450/2017/09/25/24/eko-cs5nat-1.jpg',
        'La CS 5 è una chitarra classica con body da 36", top, fondo e fasce in Tiglio. Il manico e la tastiera sono invece in Betulla.');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (4, 3, 'S300 Sunburst', 3, 99.00, 'https://images-na.ssl-images-amazon.com/images/I/71wtrMlDdTL._SX466_.jpg',
        'configurazione dei pickup S/S/S, due toni, un volume ed un selettore a 5 posizioni.');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (5, 2, 'AG1 Blue', 4, 69.00,
        'https://cdn1.bigcommerce.com/server1000/e1ff9/products/140/images/1410/CG183SBL__79363.1544574408.900.700.jpg?c=2',
        'La selezione dei materiali e la precisione dei dettagli ne fa emergere la notevole sonorità della cassa armonica');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (6, 4, 'TMB30 BK Black', 5, 170.00,
        'https://www.strumentimusicali.net/images/product/600x450/2016/11/15/17/ibanez-tmb30bk.jpg',
        'Basso elettrico nero la nuova serie di bassi Talman combina il riemergere di una forma storica.');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (7, 5, 'Passenger Acoustic', 6, 285.00,
        'https://www.strumentimusicali.net/images/product/600x450/2018/08/27/f0/kala-ubasswandererfs-3.jpg',
        'Basso acustico elettrificato a scala ridotta con borsa.');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (8, 6, 'Marcus Miller', 7, 298.00,
        'https://www.woodbrass.com/images/SQUARE400/woodbrass/MARCUSMILLER+MM086K.JPG',
        ' linea di bassi Marcus Miller con un ottimo suono, un look fantastico ed una qualità mai vista ad un prezzo simile.');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (9, 7, 'Junior Blu', 8, 179.00,
        'https://www.strumentimusicali.net/images/product/600x450/2018/06/21/be/batteria-acusticajunior-2.jpg',
        'Batteria acustica junior completa di hardware e piatti blu.');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (10, 8, 'SamplePad 4', 9, 155.00,
        'https://7132afa424c2f1a2ab6d-54d68a14e2e7c1f76563a2d8c3e9fd82.ssl.cf2.rackcdn.com/1489/images/SamplePad4_Angle-Left_RGB.png',
        'Percussione a 4 pad con ingressi trigger Alesis SamplePad 4.');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (11, 7, 'JDR5 Red', 4, 199.00, 'https://www.erretimusica.it/7743-large_default/darestone-jdr5-red.jpg',
        'Batteria junior 5 pezzi rossa completa di accessori.');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (12, 9, 'Performer Black', 10, 259.00,
        'https://www.strumentimusicali.net/images/product/600x450/2019/03/04/f0/artesia-performerblack-1.jpg',
        'Pianoforte da parco 88 testi pesati nero.');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (13, 10, 'RP25 stage digital piano', 11, 248.00,
        'https://www.strumentimusicali.net/images/product/600x450/2017/11/02/6b/ringway-rp20-5.jpg',
        'Pianoforte digitale 88 tasti pesati nero.');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (14, 11, 'SA46', 12, 44.90,
        'https://www.strumentimusicali.net/images/product/600x450/2017/05/18/ff/casio-sa46-2.jpg',
        'Tastiera portatile 32 tasti mini più che un giocattolo.');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (15, 12, 'DM800', 13, 20.00, 'https://images-na.ssl-images-amazon.com/images/I/41nRRbAIMML._SX425_.jpg',
        'Microfono cardioide per voce.');
INSERT INTO ecommerce.prodotti (id, sottocategoria, nome, produttore, prezzo, immagine, descrizione)
VALUES (16, 12, 'XM8500', 14, 30.00,
        'https://www.bhphotovideo.com/images/images500x500/Behringer_Behringer_XM8500_15_XLR_Cable_Foam_890290.jpg',
        'Microfono cardioide dinamico.');

INSERT INTO ecommerce.utenti (id, email, password, nome, tipo)
VALUES (1, 'umberto.loria@gmail.com', 'pwd', 'umbertolo', 'Normale');
