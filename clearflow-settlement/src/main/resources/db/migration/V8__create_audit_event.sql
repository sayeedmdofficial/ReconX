create table cf_audit_event (
   audit_id        raw(16) not null,
   entity_type     varchar2(50 char) not null,
   entity_id       raw(16) not null,
   action          varchar2(100 char) not null,
   actor_type      varchar2(30 char) not null,
   actor_id        varchar2(100 char) not null,
   source_system   varchar2(50 char),
   correlation_id  varchar2(100 char),
   trace_id        varchar2(64 char),
   details         clob,
   event_timestamp timestamp(6) with time zone default systimestamp not null,
   constraint pk_cf_audit_event primary key ( audit_id ),
   constraint ck_cf_audit_entity_type
      check ( entity_type in ( 'PAYMENT',
                               'INGESTION_REQUEST',
                               'IDEMPOTENCY_RECORD',
                               'OUTBOX_EVENT' ) ),
   constraint ck_cf_audit_actor_type
      check ( actor_type in ( 'SYSTEM',
                              'SERVICE',
                              'USER',
                              'OPERATOR' ) ),
   constraint ck_cf_audit_details_json check ( details is null
       or details is json )
);


create index ix_cf_audit_entity on
   cf_audit_event (
      entity_type,
      entity_id,
      event_timestamp
   );


create index ix_cf_audit_action on
   cf_audit_event (
      action,
      event_timestamp
   );


create index ix_cf_audit_actor on
   cf_audit_event (
      actor_type,
      actor_id,
      event_timestamp
   );


create index ix_cf_audit_correlation on
   cf_audit_event (
      correlation_id
   );