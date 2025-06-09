package molecule.db.compliance.test.filterAttr.one

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterAttr_id(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Can't use entity ids with filter attributes

  import api.*
  import suite.*

  "equal (apply)" - types { implicit conn =>
    for {
      _ <- Entity.s.id(Entity.long).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }

      _ <- Entity.s.long(Entity.id).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }

      // Cross reference filter attributes not allowed either
      _ <- Entity.s.long(Ref.id_).Ref.id.query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }
      _ <- Entity.s.long_(Ref.id_).Ref.id.query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }
    } yield ()
  }


  "not equal" - types { implicit conn =>
    for {
      _ <- Entity.s.id.not(Entity.long).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }

      _ <- Entity.s.long.not(Entity.id).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }
    } yield ()
  }


  "<" - types { implicit conn =>
    for {
      _ <- Entity.s.id.<(Entity.long).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }

      _ <- Entity.s.long.<(Entity.id).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }
    } yield ()
  }


  "<=" - types { implicit conn =>
    for {
      _ <- Entity.s.id.<=(Entity.long).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }

      _ <- Entity.s.long.<=(Entity.id).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }
    } yield ()
  }


  ">" - types { implicit conn =>
    for {
      _ <- Entity.s.id.>(Entity.long).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }

      _ <- Entity.s.long.>(Entity.id).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }
    } yield ()
  }


  ">=" - types { implicit conn =>
    for {
      _ <- Entity.s.id.>=(Entity.long).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }

      _ <- Entity.s.long.>=(Entity.id).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }
    } yield ()
  }
}
