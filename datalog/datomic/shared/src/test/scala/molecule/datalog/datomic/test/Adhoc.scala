package molecule.datalog.datomic.test

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object Adhoc extends DatomicTestSuite {

  //  def aa[T](types: (Connection => Future[Unit]) => Unit): Unit = {
  //    "types" - types { implicit conn =>
  //      val res: Future[Unit] = for {
  //        _ <- Ns.int(3).save.transact
  //        _ <- Ns.int.query.get.map(_ ==> List(3))
  //      } yield ()
  //      res
  //    }
  //  }
//  def aa(types: (Connection => Any) => Any): Any = {
//    types { implicit conn =>
//      val res: Future[Unit] = for {
//        _ <- Ns.int(3).save.transact
//        _ <- Ns.int.query.get.map(_ ==> List(4))
//      } yield ()
//      res
//    }
//  }

//  import molecule.coreTests.Adhoc._

  override lazy val tests = Tests {

    //    aa(types[Future[Unit]])

//    "types0" - aa(types2)

    "types" - types { implicit conn =>
      for {
        _ <- Ns.int(3).save.transact
        _ <- Ns.int.query.get.map(_ ==> List(3))
      } yield ()
    }


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


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //        _ <- Ns.i.Tx(R2.i).insert(1, 2).transact
    //          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
    //          err ==>
    //            """Missing applied value for attribute:
    //              |AttrOneManInt("R2", "i", V, Seq(), None, None, Nil, Nil, None, None)""".stripMargin
    //        }
    //      } yield ()
    //    }
  }

}
