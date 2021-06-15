create database if not exists Proyecto;
use proyecto;

create table if not exists Alumnos (
	dni int not null,
	nro int not null auto_increment unique,
	apellido varchar(30) not null,
	nombre varchar(30) not null,
	direccion varchar(30),
	telefono varchar(15),
	primary key (dni)
);
 
create table if not exists Docentes (
	dni int not null ,
	apellido varchar(30) not null,
	nombre varchar(30) not null,
	direccion varchar(30),
	telefono varchar(15),
	dedicacion enum ('exclusivo', 'semi-exclusivo', 'simple'),
	primary key (dni)
);

create table if not exists Materias (
	cod int not null auto_increment,
	nombre varchar(30),
	dni_responsable int,
	primary key (cod),
	foreign key (dni_responsable) references Docentes (dni) on update cascade on delete cascade
);

create table if not exists Actividad (
	codigo int not null auto_increment,
	descripcion varchar(50),
	cod_materia int not null,
	primary key (codigo),
	foreign key (cod_materia) references Materias (cod) on update cascade on delete cascade
);

create table if not exists Realiza (
	dni int not null,
	cod_actividad int not null,
	constraint pkrealiza primary key (dni, cod_actividad),
	foreign key (dni) references Alumnos(dni) on update cascade on delete cascade, 
	foreign key (cod_actividad) references Actividad(codigo) on update cascade on delete cascade
);

create table if not exists Resolucion(
	codigo int not null auto_increment,
	nota float,
	fecha datetime DEFAULT now(),
	dni_califica int not null,
	dni_alumno int not null,
	cod_actividad int not null,
	primary key (codigo),
	foreign key (dni_califica) references Docentes(dni) on update cascade on delete cascade,
	constraint fkresolucion foreign key (dni_alumno, cod_actividad) references Realiza (dni, cod_actividad) on update cascade on delete cascade
);

create table if not exists Facultad (
  codigo int not null auto_increment,
  descripcion varchar(45) not null,
  primary key (codigo)
);

create table if not exists Cargo (
  codigo int not null auto_increment,
  descripcion varchar(45) not null,
  primary key (codigo)
);

create table if not exists Cursa (
	dni_alumno int not null,
	cod_materia int not null,
	constraint pkrealiza primary key (dni_alumno, cod_materia),
	foreign key (dni_alumno) references Alumnos(dni) on update cascade on delete cascade, 
	foreign key (cod_materia) references Materias(cod) on update cascade on delete cascade
);

create table if not exists Pertenece (
	dni_docente int not null,
	cod_facultad int not null,
	cod_cargo int not null,
	constraint pkpertenece primary key (dni_docente, cod_facultad),
	foreign key (dni_docente) references Docentes (dni) on update cascade on delete cascade,
	foreign key (cod_facultad) references Facultad (codigo) on update cascade on delete cascade,
	foreign key (cod_cargo) references Cargo (codigo) on update cascade on delete cascade  
);

create table if not exists Equipo (
	dni_docente int not null,
	cod_materia int not null,
	constraint pkequipo primary key (dni_docente, cod_materia),
	foreign key (dni_docente) references Docentes (dni) on update cascade on delete cascade,
	foreign key (cod_materia) references Materias (cod) on update cascade on delete cascade
);

create table if not exists Auditoria(
	id int not null auto_increment, 
    codigo_resolucion int not null,
    fecha datetime DEFAULT now(),
    calificacion_anterior float,
    calificacion_nueva float,
    dni_docente int not null,
    constraint pkAuditoria primary key (id),
    foreign key (codigo_resolucion) references Resolucion (codigo) on update cascade on delete cascade,
    foreign key (dni_docente) references Docentes (dni) on update cascade on delete cascade
);

delimiter //
create trigger alta_docente before insert on Equipo
	for each row
	begin
		if exists 
		(select * from materias 
		 where (new.cod_materia = Materias.cod) and (new.dni_docente = Materias.dni_responsable)) 
		then
			signal sqlstate '45000'
			set message_text = 'Error: El Docente ya es responsable de la materia';
		end if;
	end; 
//
delimiter ;

delimiter //
create trigger modificar_responsable before update on Materias
	for each row
	begin
		if exists 
		(select * from Equipo 
		 where (Equipo.cod_materia = new.cod) and (Equipo.dni_docente = new.dni_responsable)) 
		then
			signal sqlstate '45000'
			set message_text = 'Error: El Docente ya esta en el quipo';
		end if;
	end; 
//
delimiter ;

delimiter //
create trigger ingreso_auditoria
    after update on Resolucion 
    for each row 
    begin 
        insert into Auditoria(codigo_resolucion,calificacion_anterior,calificacion_nueva,dni_docente)
        values(old.codigo,old.nota,new.nota,new.dni_califica);
    end;  
//    
delimiter ;
