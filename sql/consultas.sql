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
