package molecule.coreTests.spi.filterAttr.one

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Refs._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class Sorting(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Adjacent" - types { implicit conn =>
    for {
      _ <- Entity.i.int.insert(
        (2, 3),
        (1, 4),
        (1, 3),
        (7, 3)
      ).transact

      // Sort by Entity.i ASC, then Entity.int ASC
      // Sort marker for Entity.i is still primary even though it "comes after"
      // the expression having the secondary sort marker
      //           ------------------
      //          |                  |
      _ <- Entity.i.<(Entity.int.a2).a1.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))
      //                         |   |
      //                          ---
      // Secondary sort marker for Entity.int (even though it "comes before" the primary sort marker)

      _ <- Entity.i.<(Entity.int.d2).a1.query.get.map(_ ==> List(
        (1, 4),
        (1, 3),
        (2, 3),
      ))
      _ <- Entity.i.<(Entity.int.a2).d1.query.get.map(_ ==> List(
        (2, 3),
        (1, 3),
        (1, 4),
      ))
      _ <- Entity.i.<(Entity.int.d2).d1.query.get.map(_ ==> List(
        (2, 3),
        (1, 4),
        (1, 3),
      ))

      _ <- Entity.i.<(Entity.int.a1).a2.query.get.map(_ ==> List(
        (1, 3),
        (2, 3),
        (1, 4),
      ))
      _ <- Entity.i.<(Entity.int.d1).a2.query.get.map(_ ==> List(
        (1, 4),
        (1, 3),
        (2, 3),
      ))
      _ <- Entity.i.<(Entity.int.a1).d2.query.get.map(_ ==> List(
        (2, 3),
        (1, 3),
        (1, 4),
      ))
      _ <- Entity.i.<(Entity.int.d1).d2.query.get.map(_ ==> List(
        (1, 4),
        (2, 3),
        (1, 3),
      ))
    } yield ()
  }


  "CrossEntity" - refs { implicit conn =>
    for {
      _ <- A.i.B.i.insert(
        (2, 3),
        (1, 4),
        (1, 3),
        (7, 3)
      ).transact

      // Since sort markers can't attach to tacit filter attributes there's no sorting ambiguity

      _ <- A.i.<(B.i_).a1.B.i.a2.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))
      _ <- A.i.<(B.i_).a1.B.i.d2.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
      _ <- A.i.<(B.i_).d1.B.i.a2.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
      _ <- A.i.<(B.i_).d1.B.i.d2.query.get.map(_ ==> List((2, 3), (1, 4), (1, 3)))

      _ <- A.i.<(B.i_).a2.B.i.a1.query.get.map(_ ==> List((1, 3), (2, 3), (1, 4)))
      _ <- A.i.<(B.i_).a2.B.i.d1.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
      _ <- A.i.<(B.i_).d2.B.i.a1.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
      _ <- A.i.<(B.i_).d2.B.i.d1.query.get.map(_ ==> List((1, 4), (2, 3), (1, 3)))

      // Same as

      _ <- A.i.a1.B.i.>(A.i_).a2.query.get.map(_ ==> List((1, 3), (1, 4), (2, 3)))
      _ <- A.i.a1.B.i.>(A.i_).d2.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
      _ <- A.i.d1.B.i.>(A.i_).a2.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
      _ <- A.i.d1.B.i.>(A.i_).d2.query.get.map(_ ==> List((2, 3), (1, 4), (1, 3)))

      _ <- A.i.a2.B.i.>(A.i_).a1.query.get.map(_ ==> List((1, 3), (2, 3), (1, 4)))
      _ <- A.i.a2.B.i.>(A.i_).d1.query.get.map(_ ==> List((1, 4), (1, 3), (2, 3)))
      _ <- A.i.d2.B.i.>(A.i_).a1.query.get.map(_ ==> List((2, 3), (1, 3), (1, 4)))
      _ <- A.i.d2.B.i.>(A.i_).d1.query.get.map(_ ==> List((1, 4), (2, 3), (1, 3)))
    } yield ()
  }
}
