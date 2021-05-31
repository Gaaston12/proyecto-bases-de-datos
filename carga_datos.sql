use proyecto;

/*Carga de Docentes*/
insert into docentes values (130, 'Juan', 'Diaz', 'Rafael oblig','154127531', 'exclusivo');
insert into docentes values (131, 'Perez', 'Estela', 'Las Heras', '1231', 'exclusivo'); 
insert into docentes values (132, 'Lopez', 'Jose', 'Italia', '1232', 'exclusivo'); 
insert into docentes values (133, 'Garcia', 'Facundo', 'España', '1233', 'simple'); 
insert into docentes values (134, 'Aguero', 'Matias', 'Alberdi', '1234', 'simple'); 
insert into docentes values (135, 'Oga', 'German', 'bs as', '1235', 'simple'); 
insert into docentes values (136, 'Fernandez', 'Valentina', 'Colon', '1232', 'semi-exclusivo'); 

/*Carga de cargos*/
insert into cargo(descripcion) values("Profesor Adjunto");
insert into cargo(descripcion) values("Jefe de Trabajos Prácticos");
insert into cargo(descripcion) values("Ayudante de Primera");

/*Carga de Facultad*/
insert into facultad(descripcion) values("Computacipon");
insert into facultad(descripcion) values("Ingenieria");

/*Se asignan docentes a las facultades*/
insert into pertenece(dni_docente,cod_facultad,cod_cargo) values(130,1,1);
insert into pertenece(dni_docente,cod_facultad,cod_cargo) values(131,1,1);
insert into pertenece(dni_docente,cod_facultad,cod_cargo) values(131,2,1);
insert into pertenece(dni_docente,cod_facultad,cod_cargo) values(132,1,1);
insert into pertenece(dni_docente,cod_facultad,cod_cargo) values(133,1,3);
insert into pertenece(dni_docente,cod_facultad,cod_cargo) values(134,1,3);
insert into pertenece(dni_docente,cod_facultad,cod_cargo) values(135,2,2);
insert into pertenece(dni_docente,cod_facultad,cod_cargo) values(136,2,3);

/*Carga Materias*/
insert into Materias(nombre,dni_responsable) values ('Base de Datos', 130);
insert into Materias(nombre,dni_responsable) values ('Calculo', 131);
insert into Materias(nombre,dni_responsable) values ('Algoritmos', 132);

/*Carga Equipo docentes*/
insert into Equipo values(133, 1);
insert into Equipo values(134, 1);
insert into Equipo values(135, 2);
insert into Equipo values(136, 2);
insert into Equipo values(133, 3);

/*Carga Alumnos*/
insert into Alumnos(dni,apellido,nombre,direccion,telefono) 
	values (123, 'Perez', 'Juan', 'San Martin 111', 1501);
insert into Alumnos(dni,apellido,nombre,direccion,telefono) 
	values (124, 'Fernandez', 'Nicolas', 'Pueyrredon 110', 1502);
insert into Alumnos(dni,apellido,nombre,direccion,telefono) 
	values (125, 'Giordano', 'Marino', 'Las Heras 111', 1503);

/*Asigna los alumnos a las materias*/
insert into cursa(dni_alumno,cod_materia) values (123,1);
insert into cursa(dni_alumno,cod_materia) values (123,2);
insert into cursa(dni_alumno,cod_materia) values (123,3);
insert into cursa(dni_alumno,cod_materia) values (124,1);
insert into cursa(dni_alumno,cod_materia) values (124,3);
insert into cursa(dni_alumno,cod_materia) values (125,2);
insert into cursa(dni_alumno,cod_materia) values (125,3);

/*Carga Actividad*/
insert into Actividad(descripcion,cod_materia) values ('SQL', 1);
insert into Actividad(descripcion,cod_materia) values ('Integrales', 2);
insert into Actividad(descripcion,cod_materia) values ('Trabajo Practico de Grafos', 3);

/*Carga actividad Realizadas*/
insert into Realiza values (123,1);
insert into Realiza values (123,2);
insert into Realiza values (123,3);
insert into Realiza values (124,1);
insert into Realiza values (124,3);
insert into Realiza values (125,2);
insert into Realiza values (125,3);

/*Carga Resolucion*/
insert Resolucion(nota,dni_califica,dni_alumno,cod_actividad) Values(9, 130, 123, 1);
insert Resolucion(nota,dni_califica,dni_alumno,cod_actividad) Values(5, 131, 123, 2);
insert Resolucion(nota,dni_califica,dni_alumno,cod_actividad) Values(5, 134, 123, 3);
insert Resolucion(nota,dni_califica,dni_alumno,cod_actividad) Values(9, 134, 124, 1);
insert Resolucion(nota,dni_califica,dni_alumno,cod_actividad) Values(5, 136, 124, 3);
insert Resolucion(nota,dni_califica,dni_alumno,cod_actividad) Values(5, 133, 125, 3);
insert Resolucion(nota,dni_califica,dni_alumno,cod_actividad) Values(5, 131, 125, 2);

/* se modifica la resolucion y se agregan a auditoria*/

/*El mismo docente modifica la nota del  alumno 123*/
Update Resolucion 
	set nota = 7 where (dni_alumno = 124 and cod_actividad = 2);
Update Resolucion 
	set nota = 5 where (dni_alumno = 123 and cod_actividad = 1);

/*El mismo docente modifica la nota del alumno 124*/
Update Resolucion 
	set nota = 7 where (dni_alumno = 123 and cod_actividad = 1);    

/*Otro docente modifica la nota del alumno 124*/
Update Resolucion 
	set nota = 6, dni_califica = 136 where (dni_alumno = 124 and cod_actividad = 1);

/*Muesto aditoria*/
/*select * from Auditoria;*/
