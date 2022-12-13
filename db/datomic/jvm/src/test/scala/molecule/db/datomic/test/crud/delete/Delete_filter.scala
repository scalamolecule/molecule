package molecule.db.datomic.test.crud.delete

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Delete_filter extends DatomicTestSuite {


  lazy val tests = Tests {

    "Filter by 1 non-ns value" - types { implicit conn =>
      Ns.i.insert(1, 2).transact
      Ns.i_(1).delete.transact
      Ns.i.query.get ==> List(2)
    }


    "Filter by multiple non-ns value" - types { implicit conn =>
      val List(e1, e2, e3) = Ns.i.insert(1, 2, 2).transact.eids
      Ns.e.a1.i.query.get ==> List(
        (e1, 1),
        (e2, 2),
        (e3, 2),
      )

      Ns.i_(2).delete.multiple.transact
      Ns.e.i.query.get ==> List(
        (e1, 1),
      )
    }


    "Expression" - types { implicit conn =>
      Ns.i.insert(1, 2, 3).transact

      // Update all entities where non-ns attribute i <= 2
      Ns.i_.<=(2).delete.multiple.transact

      Ns.i.query.get ==> List(3)
    }

    "Multiple expressions" - types { implicit conn =>
      Ns.i.s.insert(
        (1, "a"),
        (2, "b"),
        (3, "c"),
      ).transact

      Ns.i.s.query.get ==> List(
        (1, "a"),
        (2, "b"),
        (3, "c"),
      )

      // Update all entities where non-ns attribute i > 1 and s < "c"
      Ns.i_.>(1).s_.<("c").delete.multiple.transact

      Ns.i.s.query.get ==> List(
        (1, "a"),
        (3, "c"),
      )
    }


    "Ref" - types { implicit conn =>
      Ns.i.insert(1).transact
      Ns.i.Ref.i.insert((2, 20), (3, 30)).transact

      Ns.i.query.get ==> List(1, 2, 3)
      Ns.i.Ref.i.query.get ==> List((2, 20), (3, 30))

      // Nothing deleted since entity 1 doesn't have a ref
      Ns.i_(1).Ref.i_.delete.transact
      Ns.i.query.get ==> List(1, 2, 3)

      // Second entity has a ref and will be deleted
      Ns.i_(2).Ref.i_.delete.transact
      Ns.i.query.get ==> List(1, 3)
      Ns.i.Ref.i.query.get ==> List((3, 30))

      // Note that Ref.int entity is a separate entity and is not deleted.
      // Only the entity of the initial namespace is deleted
      Ref.i.query.get ==> List(20, 30)

      // Ns.i entity has no ref to Ref.i_(42) so nothing is deleted
      Ns.i_.Ref.i_(42).delete.transact
      Ns.i.query.get ==> List(1, 3)
      Ns.i.Ref.i.query.get ==> List((3, 30))

      // Ns.i entity has a ref to Ref.i_(30) so it will be deleted
      Ns.i_.Ref.i_(30).delete.transact
      Ns.i.query.get ==> List(1)
      Ns.i.Ref.i.query.get ==> List()
    }


    "Ref + expr" - types { implicit conn =>
      Ns.i.Ref.i.insert((1, 10), (2, 20)).transact
      Ns.i.a1.query.get ==> List(1, 2)
      Ref.i.a1.query.get ==> List(10, 20)
      Ns.i.a1.Ref.i.query.get ==> List((1, 10), (2, 20))

      Ns.i_.Ref.i_.>(15).delete.transact
      Ns.i.query.get ==> List(1)
      // Note that Ref.int entity is a separate entity and is not deleted
      // Only the entity of the initial namespace is deleted
      Ref.i.a1.query.get ==> List(10, 20)
      Ns.i.Ref.i.query.get ==> List((1, 10))
    }


    "Composite" - types { implicit conn =>
      Ns.i.insert(1).transact
      (Ns.i + Ref.i).insert((2, 20), (3, 30)).transact

      Ns.i.query.get ==> List(1, 2, 3)
      (Ns.i + Ref.i).query.get ==> List((2, 20), (3, 30))

      // Nothing deleted since entity 1 doesn't have a ref
      (Ns.i_(1) + Ref.i_).delete.transact
      Ns.i.query.get ==> List(1, 2, 3)

      // Second entity has a ref and will be deleted
      (Ns.i_(2) + Ref.i_).delete.transact
      Ns.i.query.get ==> List(1, 3)
      (Ns.i + Ref.i).query.get ==> List((3, 30))

      // Note that Ref.int belongs to the same entity as Unique.int, and is therefore deleted together
      Ref.i.query.get ==> List(30)

      // Ns.i entity has no ref to Ref.i_(42) so nothing is deleted
      (Ns.i_ + Ref.i_(42)).delete.transact
      Ns.i.query.get ==> List(1, 3)
      (Ns.i + Ref.i).query.get ==> List((3, 30))

      // Ns.i entity has a ref to Ref.i_(30) so it will be deleted
      (Ns.i_ + Ref.i_(30)).delete.transact
      Ns.i.query.get ==> List(1)
      (Ns.i + Ref.i).query.get ==> List()
    }


    "Composite + expr" - types { implicit conn =>
      (Ns.i + Ref.i).insert((1, 10), (2, 20)).transact
      Ns.i.a1.query.get ==> List(1, 2)
      Ref.i.a1.query.get ==> List(10, 20)
      (Ns.i.a1 + Ref.i).query.get ==> List((1, 10), (2, 20))

      (Ns.i_ + Ref.i_.>(15)).delete.transact
      Ns.i.query.get ==> List(1)
      Ref.i.query.get ==> List(10)
      (Ns.i + Ref.i).query.get ==> List((1, 10))
    }


    "Semantics" - {

      "Only tacit attributes" - types { implicit conn =>
        intercept[MoleculeException](
          Ns.i.<=(2).delete.multiple.transact
        ).message ==> "Can only filter delete by values applied to tacit card-one attributes. Found:\n" +
          "AttrOneManInt(Ns,i,Le,List(2),None,None,None)"
      }

      "Multiple values" - types { implicit conn =>
        intercept[MoleculeException](
          Ns.i_(1).int(1, 2).update.transact
        ).message ==> "Can only update one value for attribute `Ns.int`. Found: ArraySeq(1, 2)"

        intercept[MoleculeException](
          Ns.i_(1).int(1, 2).upsert.transact
        ).message ==> "Can only upsert one value for attribute `Ns.int`. Found: ArraySeq(1, 2)"
      }
    }
  }
}
