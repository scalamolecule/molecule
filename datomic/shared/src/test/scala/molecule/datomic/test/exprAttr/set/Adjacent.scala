package molecule.datomic.test.exprAttr.set

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.{Ns, _}
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._

object Adjacent extends DatomicTestSuite {

  val a = (1, Set(0, 1, 2), Set(1, 2, 3))
  val b = (2, Set(2, 3), Set(2, 3))
  val c = (3, Set(4), Set(3))

  override lazy val tests = Tests {

    "equal (apply) - Sets that match other Sets" - types { implicit conn =>
      for {
        _ <- Ns.i.ii.ints.insert(a, b, c).transact

        _ <- Ns.i.ii(Ns.ints).query.get.map(_ ==> List(b))
        _ <- Ns.i.ii(Ns.ints_).query.get.map(_ ==> List((2, Set(2, 3)))) // Ns.ii
        _ <- Ns.i.ii_(Ns.ints).query.get.map(_ ==> List((2, Set(2, 3)))) // Ref.ints
        _ <- Ns.i.ii_(Ns.ints_).query.get.map(_ ==> List(2))
      } yield ()
    }


    "not equal - Sets that don't match other Sets" - types { implicit conn =>
      for {
        _ <- Ns.i.ii.ints.insert(a, b, c).transact

        _ <- Ns.i.ii.not(Ns.ints).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.ii.not(Ns.ints_).query.get.map(_ ==> List((1, Set(0, 1, 2)), (3, Set(4))))
        _ <- Ns.i.ii_.not(Ns.ints).query.get.map(_ ==> List((1, Set(1, 2, 3)), (3, Set(3))))
        _ <- Ns.i.ii_.not(Ns.ints_).query.get.map(_ ==> List(1, 3))
      } yield ()
    }


    "has - Sets that contain all values of other Set" - types { implicit conn =>
      for {
        _ <- Ns.i.ii.ints.insert(a, b, c).transact

        _ <- Ns.i.ii.has(Ns.ints).query.get.map(_ ==> List(b))
        _ <- Ns.i.ii.has(Ns.ints_).query.get.map(_ ==> List((2, Set(2, 3)))) // Ns.ii
        _ <- Ns.i.ii_.has(Ns.ints).query.get.map(_ ==> List((2, Set(2, 3)))) // Ref.ints
        _ <- Ns.i.ii_.has(Ns.ints_).query.get.map(_ ==> List(2))
      } yield ()
    }


    "hasNo - Sets that don't contain any values of other Set" - types { implicit conn =>
      for {
        _ <- Ns.i.ii.ints.insert(a, b, c).transact

        _ <- Ns.i.ii.hasNo(Ns.ints).query.get.map(_ ==> List(c))
        _ <- Ns.i.ii.hasNo(Ns.ints_).query.get.map(_ ==> List((3, Set(4))))
        _ <- Ns.i.ii_.hasNo(Ns.ints).query.get.map(_ ==> List((3, Set(3))))
        _ <- Ns.i.ii_.hasNo(Ns.ints_).query.get.map(_ ==> List(3))
      } yield ()
    }


    "has - Sets that contain value of other attribute" - types { implicit conn =>
      for {
        _ <- Ns.s.ii.i.insert(
          ("a", Set(1, 2), 1),
          ("b", Set(3), 2),
        ).transact

        _ <- Ns.s.ii.has(Ns.i).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.ii.has(Ns.i_).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii_.has(Ns.i).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.ii_.has(Ns.i_).query.get.map(_ ==> List("a"))

        // With expression on expression attribute itself
        _ <- Ns.s.ii.has(Ns.i(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.ii.has(Ns.i.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.has(Ns.i.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.has(Ns.i.<=(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.ii.has(Ns.i.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.has(Ns.i.>=(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))

        _ <- Ns.s.ii.has(Ns.i_(1)).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii.has(Ns.i_.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.has(Ns.i_.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.has(Ns.i_.<=(1)).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii.has(Ns.i_.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.has(Ns.i_.>=(1)).query.get.map(_ ==> List(("a", Set(1, 2))))

        _ <- Ns.s.ii_.has(Ns.i(1)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.ii_.has(Ns.i.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.has(Ns.i.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.has(Ns.i.<=(1)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.ii_.has(Ns.i.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.has(Ns.i.>=(1)).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.ii_.has(Ns.i_(1)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.ii_.has(Ns.i_.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.has(Ns.i_.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.has(Ns.i_.<=(1)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.ii_.has(Ns.i_.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.has(Ns.i_.>=(1)).query.get.map(_ ==> List("a"))
      } yield ()
    }


    "has - Sets that don't contain value of other attribute" - types { implicit conn =>
      for {
        _ <- Ns.s.ii.i.insert(
          ("a", Set(1, 2), 1),
          ("b", Set(3), 2),
        ).transact

        _ <- Ns.s.ii.hasNo(Ns.i).query.get.map(_ ==> List(("b", Set(3), 2)))
        _ <- Ns.s.ii.hasNo(Ns.i_).query.get.map(_ ==> List(("b", Set(3))))
        _ <- Ns.s.ii_.hasNo(Ns.i).query.get.map(_ ==> List(("b", 2)))
        _ <- Ns.s.ii_.hasNo(Ns.i_).query.get.map(_ ==> List("b"))

        // With expression on expression attribute itself
        _ <- Ns.s.ii.hasNo(Ns.i(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasNo(Ns.i.not(1)).query.get.map(_ ==> List(("b", Set(3), 2)))
        _ <- Ns.s.ii.hasNo(Ns.i.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasNo(Ns.i.<=(2)).query.get.map(_ ==> List(("b", Set(3), 2)))
        _ <- Ns.s.ii.hasNo(Ns.i.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasNo(Ns.i.>=(2)).query.get.map(_ ==> List(("b", Set(3), 2)))

        _ <- Ns.s.ii.hasNo(Ns.i_(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasNo(Ns.i_.not(1)).query.get.map(_ ==> List(("b", Set(3))))
        _ <- Ns.s.ii.hasNo(Ns.i_.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasNo(Ns.i_.<=(2)).query.get.map(_ ==> List(("b", Set(3))))
        _ <- Ns.s.ii.hasNo(Ns.i_.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasNo(Ns.i_.>=(2)).query.get.map(_ ==> List(("b", Set(3))))

        _ <- Ns.s.ii_.hasNo(Ns.i(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasNo(Ns.i.not(1)).query.get.map(_ ==> List(("b", 2)))
        _ <- Ns.s.ii_.hasNo(Ns.i.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasNo(Ns.i.<=(2)).query.get.map(_ ==> List(("b", 2)))
        _ <- Ns.s.ii_.hasNo(Ns.i.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasNo(Ns.i.>=(2)).query.get.map(_ ==> List(("b", 2)))

        _ <- Ns.s.ii_.hasNo(Ns.i_(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasNo(Ns.i_.not(1)).query.get.map(_ ==> List("b"))
        _ <- Ns.s.ii_.hasNo(Ns.i_.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasNo(Ns.i_.<=(2)).query.get.map(_ ==> List("b"))
        _ <- Ns.s.ii_.hasNo(Ns.i_.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasNo(Ns.i_.>=(2)).query.get.map(_ ==> List("b"))
      } yield ()
    }


    "hasLt - Sets with at least one value less than other attribute value" - types { implicit conn =>
      for {
        _ <- Ns.s.ii.i.insert(
          ("a", Set(1, 2), 2),
          ("b", Set(3), 3),
        ).transact

        _ <- Ns.s.ii.hasLt(Ns.i).query.get.map(_ ==> List(("a", Set(1, 2), 2)))
        _ <- Ns.s.ii.hasLt(Ns.i_).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii_.hasLt(Ns.i).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.ii_.hasLt(Ns.i_).query.get.map(_ ==> List("a"))

        // With expression on expression attribute itself
        _ <- Ns.s.ii.hasLt(Ns.i(2)).query.get.map(_ ==> List(("a", Set(1, 2), 2)))
        _ <- Ns.s.ii.hasLt(Ns.i.not(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasLt(Ns.i.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasLt(Ns.i.<=(2)).query.get.map(_ ==> List(("a", Set(1, 2), 2)))
        _ <- Ns.s.ii.hasLt(Ns.i.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasLt(Ns.i.>=(2)).query.get.map(_ ==> List(("a", Set(1, 2), 2)))

        _ <- Ns.s.ii.hasLt(Ns.i_(2)).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii.hasLt(Ns.i_.not(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasLt(Ns.i_.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasLt(Ns.i_.<=(2)).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii.hasLt(Ns.i_.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasLt(Ns.i_.>=(2)).query.get.map(_ ==> List(("a", Set(1, 2))))

        _ <- Ns.s.ii_.hasLt(Ns.i(2)).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.ii_.hasLt(Ns.i.not(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasLt(Ns.i.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasLt(Ns.i.<=(2)).query.get.map(_ ==> List(("a", 2)))
        _ <- Ns.s.ii_.hasLt(Ns.i.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasLt(Ns.i.>=(2)).query.get.map(_ ==> List(("a", 2)))

        _ <- Ns.s.ii_.hasLt(Ns.i_(2)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.ii_.hasLt(Ns.i_.not(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasLt(Ns.i_.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasLt(Ns.i_.<=(2)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.ii_.hasLt(Ns.i_.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasLt(Ns.i_.>=(2)).query.get.map(_ ==> List("a"))
      } yield ()
    }


    "hasLe - Sets with at least one value less than or equal other attribute value" - types { implicit conn =>
      for {
        _ <- Ns.s.ii.i.insert(
          ("a", Set(1, 2), 1),
          ("b", Set(3), 2),
        ).transact

        _ <- Ns.s.ii.hasLe(Ns.i).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.ii.hasLe(Ns.i_).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii_.hasLe(Ns.i).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.ii_.hasLe(Ns.i_).query.get.map(_ ==> List("a"))

        // With expression on expression attribute itself
        _ <- Ns.s.ii.hasLe(Ns.i(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.ii.hasLe(Ns.i.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasLe(Ns.i.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasLe(Ns.i.<=(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.ii.hasLe(Ns.i.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasLe(Ns.i.>=(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))

        _ <- Ns.s.ii.hasLe(Ns.i_(1)).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii.hasLe(Ns.i_.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasLe(Ns.i_.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasLe(Ns.i_.<=(1)).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii.hasLe(Ns.i_.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasLe(Ns.i_.>=(1)).query.get.map(_ ==> List(("a", Set(1, 2))))

        _ <- Ns.s.ii_.hasLe(Ns.i(1)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.ii_.hasLe(Ns.i.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasLe(Ns.i.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasLe(Ns.i.<=(1)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.ii_.hasLe(Ns.i.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasLe(Ns.i.>=(1)).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.ii_.hasLe(Ns.i_(1)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.ii_.hasLe(Ns.i_.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasLe(Ns.i_.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasLe(Ns.i_.<=(1)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.ii_.hasLe(Ns.i_.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasLe(Ns.i_.>=(1)).query.get.map(_ ==> List("a"))
      } yield ()
    }


    "hasGt - Sets with at least one value greater than other attribute value" - types { implicit conn =>
      for {
        _ <- Ns.s.ii.i.insert(
          ("a", Set(1, 2), 1),
          ("b", Set(3), 3),
        ).transact

        _ <- Ns.s.ii.hasGt(Ns.i).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.ii.hasGt(Ns.i_).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii_.hasGt(Ns.i).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.ii_.hasGt(Ns.i_).query.get.map(_ ==> List("a"))

        // With expression on expression attribute itself
        _ <- Ns.s.ii.hasGt(Ns.i(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.ii.hasGt(Ns.i.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasGt(Ns.i.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasGt(Ns.i.<=(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.ii.hasGt(Ns.i.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasGt(Ns.i.>=(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))

        _ <- Ns.s.ii.hasGt(Ns.i_(1)).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii.hasGt(Ns.i_.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasGt(Ns.i_.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasGt(Ns.i_.<=(1)).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii.hasGt(Ns.i_.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasGt(Ns.i_.>=(1)).query.get.map(_ ==> List(("a", Set(1, 2))))

        _ <- Ns.s.ii_.hasGt(Ns.i(1)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.ii_.hasGt(Ns.i.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasGt(Ns.i.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasGt(Ns.i.<=(1)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.ii_.hasGt(Ns.i.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasGt(Ns.i.>=(1)).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.ii_.hasGt(Ns.i_(1)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.ii_.hasGt(Ns.i_.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasGt(Ns.i_.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasGt(Ns.i_.<=(1)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.ii_.hasGt(Ns.i_.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasGt(Ns.i_.>=(1)).query.get.map(_ ==> List("a"))
      } yield ()
    }


    "hasGe - Sets with at least one value greater than or equal other attribute value" - types { implicit conn =>
      for {
        _ <- Ns.s.ii.i.insert(
          ("a", Set(1, 2), 1),
          ("b", Set(3), 4),
        ).transact

        _ <- Ns.s.ii.hasGe(Ns.i).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.ii.hasGe(Ns.i_).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii_.hasGe(Ns.i).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.ii_.hasGe(Ns.i_).query.get.map(_ ==> List("a"))

        // With expression on expression attribute itself
        _ <- Ns.s.ii.hasGe(Ns.i(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.ii.hasGe(Ns.i.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasGe(Ns.i.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasGe(Ns.i.<=(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.ii.hasGe(Ns.i.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasGe(Ns.i.>=(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))

        _ <- Ns.s.ii.hasGe(Ns.i_(1)).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii.hasGe(Ns.i_.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasGe(Ns.i_.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasGe(Ns.i_.<=(1)).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.ii.hasGe(Ns.i_.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii.hasGe(Ns.i_.>=(1)).query.get.map(_ ==> List(("a", Set(1, 2))))

        _ <- Ns.s.ii_.hasGe(Ns.i(1)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.ii_.hasGe(Ns.i.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasGe(Ns.i.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasGe(Ns.i.<=(1)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.ii_.hasGe(Ns.i.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasGe(Ns.i.>=(1)).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.ii_.hasGe(Ns.i_(1)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.ii_.hasGe(Ns.i_.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasGe(Ns.i_.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasGe(Ns.i_.<=(1)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.ii_.hasGe(Ns.i_.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.ii_.hasGe(Ns.i_.>=(1)).query.get.map(_ ==> List("a"))
      } yield ()
    }
  }
}
