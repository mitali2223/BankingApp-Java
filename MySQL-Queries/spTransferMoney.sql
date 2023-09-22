DELIMITER //
create procedure spTransferMoney( accountNo int , amount int , username varchar(50), user_password varchar(50))
begin
set @user1_password = (select u_password from userdata where userId = username);
update useracdetails set balance = balance - amount where userId = username and  @user1_password = user_password;
update useracdetails set balance = balance +  amount where userId = (select userId from userdata where account_no = accountNo);
if @@error_count != 0 then
	rollback;
    SELECT 'Error occurred during the transaction';
else 
	commit ;
end if;
end;//