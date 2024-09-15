package molecule.sql.mariadb.compliance.inspect

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.sql.mariadb.async._
import molecule.sql.mariadb.setup.TestSuite_mariadb
import utest._
import scala.language.implicitConversions

object Test_Inspect extends TestSuite_mariadb {

  override lazy val tests = Tests {

    /*
        Notice how the attribute name `int` is transparently
        renamed to `int_` in queries and transactions to avoid
        collision with mariadb keywords.
     */

    "Query" - {

      "Inspect without fetching" - types { implicit conn =>
        for {
          _ <- Ns.string("a").int(1).save.transact
          _ <- Ns.string.int.query.inspect.map(_ ==> ((): Unit)) // returns Unit
          /*
          ========================================
          QUERY:
          AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
          AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

          SELECT DISTINCT
            Ns.string,
            Ns.int_
          FROM Ns
          WHERE
            Ns.string IS NOT NULL AND
            Ns.int_   IS NOT NULL;
          ----------------------------------------
          */
        } yield ()
      }

      "Inspect and query" - types { implicit conn =>
        for {
          _ <- Ns.string("a").int(1).save.transact
          _ <- Ns.string.int.query.i.get.map(_ ==> List(("a", 1)))
          /*
          ========================================
          QUERY:
          AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
          AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

          SELECT DISTINCT
            Ns.string,
            Ns.int_
          FROM Ns
          WHERE
            Ns.string IS NOT NULL AND
            Ns.int_   IS NOT NULL;
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
          AttrOneManDouble("Ns", "int", Fn(avg,None), Seq(), None, None, Nil, Nil, None, Some("a1"), Seq(0, 8))
          Nested(
            Ref("Ns", "refs", "Ref", CardSet, false, Seq(0, 53, 1)),
            List(
              AttrOneManString("Ref", "string", Eq, Seq("foo"), None, None, Nil, Nil, None, None, Seq(1, 101))))

          SELECT DISTINCT
            Ns.id,
            Ns.i,
            AVG(Ns.int_) Ns_int__avg,
            Ref.string
          FROM Ns
            INNER JOIN Ns_refs_Ref ON
              Ns.id = Ns_refs_Ref.Ns_id
            INNER JOIN Ref ON
              Ns_refs_Ref.Ref_id = Ref.id
          WHERE
            Ns.i       IS NOT NULL AND
            Ref.string = 'foo'
          GROUP BY Ns.i, Ns.id, Ref.string
          ORDER BY Ns_int__avg;
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
          AttrOneManString("Ns", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, Seq(0, 7))
          AttrOneManInt("Ns", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, Seq(0, 8))

          Save(
            Ns(
              INSERT INTO Ns (
                string,
                int_
              ) VALUES (?, ?)
            )
          )
          ----------------------------------------
          */
          // (values are visible in the model elements)

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
          AttrOneManString("Ns", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, Seq(0, 7))
          AttrOneManInt("Ns", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, Seq(0, 8))

          Save(
            Ns(
              INSERT INTO Ns (
                string,
                int_
              ) VALUES (?, ?)
            )
          )
          ----------------------------------------
          */
          // (values are visible in the model elements)

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
          AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
          AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

          Insert(
            Ns(
              INSERT INTO Ns (
                string,
                int_
              ) VALUES (?, ?)
            )
          )

          (a,1)
          (b,2)
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
          AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
          AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

          Insert(
            Ns(
              INSERT INTO Ns (
                string,
                int_
              ) VALUES (?, ?)
            )
          )

          (a,1)
          (b,2)
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
          AttrOneTacID("Ns", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, Seq(0, 0))
          AttrOneManString("Ns", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, Seq(0, 7))

          Update(
            Ns(
              UPDATE Ns
              SET
                string = ?
              WHERE
                Ns.id IN(1) AND
                Ns.string IS NOT NULL
            )
          )
          ----------------------------------------
          */
          // (values are visible in the model elements)

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
          AttrOneTacID("Ns", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, Seq(0, 0))
          AttrOneManString("Ns", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, Seq(0, 7))

          Update(
            Ns(
              UPDATE Ns
              SET
                string = ?
              WHERE
                Ns.id IN(1) AND
                Ns.string IS NOT NULL
            )
          )
          ----------------------------------------
          */
          // (values are visible in the model elements)

          // Update was inspected and date updated
          _ <- Ns.string.int.query.get.map(_ ==> List(("ZZZ", 1)))
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
          AttrOneTacID("Ns", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, Seq(0, 0))

          Delete(
            Ns (
              DELETE FROM Ns WHERE id IN (1)
            )
          )
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
          AttrOneTacID("Ns", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, Seq(0, 0))

          Delete(
            Ns (
              DELETE FROM Ns WHERE id IN (1)
            )
          )
          ----------------------------------------
          */

          // Deletion was inspected and date deleted
          _ <- Ns.string.int.query.get.map(_ ==> List(("b", 2)))
        } yield ()
      }
    }
  }
}