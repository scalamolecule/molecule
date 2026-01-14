package molecule.db.compliance.test.pagination.cursor.noUnique

import scala.annotation.{nowarn, tailrec}
import scala.util.Random
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

@nowarn
case class DirectionsOptional(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  @tailrec
  final def getPairs(acc: List[(Int, Option[Int])]): List[(Int, Option[Int])] = {
    if (acc.length == 5) {
      acc
    } else {
      val pair = (
        Random.nextInt(3) + 1,
        if (Random.nextInt(3) + 1 == 1) Some(Random.nextInt(6) + 1) else None
      )
      // No duplicate rows
      if (!acc.contains(pair)) getPairs(acc :+ pair) else getPairs(acc)
    }
  }

  import api.*
  import suite.*

  "asc asc" - types {
    val pairs               = getPairs(Nil)
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Entity.i.a1.int_?.a2.query.from(cursor).limit(limit)
    for {
      _ <- Entity.i.int_?.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }

  "desc asc" - types {
    val pairs               = getPairs(Nil)
    val List(a, b, c, d, e) = pairs.sortBy(p => (-p._1, p._2))
    val query               = (cursor: String, limit: Int) => Entity.i.d1.int_?.a2.query.from(cursor).limit(limit)
    for {
      _ <- Entity.i.int_?.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }

  "asc desc" - types {
    val pairs               = getPairs(Nil)
    val List(a, b, c, d, e) = pairs
      .sortBy(p => (p._1, p._2))(using Ordering.Tuple2(using Ordering.Int, Ordering.Option[Int].reverse))
    val query               = (cursor: String, limit: Int) => Entity.i.a1.int_?.d2.query.from(cursor).limit(limit)
    for {
      _ <- Entity.i.int_?.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }

  "desc desc" - types {
    val pairs               = getPairs(Nil)
    val List(a, b, c, d, e) = pairs
      .sortBy(p => (-p._1, p._2))(using Ordering.Tuple2(using Ordering.Int, Ordering.Option[Int].reverse))
    val query               = (cursor: String, limit: Int) => Entity.i.d1.int_?.d2.query.from(cursor).limit(limit)
    for {
      _ <- Entity.i.int_?.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }
}