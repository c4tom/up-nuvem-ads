CREATE OR REPLACE FUNCTION random_between(low INT ,high INT) 
   RETURNS INT AS
$$
BEGIN
   RETURN floor(random()* (high-low + 1) + low);
END;
$$ language 'plpgsql' STRICT;


-- atualizacao do campo dataregistro
update atendimento set dataregistro = (select horainicioatendimento + (random_between(60,4100) ||' seconds')::interval from atendimento ate where ate.id = atendimento.id)

