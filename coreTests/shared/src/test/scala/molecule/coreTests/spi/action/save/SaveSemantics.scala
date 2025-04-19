package molecule.coreTests.spi.action.save

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Refs.*
import molecule.coreTests.setup.*

case class SaveSemantics(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Attribute required for each entity" - refs { implicit conn =>
    for {
      _ <- A.B.i(1).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please add at least 1 attribute to entity A before relating to B"
        }

      _ <- A.Bb.i(1).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please add at least 1 attribute to entity A before relating to Bb"
        }
    } yield ()
  }


  "Duplicate attributes not allowed, flat - Same entity" - refs { implicit conn =>
    for {
      _ <- A.i(1).i(2).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute A.i"
        }
    } yield ()
  }

  "Duplicate attributes not allowed, flat - After backref" - refs { implicit conn =>
    for {
      _ <- A.i(1).B.i(2)._A.i(3).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute A.i"
        }

      _ <- A.i(1).B.i(2).C.i(3)._B.i(4).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute B.i"
        }

      _ <- A.i(1).B.i(2).C.i(3)._B._A.i(4).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute A.i"
        }
    } yield ()
  }


  "Nested data can only be inserted, not saved" - refs { implicit conn =>
    for {
      _ <- A.i(0).Bb.*(B.i(1)).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Nested data structure not allowed in save molecule. " +
            "Please use insert instead."
        }

      _ <- A.i(0).Bb.*?(B.i(1)).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Optional nested data structure not allowed in save molecule. " +
            "Please use insert instead."
        }

      // ok
      _ <- A.i.Bb.*(B.i).insert(0, List(1)).transact
      _ <- A.i.Bb.*?(B.i).insert(0, List(1)).transact
    } yield ()
  }
}
