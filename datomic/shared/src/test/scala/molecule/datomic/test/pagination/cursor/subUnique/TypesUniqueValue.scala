package molecule.datomic.test.pagination.cursor.subUnique

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._
import scala.annotation.nowarn
import scala.util.Random

object TypesUniqueValue extends DatomicTestSuite {

  @nowarn lazy val tests = Tests {

    "String" - unique { implicit conn =>
      val pairs               = List(
        (Random.between(1, 3), string1),
        (Random.between(1, 3), string2),
        (Random.between(1, 3), string3),
        (Random.between(1, 3), string4),
        (Random.between(1, 3), string5),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Unique.i.a1.string.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.string.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Int" - unique { implicit conn =>
      val pairs               = List(
        (Random.between(1, 3), int1),
        (Random.between(1, 3), int2),
        (Random.between(1, 3), int3),
        (Random.between(1, 3), int4),
        (Random.between(1, 3), int5),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Unique.i.a1.int.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.int.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Long" - unique { implicit conn =>
      val pairs               = List(
        (Random.between(1, 3), long1),
        (Random.between(1, 3), long2),
        (Random.between(1, 3), long3),
        (Random.between(1, 3), long4),
        (Random.between(1, 3), long5),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Unique.i.a1.long.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.long.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Float" - unique { implicit conn =>
      val pairs               = List(
        (Random.between(1, 3), float1),
        (Random.between(1, 3), float2),
        (Random.between(1, 3), float3),
        (Random.between(1, 3), float4),
        (Random.between(1, 3), float5),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Unique.i.a1.float.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.float.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Double" - unique { implicit conn =>
      val pairs               = List(
        (Random.between(1, 3), double1),
        (Random.between(1, 3), double2),
        (Random.between(1, 3), double3),
        (Random.between(1, 3), double4),
        (Random.between(1, 3), double5),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Unique.i.a1.double.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.double.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Boolean" - unique { implicit conn =>
      // Can save only 2 unique boolean values
      val pairs      = List(
        (Random.between(1, 3), boolean1),
        (Random.between(1, 3), boolean2),
      )
      val List(a, b) = pairs.sortBy(p => (p._1, p._2))
      val query      = (cursor: String, limit: Int) => Unique.i.a1.boolean.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.boolean.insert(pairs).transact
        c1 <- query("", 1).get.map { case (List(`a`), cursor, true) => cursor }
        c2 <- query(c1, 1).get.map { case (List(`b`), cursor, false) => cursor }
        _ <- query(c2, -1).get.map { case (List(`a`), _, false) => () }
      } yield ()
    }

    "BigInt" - unique { implicit conn =>
      val pairs               = List(
        (Random.between(1, 3), bigInt1),
        (Random.between(1, 3), bigInt2),
        (Random.between(1, 3), bigInt3),
        (Random.between(1, 3), bigInt4),
        (Random.between(1, 3), bigInt5),
      )
      val pairsx              = List(
        (1, bigInt1),
        (1, bigInt3),
        (1, bigInt5),
        (2, bigInt2),
        (2, bigInt4),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Unique.i.a1.bigInt.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.bigInt.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "BigDecimal" - unique { implicit conn =>
      val pairs               = List(
        (Random.between(1, 3), bigDecimal1),
        (Random.between(1, 3), bigDecimal2),
        (Random.between(1, 3), bigDecimal3),
        (Random.between(1, 3), bigDecimal4),
        (Random.between(1, 3), bigDecimal5),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Unique.i.a1.bigDecimal.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.bigDecimal.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Date" - unique { implicit conn =>
      val pairs               = List(
        (Random.between(1, 3), date1),
        (Random.between(1, 3), date2),
        (Random.between(1, 3), date3),
        (Random.between(1, 3), date4),
        (Random.between(1, 3), date5),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Unique.i.a1.date.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.date.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Uuid" - unique { implicit conn =>
      val pairs               = List(
        (Random.between(1, 3), uuid1),
        (Random.between(1, 3), uuid2),
        (Random.between(1, 3), uuid3),
        (Random.between(1, 3), uuid4),
        (Random.between(1, 3), uuid5),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Unique.i.a1.uuid.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.uuid.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Uri" - unique { implicit conn =>
      val pairs               = List(
        (Random.between(1, 3), uri1),
        (Random.between(1, 3), uri2),
        (Random.between(1, 3), uri3),
        (Random.between(1, 3), uri4),
        (Random.between(1, 3), uri5),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Unique.i.a1.uri.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.uri.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Byte" - unique { implicit conn =>
      val pairs               = List(
        (Random.between(1, 3), byte1),
        (Random.between(1, 3), byte2),
        (Random.between(1, 3), byte3),
        (Random.between(1, 3), byte4),
        (Random.between(1, 3), byte5),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Unique.i.a1.byte.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.byte.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Short" - unique { implicit conn =>
      val pairs               = List(
        (Random.between(1, 3), short1),
        (Random.between(1, 3), short2),
        (Random.between(1, 3), short3),
        (Random.between(1, 3), short4),
        (Random.between(1, 3), short5),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Unique.i.a1.short.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.short.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Char" - unique { implicit conn =>
      val pairs               = List(
        (Random.between(1, 3), char1),
        (Random.between(1, 3), char2),
        (Random.between(1, 3), char3),
        (Random.between(1, 3), char4),
        (Random.between(1, 3), char5),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Unique.i.a1.char.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.char.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }
  }
}
