CREATE TABLE accounts
(
    id              BINARY(16)                                        NOT NULL,
    account_number  VARCHAR(20)                                        NOT NULL,
    customer_id     BINARY(16)                                        NOT NULL,
    account_type    ENUM ('SAVING','CURRENT','FIXED_DEPOSIT')          NOT NULL DEFAULT 'SAVING',
    balance         DECIMAL(19,4)                                      NOT NULL DEFAULT 0.0000,
    currency        VARCHAR(3)                                         NOT NULL,
    status          ENUM ('ACTIVE','FROZEN','INACTIVE','CLOSED')       NOT NULL DEFAULT 'ACTIVE',
    version         BIGINT                                             NOT NULL DEFAULT 0,
    created_at      DATETIME                                           NOT NULL,
    updated_at      DATETIME                                           NOT NULL,
    created_by      VARCHAR(100)                                       NULL,
    updated_by      VARCHAR(100)                                       NULL,

    CONSTRAINT pk_accounts PRIMARY KEY (id),
    CONSTRAINT uq_accounts_account_number UNIQUE (account_number),
    INDEX idx_accounts_customer_id (customer_id)
);