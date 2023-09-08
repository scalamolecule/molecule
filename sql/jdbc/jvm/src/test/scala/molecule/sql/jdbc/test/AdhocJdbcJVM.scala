package molecule.sql.jdbc.test

import molecule.core.util.Executor._
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
import scala.annotation.nowarn
import scala.language.implicitConversions

object AdhocJdbcJVM extends JdbcTestSuite {

  val a = (1, Set(0, 1, 2), Set(1, 2, 3))
  val b = (2, Set(2, 3), Set(2, 3))
  val c = (3, Set(4), Set(3))

  @nowarn // (Allow pattern matching results without warnings)
  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))



        _ <- Ns.i.ii.ints.insert(a, b, c).transact

        //        _ <- Ns.i.ii.has(Set(2, 3)).ints.query.get.map(_ ==> List(b))

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
        //            |  Ns.i    IS NOT NULL AND
        //            |  Ns.ii   IS NOT NULL AND
        //            |  Ns.ints IS NOT NULL
        //            |GROUP BY Ns.i
        //            |HAVING COUNT(*) > 0
        //            |;""".stripMargin)

        //        _ <- rawTransact("insert into Ns(ints) values (array[1,2,2])")

        //        _ <- rawQuery("""SELECT * from TABLE(V INT = (SELECT ints FROM Ns WHERE id = 4));""".stripMargin)
        //        _ <- rawQuery("""SELECT distinct * from TABLE(V INT = (SELECT ints FROM Ns WHERE id = 4));""".stripMargin)
        /*

        DROP TABLE A IF EXISTS;
        CREATE TABLE A(foo INT ARRAY, bar INT ARRAY);
        INSERT INTO A(foo, bar) VALUES (ARRAY[1, 2, 3], ARRAY[1, 2]), (ARRAY[2, 3], ARRAY[1, 2, 3]);
        SELECT foo FROM A WHERE DISTINCT(foo || bar) = foo;
        SELECT foo FROM A WHERE foo <contains all values of bar>;
        // [1, 2, 3]


        If I could get the distinct values of an array I could something like this

        ```lang-sql
        WHERE DISTINCT(foo || bar) = foo
        ```
        since if concatenating foo and bar doesn't add more values, the values of bar must already be i foo.


        Problem seems that I can't use the various functions UNIQUE, DISTINCT, EXISTS, INTERSECTS etc since they work on the
        array value as a whole while I need to compare the values inside. I have tried using TABLE, UNNEST and
        looked in the h2 project tests for clues, all without luck.


         */

        _ <- rawQuery(
          """SELECT DISTINCT
            |  Ns.ii,
            |  Ns.ints,
            |  array(SELECT distinct * FROM UNNEST(ARRAY[1, 2, 2])),
            |  array(SELECT distinct * FROM UNNEST(Ns.ints))
            |FROM Ns
            |WHERE
            |  Ns.i    IS NOT NULL AND
            |  Ns.ii   IS NOT NULL AND
            |  Ns.ints IS NOT NULL
            |GROUP BY Ns.i
            |HAVING COUNT(*) > 0
            |;""".stripMargin)
        //            |  array(SELECT * FROM UNNEST(ARRAY[1, 2, 2])),
        //            |  array_agg(Ns.ii || Ns.ints),
        //            |  SELECT ints FROM Ns WHERE id = 1
        //            |  (select * from unnest(Ns.ints)),
        //            |  (select V from TABLE(V INT = Ns.ints)),
        //            |  ARRAY(SELECT V FROM TABLE(V INT = (SELECT x.ints FROM Ns x WHERE x.id = 1))),
        //            |  ARRAY_AGG(Ns.ints)
        //            |  ARRAY(SELECT V FROM TABLE(V INT = Ns.ints)),
        //            |  (select Ns.ints from Ns limit 1),

        _ <- Ns.i.ii.has(Ns.ints).query.get.map(_ ==> List(b))
        //        _ <- Ns.i.ii.has(Ns.ints).query.get.map(_ ==> List(b))
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
        _ <- Uniques.i(1).save.transact

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
// Possible Map attribute key/value accessors?

//  val name   = Map("en" -> "hello", "de" -> "hello", "da" -> "hej")
//  val name_k = Set("en", "de", "da") // unique keys
//  val name_v = Seq("hello", "hej") // non-unique values
//
//  val name_
//  val name_k_
//  val name_v_
//
//  val name_?
//  val name_k_?
//  val name_v_?
 */