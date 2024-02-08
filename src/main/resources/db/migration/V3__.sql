ALTER TABLE `role`
    ADD deleted BIT(1) NULL;

ALTER TABLE `role`
    MODIFY deleted BIT (1) NOT NULL;

ALTER TABLE token
    ADD deleted BIT(1) NULL;

ALTER TABLE token
    MODIFY deleted BIT (1) NOT NULL;

ALTER TABLE user
    ADD deleted BIT(1) NULL;

ALTER TABLE user
    MODIFY deleted BIT (1) NOT NULL;