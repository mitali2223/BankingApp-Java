DELIMITER //
CREATE PROCEDURE spFetchUserData(user_name varchar(50))
begin
 select account_no as "account number" , u_name as "name" , balance from vWfetchData where userId = user_name;
end;
//