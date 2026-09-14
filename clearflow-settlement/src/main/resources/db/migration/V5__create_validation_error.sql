create table cf_validation_error (
   validation_error_id raw(16) not null,
   ingestion_id        raw(16) not null,
   payment_id          raw(16),
   validation_stage    varchar2(30 char) not null,
   field_name          varchar2(100 char),
   error_code          varchar2(100 char) not null,
   error_message       varchar2(1000 char) not null,
   severity            varchar2(20 char) default 'ERROR' not null,
   rule_id             varchar2(100 char),
   created_at          timestamp(6) with time zone default systimestamp not null,
   constraint pk_cf_validation_error primary key ( validation_error_id ),
   constraint fk_cf_valerr_ingestion foreign key ( ingestion_id )
      references cf_ingestion_request ( ingestion_id ),
   constraint fk_cf_valerr_payment foreign key ( payment_id )
      references cf_payment_instruction ( payment_id ),
   constraint ck_cf_valerr_stage
      check ( validation_stage in ( 'REQUEST',
                                    'SCHEMA',
                                    'BUSINESS',
                                    'REFERENCE_DATA' ) ),
   constraint ck_cf_valerr_severity check ( severity in ( 'WARNING',
                                                          'ERROR' ) )
);


create index ix_cf_valerr_ingestion on
   cf_validation_error (
      ingestion_id
   );


create index ix_cf_valerr_payment on
   cf_validation_error (
      payment_id
   );


create index ix_cf_valerr_code_created on
   cf_validation_error (
      error_code,
      created_at
   );