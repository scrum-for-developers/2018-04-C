-- liquibase formatted sql

-- changeset action:alter_table_books
ALTER TABLE book
  ADD COLUMN description VARCHAR(255) AFTER year_of_publication
;
