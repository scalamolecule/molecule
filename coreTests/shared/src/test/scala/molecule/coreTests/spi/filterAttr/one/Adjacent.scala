package molecule.coreTests.spi.filterAttr.one

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.*

case class Adjacent(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = ("a", 1, 2)
  val b = ("b", 3, 3)
  val c = ("c", 5, 4)

  import api.*
  import suite.*

  "equal (apply)" - types { implicit conn =>
    for {
      _ <- Entity.s.i.int.insert(
        ("a", 1, 2),
        ("b", 3, 3),
        ("c", 5, 4),
      ).transact

      _ <- Entity.s.i(Entity.int).query.get.map(_ ==> List(("b", 3, 3)))
      _ <- Entity.s.i(Entity.int_).query.get.map(_ ==> List(("b", 3))) // Entity.i
      _ <- Entity.s.i_(Entity.int).query.get.map(_ ==> List(("b", 3))) // Entity.int
      _ <- Entity.s.i_(Entity.int_).query.get.map(_ ==> List("b"))

      // Filter compare attribute itself
      _ <- Entity.s.i(Entity.int(3)).query.get.map(_ ==> List(("b", 3, 3)))
      // Same result with
      _ <- Entity.s.i.int(3).query.get.map(_ ==> List(("b", 3, 3)))

      _ <- Entity.s.i(Entity.int.not(3)).query.get.map(_ ==> List())
      _ <- Entity.s.i(Entity.int.>(3)).query.get.map(_ ==> List())
      _ <- Entity.s.i(Entity.int.>=(3)).query.get.map(_ ==> List(("b", 3, 3)))
      _ <- Entity.s.i(Entity.int.<(3)).query.get.map(_ ==> List())
      _ <- Entity.s.i(Entity.int.<=(3)).query.get.map(_ ==> List(("b", 3, 3)))

      _ <- Entity.s.i(Entity.int_(3)).query.get.map(_ ==> List(("b", 3)))
      _ <- Entity.s.i(Entity.int_.not(3)).query.get.map(_ ==> List())
      _ <- Entity.s.i(Entity.int_.>(3)).query.get.map(_ ==> List())
      _ <- Entity.s.i(Entity.int_.>=(3)).query.get.map(_ ==> List(("b", 3)))
      _ <- Entity.s.i(Entity.int_.<(3)).query.get.map(_ ==> List())
      _ <- Entity.s.i(Entity.int_.<=(3)).query.get.map(_ ==> List(("b", 3)))

      _ <- Entity.s.i_(Entity.int(3)).query.get.map(_ ==> List(("b", 3)))
      _ <- Entity.s.i_(Entity.int.not(3)).query.get.map(_ ==> List())
      _ <- Entity.s.i_(Entity.int.>(3)).query.get.map(_ ==> List())
      _ <- Entity.s.i_(Entity.int.>=(3)).query.get.map(_ ==> List(("b", 3)))
      _ <- Entity.s.i_(Entity.int.<(3)).query.get.map(_ ==> List())
      _ <- Entity.s.i_(Entity.int.<=(3)).query.get.map(_ ==> List(("b", 3)))

      _ <- Entity.s.i_(Entity.int_(3)).query.get.map(_ ==> List("b"))
      _ <- Entity.s.i_(Entity.int_.not(3)).query.get.map(_ ==> List())
      _ <- Entity.s.i_(Entity.int_.>(3)).query.get.map(_ ==> List())
      _ <- Entity.s.i_(Entity.int_.>=(3)).query.get.map(_ ==> List("b"))
      _ <- Entity.s.i_(Entity.int_.<(3)).query.get.map(_ ==> List())
      _ <- Entity.s.i_(Entity.int_.<=(3)).query.get.map(_ ==> List("b"))
    } yield ()
  }

  "not equal" - types { implicit conn =>
    for {
      _ <- Entity.s.i.int.insert(
        ("a", 1, 2),
        ("b", 3, 3),
        ("c", 5, 4),
      ).transact

      _ <- Entity.s.a1.i.not(Entity.int).query.get.map(_ ==> List(("a", 1, 2), ("c", 5, 4)))
      _ <- Entity.s.a1.i.not(Entity.int_).query.get.map(_ ==> List(("a", 1), ("c", 5)))
      _ <- Entity.s.a1.i_.not(Entity.int).query.get.map(_ ==> List(("a", 2), ("c", 4)))
      _ <- Entity.s.a1.i_.not(Entity.int_).query.get.map(_ ==> List("a", "c"))

      // Filter compare attribute itself
      _ <- Entity.s.i.not(Entity.int(2)).query.get.map(_ ==> List(("a", 1, 2)))
      _ <- Entity.s.i.not(Entity.int.not(2)).query.get.map(_ ==> List(("c", 5, 4)))
      _ <- Entity.s.i.not(Entity.int.>(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i.not(Entity.int.>=(4)).query.get.map(_ ==> List(("c", 5, 4)))
      _ <- Entity.s.i.not(Entity.int.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i.not(Entity.int.<=(2)).query.get.map(_ ==> List(("a", 1, 2)))

      _ <- Entity.s.i.not(Entity.int_(2)).query.get.map(_ ==> List(("a", 1)))
      _ <- Entity.s.i.not(Entity.int_.not(2)).query.get.map(_ ==> List(("c", 5)))
      _ <- Entity.s.i.not(Entity.int_.>(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i.not(Entity.int_.>=(4)).query.get.map(_ ==> List(("c", 5)))
      _ <- Entity.s.i.not(Entity.int_.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i.not(Entity.int_.<=(2)).query.get.map(_ ==> List(("a", 1)))

      _ <- Entity.s.i_.not(Entity.int(2)).query.get.map(_ ==> List(("a", 2)))
      _ <- Entity.s.i_.not(Entity.int.not(2)).query.get.map(_ ==> List(("c", 4)))
      _ <- Entity.s.i_.not(Entity.int.>(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.not(Entity.int.>=(4)).query.get.map(_ ==> List(("c", 4)))
      _ <- Entity.s.i_.not(Entity.int.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.not(Entity.int.<=(2)).query.get.map(_ ==> List(("a", 2)))

      _ <- Entity.s.i_.not(Entity.int_(2)).query.get.map(_ ==> List("a"))
      _ <- Entity.s.i_.not(Entity.int_.not(2)).query.get.map(_ ==> List("c"))
      _ <- Entity.s.i_.not(Entity.int_.>(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.not(Entity.int_.>=(4)).query.get.map(_ ==> List("c"))
      _ <- Entity.s.i_.not(Entity.int_.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.not(Entity.int_.<=(2)).query.get.map(_ ==> List("a"))
    } yield ()
  }


  "<" - types { implicit conn =>
    for {
      _ <- Entity.s.i.int.insert(
        ("a", 1, 2),
        ("b", 3, 3),
        ("c", 5, 4),
      ).transact

      _ <- Entity.s.i.<(Entity.int).query.get.map(_ ==> List(("a", 1, 2)))
      _ <- Entity.s.i.<(Entity.int_).query.get.map(_ ==> List(("a", 1)))
      _ <- Entity.s.i_.<(Entity.int).query.get.map(_ ==> List(("a", 2)))
      _ <- Entity.s.i_.<(Entity.int_).query.get.map(_ ==> List("a"))

      // Filter compare attribute itself
      _ <- Entity.s.i.<(Entity.int(2)).query.get.map(_ ==> List(("a", 1, 2)))
      _ <- Entity.s.i.<(Entity.int.not(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i.<(Entity.int.>(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i.<(Entity.int.>=(2)).query.get.map(_ ==> List(("a", 1, 2)))
      _ <- Entity.s.i.<(Entity.int.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i.<(Entity.int.<=(2)).query.get.map(_ ==> List(("a", 1, 2)))

      _ <- Entity.s.i.<(Entity.int_(2)).query.get.map(_ ==> List(("a", 1)))
      _ <- Entity.s.i.<(Entity.int_.not(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i.<(Entity.int_.>(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i.<(Entity.int_.>=(2)).query.get.map(_ ==> List(("a", 1)))
      _ <- Entity.s.i.<(Entity.int_.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i.<(Entity.int_.<=(2)).query.get.map(_ ==> List(("a", 1)))

      _ <- Entity.s.i_.<(Entity.int(2)).query.get.map(_ ==> List(("a", 2)))
      _ <- Entity.s.i_.<(Entity.int.not(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.<(Entity.int.>(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.<(Entity.int.>=(2)).query.get.map(_ ==> List(("a", 2)))
      _ <- Entity.s.i_.<(Entity.int.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.<(Entity.int.<=(2)).query.get.map(_ ==> List(("a", 2)))

      _ <- Entity.s.i_.<(Entity.int_(2)).query.get.map(_ ==> List("a"))
      _ <- Entity.s.i_.<(Entity.int_.not(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.<(Entity.int_.>(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.<(Entity.int_.>=(2)).query.get.map(_ ==> List("a"))
      _ <- Entity.s.i_.<(Entity.int_.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.<(Entity.int_.<=(2)).query.get.map(_ ==> List("a"))
    } yield ()
  }


  "<=" - types { implicit conn =>
    for {
      _ <- Entity.s.i.int.insert(
        ("a", 1, 2),
        ("b", 3, 3),
        ("c", 5, 4),
      ).transact

      _ <- Entity.s.a1.i.<=(Entity.int).query.get.map(_ ==> List(("a", 1, 2), ("b", 3, 3)))
      _ <- Entity.s.a1.i.<=(Entity.int_).query.get.map(_ ==> List(("a", 1), ("b", 3)))
      _ <- Entity.s.a1.i_.<=(Entity.int).query.get.map(_ ==> List(("a", 2), ("b", 3)))
      _ <- Entity.s.a1.i_.<=(Entity.int_).query.get.map(_ ==> List("a", "b"))

      // Filter compare attribute itself
      _ <- Entity.s.i.<=(Entity.int(2)).query.get.map(_ ==> List(("a", 1, 2)))
      _ <- Entity.s.i.<=(Entity.int.not(2)).query.get.map(_ ==> List(("b", 3, 3)))
      _ <- Entity.s.i.<=(Entity.int.>(2)).query.get.map(_ ==> List(("b", 3, 3)))
      _ <- Entity.s.a1.i.<=(Entity.int.>=(2)).query.get.map(_ ==> List(("a", 1, 2), ("b", 3, 3)))
      _ <- Entity.s.i.<=(Entity.int.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i.<=(Entity.int.<=(2)).query.get.map(_ ==> List(("a", 1, 2)))

      _ <- Entity.s.i.<=(Entity.int_(2)).query.get.map(_ ==> List(("a", 1)))
      _ <- Entity.s.i.<=(Entity.int_.not(2)).query.get.map(_ ==> List(("b", 3)))
      _ <- Entity.s.i.<=(Entity.int_.>(2)).query.get.map(_ ==> List(("b", 3)))
      _ <- Entity.s.a1.i.<=(Entity.int_.>=(2)).query.get.map(_ ==> List(("a", 1), ("b", 3)))
      _ <- Entity.s.i.<=(Entity.int_.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i.<=(Entity.int_.<=(2)).query.get.map(_ ==> List(("a", 1)))

      _ <- Entity.s.i_.<=(Entity.int(2)).query.get.map(_ ==> List(("a", 2)))
      _ <- Entity.s.i_.<=(Entity.int.not(2)).query.get.map(_ ==> List(("b", 3)))
      _ <- Entity.s.i_.<=(Entity.int.>(2)).query.get.map(_ ==> List(("b", 3)))
      _ <- Entity.s.a1.i_.<=(Entity.int.>=(2)).query.get.map(_ ==> List(("a", 2), ("b", 3)))
      _ <- Entity.s.i_.<=(Entity.int.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.<=(Entity.int.<=(2)).query.get.map(_ ==> List(("a", 2)))

      _ <- Entity.s.i_.<=(Entity.int_(2)).query.get.map(_ ==> List("a"))
      _ <- Entity.s.i_.<=(Entity.int_.not(2)).query.get.map(_ ==> List("b"))
      _ <- Entity.s.i_.<=(Entity.int_.>(2)).query.get.map(_ ==> List("b"))
      _ <- Entity.s.i_.<=(Entity.int_.>=(2)).query.get.map(_ ==> List("a", "b"))
      _ <- Entity.s.i_.<=(Entity.int_.<(2)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.<=(Entity.int_.<=(2)).query.get.map(_ ==> List("a"))
    } yield ()
  }


  ">" - types { implicit conn =>
    for {
      _ <- Entity.s.i.int.insert(
        ("a", 1, 2),
        ("b", 3, 3),
        ("c", 5, 4),
      ).transact

      _ <- Entity.s.i.>(Entity.int).query.get.map(_ ==> List(("c", 5, 4)))
      _ <- Entity.s.i.>(Entity.int_).query.get.map(_ ==> List(("c", 5)))
      _ <- Entity.s.i_.>(Entity.int).query.get.map(_ ==> List(("c", 4)))
      _ <- Entity.s.i_.>(Entity.int_).query.get.map(_ ==> List("c"))

      // Filter compare attribute itself
      _ <- Entity.s.i.>(Entity.int(4)).query.get.map(_ ==> List(("c", 5, 4)))
      _ <- Entity.s.i.>(Entity.int.not(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i.>(Entity.int.>(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i.>(Entity.int.>=(4)).query.get.map(_ ==> List(("c", 5, 4)))
      _ <- Entity.s.i.>(Entity.int.<(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i.>(Entity.int.<=(4)).query.get.map(_ ==> List(("c", 5, 4)))

      _ <- Entity.s.i.>(Entity.int_(4)).query.get.map(_ ==> List(("c", 5)))
      _ <- Entity.s.i.>(Entity.int_.not(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i.>(Entity.int_.>(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i.>(Entity.int_.>=(4)).query.get.map(_ ==> List(("c", 5)))
      _ <- Entity.s.i.>(Entity.int_.<(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i.>(Entity.int_.<=(4)).query.get.map(_ ==> List(("c", 5)))

      _ <- Entity.s.i_.>(Entity.int(4)).query.get.map(_ ==> List(("c", 4)))
      _ <- Entity.s.i_.>(Entity.int.not(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.>(Entity.int.>(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.>(Entity.int.>=(4)).query.get.map(_ ==> List(("c", 4)))
      _ <- Entity.s.i_.>(Entity.int.<(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.>(Entity.int.<=(4)).query.get.map(_ ==> List(("c", 4)))

      _ <- Entity.s.i_.>(Entity.int_(4)).query.get.map(_ ==> List("c"))
      _ <- Entity.s.i_.>(Entity.int_.not(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.>(Entity.int_.>(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.>(Entity.int_.>=(4)).query.get.map(_ ==> List("c"))
      _ <- Entity.s.i_.>(Entity.int_.<(4)).query.get.map(_ ==> List())
      _ <- Entity.s.i_.>(Entity.int_.<=(4)).query.get.map(_ ==> List("c"))
    } yield ()
  }


  ">=" - types { implicit conn =>
    for {
      _ <- Entity.s.i.int.insert(
        ("a", 1, 2),
        ("b", 3, 3),
        ("c", 5, 4),
      ).transact

      _ <- Entity.s.a1.i.>=(Entity.int).query.get.map(_ ==> List(("b", 3, 3), ("c", 5, 4)))
      _ <- Entity.s.a1.i.>=(Entity.int_).query.get.map(_ ==> List(("b", 3), ("c", 5)))
      _ <- Entity.s.a1.i_.>=(Entity.int).query.get.map(_ ==> List(("b", 3), ("c", 4)))
      _ <- Entity.s.a1.i_.>=(Entity.int_).query.get.map(_ ==> List("b", "c"))

      // Filter compare attribute itself
      _ <- Entity.s.a1.i.>=(Entity.int(3)).query.get.map(_ ==> List(("b", 3, 3)))
      _ <- Entity.s.a1.i.>=(Entity.int.not(3)).query.get.map(_ ==> List(("c", 5, 4)))
      _ <- Entity.s.a1.i.>=(Entity.int.>(3)).query.get.map(_ ==> List(("c", 5, 4)))
      _ <- Entity.s.a1.i.>=(Entity.int.>=(3)).query.get.map(_ ==> List(("b", 3, 3), ("c", 5, 4)))
      _ <- Entity.s.a1.i.>=(Entity.int.<(3)).query.get.map(_ ==> List())
      _ <- Entity.s.a1.i.>=(Entity.int.<=(3)).query.get.map(_ ==> List(("b", 3, 3)))

      _ <- Entity.s.a1.i.>=(Entity.int_(3)).query.get.map(_ ==> List(("b", 3)))
      _ <- Entity.s.a1.i.>=(Entity.int_.not(3)).query.get.map(_ ==> List(("c", 5)))
      _ <- Entity.s.a1.i.>=(Entity.int_.>(3)).query.get.map(_ ==> List(("c", 5)))
      _ <- Entity.s.a1.i.>=(Entity.int_.>=(3)).query.get.map(_ ==> List(("b", 3), ("c", 5)))
      _ <- Entity.s.a1.i.>=(Entity.int_.<(3)).query.get.map(_ ==> List())
      _ <- Entity.s.a1.i.>=(Entity.int_.<=(3)).query.get.map(_ ==> List(("b", 3)))

      _ <- Entity.s.a1.i_.>=(Entity.int(3)).query.get.map(_ ==> List(("b", 3)))
      _ <- Entity.s.a1.i_.>=(Entity.int.not(3)).query.get.map(_ ==> List(("c", 4)))
      _ <- Entity.s.a1.i_.>=(Entity.int.>(3)).query.get.map(_ ==> List(("c", 4)))
      _ <- Entity.s.a1.i_.>=(Entity.int.>=(3)).query.get.map(_ ==> List(("b", 3), ("c", 4)))
      _ <- Entity.s.a1.i_.>=(Entity.int.<(3)).query.get.map(_ ==> List())
      _ <- Entity.s.a1.i_.>=(Entity.int.<=(3)).query.get.map(_ ==> List(("b", 3)))

      _ <- Entity.s.a1.i_.>=(Entity.int_(3)).query.get.map(_ ==> List("b"))
      _ <- Entity.s.a1.i_.>=(Entity.int_.not(3)).query.get.map(_ ==> List("c"))
      _ <- Entity.s.a1.i_.>=(Entity.int_.>(3)).query.get.map(_ ==> List("c"))
      _ <- Entity.s.a1.i_.>=(Entity.int_.>=(3)).query.get.map(_ ==> List("b", "c"))
      _ <- Entity.s.a1.i_.>=(Entity.int_.<(3)).query.get.map(_ ==> List())
      _ <- Entity.s.a1.i_.>=(Entity.int_.<=(3)).query.get.map(_ ==> List("b"))
    } yield ()
  }
}
