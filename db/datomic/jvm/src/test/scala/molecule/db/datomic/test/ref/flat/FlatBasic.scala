package molecule.db.datomic.test.ref.flat

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object FlatBasic extends DatomicTestSuite {


  lazy val tests = Tests {

    "card one" - refs { implicit conn =>
      // Two entities created: one for Ns.i and one for referenced R1.i
      val List(a, b) = Ns.i(0).R1.i(1).save.transact.eids

      // Retrieve connected data
      Ns.i.R1.i.query.get ==> List((0, 1))

      // Retrieve entity ids too
      Ns.e.i.R1.e.i.query.get ==> List((a, 0, b, 1))
      // or separately
      Ns.e.i.query.get ==> List((a, 0))
      R1.e.i.query.get ==> List((b, 1))

      // Get referenced entity id by accessing lowercase ref attribute name
      Ns.i.r1.query.get ==> List((0, b))
    }


    "card many" - refs { implicit conn =>
      // "Flat" molecules have same semantics for cardinality many references
      val List(a, b) = Ns.i(0).Rs1.i(1).save.transact.eids

      Ns.i.Rs1.i.query.get ==> List((0, 1))
      Ns.e.i.Rs1.e.i.query.get ==> List((a, 0, b, 1))
      Ns.e.i.query.get ==> List((a, 0))
      R1.e.i.query.get ==> List((b, 1))

      // Only difference is that cardinality many ref attributes return Set of ref ids
      Ns.i.rs1.query.get ==> List((0, Set(b)))
    }


    "multiple card-many refs" - refs { implicit conn =>
      // Two entities to be referenced
      val List(b1, b2) = R1.i.insert(1, 2).transact.eids

      // Rerence Set of entities
      Ns.i(0).rs1(Set(b1, b2)).save.transact

      // Saving individual ref ids (not in a Set) is not allowed
      moleculeException(
        Ns.i(0).rs1(b1, b2).save.transact
      )(
        "Can only save one Set of values for Set attribute `:Ns/rs1`. Found: " +
          s"ArraySeq(Set($b1), Set($b2))"
      )

      // Rerencing namespace attributes repeat for each referenced entity
      Ns.i.Rs1.i.query.get ==> List(
        (0, 1),
        (0, 2), // 0 is repeated
      )

      // Card many ref attributes return Set of ref ids
      Ns.i.rs1.query.get ==> List((0, Set(b1, b2)))
    }


    "Adjacent ref without attribute" - refs { implicit conn =>
      Ns.s.R1.i.R2.i.insert(List(
        ("a", 11, 12),
        ("b", 21, 22)
      )).transact

      // We can jump multiple namespaces without having to retrieve intermediary attributes
      Ns.s.R1.R2.i.query.get ==> List(
        ("b", 22),
        ("a", 12)
      )
    }


    //    "Molecule has to end with attribute" - {
    //      "Ending with ref" - refs { implicit conn =>
    //        expectCompileError("m(Ns.s.R1)",
    //          "molecule.rs.ops.exception.VerifyRawModelException: " +
    //            "Molecule not allowed to end with a reference. Please add one or more attribute to the reference.")
    //      }
    //
    //      "Ending with refs" - refs { implicit conn =>
    //        expectCompileError("m(Ns.s.Rs1)",
    //          "molecule.rs.ops.exception.VerifyRawModelException: " +
    //            "Molecule not allowed to end with a reference. Please add one or more attribute to the reference.")
    //      }
    //    }
  }
}
