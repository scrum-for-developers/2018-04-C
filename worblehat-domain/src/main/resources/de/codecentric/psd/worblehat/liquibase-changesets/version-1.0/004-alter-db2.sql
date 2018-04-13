-- liquibase formatted sql

-- changeset action:alter_table_books
ALTER TABLE book
  MODIFY COLUMN description TEXT
;
