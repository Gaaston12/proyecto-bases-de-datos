create database Proyecto;
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

create table Materias (
cod int not null auto_increment,
nombre varchar(30),
dni_responsable int,
primary key (cod),
foreign key (dni_responsable) references Docentes (dni)
);

create table Actividad (
codigo int not null auto_increment,
descripcion varchar(50),
cod_materia int not null,
primary key (codigo),
foreign key (cod_materia) references Materias (cod)
);

create table Realiza (
dni int not null,
cod_actividad int not null,
constraint pkrealiza primary key (dni, cod_actividad),
foreign key (dni) references Alumnos(dni), 
foreign key (cod_actividad) references Actividad(codigo)
);

Create table Resolucion(
codigo int not null auto_increment,
nota float,
fecha date,
dni_califica int,
dni_alumno int not null,
cod_actividad int not null,
primary key (codigo),
foreign key (dni_califica) references Docentes(dni),
constraint fkresolucion foreign key (dni_alumno, cod_actividad) references Realiza (dni, cod_actividad)
);

CREATE TABLE Facultad (
  codigo int NOT NULL auto_increment,
  descripcion varchar(45) NOT NULL,
  PRIMARY KEY (codigo)
);

CREATE TABLE Cargo (
  codigo int NOT NULL auto_increment,
  descripcion varchar(45) NOT NULL,
  PRIMARY KEY (codigo)
);

create table Cursa (
dni int not null,
cod_materia int not null,
constraint pkrealiza primary key (dni, cod_materia),
foreign key (dni) references Alumnos(dni), 
foreign key (cod_materia) references Materias(cod));

create table Pertenece (
dni_docente int not null,
cod_facultad int not null,
cod_cargo int,
constraint pkpertenece primary key (dni_docente, cod_facultad),
foreign key (dni_docente) references Docentes (dni),
foreign key (cod_facultad) references Facultad (codigo),
foreign key (cod_cargo) references Cargo (codigo)  
);

create table Equipo (
dni_docente int not null,
cod_materia int not null,
constraint pkequipo primary key (dni_docente, cod_materia),
foreign key (dni_docente) references Docentes (dni),
foreign key (cod_materia) references Materias (cod)
);

delimiter //
create trigger alta_docente before insert on Equipo
FOR EACH ROW
BEGIN
	if exists 
    (Select * from materias 
	 where (new.cod_materia = Materias.cod) and (new.dni_docente = Materias.dni_responsable)) 
	then
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Error: El Docente ya es responsable de la materia';
	END IF;
END; //
delimiter ;

Drop  trigger alta_docente;

delimiter //
create trigger modificar_responsable before update on Materias
FOR EACH ROW
BEGIN
	if exists 
    (Select * from Equipo 
	 where (Equipo.cod_materia = new.cod) and (Equipo.dni_docente = new.dni_responsable)) 
	then
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Error: El Docente ya esta en el quipo';
	END IF;
END; //
delimiter ;

delimiter //
create trigger alta_resolucion before insert on Resolucion
FOR EACH ROW
BEGIN
	if exists 
    (Select * from materias, equipo 
	 where (new.codigo = Materias.cod) and (new.dni_docente = Materias.dni_responsable)) 
	then
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Error: El Docente ya es responsable de la materia';
	END IF;
END; //
delimiter ;

