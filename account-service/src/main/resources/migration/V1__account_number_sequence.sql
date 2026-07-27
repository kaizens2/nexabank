CREATE TABLE account_number_sequence
(
    id       BIGINT NOT NULL AUTO_INCREMENT,
    next_val BIGINT NOT NULL DEFAULT 1000000,

    CONSTRAINT pk_account_number_sequence PRIMARY KEY (id)
);

-- seed the single row we'll atomically increment
INSERT INTO account_number_sequence (next_val) VALUES (1000000);