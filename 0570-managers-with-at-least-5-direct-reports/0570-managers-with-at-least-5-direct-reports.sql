# Write your MySQL query statement below
select e1.name
from Employee e1
Inner Join Employee e2
on e1.id = e2.managerId
Group by e1.id,e1.name
Having COUNT(*) >= 5  ;

