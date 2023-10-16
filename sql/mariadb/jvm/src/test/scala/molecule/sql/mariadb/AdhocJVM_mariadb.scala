package molecule.sql.mariadb

import molecule.core.util.Executor._
import molecule.sql.mariadb.async._
import molecule.sql.mariadb.setup.TestSuite_mariadb
import utest._
import scala.language.implicitConversions

object AdhocJVM_mariadb extends TestSuite_mariadb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        //        _ <- Ns.int.insert(1).i.inspect
        _ <- Ns.int.insert(1).i.transact
        _ <- Ns.int.query.get.map(_ ==> List(1))

        _ <- {
          Ns.string.int.insert(("a", 1), ("b", 2)).inspect.map(_ ==>
            """========================================
              |INSERT:
              |AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 5))
              |AttrOneManInt("Ns", "int_", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 6))
              |
              |INSERT INTO Ns (
              |  string,
              |  int_
              |) VALUES (?, ?)
              |
              |(a,1)
              |(b,2)
              |----------------------------------------""".stripMargin
          )
//          Ns.string.int.insert(("a", 1), ("b", 2)).inspect
        }

        _ <- rawQuery(
          """SELECT JSON_ARRAYAGG(t1.v)
            |FROM Ns, JSON_TABLE(Ns.ints, '$[*]' COLUMNS (v int PATH '$')) t1
            |WHERE t1.v NOT IN(6)
            |""".stripMargin, true).map(_ ==> 42)


      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
    //        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
    //
    //      } yield ()
    //    }
    //
    //
    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }


    "validation" - validation { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Validation._
      for {
        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)

      } yield ()
    }
  }
}