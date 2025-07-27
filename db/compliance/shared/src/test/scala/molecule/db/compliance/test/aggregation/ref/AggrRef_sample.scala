package molecule.db.compliance.test.aggregation.ref

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality

case class AggrRef_sample(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "ref" - refs {
    val all = Set(1, 2, 3, 4)
    for {
      _ <- A.i.B.i.insert(List(
        (1, 1),
        (1, 2),
        (1, 3),
      )).transact

      _ <- A.B.i(sample).query.get.map(res => all.contains(res.head) ==> true)
    } yield ()
  }


  "2nd ref" - refs {
    val all = Set(1, 2, 3, 4)
    for {
      _ <- A.i.B.i.C.i.insert(List(
        (1, 1, 1),
        (1, 1, 2),
        (1, 1, 3),
      )).transact

      _ <- A.B.C.i(sample).query.get.map(res => all.contains(res.head) ==> true)
    } yield ()
  }


  "multiple refs" - refs {
    val all = Set(1, 2, 3, 4)
    for {
      _ <- A.i.B.i.C.i.insert(List(
        (1, 1, 1),
        (1, 2, 2),
        (1, 3, 3),
      )).transact

      _ <- A.B.i(sample).C.i(sample).query.get.map { res =>
        all.contains(res.head._1) ==> true
        all.contains(res.head._2) ==> true
      }
    } yield ()
  }


  "backref" - refs {
    val all = Set(1, 2, 3, 4)
    for {
      _ <- A.i.B.i._A.C.i.insert(List(
        (1, 1, 1),
        (1, 2, 2),
        (1, 3, 3),
      )).transact

      _ <- A.B.i(sample)._A.C.i(sample).query.get.map { res =>
        all.contains(res.head._1) ==> true
        all.contains(res.head._2) ==> true
      }
    } yield ()
  }
}