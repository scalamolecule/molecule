package molecule.db.compliance.test.relation

import molecule.base.error.ModelError
import molecule.core.setup.MUnit
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*


case class DatomicNoOptionals(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends Arity23 {

  import api.*
  import suite.*


  // Optional entities not implemented for Datomic.
  // Doesn't seem viable in Datalog.

  "Optional entity" - refs { implicit conn =>
    for {
      _ <- A.?(A.i).B.i.insert(List((None, 1))).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Optional entity not implemented for Datomic."
        }

      _ <- A.?(A.i).B.i.a1.query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Optional entity not implemented for Datomic."
        }
    } yield ()
  }

  "Optional ref" - refs { implicit conn =>
    for {
      _ <- A.i.B.?(B.i).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Optional ref not implemented for Datomic."
        }
    } yield ()
  }

  "Optional nested" - refs { implicit conn =>
    for {
      _ <- A.s.a1.Bb.*?(B.i).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Optional nested not implemented for Datomic."
        }
    } yield ()
  }

}
