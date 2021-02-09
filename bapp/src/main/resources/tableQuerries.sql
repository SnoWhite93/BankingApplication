drop table if EXISTS bank_users

create table bank_users
(
    user_id       serial primary key,
    username      varchar(50) not null,
    pass          varchar(50) not null,
    first_name    varchar(50) not null,
    last_name     varchar(50) not null,
    phone_number  varchar(20) not null,
    email         varchar(50) not null,
    date_of_birth date        not null,
    is_employee   bool        not null
);

create table accounts
(
    account_id   serial primary key,
    user_id      int8 references bank_users (user_id),
    balance      numeric(10, 2) not null,
    date_created date           not null
);

create table transactions
(
    transaction_id serial primary key,
    from_account   int8 references accounts (account_id),
    to_account     int8 references accounts (account_id),
    amount         numeric(10, 2) not null,
    status         varchar(50)    not null,
    transfer_date  date           not null
);

create table account_requests
(
    request_id       serial primary key,
    user_id          int8 references bank_users (user_id),
    request_status   varchar(50)    not null,
    starting_balance numeric(10, 2) not null,
    date_requested   date           not null,
    rejection_reason varchar(50)    not null
);




