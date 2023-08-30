package molecule.sql.jdbc.test

import molecule.base.error.ExecutionError
import molecule.core.util.Executor._
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
import scala.language.implicitConversions

object AdhocJdbcJVM extends JdbcTestSuite {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i(1).save.transact
        _ <- Ns.i.query.get.map(_ ==> List(1))





        //        _ <- Ns.i.string_?.insert((1, Some(string2)), (1, None), (1, Some(string1))).transact
        ////        _ <- Ns.i.int_?.insert((2, Some(int2)), (2, None), (2, Some(int1))).transact
        ////        _ <- Ns.i.long_?.insert((3, Some(long2)), (3, None), (3, Some(long1))).transact
        ////        _ <- Ns.i.float_?.insert((4, Some(float2)), (4, None), (4, Some(float1))).transact
        ////        _ <- Ns.i.double_?.insert((5, Some(double2)), (5, None), (5, Some(double1))).transact
        ////        _ <- Ns.i.boolean_?.insert((6, Some(true)), (6, None), (6, Some(false))).transact
        ////        _ <- Ns.i.bigInt_?.insert((7, Some(bigInt2)), (7, None), (7, Some(bigInt1))).transact
        ////        _ <- Ns.i.bigDecimal_?.insert((8, Some(bigDecimal2)), (8, None), (8, Some(bigDecimal1))).transact
        ////        _ <- Ns.i.date_?.insert((9, Some(date2)), (9, None), (9, Some(date1))).transact
        ////        _ <- Ns.i.uuid_?.insert((10, Some(uuid2)), (10, None), (10, Some(uuid1))).transact
        ////        _ <- Ns.i.uri_?.insert((11, Some(uri2)), (11, None), (11, Some(uri1))).transact
        ////        _ <- Ns.i.byte_?.insert((12, Some(byte2)), (12, None), (12, Some(byte1))).transact
        ////        _ <- Ns.i.short_?.insert((13, Some(short2)), (13, None), (13, Some(short1))).transact
        ////        _ <- Ns.i.char_?.insert((14, Some(char2)), (14, None), (14, Some(char1))).transact
        ////        _ <- Ns.i.ref_?.insert((15, Some(ref2)), (15, None), (15, Some(ref1))).transact
        //
        //        // Ascending
        //        _ <- Ns.i(1).string_?.a1.query.i.get.map(_ ==> List((1, None), (1, Some(string1)), (1, Some(string2))))
        ////        _ <- Ns.i(2).int_?.a1.query.get.map(_ ==> List((2, None), (2, Some(int1)), (2, Some(int2))))
        ////        _ <- Ns.i(3).long_?.a1.query.get.map(_ ==> List((3, None), (3, Some(long1)), (3, Some(long2))))
        ////        _ <- Ns.i(4).float_?.a1.query.get.map(_ ==> List((4, None), (4, Some(float1)), (4, Some(float2))))
        ////        _ <- Ns.i(5).double_?.a1.query.get.map(_ ==> List((5, None), (5, Some(double1)), (5, Some(double2))))
        ////        _ <- Ns.i(6).boolean_?.a1.query.get.map(_ ==> List((6, None), (6, Some(false)), (6, Some(true))))
        ////        _ <- Ns.i(7).bigInt_?.a1.query.get.map(_ ==> List((7, None), (7, Some(bigInt1)), (7, Some(bigInt2))))
        ////        _ <- Ns.i(8).bigDecimal_?.a1.query.get.map(_ ==> List((8, None), (8, Some(bigDecimal1)), (8, Some(bigDecimal2))))
        ////        _ <- Ns.i(9).date_?.a1.query.get.map(_ ==> List((9, None), (9, Some(date1)), (9, Some(date2))))
        ////        _ <- Ns.i(10).uuid_?.a1.query.get.map(_ ==> List((10, None), (10, Some(uuid1)), (10, Some(uuid2))))
        ////        _ <- Ns.i(11).uri_?.a1.query.get.map(_ ==> List((11, None), (11, Some(uri1)), (11, Some(uri2))))
        ////        _ <- Ns.i(12).byte_?.a1.query.get.map(_ ==> List((12, None), (12, Some(byte1)), (12, Some(byte2))))
        ////        _ <- Ns.i(13).short_?.a1.query.get.map(_ ==> List((13, None), (13, Some(short1)), (13, Some(short2))))
        ////        _ <- Ns.i(14).char_?.a1.query.get.map(_ ==> List((14, None), (14, Some(char1)), (14, Some(char2))))
        ////        _ <- Ns.i(15).ref_?.a1.query.get.map(_ ==> List((15, None), (15, Some(ref1)), (15, Some(ref2))))
        //
        //        // Descending
        //        _ <- Ns.i(1).string_?.d1.query.i.get.map(_ ==> List((1, Some(string2)), (1, Some(string1)), (1, None)))
        ////        _ <- Ns.i(2).int_?.d1.query.get.map(_ ==> List((2, Some(int2)), (2, Some(int1)), (2, None)))
        ////        _ <- Ns.i(3).long_?.d1.query.get.map(_ ==> List((3, Some(long2)), (3, Some(long1)), (3, None)))
        ////        _ <- Ns.i(4).float_?.d1.query.get.map(_ ==> List((4, Some(float2)), (4, Some(float1)), (4, None)))
        ////        _ <- Ns.i(5).double_?.d1.query.get.map(_ ==> List((5, Some(double2)), (5, Some(double1)), (5, None)))
        ////        _ <- Ns.i(6).boolean_?.d1.query.get.map(_ ==> List((6, Some(true)), (6, Some(false)), (6, None)))
        ////        _ <- Ns.i(7).bigInt_?.d1.query.get.map(_ ==> List((7, Some(bigInt2)), (7, Some(bigInt1)), (7, None)))
        ////        _ <- Ns.i(8).bigDecimal_?.d1.query.get.map(_ ==> List((8, Some(bigDecimal2)), (8, Some(bigDecimal1)), (8, None)))
        ////        _ <- Ns.i(9).date_?.d1.query.get.map(_ ==> List((9, Some(date2)), (9, Some(date1)), (9, None)))
        ////        _ <- Ns.i(10).uuid_?.d1.query.get.map(_ ==> List((10, Some(uuid2)), (10, Some(uuid1)), (10, None)))
        ////        _ <- Ns.i(11).uri_?.d1.query.get.map(_ ==> List((11, Some(uri2)), (11, Some(uri1)), (11, None)))
        ////        _ <- Ns.i(12).byte_?.d1.query.get.map(_ ==> List((12, Some(byte2)), (12, Some(byte1)), (12, None)))
        ////        _ <- Ns.i(13).short_?.d1.query.get.map(_ ==> List((13, Some(short2)), (13, Some(short1)), (13, None)))
        ////        _ <- Ns.i(14).char_?.d1.query.get.map(_ ==> List((14, Some(char2)), (14, Some(char1)), (14, None)))
        ////        _ <- Ns.i(15).ref_?.d1.query.get.map(_ ==> List((15, Some(ref2)), (15, Some(ref1)), (15, None)))
        //
        //
        //
        //        _ <- Ns.i.ii.ints.insert(a, b, c).transact
        //
        //
        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  Ns.i,
        //            |  Ns.ii,
        //            |  ARRAY_AGG(Ns.ints)
        //            |FROM Ns
        //            |WHERE
        //            |  (ARRAY_CONTAINS(Ns.ii, 2) AND
        //            |   ARRAY_CONTAINS(Ns.ii, 3)) AND
        //            |  Ns.i    IS NOT NULL AND
        //            |  Ns.ii   IS NOT NULL AND
        //            |  Ns.ints IS NOT NULL
        //            |GROUP BY Ns.i
        //            |HAVING COUNT(*) > 0
        //            |;""".stripMargin)
        //
        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  Ns.i,
        //            |  Ns.ii,
        //            |  ARRAY_AGG(Ns.ints)
        //            |FROM Ns
        //            |WHERE
        //            |  ARRAY_CONTAINS(Ns.ii, 3) AND
        //            |  Ns.i    IS NOT NULL AND
        //            |  Ns.ii   IS NOT NULL AND
        //            |  Ns.ints IS NOT NULL
        //            |GROUP BY Ns.i
        //            |HAVING COUNT(*) > 0
        //            |;""".stripMargin)
        //
        //        _ <- Ns.i.ii.has(Set(2, 3)).ints.query.i.get.map(_ ==> List(b))
        //        _ <- Ns.i.ii.has(Ns.ints).query.i.get.map(_ ==> List(b))
        //        _ <- Ns.i.ii.has(Ns.ints_).query.get.map(_ ==> List((2, Set(2, 3)))) // Ns.ii
        //        _ <- Ns.i.ii_.has(Ns.ints).query.get.map(_ ==> List((2, Set(2, 3)))) // Ref.ints
        //        _ <- Ns.i.ii_.has(Ns.ints_).query.get.map(_ ==> List(2))

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



UPDATE A SET ints = ARRAY(
  SELECT V
  FROM TABLE(V INT = (SELECT ints FROM A WHERE id = 1))
  WHERE V != 2
);



UPDATE A SET ints = ARRAY[1, 2, 3];
UPDATE A SET ints = ARRAY(SELECT V FROM TABLE(V INT = (SELECT ints FROM A WHERE id = 1)) where V != 2);
SELECT ints FROM A WHERE id = 1;
// [1, 3]


// How to replace 1 with 10?
UPDATE A SET ints = ARRAY[1, 2, 3];
SELECT ints FROM A WHERE id = 1;
UPDATE A SET ints = ARRAY(SELECT CASE when V=1 then 10 when v=2 then 20 else V end FROM TABLE(V INT = (SELECT ints FROM A WHERE id = 1)));
SELECT ints FROM A WHERE id = 1;

UPDATE A
SET ints = ARRAY(
  SELECT CASE
    when v = 1 then 10
    when v = 2 then 20
    else v
  end
FROM TABLE(v INT = (SELECT ints FROM A WHERE id = 1)));

UPDATE A SET ints = ARRAY(SELECT CASEWHEN(V=1, 10, V) FROM TABLE(V INT = (SELECT ints FROM A WHERE id = 1)));



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