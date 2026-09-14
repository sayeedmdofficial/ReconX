create table cf_idempotency_record (
   idempotency_id     raw(16) not null,
   idempotency_key    varchar2(128 char) not null,
   source_system      varchar2(50 char) not null,
   request_hash       varchar2(128 char) not null,
   ingestion_id       raw(16),
   payment_id         raw(16),
   status             varchar2(30 char) default 'PROCESSING' not null,
   response_code      number(3,0),
   response_reference varchar2(100 char),
   created_at         timestamp(6) with time zone default systimestamp not null,
   completed_at       timestamp(6) with time zone,
   expires_at         timestamp(6) with time zone not null,
   updated_at         timestamp(6) with time zone default systimestamp not null,
   constraint pk_cf_idempotency_record primary key ( idempotency_id ),
   constraint uk_cf_idempotency_key unique ( source_system,
                                             idempotency_key ),
   constraint fk_cf_idemp_ingestion foreign key ( ingestion_id )
      references cf_ingestion_request ( ingestion_id ),
   constraint fk_cf_idemp_payment foreign key ( payment_id )
      references cf_payment_instruction ( payment_id ),
   constraint ck_cf_idemp_status
      check ( status in ( 'PROCESSING',
                          'COMPLETED',
                          'FAILED' ) ),
   constraint ck_cf_idemp_response_code
      check ( response_code is null
          or response_code between 100 and 599 ),
   constraint ck_cf_idemp_complete_time
      check ( completed_at is null
          or completed_at >= created_at ),
   constraint ck_cf_idemp_expiry check ( expires_at > created_at )
);

create index ix_cf_idemp_payment on
   cf_idempotency_record (
      payment_id
   );

create index ix_cf_idemp_ingestion on
   cf_idempotency_record (
      ingestion_id
   );

create index ix_cf_idemp_status_expiry on
   cf_idempotency_record (
      status,
      expires_at
   );