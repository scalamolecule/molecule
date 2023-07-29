package molecule.coreTests.test.relation

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions


trait Bidirectional extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "one" - refs { implicit conn =>
      for {
        // 1 is married to 2
        _ <- A.i(1).A.i(2).save.transact

        // Directional
        // 1 married to 2, but 2 not married to 1, duh
        _ <- A.i.A.i.query.get.map(_ ==> List((1, 2)))
        _ <- A.i_(1).A.i.query.get.map(_ ==> List(2))
        _ <- A.i_(2).A.i.query.get.map(_ ==> List())

        // Bidirectional
        // 1 is married with 2. So 2 is also married to 1
        _ <- A.i.a1.A(bi).i.query.get.map(_ ==> List((1, 2), (2, 1)))
        _ <- A.i_(1).A(bi).i.query.get.map(_ ==> List(2))
        _ <- A.i_(2).A(bi).i.query.get.map(_ ==> List(1))

        // My spouse' spouse is me (1)
        _ <- A.i(1).A(bi).i.A(bi).i.query.get.map(_ ==> List((1, 2, 1)))
      } yield ()
    }


    "many" - refs { implicit conn =>
      for {
        // 1 is friend of 2 and 3
        _ <- A.i.Aa.*(A.i).insert((1, List(2, 3))).transact

        // Directional
        // 1 friend of 2 and 3, but 2 and 3 not friends of 1, duh
        _ <- A.i.Aa.*(A.i.a1).query.get.map(_ ==> List((1, List(2, 3))))
        _ <- A.i_(1).Aa.*(A.i.a1).query.get.map(_ ==> List(List(2, 3)))
        _ <- A.i_(2).Aa.*(A.i.a1).query.get.map(_ ==> List())
        _ <- A.i_(3).Aa.*(A.i.a1).query.get.map(_ ==> List())

        // Bidirectional
        // 1 is friend of 2 and 3. So 2 and 3 are both friends of 1
        _ <- A.i.a1.Aa(bi).*(A.i.a1).query.get.map(_ ==> List(
          (1, List(2, 3)),
          (2, List(1)),
          (3, List(1))
        ))
        _ <- A.i_(1).Aa(bi).*(A.i.a1).query.get.map(_ ==> List(List(2, 3)))
        _ <- A.i_(2).Aa(bi).*(A.i.a1).query.get.map(_ ==> List(List(1)))
        _ <- A.i_(3).Aa(bi).*(A.i.a1).query.get.map(_ ==> List(List(1)))

        // My friends' friends include me (1)
        _ <- A.i(1).Aa(bi).*(A.i.Aa(bi).*(A.i)).query.get.map(_ ==> List(
          (1, List(
            (2, List(1)),
            (3, List(1)),
          ))
        ))
      } yield ()
    }


    // todo ?
//    "optional nested" - refs { implicit conn =>
    //      // Bidirectional marker not available with optional nested data structures (won't type infer)
    //      // A.i.Aa(bi).*?(A.i)
    //      compileError("A.i.Aa(bi).*?(A.i)")
    //    }
  }
}
