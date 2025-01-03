package molecule.coreTests.spi.action

import molecule.core.api.{Api_io, Api_io_transact}
import molecule.core.spi.Spi_io
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup.{DbProviders, Test, TestUtils}
import scala.language.implicitConversions

case class Transactions_io(
  suite: Test,
  api: Api_io_transact with Api_io with Spi_io with DbProviders
) extends TestUtils {

  import api._
  import suite._


  test("Transaction bundle") {
    types { implicit conn =>
      // Mutation actions only
      for {
        _ <- transact(
          Entity.int(1).save, //         List(1)
          Entity.int.insert(2, 3), //    List(1, 2, 3)
          Entity(1).delete, //           List(2, 3)
          Entity(3).int.*(10).update, // List(2, 30)
        )
        _ <- Entity.int.a1.query.get.map(_ ==> List(2, 30))
      } yield ()
    }
  }

  test("Unit of work") {
    types { implicit conn =>
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
              .map { res =>
                val fromBalance = res.head
                // Check that fromAccount had sufficient funds
                if (fromBalance < 0) {
                  // Abort all transactions in this unit of work
                  throw new Exception(
                    "Insufficient funds in fromAccount..."
                  )
                }
              }
          } yield ()
        }.recover {
          case e =>
            // Do something with failure...
            e.getMessage ==> "Insufficient funds in fromAccount..."
        }

        // No transfer transacted
        _ <- Entity.s.int.query.get.map(_ ==> List(
          ("fromAccount", 100),
          ("toAccount", 50),
        ))
      } yield ()
    }
  }

  test("Savepoint") {
    types { implicit conn =>
      // Use savepoint within unitOfWork to
      // rollback transactions within the savepoint body
      unitOfWork {
        for {
          _ <- Entity.int.insert(1 to 4).transact
          _ <- Entity.int(count).query.get.map(_.head ==> 4)

          _ <- savepoint { sp =>
            for {
              // Delete all
              _ <- Entity.int_.delete.transact
              _ <- Entity.int(count).query.get.map(_.head ==> 0)

              // Roll back delete
              _ = sp.rollback()
              _ <- Entity.int(count).query.get.map(_.head ==> 4)
            } yield ()
          }

          // Nothing deleted
          _ <- Entity.int(count).query.get.map(_.head ==> 4)
        } yield ()
      }
    }
  }
}