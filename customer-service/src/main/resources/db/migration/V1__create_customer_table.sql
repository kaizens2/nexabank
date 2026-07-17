CREATE TABLE customers
(
    customer_id          BINARY(16)                         NOT NULL,
    first_name  VARCHAR(100)                       NOT NULL,
    last_name   VARCHAR(100)                       NOT NULL,
    email       VARCHAR(255)                       NOT NULL,
    phone       VARCHAR(20)                        NOT NULL,
    kyc_status  ENUM ('PENDING','VERIFIED','REJECTED') NOT NULL DEFAULT 'PENDING',
    active      BOOLEAN                            NOT NULL DEFAULT TRUE,
    created_at  DATETIME                           NOT NULL,
    updated_at  DATETIME                           NOT NULL,
    created_by  VARCHAR(100)                       NULL,
    updated_by  VARCHAR(100)                       NULL,

    CONSTRAINT pk_customers PRIMARY KEY (customer_id),
    CONSTRAINT uq_customers_email UNIQUE (email)
);