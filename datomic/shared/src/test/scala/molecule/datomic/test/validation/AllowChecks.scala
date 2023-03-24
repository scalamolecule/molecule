package molecule.datomic.test.validation

import molecule.base.util.exceptions._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object AllowChecks extends DatomicTestSuite {


  lazy val tests = Tests {

    "Allowed, default msg" - validation { implicit conn =>
      for {
        _ <- Allowed.luckyNumber(8).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==> "Allowed.luckyNumber" ->
              Seq("Value `8` is not one of the allowed values in Seq(7, 9, 13)")
        }
        _ <- Allowed.luckyNumber(7, 8).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==> "Allowed.luckyNumber" ->
              Seq("Value `8` is not one of the allowed values in Seq(7, 9, 13)")
        }
        _ <- Allowed.luckyNumber(7).save.transact // ok
      } yield ()
    }

    "Allowed, msg" - validation { implicit conn =>
      for {
        _ <- Allowed.luckyNumber2(5).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==> "Allowed.luckyNumber2" ->
              Seq("Lucky number can only be 7, 9 or 13")
        }
        _ <- Allowed.luckyNumber2(7).save.transact
      } yield ()
    }
  }
}
