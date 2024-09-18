package molecule.coreTests.spi.relation.nested

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait NestedBasic extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory/optional rows" - refs { implicit conn =>
      for {
        _ <- A.s.Bb.*(B.i).insert(
          ("a", List(1, 2)),
          ("b", Nil),
        ).transact

        // Mandatory nested data
        _ <- A.s.Bb.*(B.i.a1).query.get.map(_ ==> List(
          ("a", List(1, 2)),
        ))

        // Optional nested data
        _ <- A.s.a1.Bb.*?(B.i.a1).query.get.map(_ ==> List(
          ("a", List(1, 2)),
          ("b", Nil),
        ))
      } yield ()
    }


    "Nested" - {

      "one" - refs { implicit conn =>
        import molecule.coreTests.dataModels.dsl.Refs._
        for {
          _ <- A.i.Bb.*(B.i).insert(2, List(3, 4)).transact
          _ <- A.i.Bb.*(B.i.a1).query.get.map(_ ==> List((2, List(3, 4))))
        } yield ()
      }

      "set" - refs { implicit conn =>
        import molecule.coreTests.dataModels.dsl.Refs._
        for {
          _ <- A.i.Bb.*(B.iSet).insert(List((2, List(Set(3, 4))))).transact
          _ <- A.i.Bb.*(B.iSet).query.get.map(_ ==> List((2, List(Set(3, 4)))))
        } yield ()
      }
    }


    "Nested owned" - {

      "one" - refs { implicit conn =>
        import molecule.coreTests.dataModels.dsl.Refs._
        for {
          _ <- A.i.OwnBb.*(B.i).insert(2, List(3, 4)).transact
          _ <- A.i.OwnBb.*(B.i.a1).query.get.map(_ ==> List((2, List(3, 4))))
        } yield ()
      }

      "set" - refs { implicit conn =>
        import molecule.coreTests.dataModels.dsl.Refs._
        for {
          _ <- A.i.OwnBb.*(B.iSet).insert(List((2, List(Set(3, 4))))).transact
          _ <- A.i.OwnBb.*(B.iSet).query.get.map(_ ==> List((2, List(Set(3, 4)))))
        } yield ()
      }
    }
  }
}
