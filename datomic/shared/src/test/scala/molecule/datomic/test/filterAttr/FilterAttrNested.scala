package molecule.datomic.test.filterAttr

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._

object FilterAttrNested extends DatomicTestSuite {

  override lazy val tests = Tests {

    "Nested" - types { implicit conn =>
      for {
        _ <- Ns.s.i.Refs.*(Ref.int).insert(
          ("a", 1, List(2, 3)),
          ("b", 4, List(4, 5)),
          ("c", 7, List(5, 6)),
          ("d", 9, Nil),
        ).transact

        _ <- Ns.s.i(Ref.int_).Refs.*(Ref.int).query.get.map(_ ==> List(("b", 4, List(4))))

        _ <- Ns.s.i(Ref.int_).Refs.*(Ref.int(4)).query.get.map(_ ==> List(("b", 4, List(4))))
        _ <- Ns.s.i(Ref.int_).Refs.*(Ref.int.not(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ref.int_).Refs.*(Ref.int.>(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ref.int_).Refs.*(Ref.int.>=(4)).query.get.map(_ ==> List(("b", 4, List(4))))
        _ <- Ns.s.i(Ref.int_).Refs.*(Ref.int.<(4)).query.get.map(_ ==> List())
        _ <- Ns.s.i(Ref.int_).Refs.*(Ref.int.<=(4)).query.get.map(_ ==> List(("b", 4, List(4))))

        // Pointing backwards

        _ <- Ns.s.i.Refs.*(Ref.int(Ns.i_)).query.get.map(_ ==> List(("b", 4, List(4))))

        _ <- Ns.s.i(4).Refs.*(Ref.int(Ns.i_)).query.get.map(_ ==> List(("b", 4, List(4))))
        _ <- Ns.s.i.not(4).Refs.*(Ref.int(Ns.i_)).query.get.map(_ ==> List())
        _ <- Ns.s.i.>(4).Refs.*(Ref.int(Ns.i_)).query.get.map(_ ==> List())
        _ <- Ns.s.i.>=(4).Refs.*(Ref.int(Ns.i_)).query.get.map(_ ==> List(("b", 4, List(4))))
        _ <- Ns.s.i.<(4).Refs.*(Ref.int(Ns.i_)).query.get.map(_ ==> List())
        _ <- Ns.s.i.<=(4).Refs.*(Ref.int(Ns.i_)).query.get.map(_ ==> List(("b", 4, List(4))))
      } yield ()
    }


    "Optional nested" - types { implicit conn =>
      for {
        _ <- Ns.s.i(Ref.int_).Refs.*?(Ref.int).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed in optional nested data structure."
        }

        // Pointing backwards
        _ <- Ns.s.i.Refs.*?(Ref.int(Ns.i_)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed in optional nested data structure."
        }
      } yield ()
    }
  }
}
