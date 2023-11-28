package molecule.coreTests.spi.crud.delete

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait Delete_id_ref extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Referenced entities" - {

      "Card-one" - refs { implicit conn =>
        for {
          e1 <- A.i.B.i.insert(
            (1, 10),
            (2, 20)
          ).transact.map(_.id)

          // 2 entities, each referencing another entity
          _ <- A.i.a1.B.i.query.get.map(_ ==> List(
            (1, 10),
            (2, 20)
          ))
          // 2 referenced entities
          _ <- B.i.a1.query.get.map(_ ==> List(10, 20))

          _ <- A(e1).delete.transact

          // 1 entity with 1 referenced entity left
          _ <- A.i.B.i.query.get.map(_ ==> List((2, 20)))

          // Referenced entities are not deleted
          _ <- B.i.a1.query.get.map(_ ==> List(10, 20))
        } yield ()
      }


      "Card-many" - refs { implicit conn =>
        for {
          e1 <- A.i.Bb.*(B.i).insert(
            (1, Seq(10, 11)),
            (2, Seq(20, 21))
          ).transact.map(_.id)

          // 2 entities, each with 2 owned sub-entities
          _ <- A.i.a1.Bb.*(B.i.a1).query.get.map(_ ==> List(
            (1, Seq(10, 11)),
            (2, Seq(20, 21))
          ))

          // 4 referenced entities
          _ <- B.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))

          _ <- if (platform == "Jdbc jvm") {
            // 4 join rows from A to B
            rawQuery("SELECT * FROM A_bb_B").map(_ ==> List(
              List(1, 1),
              List(1, 2),
              List(2, 3),
              List(2, 4),
            ))
          } else Future.unit

          // Delete first A entity and implicitly its joins to B
          _ <- A(e1).delete.transact

          // 1 entity with 2 referenced entities left
          _ <- A.i.Bb.*(B.i.a1).query.get.map(_ ==> List(
            (2, Seq(20, 21))
          ))

          // Referenced entities are not deleted
          _ <- B.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))

          _ <- if (platform == "Jdbc jvm") {
            // Join rows deleted
            rawQuery("SELECT * FROM A_bb_B").map(_ ==> List(
              // List(1, 1),
              // List(1, 2),
              List(2, 3),
              List(2, 4),
            ))
          } else Future.unit
        } yield ()
      }
    }


    "Owned entities" - {

      "Card-one" - refs { implicit conn =>
        for {
          e1 <- A.i.OwnB.i.insert(
            (1, 10),
            (2, 20)
          ).transact.map(_.id)

          // 2 entities, each with an owned sub-entity
          _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List(
            (1, 10),
            (2, 20)
          ))
          // 2 sub-entities
          _ <- B.i.a1.query.get.map(_ ==> List(10, 20))

          _ <- A(e1).delete.transact

          // 1 entity with 1 owned sub-entity left
          _ <- A.i.OwnB.i.query.get.map(_ ==> List((2, 20)))
          _ <- B.i.query.get.map(_ ==> List(20))
        } yield ()
      }

      "Card-many" - refs { implicit conn =>
        for {
          e1 <- A.i.OwnBb.*(B.i).insert(
            (1, Seq(10, 11)),
            (2, Seq(20, 21))
          ).transact.map(_.id)

          // 2 entities, each with 2 owned sub-entities
          _ <- A.i.a1.OwnBb.*(B.i.a1).query.get.map(_ ==> List(
            (1, Seq(10, 11)),
            (2, Seq(20, 21))
          ))
          // 4 sub-entities
          _ <- B.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))

          _ <- A(e1).delete.transact

          // 1 entity with 2 owned sub-entities left
          _ <- A.i.OwnBb.*(B.i.a1).query.get.map(_ ==> List(
            (2, Seq(20, 21))
          ))
          _ <- B.i.a1.query.get.map(_ ==> List(20, 21))
        } yield ()
      }

      // All owned entities are recursively deleted!

      "one + one" - refs { implicit conn =>
        for {
          e1 <- A.i.OwnB.i.OwnC.i._B.D.i.insert(
            (11, 21, 31, 41),
            (12, 22, 32, 42)
          ).transact.map(_.id)

          _ <- A.i.a1.OwnB.i.OwnC.i._B.D.i.query.get.map(_ ==> List(
            (11, 21, 31, 41),
            (12, 22, 32, 42)
          ))
          _ <- B.i.a1.query.get.map(_ ==> List(21, 22))
          _ <- C.i.a1.query.get.map(_ ==> List(31, 32))
          _ <- D.i.a1.query.get.map(_ ==> List(41, 42))

          // Delete entity e1 and recursively its owned entities
          _ <- A(e1).delete.transact
          _ <- A.i.OwnB.i.OwnC.i._B.D.i.query.get.map(_ ==> List(
            (12, 22, 32, 42)
          ))
          _ <- B.i.query.get.map(_ ==> List(22)) // 21 deleted
          _ <- C.i.query.get.map(_ ==> List(32)) // 31 deleted
          // Non-owned D entities not recursively deleted
          _ <- D.i.a1.query.get.map(_ ==> List(41, 42))
        } yield ()
      }

      "one + many" - refs { implicit conn =>
        for {
          e1 <- A.i.OwnB.i.OwnCc.*(C.i).insert(
            (11, 21, Seq(31, 32)),
            (12, 22, Seq(33, 34))
          ).transact.map(_.id)

          _ <- A.i.a1.OwnB.i.OwnCc.*(C.i.a1).query.get.map(_ ==> List(
            (11, 21, Seq(31, 32)),
            (12, 22, Seq(33, 34))
          ))
          _ <- B.i.a1.query.get.map(_ ==> List(21, 22))
          _ <- C.i.a1.query.get.map(_ ==> List(31, 32, 33, 34))

          // Delete entity e1 and recursively its owned entities
          _ <- A(e1).delete.transact
          _ <- A.i.OwnB.i.OwnCc.*(C.i).query.get.map(_ ==> List(
            (12, 22, Seq(33, 34))
          ))
          _ <- B.i.query.get.map(_ ==> List(22)) // 21 deleted
          _ <- C.i.a1.query.get.map(_ ==> List(33, 34)) // 31, 32 deleted
        } yield ()
      }

      "many + one" - refs { implicit conn =>
        for {
          e1 <- A.i.OwnBb.*(B.i.OwnC.i).insert(
            (11, Seq((21, 31))),
            (12, Seq((22, 32)))
          ).transact.map(_.id)

          _ <- A.i.a1.OwnBb.*(B.i.OwnC.i).query.get.map(_ ==> List(
            (11, Seq((21, 31))),
            (12, Seq((22, 32)))
          ))
          _ <- B.i.a1.query.get.map(_ ==> List(21, 22))
          _ <- C.i.a1.query.get.map(_ ==> List(31, 32))

          // Delete entity e1 and recursively its owned entities
          _ <- A(e1).delete.transact
          _ <- A.i.OwnBb.*(B.i.OwnC.i).query.get.map(_ ==> List(
            (12, Seq((22, 32)))
          ))
          _ <- B.i.query.get.map(_ ==> List(22)) // 21 deleted
          _ <- C.i.query.get.map(_ ==> List(32)) // 31 deleted
        } yield ()
      }

      "many + many" - refs { implicit conn =>
        for {
          e1 <- A.i.OwnBb.*(B.i.OwnCc.*(C.i)).insert(
            (11, Seq((21, Seq(31)))),
            (12, Seq((22, Seq(32))))
          ).transact.map(_.id)

          _ <- A.i.a1.OwnBb.*(B.i.OwnCc.*(C.i)).query.get.map(_ ==> List(
            (11, Seq((21, Seq(31)))),
            (12, Seq((22, Seq(32))))
          ))
          _ <- B.i.a1.query.get.map(_ ==> List(21, 22))
          _ <- C.i.a1.query.get.map(_ ==> List(31, 32))

          // Delete entity e1 and recursively its owned entities
          _ <- A(e1).delete.transact
          _ <- A.i.OwnBb.*(B.i.OwnCc.*(C.i)).query.get.map(_ ==> List(
            (12, Seq((22, Seq(32))))
          ))
          _ <- B.i.query.get.map(_ ==> List(22)) // 21 deleted
          _ <- C.i.query.get.map(_ ==> List(32)) // 31 deleted
        } yield ()
      }
    }
  }
}
