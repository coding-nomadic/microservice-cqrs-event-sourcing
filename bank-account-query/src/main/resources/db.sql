CREATE SCHEMA IF NOT EXISTS bank;

CREATE TABLE IF NOT EXISTS bank.tbl_bank_account (
    id SERIAL PRIMARY KEY,
    account_id VARCHAR(100) NOT NULL,
    account_holder VARCHAR(255) NOT NULL,
    account_type VARCHAR(50) NOT NULL,
    opening_balance NUMERIC(15, 2) NOT NULL
);