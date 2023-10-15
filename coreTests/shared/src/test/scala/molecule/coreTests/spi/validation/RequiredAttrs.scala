package molecule.coreTests.compliance.validation

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions

trait RequiredAttrs extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Pair" - validation { implicit conn =>
      for {
        _ <- Require.username("bob").save.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Missing/empty required attributes:
                  |  Required: password
                  |  Present : username
                  |  Missing : password
                  |""".stripMargin
          }

        // Requirement goes both ways
        _ <- Require.password("secret").save.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Missing/empty required attributes:
                  |  Required: username
                  |  Present : password
                  |  Missing : username
                  |""".stripMargin
          }

        // Both required values present
        _ <- Require.username("bob").password("secret").save.transact
      } yield ()
    }


    "Triple" - validation { implicit conn =>
      for {
        _ <- Require.i(0).x(1).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Missing/empty required attributes:
                  |  Required: y, z
                  |  Present : i, x
                  |  Missing : y, z
                  |""".stripMargin
          }
        _ <- Require.i(0).x(1).y(2).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Missing/empty required attributes:
                  |  Required: y, z, x
                  |  Present : i, x, y
                  |  Missing : z
                  |""".stripMargin
          }

        // All required values present
        _ <- Require.i(0).x(1).y(2).z(3).save.transact
      } yield ()
    }


    "Attr/ref" - validation { implicit conn =>
      for {
        _ <- Require.int(1).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Missing/empty required attributes:
                  |  Required: refB
                  |  Present : int
                  |  Missing : refB
                  |""".stripMargin
          }

        // Required ref applied
        _ <- Require.int(1).refB(42L).save.transact

        // Required ref constructed
        _ <- Require.int(1).RefB.i(2).save.transact
      } yield ()
    }


    "Ref/ref" - validation { implicit conn =>
      for {
        _ <- Require.ref1(1L).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Missing/empty required attributes:
                  |  Required: ref2
                  |  Present : ref1
                  |  Missing : ref2
                  |""".stripMargin
          }

        // 2 required refs applied
        _ <- Require.ref1(1L).ref2(2L).save.transact

        // 1 required ref applied, 1 constructed
        _ <- Require.ref1(1L).Ref2.luckyNumber(7).save.transact

        // 2 required refs constructed
        _ <- Require.i(0).Ref1.i(1)._Require.Ref2.luckyNumber(7).save.transact
      } yield ()
    }
  }
}
