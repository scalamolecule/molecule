package molecule.db.compliance.test.filterAttr.seq

import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Adjacent(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

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
