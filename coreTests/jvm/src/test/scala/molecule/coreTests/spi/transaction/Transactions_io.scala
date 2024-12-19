package molecule.coreTests.spi.transaction

import molecule.core.api.{Api_io, Api_io_transact}
import molecule.core.spi.Spi_io
import molecule.coreTests.dataModels.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite_io
import scala.language.implicitConversions

trait Transactions_io extends CoreTestSuite_io with Api_io with Api_io_transact { spi: Spi_io =>

  test("Transaction bundle") {
    types { implicit conn =>
      // Mutation actions only
      for {
        _ <- transact(
          Ns.int(1).save, //         List(1)
          Ns.int.insert(2, 3), //    List(1, 2, 3)
          Ns(1).delete, //           List(2, 3)
          Ns(3).int.*(10).update, // List(2, 30)
        )
        _ <- Ns.int.query.get.map(_ ==> List(2, 30))
      } yield ()
    }
  }

  test("Unit of work") {
    types { implicit conn =>
      // Use a unitOfWork when both mutations and queries are needed
      for {
        // Initial balance in two bank accounts
        _ <- Ns.s("fromAccount").int(100).save.transact
        _ <- Ns.s("toAccount").int(50).save.transact

        _ <- unitOfWork {
          for {
            _ <- Ns.s_("fromAccount").int.-(200).update.transact
            _ <- Ns.s_("toAccount").int.+(200).update.transact

            _ <- Ns.s_("fromAccount").int.query.get
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
        _ <- Ns.s.int.query.get.map(_ ==> List(
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
          _ <- Ns.int.insert(1 to 4).transact
          _ <- Ns.int(count).query.get.map(_.head ==> 4)

          _ <- savepoint { sp =>
            for {
              // Delete all
              _ <- Ns.int_.delete.transact
              _ <- Ns.int(count).query.get.map(_.head ==> 0)

              // Roll back delete
              _ = sp.rollback()
              _ <- Ns.int(count).query.get.map(_.head ==> 4)
            } yield ()
          }

          // Nothing deleted
          _ <- Ns.int(count).query.get.map(_.head ==> 4)
        } yield ()
      }
    }
  }
}
