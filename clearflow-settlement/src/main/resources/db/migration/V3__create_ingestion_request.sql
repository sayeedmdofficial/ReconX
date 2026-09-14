create table cf_ingestion_request (
   ingestion_id            raw(16) not null,
   payment_id              raw(16),
   request_id              varchar2(100 char) not null,
   source_system           varchar2(50 char) not null,
   source_channel          varchar2(30 char) not null,
   correlation_id          varchar2(100 char) not null,
   trace_id                varchar2(64 char),
   status                  varchar2(30 char) default 'RECEIVED' not null,
   received_at             timestamp(6) with time zone default systimestamp not null,
   processing_started_at   timestamp(6) with time zone,
   processing_completed_at timestamp(6) with time zone,
   error_code              varchar2(100 char),
   error_message           varchar2(1000 char),
   updated_at              timestamp(6) with time zone default systimestamp not null,
   constraint pk_cf_ingestion_request primary key ( ingestion_id ),
   constraint fk_cf_ingest_payment foreign key ( payment_id )
      references cf_payment_instruction ( payment_id ),
   constraint uk_cf_ingest_source_request unique ( source_system,
                                                   request_id ),
   constraint ck_cf_ingest_channel
      check ( source_channel in ( 'REST_API',
                                  'KAFKA',
                                  'BATCH_FILE' ) ),
   constraint ck_cf_ingest_status
      check ( status in ( 'RECEIVED',
                          'PROCESSING',
                          'COMPLETED',
                          'FAILED' ) ),
   constraint ck_cf_ingest_start_time
      check ( processing_started_at is null
          or processing_started_at >= received_at ),
   constraint ck_cf_ingest_complete_time
      check ( processing_completed_at is null
          or processing_started_at is not null ),
   constraint ck_cf_ingest_time_order
      check ( processing_completed_at is null
          or processing_completed_at >= processing_started_at )
);

create index ix_cf_ingest_payment on
   cf_ingestion_request (
      payment_id
   );

create index ix_cf_ingest_status_received on
   cf_ingestion_request (
      status,
      received_at
   );

create index ix_cf_ingest_correlation on
   cf_ingestion_request (
      correlation_id
   );