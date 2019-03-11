create table categorie (
  id   int auto_increment
    primary key,
  nome varchar(20) not null
);


create table prodotti (
  id         int auto_increment
    primary key,
  nome       varchar(50)   not null,
  prezzo     decimal(5, 2) not null,
  produttore int           not null,
  immagine   varchar(500)  not null,
  constraint prodotti_ibfk_1
    foreign key (produttore) references produttori (id)
      on update cascade
);

create index produttore
  on prodotti (produttore);

INSERT INTO ecommerce.prodotti (id, nome, prezzo, produttore, immagine) VALUES (1, 'Sonor 507', 550.00, 1, 'https://d1aeri3ty3izns.cloudfront.net/media/9/9349/1200/preview.jpg');
INSERT INTO ecommerce.prodotti (id, nome, prezzo, produttore, immagine) VALUES (2, 'Fender Stratocaster', 900.00, 2, 'https://images.reverb.com/image/upload/s--GxJlSHKe--/a_exif,c_limit,e_unsharp_mask:80,f_auto,fl_progressive,g_south,h_620,q_90,w_620/v1485772160/xqvm6hnob1srycd9q5v7.jpg');
create table produttori (
  id   int auto_increment
    primary key,
  nome varchar(20) not null
);

INSERT INTO ecommerce.produttori (id, nome) VALUES (1, 'Sonor');
INSERT INTO ecommerce.produttori (id, nome) VALUES (2, 'Fender');