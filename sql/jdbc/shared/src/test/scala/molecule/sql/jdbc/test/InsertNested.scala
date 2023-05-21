//package molecule.sql.jdbc.test
//
//import molecule.coreTests.dataModels.core.dsl.Refs._
//import molecule.sql.jdbc.setup.JdbcTestSuite
//import molecule.sql.jdbc.sync._
//import utest._
//import scala.language.implicitConversions
//
//object InsertNested extends JdbcTestSuite {
//
//  override lazy val tests = Tests {
//
//    "backref" - refs { implicit conn =>
//      A.s.Bb.*(B.i).insert(
//        ("a", List(1, 2)),
//        ("b", List(3, 4)),
//      )
//
//      A.i.s.B.i.s.Cc.i._B.C.i.s._B._A.Bb.i.insert(
//        (0, "a", 1, "b", 22, 2, "c", 11),
//        (1, "a", 1, "b", 22, 2, "c", 11),
//      ).transact
//
//      A.i.s.B.i.s.Cc.i._B.C.i.s._B._A.Bb.i.query.get ==> List(
//        (0, "a", 1, "b", 22, 2, "c", 11),
//        (1, "a", 1, "b", 22, 2, "c", 11),
//      )
//    }
//  }
//}
