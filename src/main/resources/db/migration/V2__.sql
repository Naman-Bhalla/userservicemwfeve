ALTER TABLE `role`
    ADD is_deleted BIT(1) NULL DEFAULT false;

ALTER TABLE `role`
    MODIFY is_deleted BIT (1) NOT NULL DEFAULT false;

ALTER TABLE token
    ADD is_deleted BIT(1) NULL DEFAULT false;

ALTER TABLE token
    MODIFY is_deleted BIT (1) NOT NULL DEFAULT false;

ALTER TABLE user
    ADD is_deleted BIT(1) NULL DEFAULT false;

ALTER TABLE user
    MODIFY is_deleted BIT (1) NOT NULL DEFAULT false;