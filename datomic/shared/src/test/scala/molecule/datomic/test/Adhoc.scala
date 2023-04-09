package molecule.datomic.test

import java.util.Date
import molecule.base.ast.SchemaAST._
import molecule.base.error._
import molecule.core.api.TxReport
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions
import molecule.boilerplate.ast.Model._

object Adhoc extends DatomicTestSuite {


  override lazy val tests = Tests {

    //    "types" - types { implicit conn =>
    //      for {
    //        _ <- Ns.int(3).save.transact
    //        _ <- Ns.int.query.get.map(_ ==> List(3))
    //
    //      } yield ()
    //    }

    "Msg" - validation { implicit conn =>
      for {
        //        _ <- Constants.errorMsg(4).save.transact
        //        _ <- Variables.errorMsg(1).int1(2).save.transact
        _ <- Variables.errorMsg(1).int1(2).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              "One-line error msg"
            )
        }


      } yield ()
    }


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
    //              |AttrOneManInt("R2", "i", V, Seq(), None, Nil, Nil, None, None)""".stripMargin
    //        }
    //      } yield ()
    //    }
  }

}
