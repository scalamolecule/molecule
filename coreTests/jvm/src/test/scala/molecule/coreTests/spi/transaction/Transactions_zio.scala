package molecule.coreTests.spi.transaction


import molecule.base.error.{ModelError, MoleculeError}
import molecule.core.api.{Api_zio, Api_zio_transact}
import molecule.core.spi.{Conn, Spi_zio}
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup.{DbProviders, Test, TestUtils}
import zio.{Runtime, Unsafe, ZEnvironment, ZIO}

case class Transactions_zio(
  suite: Test,
  api: Api_zio_transact with Api_zio with Spi_zio with DbProviders
) extends TestUtils {

  import api._
  import suite._


  def runIO[A](io: ZIO[Conn, MoleculeError, A])(implicit conn: Conn): A = {
    Unsafe.unsafe { implicit unsafe =>
      val runtime = Runtime.default
      runtime.unsafe.run(io.provideEnvironment(ZEnvironment(conn))).getOrThrow()
    }
  }

  test("Transaction bundle") {
    types { implicit conn =>
      // Mutation actions only
      runIO(transact(
        Entity.int(1).save, //         List(1)
        Entity.int.insert(2, 3), //    List(1, 2, 3)
        Entity(1).delete, //           List(2, 3)
        Entity(3).int.*(10).update, // List(2, 30)
      ))
      runIO(Entity.int.query.get.map(_ ==> List(2, 30)))
    }
  }

  test("Unit of work") {
    types { implicit conn =>
      // Use a unitOfWork when both mutations and queries are needed
      // Initial balance in two bank accounts
      runIO(Entity.s("fromAccount").int(100).save.transact)
      runIO(Entity.s("toAccount").int(50).save.transact)

      runIO(
        unitOfWork {
          for {
            _ <- Entity.s_("fromAccount").int.-(200).update.transact
            _ <- Entity.s_("toAccount").int.+(200).update.transact

            _ <- Entity.s_("fromAccount").int.query.get
              .flatMap {
                // Check that fromAccount had sufficient funds
                case i if i.head < 0 =>
                  // Abort all transactions in this unit of work
                  ZIO.fail(ModelError("Insufficient funds in fromAccount..."))
                case i               => ZIO.succeed(i)
              }
          } yield ()
        }.either // don't let failed ZIO fail test
      )

      // No transfer transacted
      runIO(Entity.s.int.query.get) ==> List(
        ("fromAccount", 100),
        ("toAccount", 50),
      )
    }
  }

  test("Savepoint") {
    types { implicit conn =>
      // Use savepoint within unitOfWork to
      // rollback transactions within the savepoint body
      runIO(
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
      )
    }
  }
}
