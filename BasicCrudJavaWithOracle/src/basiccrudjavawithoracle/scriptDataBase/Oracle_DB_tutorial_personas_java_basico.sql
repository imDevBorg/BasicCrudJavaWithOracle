--DROP TABLE tb_persona_tutorial;
--Se crea la tabla

--Sigueme en YouTube para el tutorial completo
CREATE TABLE tb_persona_tutorial(
    idPersona NUMBER(10),
    nombre VARCHAR2(100),
    apellido VARCHAR2(100),
    email VARCHAR2(30),
    CONSTRAINT tb_persona_pk PRIMARY KEY (idPersona)
);/
--Sigueme en YouTube para el tutorial completo
-- validar correo con expresion regular en Oracle
ALTER TABLE tb_persona_tutorial
ADD CONSTRAINT chk_tb_persona_tutorial_email
CHECK(
REGEXP_LIKE(
email,'^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$'
)
);
 --vamos a insertar 
--insert into tb_persona_tutorial (nombre, apellido, email) 
--values ('Carlos','Xing','a@a.com');
--commit;
--select *from tb_persona_tutorial;

-- Se crea la secuencia
CREATE SEQUENCE tb_persona_tuto_trigger_seq START WITH 1;/

-- Se crea trigger para auto incrementar el id (PK), de la tabla 
CREATE OR REPLACE TRIGGER tb_persona_Tutorial_trigger_id
BEFORE INSERT ON tb_persona_tutorial
FOR EACH ROW
BEGIN
    SELECT tb_persona_tuto_trigger_seq.NEXTVAL
    INTO :new.idPersona
    from dual;
END;

-- Se crea un cursor
CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/

--------Procedimentos y funciones tb_persona_tutorial

--
-- Procedimientos Almacenados (Store procedure)
--

--create
create or replace procedure insertPersona
    (nombrePersona in tb_persona_tutorial.nombre%type,
    apellidoPersona in tb_persona_tutorial.apellido%type,
    emailPersona in  tb_persona_tutorial.email%type)
as
begin
    insert into tb_persona_tutorial(nombre, apellido, email)
    values (nombrePersona,apellidoPersona,emailPersona);
    commit;
end;
/
--delete
create or replace procedure deletePersona(idPersonaTb in tb_persona_tutorial.idPersona%type)
as
begin
    delete from tb_persona_tutorial where idPersona = idPersonaTb;
    commit;
end;
/
--update
create or replace procedure updatePersona
    (
    idPersonaTb in tb_persona_tutorial.idPersona%type,
    nombrePersona in tb_persona_tutorial.nombre%type,
    apellidoPersona in tb_persona_tutorial.apellido%type,
    emailPersona in  tb_persona_tutorial.email%type
    )
as
begin
    update tb_persona_tutorial set nombre =nombrePersona , apellido = apellidoPersona, email =emailPersona
    where idPersona =idPersonaTb;
    commit;
end;
/

--
--Funciones
--
-- findAll
create or replace function findAllPersona
return types.ref_cursor
as
    persona_tb_cursor types.ref_cursor;
begin
    open persona_tb_cursor for
    select idPersona, nombre, apellido, email from tb_persona_tutorial;
return persona_tb_cursor;
end; 
/
--findOne
create or replace function findOnePersona(idPersonaTb in tb_persona_tutorial.idPersona%type)
return types.ref_cursor
as
    persona_tb_cursor types.ref_cursor;
begin
    open persona_tb_cursor for
    select idPersona, nombre, apellido, email from tb_persona_tutorial where idPersona =idPersonaTb;
    return persona_tb_cursor;
end;
/

insert into tb_persona_tutorial (nombre, apellido, email) values ('Alejandro','Perez','cperez@tutorial.com');
insert into tb_persona_tutorial (nombre, apellido, email) values ('Bern','Castellanos','bcastellanos@tutorial.com');
insert into tb_persona_tutorial (nombre, apellido, email) values ('Carlos','Xing','cxing@tutorial.com');
commit;

Select*from tb_persona_tutorial order by idpersona ASC;

