insert into account (username, email,deleted,password_hash,role,lang) values ('USERUNO','user@gmail.com',false,'dsareqw','CANDIDATE','PL');
insert into answer (id,content) values (1001,'Bla');
insert into question (id,content,question_type) values (1002,'Jaka jest wartość x?','OPEN');
insert into answer_to_question (id,answer,is_correct,question_id) values (1001,'BlaBla',TRUE,1002);
insert into position (name) values ('junior dev');
insert into test_template_content (id) values (1032);
insert into test_template (id,en_id,pl_id,position_id) values (1678,1032,1032,'junior dev');
insert into test_result (id,template_id) values (1039, 1678);
