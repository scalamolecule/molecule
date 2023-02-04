//package molecule.db.datomic.test.pagination.cursor.primaryUnique
//
//import molecule.core.util.Executor._
//import molecule.coreTests.dataModels.core.dsl.Unique._
//import molecule.db.datomic._
//import molecule.db.datomic.setup.DatomicTestSuite
//import utest._
//import scala.annotation.nowarn
//
//object Nested extends DatomicTestSuite {
//  val x     = ""
//  val a     = (1, List(1))
//  val b     = (2, List(1))
//  val c     = (3, List(1))
//  val d     = (4, List(1))
//  val e     = (5, List(1))
//  val data  = List(a, b, c, d, e)
//  val query = Unique.int.a1.Refs.*(Ref.i).query
//
//  // (Allow pattern matching the result without warnings)
//  @nowarn lazy val tests = Tests {
//
//    "Unique primary sort" - {
//
//      "From start" - unique { implicit conn =>
//        for {
//          // Using attribute Unique.int with only unique values
//          _ <- Unique.int.Refs.*(Ref.i).insert(data).transact
//
//          // Sorted by unique int attribute
//          c1 <- query.from(x).limit(2).get.map { case (List(`a`, `b`), cur, true) => cur }
//          c2 <- query.from(c1).limit(2).get.map { case (List(`c`, `d`), cur, true) => cur }
//          c3 <- query.from(c2).limit(2).get.map { case (List(`e`), cur, false) => cur }
//          c2 <- query.from(c3).limit(-2).get.map { case (List(`c`, `d`), cur, true) => cur }
//          _ <- query.from(c2).limit(-2).get.map { case (List(`a`, `b`), _, false) => () }
//        } yield ()
//      }
//
//      "From end" - unique { implicit conn =>
//        for {
//          _ <- Unique.int.insert(1, 2, 3, 4, 5).transact
//
//          c1 <- query.from(x).limit(-2).get.map { case (List(`d`, `e`), cur, true) => cur }
//          c2 <- query.from(c1).limit(-2).get.map { case (List(`b`, `c`), cur, true) => cur }
//          c3 <- query.from(c2).limit(-2).get.map { case (List(`a`), cur, false) => cur }
//          c2 <- query.from(c3).limit(2).get.map { case (List(`b`, `c`), cur, true) => cur }
//          _ <- query.from(c2).limit(2).get.map { case (List(`d`, `e`), _, false) => () }
//        } yield ()
//      }
//    }
//  }
//}