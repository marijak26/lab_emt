create materialized view accommodations_by_host as
select h.host_id       as host_id,
       count(a.id) as accommodations_count
from hosts h
         join
     accommodations a on a.host_host_id = h.host_id
group by h.host_id
order by h.host_id;


create materialized view hosts_by_country as
select c.country_id        as country_id,
       count(h.host_id) as hosts_count
from countries c
         join
     hosts h on h.country_country_id = c.country_id
group by c.country_id
order by c.country_id;