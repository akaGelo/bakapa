CREATE TABLE testTable (
  id      INT          NOT NULL,
  content VARCHAR(200) NOT NULL
);

INSERT INTO testTable (id, content) VALUES
  ('1', 'one'),
  ('2', 'two'),
  ('3', 'three');


CREATE TABLE test_ignore_table (
  id      INT          NOT NULL,
  content VARCHAR(200) NOT NULL
);

INSERT INTO test_ignore_table (id, content) VALUES
  ('4', 'four'),
  ('5', 'five'),
  ('6', 'six');


