-- ojito cambiar el nombre de la bd en MariaDbConig
create database BdPaises 
use BdPaises

create table tpais(
idPais char(36) not null,
Name varchar(70) not null,
createdAt datetime not null,
primary key(idPais)
) engine-innodb;


select *from tpais;