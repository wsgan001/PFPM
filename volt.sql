create table transaction(iid int not null, tid int not null);
partition table transaction on column tid;
