package molecule.db.datalog.datomic.compliance.inspection

import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{Test, TestUtils}
import molecule.db.datalog
import molecule.db.datalog.datomic.async.*
import molecule.db.datalog.datomic.setup.DbProviders_datomic


class Test_Inspect extends Test with DbProviders_datomic with TestUtils {

  "Query" - {

    "Inspect without fetching" - types { implicit conn =>
      for {
        _ <- Entity.string("a").int(1).save.transact
        _ <- Entity.string.int.query.inspect.map(_ ==> ((): Unit)) // returns Unit
        /*
        ========================================
        QUERY:
        AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

        [:find  ?b ?c
         :where [?a :Entity/string ?b]
                [?a :Entity/int ?c]]
        ----------------------------------------
        */
      } yield ()
    }

    "Inspect and query" - types { implicit conn =>
      for {
        _ <- Entity.string("a").int(1).save.transact
        _ <- Entity.string.int.query.i.get.map(_ ==> List(("a", 1))) // returns query result
        /*
        ========================================
        QUERY:
        AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

        [:find  ?b ?c
         :where [?a :Entity/string ?b]
                [?a :Entity/int ?c]]
        ----------------------------------------
        */
      } yield ()
    }

    "Inspect more complex query" - types { implicit conn =>
      for {
        _ <- Entity.i.int(avg).a1.Refs.*(Ref.string("foo")).query.inspect
        /*
        ========================================
        QUERY:
        AttrOneManInt("Entity", "i", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 1))
        AttrOneManDouble("Entity", "int", Fn(avg,None), Seq(), None, None, Nil, Nil, None, Some("a1"), Seq(0, 8))
        Nested(
          Ref("Entity", "refs", "Ref", CardSet, false, Seq(0, 53, 1)),
          List(
            AttrOneManString("Ref", "string", Eq, Seq("foo"), None, None, Nil, Nil, None, None, Seq(1, 101))))

        [:find  ?id0 ?b
                (avg ?c) ?e
         :with  ?a
         :in    $ [?e ...]
         :where [?a :Entity/i ?b]
                [?a :Entity/int ?c]
                [(identity ?a) ?id0]
                [?a :Entity/refs ?d]
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
        _ <- Entity.string("a").int(1).save.inspect
        /*
        ========================================
        SAVE:
        AttrOneManString("Entity", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Entity", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, Seq(0, 8))

        [
          [:db/add #db/id[db.part/user -1] :Entity/string a]
          [:db/add #db/id[db.part/user -1] :Entity/int 1]
        ]
        ----------------------------------------
        */

        // Save action was inspected without saving
        _ <- Entity.string.int.query.get.map(_ ==> Nil)
      } yield ()
    }

    "Inspect and save" - types { implicit conn =>
      for {
        _ <- Entity.string("a").int(1).save.i.transact
        /*
        ========================================
        SAVE:
        AttrOneManString("Entity", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Entity", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, Seq(0, 8))

        [
          [:db/add #db/id[db.part/user -1] :Entity/string a]
          [:db/add #db/id[db.part/user -1] :Entity/int 1]
        ]
        ----------------------------------------
        */

        // Save action was inspected and data saved
        _ <- Entity.string.int.query.get.map(_ ==> List(("a", 1)))
      } yield ()
    }
  }


  "Insert" - {

    "Inspect without inserting" - types { implicit conn =>
      for {
        _ <- Entity.string.int.insert(("a", 1), ("b", 2)).inspect
        /*
        ========================================
        INSERT:
        AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

        [
          [:db/add #db/id[db.part/user -1] :Entity/string a]
          [:db/add #db/id[db.part/user -1] :Entity/int 1]
          [:db/add #db/id[db.part/user -2] :Entity/string b]
          [:db/add #db/id[db.part/user -2] :Entity/int 2]
        ]
        ----------------------------------------
        */

        // Insert action was inspected without inserting
        _ <- Entity.string.int.query.get.map(_ ==> Nil)
      } yield ()
    }

    "Inspect and insert" - types { implicit conn =>
      for {
        _ <- Entity.string.int.insert(("a", 1), ("b", 2)).i.transact
        /*
        ========================================
        INSERT:
        AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

        [
          [:db/add #db/id[db.part/user -1] :Entity/string a]
          [:db/add #db/id[db.part/user -1] :Entity/int 1]
          [:db/add #db/id[db.part/user -2] :Entity/string b]
          [:db/add #db/id[db.part/user -2] :Entity/int 2]
        ]
        ----------------------------------------
        */

        // Insert action was inspected and data inserted
        _ <- Entity.string.int.a1.query.get.map(_ ==> List(("a", 1), ("b", 2)))
      } yield ()
    }
  }


  "Update" - {

    "Inspect without updating" - types { implicit conn =>
      for {
        id <- Entity.string("a").int(1).save.transact.map(_.id)
        _ <- Entity(id).string("ZZZ").update.inspect
        /*
        ========================================
        UPDATE:
        AttrOneTacID("Entity", "id", Eq, Seq(17592186045418L), None, None, Nil, Nil, None, None, Seq(0, 0))
        AttrOneManString("Entity", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, Seq(0, 7))

        [
          [:db/add 17592186045418 :Entity/string ZZZ]
        ]
        ----------------------------------------
        */

        // Update was inspected without updating
        _ <- Entity.string.int.query.get.map(_ ==> List(("a", 1)))
      } yield ()
    }

    "Inspect and update" - types { implicit conn =>
      for {
        id <- Entity.string("a").int(1).save.transact.map(_.id)
        _ <- Entity(id).string("ZZZ").update.i.transact
        /*
        ========================================
        UPDATE:
        AttrOneTacID("Entity", "id", Eq, Seq(17592186045418L), None, None, Nil, Nil, None, None, Seq(0, 0))
        AttrOneManString("Entity", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, Seq(0, 7))

        [
          [:db/add 17592186045418 :Entity/string ZZZ]
        ]
        ----------------------------------------
        */

        // Update was inspected and date updated
        _ <- Entity.string.int.query.get.map(_ ==> List(("ZZZ", 1)))
      } yield ()
    }
  }


  "Delete" - {

    "Inspect without deleting" - types { implicit conn =>
      for {
        List(a, b) <- Entity.string.int.insert(("a", 1), ("b", 2)).transact.map(_.ids)
        _ <- Entity(a).delete.inspect
        /*
        ========================================
        DELETE:
        AttrOneTacID("Entity", "id", Eq, Seq(17592186045418L), None, None, Nil, Nil, None, None, Seq(0, 0))

        [
          [:db/retractEntity 17592186045418]
        ]
        ----------------------------------------
        */

        // Deletion was inspected without deleting
        _ <- Entity.string.int.a1.query.get.map(_ ==> List(("a", 1), ("b", 2)))
      } yield ()
    }

    "Inspect and delete" - types { implicit conn =>
      for {
        List(a, b) <- Entity.string.int.insert(("a", 1), ("b", 2)).transact.map(_.ids)
        _ <- Entity(a).delete.i.transact
        /*
        ========================================
        DELETE:
        AttrOneTacID("Entity", "id", Eq, Seq(17592186045418L), None, None, Nil, Nil, None, None, Seq(0, 0))

        [
          [:db/retractEntity 17592186045418]
        ]
        ----------------------------------------
        */

        // Deletion was inspected and date deleted
        _ <- Entity.string.int.query.get.map(_ ==> List(("b", 2)))
      } yield ()
    }
  }
}