package molecule.db.datomic.test.ref.nested

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedRefs2 extends DatomicTestSuite {


  lazy val tests = Tests {

    "ref + opt attr" - refs { implicit conn =>
      m(Ns.str.Rs1 * R1.int1_?.R2.int2_?.str2).insert(List(
        ("a", List((Some(11), Some(12), "aa"))),
        ("b", List((Some(13), None, "bb"))),
        ("c", List((None, Some(14), "cc"))),
        ("d", List())
      )).transact

      m(Ns.str.Rs1 * R1.int1_?.R2.int2_?.str2).query.get ==> List(
        ("a", List((Some(11), Some(12), "aa"))),
        ("b", List((Some(13), None, "bb"))),
        ("c", List((None, Some(14), "cc"))),
      )
      m(Ns.str.Rs1 * R1.int1_?.R2.int2.str2).query.get ==> List(
        ("a", List((Some(11), 12, "aa"))),
        ("c", List((None, 14, "cc"))),
      )
      m(Ns.str.Rs1 * R1.int1.R2.int2_?.str2).query.get ==> List(
        ("a", List((11, Some(12), "aa"))),
        ("b", List((13, None, "bb"))),
      )
      m(Ns.str.Rs1 * R1.int1.R2.int2.str2).query.get ==> List(
        ("a", List((11, 12, "aa"))),
      )

      m(Ns.str.a1.Rs1 *? R1.int1_?.R2.int2_?.str2).query.get ==> List(
        ("a", List((Some(11), Some(12), "aa"))),
        ("b", List((Some(13), None, "bb"))),
        ("c", List((None, Some(14), "cc"))),
        ("d", List())
      )
      m(Ns.str.a1.Rs1 *? R1.int1_?.R2.int2.str2).query.get ==> List(
        ("a", List((Some(11), 12, "aa"))),
        ("b", List()),
        ("c", List((None, 14, "cc"))),
        ("d", List())
      )
      m(Ns.str.a1.Rs1 *? R1.int1.R2.int2_?.str2).query.get ==> List(
        ("a", List((11, Some(12), "aa"))),
        ("b", List((13, None, "bb"))),
        ("c", List()),
        ("d", List())
      )
      m(Ns.str.a1.Rs1 *? R1.int1.R2.int2.str2).query.get ==> List(
        ("a", List((11, 12, "aa"))),
        ("b", List()),
        ("c", List()),
        ("d", List())
      )
    }


    "ref + tacit attr" - refs { implicit conn =>
      m(Ns.str.Rs1 * R1.int1_?.R2.int2_?.str2).insert(List(
        ("a", List((Some(11), Some(12), "aa"))),
        ("b", List((Some(13), None, "bb"))),
        ("c", List((None, Some(14), "cc"))),
        ("d", List())
      )).transact

      m(Ns.str.Rs1 * R1.int1_?.R2.int2_.str2).query.get ==> List(
        ("a", List((Some(11), "aa"))),
        ("c", List((None, "cc"))),
      )
      m(Ns.str.Rs1 * R1.int1.R2.int2_.str2).query.get ==> List(
        ("a", List((11, "aa"))),
      )
      m(Ns.str.Rs1 * R1.int1_.R2.int2_?.str2).query.get ==> List(
        ("a", List((Some(12), "aa"))),
        ("b", List((None, "bb"))),
      )
      m(Ns.str.Rs1 * R1.int1_.R2.int2.str2).query.get ==> List(
        ("a", List((12, "aa"))),
      )
      m(Ns.str.Rs1 * R1.int1_.R2.int2_.str2).query.get ==> List(
        ("a", List("aa")),
      )

      m(Ns.str.a1.Rs1 *? R1.int1_?.R2.int2_.str2).query.get ==> List(
        ("a", List((Some(11), "aa"))),
        ("b", List()),
        ("c", List((None, "cc"))),
        ("d", List())
      )
      m(Ns.str.a1.Rs1 *? R1.int1.R2.int2_.str2).query.get ==> List(
        ("a", List((11, "aa"))),
        ("b", List()),
        ("c", List()),
        ("d", List())
      )
      m(Ns.str.a1.Rs1 *? R1.int1_.R2.int2_?.str2).query.get ==> List(
        ("a", List((Some(12), "aa"))),
        ("b", List((None, "bb"))),
        ("c", List()),
        ("d", List())
      )
      m(Ns.str.a1.Rs1 *? R1.int1_.R2.int2.str2).query.get ==> List(
        ("a", List((12, "aa"))),
        ("b", List()),
        ("c", List()),
        ("d", List())
      )
      m(Ns.str.a1.Rs1 *? R1.int1_.R2.int2_.str2).query.get ==> List(
        ("a", List("aa")),
        ("b", List()),
        ("c", List()),
        ("d", List())
      )
    }


    "Intermediate references without attributes" - refs { implicit conn =>
      m(Ns.str.Rs1 * R1.R2.int2.str2_?).insert(List(
        ("A", List((10, Some("a")), (20, None))),
        ("B", List())
      )).transact

      m(Ns.str.Rs1 * R1.R2.int2.str2_?).query.get ==> List(
        ("A", List((10, Some("a")), (20, None))),
      )
      m(Ns.str.Rs1 * R1.R2.int2.str2).query.get ==> List(
        ("A", List((10, "a"))),
      )
      m(Ns.str.Rs1 * R1.R2.int2.str2_).query.get ==> List(
        ("A", List(10)),
      )

      m(Ns.str.a1.Rs1 *? R1.R2.int2.str2_?).query.get ==> List(
        ("A", List((10, Some("a")), (20, None))),
        ("B", List())
      )
      m(Ns.str.a1.Rs1 *? R1.R2.int2.str2).query.get ==> List(
        ("A", List((10, "a"))),
        ("B", List())
      )
      m(Ns.str.a1.Rs1 *? R1.R2.int2.str2_).query.get ==> List(
        ("A", List(10)),
        ("B", List())
      )
    }


    "Flat card many refs" - refs { implicit conn =>
      Ns.str.Rs1.*(R1.int1.Rs2.*(R2.int2)).insert(List(
        ("a", List(
          (1, List(11, 12)),
          (2, List()),
        )),
        ("b", List())
      )).transact

      // Flat card many ref allowed in mandatory nested structure
      Ns.str.a1.Rs1.*(R1.int1.Rs2.int2.a1).query.get ==> List(
        ("a", List(
          (1, 11),
          (1, 12),
        ))
      )
      // Without sort markers and sorting on the returned result
      Ns.str.Rs1.*(R1.int1.Rs2.int2).query.get.map(p => (p._1, p._2.sorted)) ==> List(
        ("a", List(
          (1, 11),
          (1, 12),
        ))
      )

      //        // Flat card many refs not allowed in optional nested structure
      //        _ = expectCompileError("m(Ns.str.Rs1.*?(R1.int1.Rs2.int2))",
      //          "molecule.core.transform.exception.Dsl2ModelException: " +
      //            "Flat card many ref not allowed with optional nesting. " +
      //            """Found: Bond("Ref1", "refs2", "R2", 2, Seq())""")


      // Flat card many refs before nested structure
      Ns.str.Rs1.int1.Rs2.*(R2.int2).query.get ==> List(
        ("a", 1, List(11, 12))
      )
      Ns.str.Rs1.int1.Rs2.*?(R2.int2).query.get ==> List(
        ("a", 1, List(11, 12)),
        ("a", 2, List()),
      )

      // Implicit ref
      Ns.str.Rs1.Rs2.*?(R2.int2).query.get.sortBy(_._2.size) ==> List(
        ("a", List()),
        ("a", List(11, 12))
      )
      Ns.str.Rs1.Rs2.*(R2.int2).query.get ==> List(
        ("a", List(11, 12))
      )
    }


    "Back ref" - {

      "Nested" - refs { implicit conn =>
        m(Ns.str.R1.str1._Ns.Rs1 * R1.str1).insert(List(("book", "John", List("Marc")))).transact

        m(Ns.str.R1.str1._Ns.Rs1 * R1.str1).query.get ==> List(("book", "John", List("Marc")))
        m(Ns.str.R1.str1._Ns.Rs1.str1).query.get ==> List(("book", "John", "Marc"))
      }

      "Nested + adjacent" - refs { implicit conn =>
        m(Ns.str.R1.str1._Ns.Rs1 * R1.str1.Rs2.str2).insert(List(("book", "John", List(("Marc", "Musician"))))).transact

        m(Ns.str.R1.str1._Ns.Rs1 * R1.str1.Rs2.str2).query.get ==> List(("book", "John", List(("Marc", "Musician"))))
        m(Ns.str.R1.str1._Ns.Rs1.str1.Rs2.str2).query.get ==> List(("book", "John", "Marc", "Musician"))
      }

      "Nested + nested" - refs { implicit conn =>
        m(Ns.str.R1.str1._Ns.Rs1 * (R1.str1.Rs2 * R2.str2)).insert(List(("book", "John", List(("Marc", List("Musician")))))).transact

        m(Ns.str.R1.str1._Ns.Rs1 * (R1.str1.Rs2 * R2.str2)).query.get ==> List(("book", "John", List(("Marc", List("Musician")))))
        m(Ns.str.R1.str1._Ns.Rs1.str1.Rs2.str2).query.get ==> List(("book", "John", "Marc", "Musician"))
      }
    }


    "Flat ref before nested" - refs { implicit conn =>
      m(Ns.str.Rs1.int1_?.Rs2 * R2.int2).insert(List(
        ("a", Some(1), List(1)),
        ("b", None, List(2)),
        ("c", Some(3), List()),
        ("d", None, List()),
      )).transact

      m(Ns.str.Rs1.int1_?.Rs2 * R2.int2).query.get ==> List(
        ("a", Some(1), List(1)),
        ("b", None, List(2)),
      )
      m(Ns.str.Rs1.int1.Rs2 * R2.int2).query.get ==> List(
        ("a", 1, List(1)),
      )
      m(Ns.str.Rs1.int1_.Rs2 * R2.int2).query.get ==> List(
        ("a", List(1)),
      )
      m(Ns.str.Rs1.Rs2 * R2.int2).query.get ==> List(
        ("a", List(1)),
        ("b", List(2)),
      )

      m(Ns.str.a1.Rs1.int1_?.Rs2 *? R2.int2).query.get ==> List(
        ("a", Some(1), List(1)),
        ("b", None, List(2)),
        ("c", Some(3), List())
      )
      m(Ns.str.a1.Rs1.int1.Rs2 *? R2.int2).query.get ==> List(
        ("a", 1, List(1)),
        ("c", 3, List()),
      )
      m(Ns.str.a1.Rs1.int1_.Rs2 *? R2.int2).query.get ==> List(
        ("a", List(1)),
        ("c", List()),
      )
      m(Ns.str.a1.Rs1.Rs2 *? R2.int2).query.get ==> List(
        ("a", List(1)),
        ("b", List(2)),
        ("c", List()),
      )
    }


    "Opt data before nested" - refs { implicit conn =>
      m(Ns.str_?.Rs1.int1.Rs2 * R2.int2).insert(List(
        (Some("a"), 10, List(1, 2)),
        (Some("b"), 20, List(3)),
        (None, 30, List()),
        (Some("d"), 40, List()),
      )).transact

      m(Ns.str_?.Rs1.int1.a1.Rs2 *? R2.int2).query.get ==> List(
        (Some("a"), 10, List(1, 2)),
        (Some("b"), 20, List(3)),
        (None, 30, List()),
        (Some("d"), 40, List())
      )
      // Without sort marker and sorting on the returned result
      m(Ns.str_?.Rs1.int1.Rs2 *? R2.int2).query.get.sortBy(_._2) ==> List(
        (Some("a"), 10, List(1, 2)),
        (Some("b"), 20, List(3)),
        (None, 30, List()),
        (Some("d"), 40, List())
      )
    }


    "Implicit initial namespace" - refs { implicit conn =>
      val List(ref1a, _, _, _, _, _) = R1.str1.Rs2.*(R2.str2).insert(List(
        ("r1a", List("r2a", "r2b")),
        ("r1b", List("r2c", "r2d")) // <-- will not be referenced from Ns
      )).transact.eids

      // Both Ns entities reference the same Ref1 entity
      Ns.str.rs1.insert(List(
        ("a", Set(ref1a)),
        ("b", Set(ref1a))
      )).transact

      // Without Ns
      R1.str1.Rs2.*(R2.str2).query.get ==> List(
        ("r1a", List("r2a", "r2b")),
        ("r1b", List("r2c", "r2d"))
      )

      // With Ns
      // "Implicit" reference from Ns to Ref1 (without any attributes) implies that
      // some Ns entity is referencing some Ref1 entity.
      // This excludes "r1b" since no Ns entities reference it.
      Ns.Rs1.str1.Rs2.*(R2.str2).query.get ==> List(
        ("r1a", List("r2a", "r2b"))
      )
    }

    "Applied eid" - refs { implicit conn =>
      val eid = Ns.str.Rs1.*(R1.int1).insert("a", List(1, 2)).transact.eids.head
      Ns(eid).str.query.get.head ==> "a"
      Ns(eid).Rs1.*(R1.int1).query.get.head ==> List(1, 2)
    }
  }
}
