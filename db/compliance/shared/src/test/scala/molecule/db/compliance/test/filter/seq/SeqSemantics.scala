package molecule.db.compliance.test.filter.seq

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class SeqSemantics(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "equal" - types {
    for {
      _ <- Entity.i.intSeq(Seq(int1)).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Matching collections (Entity.intSeq) not supported in queries."
        }

      _ <- Entity.i.intSeq_(Seq(int1)).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Matching collections (Entity.intSeq) not supported in queries."
        }

      _ <- Entity.i.intSeq_?(Some(Seq(int1))).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Matching collections (Entity.intSeq) not supported in queries."
        }
    } yield ()
  }


  "equal nothing" - types {
    for {
      _ <- Entity.i.intSeq_?.insert(List(
        (0, None),
        (1, Some(Seq(int1, int2))),
      )).transact

      // Match non-asserted attribute (null) with tacit attribute
      _ <- Entity.i.intSeq_().query.get.map(_ ==> List(0))

      // Can't query for empty attribute
      _ <- Entity.i.intSeq().query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Applying nothing to mandatory attribute (Entity.intSeq) is reserved for updates to retract."
        }
    } yield ()
  }
}