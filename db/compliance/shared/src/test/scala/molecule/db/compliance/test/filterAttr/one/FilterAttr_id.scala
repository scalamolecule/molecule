package molecule.db.compliance.test.filterAttr.one

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class FilterAttr_id(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // Can't use entity ids with filter attributes

  import api.*
  import suite.*

  "equal (apply)" - types {
    for {
      _ <- Entity.s.id(Entity.long_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }

      _ <- Entity.s.long(Entity.id_).query.get
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


  "not equal" - types {
    for {
      _ <- Entity.s.id.not(Entity.long_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }

      _ <- Entity.s.long.not(Entity.id_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }
    } yield ()
  }


  "<" - types {
    for {
      _ <- Entity.s.id.<(Entity.long_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }

      _ <- Entity.s.long.<(Entity.id_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }
    } yield ()
  }


  "<=" - types {
    for {
      _ <- Entity.s.id.<=(Entity.long_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }

      _ <- Entity.s.long.<=(Entity.id_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }
    } yield ()
  }


  ">" - types {
    for {
      _ <- Entity.s.id.>(Entity.long_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }

      _ <- Entity.s.long.>(Entity.id_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }
    } yield ()
  }


  ">=" - types {
    for {
      _ <- Entity.s.id.>=(Entity.long_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }

      _ <- Entity.s.long.>=(Entity.id_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed to involve entity ids."
        }
    } yield ()
  }
}
