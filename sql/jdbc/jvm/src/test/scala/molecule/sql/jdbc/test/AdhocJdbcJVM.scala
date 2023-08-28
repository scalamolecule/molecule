package molecule.sql.jdbc.test

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions

object AdhocJdbcJVM extends JdbcTestSuite {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i(1).save.transact
        _ <- Ns.i.query.get.map(_ ==> List(1))


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))


      } yield ()
    }


    "unique" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Uniques._
      for {
        _ <- Uniques.int.i.s.insert(0, 1, "a").transact
        _ <- Uniques.i.s.query.get.map(_ ==> List((1, "a")))


      } yield ()
    }


    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        _ <- Require.int1.errorMsg.insert(
    //          (1, 2),
    //          (2, 2),
    //          (3, 2),
    //        ).transact
    //
    //        _ <- Variables.int1.errorMsg.query.inspect
    //        _ <- Variables.int1.<(Variables.errorMsg).query.inspect
    //        _ <- Variables.int1.<(Variables.errorMsg).query.get.map(_ ==> List())
    //
    //
    //      } yield ()
    //    }
  }
}

/*
//  val name   = Map("en" -> "hello", "de" -> "hello", "da" -> "hej")
//  val name_k = Set("en", "de", "da")
//  val name_v = Set("hello", "hej")
//
//  val name_   = 7
//  val name_k_ = 7
//  val name_v_ = 7
//
//  val name_?   = 7
//  val name_k_? = 7
//  val name_v_? = 7
//
//  val ranked   = List("Peter", "Bob", "Mary")
//  val ranked_i = List(0, 1, 2)
//
//  val rankedA   = Array("Peter", "Bob", "Mary")
//  val rankedA_i = Array(0, 1, 2)

//  val x: Instant = ???
//  val y: Long    = x.toEpochMilli
 */


