-- ============================================================
-- V12__link_payment_instruction_to_ingestion.sql
--
-- Purpose:
-- Link each payment instruction back to the ingestion request
-- from which the payment was created.
-- ============================================================


-- 1. Add ingestion_id to payment instruction
ALTER TABLE cf_payment_instruction
ADD ingestion_id RAW(16);


-- 2. Create foreign key relationship
ALTER TABLE cf_payment_instruction
ADD CONSTRAINT fk_cf_pay_ingestion
FOREIGN KEY (ingestion_id)
REFERENCES cf_ingestion_request (ingestion_id);


-- 3. Add index for faster joins/lookups by ingestion_id
CREATE INDEX ix_cf_pay_ingestion
ON cf_payment_instruction (ingestion_id);