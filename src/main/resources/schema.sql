-- create custom sequence
create sequence customer_sequence start with 1000;

-- create sample table
create table customer (
   id bigint default next value for customer_sequence,
   firstName varchar(100) not null,
   lastName varchar(100) not null,
   company varchar(100) not null,
   primary key(id)
);