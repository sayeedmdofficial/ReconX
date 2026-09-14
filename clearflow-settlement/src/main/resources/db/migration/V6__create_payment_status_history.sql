create table cf_payment_status_history (
   status_history_id  raw(16) not null,
   payment_id         raw(16) not null,
   from_status        varchar2(30 char),
   to_status          varchar2(30 char) not null,
   reason_code        varchar2(100 char),
   reason_description varchar2(1000 char),
   changed_by         varchar2(100 char) not null,
   change_source      varchar2(30 char) not null,
   correlation_id     varchar2(100 char),
   trace_id           varchar2(64 char),
   changed_at         timestamp(6) with time zone default systimestamp not null,
   constraint pk_cf_payment_status_history primary key ( status_history_id ),
   constraint fk_cf_pay_status_payment foreign key ( payment_id )
      references cf_payment_instruction ( payment_id ),
   constraint ck_cf_pay_status_from
      check ( from_status is null
          or from_status in ( 'RECEIVED',
                              'VALIDATING',
                              'VALIDATED',
                              'ACCEPTED',
                              'REJECTED' ) ),
   constraint ck_cf_pay_status_to
      check ( to_status in ( 'RECEIVED',
                             'VALIDATING',
                             'VALIDATED',
                             'ACCEPTED',
                             'REJECTED' ) ),
   constraint ck_cf_pay_status_change_source
      check ( change_source in ( 'API',
                                 'VALIDATION_ENGINE',
                                 'SYSTEM',
                                 'OPERATOR' ) ),
   constraint ck_cf_pay_status_different
      check ( from_status is null
          or from_status <> to_status )
);


create index ix_cf_pay_status_hist_payment on
   cf_payment_status_history (
      payment_id,
      changed_at
   );


create index ix_cf_pay_status_hist_to_status on
   cf_payment_status_history (
      to_status,
      changed_at
   );


create index ix_cf_pay_status_hist_correlation on
   cf_payment_status_history (
      correlation_id
   );