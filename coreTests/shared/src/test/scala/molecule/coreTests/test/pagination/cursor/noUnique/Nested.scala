package molecule.coreTests.test.pagination.cursor.noUnique

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.annotation.{nowarn, tailrec}
import scala.util.Random

trait Nested extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  @tailrec
  final def getData(acc: List[(Int, Int, List[Int])]): List[(Int, Int, List[Int])] = {
    if (acc.length != 5) {
      val pair = (Random.nextInt(3) + 1, Random.nextInt(6) + 1, List(1))
      // No duplicate rows
      if (!acc.contains(pair)) getData(acc :+ pair) else getData(acc)
    } else {
      acc
    }
  }

  @nowarn lazy val tests = Tests {

    "Forward, asc asc" - types { implicit conn =>
      val pairs               = getData(Nil)
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Ns.i.a1.int.a2.Refs.*(Ref.i).query.from(cursor).limit(limit)
      for {
        _ <- Ns.i.int.Refs.*(Ref.i).insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Forward, desc asc" - types { implicit conn =>
      val pairs               = getData(Nil)
      val List(a, b, c, d, e) = pairs.sortBy(p => (-p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Ns.i.d1.int.a2.Refs.*(Ref.i).query.from(cursor).limit(limit)
      for {
        _ <- Ns.i.int.Refs.*(Ref.i).insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Forward, asc desc" - types { implicit conn =>
      val pairs               = getData(Nil)
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, -p._2))
      val query               = (cursor: String, limit: Int) =>
        Ns.i.a1.int.d2.Refs.*(Ref.i).query.from(cursor).limit(limit)
      for {
        _ <- Ns.i.int.Refs.*(Ref.i).insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Forward, desc desc" - types { implicit conn =>
      val pairs               = getData(Nil)
      val List(a, b, c, d, e) = pairs.sortBy(p => (-p._1, -p._2))
      val query               = (cursor: String, limit: Int) =>
        Ns.i.d1.int.d2.Refs.*(Ref.i).query.from(cursor).limit(limit)
      for {
        _ <- Ns.i.int.Refs.*(Ref.i).insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }


    "Backwards, asc asc" - types { implicit conn =>
      val pairs               = getData(Nil)
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Ns.i.a1.int.a2.Refs.*(Ref.i).query.from(cursor).limit(limit)
      for {
        _ <- Ns.i.int.Refs.*(Ref.i).insert(pairs).transact
        c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
        c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
        c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
      } yield ()
    }

    "Backwards, desc asc" - types { implicit conn =>
      val pairs               = getData(Nil)
      val List(a, b, c, d, e) = pairs.sortBy(p => (-p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Ns.i.d1.int.a2.Refs.*(Ref.i).query.from(cursor).limit(limit)
      for {
        _ <- Ns.i.int.Refs.*(Ref.i).insert(pairs).transact
        c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
        c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
        c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
      } yield ()
    }

    "Backwards, asc desc" - types { implicit conn =>
      val pairs               = getData(Nil)
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, -p._2))
      val query               = (cursor: String, limit: Int) =>
        Ns.i.a1.int.d2.Refs.*(Ref.i).query.from(cursor).limit(limit)
      for {
        _ <- Ns.i.int.Refs.*(Ref.i).insert(pairs).transact
        c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
        c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
        c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
      } yield ()
    }

    "Backwards, desc desc" - types { implicit conn =>
      val pairs               = getData(Nil)
      val List(a, b, c, d, e) = pairs.sortBy(p => (-p._1, -p._2))
      val query               = (cursor: String, limit: Int) =>
        Ns.i.d1.int.d2.Refs.*(Ref.i).query.from(cursor).limit(limit)
      for {
        _ <- Ns.i.int.Refs.*(Ref.i).insert(pairs).transact
        c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
        c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
        c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
      } yield ()
    }
  }
}