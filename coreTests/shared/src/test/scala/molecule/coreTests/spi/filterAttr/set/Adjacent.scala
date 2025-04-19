package molecule.coreTests.spi.filterAttr.set

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.*

case class Adjacent(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Set(0, 1, 2), Set(1, 2, 3))
  val b = (2, Set(2, 3), Set(2, 3))
  val c = (3, Set(4), Set(3))

  import api.*
  import suite.*

  "has" - types { implicit conn =>
    for {
      _ <- Entity.s.iSet.i.insert(
        ("a", Set(1, 2), 1),
        ("b", Set(3), 2),
      ).transact

      _ <- Entity.s.iSet.has(Entity.i).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
      _ <- Entity.s.iSet.has(Entity.i_).query.get.map(_ ==> List(("a", Set(1, 2))))
      _ <- Entity.s.iSet_.has(Entity.i).query.get.map(_ ==> List(("a", 1)))
      _ <- Entity.s.iSet_.has(Entity.i_).query.get.map(_ ==> List("a"))

      // Filter compare attribute itself
      _ <- Entity.s.iSet.has(Entity.i(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
      _ <- Entity.s.iSet.has(Entity.i.not(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet.has(Entity.i.<(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet.has(Entity.i.<=(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
      _ <- Entity.s.iSet.has(Entity.i.>(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet.has(Entity.i.>=(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))

      _ <- Entity.s.iSet.has(Entity.i_(1)).query.get.map(_ ==> List(("a", Set(1, 2))))
      _ <- Entity.s.iSet.has(Entity.i_.not(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet.has(Entity.i_.<(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet.has(Entity.i_.<=(1)).query.get.map(_ ==> List(("a", Set(1, 2))))
      _ <- Entity.s.iSet.has(Entity.i_.>(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet.has(Entity.i_.>=(1)).query.get.map(_ ==> List(("a", Set(1, 2))))

      _ <- Entity.s.iSet_.has(Entity.i(1)).query.get.map(_ ==> List(("a", 1)))
      _ <- Entity.s.iSet_.has(Entity.i.not(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet_.has(Entity.i.<(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet_.has(Entity.i.<=(1)).query.get.map(_ ==> List(("a", 1)))
      _ <- Entity.s.iSet_.has(Entity.i.>(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet_.has(Entity.i.>=(1)).query.get.map(_ ==> List(("a", 1)))

      _ <- Entity.s.iSet_.has(Entity.i_(1)).query.get.map(_ ==> List("a"))
      _ <- Entity.s.iSet_.has(Entity.i_.not(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet_.has(Entity.i_.<(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet_.has(Entity.i_.<=(1)).query.get.map(_ ==> List("a"))
      _ <- Entity.s.iSet_.has(Entity.i_.>(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet_.has(Entity.i_.>=(1)).query.get.map(_ ==> List("a"))
    } yield ()
  }


  "hasNo" - types { implicit conn =>
    for {
      _ <- Entity.s.iSet.i.insert(
        ("a", Set(1, 2), 1),
        ("b", Set(3), 2),
      ).transact

      _ <- Entity.s.iSet.hasNo(Entity.i).query.get.map(_ ==> List(("b", Set(3), 2)))
      _ <- Entity.s.iSet.hasNo(Entity.i_).query.get.map(_ ==> List(("b", Set(3))))
      _ <- Entity.s.iSet_.hasNo(Entity.i).query.get.map(_ ==> List(("b", 2)))
      _ <- Entity.s.iSet_.hasNo(Entity.i_).query.get.map(_ ==> List("b"))

      // Filter compare attribute itself
      _ <- Entity.s.iSet.hasNo(Entity.i(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet.hasNo(Entity.i.not(1)).query.get.map(_ ==> List(("b", Set(3), 2)))
      _ <- Entity.s.iSet.hasNo(Entity.i.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet.hasNo(Entity.i.<=(2)).query.get.map(_ ==> List(("b", Set(3), 2)))
      _ <- Entity.s.iSet.hasNo(Entity.i.>(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet.hasNo(Entity.i.>=(2)).query.get.map(_ ==> List(("b", Set(3), 2)))

      _ <- Entity.s.iSet.hasNo(Entity.i_(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet.hasNo(Entity.i_.not(1)).query.get.map(_ ==> List(("b", Set(3))))
      _ <- Entity.s.iSet.hasNo(Entity.i_.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet.hasNo(Entity.i_.<=(2)).query.get.map(_ ==> List(("b", Set(3))))
      _ <- Entity.s.iSet.hasNo(Entity.i_.>(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet.hasNo(Entity.i_.>=(2)).query.get.map(_ ==> List(("b", Set(3))))

      _ <- Entity.s.iSet_.hasNo(Entity.i(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet_.hasNo(Entity.i.not(1)).query.get.map(_ ==> List(("b", 2)))
      _ <- Entity.s.iSet_.hasNo(Entity.i.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet_.hasNo(Entity.i.<=(2)).query.get.map(_ ==> List(("b", 2)))
      _ <- Entity.s.iSet_.hasNo(Entity.i.>(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet_.hasNo(Entity.i.>=(2)).query.get.map(_ ==> List(("b", 2)))

      _ <- Entity.s.iSet_.hasNo(Entity.i_(1)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet_.hasNo(Entity.i_.not(1)).query.get.map(_ ==> List("b"))
      _ <- Entity.s.iSet_.hasNo(Entity.i_.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet_.hasNo(Entity.i_.<=(2)).query.get.map(_ ==> List("b"))
      _ <- Entity.s.iSet_.hasNo(Entity.i_.>(2)).query.get.map(_ ==> List())
      _ <- Entity.s.iSet_.hasNo(Entity.i_.>=(2)).query.get.map(_ ==> List("b"))
    } yield ()
  }
}
