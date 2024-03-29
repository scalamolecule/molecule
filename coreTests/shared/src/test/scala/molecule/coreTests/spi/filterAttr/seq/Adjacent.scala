package molecule.coreTests.spi.filterAttr.seq

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Adjacent extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "has" - types { implicit conn =>
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


    "hasNo" - types { implicit conn =>
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
