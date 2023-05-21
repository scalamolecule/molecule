package molecule.sql.jdbc.test

import molecule.coreTests.dataModels.core.dsl.Types._
//import molecule.sql.jdbc.async._
import molecule.sql.jdbc.setup.JdbcTestSuite
import molecule.sql.jdbc.sync._
import utest._
import scala.language.implicitConversions

object AdhocJdbc extends JdbcTestSuite {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      Ns.i.insert(2, 1, 3).transact
      Ns.i.query.get ==> List(1, 2, 3)
      Ns.i.a1.query.get ==> List(1, 2, 3)
      Ns.i.d1.query.get ==> List(3, 2, 1)
    }

    //    "types" - types { implicit conn =>
    //      for {
    //        eid <- Ns.int(3).save.transact.map(_.eid)
    //        _ = println(eid)
    //        eid2 <- Ns.int(4).save.transact.map(_.eid)
    //        _ = println(eid2)
    //        _ <- Ns.int.query.get.map(_ ==> List(3, 4))
    //      } yield ()
    //    }


    //    "validation" - validation { implicit conn =>
    //      for {
    //        _ <- Type.int(3).long(3L).save.transact
    //
    //      } yield ()
    //    }


    //    "Compare with other attr value" - validation { implicit conn =>
    //      for {
    //        _ <- AttrValue.low(5).high(5).save.transact
    //          .map(_ ==> "Unexpected success").recover {
    //          case ValidationErrors(errorMap) =>
    //            errorMap ==>
    //              Map(
    //                "AttrValue.low" -> Seq(
    //                  s"""AttrValue.low with value `5` doesn't satisfy validation:
    //                     |  _ < high.value
    //                     |""".stripMargin
    //                )
    //              )
    //        }
    //      } yield ()
    //    }
    //
    //
    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //
    //      //      Ns.s.i.insert(
    //      //        ("a", 3),
    //      //        ("b", 4),
    //      //      ).transact
    //      //
    //    }
  }
}
