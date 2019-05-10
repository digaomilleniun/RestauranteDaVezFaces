
SELECT *
from (
	SELECT count(1), id_restaurante
	FROM public."Voto"
	group by id_restaurante
	) c
	order by c.count desc

	SELECT DATE_TRUNC('day', TIMESTAMP '2017-03-17 02:09:30');
	
	SELECT count(1), id_restaurante, DATE_TRUNC('day', data)
	FROM public."Voto"
	where data between DATE_TRUNC('day', now()) and DATE_TRUNC('day', now())
	group by id_restaurante,data
	order by count desc