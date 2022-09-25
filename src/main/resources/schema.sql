drop table if exists Rice_Noodle_Order;
drop table if exists Rice_Noodle;
drop table if exists Ingredient_Ref;
drop table if exists Ingredient;

create table if not exists Rice_Noodle_Order (
  id identity, 
  delivery_name varchar(50) not null, 
  delivery_street varchar(50) not null, 
  delivery_town varchar(50) not null, 
  delivery_district varchar(50) not null, 
  delivery_province varchar(50) not null,
  cc_number varchar(16) not null, 
  cc_expiration varchar(5) not null, 
  cc_cvv varchar(3) not null, 
  placed_at timestamp not null
);

-- rice_noodle_order: order's ID
-- rice_noodle_order_key: the specific of a noodle in the order e.g. 1, 2,...
create table if not exists Rice_Noodle (
  id identity, 
  name varchar(50) not null, 
  rice_noodle_order bigint not null,
  rice_noodle_order_key bigint not null,
  created_at timestamp not null
);

create table if not exists Ingredient_Ref (
  ingredient varchar(4) not null, 
  rice_noodle bigint not null, 
  rice_noodle_key bigint not null
);

create table if not exists Ingredient (
  id varchar(4) not null unique, 
  name varchar(25) not null, 
  type varchar(20) not null,
  primary key (id)
);

alter table Rice_Noodle
  add foreign key (rice_noodle_order) references Rice_Noodle_Order(id);

alter table Ingredient_Ref
  add foreign key (ingredient) references Ingredient(id);
