use proyecto;

/*Carga Docentes*/
insert into docentes values (30709655, 'Juan', 'Diaz', 'Rafael oblig','154127531', 'simple');
insert into docentes values (134, 'Perez', 'Jose', 'San Martin', '1231', 'simple'); 
insert into docentes values (135, 'Lopez', 'Jose', 'San Martin', '1232', 'simple'); 

/*Carga Materias*/
insert into Materias values (null, 'Base de Datos', 30709655);

/*Carga Equipo docentes*/
insert into Equipo values(30709655, 1);
insert into Equipo values(134, 1);

/*Carga Alumnos*/
insert into Alumnos values (123, null, 'Perez', 'Juan', 'San Martin 111', 1501);
insert into Alumnos values (124, null, 'Fernandez', 'Nicolas', 'San Martin 111', 1505);

/*Carga Actividad*/
insert into Actividad values (null, 'SQL', 1);

/*Carga Alumnos que realizan actividad*/
insert into Realiza values (123,1);
insert into Realiza values (124,1);

/*Carga Resolucion*/
insert Resolucion Values(null, 9, current_date(), 134, 123, 1);
insert Resolucion Values(null, 5, current_date(), 134, 124, 1);

/*El mismo docente modifica la nota del  alumno 123*/
Update Resolucion 
	set nota = 7 where (dni_alumno = 123 and cod_actividad = 1);
Update Resolucion 
	set nota = 5 where (dni_alumno = 123 and cod_actividad = 1);

/*El mismo docente modifica la nota del alumno 124*/
Update Resolucion 
	set nota = 7 where (dni_alumno = 124 and cod_actividad = 1);    

/*Otro docente modifica la nota del alumno 124*/
Update Resolucion 
	set nota = 4, dni_califica = 135 where (dni_alumno = 123 and cod_actividad = 1);

/*Muesto aditoria*/
select * from Auditoria;
