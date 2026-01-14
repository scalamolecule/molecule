package molecule.db.compliance.test.pagination.subUnique

import scala.annotation.nowarn
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Uniques.*
import molecule.db.compliance.setup.DbProviders

@nowarn
case class DirectionsUniqueStandard(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // (Allow pattern matching the result without warnings)

  import api.*
  import suite.*

  "asc asc" - unique {
    val pairs               = (1 to 5).toList.map((_, scala.util.Random.nextInt(3) + 1))
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._2, p._1))
    val query               = (cursor: String, limit: Int) => Uniques.int.a2.i.a1.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.int.i.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }

  "desc asc" - unique {
    val pairs               = (1 to 5).toList.map((_, scala.util.Random.nextInt(3) + 1))
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._2, -p._1))
    val query               = (cursor: String, limit: Int) => Uniques.int.d2.i.a1.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.int.i.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }

  "asc desc" - unique {
    val pairs               = (1 to 5).toList.map((_, scala.util.Random.nextInt(3) + 1))
    val List(a, b, c, d, e) = pairs.sortBy(p => (-p._2, p._1))
    val query               = (cursor: String, limit: Int) => Uniques.int.a2.i.d1.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.int.i.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }

  "desc desc" - unique {
    val pairs               = (1 to 5).toList.map((_, scala.util.Random.nextInt(3) + 1))
    val List(a, b, c, d, e) = pairs.sortBy(p => (-p._2, -p._1))
    val query               = (cursor: String, limit: Int) => Uniques.int.d2.i.d1.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.int.i.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }
}