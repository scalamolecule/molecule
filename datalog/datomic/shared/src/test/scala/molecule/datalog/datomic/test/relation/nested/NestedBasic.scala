package molecule.datalog.datomic.test.relation.nested

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._


object NestedBasic extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Mandatory/optional rows" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i).insert(
          (1, List(10, 11)),
          (2, Nil),
        ).transact

        // Mandatory nested data
        _ <- Ns.i.Rs1.*(R1.i.a1).query.get.map(_ ==> List(
          (1, List(10, 11)),
        ))

        // Optional nested data
        _ <- Ns.i.Rs1.*?(R1.i.a1).query.get.map(_ ==> List(
          (1, List(10, 11)),
          (2, Nil),
        ))
      } yield ()
    }
  }
}