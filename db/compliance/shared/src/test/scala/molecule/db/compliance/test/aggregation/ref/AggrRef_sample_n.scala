package molecule.db.compliance.test.aggregation.ref

import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class AggrRef_sample_n(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "ref" - refs { implicit conn =>
    val all = Set(1, 2, 3, 4)
    for {
      _ <- A.i.B.i.insert(List(
        (1, 1),
        (1, 2),
        (1, 3),
      )).transact

      _ <- A.B.i(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "2nd ref" - refs { implicit conn =>
    val all = Set(1, 2, 3, 4)
    for {
      _ <- A.i.B.i.C.i.insert(List(
        (1, 1, 1),
        (1, 1, 2),
        (1, 1, 3),
      )).transact

      _ <- A.B.C.i(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "multiple refs" - refs { implicit conn =>
    val all = Set(1, 2, 3, 4)
    for {
      _ <- A.i.B.i.C.i.insert(List(
        (1, 1, 1),
        (1, 2, 2),
        (1, 3, 3),
      )).transact

      _ <- A.B.i(sample(2)).C.i(sample(2)).query.get.map { res =>
        all.intersect(res.head._1).nonEmpty ==> true
        all.intersect(res.head._2).nonEmpty ==> true
      }
    } yield ()
  }


  "backref" - refs { implicit conn =>
    val all = Set(1, 2, 3, 4)
    for {
      _ <- A.i.B.i._A.C.i.insert(List(
        (1, 1, 1),
        (1, 2, 2),
        (1, 3, 3),
      )).transact

      _ <- A.B.i(sample(2))._A.C.i(sample(2)).query.get.map { res =>
        all.intersect(res.head._1).nonEmpty ==> true
        all.intersect(res.head._2).nonEmpty ==> true
      }
    } yield ()
  }
}