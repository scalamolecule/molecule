package molecule.datalog.datomic.compliance.inspect

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import utest._
import scala.language.implicitConversions

object Inspect extends TestSuite_datomic {

  override lazy val tests = Tests {

    "Query" - {

      "Inspect without fetching" - types { implicit conn =>
        for {
          _ <- Ns.string("a").int(1).save.transact
          _ <- Ns.string.int.query.inspect.map(_ ==> ()) // returns Unit
          /*
          ========================================
          QUERY:
          AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 5))
          AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 6))

          [:find  ?b ?c
           :where [?a :Ns/string ?b]
                  [?a :Ns/int ?c]]
          ----------------------------------------
          */
        } yield ()
      }

      "Inspect and query" - types { implicit conn =>
        for {
          _ <- Ns.string("a").int(1).save.transact
          _ <- Ns.string.int.query.i.get.map(_ ==> List(("a", 1))) // returns query result
          /*
          ========================================
          QUERY:
          AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 5))
          AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 6))

          [:find  ?b ?c
           :where [?a :Ns/string ?b]
                  [?a :Ns/int ?c]]
          ----------------------------------------
          */
        } yield ()
      }

      "Inspect more complex query" - types { implicit conn =>
        for {
          _ <- Ns.i.int(avg).a1.Refs.*(Ref.string("foo")).query.inspect
          /*
          ========================================
          QUERY:
          AttrOneManInt("Ns", "i", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 1))
          AttrOneManDouble("Ns", "int", Fn(avg,None), Seq(), None, None, Nil, Nil, None, Some("a1"), Seq(0, 6))
          Nested(
            Ref("Ns", "refs", "Ref", CardSet, Seq(0, 51, 1)),
            List(
              AttrOneManString("Ref", "string", Eq, Seq("foo"), None, None, Nil, Nil, None, None, Seq(1, 55))))

          [:find  ?id0 ?b
                  (avg ?c) ?e
           :in    $ [?e ...]
           :where [?a :Ns/i ?b]
                  [?a :Ns/int ?c]
                  [(identity ?a) ?id0]
                  [?a :Ns/refs ?d]
                  [?d :Ref/string ?e]]

          List(foo)
          ----------------------------------------
          */
        } yield ()
      }
    }


    "Save" - {

      "Inspect without saving" - types { implicit conn =>
        for {
          _ <- Ns.string("a").int(1).save.inspect
          /*
          ========================================
          SAVE:
          AttrOneManString("Ns", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, Seq(0, 5))
          AttrOneManInt("Ns", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, Seq(0, 6))

          [
            [:db/add #db/id[db.part/user -1] :Ns/string a]
            [:db/add #db/id[db.part/user -1] :Ns/int 1]
          ]
          ----------------------------------------
          */

          // Save action was inspected without saving
          _ <- Ns.string.int.query.get.map(_ ==> Nil)
        } yield ()
      }

      "Inspect and save" - types { implicit conn =>
        for {
          _ <- Ns.string("a").int(1).save.i.transact
          /*
          ========================================
          SAVE:
          AttrOneManString("Ns", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, Seq(0, 5))
          AttrOneManInt("Ns", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, Seq(0, 6))

          [
            [:db/add #db/id[db.part/user -1] :Ns/string a]
            [:db/add #db/id[db.part/user -1] :Ns/int 1]
          ]
          ----------------------------------------
          */

          // Save action was inspected and data saved
          _ <- Ns.string.int.query.get.map(_ ==> List(("a", 1)))
        } yield ()
      }
    }


    "Insert" - {

      "Inspect without inserting" - types { implicit conn =>
        for {
          _ <- Ns.string.int.insert(("a", 1), ("b", 2)).inspect
          /*
          ========================================
          INSERT:
          AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 5))
          AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 6))

          [
            [:db/add #db/id[db.part/user -1] :Ns/string a]
            [:db/add #db/id[db.part/user -1] :Ns/int 1]
            [:db/add #db/id[db.part/user -2] :Ns/string b]
            [:db/add #db/id[db.part/user -2] :Ns/int 2]
          ]
          ----------------------------------------
          */

          // Insert action was inspected without inserting
          _ <- Ns.string.int.query.get.map(_ ==> Nil)
        } yield ()
      }

      "Inspect and insert" - types { implicit conn =>
        for {
          _ <- Ns.string.int.insert(("a", 1), ("b", 2)).i.transact
          /*
          ========================================
          INSERT:
          AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 5))
          AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 6))

          [
            [:db/add #db/id[db.part/user -1] :Ns/string a]
            [:db/add #db/id[db.part/user -1] :Ns/int 1]
            [:db/add #db/id[db.part/user -2] :Ns/string b]
            [:db/add #db/id[db.part/user -2] :Ns/int 2]
          ]
          ----------------------------------------
          */

          // Insert action was inspected and data inserted
          _ <- Ns.string.int.a1.query.get.map(_ ==> List(("a", 1), ("b", 2)))
        } yield ()
      }
    }


    "Update" - {

      "Inspect without updating" - types { implicit conn =>
        for {
          id <- Ns.string("a").int(1).save.transact.map(_.id)
          _ <- Ns(id).string("ZZZ").update.inspect
          /*
          ========================================
          UPDATE:
          AttrOneTacID("Ns", "id", Eq, Seq("17592186045418"), None, None, Nil, Nil, None, None, Seq(0, 0))
          AttrOneManString("Ns", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, Seq(0, 5))

          [
            [:db/add 17592186045418 :Ns/string ZZZ]
          ]
          ----------------------------------------
          */

          // Update was inspected without updating
          _ <- Ns.string.int.query.get.map(_ ==> List(("a", 1)))
        } yield ()
      }

      "Inspect and update" - types { implicit conn =>
        for {
          id <- Ns.string("a").int(1).save.transact.map(_.id)
          _ <- Ns(id).string("ZZZ").update.i.transact
          /*
          ========================================
          UPDATE:
          AttrOneTacID("Ns", "id", Eq, Seq("17592186045418"), None, None, Nil, Nil, None, None, Seq(0, 0))
          AttrOneManString("Ns", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, Seq(0, 5))

          [
            [:db/add 17592186045418 :Ns/string ZZZ]
          ]
          ----------------------------------------
          */

          // Update was inspected and date updated
          _ <- Ns.string.int.query.get.map(_ ==> List(("ZZZ", 1)))
        } yield ()
      }

      "Inspect more complex update" - types { implicit conn =>
        for {
          id <- Ns.ints(Set(3, 4)).save.transact.map(_.id)
          _ <- Ns(id).ints.swap(3 -> 6, 4 -> 7).update.inspect
          /*
          ========================================
          UPDATE:
          AttrOneTacID("Ns", "id", Eq, Seq("17592186045418"), None, None, Nil, Nil, None, None, Seq(0, 0))
          AttrSetManInt("Ns", "ints", Swap, Seq(Set(3), Set(4), Set(6), Set(7)), None, None, Nil, Nil, None, None, Seq(0, 30))

          [
            [:db/retract 17592186045418 :Ns/ints 3]
            [:db/retract 17592186045418 :Ns/ints 4]
            [:db/add 17592186045418 :Ns/ints 6]
            [:db/add 17592186045418 :Ns/ints 7]
          ]
          ----------------------------------------
          */
        } yield ()
      }
    }


    "Delete" - {

      "Inspect without deleting" - types { implicit conn =>
        for {
          List(a, b) <- Ns.string.int.insert(("a", 1), ("b", 2)).transact.map(_.ids)
          _ <- Ns(a).delete.inspect

          /*
          ========================================
          DELETE:
          AttrOneTacID("Ns", "id", Eq, Seq("17592186045418"), None, None, Nil, Nil, None, None, Seq(0, 0))

          [
            [:db/retractEntity 17592186045418]
          ]
          ----------------------------------------
          */

          // Deletion was inspected without deleting
          _ <- Ns.string.int.a1.query.get.map(_ ==> List(("a", 1), ("b", 2)))
        } yield ()
      }

      "Inspect and delete" - types { implicit conn =>
        for {
          List(a, b) <- Ns.string.int.insert(("a", 1), ("b", 2)).transact.map(_.ids)
          _ <- Ns(a).delete.i.transact
          /*
          ========================================
          DELETE:
          AttrOneTacID("Ns", "id", Eq, Seq("17592186045418"), None, None, Nil, Nil, None, None, Seq(0, 0))

          [
            [:db/retractEntity 17592186045418]
          ]
          ----------------------------------------
          */

          // Deletion was inspected and date deleted
          _ <- Ns.string.int.query.get.map(_ ==> List(("b", 2)))
        } yield ()
      }
    }
  }
}