package molecule.coreTests.spi.filterAttr.seq

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Refs._
import molecule.coreTests.setup._

case class CrossEntity(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "has" - refs { implicit conn =>
    for {
      _ <- A.i.iSeq.B.iSeq.i.insert(
        (1, List(1, 2), List(1, 2, 3, 3), 3),
        (2, List(2, 3), List(2, 3), 3),
        (2, List(4), List(4), 4),
      ).transact

      _ <- A.i.iSeq_.has(B.i_).B.iSeq.i.a1.query.get.map(_.sortBy(_._2.head) ==> List(
        (2, List(2, 3), 3),
        (2, List(4), 4),
      ))
      _ <- A.i.iSeq.has(B.i_).B.iSeq_.i.a1.query.get.map(_.sortBy(_._2.head) ==> List(
        (2, List(2, 3), 3),
        (2, List(4), 4),
      ))

      _ <- A.i.a1.iSeq_.B.iSeq.has(A.i_).query.get.map(_ ==> List(
        (1, List(1, 2, 3, 3)),
        (2, List(2, 3)),
      ))
      _ <- A.i.a1.iSeq.B.iSeq_.has(A.i_).query.get.map(_ ==> List(
        (1, List(1, 2)),
        (2, List(2, 3)),
      ))
    } yield ()
  }


  "hasNo" - refs { implicit conn =>
    for {
      _ <- A.i.iSeq.B.iSeq.i.insert(
        (1, List(1, 2), List(1, 2, 3, 3), 3),
        (2, List(2, 3), List(2, 3), 3),
        (2, List(4), List(4), 4),
        (2, List(4), List(3), 4),
      ).transact

      _ <- A.i.iSeq_.hasNo(B.i_).B.iSeq.i.a1.query.get.map(_ ==> List(
        (1, List(1, 2, 3, 3), 3),
      ))
      _ <- A.i.iSeq.hasNo(B.i_).B.iSeq_.i.a1.query.get.map(_ ==> List(
        (1, List(1, 2), 3),
      ))

      _ <- A.i.iSeq_.B.iSeq.hasNo(A.i_).query.get.map(_.sortBy(_._2.head) ==> List(
        (2, List(3)),
        (2, List(4)),
      ))
      _ <- A.i.iSeq.B.iSeq_.hasNo(A.i_).query.get.map(_ ==> List(
        (2, List(4)),
      ))
    } yield ()
  }
}
