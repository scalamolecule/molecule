package molecule.coreTests.spi.filterAttr.seq

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Adjacent extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  val a = (1, List(0, 1, 2), List(1, 2, 3))
  val b = (2, List(2, 3), List(2, 3))
  val c = (3, List(4), List(3))

  override lazy val tests = Tests {

    "equal (apply) - Lists that match other Lists" - types { implicit conn =>
      for {
        _ <- Ns.i.iSeq.intSeq.insert(a, b, c).transact

        _ <- Ns.i.iSeq(Ns.intSeq).query.get.map(_ ==> List(b))
        _ <- Ns.i.iSeq(Ns.intSeq_).query.get.map(_ ==> List((2, List(2, 3)))) // Ns.iSeq
        _ <- Ns.i.iSeq_(Ns.intSeq).query.get.map(_ ==> List((2, List(2, 3)))) // Ref.intSeq
        _ <- Ns.i.iSeq_(Ns.intSeq_).query.get.map(_ ==> List(2))
      } yield ()
    }


    "not equal - Lists that don't match other Lists" - types { implicit conn =>
      for {
        _ <- Ns.i.iSeq.intSeq.insert(a, b, c).transact

        _ <- Ns.i.a1.iSeq.not(Ns.intSeq).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.iSeq.not(Ns.intSeq_).query.get.map(_ ==> List((1, List(0, 1, 2)), (3, List(4))))
        _ <- Ns.i.a1.iSeq_.not(Ns.intSeq).query.get.map(_ ==> List((1, List(1, 2, 3)), (3, List(3))))
        _ <- Ns.i.a1.iSeq_.not(Ns.intSeq_).query.get.map(_ ==> List(1, 3))
      } yield ()
    }


    "has - Lists that contain all values of other List" - types { implicit conn =>
      for {
        _ <- Ns.i.iSeq.intSeq.insert(a, b, c).transact

        _ <- Ns.i.iSeq.has(Ns.intSeq).query.get.map(_ ==> List(b)) // Ns.iSeq and Ref.intSeq
        _ <- Ns.i.iSeq.has(Ns.intSeq_).query.get.map(_ ==> List((2, List(2, 3)))) // Ns.iSeq
        _ <- Ns.i.iSeq_.has(Ns.intSeq).query.get.map(_ ==> List((2, List(2, 3)))) // Ref.intSeq
        _ <- Ns.i.iSeq_.has(Ns.intSeq_).query.get.map(_ ==> List(2))
      } yield ()
    }


    "hasNo - Lists that don't contain any values of other List" - types { implicit conn =>
      for {
        _ <- Ns.i.iSeq.intSeq.insert(a, b, c).transact

        _ <- Ns.i.iSeq.hasNo(Ns.intSeq).query.get.map(_ ==> List(c))
        _ <- Ns.i.iSeq.hasNo(Ns.intSeq_).query.get.map(_ ==> List((3, List(4))))
        _ <- Ns.i.iSeq_.hasNo(Ns.intSeq).query.get.map(_ ==> List((3, List(3))))
        _ <- Ns.i.iSeq_.hasNo(Ns.intSeq_).query.get.map(_ ==> List(3))
      } yield ()
    }


    "has - Lists that contain value of other attribute" - types { implicit conn =>
      for {
        _ <- Ns.s.iSeq.i.insert(
          ("a", List(1, 2), 1),
          ("b", List(3), 2),
        ).transact

        _ <- Ns.s.iSeq.has(Ns.i).query.get.map(_ ==> List(("a", List(1, 2), 1)))
        _ <- Ns.s.iSeq.has(Ns.i_).query.get.map(_ ==> List(("a", List(1, 2))))
        _ <- Ns.s.iSeq_.has(Ns.i).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.iSeq_.has(Ns.i_).query.get.map(_ ==> List("a"))

        // Filter compare attribute itself
        _ <- Ns.s.iSeq.has(Ns.i(1)).query.get.map(_ ==> List(("a", List(1, 2), 1)))
        _ <- Ns.s.iSeq.has(Ns.i.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq.has(Ns.i.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq.has(Ns.i.<=(1)).query.get.map(_ ==> List(("a", List(1, 2), 1)))
        _ <- Ns.s.iSeq.has(Ns.i.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq.has(Ns.i.>=(1)).query.get.map(_ ==> List(("a", List(1, 2), 1)))

        _ <- Ns.s.iSeq.has(Ns.i_(1)).query.get.map(_ ==> List(("a", List(1, 2))))
        _ <- Ns.s.iSeq.has(Ns.i_.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq.has(Ns.i_.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq.has(Ns.i_.<=(1)).query.get.map(_ ==> List(("a", List(1, 2))))
        _ <- Ns.s.iSeq.has(Ns.i_.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq.has(Ns.i_.>=(1)).query.get.map(_ ==> List(("a", List(1, 2))))

        _ <- Ns.s.iSeq_.has(Ns.i(1)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.iSeq_.has(Ns.i.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq_.has(Ns.i.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq_.has(Ns.i.<=(1)).query.get.map(_ ==> List(("a", 1)))
        _ <- Ns.s.iSeq_.has(Ns.i.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq_.has(Ns.i.>=(1)).query.get.map(_ ==> List(("a", 1)))

        _ <- Ns.s.iSeq_.has(Ns.i_(1)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.iSeq_.has(Ns.i_.not(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq_.has(Ns.i_.<(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq_.has(Ns.i_.<=(1)).query.get.map(_ ==> List("a"))
        _ <- Ns.s.iSeq_.has(Ns.i_.>(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq_.has(Ns.i_.>=(1)).query.get.map(_ ==> List("a"))
      } yield ()
    }


    "hasNo - Lists that don't contain value of other attribute" - types { implicit conn =>
      for {
        _ <- Ns.s.iSeq.i.insert(
          ("a", List(1, 2), 1),
          ("b", List(3), 2),
        ).transact

        _ <- Ns.s.iSeq.hasNo(Ns.i).query.get.map(_ ==> List(("b", List(3), 2)))
        _ <- Ns.s.iSeq.hasNo(Ns.i_).query.get.map(_ ==> List(("b", List(3))))
        _ <- Ns.s.iSeq_.hasNo(Ns.i).query.get.map(_ ==> List(("b", 2)))
        _ <- Ns.s.iSeq_.hasNo(Ns.i_).query.get.map(_ ==> List("b"))

        // Filter compare attribute itself
        _ <- Ns.s.iSeq.hasNo(Ns.i(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq.hasNo(Ns.i.not(1)).query.get.map(_ ==> List(("b", List(3), 2)))
        _ <- Ns.s.iSeq.hasNo(Ns.i.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq.hasNo(Ns.i.<=(2)).query.get.map(_ ==> List(("b", List(3), 2)))
        _ <- Ns.s.iSeq.hasNo(Ns.i.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq.hasNo(Ns.i.>=(2)).query.get.map(_ ==> List(("b", List(3), 2)))

        _ <- Ns.s.iSeq.hasNo(Ns.i_(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq.hasNo(Ns.i_.not(1)).query.get.map(_ ==> List(("b", List(3))))
        _ <- Ns.s.iSeq.hasNo(Ns.i_.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq.hasNo(Ns.i_.<=(2)).query.get.map(_ ==> List(("b", List(3))))
        _ <- Ns.s.iSeq.hasNo(Ns.i_.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq.hasNo(Ns.i_.>=(2)).query.get.map(_ ==> List(("b", List(3))))

        _ <- Ns.s.iSeq_.hasNo(Ns.i(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq_.hasNo(Ns.i.not(1)).query.get.map(_ ==> List(("b", 2)))
        _ <- Ns.s.iSeq_.hasNo(Ns.i.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq_.hasNo(Ns.i.<=(2)).query.get.map(_ ==> List(("b", 2)))
        _ <- Ns.s.iSeq_.hasNo(Ns.i.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq_.hasNo(Ns.i.>=(2)).query.get.map(_ ==> List(("b", 2)))

        _ <- Ns.s.iSeq_.hasNo(Ns.i_(1)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq_.hasNo(Ns.i_.not(1)).query.get.map(_ ==> List("b"))
        _ <- Ns.s.iSeq_.hasNo(Ns.i_.<(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq_.hasNo(Ns.i_.<=(2)).query.get.map(_ ==> List("b"))
        _ <- Ns.s.iSeq_.hasNo(Ns.i_.>(2)).query.get.map(_ ==> List())
        _ <- Ns.s.iSeq_.hasNo(Ns.i_.>=(2)).query.get.map(_ ==> List("b"))
      } yield ()
    }
  }
}
