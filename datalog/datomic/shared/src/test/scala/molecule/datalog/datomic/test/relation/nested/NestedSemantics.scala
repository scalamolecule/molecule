package molecule.datalog.datomic.test.relation.nested

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._


object NestedSemantics extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Nested namespace must match" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R4.i).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "`Rs1` can only nest to `R1`. Found: `R4`"
        }

        _ <- Ns.i.Rs1.*?(R4.i).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "`Rs1` can only nest to `R1`. Found: `R4`"
        }
      } yield ()
    }


    "Mixing *?/* not allowed" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.Rs2.*?(R2.i)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't mix mandatory/optional nested data structures."
        }

        _ <- Ns.i.Rs1.*?(R1.i.Rs2.*(R2.i)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't mix mandatory/optional nested data structures."
        }
      } yield ()
    }
  }
}