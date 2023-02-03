//package molecule.db.datomic.test.pagination.cursor
//
//import molecule.core.util.Executor._
//import molecule.coreTests.dataModels.core.dsl.Unique._
//import molecule.db.datomic._
//import molecule.db.datomic.setup.DatomicTestSuite
//import utest._
//import scala.annotation.nowarn
//
//object Cursor extends DatomicTestSuite {
//
//  // (Allow pattern matching the result without warnings)
//  @nowarn lazy val tests = Tests {
//
//        "Time" - {
//
//          "AsOf" - types { implicit conn =>
//            for {
//              t <- Unique.int.insert(1, 2, 3, 4, 5).map(_.t)
//              _ <- Unique.int(6).save
//
//              _ <- Unique.int.getAsOf(t).map(_ ==> List(1, 2, 3, 4, 5))
//
//              c <- Unique.int.a1.getAsOf(t, 2, x).map { case (List(1, 2), c, 3) => c }
//              c <- Unique.int.a1.getAsOf(t, 2, c).map { case (List(3, 4), c, 1) => c }
//              c <- Unique.int.a1.getAsOf(t, 2, c).map { case (List(5), c, 0) => c }
//              c <- Unique.int.a1.getAsOf(t, -2, c).map { case (List(3, 4), c, 2) => c }
//              _ <- Unique.int.a1.getAsOf(t, -2, c).map { case (List(1, 2), _, 0) => () }
//            } yield ()
//          }
//
//          "Since" - types { implicit conn =>
//            for {
//              t <- Unique.int(6).save.map(_.t)
//              _ <- Unique.int.insert(1, 2, 3, 4, 5)
//
//              _ <- Unique.int.getSince(t).map(_ ==> List(1, 2, 3, 4, 5))
//
//              c <- Unique.int.a1.getSince(t, 2, x).map { case (List(1, 2), c, 3) => c }
//              c <- Unique.int.a1.getSince(t, 2, c).map { case (List(3, 4), c, 1) => c }
//              c <- Unique.int.a1.getSince(t, 2, c).map { case (List(5), c, 0) => c }
//              c <- Unique.int.a1.getSince(t, -2, c).map { case (List(3, 4), c, 2) => c }
//              _ <- Unique.int.a1.getSince(t, -2, c).map { case (List(1, 2), _, 0) => () }
//            } yield ()
//          }
//
//          "With" - types { implicit conn =>
//            for {
//              _ <- Unique.int.insert(1,2,3)
//
//              moreData = Unique.int.getInsertStmts(4, 5)
//              _ <- Unique.int.getWith(moreData).map(_ ==> List(1, 2, 3, 4, 5))
//
//              c <- Unique.int.a1.getWith(2, x, moreData).map { case (List(1, 2), c, 3) => c }
//              c <- Unique.int.a1.getWith(2, c, moreData).map { case (List(3, 4), c, 1) => c }
//              c <- Unique.int.a1.getWith(2, c, moreData).map { case (List(5), c, 0) => c }
//              c <- Unique.int.a1.getWith(-2, c, moreData).map { case (List(3, 4), c, 2) => c }
//              _ <- Unique.int.a1.getWith(-2, c, moreData).map { case (List(1, 2), _, 0) => () }
//            } yield ()
//          }
//
//          // Pagination for getHistory is not implemented.
//        }
//
//
//        "Sort value types" - types { implicit conn =>
//          for {
//            _ <- Unique.string.int.long.ref1.double.bool.date.uuid.uri.bigInt.bigDec.enumm insert List(
//              ("a", 1, 1L, 1L, 1.1, true, date1, uuid1, uri1, bigInt1, bigDec1, enum1),
//              ("b", 2, 2L, 2L, 2.2, false, date2, uuid2, uri2, bigInt2, bigDec2, enum2),
//              ("c", 3, 3L, 3L, 3.3, false, date3, uuid3, uri3, bigInt3, bigDec3, enum3)
//            )
//
//            c <- Unique.string.a1.query.get(2, x).map { case (List("a", "b"), c, 1) => c }
//            _ <- Unique.string.a1.query.get(2, c).map { case (List("c"), _, 0) => () }
//
//            c <- Unique.int.a1.query.get(2, x).map { case (List(1, 2), c, 1) => c }
//            _ <- Unique.int.a1.query.get(2, c).map { case (List(3), _, 0) => () }
//
//            c <- Unique.long.a1.query.get(2, x).map { case (List(1L, 2L), c, 1) => c }
//            _ <- Unique.long.a1.query.get(2, c).map { case (List(3L), _, 0) => () }
//
//            c <- Unique.ref.a1.query.get(2, x).map { case (List(1L, 2L), c, 1) => c }
//            _ <- Unique.ref.a1.query.get(2, c).map { case (List(3L), _, 0) => () }
//
//            c <- Unique.double.a1.query.get(2, x).map { case (List(1.1, 2.2), c, 1) => c }
//            _ <- Unique.double.a1.query.get(2, c).map { case (List(3.3), _, 0) => () }
//
//            c <- Unique.boolean.a1.query.get(1, x).map { case (List(false), c, 1) => c }
//            _ <- Unique.boolean.a1.query.get(1, c).map { case (List(true), _, 0) => () }
//
//            c <- Unique.date.a1.query.get(2, x).map { case (List(`date1`, `date2`), c, 1) => c }
//            _ <- Unique.date.a1.query.get(2, c).map { case (List(`date3`), _, 0) => () }
//
//            c <- Unique.uuid.a1.query.get(2, x).map { case (List(`uuid1`, `uuid2`), c, 1) => c }
//            _ <- Unique.uuid.a1.query.get(2, c).map { case (List(`uuid3`), _, 0) => () }
//
//            c <- Unique.uri.a1.query.get(2, x).map { case (List(`uri1`, `uri2`), c, 1) => c }
//            _ <- Unique.uri.a1.query.get(2, c).map { case (List(`uri3`), _, 0) => () }
//
//            c <- Unique.bigInt.a1.query.get(2, x).map { case (List(`bigInt1`, `bigInt2`), c, 1) => c }
//            _ <- Unique.bigInt.a1.query.get(2, c).map { case (List(`bigInt3`), _, 0) => () }
//
//            c <- Unique.bigDecimal.a1.query.get(2, x).map { case (List(`bigDec1`, `bigDec2`), c, 1) => c }
//            _ <- Unique.bigDecimal.a1.query.get(2, c).map { case (List(`bigDec3`), _, 0) => () }
//          } yield ()
//        }
//
//
//        "Expressions" - types { implicit conn =>
//          for {
//            _ <- Unique.int.insert(0, 1, 2, 3, 4, 5)
//            c <- Unique.int.>(0).a1.query.get(2, x).map { case (List(1, 2), c, 3) => c }
//            c <- Unique.int.>(0).a1.query.get(2, c).map { case (List(3, 4), c, 1) => c }
//            c <- Unique.int.>(0).a1.query.get(2, c).map { case (List(5), c, 0) => c }
//            c <- Unique.int.>(0).a1.query.get(-2, c).map { case (List(3, 4), c, 2) => c }
//            _ <- Unique.int.>(0).a1.query.get(-2, c).map { case (List(1, 2), _, 0) => () }
//          } yield ()
//        }
//
//
//
//        "Forward" - {
//
//          "Add data" - {
//
//            "Add rows (outside same sort value window)" - types { implicit conn =>
//              for {
//                _ <- Unique.int.insert(1, 2, 3)
//
//                c1 <- Unique.int.a1.query.get(2, x).map { case (List(1, 2), c, 1) => c }
//
//                // Current last page
//                c2 <- Unique.int.a1.query.get(2, c1).map { case (List(3), c, 0) => c }
//
//                // New data will show on new page
//                // (regardless if it could have fitted on the old page)
//                _ <- Unique.int(4).save
//                c3 <- Unique.int.a1.query.get(2, c2).map { case (List(4), c, 0) => c }
//
//                // Adding data beyond next page
//                _ <- Unique.int.insert(5, 6, 7)
//                c4 <- Unique.int.a1.query.get(2, c3).map { case (List(5, 6), c, 1) => c }
//                _ <- Unique.int.a1.query.get(2, c4).map { case (List(7), _, 0) => () }
//              } yield ()
//            }
//
//            "Add row after cursor within window" - types { implicit conn =>
//              for {
//                // Window of rows with same sort value (1)
//                _ <- Unique.string.int insert List(
//                  ("a", 1),
//                  ("b", 1),
//                  ("c", 1),
//                )
//
//                // Order of non-sorted attributes is non-deterministic
//                // (but will likely not vary in-between internal indexing jobs)
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("b", 1),
//                  ("a", 1), // cursor row
//                  ("c", 1),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(2, x).map { case (List(("b", 1), ("a", 1)), c, 1) => c }
//
//                _ <- Unique.str("d").int(1).save
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("b", 1),
//                  ("a", 1), // cursor row
//                  ("d", 1), // added row after cursor row
//                  ("c", 1),
//                ))
//
//                // Page 2 including new bb row since it's after cursor row
//                _ <- Unique.string.int.a1.query.get(2, c).map { case (List(("d", 1), ("c", 1)), _, 0) => () }
//              } yield ()
//            }
//
//
//            "Add row before cursor within window" - types { implicit conn =>
//              for {
//                _ <- Unique.string.int insert List(
//                  ("a", 1),
//                  ("bb", 1),
//                  ("c", 1),
//                )
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("a", 1),
//                  ("bb", 1),
//                  ("c", 1),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(2, x).map { case (List(("a", 1), ("bb", 1)), c, 1) => c }
//
//                _ <- Unique.str("ba").int(1).save
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("a", 1),
//                  ("ba", 1), // added row before cursor row
//                  ("bb", 1), // cursor row
//                  ("c", 1),
//                ))
//
//                // Page 2 excluding new ba row since it's before cursor row
//                _ <- Unique.string.int.a1.query.get(2, c).map { case (List(("c", 1)), _, 0) => () }
//              } yield ()
//            }
//          }
//
//
//          "Retract non-cursor row" - {
//
//            "outside page" - types { implicit conn =>
//              for {
//                a1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("y", 2),
//                ) map (_.eids(1))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1), // cursor row
//                  ("a", 1), // outside page 1
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(2, x).map { case (List(("x", 0), ("b", 1)), c, 2) => c }
//
//                _ <- a1.retract
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1), // cursor row
//                  ("y", 2),
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(2, c).map { case (List(("y", 2)), _, 0) => () }
//              } yield ()
//            }
//
//            "inside page" - types { implicit conn =>
//              for {
//                b1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("y", 2),
//                ) map (_.eids(2))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1), // inside page 1
//                  ("a", 1), // cursor row
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(3, x).map { case (List(("x", 0), ("b", 1), ("a", 1)), c, 1) => c }
//
//                _ <- b1.retract
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("a", 1), // cursor row
//                  ("y", 2),
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(3, c).map { case (List(("y", 2)), _, 0) => () }
//              } yield ()
//            }
//          }
//
//
//          "Retract cursor row, first in window" - {
//
//            "Retraction only" - types { implicit conn =>
//              for {
//                b1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("y", 2),
//                ) map (_.eids(2))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1), // cursor row first in window
//                  ("a", 1),
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(2, x).map { case (List(("x", 0), ("b", 1)), c, 2) => c }
//
//                _ <- b1.retract
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0), // substitute cursor row (first before b on previous page)
//                  ("a", 1),
//                  ("y", 2),
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(2, c).map { case (List(("a", 1), ("y", 2)), _, 0) => () }
//              } yield ()
//            }
//
//            "Retraction + added data" - types { implicit conn =>
//              for {
//                b1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("y", 2),
//                ) map (_.eids(2))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1), // cursor row first in window
//                  ("a", 1),
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(2, x).map { case (List(("x", 0), ("b", 1)), c, 2) => c }
//
//                _ <- b1.retract
//                // Add data in window
//                _ <- Unique.str("c").int(1).save
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0), // substitute cursor row (first before b on previous page)
//                  ("a", 1),
//                  ("c", 1),
//                  ("y", 2),
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(2, c).map { case (List(("a", 1), ("c", 1)), _, 1) => () }
//              } yield ()
//            }
//          }
//
//
//          "Retract cursor row, last in window" - {
//
//            "Retraction only" - types { implicit conn =>
//              for {
//                a1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("y", 2),
//                ) map (_.eids(1))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1),
//                  ("a", 1), // cursor row last in window
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(3, x).map { case (List(("x", 0), ("b", 1), ("a", 1)), c, 1) => c }
//
//                _ <- a1.retract
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1), // substitute cursor row (first before a on previous page)
//                  ("y", 2),
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(3, c).map { case (List(("y", 2)), _, 0) => () }
//              } yield ()
//            }
//
//            "Retraction + added data" - types { implicit conn =>
//              for {
//                a1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("y", 2),
//                ) map (_.eids(1))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1),
//                  ("a", 1), // cursor row last in window
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(3, x).map { case (List(("x", 0), ("b", 1), ("a", 1)), c, 1) => c }
//
//                _ <- a1.retract
//                // Add data in window
//                _ <- Unique.str("c").int(1).save
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1), // substitute cursor row (first before a on previous page)
//                  ("c", 1),
//                  ("y", 2),
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(3, c).map { case (List(("c", 1), ("y", 2)), _, 0) => () }
//              } yield ()
//            }
//          }
//
//
//          "Retract cursor row, inside window" - {
//
//            "Retraction only" - types { implicit conn =>
//              for {
//                a1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("c", 1),
//                  ("y", 2),
//                ) map (_.eids(1))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1),
//                  ("a", 1), // cursor row
//                  ("c", 1),
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(3, x).map { case (List(("x", 0), ("b", 1), ("a", 1)), c, 2) => c }
//
//                _ <- a1.retract
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1), // substitute cursor row (first before a on previous page)
//                  ("c", 1),
//                  ("y", 2),
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(3, c).map { case (List(("c", 1), ("y", 2)), _, 0) => () }
//              } yield ()
//            }
//
//            "Retraction + added data" - types { implicit conn =>
//              for {
//                a1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("c", 1),
//                  ("y", 2),
//                ) map (_.eids(1))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1),
//                  ("a", 1), // cursor row
//                  ("c", 1),
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(3, x).map { case (List(("x", 0), ("b", 1), ("a", 1)), c, 2) => c }
//
//                _ <- a1.retract
//                // Add data in window
//                _ <- Unique.str("d").int(1).save
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1), // substitute cursor row (first before a on previous page)
//                  ("d", 1),
//                  ("c", 1),
//                  ("y", 2),
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(3, c).map { case (List(("d", 1), ("c", 1), ("y", 2)), _, 0) => () }
//              } yield ()
//            }
//          }
//        }
//
//
//        "Backward" - {
//
//          "Add data" - {
//
//            "Add rows (outside same sort value window)" - types { implicit conn =>
//              for {
//                _ <- Unique.int.insert(5, 6, 7)
//
//                c1 <- Unique.int.a1.query.get(-2, x).map { case (List(6, 7), c, 1) => c }
//
//                // Current last page
//                c2 <- Unique.int.a1.query.get(-2, c1).map { case (List(5), c, 0) => c }
//
//                // New data will show on new page
//                // (regardless if it could have fitted on the old page)
//                _ <- Unique.int(4).save
//                c3 <- Unique.int.a1.query.get(-2, c2).map { case (List(4), c, 0) => c }
//
//                // Adding data beyond next page
//                _ <- Unique.int.insert(1, 2, 3)
//                c4 <- Unique.int.a1.query.get(-2, c3).map { case (List(2, 3), c, 1) => c }
//                _ <- Unique.int.a1.query.get(-2, c4).map { case (List(1), _, 0) => () }
//              } yield ()
//            }
//
//            "Add row after cursor within window" - types { implicit conn =>
//              for {
//                // Window of rows with same sort value (1)
//                _ <- Unique.string.int insert List(
//                  ("a", 1),
//                  ("ba", 1),
//                  ("c", 1),
//                )
//
//                // Order of non-sorted attributes is non-deterministic
//                // (but will likely not vary in-between internal indexing jobs)
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("a", 1),
//                  ("ba", 1),
//                  ("c", 1),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(-2, x).map { case (List(("ba", 1), ("c", 1)), c, 1) => c }
//
//                _ <- Unique.str("bb").int(1).save
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("a", 1),
//                  ("ba", 1), // cursor row
//                  ("bb", 1), // added row after cursor row
//                  ("c", 1),
//                ))
//
//                // Page 2 excluding new bb row since it's after cursor row and we are going backwards
//                _ <- Unique.string.int.a1.query.get(-2, c).map { case (List(("a", 1)), _, 0) => () }
//              } yield ()
//            }
//
//
//            "Add row before cursor within window" - types { implicit conn =>
//              for {
//                _ <- Unique.string.int insert List(
//                  ("a", 1),
//                  ("bb", 1),
//                  ("c", 1),
//                )
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("a", 1),
//                  ("bb", 1),
//                  ("c", 1),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(-2, x).map { case (List(("bb", 1), ("c", 1)), c, 1) => c }
//
//                _ <- Unique.str("ba").int(1).save
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("a", 1),
//                  ("ba", 1), // added row before cursor row
//                  ("bb", 1), // cursor row
//                  ("c", 1),
//                ))
//
//                // Page 2 including new ba row since it's before cursor row and we go backwards
//                _ <- Unique.string.int.a1.query.get(-2, c).map { case (List(("a", 1), ("ba", 1)), _, 0) => () }
//              } yield ()
//            }
//          }
//
//
//          "Retract non-cursor row" - {
//
//            "outside page" - types { implicit conn =>
//              for {
//                b1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("y", 2),
//                ) map (_.eids(2))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1), // outside page 1
//                  ("a", 1), // cursor row
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(-2, x).map { case (List(("a", 1), ("y", 2)), c, 2) => c }
//
//                _ <- b1.retract
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("a", 1), // cursor row
//                  ("y", 2),
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(-2, c).map { case (List(("x", 0)), _, 0) => () }
//              } yield ()
//            }
//
//            "inside page" - types { implicit conn =>
//              for {
//                a1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("y", 2),
//                ) map (_.eids(1))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1), // cursor row
//                  ("a", 1), // inside page 1
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(-3, x).map { case (List(("b", 1), ("a", 1), ("y", 2)), c, 1) => c }
//
//                _ <- a1.retract
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1), // cursor row
//                  ("y", 2),
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(-3, c).map { case (List(("x", 0)), _, 0) => () }
//              } yield ()
//            }
//          }
//
//
//          "Retract cursor row, first in window" - {
//
//            "Retraction only" - types { implicit conn =>
//              for {
//                b1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("y", 2),
//                ) map (_.eids(2))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1), // cursor row first in window
//                  ("a", 1),
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(-3, x).map { case (List(("b", 1), ("a", 1), ("y", 2)), c, 1) => c }
//
//                _ <- b1.retract
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("a", 1), // substitute cursor row (first after b on previous page)
//                  ("y", 2),
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(-3, c).map { case (List(("x", 0)), _, 0) => () }
//              } yield ()
//            }
//
//            "Retraction + added data" - types { implicit conn =>
//              for {
//                b1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("y", 2),
//                ) map (_.eids(2))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1), // cursor row first in window
//                  ("a", 1),
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(-3, x).map { case (List(("b", 1), ("a", 1), ("y", 2)), c, 1) => c }
//
//                _ <- b1.retract
//                // Add data in window
//                _ <- Unique.str("c").int(1).save
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("a", 1), // substitute cursor row (first after b on previous page)
//                  ("c", 1),
//                  ("y", 2),
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(-3, c).map { case (List(("x", 0)), _, 0) => () }
//              } yield ()
//            }
//          }
//
//
//          "Retract cursor row, last in window" - {
//
//            "Retraction only" - types { implicit conn =>
//              for {
//                a1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("y", 2),
//                ) map (_.eids(1))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1),
//                  ("a", 1),  // cursor row last in window
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(-2, x).map { case (List(("a", 1), ("y", 2)), c, 2) => c }
//
//                _ <- a1.retract
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1),
//                  ("y", 2), // substitute cursor row (first after a on previous page)
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(-2, c).map { case (List(("x", 0), ("b", 1)), _, 0) => () }
//              } yield ()
//            }
//
//            "Retraction + added data" - types { implicit conn =>
//              for {
//                a1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("y", 2),
//                ) map (_.eids(1))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1),
//                  ("a", 1), // cursor row last in window
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(-2, x).map { case (List(("a", 1), ("y", 2)), c, 2) => c }
//
//                _ <- a1.retract
//                // Add data in window
//                _ <- Unique.str("c").int(1).save
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1),
//                  ("c", 1),
//                  ("y", 2), // substitute cursor row (first after a on previous page)
//                ))
//
//                // Page 2-3
//                c <- Unique.string.int.a1.query.get(-2, c).map { case (List(("b", 1), ("c", 1)), c, 1) => c }
//                _ <- Unique.string.int.a1.query.get(-2, c).map { case (List(("x", 0)), _, 0) => () }
//              } yield ()
//            }
//          }
//
//
//          "Retract cursor row, inside window" - {
//
//            "Retraction only" - types { implicit conn =>
//              for {
//                a1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("c", 1),
//                  ("y", 2),
//                ) map (_.eids(1))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1),
//                  ("a", 1), // cursor row
//                  ("c", 1),
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(-3, x).map { case (List(("a", 1), ("c", 1), ("y", 2)), c, 2) => c }
//
//                _ <- a1.retract
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1),
//                  ("c", 1), // substitute cursor row (first after a on previous page)
//                  ("y", 2),
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(-3, c).map { case (List(("x", 0), ("b", 1)), _, 0) => () }
//              } yield ()
//            }
//
//            "Retraction + added data" - types { implicit conn =>
//              for {
//                a1 <- Unique.string.int insert List(
//                  ("x", 0),
//                  ("a", 1),
//                  ("b", 1),
//                  ("c", 1),
//                  ("y", 2),
//                ) map (_.eids(1))
//
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1),
//                  ("a", 1), // cursor row
//                  ("c", 1),
//                  ("y", 2),
//                ))
//
//                // Page 1
//                c <- Unique.string.int.a1.query.get(-3, x).map { case (List(("a", 1), ("c", 1), ("y", 2)), c, 2) => c }
//
//                _ <- a1.retract
//                // Add data in window
//                _ <- Unique.str("d").int(1).save
//                _ <- Unique.string.int.a1.get.map(_ ==> List(
//                  ("x", 0),
//                  ("b", 1),
//                  ("d", 1),
//                  ("c", 1), // substitute cursor row (first after a on previous page)
//                  ("y", 2),
//                ))
//
//                // Page 2
//                _ <- Unique.string.int.a1.query.get(-3, c).map { case (List(("x", 0), ("b", 1), ("d", 1)), _, 0) => () }
//              } yield ()
//            }
//          }
//        }
//  }
//}