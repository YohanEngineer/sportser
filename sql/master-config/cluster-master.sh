mysql -u root
CREATE USER 'toto'@'192.168.1.21' IDENTIFIED BY 'toto';
GRANT REPLICATION SLAVE ON *.* TO 'toto'@'192.168.1.21';
FLUSH TABLES WITH READ LOCK;
SHOW MASTER STATUS;

mysqldump -uroot -p --all-databases --single-transaction --triggers --routines > dump.sql

mysql -u root
UNLOCK TABLES;

scp dump.sql toto@192.168.1.21:/tmp