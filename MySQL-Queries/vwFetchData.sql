CREATE VIEW vwFetchData AS
    SELECT account_no ,u_name , userdata.userId  , balance
    FROM userdata INNER JOIN useracdetails
    ON (userdata.userId = useracdetails.userId)