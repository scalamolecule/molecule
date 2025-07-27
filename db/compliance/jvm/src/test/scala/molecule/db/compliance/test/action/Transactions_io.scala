package molecule.db.compliance.test.action

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.{Api_io, Api_io_transact}
import molecule.db.common.spi.Spi_io


case class Transactions_io(
  suite: MUnit,
  api: Api_io_transact & Api_io & Spi_io & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  test("Transaction bundle") {
    types {
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
    types {
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
    types {
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
