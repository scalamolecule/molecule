package molecule.coreTests.test.crud.save

import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._

trait SaveComposite extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>


  override lazy val tests = Tests {

    "Card one" - refs { implicit conn =>
      for {
        _ <- A.i(1).B.i(2).save.transact
        _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))
      } yield ()
    }


    "Card many" - refs { implicit conn =>
      for {
        _ <- A.i(1).Bb.i(2).save.transact
        _ <- A.i.Bb.i.query.get.map(_ ==> List((1, 2)))
      } yield ()
    }


    "Backref" - refs { implicit conn =>
      for {
        _ <- A.i(1).Bb.i(2)._A.s("a").save.transact
        _ <- A.i.Bb.i._A.s.query.get.map(_ ==> List((1, 2, "a")))
      } yield ()
    }


    "Composite" - refs { implicit conn =>
      for {
        _ <- (A.i(1) + C.i(2)).save.transact
        _ <- (A.i + C.i).query.get.map(_ ==> List((1, 2)))

        _ <- (A.B.i(1) + C.i(2)).save.transact
        _ <- (A.B.i + C.i).query.get.map(_ ==> List((1, 2)))
      } yield ()
    }
  }
}
