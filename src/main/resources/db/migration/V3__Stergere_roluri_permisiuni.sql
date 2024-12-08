
-- stergere tabele roluri si permisiuni

START TRANSACTION;

ALTER TABLE users DROP FOREIGN KEY fk_users_roluri1;

DROP TABLE IF EXISTS roluri_permisiuni;

DROP TABLE IF EXISTS permisiuni;

DROP TABLE IF EXISTS roluri;

COMMIT;