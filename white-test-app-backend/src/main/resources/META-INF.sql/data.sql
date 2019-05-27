insert into account (username, email,password_hash,role,lang) values ('useruno','user@gmail.com','$2a$10$ndyS.7lNeC17DjzsRv4ImuuIxBOHLGvP6iIoICxQZasHNMGL0duT.','CANDIDATE','PL'); --pass: testpass
insert into account (username, email,password_hash,role,lang) values ('red1','user1@gmail.com','$2a$10$ndyS.7lNeC17DjzsRv4ImuuIxBOHLGvP6iIoICxQZasHNMGL0duT.','REDACTOR','PL');
insert into account (username, email,password_hash,role,lang) values ('red2','user2@gmail.com','$2a$10$ndyS.7lNeC17DjzsRv4ImuuIxBOHLGvP6iIoICxQZasHNMGL0duT.','REDACTOR','PL');
insert into account (username, email,password_hash,role,lang) values ('cand1','user3@gmail.com','$2a$10$ndyS.7lNeC17DjzsRv4ImuuIxBOHLGvP6iIoICxQZasHNMGL0duT.','CANDIDATE','PL');
insert into account (username, email,password_hash,role,lang) values ('mod1','user4@gmail.com','$2a$10$ndyS.7lNeC17DjzsRv4ImuuIxBOHLGvP6iIoICxQZasHNMGL0duT.','MODERATOR','PL');
insert into answer (id,content) values (1001,'nieznana');
insert into answer (id,content) values (1002,'znana');
insert into answer (id,content) values (1003,'1');
insert into answer (id,content) values (1004,'2');
insert into question (id,content,question_type) values (1002,'Jaka jest wartość x - otwarte?','OPEN');
insert into question (id,content,question_type) values (1003,'Jaka jest wartość x? - number','NUMBER');
insert into question (id,content,question_type) values (1004,'Jaka jest wartość x? - wyboru ','CHOICE');
insert into question (id,content,question_type) values (1005,'Jaka jest wartość x? - skali','SCALE');
insert into answer_to_question (id,answer,is_correct,question_id) values (1001,'BlaBla',TRUE,1002);
insert into answer_to_question (id,answer,is_correct,question_id) values (1002,'1',TRUE,1003);
insert into answer_to_question (id,answer,is_correct,question_id) values (1003,'znana',TRUE,1004);
insert into answer_to_question (id,answer,is_correct,question_id) values (1004,'2',TRUE,1005);
insert into position (name,activated) values ('junior dev', false );
insert into position (name,activated) values ('architect', true);
insert into position (name,activated) values ('dev',false );
insert into position (name,activated) values ('senior', true);
insert into test_template_content (id, is_deleted) values (1032, false);
insert into test_template (id,name,en_id,pl_id,position_id,author_id, is_deleted) values (1678,'test',null,1032,'junior dev','red1',false);
update test_template_content set test_template_id = 1678 where id = 1032;
insert into test_result (id,participant_username, template_id) values (1039, 'useruno', 1032);
insert into test_result_answers (test_result_id, answers_id) values (1039, 1001);
insert into test_result_answers (test_result_id, answers_id) values (1039, 1002);
insert into test_result_answers (test_result_id, answers_id) values (1039, 1003);
insert into test_result_answers (test_result_id, answers_id) values (1039, 1004);
insert into test_template_content_questions(test_template_content_id, questions_id) VALUES (1032, 1002);
insert into test_template_content_questions(test_template_content_id, questions_id) VALUES (1032, 1003);
insert into test_template_content_questions(test_template_content_id, questions_id) VALUES (1032, 1004);
insert into test_template_content_questions(test_template_content_id, questions_id) VALUES (1032, 1005);
insert into question_answers (question_id, answers_id) values (1004, 1001);
insert into question_answers (question_id, answers_id) values (1004, 1002);
insert into question_answers (question_id, answers_id) values (1005, 1003);
insert into question_answers (question_id, answers_id) values (1005, 1004);
