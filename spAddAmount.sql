
DELIMITER //
CREATE  PROCEDURE spAddAmount(username varchar(50), amount int)
begin
 set @amt =  (select balance from useracdetails where userId = username);
   update useracdetails set balance = @amt +  amount  where userId = username;
if @@error_count != 0 then
rollback;
    SELECT 'Error occurred during the transaction';
else 
commit;
    SELECT ' transaction completed successfully';
end if;
  commit;
end;
//