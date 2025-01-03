package molecule.coreTests.spi.filterAttr.seq

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class Adjacent(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "has" - types { implicit conn =>
    for {
      _ <- Entity.s.iSeq.i.insert(
        ("a", List(1, 2), 1),
        ("b", List(3), 2),
      ).transact

      _ <- Entity.s.iSeq.has(Entity.i).query.get.map(_ ==> List(("a", List(1, 2), 1)))
      _ <- Entity.s.iSeq.has(Entity.i_).query.get.map(_ ==> List(("a", List(1, 2))))
      _ <- Entity.s.iSeq_.has(Entity.i).query.get.map(_ ==> List(("a", 1)))
      _ <- Entity.s.iSeq_.has(Entity.i_).query.get.map(_ ==> List("a"))

      // Filter compare attribute itself
      _ <- Entity.s.iSeq.has(Entity.i(1)).query.get.map(_ ==> List(("a", List(1, 2), 1)))
      _ <- Entity.s.iSeq.has(Entity.i.not(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq.has(Entity.i.<(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq.has(Entity.i.<=(1)).query.get.map(_ ==> List(("a", List(1, 2), 1)))
      _ <- Entity.s.iSeq.has(Entity.i.>(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq.has(Entity.i.>=(1)).query.get.map(_ ==> List(("a", List(1, 2), 1)))

      _ <- Entity.s.iSeq.has(Entity.i_(1)).query.get.map(_ ==> List(("a", List(1, 2))))
      _ <- Entity.s.iSeq.has(Entity.i_.not(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq.has(Entity.i_.<(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq.has(Entity.i_.<=(1)).query.get.map(_ ==> List(("a", List(1, 2))))
      _ <- Entity.s.iSeq.has(Entity.i_.>(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq.has(Entity.i_.>=(1)).query.get.map(_ ==> List(("a", List(1, 2))))

      _ <- Entity.s.iSeq_.has(Entity.i(1)).query.get.map(_ ==> List(("a", 1)))
      _ <- Entity.s.iSeq_.has(Entity.i.not(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq_.has(Entity.i.<(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq_.has(Entity.i.<=(1)).query.get.map(_ ==> List(("a", 1)))
      _ <- Entity.s.iSeq_.has(Entity.i.>(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq_.has(Entity.i.>=(1)).query.get.map(_ ==> List(("a", 1)))

      _ <- Entity.s.iSeq_.has(Entity.i_(1)).query.get.map(_ ==> List("a"))
      _ <- Entity.s.iSeq_.has(Entity.i_.not(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq_.has(Entity.i_.<(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq_.has(Entity.i_.<=(1)).query.get.map(_ ==> List("a"))
      _ <- Entity.s.iSeq_.has(Entity.i_.>(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq_.has(Entity.i_.>=(1)).query.get.map(_ ==> List("a"))
    } yield ()
  }


  "hasNo" - types { implicit conn =>
    for {
      _ <- Entity.s.iSeq.i.insert(
        ("a", List(1, 2), 1),
        ("b", List(3), 2),
      ).transact

      _ <- Entity.s.iSeq.hasNo(Entity.i).query.get.map(_ ==> List(("b", List(3), 2)))
      _ <- Entity.s.iSeq.hasNo(Entity.i_).query.get.map(_ ==> List(("b", List(3))))
      _ <- Entity.s.iSeq_.hasNo(Entity.i).query.get.map(_ ==> List(("b", 2)))
      _ <- Entity.s.iSeq_.hasNo(Entity.i_).query.get.map(_ ==> List("b"))

      // Filter compare attribute itself
      _ <- Entity.s.iSeq.hasNo(Entity.i(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq.hasNo(Entity.i.not(1)).query.get.map(_ ==> List(("b", List(3), 2)))
      _ <- Entity.s.iSeq.hasNo(Entity.i.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq.hasNo(Entity.i.<=(2)).query.get.map(_ ==> List(("b", List(3), 2)))
      _ <- Entity.s.iSeq.hasNo(Entity.i.>(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq.hasNo(Entity.i.>=(2)).query.get.map(_ ==> List(("b", List(3), 2)))

      _ <- Entity.s.iSeq.hasNo(Entity.i_(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq.hasNo(Entity.i_.not(1)).query.get.map(_ ==> List(("b", List(3))))
      _ <- Entity.s.iSeq.hasNo(Entity.i_.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq.hasNo(Entity.i_.<=(2)).query.get.map(_ ==> List(("b", List(3))))
      _ <- Entity.s.iSeq.hasNo(Entity.i_.>(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq.hasNo(Entity.i_.>=(2)).query.get.map(_ ==> List(("b", List(3))))

      _ <- Entity.s.iSeq_.hasNo(Entity.i(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq_.hasNo(Entity.i.not(1)).query.get.map(_ ==> List(("b", 2)))
      _ <- Entity.s.iSeq_.hasNo(Entity.i.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq_.hasNo(Entity.i.<=(2)).query.get.map(_ ==> List(("b", 2)))
      _ <- Entity.s.iSeq_.hasNo(Entity.i.>(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq_.hasNo(Entity.i.>=(2)).query.get.map(_ ==> List(("b", 2)))

      _ <- Entity.s.iSeq_.hasNo(Entity.i_(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq_.hasNo(Entity.i_.not(1)).query.get.map(_ ==> List("b"))
      _ <- Entity.s.iSeq_.hasNo(Entity.i_.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq_.hasNo(Entity.i_.<=(2)).query.get.map(_ ==> List("b"))
      _ <- Entity.s.iSeq_.hasNo(Entity.i_.>(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSeq_.hasNo(Entity.i_.>=(2)).query.get.map(_ ==> List("b"))
    } yield ()
  }
}
