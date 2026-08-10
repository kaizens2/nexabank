CREATE TABLE IF NOT EXISTS transactions
(
    id              BINARY(16)                                  NOT NULL,
    account_number  VARCHAR(20)                                  NOT NULL,
    customer_id     BINARY(16)                                  NOT NULL,
    direction       ENUM ('CREDIT','DEBIT')                      NOT NULL,
    amount          DECIMAL(19,4)                                NOT NULL,
    currency        VARCHAR(3)                                   NOT NULL,
    reference       VARCHAR(255)                                 NULL,
    status          ENUM ('PENDING','COMPLETED','FAILED')        NOT NULL DEFAULT 'PENDING',
    metadata        JSON                                         NULL,
    balance_after   DECIMAL(19,4)                                NOT NULL,
    created_at      DATETIME                                     NOT NULL,
    created_by      VARCHAR(100)                                 NULL,

    CONSTRAINT pk_transactions PRIMARY KEY (id)
);

-- Indexes created separately with existence checks —
-- MySQL doesn't support "CREATE INDEX IF NOT EXISTS" directly,
-- so we guard using information_schema instead
SET @index_exists = (
    SELECT COUNT(1) FROM information_schema.statistics
    WHERE table_schema = DATABASE()
      AND table_name = 'transactions'
      AND index_name = 'idx_transactions_account_number'
);
SET @sql = IF(@index_exists = 0,
    'CREATE INDEX idx_transactions_account_number ON transactions (account_number)',
    'SELECT 1'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @index_exists = (
    SELECT COUNT(1) FROM information_schema.statistics
    WHERE table_schema = DATABASE()
      AND table_name = 'transactions'
      AND index_name = 'idx_transactions_customer_id'
);
SET @sql = IF(@index_exists = 0,
    'CREATE INDEX idx_transactions_customer_id ON transactions (customer_id)',
    'SELECT 1'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @index_exists = (
    SELECT COUNT(1) FROM information_schema.statistics
    WHERE table_schema = DATABASE()
      AND table_name = 'transactions'
      AND index_name = 'idx_transactions_created_at'
);
SET @sql = IF(@index_exists = 0,
    'CREATE INDEX idx_transactions_created_at ON transactions (created_at)',
    'SELECT 1'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;