package molecule.db.datomic.test.ref.flat

import molecule.coreTests.dataModels.core.ref.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object FlatBasic extends DatomicTestSuite {


  lazy val tests = Tests {

    "card one" - refs { implicit conn =>
      // Two entities created: one for Ns.n and one for referenced Ref1.n1
      val List(a, b) = Ns.n(0).Ref1.n1(1).save.transact.eids

      // Retrieve connected data
      Ns.n.Ref1.n1.query.get ==> List((0, 1))

      // Retrieve entity ids too
      Ns.e.n.Ref1.e.n1.query.get ==> List((a, 0, b, 1))
      // or separately
      Ns.e.n.query.get ==> List((a, 0))
      Ref1.e.n1.query.get ==> List((b, 1))

      // Get referenced entity id by accessing lowercase ref attribute name
      Ns.n.ref1.query.get ==> List((0, b))
    }


    "card many" - refs { implicit conn =>
      // "Flat" molecules have same semantics for cardinality many references
      val List(a, b) = Ns.n(0).Refs1.n1(1).save.transact.eids

      Ns.n.Refs1.n1.query.get ==> List((0, 1))
      Ns.e.n.Refs1.e.n1.query.get ==> List((a, 0, b, 1))
      Ns.e.n.query.get ==> List((a, 0))
      Ref1.e.n1.query.get ==> List((b, 1))

      // Only difference is that cardinality many ref attributes return Set of ref ids
      Ns.n.refs1.query.get ==> List((0, Set(b)))
    }


    "multiple card-many refs" - refs { implicit conn =>
      // Two entities to be referenced
      val List(b1, b2) = Ref1.n1.insert(1, 2).transact.eids

      // Reference Set of entities
      Ns.n(0).refs1(Set(b1, b2)).save.transact

      // Saving individual ref ids (not in a Set) is not allowed
      moleculeException(
        Ns.n(0).refs1(b1, b2).save.transact
      )(
        "Can only save one Set of values per Set attribute. Found: " +
          s"AttrSetManLong(Ns,refs1,Appl,ArraySeq(Set($b1), Set($b2)),None,None,None)"
      )

      // Referencing namespace attributes repeat for each referenced entity
      Ns.n.Refs1.n1.query.get ==> List(
        (0, 1),
        (0, 2), // 0 is repeated
      )

      // Card many ref attributes return Set of ref ids
      Ns.n.refs1.query.get ==> List((0, Set(b1, b2)))
    }


    "Adjacent ref without attribute" - refs { implicit conn =>
      Ns.str.Ref1.int1.Ref2.int2.insert(List(
        ("a", 11, 12),
        ("b", 21, 22)
      )).transact

      // We can jump multiple namespaces without having to retrieve intermediary attributes
      Ns.str.Ref1.Ref2.int2.query.get ==> List(
        ("b", 22),
        ("a", 12)
      )
    }


    //    "Molecule has to end with attribute" - {
    //      "Ending with ref" - refs { implicit conn =>
    //        expectCompileError("m(Ns.str.Ref1)",
    //          "molecule.refs.ops.exception.VerifyRawModelException: " +
    //            "Molecule not allowed to end with a reference. Please add one or more attribute to the reference.")
    //      }
    //
    //      "Ending with refs" - refs { implicit conn =>
    //        expectCompileError("m(Ns.str.Refs1)",
    //          "molecule.refs.ops.exception.VerifyRawModelException: " +
    //            "Molecule not allowed to end with a reference. Please add one or more attribute to the reference.")
    //      }
    //    }
  }
}
