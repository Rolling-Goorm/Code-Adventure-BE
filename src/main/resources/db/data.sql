
INSERT INTO users (id, login_id, login_password, name, nickname, birth, email, phone_number, coin, preferred_language)
VALUES
    (1, 'admin', 'admin', 'adminName', 'adminNickname', '2000-01-01', 'admin@gmail.com', '010-9999-9999', 1000, 'JAVA'),
    (2, 'admin2', 'admin2', 'adminName2', 'adminNickname2', '2000-01-01', 'admin2@gmail.com', '010-8888-8888', 0, 'JAVASCRIPT'),
    (3,'user3', 'password3', 'John Doe', 'johnny', '1991-01-01', 'john@example.com', '012-1232-5676', 102, 'JAVA'),
    (4,'user4', 'password4', 'Jane Smith', 'jane', '1994-01-01', 'jane@example.com', '012-8763-4319', 202, 'JAVASCRIPT'),
    (5,'user5', 'password5', 'Alice Johnson', 'alice', '1987-01-01', 'alice@example.com', '012-1109-2220', 302, 'JAVA'),
    (6,'user6', 'password6', 'Bob Brown', 'bobby', '1990-02-02', 'bob@example.com', '012-3331-4442', 402, 'JAVASCRIPT'),
    (7,'user7', 'password7', 'Charlie Green', 'charlie', '1993-03-03', 'charlie@example.com', '012-5553-6664', 152, 'JAVA'),
    (8,'user8', 'password8', 'Diana White', 'diana', '1995-04-04', 'diana@example.com', '012-7775-8886', 252, 'JAVASCRIPT'),
    (9,'user9', 'password9', 'Ethan Black', 'ethan', '1988-05-05', 'ethan@example.com', '012-9997-1000', 352, 'JAVA'),
    (10,'user10', 'password10', 'Fiona Blue', 'fiona', '1991-06-06', 'fiona@example.com', '012-1232-5677', 452, 'JAVASCRIPT'),
    (11, 'user11', 'password11', 'George Red', 'george', '1996-07-07', 'george@example.com', '012-2343-6787', 502, 'JAVA'),
    (12, 'user12', 'password12', 'Hannah Yellow', 'hannah', '1997-08-08', 'hannah@example.com', '012-3454-7888', 552, 'JAVASCRIPT');

INSERT INTO stages (id, level, programming_language, category)
 VALUES
     (1, 'Easy', 'JAVA', 'IO'),
     (2, 'Medium', 'JAVA', 'IO'),
     (3, 'Hard', 'JAVA', 'IO'),
     (4, 'Easy', 'JAVA', 'LOOP'),
     (5, 'Medium', 'JAVA', 'LOOP'),
     (6, 'Hard', 'JAVA', 'LOOP'),
     (7, 'Easy', 'JAVA', 'CONDITION'),
     (8, 'Medium', 'JAVA', 'CONDITION'),
     (9, 'Hard', 'JAVA', 'CONDITION'),
     (10, 'Easy', 'JAVA', 'ONE_DIMENSION_ARRAY'),
     (11, 'Medium', 'JAVA', 'ONE_DIMENSION_ARRAY'),
     (12, 'Hard', 'JAVA', 'ONE_DIMENSION_ARRAY'),
     (13, 'Easy', 'JAVA', 'STRING'),
     (14, 'Medium', 'JAVA', 'STRING'),
     (15, 'Hard', 'JAVA', 'STRING'),
     (16, 'Easy', 'JAVA', 'TWO_DIMENSION_ARRAY'),
     (17, 'Medium', 'JAVA', 'TWO_DIMENSION_ARRAY'),
     (18, 'Hard', 'JAVA', 'TWO_DIMENSION_ARRAY'),
     (19, 'Easy', 'JAVA', 'DEEPEN'),
     (20, 'Medium', 'JAVA', 'DEEPEN'),
     (21, 'Hard', 'JAVA', 'DEEPEN'),
     (22, 'Easy', 'JAVA', 'FUNCTION'),
     (23, 'Medium', 'JAVA', 'FUNCTION'),
     (24, 'Hard', 'JAVA', 'FUNCTION'),
     (25, 'Easy', 'JAVA', 'MATH'),
     (26, 'Medium', 'JAVA', 'MATH'),
     (27, 'Hard', 'JAVA', 'MATH'),
     (28, 'Easy', 'JAVA', 'RECURSION'),
     (29, 'Medium', 'JAVA', 'RECURSION'),
     (30, 'Hard', 'JAVA', 'RECURSION'),
     (31, 'Easy', 'JAVASCRIPT', 'IO'),
     (32, 'Medium', 'JAVASCRIPT', 'IO'),
     (33, 'Hard', 'JAVASCRIPT', 'IO'),
     (34, 'Easy', 'JAVASCRIPT', 'LOOP'),
     (35, 'Medium', 'JAVASCRIPT', 'LOOP'),
     (36, 'Hard', 'JAVASCRIPT', 'LOOP'),
     (37, 'Easy', 'JAVASCRIPT', 'CONDITION'),
     (38, 'Medium', 'JAVASCRIPT', 'CONDITION'),
     (39, 'Hard', 'JAVASCRIPT', 'CONDITION'),
     (40, 'Easy', 'JAVASCRIPT', 'ONE_DIMENSION_ARRAY'),
     (41, 'Medium', 'JAVASCRIPT', 'ONE_DIMENSION_ARRAY'),
     (42, 'Hard', 'JAVASCRIPT', 'ONE_DIMENSION_ARRAY'),
     (43, 'Easy', 'JAVASCRIPT', 'STRING'),
     (44, 'Medium', 'JAVASCRIPT', 'STRING'),
     (45, 'Hard', 'JAVASCRIPT', 'STRING'),
     (46, 'Easy', 'JAVASCRIPT', 'TWO_DIMENSION_ARRAY'),
     (47, 'Medium', 'JAVASCRIPT', 'TWO_DIMENSION_ARRAY'),
     (48, 'Hard', 'JAVASCRIPT', 'TWO_DIMENSION_ARRAY'),
     (49, 'Easy', 'JAVASCRIPT', 'DEEPEN'),
     (50, 'Medium', 'JAVASCRIPT', 'DEEPEN'),
     (51, 'Hard', 'JAVASCRIPT', 'DEEPEN'),
     (52, 'Easy', 'JAVASCRIPT', 'FUNCTION'),
     (53, 'Medium', 'JAVASCRIPT', 'FUNCTION'),
     (54, 'Hard', 'JAVASCRIPT', 'FUNCTION'),
     (55, 'Easy', 'JAVASCRIPT', 'MATH'),
     (56, 'Medium', 'JAVASCRIPT', 'MATH'),
     (57, 'Hard', 'JAVASCRIPT', 'MATH'),
     (58, 'Easy', 'JAVASCRIPT', 'RECURSION'),
     (59, 'Medium', 'JAVASCRIPT', 'RECURSION'),
     (60, 'Hard', 'JAVASCRIPT', 'RECURSION');

