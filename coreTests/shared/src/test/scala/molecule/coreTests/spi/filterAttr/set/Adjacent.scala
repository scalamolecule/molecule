package molecule.coreTests.spi.filterAttr.set

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Adjacent extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  val a = (1, Set(0, 1, 2), Set(1, 2, 3))
  val b = (2, Set(2, 3), Set(2, 3))
  val c = (3, Set(4), Set(3))

  override lazy val tests = Tests {

    "has" - types { implicit conn =>
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


    "hasNo" - types { implicit conn =>
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
