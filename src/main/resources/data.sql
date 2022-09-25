delete from Ingredient_Ref; 
delete from Rice_Noodle; 
delete from Rice_Noodle_Order; 

delete from Ingredient; 
insert into Ingredient (id, name, type) 
	values ('FULL', 'Day du', 'VEGGIES'); 
insert into Ingredient (id, name, type) 
	values ('SPOL', 'Chi gia', 'VEGGIES');
insert into Ingredient (id, name, type)
	values ('VEOL', 'Chi rau song', 'VEGGIES');
insert into Ingredient (id, name, type)
	values ('BEEF', 'Thit bo', 'MEAT');
insert into Ingredient (id, name, type)
	values ('PORK', 'Thit heo', 'MEAT');
insert into Ingredient (id, name, type)
	values ('PSFH', 'Cha ca', 'PASTE');
insert into Ingredient (id, name, type)
	values ('PSBF', 'Cha bo', 'PASTE');
insert into Ingredient (id, name, type)
	values ('PSPK', 'Cha heo', 'PASTE');
insert into Ingredient (id, name, type)
	values ('PSCR', 'Cha cua', 'PASTE');
insert into Ingredient (id, name, type)
	values ('SLNO', 'Khong cay', 'SPICY_LEVEL');
insert into Ingredient (id, name, type)
	values ('SLMD', 'Cay vua', 'SPICY_LEVEL');
insert into Ingredient (id, name, type)
	values ('SLEX', 'Cay manh', 'SPICY_LEVEL');
insert into Ingredient (id, name, type)
	values ('NTBU', 'Soi bun', 'NOODLE_TYPE');
insert into Ingredient (id, name, type)
	values ('NTPH', 'Soi pho', 'NOODLE_TYPE');
