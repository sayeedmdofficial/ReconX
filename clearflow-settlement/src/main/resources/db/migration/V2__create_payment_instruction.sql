create table cf_payment_instruction (
   payment_id                raw(16) not null,
   payment_reference         varchar2(64 char) not null,
   source_system             varchar2(50 char) not null,
   external_reference        varchar2(100 char) not null,
   payment_type              varchar2(30 char) not null,
   debtor_participant_id     varchar2(35 char) not null,
   creditor_participant_id   varchar2(35 char) not null,
   debtor_account_ref        varchar2(64 char) not null,
   creditor_account_ref      varchar2(64 char) not null,
   amount                    number(19,4) not null,
   currency                  varchar2(3 char) not null,
   requested_settlement_date date not null,
   status                    varchar2(30 char) default 'RECEIVED' not null,
   version_no                number(10,0) default 0 not null,
   created_at                timestamp(6) with time zone default systimestamp not null,
   updated_at                timestamp(6) with time zone default systimestamp not null,
   constraint pk_cf_payment_instruction primary key ( payment_id ),
   constraint uk_cf_pay_reference unique ( payment_reference ),
   constraint uk_cf_pay_source_extref unique ( source_system,
                                               external_reference ),
   constraint ck_cf_pay_amount_pos check ( amount > 0 ),
   constraint ck_cf_pay_currency_fmt
      check ( length(currency) = 3
         and currency = upper(currency) ),
   constraint ck_cf_pay_settle_date check ( requested_settlement_date = trunc(requested_settlement_date) ),
   constraint ck_cf_pay_version_nonneg check ( version_no >= 0 ),
   constraint ck_cf_pay_status
      check ( status in ( 'RECEIVED',
                          'VALIDATING',
                          'VALIDATED',
                          'ACCEPTED',
                          'REJECTED' ) )
);

create index ix_cf_pay_status_settle on
   cf_payment_instruction (
      status,
      requested_settlement_date
   );

create index ix_cf_pay_created_id on
   cf_payment_instruction (
      created_at,
      payment_id
   );