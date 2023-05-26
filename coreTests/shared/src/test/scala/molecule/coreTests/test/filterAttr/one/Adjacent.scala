package molecule.coreTests.test.filterAttr.one

import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._

trait Adjacent extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  val a = ("a", 1, 2)
  val b = ("b", 3, 3)
  val c = ("c", 5, 4)

  override lazy val tests = Tests {

    "equal (apply)" - types { implicit conn =>
      for {
        _ <- Ns.s.i.int.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- Ns.s.i(Ns.int).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i(Ns.int_).query.get.map(_ ==> List(("b", 3))) // Ns.i
        _ <- Ns.s.i_(Ns.int).query.get.map(_ ==> List(("b", 3))) // Ns.int
        _ <- Ns.s.i_(Ns.int_).query.get.map(_ ==> List("b"))

        // Filter compare attribute itself
        _ <- Ns.s.i(Ns.int(3)).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i(Ns.int.not(3)).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ns.int.>(3)).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ns.int.>=(3)).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i(Ns.int.<(3)).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ns.int.<=(3)).query.get.map(_ ==> List(("b", 3, 3)))

        _ <- Ns.s.i(Ns.int_(3)).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i(Ns.int_.not(3)).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ns.int_.>(3)).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ns.int_.>=(3)).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i(Ns.int_.<(3)).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ns.int_.<=(3)).query.get.map(_ ==> List(("b", 3)))

        _ <- Ns.s.i_(Ns.int(3)).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i_(Ns.int.not(3)).query.get.map(_ ==> List())
        _ <- Ns.s.i_(Ns.int.>(3)).query.get.map(_ ==> List())
        _ <- Ns.s.i_(Ns.int.>=(3)).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i_(Ns.int.<(3)).query.get.map(_ ==> List())
        _ <- Ns.s.i_(Ns.int.<=(3)).query.get.map(_ ==> List(("b", 3)))

        _ <- Ns.s.i_(Ns.int_(3)).query.get.map(_ ==> List("b"))
        _ <- Ns.s.i_(Ns.int_.not(3)).query.get.map(_ ==> List())
        _ <- Ns.s.i_(Ns.int_.>(3)).query.get.map(_ ==> List())
        _ <- Ns.s.i_(Ns.int_.>=(3)).query.get.map(_ ==> List("b"))
        _ <- Ns.s.i_(Ns.int_.<(3)).query.get.map(_ ==> List())
        _ <- Ns.s.i_(Ns.int_.<=(3)).query.get.map(_ ==> List("b"))
      } yield ()
    }

    "not equal" - types { implicit conn =>
      for {
        _ <- Ns.s.i.int.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- Ns.s.a1.i.not(Ns.int).query.get.map(_ ==> List(("a", 1, 2), ("c", 5, 4)))
        _ <- Ns.s.a1.i.not(Ns.int_).query.get.map(_ ==> List(("a", 1), ("c", 5)))
        _ <- Ns.s.a1.i_.not(Ns.int).query.get.map(_ ==> List(("a", 2), ("c", 4)))
        _ <- Ns.s.a1.i_.not(Ns.int_).query.get.map(_ ==> List("a", "c"))

        // Filter compare attribute itself
        _ <- Ns.s.i.not(Ns.int(2)).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- Ns.s.i.not(Ns.int.not(2)).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.not(Ns.int.>(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i.not(Ns.int.>=(4)).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.not(Ns.int.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i.not(Ns.int.<=(2)).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- Ns.s.i.not(Ns.int_(2)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.i.not(Ns.int_.not(2)).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i.not(Ns.int_.>(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i.not(Ns.int_.>=(4)).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i.not(Ns.int_.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i.not(Ns.int_.<=(2)).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.i_.not(Ns.int(2)).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.i_.not(Ns.int.not(2)).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.not(Ns.int.>(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.not(Ns.int.>=(4)).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.not(Ns.int.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.not(Ns.int.<=(2)).query.get.map(_ ==> List(("a", 2)))

        _ <- Ns.s.i_.not(Ns.int_(2)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.i_.not(Ns.int_.not(2)).query.get.map(_ ==> List("c"))
        _ <- Ns.s.i_.not(Ns.int_.>(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.not(Ns.int_.>=(4)).query.get.map(_ ==> List("c"))
        _ <- Ns.s.i_.not(Ns.int_.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.not(Ns.int_.<=(2)).query.get.map(_ ==> List("a"))
      } yield ()
    }


    "<" - types { implicit conn =>
      for {
        _ <- Ns.s.i.int.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- Ns.s.i.<(Ns.int).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- Ns.s.i.<(Ns.int_).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.i_.<(Ns.int).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.i_.<(Ns.int_).query.get.map(_ ==> List("a"))

        // Filter compare attribute itself
        _ <- Ns.s.i.<(Ns.int(2)).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- Ns.s.i.<(Ns.int.not(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i.<(Ns.int.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i.<(Ns.int.>=(2)).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- Ns.s.i.<(Ns.int.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i.<(Ns.int.<=(2)).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- Ns.s.i.<(Ns.int_(2)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.i.<(Ns.int_.not(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i.<(Ns.int_.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i.<(Ns.int_.>=(2)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.i.<(Ns.int_.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i.<(Ns.int_.<=(2)).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.i_.<(Ns.int(2)).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.i_.<(Ns.int.not(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<(Ns.int.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<(Ns.int.>=(2)).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.i_.<(Ns.int.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<(Ns.int.<=(2)).query.get.map(_ ==> List(("a", 2)))

        _ <- Ns.s.i_.<(Ns.int_(2)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.i_.<(Ns.int_.not(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<(Ns.int_.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<(Ns.int_.>=(2)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.i_.<(Ns.int_.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<(Ns.int_.<=(2)).query.get.map(_ ==> List("a"))
      } yield ()
    }


    "<=" - types { implicit conn =>
      for {
        _ <- Ns.s.i.int.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- Ns.s.a1.i.<=(Ns.int).query.get.map(_ ==> List(("a", 1, 2), ("b", 3, 3)))
        _ <- Ns.s.a1.i.<=(Ns.int_).query.get.map(_ ==> List(("a", 1), ("b", 3)))
        _ <- Ns.s.a1.i_.<=(Ns.int).query.get.map(_ ==> List(("a", 2), ("b", 3)))
        _ <- Ns.s.a1.i_.<=(Ns.int_).query.get.map(_ ==> List("a", "b"))

        // Filter compare attribute itself
        _ <- Ns.s.i.<=(Ns.int(2)).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- Ns.s.i.<=(Ns.int.not(2)).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i.<=(Ns.int.>(2)).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.i.<=(Ns.int.>=(2)).query.get.map(_ ==> List(("a", 1, 2), ("b", 3, 3)))
        _ <- Ns.s.i.<=(Ns.int.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i.<=(Ns.int.<=(2)).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- Ns.s.i.<=(Ns.int_(2)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.i.<=(Ns.int_.not(2)).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i.<=(Ns.int_.>(2)).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i.<=(Ns.int_.>=(2)).query.get.map(_ ==> List(("a", 1), ("b", 3)))
        _ <- Ns.s.i.<=(Ns.int_.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i.<=(Ns.int_.<=(2)).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.i_.<=(Ns.int(2)).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.i_.<=(Ns.int.not(2)).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i_.<=(Ns.int.>(2)).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.i_.<=(Ns.int.>=(2)).query.get.map(_ ==> List(("a", 2), ("b", 3)))
        _ <- Ns.s.i_.<=(Ns.int.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<=(Ns.int.<=(2)).query.get.map(_ ==> List(("a", 2)))

        _ <- Ns.s.i_.<=(Ns.int_(2)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.i_.<=(Ns.int_.not(2)).query.get.map(_ ==> List("b"))
        _ <- Ns.s.i_.<=(Ns.int_.>(2)).query.get.map(_ ==> List("b"))
        _ <- Ns.s.i_.<=(Ns.int_.>=(2)).query.get.map(_ ==> List("a", "b"))
        _ <- Ns.s.i_.<=(Ns.int_.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.<=(Ns.int_.<=(2)).query.get.map(_ ==> List("a"))
      } yield ()
    }


    ">" - types { implicit conn =>
      for {
        _ <- Ns.s.i.int.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- Ns.s.i.>(Ns.int).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.>(Ns.int_).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i_.>(Ns.int).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.>(Ns.int_).query.get.map(_ ==> List("c"))

        // Filter compare attribute itself
        _ <- Ns.s.i.>(Ns.int(4)).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.>(Ns.int.not(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(Ns.int.>(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(Ns.int.>=(4)).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.i.>(Ns.int.<(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(Ns.int.<=(4)).query.get.map(_ ==> List(("c", 5, 4)))

        _ <- Ns.s.i.>(Ns.int_(4)).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i.>(Ns.int_.not(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(Ns.int_.>(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(Ns.int_.>=(4)).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.i.>(Ns.int_.<(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(Ns.int_.<=(4)).query.get.map(_ ==> List(("c", 5)))

        _ <- Ns.s.i_.>(Ns.int(4)).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.>(Ns.int.not(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(Ns.int.>(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(Ns.int.>=(4)).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.i_.>(Ns.int.<(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(Ns.int.<=(4)).query.get.map(_ ==> List(("c", 4)))

        _ <- Ns.s.i_.>(Ns.int_(4)).query.get.map(_ ==> List("c"))
        _ <- Ns.s.i_.>(Ns.int_.not(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(Ns.int_.>(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(Ns.int_.>=(4)).query.get.map(_ ==> List("c"))
        _ <- Ns.s.i_.>(Ns.int_.<(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i_.>(Ns.int_.<=(4)).query.get.map(_ ==> List("c"))
      } yield ()
    }


    ">=" - types { implicit conn =>
      for {
        _ <- Ns.s.i.int.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- Ns.s.a1.i.>=(Ns.int).query.get.map(_ ==> List(("b", 3, 3), ("c", 5, 4)))
        _ <- Ns.s.a1.i.>=(Ns.int_).query.get.map(_ ==> List(("b", 3), ("c", 5)))
        _ <- Ns.s.a1.i_.>=(Ns.int).query.get.map(_ ==> List(("b", 3), ("c", 4)))
        _ <- Ns.s.a1.i_.>=(Ns.int_).query.get.map(_ ==> List("b", "c"))

        // Filter compare attribute itself
        _ <- Ns.s.a1.i.>=(Ns.int(3)).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- Ns.s.a1.i.>=(Ns.int.not(3)).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.a1.i.>=(Ns.int.>(3)).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- Ns.s.a1.i.>=(Ns.int.>=(3)).query.get.map(_ ==> List(("b", 3, 3), ("c", 5, 4)))
        _ <- Ns.s.a1.i.>=(Ns.int.<(3)).query.get.map(_ ==> List())
        _ <- Ns.s.a1.i.>=(Ns.int.<=(3)).query.get.map(_ ==> List(("b", 3, 3)))

        _ <- Ns.s.a1.i.>=(Ns.int_(3)).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.a1.i.>=(Ns.int_.not(3)).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.a1.i.>=(Ns.int_.>(3)).query.get.map(_ ==> List(("c", 5)))
        _ <- Ns.s.a1.i.>=(Ns.int_.>=(3)).query.get.map(_ ==> List(("b", 3), ("c", 5)))
        _ <- Ns.s.a1.i.>=(Ns.int_.<(3)).query.get.map(_ ==> List())
        _ <- Ns.s.a1.i.>=(Ns.int_.<=(3)).query.get.map(_ ==> List(("b", 3)))

        _ <- Ns.s.a1.i_.>=(Ns.int(3)).query.get.map(_ ==> List(("b", 3)))
        _ <- Ns.s.a1.i_.>=(Ns.int.not(3)).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.a1.i_.>=(Ns.int.>(3)).query.get.map(_ ==> List(("c", 4)))
        _ <- Ns.s.a1.i_.>=(Ns.int.>=(3)).query.get.map(_ ==> List(("b", 3), ("c", 4)))
        _ <- Ns.s.a1.i_.>=(Ns.int.<(3)).query.get.map(_ ==> List())
        _ <- Ns.s.a1.i_.>=(Ns.int.<=(3)).query.get.map(_ ==> List(("b", 3)))

        _ <- Ns.s.a1.i_.>=(Ns.int_(3)).query.get.map(_ ==> List("b"))
        _ <- Ns.s.a1.i_.>=(Ns.int_.not(3)).query.get.map(_ ==> List("c"))
        _ <- Ns.s.a1.i_.>=(Ns.int_.>(3)).query.get.map(_ ==> List("c"))
        _ <- Ns.s.a1.i_.>=(Ns.int_.>=(3)).query.get.map(_ ==> List("b", "c"))
        _ <- Ns.s.a1.i_.>=(Ns.int_.<(3)).query.get.map(_ ==> List())
        _ <- Ns.s.a1.i_.>=(Ns.int_.<=(3)).query.get.map(_ ==> List("b"))
      } yield ()
    }
  }
}
