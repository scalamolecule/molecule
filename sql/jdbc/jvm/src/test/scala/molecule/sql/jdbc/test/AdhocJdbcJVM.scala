package molecule.sql.jdbc.test

import molecule.base.ast.SchemaAST.CardOne
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.facade.JdbcConn_jvm
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
import scala.collection.immutable.List
import scala.concurrent.Future
import scala.language.implicitConversions

object AdhocJdbcJVM extends JdbcTestSuite {

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


  override lazy val tests = Tests {
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

    "types" - types { implicit conn =>

      for {
        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))


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




        _ <- Ns.i.insert(1).transact
//        _ <- Ns.i_.<=(2).int(4).update.transact


        _ <- Ns.i.int_?.query.get.map(_ ==> List((1, None)))

        // Update entities with non-unique `i` attribute with value 1
        // Updating a non-asserted value doesn't insert it (nothing is updated)
        _ <- Ns.i_(1).int(2).update.transact
        _ <- Ns.i.int_?.query.get.map(_ ==> List((1, None)))

        // Upserting a non-asserted value inserts it
        _ <- Ns.i_(1).int(2).upsert.transact
//        _ <- rawTransact(
//          """UPDATE Ns SET
//            |  Ns.int = 2
//            |WHERE Ns.i = 1""".stripMargin
//        )

        _ <- Ns.i.int_?.query.get.map(_ ==> List((1, Some(2))))

        // Updating an asserted value updates it
        _ <- Ns.i_(1).int(3).upsert.transact
        _ <- Ns.i.int.query.get.map(_ ==> List((1, 3)))

        // Upserting an asserted value updates it
        _ <- Ns.i_(1).int(4).upsert.transact
        _ <- Ns.i.int.query.get.map(_ ==> List((1, 4)))


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

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      for {
        //        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
        //        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))


        _ <- rawQuery(
          s"""SELECT A_ownBb_B.B_id
             |FROM A_ownBb_B
             |INNER JOIN A on A.id = A_ownBb_B.A_id
             |WHERE A.id in (1)
             |""".stripMargin
        )

      } yield ()
    }

  }
}

/*

Can't use cascading deletes since the semantic model of a
cardinality-one relationship in molecule (and Datomic) is reversed
compared to sql. In Molecule, an entity is a parent if it holds
a reference to another entity. Let's see what problems we get in sql:


drop table A if exists;
drop table B if exists;
drop table C if exists;
create table A(
  id int primary key auto_increment,
  a int,
  b_id int
);
create table B(
  id int primary key auto_increment,
  b int,
  c_id int
);
create table C(
  id int primary key auto_increment,
  c int,
  c_id int
);

ALTER TABLE A
  ADD CONSTRAINT Ax
  FOREIGN KEY (B_id)
  REFERENCES B(id)
  ON DELETE CASCADE;

ALTER TABLE B
  ADD CONSTRAINT Bx
  FOREIGN KEY (C_id)
  REFERENCES C(id)
  ON DELETE CASCADE;

Insert into C(c) values (100), (200);
Insert into B(b, c_id) values (10, 1), (20, 2);
Insert into A(a, B_id) values (1, 1), (2, 2);

Select * from A;
Select * from B;
Select * from C;

/*
  In sql we would need to go "backwards" from the deepest owned entity.
  This breaks down if A has multiple owned refs that need to be deleted -
  especially if those in turn have other owned entities etc!
 */
DELETE FROM C WHERE ID in (
  select C_id from B
  inner join A
  where A.b_id = b.id And
  A.id = 1
);

Select * from A;
Select * from B;
Select * from C;

*/


