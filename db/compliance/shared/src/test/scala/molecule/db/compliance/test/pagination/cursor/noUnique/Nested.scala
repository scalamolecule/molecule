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
case class Nested(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  @tailrec
  final def getData(acc: List[(Int, Int, List[Int])]): List[(Int, Int, List[Int])] = {
    if (acc.length == 5) {
      acc
    } else {
      val pair = (Random.nextInt(3) + 1, Random.nextInt(6) + 1, List(1))
      // No duplicate rows
      if (!acc.contains(pair)) getData(acc :+ pair) else getData(acc)
    }
  }

  import api.*
  import suite.*

  "asc asc" - types {
    val pairs               = getData(Nil)
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) =>
      Entity.i.a1.int.a2.Refs.*(Ref.i).query.from(cursor).limit(limit)
    for {
      _ <- Entity.i.int.Refs.*(Ref.i).insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }

  "desc asc" - types {
    val pairs               = getData(Nil)
    val List(a, b, c, d, e) = pairs.sortBy(p => (-p._1, p._2))
    val query               = (cursor: String, limit: Int) =>
      Entity.i.d1.int.a2.Refs.*(Ref.i).query.from(cursor).limit(limit)
    for {
      _ <- Entity.i.int.Refs.*(Ref.i).insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }

  "asc desc" - types {
    val pairs               = getData(Nil)
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, -p._2))
    val query               = (cursor: String, limit: Int) =>
      Entity.i.a1.int.d2.Refs.*(Ref.i).query.from(cursor).limit(limit)
    for {
      _ <- Entity.i.int.Refs.*(Ref.i).insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }

  "desc desc" - types {
    val pairs               = getData(Nil)
    val List(a, b, c, d, e) = pairs.sortBy(p => (-p._1, -p._2))
    val query               = (cursor: String, limit: Int) =>
      Entity.i.d1.int.d2.Refs.*(Ref.i).query.from(cursor).limit(limit)
    for {
      _ <- Entity.i.int.Refs.*(Ref.i).insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
    } yield ()
  }
}