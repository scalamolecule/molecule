package molecule.coreTests.spi.filter.seq.ref

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Refs._
import molecule.coreTests.setup._

case class FilterRefSeq_Card1Ref(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "mandatory" - refs { implicit conn =>
    for {
      _ <- A.i.B.iSeq.insert(
        (1, List(1, 2, 2)),
        (2, List(2)),
        (2, List(7)),
        (3, List(3)),
        (4, List())
      ).transact

      // has

      _ <- A.i.a1.B.iSeq.has(1).query.get.map(_ ==> List(
        (1, List(1, 2, 2)),
      ))
      _ <- A.i.a1.B.iSeq.has(2).query.get.map(_ ==> List(
        (1, List(1, 2, 2)),
        (2, List(2)),
      ))

      _ <- A.i.a1.B.iSeq.has(2, 1).query.get.map(_ ==> List(
        (1, List(1, 2, 2)),
        (2, List(2)),
      ))
      _ <- A.i.a1.B.iSeq.has(2, 7).query.get.map(_ ==> List(
        (1, List(1, 2, 2)),
        (2, List(2)),
        (2, List(7)),
      ))
      _ <- A.i.a1.B.iSeq.has(2, 3).query.get.map(_ ==> List(
        (1, List(1, 2, 2)),
        (2, List(2)),
        (3, List(3)),
      ))


      // hasNo

      _ <- A.i.a1.B.iSeq.hasNo(1).query.get.map(_ ==> List(
        (2, List(2)),
        (2, List(7)),
        (3, List(3)),
      ))
      _ <- A.i.a1.B.iSeq.hasNo(2).query.get.map(_ ==> List(
        (2, List(7)),
        (3, List(3)),
      ))
      _ <- A.i.a1.B.iSeq.hasNo(3).query.get.map(_ ==> List(
        (1, List(1, 2, 2)),
        (2, List(2)),
        (2, List(7)),
      ))
    } yield ()
  }


  "tacit" - refs { implicit conn =>
    for {
      _ <- A.i.B.iSeq.insert(
        (1, List(1, 2, 2)),
        (2, List(2)),
        (2, List(7)),
        (3, List(3)),
        (4, List())
      ).transact

      // has
      _ <- A.i.a1.B.iSeq_.has(1).query.get.map(_ ==> List(1))
      _ <- A.i.a1.B.iSeq_.has(2).query.get.map(_ ==> List(1, 2))
      _ <- A.i.a1.B.iSeq_.has(2, 7).query.get.map(_ ==> List(1, 2))
      _ <- A.i.a1.B.iSeq_.has(2, 3).query.get.map(_ ==> List(1, 2, 3))

      // hasNo
      _ <- A.i.a1.B.iSeq_.hasNo(1).query.get.map(_ ==> List(2, 3))
      _ <- A.i.a1.B.iSeq_.hasNo(2).query.get.map(_ ==> List(2, 3))
      _ <- A.i.a1.B.iSeq_.hasNo(3).query.get.map(_ ==> List(1, 2))

      // no value - match non-asserted attribute (null)
      _ <- A.i.a1.B.iSeq_().query.get.map(_ ==> List(4))
    } yield ()
  }

  // No filtering on optional Seq attributes
}