/*

How to manipulate array values?

How can I retract and update values in an array?

Since I'm trying to manage a _Set_ of distinct values in an array I don't know the ordinality/position of values in the array and can
therefore only operate by value and not use the position of values in the array - if possible?

I can add a value with concatenation:
```lang-sql
DROP TABLE A IF EXISTS;
CREATE TABLE A(id INT PRIMARY KEY AUTO_INCREMENT, ints INT ARRAY);
INSERT INTO A(ints) VALUES (ARRAY[1, 2]);
SELECT ints FROM A WHERE id = 1;
// [1, 2]

// Concatenate
UPDATE A SET ints = ints || 3;
SELECT ints FROM A WHERE id = 1;
// [1, 2, 3]
```

But how can I retract a specific value?

```lang-sql
// How to retract 2?
UPDATE A SET ints = ???;
UPDATE A SET ints[2] = 7;
SELECT ints FROM A WHERE id = 1;
// [1, 3]

// Or, how to retract 1 and 2?
UPDATE A SET ints = ???;
SELECT ints FROM A WHERE id = 1;
// [3]
```

And how can I update one value to another?

```lang-sql
// How to replace 1 with 10?
UPDATE A SET ints = ???;
SELECT ints FROM A WHERE id = 1;
// [10, 2, 3]

// Or, how to replace 1 with 10 and 3 with 30?
UPDATE A SET ints = ???;
SELECT ints FROM A WHERE id = 1;
// [10, 2, 30]
```

I have tried variations on unnesting the values and filter them to exclude (retract) certain valus, then aggregate them back along these (wrong) lines:

```lang-sql
UPDATE A SET ints = ARRAY_AGG(SELECT * FROM UNNEST((SELECT ints FROM A)) WHERE ? != 2) WHERE id = 1;





UPDATE A SET ints = ints filter (where ;





array_agg(v order by v desc) filter (where v >= '4')


UPDATE A SET ints = ARRAY_AGG(SELECT * FROM UNNEST((SELECT ints FROM A)) WHERE ? != 2) WHERE id = 1;
```

DROP TABLE A IF EXISTS;
CREATE TABLE A(id INT PRIMARY KEY AUTO_INCREMENT, ints INT ARRAY);
INSERT INTO A(ints) VALUES (ARRAY[1, 2, 3]);
SELECT ints FROM A WHERE id = 1;
// [1, 2, 3]






DROP TABLE A IF EXISTS;
CREATE TABLE A(id INT PRIMARY KEY AUTO_INCREMENT, ints INT ARRAY);
INSERT INTO A(ints) VALUES (ARRAY[1, 2, 3]);
SELECT ints FROM A WHERE id = 1;
UPDATE A SET ints = (
  SELECT ARRAY_AGG(i) FROM (
    SELECT i FROM UNNEST(SELECT ints FROM A WHERE id = 1) AS i WHERE i != 2
  )
) WHERE id = 1;
SELECT ints FROM A WHERE id = 1;

DROP TABLE A IF EXISTS;
CREATE TABLE A(id INT PRIMARY KEY AUTO_INCREMENT, ints INT ARRAY);
INSERT INTO A(ints) VALUES (ARRAY[1, 2, 3]);
SELECT ints FROM A WHERE id = 1;
UPDATE A SET ints = (
  SELECT ARRAY_AGG(CASE WHEN i = 1 THEN 40 ELSE i END) FROM (
    SELECT i FROM UNNEST(SELECT ints FROM A WHERE id = 1) AS i
  )
) WHERE id = 1;
SELECT ints FROM A WHERE id = 1;


DROP TABLE A IF EXISTS;
CREATE TABLE A(id INT PRIMARY KEY AUTO_INCREMENT, ints INT ARRAY);
INSERT INTO A(ints) VALUES (ARRAY[1, 2, 3]);
SELECT ints FROM A WHERE id = 1;
UPDATE A SET ints = (
  SELECT ARRAY_AGG(CASE
    WHEN i = 1 THEN 40
    WHEN i = 3 THEN 72
    ELSE i
    END) FROM (
    SELECT i FROM UNNEST(SELECT ints FROM A WHERE id = 1) AS i
  )
) WHERE id = 1;
SELECT ints FROM A WHERE id = 1;


UPDATE A SET ints = (
  SELECT ARRAY_AGG(CASE
    WHEN i = 1 THEN 40
    WHEN i = 3 THEN 72
    ELSE i
    END) FROM (
    SELECT i FROM UNNEST(SELECT ints FROM A WHERE id = 1) AS i
  )
) WHERE id = 1;






UPDATE A SET ints = (
  SELECT ARRAY_AGG(i) FROM UNNEST((SELECT ints FROM A)) i WHERE i != 2
) WHERE id = 1;

UPDATE A SET ints = (
  SELECT ARRAY_AGG(CASE WHEN i = 1 THEN 40 ELSE i END) FROM UNNEST((SELECT ints FROM A)) as i
) WHERE id = 1;

UPDATE A SET ints = (
  SELECT ARRAY_AGG(i) FROM UNNEST(A.ints) i WHERE i != 2
) WHERE id = 1;

UPDATE A SET ints = (
  SELECT ARRAY_AGG(CASE
                     WHEN i = 1 THEN 40
                     WHEN i = 3 THEN 72
                     ELSE i
                   END) FROM UNNEST(SELECT ints FROM A) i
) WHERE id = 1;

Thanks @Freeman. Unfortunately I can't get any of your suggestions to work. `ints` can't be found, but after replacing that with `SELECT ints FROM A`, `i` is not found.


wget https://repo1.maven.org/maven2/com/h2database/h2/2.2.220/h2-2.2.220.jar
java -cp h2-2.2.220.jar org.h2.tools.Shell

SELECT ints FROM A WHERE id = 1;
// [1, 3]

UPDATE A SET ints = ints || 2;
SELECT ints FROM A WHERE id = 1;
// [1, 3, 2]

UPDATE A SET ints = ARRAY_EXCEPT(ints, ARRAY[1, 2]) WHERE id = 1;
SELECT ints FROM A WHERE id = 1;
// [3]

UPDATE A SET ints = ints || array[1, 2];
SELECT ints FROM A WHERE id = 1;
// [3, 1, 2]

UPDATE A SET ints = ARRAY_REPLACE(ARRAY_REPLACE(ints, 1, 40), 3, 72) WHERE id = 1;
SELECT ints FROM A WHERE id = 1;
// [72, 40, 2]


//UPDATE A SET ints = ARRAY_REMOVE(ints, 2) WHERE id = 1;

SELECT * FROM UNNEST((SELECT ints FROM A)) where c1 > 2;
SELECT * FROM UNNEST((SELECT ints FROM A)) where c1 != 7;
SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7;

array_agg(v order by v desc) filter (where v >= '4')

select array_agg(SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7) from A;
select array_agg(SELECT * FROM UNNEST(SELECT ints FROM A FETCH FIRST ROW ONLY) where c1 != 7) from A;
select array_agg((SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7 FETCH FIRST ROW ONLY)) from A;

select array_agg(SELECT * FROM UNNEST(SELECT ints FROM A) FETCH FIRST ROW ONLY) from A;
SELECT * FROM UNNEST(SELECT ints FROM A) FETCH FIRST ROW ONLY;
SELECT * FROM UNNEST(SELECT ints FROM A);

select array_agg(SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7);
select array_agg((SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7)) over ();
select array_agg((SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7)) from A;
select array_agg(ANY (SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7)) from A;
select array_agg(SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7) from A;

SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7;
select concat((SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7), ', ');
select concat((SELECT id FROM A), ', ');
select concat_ws(', ', (SELECT ints FROM A), 'XXX');
select concat_ws(', ', array_agg(SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7), 'XXX');
select concat_ws(', ', (SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7 FETCH FIRST ROW ONLY), 'XXX');
select concat_ws(', ', (SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7 limit 2), 'XXX');
select concat_ws(', ', (SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7), 'XXX');

select array_agg(SELECT * FROM UNNEST(SELECT ints FROM A) FETCH FIRST ROW ONLY);

select array_agg(SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 7);
select array_agg((SELECT ints FROM A));


SELECT * FROM UNNEST((SELECT ints FROM A)) where c1 != 7;
SELECT values(ints) t(a) from A;

select *
from (values (array[1, 2])) t(a)
where 1 in (unnest(t.a));

select * from (values (array[1, 2])) t(a) where 1 in (table(i int = t.a));
select * from (values (array[1, 2])) t(a) where 1 in (UNNEST(t.a));

VALUES ARRAY(SELECT X FROM SYSTEM_RANGE(1, 10));
VALUES ARRAY(SELECT * FROM SYSTEM_RANGE(1, 10));
select SYSTEM_RANGE(1, 10);


VALUES ARRAY(SELECT * FROM SYSTEM_RANGE(1, 10));
VALUES ARRAY(SELECT * FROM UNNEST((SELECT ints FROM A)) where c1 != 7);
VALUES ARRAY(SELECT c1 FROM UNNEST(SELECT ints FROM A));
select ARRAY(SELECT c1 FROM UNNEST(SELECT ints FROM A));

SELECT X FROM SYSTEM_RANGE(1, 10);
select array(SELECT X FROM SYSTEM_RANGE(1, 10));
select (SELECT X FROM SYSTEM_RANGE(1, 10)) FETCH FIRST ROW ONLY;
SELECT X FROM SYSTEM_RANGE(1, 10) FETCH FIRST ROW ONLY;
SELECT X FROM SYSTEM_RANGE(1, 10) limit 2;

explain SELECT X FROM SYSTEM_RANGE(1, 10);
VALUES ARRAY(SELECT * FROM UNNEST(SELECT ints FROM A FETCH FIRST ROW ONLY) where c1 != 7);

select array_agg(SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 2);

//SELECT * FROM UNNEST(SELECT ints FROM A FETCH FIRST ROW ONLY) where c1 != 7;


VALUES ARRAY(SELECT X FROM SYSTEM_RANGE(1, 3));
// [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


UPDATE A SET ints = array[4, 5, 6];
UPDATE A SET ints = values array(select (1,2,3));

SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 2;
// C1
// 1
// 3
VALUES ARRAY(SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 2);
// [null, null]


DROP TABLE A IF EXISTS;
CREATE TABLE A(id INT PRIMARY KEY AUTO_INCREMENT, ints INT ARRAY);
INSERT INTO A(ints) VALUES (ARRAY[1, 2, 3]);

DROP TABLE B IF EXISTS;
CREATE TABLE B(n INT);
INSERT INTO B(n) VALUES (1), (2), (4);
SELECT * FROM B;

SELECT * FROM A;
UPDATE A SET ints = values array(select n from B);
SELECT * FROM A;



SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 2;

select array_agg(SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 2);
select values(SELECT * FROM UNNEST(SELECT ints FROM A) where c1 != 2);

*/