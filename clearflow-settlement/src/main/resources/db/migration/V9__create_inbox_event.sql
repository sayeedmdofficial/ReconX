create table cf_inbox_event (
   inbox_id              raw(16) not null,
   event_id              varchar2(128 char) not null,
   event_type            varchar2(100 char) not null,
   event_version         number(5,0) default 1 not null,
   consumer_group        varchar2(100 char) not null,
   topic_name            varchar2(200 char) not null,
   partition_no          number(10,0) not null,
   offset_no             number(19,0) not null,
   message_key           varchar2(200 char),
   payload_hash          varchar2(128 char),
   correlation_id        varchar2(100 char),
   trace_id              varchar2(64 char),
   ingestion_id          raw(16),
   status                varchar2(30 char) default 'RECEIVED' not null,
   received_at           timestamp(6) with time zone default systimestamp not null,
   processing_started_at timestamp(6) with time zone,
   processed_at          timestamp(6) with time zone,
   retry_count           number(5,0) default 0 not null,
   last_error_code       varchar2(100 char),
   last_error_message    varchar2(2000 char),
   updated_at            timestamp(6) with time zone default systimestamp not null,
   constraint pk_cf_inbox_event primary key ( inbox_id ),
   constraint fk_cf_inbox_ingestion foreign key ( ingestion_id )
      references cf_ingestion_request ( ingestion_id ),
   constraint uk_cf_inbox_event unique ( consumer_group,
                                         event_id ),
   constraint uk_cf_inbox_offset unique ( consumer_group,
                                          topic_name,
                                          partition_no,
                                          offset_no ),
   constraint ck_cf_inbox_status
      check ( status in ( 'RECEIVED',
                          'PROCESSING',
                          'PROCESSED',
                          'FAILED',
                          'IGNORED' ) ),
   constraint ck_cf_inbox_version check ( event_version > 0 ),
   constraint ck_cf_inbox_partition check ( partition_no >= 0 ),
   constraint ck_cf_inbox_offset check ( offset_no >= 0 ),
   constraint ck_cf_inbox_retry check ( retry_count >= 0 ),
   constraint ck_cf_inbox_start_time
      check ( processing_started_at is null
          or processing_started_at >= received_at ),
   constraint ck_cf_inbox_processed_time
      check ( processed_at is null
          or ( processing_started_at is not null
         and processed_at >= processing_started_at ) )
);


create index ix_cf_inbox_status_received on
   cf_inbox_event (
      status,
      received_at
   );


create index ix_cf_inbox_ingestion on
   cf_inbox_event (
      ingestion_id
   );


create index ix_cf_inbox_correlation on
   cf_inbox_event (
      correlation_id
   );


create index ix_cf_inbox_event_type on
   cf_inbox_event (
      event_type,
      received_at
   );