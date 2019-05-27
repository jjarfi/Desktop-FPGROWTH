CREATE TABLE t_admin (
id_admin VARCHAR(20) NOT NULL UNIQUE,
fullname VARCHAR (50) NOT NULL UNIQUE,
email VARCHAR (50) NOT NULL UNIQUE,
handphone VARCHAR (12) UNIQUE,
username VARCHAR (50) NOT NULL UNIQUE,
password VARCHAR (50) NOT NULL,
createDate TIMESTAMP,
lastUpdate TIMESTAMP,
PRIMARY KEY (id_admin));

CREATE TABLE t_kontak_alumni(
email VARCHAR (50) NOT NULL UNIQUE,
no_handphone VARCHAR (12)NOT NULL UNIQUE,
facebook VARCHAR (90) UNIQUE,
whatsapp VARCHAR (30) UNIQUE,
alamat VARCHAR (50) NOT NULL,
kota VARCHAR (50) NOT NULL,
kota_kabupaten VARCHAR (30) NOT NULL,
provinsi VARCHAR (60) NOT NULL,
kode_pos BIGINT NOT NULL,
create_date TIMESTAMP,
last_update TIMESTAMP,
npm VARCHAR (8));

CREATE TABLE t_alumni (
npm VARCHAR (8) NOT NULL UNIQUE
REFERENCES t_admin 
ON UPDATE CASCADE
ON DELETE CASCADE,
nama_lengkap  VARCHAR (50) NOT NULL UNIQUE,
jenis_kelamin VARCHAR (12) NOT NULL,
prodi VARCHAR (20) NOT NULL,
bulan_wisuda VARCHAR (14) NOT NULL,
tahun_wisuda VARCHAR (12) NOT NULL,
createDate TIMESTAMP,
lastUpdate TIMESTAMP,
id_admin VARCHAR (20),
PRIMARY KEY  (id_admin, npm));

CREATE TABLE t_berita(
id_berita VARCHAR (40) NOT NULL UNIQUE
REFERENCES t_admin
ON UPDATE CASCADE
ON DELETE CASCADE,
judul VARCHAR (100) NOT NULL,
deskripsi VARCHAR (255),
link_berita VARCHAR (255) UNIQUE,
createDate TIMESTAMP,
lastUpdate TIMESTAMP,
id_admin VARCHAR (20),
PRIMARY KEY (id_admin, id_berita));


CREATE TABLE t_pertanyaan(
id_pertanyaan VARCHAR (60) NOT NULL UNIQUE
REFERENCES t_admin
ON UPDATE CASCADE
ON DELETE CASCADE,
pertanyaan VARCHAR (100) NOT NULL UNIQUE,
jawaban VARCHAR (200),
createDate TIMESTAMP,
lastUpdate TIMESTAMP,
id_admin VARCHAR (20),
PRIMARY KEY (id_admin, id_pertanyaan));