-- V10__insert_sample_clearflow_seed_data.sql
-- Purpose:
-- Seed realistic ClearFlow settlement data for local development, API testing,
-- pagination testing, retry testing, audit review, and interview explanation.

--------------------------------------------------------------------------------
-- 1. PAYMENT INSTRUCTIONS
--------------------------------------------------------------------------------

insert into cf_payment_instruction (
    payment_id,
    payment_reference,
    source_system,
    external_reference,
    payment_type,
    debtor_participant_id,
    creditor_participant_id,
    debtor_account_ref,
    creditor_account_ref,
    amount,
    currency,
    requested_settlement_date,
    status,
    version_no,
    created_at,
    updated_at
) values (
    hextoraw('11111111111111111111111111111111'),
    'PAY-2026-000001',
    'MOBILE_BANKING',
    'MB-EXT-000001',
    'CUSTOMER_TRANSFER',
    'HDFCINBB',
    'ICICINBB',
    'DEBTOR-ACC-100001',
    'CREDITOR-ACC-200001',
    12500.7500,
    'INR',
    date '2026-09-21',
    'ACCEPTED',
    4,
    to_timestamp_tz('2026-09-20 09:00:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:02:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_payment_instruction (
    payment_id,
    payment_reference,
    source_system,
    external_reference,
    payment_type,
    debtor_participant_id,
    creditor_participant_id,
    debtor_account_ref,
    creditor_account_ref,
    amount,
    currency,
    requested_settlement_date,
    status,
    version_no,
    created_at,
    updated_at
) values (
    hextoraw('22222222222222222222222222222222'),
    'PAY-2026-000002',
    'CORPORATE_PORTAL',
    'CP-EXT-000002',
    'VENDOR_PAYMENT',
    'AXISINBB',
    'SBININBB',
    'DEBTOR-ACC-100002',
    'CREDITOR-ACC-200002',
    985000.0000,
    'INR',
    date '2026-09-21',
    'VALIDATED',
    3,
    to_timestamp_tz('2026-09-20 09:10:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:11:45 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_payment_instruction (
    payment_id,
    payment_reference,
    source_system,
    external_reference,
    payment_type,
    debtor_participant_id,
    creditor_participant_id,
    debtor_account_ref,
    creditor_account_ref,
    amount,
    currency,
    requested_settlement_date,
    status,
    version_no,
    created_at,
    updated_at
) values (
    hextoraw('33333333333333333333333333333333'),
    'PAY-2026-000003',
    'MOBILE_BANKING',
    'MB-EXT-000003',
    'CUSTOMER_TRANSFER',
    'HDFCINBB',
    'UNKNOWNBIC',
    'DEBTOR-ACC-100003',
    'CREDITOR-ACC-200003',
    7500.0000,
    'INR',
    date '2026-09-21',
    'REJECTED',
    3,
    to_timestamp_tz('2026-09-20 09:20:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:21:10 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_payment_instruction (
    payment_id,
    payment_reference,
    source_system,
    external_reference,
    payment_type,
    debtor_participant_id,
    creditor_participant_id,
    debtor_account_ref,
    creditor_account_ref,
    amount,
    currency,
    requested_settlement_date,
    status,
    version_no,
    created_at,
    updated_at
) values (
    hextoraw('44444444444444444444444444444444'),
    'PAY-2026-000004',
    'BATCH_HOST',
    'BH-EXT-000004',
    'SALARY_CREDIT',
    'YESBINBB',
    'KKBKINBB',
    'DEBTOR-ACC-100004',
    'CREDITOR-ACC-200004',
    45000.0000,
    'INR',
    date '2026-09-22',
    'ACCEPTED',
    4,
    to_timestamp_tz('2026-09-20 09:30:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:32:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

--------------------------------------------------------------------------------
-- 2. INGESTION REQUESTS
--------------------------------------------------------------------------------

insert into cf_ingestion_request (
    ingestion_id,
    payment_id,
    request_id,
    source_system,
    source_channel,
    correlation_id,
    trace_id,
    status,
    received_at,
    processing_started_at,
    processing_completed_at,
    error_code,
    error_message,
    updated_at
) values (
    hextoraw('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'),
    hextoraw('11111111111111111111111111111111'),
    'REQ-MB-000001',
    'MOBILE_BANKING',
    'REST_API',
    'CORR-20260920-000001',
    'TRACE-000001',
    'COMPLETED',
    to_timestamp_tz('2026-09-20 09:00:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:00:02 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:02:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    null,
    null,
    to_timestamp_tz('2026-09-20 09:02:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_ingestion_request (
    ingestion_id,
    payment_id,
    request_id,
    source_system,
    source_channel,
    correlation_id,
    trace_id,
    status,
    received_at,
    processing_started_at,
    processing_completed_at,
    error_code,
    error_message,
    updated_at
) values (
    hextoraw('bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb'),
    hextoraw('22222222222222222222222222222222'),
    'REQ-CP-000002',
    'CORPORATE_PORTAL',
    'REST_API',
    'CORR-20260920-000002',
    'TRACE-000002',
    'COMPLETED',
    to_timestamp_tz('2026-09-20 09:10:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:10:01 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:11:45 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    null,
    null,
    to_timestamp_tz('2026-09-20 09:11:45 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_ingestion_request (
    ingestion_id,
    payment_id,
    request_id,
    source_system,
    source_channel,
    correlation_id,
    trace_id,
    status,
    received_at,
    processing_started_at,
    processing_completed_at,
    error_code,
    error_message,
    updated_at
) values (
    hextoraw('cccccccccccccccccccccccccccccccc'),
    hextoraw('33333333333333333333333333333333'),
    'REQ-MB-000003',
    'MOBILE_BANKING',
    'REST_API',
    'CORR-20260920-000003',
    'TRACE-000003',
    'FAILED',
    to_timestamp_tz('2026-09-20 09:20:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:20:02 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:21:10 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    'INVALID_PARTICIPANT',
    'Creditor participant UNKNOWNBIC is not active in reference data.',
    to_timestamp_tz('2026-09-20 09:21:10 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_ingestion_request (
    ingestion_id,
    payment_id,
    request_id,
    source_system,
    source_channel,
    correlation_id,
    trace_id,
    status,
    received_at,
    processing_started_at,
    processing_completed_at,
    error_code,
    error_message,
    updated_at
) values (
    hextoraw('dddddddddddddddddddddddddddddddd'),
    hextoraw('44444444444444444444444444444444'),
    'REQ-BH-000004',
    'BATCH_HOST',
    'BATCH_FILE',
    'CORR-20260920-000004',
    'TRACE-000004',
    'COMPLETED',
    to_timestamp_tz('2026-09-20 09:30:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:30:10 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:32:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    null,
    null,
    to_timestamp_tz('2026-09-20 09:32:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

--------------------------------------------------------------------------------
-- 3. IDEMPOTENCY RECORDS
--------------------------------------------------------------------------------

insert into cf_idempotency_record (
    idempotency_id,
    idempotency_key,
    source_system,
    request_hash,
    ingestion_id,
    payment_id,
    status,
    response_code,
    response_reference,
    created_at,
    completed_at,
    expires_at,
    updated_at
) values (
    hextoraw('90000000000000000000000000000001'),
    'IDEMP-MB-REQ-000001',
    'MOBILE_BANKING',
    'sha256:mb-req-000001-hash',
    hextoraw('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'),
    hextoraw('11111111111111111111111111111111'),
    'COMPLETED',
    201,
    'PAY-2026-000001',
    to_timestamp_tz('2026-09-20 09:00:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:02:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-21 09:00:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:02:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_idempotency_record (
    idempotency_id,
    idempotency_key,
    source_system,
    request_hash,
    ingestion_id,
    payment_id,
    status,
    response_code,
    response_reference,
    created_at,
    completed_at,
    expires_at,
    updated_at
) values (
    hextoraw('90000000000000000000000000000002'),
    'IDEMP-CP-REQ-000002',
    'CORPORATE_PORTAL',
    'sha256:cp-req-000002-hash',
    hextoraw('bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb'),
    hextoraw('22222222222222222222222222222222'),
    'COMPLETED',
    202,
    'PAY-2026-000002',
    to_timestamp_tz('2026-09-20 09:10:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:11:45 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-21 09:10:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:11:45 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_idempotency_record (
    idempotency_id,
    idempotency_key,
    source_system,
    request_hash,
    ingestion_id,
    payment_id,
    status,
    response_code,
    response_reference,
    created_at,
    completed_at,
    expires_at,
    updated_at
) values (
    hextoraw('90000000000000000000000000000003'),
    'IDEMP-MB-REQ-000003',
    'MOBILE_BANKING',
    'sha256:mb-req-000003-hash',
    hextoraw('cccccccccccccccccccccccccccccccc'),
    hextoraw('33333333333333333333333333333333'),
    'FAILED',
    422,
    'PAY-2026-000003',
    to_timestamp_tz('2026-09-20 09:20:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:21:10 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-21 09:20:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:21:10 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_idempotency_record (
    idempotency_id,
    idempotency_key,
    source_system,
    request_hash,
    ingestion_id,
    payment_id,
    status,
    response_code,
    response_reference,
    created_at,
    completed_at,
    expires_at,
    updated_at
) values (
    hextoraw('90000000000000000000000000000004'),
    'IDEMP-BH-REQ-000004',
    'BATCH_HOST',
    'sha256:bh-req-000004-hash',
    hextoraw('dddddddddddddddddddddddddddddddd'),
    hextoraw('44444444444444444444444444444444'),
    'COMPLETED',
    201,
    'PAY-2026-000004',
    to_timestamp_tz('2026-09-20 09:30:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:32:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-21 09:30:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:32:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

--------------------------------------------------------------------------------
-- 4. VALIDATION ERRORS
--------------------------------------------------------------------------------

insert into cf_validation_error (
    validation_error_id,
    ingestion_id,
    payment_id,
    validation_stage,
    field_name,
    error_code,
    error_message,
    severity,
    rule_id,
    created_at
) values (
    hextoraw('91000000000000000000000000000001'),
    hextoraw('cccccccccccccccccccccccccccccccc'),
    hextoraw('33333333333333333333333333333333'),
    'REFERENCE_DATA',
    'creditor_participant_id',
    'INVALID_PARTICIPANT',
    'Creditor participant UNKNOWNBIC is not present or not active in participant reference data.',
    'ERROR',
    'RULE-PARTICIPANT-001',
    to_timestamp_tz('2026-09-20 09:21:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_validation_error (
    validation_error_id,
    ingestion_id,
    payment_id,
    validation_stage,
    field_name,
    error_code,
    error_message,
    severity,
    rule_id,
    created_at
) values (
    hextoraw('91000000000000000000000000000002'),
    hextoraw('bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb'),
    hextoraw('22222222222222222222222222222222'),
    'BUSINESS',
    'amount',
    'HIGH_VALUE_PAYMENT_REVIEW',
    'Payment amount is above configured operational review threshold.',
    'WARNING',
    'RULE-LIMIT-REVIEW-001',
    to_timestamp_tz('2026-09-20 09:11:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

--------------------------------------------------------------------------------
-- 5. PAYMENT STATUS HISTORY
--------------------------------------------------------------------------------

-- Payment 1: full successful path
insert into cf_payment_status_history values (
    hextoraw('a1000000000000000000000000000001'),
    hextoraw('11111111111111111111111111111111'),
    null,
    'RECEIVED',
    'REQUEST_ACCEPTED',
    'Payment instruction received from source channel.',
    'clearflow-api',
    'API',
    'CORR-20260920-000001',
    'TRACE-000001',
    to_timestamp_tz('2026-09-20 09:00:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_payment_status_history values (
    hextoraw('a1000000000000000000000000000002'),
    hextoraw('11111111111111111111111111111111'),
    'RECEIVED',
    'VALIDATING',
    'VALIDATION_STARTED',
    'Validation engine started business and reference data checks.',
    'validation-engine',
    'VALIDATION_ENGINE',
    'CORR-20260920-000001',
    'TRACE-000001',
    to_timestamp_tz('2026-09-20 09:00:05 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_payment_status_history values (
    hextoraw('a1000000000000000000000000000003'),
    hextoraw('11111111111111111111111111111111'),
    'VALIDATING',
    'VALIDATED',
    'VALIDATION_PASSED',
    'Payment instruction passed all configured validation rules.',
    'validation-engine',
    'VALIDATION_ENGINE',
    'CORR-20260920-000001',
    'TRACE-000001',
    to_timestamp_tz('2026-09-20 09:01:10 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_payment_status_history values (
    hextoraw('a1000000000000000000000000000004'),
    hextoraw('11111111111111111111111111111111'),
    'VALIDATED',
    'ACCEPTED',
    'READY_FOR_CLEARING',
    'Payment accepted and ready for clearing publication.',
    'settlement-orchestrator',
    'SYSTEM',
    'CORR-20260920-000001',
    'TRACE-000001',
    to_timestamp_tz('2026-09-20 09:02:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

-- Payment 2: validated but not yet accepted
insert into cf_payment_status_history values (
    hextoraw('a2000000000000000000000000000001'),
    hextoraw('22222222222222222222222222222222'),
    null,
    'RECEIVED',
    'REQUEST_ACCEPTED',
    'Payment instruction received from corporate portal.',
    'clearflow-api',
    'API',
    'CORR-20260920-000002',
    'TRACE-000002',
    to_timestamp_tz('2026-09-20 09:10:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_payment_status_history values (
    hextoraw('a2000000000000000000000000000002'),
    hextoraw('22222222222222222222222222222222'),
    'RECEIVED',
    'VALIDATING',
    'VALIDATION_STARTED',
    'Validation started for corporate high-value payment.',
    'validation-engine',
    'VALIDATION_ENGINE',
    'CORR-20260920-000002',
    'TRACE-000002',
    to_timestamp_tz('2026-09-20 09:10:10 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_payment_status_history values (
    hextoraw('a2000000000000000000000000000003'),
    hextoraw('22222222222222222222222222222222'),
    'VALIDATING',
    'VALIDATED',
    'VALIDATION_PASSED_WITH_WARNING',
    'Payment passed validation with high-value review warning.',
    'validation-engine',
    'VALIDATION_ENGINE',
    'CORR-20260920-000002',
    'TRACE-000002',
    to_timestamp_tz('2026-09-20 09:11:45 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

-- Payment 3: rejected path
insert into cf_payment_status_history values (
    hextoraw('a3000000000000000000000000000001'),
    hextoraw('33333333333333333333333333333333'),
    null,
    'RECEIVED',
    'REQUEST_ACCEPTED',
    'Payment instruction received from mobile banking.',
    'clearflow-api',
    'API',
    'CORR-20260920-000003',
    'TRACE-000003',
    to_timestamp_tz('2026-09-20 09:20:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_payment_status_history values (
    hextoraw('a3000000000000000000000000000002'),
    hextoraw('33333333333333333333333333333333'),
    'RECEIVED',
    'VALIDATING',
    'VALIDATION_STARTED',
    'Reference data validation started.',
    'validation-engine',
    'VALIDATION_ENGINE',
    'CORR-20260920-000003',
    'TRACE-000003',
    to_timestamp_tz('2026-09-20 09:20:05 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_payment_status_history values (
    hextoraw('a3000000000000000000000000000003'),
    hextoraw('33333333333333333333333333333333'),
    'VALIDATING',
    'REJECTED',
    'INVALID_PARTICIPANT',
    'Payment rejected because creditor participant is invalid.',
    'validation-engine',
    'VALIDATION_ENGINE',
    'CORR-20260920-000003',
    'TRACE-000003',
    to_timestamp_tz('2026-09-20 09:21:10 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

-- Payment 4: accepted, but outbox publish has failed
insert into cf_payment_status_history values (
    hextoraw('a4000000000000000000000000000001'),
    hextoraw('44444444444444444444444444444444'),
    null,
    'RECEIVED',
    'REQUEST_ACCEPTED',
    'Batch payment instruction received from host file.',
    'batch-ingestion-worker',
    'SYSTEM',
    'CORR-20260920-000004',
    'TRACE-000004',
    to_timestamp_tz('2026-09-20 09:30:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_payment_status_history values (
    hextoraw('a4000000000000000000000000000002'),
    hextoraw('44444444444444444444444444444444'),
    'RECEIVED',
    'VALIDATING',
    'VALIDATION_STARTED',
    'Batch payment validation started.',
    'validation-engine',
    'VALIDATION_ENGINE',
    'CORR-20260920-000004',
    'TRACE-000004',
    to_timestamp_tz('2026-09-20 09:30:20 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_payment_status_history values (
    hextoraw('a4000000000000000000000000000003'),
    hextoraw('44444444444444444444444444444444'),
    'VALIDATING',
    'VALIDATED',
    'VALIDATION_PASSED',
    'Batch payment passed validation.',
    'validation-engine',
    'VALIDATION_ENGINE',
    'CORR-20260920-000004',
    'TRACE-000004',
    to_timestamp_tz('2026-09-20 09:31:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_payment_status_history values (
    hextoraw('a4000000000000000000000000000004'),
    hextoraw('44444444444444444444444444444444'),
    'VALIDATED',
    'ACCEPTED',
    'READY_FOR_CLEARING',
    'Payment accepted but event publishing is currently failing.',
    'settlement-orchestrator',
    'SYSTEM',
    'CORR-20260920-000004',
    'TRACE-000004',
    to_timestamp_tz('2026-09-20 09:32:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

--------------------------------------------------------------------------------
-- 6. OUTBOX EVENTS
--------------------------------------------------------------------------------

insert into cf_outbox_event (
    event_id,
    aggregate_type,
    aggregate_id,
    event_type,
    event_version,
    payload,
    correlation_id,
    trace_id,
    status,
    created_at,
    available_at,
    published_at,
    retry_count,
    last_attempt_at,
    last_error_code,
    last_error_message
) values (
    hextoraw('b1000000000000000000000000000001'),
    'PAYMENT',
    hextoraw('11111111111111111111111111111111'),
    'PAYMENT_ACCEPTED',
    1,
    q'~{"paymentReference":"PAY-2026-000001","amount":12500.75,"currency":"INR","status":"ACCEPTED"}~',
    'CORR-20260920-000001',
    'TRACE-000001',
    'PUBLISHED',
    to_timestamp_tz('2026-09-20 09:02:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:02:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:03:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    0,
    to_timestamp_tz('2026-09-20 09:03:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    null,
    null
);

insert into cf_outbox_event (
    event_id,
    aggregate_type,
    aggregate_id,
    event_type,
    event_version,
    payload,
    correlation_id,
    trace_id,
    status,
    created_at,
    available_at,
    published_at,
    retry_count,
    last_attempt_at,
    last_error_code,
    last_error_message
) values (
    hextoraw('b2000000000000000000000000000001'),
    'PAYMENT',
    hextoraw('22222222222222222222222222222222'),
    'PAYMENT_VALIDATED',
    1,
    q'~{"paymentReference":"PAY-2026-000002","amount":985000.00,"currency":"INR","status":"VALIDATED","warning":"HIGH_VALUE_PAYMENT_REVIEW"}~',
    'CORR-20260920-000002',
    'TRACE-000002',
    'PENDING',
    to_timestamp_tz('2026-09-20 09:11:45 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:11:45 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    null,
    0,
    null,
    null,
    null
);

insert into cf_outbox_event (
    event_id,
    aggregate_type,
    aggregate_id,
    event_type,
    event_version,
    payload,
    correlation_id,
    trace_id,
    status,
    created_at,
    available_at,
    published_at,
    retry_count,
    last_attempt_at,
    last_error_code,
    last_error_message
) values (
    hextoraw('b3000000000000000000000000000001'),
    'PAYMENT',
    hextoraw('33333333333333333333333333333333'),
    'PAYMENT_REJECTED',
    1,
    q'~{"paymentReference":"PAY-2026-000003","amount":7500.00,"currency":"INR","status":"REJECTED","reasonCode":"INVALID_PARTICIPANT"}~',
    'CORR-20260920-000003',
    'TRACE-000003',
    'PUBLISHED',
    to_timestamp_tz('2026-09-20 09:21:10 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:21:10 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:21:20 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    0,
    to_timestamp_tz('2026-09-20 09:21:20 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    null,
    null
);

insert into cf_outbox_event (
    event_id,
    aggregate_type,
    aggregate_id,
    event_type,
    event_version,
    payload,
    correlation_id,
    trace_id,
    status,
    created_at,
    available_at,
    published_at,
    retry_count,
    last_attempt_at,
    last_error_code,
    last_error_message
) values (
    hextoraw('b4000000000000000000000000000001'),
    'PAYMENT',
    hextoraw('44444444444444444444444444444444'),
    'PAYMENT_ACCEPTED',
    1,
    q'~{"paymentReference":"PAY-2026-000004","amount":45000.00,"currency":"INR","status":"ACCEPTED"}~',
    'CORR-20260920-000004',
    'TRACE-000004',
    'FAILED',
    to_timestamp_tz('2026-09-20 09:32:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:32:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    null,
    3,
    to_timestamp_tz('2026-09-20 09:40:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    'KAFKA_BROKER_UNAVAILABLE',
    'Failed to publish event after 3 retry attempts because Kafka broker was unavailable.'
);

--------------------------------------------------------------------------------
-- 7. AUDIT EVENTS
--------------------------------------------------------------------------------

insert into cf_audit_event values (
    hextoraw('c1000000000000000000000000000001'),
    'PAYMENT',
    hextoraw('11111111111111111111111111111111'),
    'PAYMENT_CREATED',
    'SERVICE',
    'clearflow-api',
    'MOBILE_BANKING',
    'CORR-20260920-000001',
    'TRACE-000001',
    q'~{"paymentReference":"PAY-2026-000001","sourceChannel":"REST_API"}~',
    to_timestamp_tz('2026-09-20 09:00:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_audit_event values (
    hextoraw('c1000000000000000000000000000002'),
    'PAYMENT',
    hextoraw('11111111111111111111111111111111'),
    'PAYMENT_ACCEPTED',
    'SYSTEM',
    'settlement-orchestrator',
    'CLEARFLOW',
    'CORR-20260920-000001',
    'TRACE-000001',
    q'~{"fromStatus":"VALIDATED","toStatus":"ACCEPTED"}~',
    to_timestamp_tz('2026-09-20 09:02:30 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_audit_event values (
    hextoraw('c3000000000000000000000000000001'),
    'PAYMENT',
    hextoraw('33333333333333333333333333333333'),
    'PAYMENT_REJECTED',
    'SYSTEM',
    'validation-engine',
    'CLEARFLOW',
    'CORR-20260920-000003',
    'TRACE-000003',
    q'~{"reasonCode":"INVALID_PARTICIPANT","field":"creditor_participant_id"}~',
    to_timestamp_tz('2026-09-20 09:21:10 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_audit_event values (
    hextoraw('c4000000000000000000000000000001'),
    'OUTBOX_EVENT',
    hextoraw('b4000000000000000000000000000001'),
    'OUTBOX_PUBLISH_FAILED',
    'SYSTEM',
    'outbox-publisher',
    'CLEARFLOW',
    'CORR-20260920-000004',
    'TRACE-000004',
    q'~{"retryCount":3,"lastErrorCode":"KAFKA_BROKER_UNAVAILABLE"}~',
    to_timestamp_tz('2026-09-20 09:40:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

--------------------------------------------------------------------------------
-- 8. INBOX EVENTS
--------------------------------------------------------------------------------

insert into cf_inbox_event (
    inbox_id,
    event_id,
    event_type,
    event_version,
    consumer_group,
    topic_name,
    partition_no,
    offset_no,
    message_key,
    payload_hash,
    correlation_id,
    trace_id,
    ingestion_id,
    status,
    received_at,
    processing_started_at,
    processed_at,
    retry_count,
    last_error_code,
    last_error_message,
    updated_at
) values (
    hextoraw('d1000000000000000000000000000001'),
    'EXT-CLEARING-ACK-000001',
    'CLEARING_ACK_RECEIVED',
    1,
    'clearflow-settlement-consumer',
    'clearing.ack.v1',
    0,
    10001,
    'PAY-2026-000001',
    'sha256:clearing-ack-000001',
    'CORR-20260920-000001',
    'TRACE-000001',
    hextoraw('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'),
    'PROCESSED',
    to_timestamp_tz('2026-09-20 09:05:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:05:02 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:05:08 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    0,
    null,
    null,
    to_timestamp_tz('2026-09-20 09:05:08 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

insert into cf_inbox_event (
    inbox_id,
    event_id,
    event_type,
    event_version,
    consumer_group,
    topic_name,
    partition_no,
    offset_no,
    message_key,
    payload_hash,
    correlation_id,
    trace_id,
    ingestion_id,
    status,
    received_at,
    processing_started_at,
    processed_at,
    retry_count,
    last_error_code,
    last_error_message,
    updated_at
) values (
    hextoraw('d4000000000000000000000000000001'),
    'EXT-CLEARING-ACK-000004',
    'CLEARING_ACK_RECEIVED',
    1,
    'clearflow-settlement-consumer',
    'clearing.ack.v1',
    0,
    10002,
    'PAY-2026-000004',
    'sha256:clearing-ack-000004',
    'CORR-20260920-000004',
    'TRACE-000004',
    hextoraw('dddddddddddddddddddddddddddddddd'),
    'FAILED',
    to_timestamp_tz('2026-09-20 09:41:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    to_timestamp_tz('2026-09-20 09:41:02 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM'),
    null,
    2,
    'PAYMENT_STATE_CONFLICT',
    'Received clearing acknowledgement while outbox event is still in FAILED state.',
    to_timestamp_tz('2026-09-20 09:45:00 +05:30', 'YYYY-MM-DD HH24:MI:SS TZH:TZM')
);

commit;