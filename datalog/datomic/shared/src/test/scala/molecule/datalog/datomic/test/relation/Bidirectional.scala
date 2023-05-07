package molecule.datalog.datomic.test.relation

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions


object Bidirectional extends DatomicTestSuite {

  override lazy val tests = Tests {

    "one" - refs { implicit conn =>
      for {
        // 1 is married to 2
        _ <- Ns.i(1).Spouse.i(2).save.transact

        // Directional
        // 1 married to 2, but 2 not married to 1, duh
        _ <- Ns.i.Spouse.i.query.get.map(_ ==> List((1, 2)))
        _ <- Ns.i_(1).Spouse.i.query.get.map(_ ==> List(2))
        _ <- Ns.i_(2).Spouse.i.query.get.map(_ ==> List())

        // Bidirectional
        // 1 is married with 2. So 2 is also married to 1
        _ <- Ns.i.a1.Spouse(bi).i.query.get.map(_ ==> List((1, 2), (2, 1)))
        _ <- Ns.i_(1).Spouse(bi).i.query.get.map(_ ==> List(2))
        _ <- Ns.i_(2).Spouse(bi).i.query.get.map(_ ==> List(1))

        // My spouse' spouse is me (1)
        _ <- Ns.i(1).Spouse(bi).i.Spouse(bi).i.query.get.map(_ ==> List((1, 2, 1)))
      } yield ()
    }


    "many" - refs { implicit conn =>
      for {
        // 1 is friend of 2 and 3
        _ <- Ns.i.Friends.*(Ns.i).insert((1, List(2, 3))).transact

        // Directional
        // 1 friend of 2 and 3, but 2 and 3 not friends of 1, duh
        _ <- Ns.i.Friends.*(Ns.i.a1).query.get.map(_ ==> List((1, List(2, 3))))
        _ <- Ns.i_(1).Friends.*(Ns.i.a1).query.get.map(_ ==> List(List(2, 3)))
        _ <- Ns.i_(2).Friends.*(Ns.i.a1).query.get.map(_ ==> List())
        _ <- Ns.i_(3).Friends.*(Ns.i.a1).query.get.map(_ ==> List())

        // Bidirectional
        // 1 is friend of 2 and 3. So 2 and 3 are both friends of 1
        _ <- Ns.i.a1.Friends(bi).*(Ns.i.a1).query.get.map(_ ==> List(
          (1, List(2, 3)),
          (2, List(1)),
          (3, List(1))
        ))
        _ <- Ns.i_(1).Friends(bi).*(Ns.i.a1).query.get.map(_ ==> List(List(2, 3)))
        _ <- Ns.i_(2).Friends(bi).*(Ns.i.a1).query.get.map(_ ==> List(List(1)))
        _ <- Ns.i_(3).Friends(bi).*(Ns.i.a1).query.get.map(_ ==> List(List(1)))

        // My friends' friends include me (1)
        _ <- Ns.i(1).Friends(bi).*(Ns.i.Friends(bi).*(Ns.i)).query.get.map(_ ==> List(
          (1, List(
            (2, List(1)),
            (3, List(1)),
          ))
        ))
      } yield ()
    }


    "optional nested" - refs { implicit conn =>
      // Bidirectional marker not available with optional nested data structures (won't type infer)
      // Ns.i.Friends(bi).*?(Ns.i)
      compileError("Ns.i.Friends(bi).*?(Ns.i)")
    }
  }
}
