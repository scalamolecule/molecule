package molecule.coreTests.spi.filterAttr.set

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Adjacent extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  val a = (1, Set(0, 1, 2), Set(1, 2, 3))
  val b = (2, Set(2, 3), Set(2, 3))
  val c = (3, Set(4), Set(3))

  override lazy val tests = Tests {

    "equal (apply) - Sets that match other Sets" - types { implicit conn =>
      for {
        _ <- Ns.i.iSet.intSet.insert(a, b, c).transact

        _ <- Ns.i.iSet(Ns.intSet).query.get.map(_ ==> List(b))
        _ <- Ns.i.iSet(Ns.intSet_).query.get.map(_ ==> List((2, Set(2, 3)))) // Ns.iSet
        _ <- Ns.i.iSet_(Ns.intSet).query.get.map(_ ==> List((2, Set(2, 3)))) // Ref.intSet
        _ <- Ns.i.iSet_(Ns.intSet_).query.get.map(_ ==> List(2))
      } yield ()
    }


    "not equal - Sets that don't match other Sets" - types { implicit conn =>
      for {
        _ <- Ns.i.iSet.intSet.insert(a, b, c).transact

        _ <- Ns.i.a1.iSet.not(Ns.intSet).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.iSet.not(Ns.intSet_).query.get.map(_ ==> List((1, Set(0, 1, 2)), (3, Set(4))))
        _ <- Ns.i.a1.iSet_.not(Ns.intSet).query.get.map(_ ==> List((1, Set(1, 2, 3)), (3, Set(3))))
        _ <- Ns.i.a1.iSet_.not(Ns.intSet_).query.get.map(_ ==> List(1, 3))
      } yield ()
    }


    "has - Sets that contain all values of other Set" - types { implicit conn =>
      for {
        _ <- Ns.i.iSet.intSet.insert(a, b, c).transact

        _ <- Ns.i.iSet.has(Ns.intSet).query.get.map(_ ==> List(b)) // Ns.iSet and Ref.intSet
        _ <- Ns.i.iSet.has(Ns.intSet_).query.get.map(_ ==> List((2, Set(2, 3)))) // Ns.iSet
        _ <- Ns.i.iSet_.has(Ns.intSet).query.get.map(_ ==> List((2, Set(2, 3)))) // Ref.intSet
        _ <- Ns.i.iSet_.has(Ns.intSet_).query.get.map(_ ==> List(2))
      } yield ()
    }


    "hasNo - Sets that don't contain any values of other Set" - types { implicit conn =>
      for {
        _ <- Ns.i.iSet.intSet.insert(a, b, c).transact

        _ <- Ns.i.iSet.hasNo(Ns.intSet).query.get.map(_ ==> List(c))
        _ <- Ns.i.iSet.hasNo(Ns.intSet_).query.get.map(_ ==> List((3, Set(4))))
        _ <- Ns.i.iSet_.hasNo(Ns.intSet).query.get.map(_ ==> List((3, Set(3))))
        _ <- Ns.i.iSet_.hasNo(Ns.intSet_).query.get.map(_ ==> List(3))
      } yield ()
    }


    "has - Sets that contain value of other attribute" - types { implicit conn =>
      for {
        _ <- Ns.s.iSet.i.insert(
          ("a", Set(1, 2), 1),
          ("b", Set(3), 2),
        ).transact

        _ <- Ns.s.iSet.has(Ns.i).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.iSet.has(Ns.i_).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.iSet_.has(Ns.i).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.iSet_.has(Ns.i_).query.get.map(_ ==> List("a"))

        // Filter compare attribute itself
        _ <- Ns.s.iSet.has(Ns.i(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.iSet.has(Ns.i.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet.has(Ns.i.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet.has(Ns.i.<=(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))
        _ <- Ns.s.iSet.has(Ns.i.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet.has(Ns.i.>=(1)).query.get.map(_ ==> List(("a", Set(1, 2), 1)))

        _ <- Ns.s.iSet.has(Ns.i_(1)).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.iSet.has(Ns.i_.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet.has(Ns.i_.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet.has(Ns.i_.<=(1)).query.get.map(_ ==> List(("a", Set(1, 2))))
        _ <- Ns.s.iSet.has(Ns.i_.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet.has(Ns.i_.>=(1)).query.get.map(_ ==> List(("a", Set(1, 2))))

        _ <- Ns.s.iSet_.has(Ns.i(1)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.iSet_.has(Ns.i.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet_.has(Ns.i.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet_.has(Ns.i.<=(1)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.iSet_.has(Ns.i.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet_.has(Ns.i.>=(1)).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.iSet_.has(Ns.i_(1)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.iSet_.has(Ns.i_.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet_.has(Ns.i_.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet_.has(Ns.i_.<=(1)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.iSet_.has(Ns.i_.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet_.has(Ns.i_.>=(1)).query.get.map(_ ==> List("a"))
      } yield ()
    }


    "hasNo - Sets that don't contain value of other attribute" - types { implicit conn =>
      for {
        _ <- Ns.s.iSet.i.insert(
          ("a", Set(1, 2), 1),
          ("b", Set(3), 2),
        ).transact

        _ <- Ns.s.iSet.hasNo(Ns.i).query.get.map(_ ==> List(("b", Set(3), 2)))
        _ <- Ns.s.iSet.hasNo(Ns.i_).query.get.map(_ ==> List(("b", Set(3))))
        _ <- Ns.s.iSet_.hasNo(Ns.i).query.get.map(_ ==> List(("b", 2)))
        _ <- Ns.s.iSet_.hasNo(Ns.i_).query.get.map(_ ==> List("b"))

        // Filter compare attribute itself
        _ <- Ns.s.iSet.hasNo(Ns.i(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet.hasNo(Ns.i.not(1)).query.get.map(_ ==> List(("b", Set(3), 2)))
        _ <- Ns.s.iSet.hasNo(Ns.i.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet.hasNo(Ns.i.<=(2)).query.get.map(_ ==> List(("b", Set(3), 2)))
        _ <- Ns.s.iSet.hasNo(Ns.i.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet.hasNo(Ns.i.>=(2)).query.get.map(_ ==> List(("b", Set(3), 2)))

        _ <- Ns.s.iSet.hasNo(Ns.i_(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet.hasNo(Ns.i_.not(1)).query.get.map(_ ==> List(("b", Set(3))))
        _ <- Ns.s.iSet.hasNo(Ns.i_.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet.hasNo(Ns.i_.<=(2)).query.get.map(_ ==> List(("b", Set(3))))
        _ <- Ns.s.iSet.hasNo(Ns.i_.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet.hasNo(Ns.i_.>=(2)).query.get.map(_ ==> List(("b", Set(3))))

        _ <- Ns.s.iSet_.hasNo(Ns.i(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet_.hasNo(Ns.i.not(1)).query.get.map(_ ==> List(("b", 2)))
        _ <- Ns.s.iSet_.hasNo(Ns.i.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet_.hasNo(Ns.i.<=(2)).query.get.map(_ ==> List(("b", 2)))
        _ <- Ns.s.iSet_.hasNo(Ns.i.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet_.hasNo(Ns.i.>=(2)).query.get.map(_ ==> List(("b", 2)))

        _ <- Ns.s.iSet_.hasNo(Ns.i_(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet_.hasNo(Ns.i_.not(1)).query.get.map(_ ==> List("b"))
        _ <- Ns.s.iSet_.hasNo(Ns.i_.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet_.hasNo(Ns.i_.<=(2)).query.get.map(_ ==> List("b"))
        _ <- Ns.s.iSet_.hasNo(Ns.i_.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSet_.hasNo(Ns.i_.>=(2)).query.get.map(_ ==> List("b"))
      } yield ()
    }
  }
}
