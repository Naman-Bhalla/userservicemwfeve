ALTER TABLE user
    ADD hashed_password VARCHAR(255) NULL;

ALTER TABLE user
DROP
COLUMN password;