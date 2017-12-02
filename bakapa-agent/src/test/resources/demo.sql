CREATE TABLE testTable (
  id      INT          NOT NULL,
  content VARCHAR(200) NOT NULL
);

INSERT INTO testTable (id, content) VALUES
  ('1', 'one'),
  ('2', 'two'),
  ('1', 'three');

