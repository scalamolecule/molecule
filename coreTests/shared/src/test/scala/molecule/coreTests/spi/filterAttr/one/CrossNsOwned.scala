package molecule.coreTests.spi.filterAttr.one

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs._

import molecule.coreTests.setup.CoreTestSuite
import utest._

trait CrossNsOwned extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "equal (apply)" - refs { implicit conn =>
      for {
        _ <- A.s.i.OwnB.i.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- A.s.i(B.i_).OwnB.i.query.get.map(_ ==> List(("b", 3, 3)))
        _ <- A.s.i(B.i_).OwnB.i_.query.get.map(_ ==> List(("b", 3))) // A.i
        _ <- A.s.i_(B.i_).OwnB.i.query.get.map(_ ==> List(("b", 3))) // A.i
        _ <- A.s.i_(B.i_).OwnB.i_.query.get.map(_ ==> List("b"))

        // Filter compare attribute itself
        _ <- A.s.i(B.i_).OwnB.i(3).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- A.s.i(B.i_).OwnB.i.not(3).query.get.map(_ ==> List())
        _ <- A.s.i(B.i_).OwnB.i.>(3).query.get.map(_ ==> List())
        _ <- A.s.i(B.i_).OwnB.i.>=(3).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- A.s.i(B.i_).OwnB.i.<(3).query.get.map(_ ==> List())
        _ <- A.s.i(B.i_).OwnB.i.<=(3).query.get.map(_ ==> List(("b", 3, 3)))

        _ <- A.s.i(B.i_).OwnB.i_(3).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.i(B.i_).OwnB.i_.not(3).query.get.map(_ ==> List())
        _ <- A.s.i(B.i_).OwnB.i_.>(3).query.get.map(_ ==> List())
        _ <- A.s.i(B.i_).OwnB.i_.>=(3).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.i(B.i_).OwnB.i_.<(3).query.get.map(_ ==> List())
        _ <- A.s.i(B.i_).OwnB.i_.<=(3).query.get.map(_ ==> List(("b", 3)))

        _ <- A.s.i_(B.i_).OwnB.i(3).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.i_(B.i_).OwnB.i.not(3).query.get.map(_ ==> List())
        _ <- A.s.i_(B.i_).OwnB.i.>(3).query.get.map(_ ==> List())
        _ <- A.s.i_(B.i_).OwnB.i.>=(3).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.i_(B.i_).OwnB.i.<(3).query.get.map(_ ==> List())
        _ <- A.s.i_(B.i_).OwnB.i.<=(3).query.get.map(_ ==> List(("b", 3)))

        _ <- A.s.i_(B.i_).OwnB.i_(3).query.get.map(_ ==> List("b"))
        _ <- A.s.i_(B.i_).OwnB.i_.not(3).query.get.map(_ ==> List())
        _ <- A.s.i_(B.i_).OwnB.i_.>(3).query.get.map(_ ==> List())
        _ <- A.s.i_(B.i_).OwnB.i_.>=(3).query.get.map(_ ==> List("b"))
        _ <- A.s.i_(B.i_).OwnB.i_.<(3).query.get.map(_ ==> List())
        _ <- A.s.i_(B.i_).OwnB.i_.<=(3).query.get.map(_ ==> List("b"))


        // Pointing backwards

        _ <- A.s.i.OwnB.i(A.i_).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- A.s.i.OwnB.i_(A.i_).query.get.map(_ ==> List(("b", 3))) // A.i
        _ <- A.s.i_.OwnB.i(A.i_).query.get.map(_ ==> List(("b", 3))) // A.i
        _ <- A.s.i_.OwnB.i_(A.i_).query.get.map(_ ==> List("b"))

        // Filter compare attribute itself
        _ <- A.s.i(3).OwnB.i(A.i_).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- A.s.i.not(3).OwnB.i(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.>(3).OwnB.i(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.>=(3).OwnB.i(A.i_).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- A.s.i.<(3).OwnB.i(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.<=(3).OwnB.i(A.i_).query.get.map(_ ==> List(("b", 3, 3)))

        _ <- A.s.i(3).OwnB.i_(A.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.i.not(3).OwnB.i_(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.>(3).OwnB.i_(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.>=(3).OwnB.i_(A.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.i.<(3).OwnB.i_(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.<=(3).OwnB.i_(A.i_).query.get.map(_ ==> List(("b", 3)))

        _ <- A.s.i_(3).OwnB.i(A.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.i_.not(3).OwnB.i(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.>(3).OwnB.i(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.>=(3).OwnB.i(A.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.i_.<(3).OwnB.i(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.<=(3).OwnB.i(A.i_).query.get.map(_ ==> List(("b", 3)))

        _ <- A.s.i_(3).OwnB.i_(A.i_).query.get.map(_ ==> List("b"))
        _ <- A.s.i_.not(3).OwnB.i_(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.>(3).OwnB.i_(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.>=(3).OwnB.i_(A.i_).query.get.map(_ ==> List("b"))
        _ <- A.s.i_.<(3).OwnB.i_(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.<=(3).OwnB.i_(A.i_).query.get.map(_ ==> List("b"))
      } yield ()
    }


    "not equal" - refs { implicit conn =>
      for {
        _ <- A.s.i.OwnB.i.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- A.s.a1.i.not(B.i_).OwnB.i.query.get.map(_ ==> List(("a", 1, 2), ("c", 5, 4)))
        _ <- A.s.a1.i.not(B.i_).OwnB.i_.query.get.map(_ ==> List(("a", 1), ("c", 5)))
        _ <- A.s.a1.i_.not(B.i_).OwnB.i.query.get.map(_ ==> List(("a", 2), ("c", 4)))
        _ <- A.s.a1.i_.not(B.i_).OwnB.i_.query.get.map(_ ==> List("a", "c"))

        // Filter compare attribute itself
        _ <- A.s.i.not(B.i_).OwnB.i(2).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- A.s.i.not(B.i_).OwnB.i.not(2).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- A.s.i.not(B.i_).OwnB.i.>(4).query.get.map(_ ==> List())
        _ <- A.s.i.not(B.i_).OwnB.i.>=(4).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- A.s.i.not(B.i_).OwnB.i.<(2).query.get.map(_ ==> List())
        _ <- A.s.i.not(B.i_).OwnB.i.<=(2).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- A.s.i.not(B.i_).OwnB.i_(2).query.get.map(_ ==> List(("a", 1)))
        _ <- A.s.i.not(B.i_).OwnB.i_.not(2).query.get.map(_ ==> List(("c", 5)))
        _ <- A.s.i.not(B.i_).OwnB.i_.>(4).query.get.map(_ ==> List())
        _ <- A.s.i.not(B.i_).OwnB.i_.>=(4).query.get.map(_ ==> List(("c", 5)))
        _ <- A.s.i.not(B.i_).OwnB.i_.<(2).query.get.map(_ ==> List())
        _ <- A.s.i.not(B.i_).OwnB.i_.<=(2).query.get.map(_ ==> List(("a", 1)))

        _ <- A.s.i_.not(B.i_).OwnB.i(2).query.get.map(_ ==> List(("a", 2)))
        _ <- A.s.i_.not(B.i_).OwnB.i.not(2).query.get.map(_ ==> List(("c", 4)))
        _ <- A.s.i_.not(B.i_).OwnB.i.>(4).query.get.map(_ ==> List())
        _ <- A.s.i_.not(B.i_).OwnB.i.>=(4).query.get.map(_ ==> List(("c", 4)))
        _ <- A.s.i_.not(B.i_).OwnB.i.<(2).query.get.map(_ ==> List())
        _ <- A.s.i_.not(B.i_).OwnB.i.<=(2).query.get.map(_ ==> List(("a", 2)))

        _ <- A.s.i_.not(B.i_).OwnB.i_(2).query.get.map(_ ==> List("a"))
        _ <- A.s.i_.not(B.i_).OwnB.i_.not(2).query.get.map(_ ==> List("c"))
        _ <- A.s.i_.not(B.i_).OwnB.i_.>(4).query.get.map(_ ==> List())
        _ <- A.s.i_.not(B.i_).OwnB.i_.>=(4).query.get.map(_ ==> List("c"))
        _ <- A.s.i_.not(B.i_).OwnB.i_.<(2).query.get.map(_ ==> List())
        _ <- A.s.i_.not(B.i_).OwnB.i_.<=(2).query.get.map(_ ==> List("a"))


        // Pointing backwards

        _ <- A.s.a1.i.OwnB.i.not(A.i_).query.get.map(_ ==> List(("a", 1, 2), ("c", 5, 4)))
        _ <- A.s.a1.i.OwnB.i_.not(A.i_).query.get.map(_ ==> List(("a", 1), ("c", 5)))
        _ <- A.s.a1.i_.OwnB.i.not(A.i_).query.get.map(_ ==> List(("a", 2), ("c", 4)))
        _ <- A.s.a1.i_.OwnB.i_.not(A.i_).query.get.map(_ ==> List("a", "c"))

        // Filter compare attribute itself
        _ <- A.s.i(1).OwnB.i.not(A.i_).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- A.s.i.not(1).OwnB.i.not(A.i_).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- A.s.i.>(5).OwnB.i.not(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.>=(5).OwnB.i.not(A.i_).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- A.s.i.<(1).OwnB.i.not(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.<=(1).OwnB.i.not(A.i_).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- A.s.i(1).OwnB.i_.not(A.i_).query.get.map(_ ==> List(("a", 1)))
        _ <- A.s.i.not(1).OwnB.i_.not(A.i_).query.get.map(_ ==> List(("c", 5)))
        _ <- A.s.i.>(5).OwnB.i_.not(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.>=(5).OwnB.i_.not(A.i_).query.get.map(_ ==> List(("c", 5)))
        _ <- A.s.i.<(1).OwnB.i_.not(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.<=(1).OwnB.i_.not(A.i_).query.get.map(_ ==> List(("a", 1)))

        _ <- A.s.i_(1).OwnB.i.not(A.i_).query.get.map(_ ==> List(("a", 2)))
        _ <- A.s.i_.not(1).OwnB.i.not(A.i_).query.get.map(_ ==> List(("c", 4)))
        _ <- A.s.i_.>(5).OwnB.i.not(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.>=(5).OwnB.i.not(A.i_).query.get.map(_ ==> List(("c", 4)))
        _ <- A.s.i_.<(1).OwnB.i.not(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.<=(1).OwnB.i.not(A.i_).query.get.map(_ ==> List(("a", 2)))

        _ <- A.s.i_(1).OwnB.i_.not(A.i_).query.get.map(_ ==> List("a"))
        _ <- A.s.i_.not(1).OwnB.i_.not(A.i_).query.get.map(_ ==> List("c"))
        _ <- A.s.i_.>(5).OwnB.i_.not(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.>=(5).OwnB.i_.not(A.i_).query.get.map(_ ==> List("c"))
        _ <- A.s.i_.<(1).OwnB.i_.not(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.<=(1).OwnB.i_.not(A.i_).query.get.map(_ ==> List("a"))
      } yield ()
    }


    "<" - refs { implicit conn =>
      for {
        _ <- A.s.i.OwnB.i.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- A.s.i.<(B.i_).OwnB.i.query.get.map(_ ==> List(("a", 1, 2)))
        _ <- A.s.i.<(B.i_).OwnB.i_.query.get.map(_ ==> List(("a", 1)))
        _ <- A.s.i_.<(B.i_).OwnB.i.query.get.map(_ ==> List(("a", 2)))
        _ <- A.s.i_.<(B.i_).OwnB.i_.query.get.map(_ ==> List("a"))

        // Filter compare attribute itself
        _ <- A.s.i.<(B.i_).OwnB.i(2).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- A.s.i.<(B.i_).OwnB.i.not(2).query.get.map(_ ==> List())
        _ <- A.s.i.<(B.i_).OwnB.i.>(2).query.get.map(_ ==> List())
        _ <- A.s.i.<(B.i_).OwnB.i.>=(2).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- A.s.i.<(B.i_).OwnB.i.<(2).query.get.map(_ ==> List())
        _ <- A.s.i.<(B.i_).OwnB.i.<=(2).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- A.s.i.<(B.i_).OwnB.i_(2).query.get.map(_ ==> List(("a", 1)))
        _ <- A.s.i.<(B.i_).OwnB.i_.not(2).query.get.map(_ ==> List())
        _ <- A.s.i.<(B.i_).OwnB.i_.>(2).query.get.map(_ ==> List())
        _ <- A.s.i.<(B.i_).OwnB.i_.>=(2).query.get.map(_ ==> List(("a", 1)))
        _ <- A.s.i.<(B.i_).OwnB.i_.<(2).query.get.map(_ ==> List())
        _ <- A.s.i.<(B.i_).OwnB.i_.<=(2).query.get.map(_ ==> List(("a", 1)))

        _ <- A.s.i_.<(B.i_).OwnB.i(2).query.get.map(_ ==> List(("a", 2)))
        _ <- A.s.i_.<(B.i_).OwnB.i.not(2).query.get.map(_ ==> List())
        _ <- A.s.i_.<(B.i_).OwnB.i.>(2).query.get.map(_ ==> List())
        _ <- A.s.i_.<(B.i_).OwnB.i.>=(2).query.get.map(_ ==> List(("a", 2)))
        _ <- A.s.i_.<(B.i_).OwnB.i.<(2).query.get.map(_ ==> List())
        _ <- A.s.i_.<(B.i_).OwnB.i.<=(2).query.get.map(_ ==> List(("a", 2)))

        _ <- A.s.i_.<(B.i_).OwnB.i_(2).query.get.map(_ ==> List("a"))
        _ <- A.s.i_.<(B.i_).OwnB.i_.not(2).query.get.map(_ ==> List())
        _ <- A.s.i_.<(B.i_).OwnB.i_.>(2).query.get.map(_ ==> List())
        _ <- A.s.i_.<(B.i_).OwnB.i_.>=(2).query.get.map(_ ==> List("a"))
        _ <- A.s.i_.<(B.i_).OwnB.i_.<(2).query.get.map(_ ==> List())
        _ <- A.s.i_.<(B.i_).OwnB.i_.<=(2).query.get.map(_ ==> List("a"))


        // Pointing backwards

        _ <- A.s.i.OwnB.i.<(A.i_).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- A.s.i.OwnB.i_.<(A.i_).query.get.map(_ ==> List(("c", 5)))
        _ <- A.s.i_.OwnB.i.<(A.i_).query.get.map(_ ==> List(("c", 4)))
        _ <- A.s.i_.OwnB.i_.<(A.i_).query.get.map(_ ==> List("c"))

        // Filter compare attribute itself
        _ <- A.s.i(5).OwnB.i.<(A.i_).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- A.s.i.not(5).OwnB.i.<(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.>(5).OwnB.i.<(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.>=(5).OwnB.i.<(A.i_).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- A.s.i.<(5).OwnB.i.<(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.<=(5).OwnB.i.<(A.i_).query.get.map(_ ==> List(("c", 5, 4)))

        _ <- A.s.i(5).OwnB.i_.<(A.i_).query.get.map(_ ==> List(("c", 5)))
        _ <- A.s.i.not(5).OwnB.i_.<(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.>(5).OwnB.i_.<(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.>=(5).OwnB.i_.<(A.i_).query.get.map(_ ==> List(("c", 5)))
        _ <- A.s.i.<(5).OwnB.i_.<(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.<=(5).OwnB.i_.<(A.i_).query.get.map(_ ==> List(("c", 5)))

        _ <- A.s.i_(5).OwnB.i.<(A.i_).query.get.map(_ ==> List(("c", 4)))
        _ <- A.s.i_.not(5).OwnB.i.<(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.>(5).OwnB.i.<(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.>=(5).OwnB.i.<(A.i_).query.get.map(_ ==> List(("c", 4)))
        _ <- A.s.i_.<(5).OwnB.i.<(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.<=(5).OwnB.i.<(A.i_).query.get.map(_ ==> List(("c", 4)))

        _ <- A.s.i_(5).OwnB.i_.<(A.i_).query.get.map(_ ==> List("c"))
        _ <- A.s.i_.not(5).OwnB.i_.<(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.>(5).OwnB.i_.<(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.>=(5).OwnB.i_.<(A.i_).query.get.map(_ ==> List("c"))
        _ <- A.s.i_.<(5).OwnB.i_.<(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.<=(5).OwnB.i_.<(A.i_).query.get.map(_ ==> List("c"))
      } yield ()
    }


    "<=" - refs { implicit conn =>
      for {
        _ <- A.s.i.OwnB.i.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- A.s.a1.i.<=(B.i_).OwnB.i.query.get.map(_ ==> List(("a", 1, 2), ("b", 3, 3)))
        _ <- A.s.a1.i.<=(B.i_).OwnB.i_.query.get.map(_ ==> List(("a", 1), ("b", 3)))
        _ <- A.s.a1.i_.<=(B.i_).OwnB.i.query.get.map(_ ==> List(("a", 2), ("b", 3)))
        _ <- A.s.a1.i_.<=(B.i_).OwnB.i_.query.get.map(_ ==> List("a", "b"))

        // Filter compare attribute itself
        _ <- A.s.a1.i.<=(B.i_).OwnB.i(2).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- A.s.a1.i.<=(B.i_).OwnB.i.not(2).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- A.s.a1.i.<=(B.i_).OwnB.i.>(2).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- A.s.a1.i.<=(B.i_).OwnB.i.>=(2).query.get.map(_ ==> List(("a", 1, 2), ("b", 3, 3)))
        _ <- A.s.a1.i.<=(B.i_).OwnB.i.<(2).query.get.map(_ ==> List())
        _ <- A.s.a1.i.<=(B.i_).OwnB.i.<=(2).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- A.s.a1.i.<=(B.i_).OwnB.i_(2).query.get.map(_ ==> List(("a", 1)))
        _ <- A.s.a1.i.<=(B.i_).OwnB.i_.not(2).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.a1.i.<=(B.i_).OwnB.i_.>(2).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.a1.i.<=(B.i_).OwnB.i_.>=(2).query.get.map(_ ==> List(("a", 1), ("b", 3)))
        _ <- A.s.a1.i.<=(B.i_).OwnB.i_.<(2).query.get.map(_ ==> List())
        _ <- A.s.a1.i.<=(B.i_).OwnB.i_.<=(2).query.get.map(_ ==> List(("a", 1)))

        _ <- A.s.a1.i_.<=(B.i_).OwnB.i(2).query.get.map(_ ==> List(("a", 2)))
        _ <- A.s.a1.i_.<=(B.i_).OwnB.i.not(2).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.a1.i_.<=(B.i_).OwnB.i.>(2).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.a1.i_.<=(B.i_).OwnB.i.>=(2).query.get.map(_ ==> List(("a", 2), ("b", 3)))
        _ <- A.s.a1.i_.<=(B.i_).OwnB.i.<(2).query.get.map(_ ==> List())
        _ <- A.s.a1.i_.<=(B.i_).OwnB.i.<=(2).query.get.map(_ ==> List(("a", 2)))

        _ <- A.s.a1.i_.<=(B.i_).OwnB.i_(2).query.get.map(_ ==> List("a"))
        _ <- A.s.a1.i_.<=(B.i_).OwnB.i_.not(2).query.get.map(_ ==> List("b"))
        _ <- A.s.a1.i_.<=(B.i_).OwnB.i_.>(2).query.get.map(_ ==> List("b"))
        _ <- A.s.a1.i_.<=(B.i_).OwnB.i_.>=(2).query.get.map(_ ==> List("a", "b"))
        _ <- A.s.a1.i_.<=(B.i_).OwnB.i_.<(2).query.get.map(_ ==> List())
        _ <- A.s.a1.i_.<=(B.i_).OwnB.i_.<=(2).query.get.map(_ ==> List("a"))


        // Pointing backwards

        _ <- A.s.a1.i.OwnB.i.<=(A.i_).query.get.map(_ ==> List(("b", 3, 3), ("c", 5, 4)))
        _ <- A.s.a1.i.OwnB.i_.<=(A.i_).query.get.map(_ ==> List(("b", 3), ("c", 5)))
        _ <- A.s.a1.i_.OwnB.i.<=(A.i_).query.get.map(_ ==> List(("b", 3), ("c", 4)))
        _ <- A.s.a1.i_.OwnB.i_.<=(A.i_).query.get.map(_ ==> List("b", "c"))

        // Filter compare attribute itself
        _ <- A.s.a1.i(5).OwnB.i.<=(A.i_).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- A.s.a1.i.not(5).OwnB.i.<=(A.i_).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- A.s.a1.i.>(5).OwnB.i.<=(A.i_).query.get.map(_ ==> List())
        _ <- A.s.a1.i.>=(5).OwnB.i.<=(A.i_).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- A.s.a1.i.<(5).OwnB.i.<=(A.i_).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- A.s.a1.i.<=(5).OwnB.i.<=(A.i_).query.get.map(_ ==> List(("b", 3, 3), ("c", 5, 4)))

        _ <- A.s.a1.i(5).OwnB.i_.<=(A.i_).query.get.map(_ ==> List(("c", 5)))
        _ <- A.s.a1.i.not(5).OwnB.i_.<=(A.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.a1.i.>(5).OwnB.i_.<=(A.i_).query.get.map(_ ==> List())
        _ <- A.s.a1.i.>=(5).OwnB.i_.<=(A.i_).query.get.map(_ ==> List(("c", 5)))
        _ <- A.s.a1.i.<(5).OwnB.i_.<=(A.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.a1.i.<=(5).OwnB.i_.<=(A.i_).query.get.map(_ ==> List(("b", 3), ("c", 5)))

        _ <- A.s.a1.i_(5).OwnB.i.<=(A.i_).query.get.map(_ ==> List(("c", 4)))
        _ <- A.s.a1.i_.not(5).OwnB.i.<=(A.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.a1.i_.>(5).OwnB.i.<=(A.i_).query.get.map(_ ==> List())
        _ <- A.s.a1.i_.>=(5).OwnB.i.<=(A.i_).query.get.map(_ ==> List(("c", 4)))
        _ <- A.s.a1.i_.<(5).OwnB.i.<=(A.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.a1.i_.<=(5).OwnB.i.<=(A.i_).query.get.map(_ ==> List(("b", 3), ("c", 4)))

        _ <- A.s.a1.i_(5).OwnB.i_.<=(A.i_).query.get.map(_ ==> List("c"))
        _ <- A.s.a1.i_.not(5).OwnB.i_.<=(A.i_).query.get.map(_ ==> List("b"))
        _ <- A.s.a1.i_.>(5).OwnB.i_.<=(A.i_).query.get.map(_ ==> List())
        _ <- A.s.a1.i_.>=(5).OwnB.i_.<=(A.i_).query.get.map(_ ==> List("c"))
        _ <- A.s.a1.i_.<(5).OwnB.i_.<=(A.i_).query.get.map(_ ==> List("b"))
        _ <- A.s.a1.i_.<=(5).OwnB.i_.<=(A.i_).query.get.map(_ ==> List("b", "c"))
      } yield ()
    }


    ">" - refs { implicit conn =>
      for {
        _ <- A.s.i.OwnB.i.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- A.s.i.>(B.i_).OwnB.i.query.get.map(_ ==> List(("c", 5, 4)))
        _ <- A.s.i.>(B.i_).OwnB.i_.query.get.map(_ ==> List(("c", 5)))
        _ <- A.s.i_.>(B.i_).OwnB.i.query.get.map(_ ==> List(("c", 4)))
        _ <- A.s.i_.>(B.i_).OwnB.i_.query.get.map(_ ==> List("c"))

        // Filter compare attribute itself
        _ <- A.s.i.>(B.i_).OwnB.i(4).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- A.s.i.>(B.i_).OwnB.i.not(4).query.get.map(_ ==> List())
        _ <- A.s.i.>(B.i_).OwnB.i.>(4).query.get.map(_ ==> List())
        _ <- A.s.i.>(B.i_).OwnB.i.>=(4).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- A.s.i.>(B.i_).OwnB.i.<(4).query.get.map(_ ==> List())
        _ <- A.s.i.>(B.i_).OwnB.i.<=(4).query.get.map(_ ==> List(("c", 5, 4)))

        _ <- A.s.i.>(B.i_).OwnB.i_(4).query.get.map(_ ==> List(("c", 5)))
        _ <- A.s.i.>(B.i_).OwnB.i_.not(4).query.get.map(_ ==> List())
        _ <- A.s.i.>(B.i_).OwnB.i_.>(4).query.get.map(_ ==> List())
        _ <- A.s.i.>(B.i_).OwnB.i_.>=(4).query.get.map(_ ==> List(("c", 5)))
        _ <- A.s.i.>(B.i_).OwnB.i_.<(4).query.get.map(_ ==> List())
        _ <- A.s.i.>(B.i_).OwnB.i_.<=(4).query.get.map(_ ==> List(("c", 5)))

        _ <- A.s.i_.>(B.i_).OwnB.i(4).query.get.map(_ ==> List(("c", 4)))
        _ <- A.s.i_.>(B.i_).OwnB.i.not(4).query.get.map(_ ==> List())
        _ <- A.s.i_.>(B.i_).OwnB.i.>(4).query.get.map(_ ==> List())
        _ <- A.s.i_.>(B.i_).OwnB.i.>=(4).query.get.map(_ ==> List(("c", 4)))
        _ <- A.s.i_.>(B.i_).OwnB.i.<(4).query.get.map(_ ==> List())
        _ <- A.s.i_.>(B.i_).OwnB.i.<=(4).query.get.map(_ ==> List(("c", 4)))

        _ <- A.s.i_.>(B.i_).OwnB.i_(4).query.get.map(_ ==> List("c"))
        _ <- A.s.i_.>(B.i_).OwnB.i_.not(4).query.get.map(_ ==> List())
        _ <- A.s.i_.>(B.i_).OwnB.i_.>(4).query.get.map(_ ==> List())
        _ <- A.s.i_.>(B.i_).OwnB.i_.>=(4).query.get.map(_ ==> List("c"))
        _ <- A.s.i_.>(B.i_).OwnB.i_.<(4).query.get.map(_ ==> List())
        _ <- A.s.i_.>(B.i_).OwnB.i_.<=(4).query.get.map(_ ==> List("c"))


        // Pointing backwards

        _ <- A.s.i.OwnB.i.>(A.i_).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- A.s.i.OwnB.i_.>(A.i_).query.get.map(_ ==> List(("a", 1)))
        _ <- A.s.i_.OwnB.i.>(A.i_).query.get.map(_ ==> List(("a", 2)))
        _ <- A.s.i_.OwnB.i_.>(A.i_).query.get.map(_ ==> List("a"))

        // Filter compare attribute itself
        _ <- A.s.i(1).OwnB.i.>(A.i_).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- A.s.i.not(1).OwnB.i.>(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.>(1).OwnB.i.>(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.>=(1).OwnB.i.>(A.i_).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- A.s.i.<(1).OwnB.i.>(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.<=(1).OwnB.i.>(A.i_).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- A.s.i(1).OwnB.i_.>(A.i_).query.get.map(_ ==> List(("a", 1)))
        _ <- A.s.i.not(1).OwnB.i_.>(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.>(1).OwnB.i_.>(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.>=(1).OwnB.i_.>(A.i_).query.get.map(_ ==> List(("a", 1)))
        _ <- A.s.i.<(1).OwnB.i_.>(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i.<=(1).OwnB.i_.>(A.i_).query.get.map(_ ==> List(("a", 1)))

        _ <- A.s.i_(1).OwnB.i.>(A.i_).query.get.map(_ ==> List(("a", 2)))
        _ <- A.s.i_.not(1).OwnB.i.>(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.>(1).OwnB.i.>(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.>=(1).OwnB.i.>(A.i_).query.get.map(_ ==> List(("a", 2)))
        _ <- A.s.i_.<(1).OwnB.i.>(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.<=(1).OwnB.i.>(A.i_).query.get.map(_ ==> List(("a", 2)))

        _ <- A.s.i_(1).OwnB.i_.>(A.i_).query.get.map(_ ==> List("a"))
        _ <- A.s.i_.not(1).OwnB.i_.>(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.>(1).OwnB.i_.>(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.>=(1).OwnB.i_.>(A.i_).query.get.map(_ ==> List("a"))
        _ <- A.s.i_.<(1).OwnB.i_.>(A.i_).query.get.map(_ ==> List())
        _ <- A.s.i_.<=(1).OwnB.i_.>(A.i_).query.get.map(_ ==> List("a"))
      } yield ()
    }


    ">=" - refs { implicit conn =>
      for {
        _ <- A.s.i.OwnB.i.insert(
          ("a", 1, 2),
          ("b", 3, 3),
          ("c", 5, 4),
        ).transact

        _ <- A.s.a1.i.>=(B.i_).OwnB.i.query.get.map(_ ==> List(("b", 3, 3), ("c", 5, 4)))
        _ <- A.s.a1.i.>=(B.i_).OwnB.i_.query.get.map(_ ==> List(("b", 3), ("c", 5)))
        _ <- A.s.a1.i_.>=(B.i_).OwnB.i.query.get.map(_ ==> List(("b", 3), ("c", 4)))
        _ <- A.s.a1.i_.>=(B.i_).OwnB.i_.query.get.map(_ ==> List("b", "c"))

        // Filter compare attribute itself
        _ <- A.s.a1.i.>=(B.i_).OwnB.i(3).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- A.s.a1.i.>=(B.i_).OwnB.i.not(3).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- A.s.a1.i.>=(B.i_).OwnB.i.>(3).query.get.map(_ ==> List(("c", 5, 4)))
        _ <- A.s.a1.i.>=(B.i_).OwnB.i.>=(3).query.get.map(_ ==> List(("b", 3, 3), ("c", 5, 4)))
        _ <- A.s.a1.i.>=(B.i_).OwnB.i.<(3).query.get.map(_ ==> List())
        _ <- A.s.a1.i.>=(B.i_).OwnB.i.<=(3).query.get.map(_ ==> List(("b", 3, 3)))

        _ <- A.s.a1.i.>=(B.i_).OwnB.i_(3).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.a1.i.>=(B.i_).OwnB.i_.not(3).query.get.map(_ ==> List(("c", 5)))
        _ <- A.s.a1.i.>=(B.i_).OwnB.i_.>(3).query.get.map(_ ==> List(("c", 5)))
        _ <- A.s.a1.i.>=(B.i_).OwnB.i_.>=(3).query.get.map(_ ==> List(("b", 3), ("c", 5)))
        _ <- A.s.a1.i.>=(B.i_).OwnB.i_.<(3).query.get.map(_ ==> List())
        _ <- A.s.a1.i.>=(B.i_).OwnB.i_.<=(3).query.get.map(_ ==> List(("b", 3)))

        _ <- A.s.a1.i_.>=(B.i_).OwnB.i(3).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.a1.i_.>=(B.i_).OwnB.i.not(3).query.get.map(_ ==> List(("c", 4)))
        _ <- A.s.a1.i_.>=(B.i_).OwnB.i.>(3).query.get.map(_ ==> List(("c", 4)))
        _ <- A.s.a1.i_.>=(B.i_).OwnB.i.>=(3).query.get.map(_ ==> List(("b", 3), ("c", 4)))
        _ <- A.s.a1.i_.>=(B.i_).OwnB.i.<(3).query.get.map(_ ==> List())
        _ <- A.s.a1.i_.>=(B.i_).OwnB.i.<=(3).query.get.map(_ ==> List(("b", 3)))

        _ <- A.s.a1.i_.>=(B.i_).OwnB.i_(3).query.get.map(_ ==> List("b"))
        _ <- A.s.a1.i_.>=(B.i_).OwnB.i_.not(3).query.get.map(_ ==> List("c"))
        _ <- A.s.a1.i_.>=(B.i_).OwnB.i_.>(3).query.get.map(_ ==> List("c"))
        _ <- A.s.a1.i_.>=(B.i_).OwnB.i_.>=(3).query.get.map(_ ==> List("b", "c"))
        _ <- A.s.a1.i_.>=(B.i_).OwnB.i_.<(3).query.get.map(_ ==> List())
        _ <- A.s.a1.i_.>=(B.i_).OwnB.i_.<=(3).query.get.map(_ ==> List("b"))


        // Pointing backwards

        _ <- A.s.a1.i.OwnB.i.>=(A.i_).query.get.map(_ ==> List(("a", 1, 2), ("b", 3, 3)))
        _ <- A.s.a1.i.OwnB.i_.>=(A.i_).query.get.map(_ ==> List(("a", 1), ("b", 3)))
        _ <- A.s.a1.i_.OwnB.i.>=(A.i_).query.get.map(_ ==> List(("a", 2), ("b", 3)))
        _ <- A.s.a1.i_.OwnB.i_.>=(A.i_).query.get.map(_ ==> List("a", "b"))

        // Filter compare attribute itself
        _ <- A.s.a1.i(1).OwnB.i.>=(A.i_).query.get.map(_ ==> List(("a", 1, 2)))
        _ <- A.s.a1.i.not(1).OwnB.i.>=(A.i_).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- A.s.a1.i.>(1).OwnB.i.>=(A.i_).query.get.map(_ ==> List(("b", 3, 3)))
        _ <- A.s.a1.i.>=(1).OwnB.i.>=(A.i_).query.get.map(_ ==> List(("a", 1, 2), ("b", 3, 3)))
        _ <- A.s.a1.i.<(1).OwnB.i.>=(A.i_).query.get.map(_ ==> List())
        _ <- A.s.a1.i.<=(1).OwnB.i.>=(A.i_).query.get.map(_ ==> List(("a", 1, 2)))

        _ <- A.s.a1.i(1).OwnB.i_.>=(A.i_).query.get.map(_ ==> List(("a", 1)))
        _ <- A.s.a1.i.not(1).OwnB.i_.>=(A.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.a1.i.>(1).OwnB.i_.>=(A.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.a1.i.>=(1).OwnB.i_.>=(A.i_).query.get.map(_ ==> List(("a", 1), ("b", 3)))
        _ <- A.s.a1.i.<(1).OwnB.i_.>=(A.i_).query.get.map(_ ==> List())
        _ <- A.s.a1.i.<=(1).OwnB.i_.>=(A.i_).query.get.map(_ ==> List(("a", 1)))

        _ <- A.s.a1.i_(1).OwnB.i.>=(A.i_).query.get.map(_ ==> List(("a", 2)))
        _ <- A.s.a1.i_.not(1).OwnB.i.>=(A.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.a1.i_.>(1).OwnB.i.>=(A.i_).query.get.map(_ ==> List(("b", 3)))
        _ <- A.s.a1.i_.>=(1).OwnB.i.>=(A.i_).query.get.map(_ ==> List(("a", 2), ("b", 3)))
        _ <- A.s.a1.i_.<(1).OwnB.i.>=(A.i_).query.get.map(_ ==> List())
        _ <- A.s.a1.i_.<=(1).OwnB.i.>=(A.i_).query.get.map(_ ==> List(("a", 2)))

        _ <- A.s.a1.i_(1).OwnB.i_.>=(A.i_).query.get.map(_ ==> List("a"))
        _ <- A.s.a1.i_.not(1).OwnB.i_.>=(A.i_).query.get.map(_ ==> List("b"))
        _ <- A.s.a1.i_.>(1).OwnB.i_.>=(A.i_).query.get.map(_ ==> List("b"))
        _ <- A.s.a1.i_.>=(1).OwnB.i_.>=(A.i_).query.get.map(_ ==> List("a", "b"))
        _ <- A.s.a1.i_.<(1).OwnB.i_.>=(A.i_).query.get.map(_ ==> List())
        _ <- A.s.a1.i_.<=(1).OwnB.i_.>=(A.i_).query.get.map(_ ==> List("a"))
      } yield ()
    }
  }
}