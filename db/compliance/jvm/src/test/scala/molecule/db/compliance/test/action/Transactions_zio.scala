package molecule.db.compliance.test.action

import molecule.base.error.ExecutionError
import molecule.core.setup.TestUtils
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders_zio
import molecule.db.core.api.{Api_zio, Api_zio_transact}
import molecule.db.core.spi.Spi_zio
import zio.*
import zio.test.*
import zio.test.TestAspect.*
import scala.annotation.nowarn


@nowarn
case class Transactions_zio(api: Api_zio_transact with Api_zio with Spi_zio with DbProviders_zio)
  extends ZIOSpecDefault with TestUtils {

  import api.*

  @nowarn override def spec: Spec[TestEnvironment with Scope, Any] =
    suite("transactions")(
      test("commit") {
        for {
          _ <- Entity.int.insert(1 to 7).transact
          a <- Entity.int(count).query.get.map(_.head)

          _ <- Entity.int_.delete.transact
          b <- Entity.int(count).query.get.map(_.head)
        } yield {
          assertTrue(a == 7) && assertTrue(b == 0)
        }
      }.provide(types.orDie),


      test("Transaction bundle") {
        for {
          _ <- transact(
            Entity.int(1).save, //         List(1)
            Entity.int.insert(2, 3), //    List(1, 2, 3)
            Entity(1).delete, //           List(2, 3)
            Entity(3).int.*(10).update, // List(2, 30)
          )
          a <- Entity.int.a1.query.get
        } yield {
          assertTrue(a == List(2, 30))
        }
      }.provide(types.orDie),


      test("Unit of work") {
        // Use a unitOfWork when both mutations and queries are needed
        for {
          // Initial balance in two bank accounts
          _ <- Entity.s("fromAccount").int(100).save.transact
          _ <- Entity.s("toAccount").int(50).save.transact

          _ <- unitOfWork {
            for {
              _ <- Entity.s_("fromAccount").int.-(200).update.transact
              _ <- Entity.s_("toAccount").int.+(200).update.transact

              _ <- Entity.s_("fromAccount").int.query.get
                .flatMap { res =>
                  val fromBalance = res.head
                  // Check that fromAccount had sufficient funds
                  if (fromBalance < 0) {
                    // Abort all transactions in this unit of work
                    ZIO.fail(ExecutionError("Insufficient funds in fromAccount..."))
                  } else ZIO.unit
                }
            } yield ()
          }.flip.map(error =>
            assertTrue(
              error.getMessage == "Insufficient funds in fromAccount..."
            )
          )

          // No transfer transacted
          a <- Entity.s.int.query.get
        } yield {
          assertTrue(a == List(
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
            _ <- Entity.int.insert(1 to 4).transact
            a <- Entity.int(count).query.get.map(_.head)

            _ <- savepoint { sp =>
              for {
                // Delete all
                _ <- Entity.int_.delete.transact
                x <- Entity.int(count).query.get.map(_.head)

                // Roll back delete
                _ = sp.rollback()
                y <- Entity.int(count).query.get.map(_.head)
              } yield {
                assertTrue(x == 4) && assertTrue(y == 4)
              }
            }

            // Nothing deleted
            b <- Entity.int(count).query.get.map(_.head)
          } yield {
            assertTrue(a == 4) && assertTrue(b == 4)
          }
        }
      }.provide(types.orDie),

    ) @@ sequential
}
