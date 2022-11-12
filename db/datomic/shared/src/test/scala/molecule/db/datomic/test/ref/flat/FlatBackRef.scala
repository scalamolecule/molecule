package molecule.db.datomic.test.ref.flat

import molecule.coreTests.dataModels.core.ref.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object FlatBackRef extends DatomicTestSuite {


  lazy val tests = Tests {

    "BackRef insert" - refs { implicit conn =>
        Ns.str.Ref1.str1._Ns.Refs1.int1.insert(List(
          ("a", "a1", 1),
          ("b", "b1", 2))).transact

        Ns.str.Ref1.str1._Ns.Refs1.int1.query.get ==> List(
          ("a", "a1", 1),
          ("b", "b1", 2)
        )

        Ns.str.Refs1.int1._Ns.Ref1.str1.query.get ==> List(
          ("a", 1, "a1"),
          ("b", 2, "b1")
        )
    }


    "BackRef save" - refs { implicit conn =>
        Ns.n(0).Ref1.n1(11)._Ns.Refs1.n1(12).save.transact

        Ns.n.Ref1.n1._Ns.Refs1.n1.query.get ==> List((0, 11, 12))
    }


//    "BackRef, 2 levels" - refs { implicit conn =>
//        Ns.str.Ref1.str1.Ref2.str2._Ref1._Ns.Refs1.int1 insert List(
//          ("a", "a1", "a2", 1),
//          ("b", "b1", "b2", 2))
//
//        Ns.str.Ref1.str1.Ref2.str2._Ref1._Ns.Refs1.int1.get.map(_ ==> List(
//          ("a", "a1", "a2", 1),
//          ("b", "b1", "b2", 2)))
//    }
//
//
//    "Back ref, Adjacent" - refs { implicit conn =>
//        m(Ns.str.Ref1.str1._Ns.Refs1.str1) insert List(("book", "John", "Marc"))
//        m(Ns.str.Ref1.str1._Ns.Refs1.str1).get.map(_ ==> List(("book", "John", "Marc")))
//        m(Ns.str.Ref1.str1._Ns.Refs1 * Ref1.str1).get.map(_ ==> List(("book", "John", List("Marc"))))
//    }
//
//
//    "Ns with only ref" - refs { implicit conn =>
//        // Relationship with Ns having a normal attribute value (for str)
//        Ns.str.Ref1.int1 insert List(
//          ("a", 1),
//          ("b", 2))
//
//        // Ref1 value without Ns -> Ref1 relationship
//        Ref1.int1(3).save
//
//        // Values where Ns -> Ref1 relationship is not guaranteed
//        Ref1.int1.get.map(_ ==> List(1, 2, 3))
//
//        // Values where Ns -> Ref1 relationship is guaranteed
//        Ns.Ref1.int1.get.map(_ ==> List(1, 2))
//
//        // Adding ref value where Ns -> Ref1 relationship is estabished too
//        Ns.Ref1.int1(4).save
//
//        // Values where Ns -> Ref1 relationship is not guaranteed
//        Ref1.int1.get.map(_ ==> List(1, 2, 3, 4))
//
//        // Values where Ns -> Ref1 relationship is guaranteed
//        Ns.Ref1.int1.get.map(_ ==> List(1, 2, 4))
//
//        // Values where :Ns/str is asserted and Ns -> Ref1 relationship is guaranteed
//        Ns.str_.Ref1.int1.get.map(_ ==> List(1, 2))
//    }





    //    "Aggregates one" - refs { implicit conn =>
    //      for {
    //        m(Ns.str.ref1) insert List(
    //          ("a", 1L),
    //          ("b", 2L))
    //
    //        m(Ns.str.ref1(count)).get.map(_ ==> List(
    //          ("a", 1),
    //          ("b", 1)))
    //      } yield ()
    //    }
    //
    //
    //    "Aggregates many" - refs { implicit conn =>
    //      for {
    //        m(Ns.str.refs1) insert List(
    //          ("a", Set(1L)),
    //          ("b", Set(2L, 3L)))
    //
    //        m(Ns.str.refs1(count)).get.map(_ ==> List(
    //          ("a", 1),
    //          ("b", 2)))
    //      } yield ()
    //    }


//    "Self-refs" - refs { implicit conn =>
//        // OBS: not considered "Self-joins" in this context
//        m(Ns.str.Parent.str) insert List(("child", "parent"))
//        m(Ns.str.Parent.str).get.map(_ ==> List(("child", "parent")))
//
//        m(Ns.str.Parents * Ns.str) insert List(("child", List("parent1", "parent2")))
//        m(Ns.str.Parents * Ns.str).get.map(_ ==> List(("child", List("parent1", "parent2"))))
//        m(Ns.str.Parents.str).get.map(_ ==> List(("child", "parent1"), ("child", "parent2")))
//    }


//    "Many attribute + ref" - refs { implicit conn =>
//      for {
//        m(Ns.str.Refs1.*(Ref1.int1)) insert List(("a", List(1, 2)))
//
//        _ = expectCompileError("m(Ns.str.refs1.Refs1.int1)",
//          "molecule.refs.ops.exception.VerifyRawModelException: " +
//            "Instead of getting the ref id with `refs1` please get it via the referenced namespace: `Refs1.e ...`")
//
//        _ = expectCompileError("m(Ns.refs1.str.Refs1.int1)",
//          "molecule.refs.ops.exception.VerifyRawModelException: " +
//            "Instead of getting the ref id with `refs1` please get it via the referenced namespace: `Refs1.e ...`")
//      } yield ()
//    }


  }
}
