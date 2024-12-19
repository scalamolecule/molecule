package molecule.coreTests.spi.transaction


import molecule.base.error.{InsertErrors, ModelError, ValidationErrors}
import molecule.core.api.{Api_zio, Api_zio_transact}
import molecule.core.spi.Spi_zio
import molecule.coreTests.dataModels.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite_zio
import zio._
import zio.test.TestAspect._
import zio.test._
import scala.annotation.nowarn


trait Transactions_zio extends CoreTestSuite_zio with Api_zio with Api_zio_transact { spi: Spi_zio =>

  @nowarn override def spec: Spec[TestEnvironment with Scope, Any] =
    suite("Molecule ZIO api")(

      test("Transaction bundle") {
        // Mutation actions only
        for {
          _ <- transact(
            Ns.int(1).save, //         List(1)
            Ns.int.insert(2, 3), //    List(1, 2, 3)
            Ns(1).delete, //           List(2, 3)
            Ns(3).int.*(10).update, // List(2, 30)
          )
          result <- Ns.int.query.get
        } yield {
          assertTrue(result == List(2, 30))
          //          assertTrue(result == List())
        }
      }.provide(types.orDie),


      test("Unit of work") {
        // Use a unitOfWork when both mutations and queries are needed
        for {
          // Initial balance in two bank accounts
          _ <- Ns.s("fromAccount").int(100).save.transact
          _ <- Ns.s("toAccount").int(50).save.transact

          _ <- unitOfWork(
            for {
              _ <- Ns.s_("fromAccount").int.-(200).update.transact
              _ <- Ns.s_("toAccount").int.+(200).update.transact

              _ <- Ns.s_("fromAccount").int.query.get
                .flatMap {
                  // Check that fromAccount had sufficient funds
                  case i if i.head < 0 =>
                    // Abort all transactions in this unit of work
                    ZIO.fail(ModelError("Insufficient funds in fromAccount..."))
                  case i               => ZIO.succeed(i)
                }
            } yield ()
          ).either // don't let failed ZIO fail test

          result <- Ns.s.a1.int.query.get
        } yield {
          // No transfer transacted
          assertTrue(result == List(
            ("fromAccount", 100),
            ("toAccount", 50),
          ))
        }
      }.provide(types.orDie),


      test("Savepoint") {
        // Use savepoint within unitOfWork to
        // rollback transactions within the savepoint body
        unitOfWork {
          for {
            _ <- Ns.int.insert(1 to 4).transact
            a <- Ns.int(count).query.get

            _ <- savepoint { sp =>
              for {
                // Delete all
                _ <- Ns.int_.delete.transact
                b <- Ns.int(count).query.get

                // Roll back delete
                _ = sp.rollback()
                c <- Ns.int(count).query.get
              } yield {
                assertTrue(b == List(0)) &&
                  assertTrue(c == List(4))
              }
            }

            // Nothing deleted
            d <- Ns.int(count).query.get
          } yield {
            assertTrue(a == List(4)) &&
              assertTrue(d == List(4))
          }
        }
      }.provide(types.orDie),

    ) @@ sequential
}
