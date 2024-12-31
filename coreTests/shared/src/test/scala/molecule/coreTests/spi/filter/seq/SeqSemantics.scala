package molecule.coreTests.spi.filter.seq

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class SeqSemantics(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "equal" - types { implicit conn =>
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


  "equal nothing" - types { implicit conn =>
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