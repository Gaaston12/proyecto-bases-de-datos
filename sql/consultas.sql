/*Lista de alumnos que no entregaron alguna resolucion de una materia, en este caso Base de Datos*/
select distinct a.dni_alumno from
(select dni_alumno, codigo 
from
(cursa join actividad on cursa.cod_materia = actividad.cod_materia)
where actividad.cod_materia = 1) as a /*Obtenemos el dni y  los codigos de actividad de los alumnos que cursan bd*/
where not exists 
(select * from resolucion
where
resolucion.cod_actividad = a.codigo and resolucion.dni_alumno = a.dni_alumno);/*Conjunto de actividades con resoluciones entregadas*/

/*Listar las materias que solo tienen responsable y sin ningún docente en
el equipo docente.*/
select cod, nombre
from materias
where cod not in(
select cod from
materias join equipo on materias.cod = equipo.cod_materia)
;

/*Listar alumnos que también son docentes con todos sus datos
persoanles.*/
select alumnos.* 
from alumnos join docentes on alumnos.dni = docentes.dni
;

/*+++++++Consultas Propias++++++++*/

/*Listar el promedio de los alumnos de una materia dada*/
select dni_alumno, avg(nota) as promedio
from
(select distinct dni_alumno, cod_actividad, nota, cod_materia /*alumnos */
from
(resolucion join actividad on resolucion.cod_actividad = actividad.codigo)
where actividad.cod_materia = 1) as a
group by a.dni_alumno;

/*Listado de Materias con los datos de sus responsables*/
select materias.*, apellido, docentes.nombre 
from materias join docentes on materias.dni_responsable = docentes.dni;

/*Listado de alumnos que cursan Base de Datos*/
Select alumnos.* from
cursa join alumnos on cursa.dni_alumno = alumnos.dni
where cod_materia = 1;
 
/*Listar los docentes que no pertenecen a ningun equipo*/
select * 
from docentes
where dni not in
(select dni_docente from equipo)


