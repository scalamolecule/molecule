package molecule.db.compliance.test.pagination.subUnique

import scala.annotation.nowarn
import scala.util.Random
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Uniques.*
import molecule.db.compliance.setup.DbProviders

@nowarn
case class DirectionsStandardUnique(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "asc asc" - unique {
    val pairs               = (1 to 5).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.int.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.int.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }

  "desc asc" - unique {
    val pairs               = (1 to 5).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d, e) = pairs.sortBy(p => (-p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.d1.int.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.int.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }

  "asc desc" - unique {
    val pairs               = (1 to 5).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, -p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.int.d2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.int.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }

  "desc desc" - unique {
    val pairs               = (1 to 5).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d, e) = pairs.sortBy(p => (-p._1, -p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.d1.int.d2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.int.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }
}