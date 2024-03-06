package molecule.sql.h2.compliance.inspect

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.sql.h2.async._
import molecule.sql.h2.setup.TestSuite_h2
import utest._
import scala.language.implicitConversions

object Inspect extends TestSuite_h2 {

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

          SELECT DISTINCT
            Ns.string,
            Ns.int
          FROM Ns
          WHERE
            Ns.string IS NOT NULL AND
            Ns.int    IS NOT NULL;
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
          AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 5))
          AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 6))

          SELECT DISTINCT
            Ns.string,
            Ns.int
          FROM Ns
          WHERE
            Ns.string IS NOT NULL AND
            Ns.int    IS NOT NULL;
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

          SELECT DISTINCT
            Ns.id,
            Ns.i,
            AVG(DISTINCT Ns.int) Ns_int_avg,
            Ref.string
          FROM Ns
            INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
            INNER JOIN Ref         ON Ns_refs_Ref.Ref_id = Ref.id
          WHERE
            Ref.string = 'foo' AND
            Ns.i       IS NOT NULL AND
            Ns.int     IS NOT NULL AND
            Ref.string IS NOT NULL
          GROUP BY Ns.i, Ref.string
          ORDER BY Ns_int_avg;
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

          INSERT INTO Ns (
            string,
            int
          ) VALUES (?, ?)
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
          AttrOneManString("Ns", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, Seq(0, 5))
          AttrOneManInt("Ns", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, Seq(0, 6))

          INSERT INTO Ns (
            string,
            int
          ) VALUES (?, ?)
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
          AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 5))
          AttrOneManInt("Ns", "int_", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 6))

          INSERT INTO Ns (
            string,
            int_
          ) VALUES (?, ?)

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
          AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 5))
          AttrOneManInt("Ns", "int_", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 6))

          INSERT INTO Ns (
            string,
            int_
          ) VALUES (?, ?)

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
          AttrOneTacID("Ns", "id", Eq, Seq("1"), None, None, Nil, Nil, None, None, Seq(0, 0))
          AttrOneManString("Ns", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, Seq(0, 5))

          UPDATE Ns
          SET
            string = ?
          WHERE Ns.id IN(1) AND
            Ns.string IS NOT NULL
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
          AttrOneTacID("Ns", "id", Eq, Seq("1"), None, None, Nil, Nil, None, None, Seq(0, 0))
          AttrOneManString("Ns", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, Seq(0, 5))

          UPDATE Ns
          SET
            string = ?
          WHERE Ns.id IN(1) AND
            Ns.string IS NOT NULL
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

          // Deletions make sure not to orphan possible joins involving the deleted ids
          /*
          ========================================
          DELETE:
          AttrOneTacID("Ns", "id", Eq, Seq("1"), None, None, Nil, Nil, None, None, Seq(0, 0))

          DELETE FROM Ns_refs_Ref WHERE Ns_id IN (1)
          --------
          DELETE FROM Ns WHERE Ns.id IN (1)
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
          AttrOneTacID("Ns", "id", Eq, Seq("1"), None, None, Nil, Nil, None, None, Seq(0, 0))

          DELETE FROM Ns_refs_Ref WHERE Ns_id IN (1)
          --------
          DELETE FROM Ns WHERE Ns.id IN (1)
          ----------------------------------------
          */

          // Deletion was inspected and date deleted
          _ <- Ns.string.int.query.get.map(_ ==> List(("b", 2)))
        } yield ()
      }
    }
  }
}