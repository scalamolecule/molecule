package molecule.db.compliance.test.crud.delete

import scala.concurrent.Future
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders

case class Delete_id(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "1 entity" - refs {
    for {
      case List(e1, e2, _) <- A.i.insert(1, 2, 3).transact.map(_.ids)
      _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- A(e1).delete.transact
      // or
      _ <- A.id_(e2).delete.transact
      _ <- A.i.query.get.map(_ ==> List(3))
    } yield ()
  }


  "n entities vararg" - refs {
    for {
      case List(e1, e2, _) <- A.i.insert(1, 2, 3).transact.map(_.ids)
      _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- A(e1, e2).delete.transact
      _ <- A.i.query.get.map(_ ==> List(3))
    } yield ()
  }

  "n entities iterable" - refs {
    for {
      case List(e1, e2, _) <- A.i.insert(1, 2, 3).transact.map(_.ids)
      _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- A(Seq(e1, e2)).delete.transact
      _ <- A.i.query.get.map(_ ==> List(3))
    } yield ()
  }

  "0 entities" - refs {
    for {
      _ <- A.i.insert(1, 2, 3).transact
      _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))

      _ <- A(Seq.empty[Long]).delete.transact

      // No entities deleted
      _ <- A.i.a1.query.get.map(_ ==> List(1, 2, 3))
    } yield ()
  }


  "Delete child rows" - refs {
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


  "Parents with non-owned children can't be deleted " - refs {
    // A doesn't own B
    for {
      e1 <- A.i.Bb.*(B.i).insert(
        (1, Seq(10, 11)),
        (2, Seq(20, 21))
      ).transact.map(_.id)

      // 2 entities, each with 2 sub-entities
      _ <- A.i.a1.Bb.*(B.i.a1).query.get.map(_ ==> List(
        (1, Seq(10, 11)),
        (2, Seq(20, 21))
      ))

      // 4 referenced entities
      _ <- B.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))

      // Referential integrity constraint on fk prevents orphaning B children
      _ <- A(e1).delete.transact
        .map(_ ==> "Unexpected success").recover {
          case e: Exception =>
            e.getMessage ==>
              """Referential integrity constraint violation: "_A_2: PUBLIC.B FOREIGN KEY(A) REFERENCES PUBLIC.A(ID) (CAST(1 AS BIGINT))"; SQL statement:
                |DELETE FROM A
                |WHERE
                |  A.id = 1 [23503-232]""".stripMargin
        }

      // No data deleted
      _ <- A.i.a1.Bb.*(B.i.a1).query.get.map(_ ==> List(
        (1, Seq(10, 11)),
        (2, Seq(20, 21))
      ))
    } yield ()
  }


  "Delete parent and owned children" - refs {
    // G owns H
    for {
      e1 <- G.i.Hh.*(H.i).insert(
        (1, Seq(10, 11)),
        (2, Seq(20, 21))
      ).transact.map(_.id)

      // 2 entities, each with 2 sub-entities
      _ <- G.i.a1.Hh.*(H.i.a1).query.get.map(_ ==> List(
        (1, Seq(10, 11)),
        (2, Seq(20, 21))
      ))

      // 4 referenced entities
      _ <- H.i.a1.query.get.map(_ ==> List(10, 11, 20, 21))

      // Delete first G parent and its H children with cascade deletion
      _ <- G(e1).delete.transact

      // 1 parent with 2 children remains
      _ <- G.i.Hh.*(H.i.a1).query.get.map(_ ==> List(
        (2, Seq(20, 21))
      ))

      // Children (10, 11) of fist parent are cascade deleted
      _ <- H.i.a1.query.get.map(_ ==> List(20, 21))
    } yield ()
  }
}
