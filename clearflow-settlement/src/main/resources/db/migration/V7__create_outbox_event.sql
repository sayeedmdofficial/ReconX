create table cf_outbox_event (
   event_id           raw(16) not null,
   aggregate_type     varchar2(50 char) not null,
   aggregate_id       raw(16) not null,
   event_type         varchar2(100 char) not null,
   event_version      number(5,0) default 1 not null,
   payload            clob not null,
   correlation_id     varchar2(100 char),
   trace_id           varchar2(64 char),
   status             varchar2(30 char) default 'PENDING' not null,
   created_at         timestamp(6) with time zone default systimestamp not null,
   available_at       timestamp(6) with time zone default systimestamp not null,
   published_at       timestamp(6) with time zone,
   retry_count        number(5,0) default 0 not null,
   last_attempt_at    timestamp(6) with time zone,
   last_error_code    varchar2(100 char),
   last_error_message varchar2(2000 char),
   constraint pk_cf_outbox_event primary key ( event_id ),
   constraint ck_cf_outbox_aggregate_type check ( aggregate_type in ( 'PAYMENT' ) ),
   constraint ck_cf_outbox_status
      check ( status in ( 'PENDING',
                          'PROCESSING',
                          'PUBLISHED',
                          'FAILED' ) ),
   constraint ck_cf_outbox_version check ( event_version > 0 ),
   constraint ck_cf_outbox_retry check ( retry_count >= 0 ),
   constraint ck_cf_outbox_available_time check ( available_at >= created_at ),
   constraint ck_cf_outbox_published_time
      check ( published_at is null
          or published_at >= created_at ),
   constraint ck_cf_outbox_json check ( payload is json )
);


create index ix_cf_outbox_pending on
   cf_outbox_event (
      status,
      available_at,
      created_at
   );


create index ix_cf_outbox_aggregate on
   cf_outbox_event (
      aggregate_type,
      aggregate_id,
      created_at
   );


create index ix_cf_outbox_correlation on
   cf_outbox_event (
      correlation_id
   );