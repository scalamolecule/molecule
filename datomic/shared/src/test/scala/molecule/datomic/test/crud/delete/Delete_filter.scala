package molecule.datomic.test.crud.delete

import molecule.base.util.exceptions.MoleculeError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object Delete_filter extends DatomicTestSuite {


  lazy val tests = Tests {

    "Filter by 1 non-ns value" - types { implicit conn =>
      for {
        _ <- Ns.i.insert(1, 2).transact
        _ <- Ns.i_(1).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(2))
      } yield ()
    }


    "Filter by multiple non-ns value" - types { implicit conn =>
      for {
        List(e1, e2, e3) <- Ns.i.insert(1, 2, 2).transact.map(_.eids)
        _ <- Ns.e.a1.i.query.get.map(_ ==> List(
          (e1, 1),
          (e2, 2),
          (e3, 2),
        ))

        _ <- Ns.i_(2).delete.transact
        _ <- Ns.e.i.query.get.map(_ ==> List(
          (e1, 1),
        ))
      } yield ()
    }


    "Expression" - types { implicit conn =>
      for {
        _ <- Ns.i.insert(1, 2, 3).transact

        // Update all entities where non-ns attribute i <= 2
        _ <- Ns.i_.<=(2).delete.transact

        _ <- Ns.i.query.get.map(_ ==> List(3))
      } yield ()
    }

    "Multiple expressions" - types { implicit conn =>
      for {
        _ <- Ns.i.s.insert(
          (1, "a"),
          (2, "b"),
          (3, "c"),
        ).transact

        _ <- Ns.i.s.query.get.map(_ ==> List(
          (1, "a"),
          (2, "b"),
          (3, "c"),
        ))

        // Update all entities where non-ns attribute i > 1 and s < "c"
        _ <- Ns.i_.>(1).s_.<("c").delete.transact

        _ <- Ns.i.s.query.get.map(_ ==> List(
          (1, "a"),
          (3, "c"),
        ))
      } yield ()
    }


    "Ref" - types { implicit conn =>
      for {
        _ <- Ns.i.insert(1).transact
        _ <- Ns.i.Ref.i.insert((2, 20), (3, 30)).transact

        _ <- Ns.i.query.get.map(_ ==> List(1, 2, 3))
        _ <- Ns.i.Ref.i.query.get.map(_ ==> List((2, 20), (3, 30)))

        // Nothing deleted since entity 1 doesn't have a ref
        _ <- Ns.i_(1).Ref.i_.delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(1, 2, 3))

        // Second entity has a ref and will be deleted
        _ <- Ns.i_(2).Ref.i_.delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(1, 3))
        _ <- Ns.i.Ref.i.query.get.map(_ ==> List((3, 30)))

        // Note that Ref.int entity is a separate entity and is not deleted.
        // Only the entity of the initial namespace is deleted
        _ <- Ref.i.query.get.map(_ ==> List(20, 30))

        // Ns.i entity has no ref to Ref.i_(42) so nothing is deleted
        _ <- Ns.i_.Ref.i_(42).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(1, 3))
        _ <- Ns.i.Ref.i.query.get.map(_ ==> List((3, 30)))

        // Ns.i entity has a ref to Ref.i_(30) so it will be deleted
        _ <- Ns.i_.Ref.i_(30).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(1))
        _ <- Ns.i.Ref.i.query.get.map(_ ==> List())
      } yield ()
    }


    "Ref + expr" - types { implicit conn =>
      for {
        _ <- Ns.i.Ref.i.insert((1, 10), (2, 20)).transact
        _ <- Ns.i.a1.query.get.map(_ ==> List(1, 2))
        _ <- Ref.i.a1.query.get.map(_ ==> List(10, 20))
        _ <- Ns.i.a1.Ref.i.query.get.map(_ ==> List((1, 10), (2, 20)))

        _ <- Ns.i_.Ref.i_.>(15).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(1))
        // Note that Ref.int entity is a separate entity and is not deleted
        // Only the entity of the initial namespace is deleted
        _ <- Ref.i.a1.query.get.map(_ ==> List(10, 20))
        _ <- Ns.i.Ref.i.query.get.map(_ ==> List((1, 10)))
      } yield ()
    }


    "Composite" - types { implicit conn =>
      for {
        _ <- Ns.i.insert(1).transact
        _ <- (Ns.i + Ref.i).insert((2, 20), (3, 30)).transact

        _ <- Ns.i.query.get.map(_ ==> List(1, 2, 3))
        _ <- (Ns.i + Ref.i).query.get.map(_ ==> List((2, 20), (3, 30)))

        // Nothing deleted since entity 1 doesn't have a ref
        _ <- (Ns.i_(1) + Ref.i_).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(1, 2, 3))

        // Second entity has a ref and will be deleted
        _ <- (Ns.i_(2) + Ref.i_).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(1, 3))
        _ <- (Ns.i + Ref.i).query.get.map(_ ==> List((3, 30)))

        // Note that Ref.int belongs to the same entity as Unique.int, and is therefore deleted together
        _ <- Ref.i.query.get.map(_ ==> List(30))

        // Ns.i entity has no ref to Ref.i_(42) so nothing is deleted
        _ <- (Ns.i_ + Ref.i_(42)).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(1, 3))
        _ <- (Ns.i + Ref.i).query.get.map(_ ==> List((3, 30)))

        // Ns.i entity has a ref to Ref.i_(30) so it will be deleted
        _ <- (Ns.i_ + Ref.i_(30)).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(1))
        _ <- (Ns.i + Ref.i).query.get.map(_ ==> List())
      } yield ()
    }


    "Composite + expr" - types { implicit conn =>
      for {
        _ <- (Ns.i + Ref.i).insert((1, 10), (2, 20)).transact
        _ <- Ns.i.a1.query.get.map(_ ==> List(1, 2))
        _ <- Ref.i.a1.query.get.map(_ ==> List(10, 20))
        _ <- (Ns.i.a1 + Ref.i).query.get.map(_ ==> List((1, 10), (2, 20)))

        _ <- (Ns.i_ + Ref.i_.>(15)).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(1))
        _ <- Ref.i.query.get.map(_ ==> List(10))
        _ <- (Ns.i + Ref.i).query.get.map(_ ==> List((1, 10)))
      } yield ()
    }


    "Semantics" - {

      "Only tacit attributes" - types { implicit conn =>
        for {
          _ <- Ns.i.<=(2).delete.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can only filter delete by values applied to tacit card-one attributes. Found:\n" +
              """AttrOneManInt("Ns", "i", Le, Seq(2), None, None, None)"""
          }
        } yield ()
      }

      "Multiple values" - types { implicit conn =>
        for {
          _ <- Ns.i_(1).int(1, 2).update.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can only update one value for attribute `Ns.int`. Found: 1, 2"
          }

          _ <- Ns.i_(1).int(1, 2).upsert.transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can only upsert one value for attribute `Ns.int`. Found: 1, 2"
          }
        } yield ()
      }
    }
  }
}
