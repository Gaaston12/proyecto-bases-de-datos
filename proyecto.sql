/*create database Proyecto;
use proyecto;

create table Alumnos (
dni int not null ,
nro int not null auto_increment unique,
apellido varchar(30) not null,
nombre varchar(30) not null,
direccion varchar(30),
telefono varchar(15),
primary key (dni)
);
 
create table Docentes (
dni int not null ,
apellido varchar(30) not null,
nombre varchar(30) not null,
direccion varchar(30),
telefono varchar(15),
dedicacion enum ('exclusivo', 'semi-exclusivo', 'simple'),
primary key (dni)
);

insert into docentes values (30709655, 'Juan', 'Diaz', 'Rafael oblig','154127531', 'simple');*/
use proyecto;

create table Materias (
cod int not null auto_increment,
nombre varchar(30),
dni_responsable int,
primary key (cod),
foreign key (dni_responsable) references Docentes (dni)
)