INSERT INTO problems (id, title, problem_description, input_description, output_description, stage_id)
VALUES
    (1,'Hello World', 'Hello World!를 출력하시오.', '없음', 'Hello World!를 출력하시오.', 1),
    (2,'A+B', '두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.', '첫째 줄에 A와 B가 주어진다. (0 < A, B < 10)', '첫째 줄에 A+B를 출력한다.', 2),
    (3,'A-B', '두 정수 A와 B를 입력받은 다음, A-B를 출력하는 프로그램을 작성하시오.', '첫째 줄에 A와 B가 주어진다. (0 < A, B < 10)', '첫째 줄에 A-B를 출력한다.', 3),
    (4,'Hello World', 'Hello World!를 출력하시오.', '없음', 'Hello World!를 출력하시오.', 31),
    (5,'A+B', '두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.', '첫째 줄에 A와 B가 주어진다. (0 < A, B < 10)', '첫째 줄에 A+B를 출력한다.', 32),
    (6,'A-B', '두 정수 A와 B를 입력받은 다음, A-B를 출력하는 프로그램을 작성하시오.', '첫째 줄에 A와 B가 주어진다. (0 < A, B < 10)', '첫째 줄에 A-B를 출력한다.', 33);
--     (4,'Problem 4', 'Description 4', 'Input Desc 4', 'Output Desc 4', 4),
--     (5,'Problem 5', 'Description 5', 'Input Desc 5', 'Output Desc 5', 5),
--     (6,'Problem 6', 'Description 6', 'Input Desc 6', 'Output Desc 6', 6),
--     (7,'Problem 7', 'Description 7', 'Input Desc 7', 'Output Desc 7', 7),
--     (8,'Problem 8', 'Description 8', 'Input Desc 8', 'Output Desc 8', 8),
--     (9,'Problem 9', 'Description 9', 'Input Desc 9', 'Output Desc 9', 9),
--     (10,'Problem 10', 'Description 10', 'Input Desc 10', 'Output Desc 10', 10);


INSERT INTO io_examples (id, input, output, problem_id)
VALUES
    (1,'', 'Hello World!', 1),
    (2, '1 2', '3', 2),
    (3,'3 2', '1', 3),
    (4,'', 'Hello World!', 4),
    (5, '1 2', '3', 5),
    (6,'3 2', '1', 6);


-- attempt의 경우 사용자가 문제풀이를 진행하면서 생기는 데이터이기 때문에 초기 데이터가 필요없음
-- INSERT INTO attempt (id, result, submitted_code, submit_time, user_id, problem_id)
-- VALUES
--     (1,'SUCCESS', 'code1', 1622520000000, 1, 1),
--     (2,'FAIL', 'code2', 1622523600000, 2, 2),
--     (3,'SUCCESS', 'code3', 1622527200000, 3, 3),
--     (4,'FAIL', 'code4', 1622530800000, 4, 4),
--     (5,'SUCCESS', 'code5', 1622534400000, 5, 5),
--     (6,'FAIL', 'code6', 1622538000000, 6, 6),
--     (7,'SUCCESS', 'code7', 1622541600000, 7, 7),
--     (8,'FAIL', 'code8', 1622545200000, 8, 8),
--     (9,'SUCCESS', 'code9', 1622548800000, 9, 9),
--     (10,'FAIL', 'code10', 1622552400000, 10, 10);

-- progress의 경우 사용자가 문제풀이를 진행하면서 생기는 데이터이기 때문에 초기 데이터가 필요없음
-- INSERT INTO progresses (id, user_id, stage_id, attempt_result)
-- VALUES
--     (1, 1, 1, 'SUCCESS'),
--     (2, 2, 2, 'FAIL'),
--     (3, 3, 3, 'NOT_ATTEMPTED'),
--     (4, 4, 4, 'FAIL'),
--     (5, 5, 5, 'SUCCESS'),
--     (6, 6, 6, 'NOT_ATTEMPTED'),
--     (7, 7, 7, 'NOT_ATTEMPTED'),
--     (8, 8, 8, 'FAIL'),
--     (9, 9, 9, 'SUCCESS'),
--     (10, 10, 10, 'FAIL');