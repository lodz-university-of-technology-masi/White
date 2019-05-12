insert into account (username, email,deleted,password_hash,role,lang) values ('USERUNO','user@gmail.com',false,'dsareqw','CANDIDATE','PL');
insert into answer (id,content) values (1001,'nieznana');
insert into answer (id,content) values (1002,'znana');
insert into answer (id,content) values (1003,'1');
insert into answer (id,content) values (1004,'2');
insert into question (id,content,question_type) values (1002,'Jaka jest wartość x - otwarte?','OPEN');
insert into question (id,content,question_type) values (1003,'Jaka jest wartość x? - number','NUMBER');
insert into question (id,content,question_type) values (1004,'Jaka jest wartość x? - wyboru ','CHOICE');
insert into question (id,content,question_type) values (1005,'Jaka jest wartość x? - skali','SCALE');
insert into answer_to_question (id,answer,is_correct,question_id) values (1001,'BlaBla',TRUE,1002);
insert into position (name) values ('junior dev');
insert into test_template_content (id, is_deleted) values (1032, false);
insert into test_template (id,name,en_id,pl_id,position_id,author_id, is_deleted) values (1678,'test',null,1032,'junior dev','USERUNO',false);
update test_template_content set test_template_id = 1678 where id = 1032;
insert into test_result (id,participant_username, template_id) values (1039, 'USERUNO', 1032);
insert into test_template_content_questions(test_template_content_id, questions_id) VALUES (1032, 1002);
insert into test_template_content_questions(test_template_content_id, questions_id) VALUES (1032, 1003);
insert into test_template_content_questions(test_template_content_id, questions_id) VALUES (1032, 1004);
insert into test_template_content_questions(test_template_content_id, questions_id) VALUES (1032, 1005);
insert into question_answers (question_id, answers_id) values (1004, 1001);
insert into question_answers (question_id, answers_id) values (1004, 1002);
insert into question_answers (question_id, answers_id) values (1005, 1003);
insert into question_answers (question_id, answers_id) values (1005, 1004);


