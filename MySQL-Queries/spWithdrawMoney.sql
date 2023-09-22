DELIMITER //
create procedure spWithdrawMoney( amount int , username varchar(50), user_password varchar(50))
begin 
set @amt = (select balance from userdata inner join useracdetails on userdata.userId = useracdetails.userId where userdata.userId = username and userdata.u_password = user_password);
update useracdetails set balance = @amt - amount where useracdetails.userId = username;
if @@error_count != 0 then
rollback;
    SELECT 'Error occurred during the transaction';
else 
commit;
    SELECT ' transaction completed successfully';
end if;
end;//







