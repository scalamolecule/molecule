package molecule.db.datomic.test.ref.flat

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object FlatBasic extends DatomicTestSuite {


  lazy val tests = Tests {

    "card one" - refs { implicit conn =>
      // Two entities created: one for Ns.n and one for referenced R1.n1
      val List(a, b) = Ns.n(0).R1.n1(1).save.transact.eids

      // Retrieve connected data
      Ns.n.R1.n1.query.get ==> List((0, 1))

      // Retrieve entity ids too
      Ns.e.n.R1.e.n1.query.get ==> List((a, 0, b, 1))
      // or separately
      Ns.e.n.query.get ==> List((a, 0))
      R1.e.n1.query.get ==> List((b, 1))

      // Get referenced entity id by accessing lowercase ref attribute name
      Ns.n.r1.query.get ==> List((0, b))
    }


    "card many" - refs { implicit conn =>
      // "Flat" molecules have same semantics for cardinality many references
      val List(a, b) = Ns.n(0).Rs1.n1(1).save.transact.eids

      Ns.n.Rs1.n1.query.get ==> List((0, 1))
      Ns.e.n.Rs1.e.n1.query.get ==> List((a, 0, b, 1))
      Ns.e.n.query.get ==> List((a, 0))
      R1.e.n1.query.get ==> List((b, 1))

      // Only difference is that cardinality many ref attributes return Set of ref ids
      Ns.n.rs1.query.get ==> List((0, Set(b)))
    }


    "multiple card-many refs" - refs { implicit conn =>
      // Two entities to be referenced
      val List(b1, b2) = R1.n1.insert(1, 2).transact.eids

      // Rerence Set of entities
      Ns.n(0).rs1(Set(b1, b2)).save.transact

      // Saving individual ref ids (not in a Set) is not allowed
      moleculeException(
        Ns.n(0).rs1(b1, b2).save.transact
      )(
        "Can only save one Set of values per Set attribute. Found: " +
          s"AttrSetManLong(Ns,refs1,Appl,ArraySeq(Set($b1), Set($b2)),None,None,None)"
      )

      // Rerencing namespace attributes repeat for each referenced entity
      Ns.n.Rs1.n1.query.get ==> List(
        (0, 1),
        (0, 2), // 0 is repeated
      )

      // Card many ref attributes return Set of ref ids
      Ns.n.rs1.query.get ==> List((0, Set(b1, b2)))
    }


    "Adjacent ref without attribute" - refs { implicit conn =>
      Ns.str.R1.int1.R2.int2.insert(List(
        ("a", 11, 12),
        ("b", 21, 22)
      )).transact

      // We can jump multiple namespaces without having to retrieve intermediary attributes
      Ns.str.R1.R2.int2.query.get ==> List(
        ("b", 22),
        ("a", 12)
      )
    }


    //    "Molecule has to end with attribute" - {
    //      "Ending with ref" - refs { implicit conn =>
    //        expectCompileError("m(Ns.str.R1)",
    //          "molecule.rs.ops.exception.VerifyRawModelException: " +
    //            "Molecule not allowed to end with a reference. Please add one or more attribute to the reference.")
    //      }
    //
    //      "Ending with refs" - refs { implicit conn =>
    //        expectCompileError("m(Ns.str.Rs1)",
    //          "molecule.rs.ops.exception.VerifyRawModelException: " +
    //            "Molecule not allowed to end with a reference. Please add one or more attribute to the reference.")
    //      }
    //    }
  }
}
