package molecule.coreTests.test.filterAttr

import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._

// Works as with normal relationships - see test.filterAttr.one.CrossNs
trait FilterAttrComposite extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "basic" - types { implicit conn =>
      for {
        _ <- (Ns.s.i + Ref.s.int).insert(
          (("a", 1), ("x", 2)),
          (("b", 3), ("y", 3)),
          (("c", 5), ("z", 4)),
        ).transact

        _ <- (Ns.s.i(Ref.int_) + Ref.s.int).query.get.map(_ ==> List((("b", 3), ("y", 3))))
        _ <- (Ns.s.i(Ref.int_) + Ref.s.int_).query.get.map(_ ==> List((("b", 3), "y")))
        _ <- (Ns.s.i_(Ref.int_) + Ref.s.int).query.get.map(_ ==> List(("b", ("y", 3))))
        _ <- (Ns.s.i_(Ref.int_) + Ref.s.int_).query.get.map(_ ==> List(("b", "y")))

        // Filter compare attribute itself
        _ <- (Ns.s.i(Ref.int_) + Ref.s.int(3)).query.get.map(_    ==> List((("b", 3), ("y", 3))))
        _ <- (Ns.s.i(Ref.int_) + Ref.s.int.not(3)).query.get.map(_ ==> List())
        _ <- (Ns.s.i(Ref.int_) + Ref.s.int.>(3)).query.get.map(_ ==> List())
        _ <- (Ns.s.i(Ref.int_) + Ref.s.int.>=(3)).query.get.map(_ ==> List((("b", 3), ("y", 3))))
        _ <- (Ns.s.i(Ref.int_) + Ref.s.int.<(3)).query.get.map(_ ==> List())
        _ <- (Ns.s.i(Ref.int_) + Ref.s.int.<=(3)).query.get.map(_ ==> List((("b", 3), ("y", 3))))


        // Pointing backwards

        _ <- (Ns.s.i + Ref.s.int(Ns.i_)).query.get.map(_ ==> List((("b", 3), ("y", 3))))
        _ <- (Ns.s.i + Ref.s.int_(Ns.i_)).query.get.map(_ ==> List((("b", 3), "y")))
        _ <- (Ns.s.i_ + Ref.s.int(Ns.i_)).query.get.map(_ ==> List(("b", ("y", 3))))
        _ <- (Ns.s.i_ + Ref.s.int_(Ns.i_)).query.get.map(_ ==> List(("b", "y")))

        // Filter compare attribute itself
        _ <- (Ns.s.i(3)    + Ref.s.int(Ns.i_)).query.get.map(_ ==> List((("b", 3), ("y", 3))))
        _ <- (Ns.s.i.not(3) + Ref.s.int(Ns.i_)).query.get.map(_ ==> List())
        _ <- (Ns.s.i.>(3) + Ref.s.int(Ns.i_)).query.get.map(_ ==> List())
        _ <- (Ns.s.i.>=(3) + Ref.s.int(Ns.i_)).query.get.map(_ ==> List((("b", 3), ("y", 3))))
        _ <- (Ns.s.i.<(3) + Ref.s.int(Ns.i_)).query.get.map(_ ==> List())
        _ <- (Ns.s.i.<=(3) + Ref.s.int(Ns.i_)).query.get.map(_ ==> List((("b", 3), ("y", 3))))
      } yield ()
    }
  }
}
