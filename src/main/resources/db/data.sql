
INSERT INTO users (id, login_id, login_password, name, nickname, birth, email, phone_number, coin, preferred_language)
VALUES
    (1,'user1', 'password1', 'John Doe', 'johnny', '1990-01-01', 'john@example.com', '010-1234-5678', 100, 'JAVA'),
    (2,'user2', 'password2', 'Jane Smith', 'jane', '1992-02-02', 'jane@example.com', '010-8765-4321', 200, 'JAVASCRIPT'),
    (3,'user3', 'password3', 'Alice Johnson', 'alice', '1985-03-03', 'alice@example.com', '010-1111-2222', 300, 'JAVA'),
    (4,'user4', 'password4', 'Bob Brown', 'bobby', '1988-04-04', 'bob@example.com', '010-3333-4444', 400, 'JAVASCRIPT'),
    (5,'user5', 'password5', 'Charlie Green', 'charlie', '1991-05-05', 'charlie@example.com', '010-5555-6666', 150, 'JAVA'),
    (6,'user6', 'password6', 'Diana White', 'diana', '1993-06-06', 'diana@example.com', '010-7777-8888', 250, 'JAVASCRIPT'),
    (7,'user7', 'password7', 'Ethan Black', 'ethan', '1986-07-07', 'ethan@example.com', '010-9999-0000', 350, 'JAVA'),
    (8,'user8', 'password8', 'Fiona Blue', 'fiona', '1989-08-08', 'fiona@example.com', '010-1234-5679', 450, 'JAVASCRIPT'),
    (9, 'user9', 'password9', 'George Red', 'george', '1994-09-09', 'george@example.com', '010-2345-6789', 500, 'JAVA'),
    (10,'user10', 'password10', 'Hannah Yellow', 'hannah', '1995-10-10', 'hannah@example.com', '010-3456-7890', 550, 'JAVASCRIPT');

INSERT INTO stages (id, level, programming_language, category)
 VALUES
     (1, 'Easy', 'JAVA', 'CONDITION'),
     (2, 'Medium', 'JAVA', 'CONDITION'),
     (3, 'Hard', 'JAVA', 'LOOP'),
     (4, 'Easy', 'JAVASCRIPT', 'CONDITION'),
     (5, 'Medium', 'JAVASCRIPT', 'STRING'),
     (6, 'Hard', 'JAVASCRIPT', 'CONDITION'),
     (7, 'Easy', 'JAVA', 'DEEPEN'),
     (8, 'Medium', 'JAVA', 'FUNCTION'),
     (9, 'Hard', 'JAVA', 'MATH'),
     (10, 'Easy', 'JAVASCRIPT', 'RECURSION');

INSERT INTO problems (id, title, problem_description, input_description, output_description, stage_id)
VALUES
    (1,'Problem 1', 'Description 1', 'Input Desc 1', 'Output Desc 1', 1),
    (2,'Problem 2', 'Description 2', 'Input Desc 2', 'Output Desc 2', 2),
    (3,'Problem 3', 'Description 3', 'Input Desc 3', 'Output Desc 3', 3),
    (4,'Problem 4', 'Description 4', 'Input Desc 4', 'Output Desc 4', 4),
    (5,'Problem 5', 'Description 5', 'Input Desc 5', 'Output Desc 5', 5),
    (6,'Problem 6', 'Description 6', 'Input Desc 6', 'Output Desc 6', 6),
    (7,'Problem 7', 'Description 7', 'Input Desc 7', 'Output Desc 7', 7),
    (8,'Problem 8', 'Description 8', 'Input Desc 8', 'Output Desc 8', 8),
    (9,'Problem 9', 'Description 9', 'Input Desc 9', 'Output Desc 9', 9),
    (10,'Problem 10', 'Description 10', 'Input Desc 10', 'Output Desc 10', 10);


INSERT INTO io_examples (id, input, output, problem_id)
VALUES
    (1,'Input 1-1', 'Output 1-1', 1),
    (2,'Input 1-2', 'Output 1-2', 2),
    (3,'Input 2-1', 'Output 2-1', 3),
    (4,'Input 2-2', 'Output 2-2', 4);

INSERT INTO attempt (id, result, submitted_code, submit_time, remaining_attempt_count, user_id, problem_id)
VALUES
    (1,'SUCCESS', 'code1', 1622520000000, 5, 1, 1),
    (2,'FAIL', 'code2', 1622523600000, 5, 2, 2),
    (3,'SUCCESS', 'code3', 1622527200000, 5, 3, 3),
    (4,'FAIL', 'code4', 1622530800000, 5, 4, 4),
    (5,'SUCCESS', 'code5', 1622534400000, 5, 5, 5),
    (6,'FAIL', 'code6', 1622538000000, 5, 6, 6),
    (7,'SUCCESS', 'code7', 1622541600000, 5, 7, 7),
    (8,'FAIL', 'code8', 1622545200000, 5, 8, 8),
    (9,'SUCCESS', 'code9', 1622548800000, 5, 9, 9),
    (10,'FAIL', 'code10', 1622552400000, 5, 10, 10);

INSERT INTO progresses (id, user_id, stage_id, attempt_result)
VALUES
    (1, 1, 1, 'SUCCESS'),
    (2, 2, 2, 'FAIL'),
    (3, 3, 3, 'NOT_ATTEMPTED'),
    (4, 4, 4, 'FAIL'),
    (5, 5, 5, 'SUCCESS'),
    (6, 6, 6, 'NOT_ATTEMPTED'),
    (7, 7, 7, 'NOT_ATTEMPTED'),
    (8, 8, 8, 'FAIL'),
    (9, 9, 9, 'SUCCESS'),
    (10, 10, 10, 'FAIL');




