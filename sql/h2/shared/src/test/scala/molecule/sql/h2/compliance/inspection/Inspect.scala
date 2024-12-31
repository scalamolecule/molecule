package molecule.sql.h2.compliance.inspection

import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup.{MUnitSuite, TestUtils}
import molecule.sql.h2.async._
import molecule.sql.h2.setup.DbProviders_h2
import scala.language.implicitConversions

class Test_Inspect extends MUnitSuite with DbProviders_h2 with TestUtils {

  "Query" - {

    "Inspect without fetching" - types { implicit conn =>
      for {
        _ <- Entity.string("a").int(1).save.transact
        _ <- Entity.string.int.query.inspect.map(_ ==> ((): Unit)) // returns Unit
        /*
        ========================================
        QUERY:
        AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

        SELECT DISTINCT
          Entity.string,
          Entity.int
        FROM Ns
        WHERE
          Entity.string IS NOT NULL AND
          Entity.int    IS NOT NULL;
        ----------------------------------------
        */
      } yield ()
    }

    "Inspect and query" - types { implicit conn =>
      for {
        _ <- Entity.string("a").int(1).save.transact
        _ <- Entity.string.int.query.i.get.map(_ ==> List(("a", 1)))
        /*
        ========================================
        QUERY:
        AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

        SELECT DISTINCT
          Entity.string,
          Entity.int
        FROM Ns
        WHERE
          Entity.string IS NOT NULL AND
          Entity.int    IS NOT NULL;
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
        AttrOneManInt("Ns", "i", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 1))
        AttrOneManDouble("Ns", "int", Fn(avg,None), Seq(), None, None, Nil, Nil, None, Some("a1"), Seq(0, 8))
        Nested(
          Ref("Ns", "refs", "Ref", CardSet, false, Seq(0, 53, 1)),
          List(
            AttrOneManString("Ref", "string", Eq, Seq("foo"), None, None, Nil, Nil, None, None, Seq(1, 101))))

        SELECT DISTINCT
          Entity.id,
          Entity.i,
          AVG(Entity.int) Entity_int_avg,
          Ref.string
        FROM Ns
          INNER JOIN Entity_refs_Ref ON
            Entity.id = Entity_refs_Ref.Entity_id
          INNER JOIN Ref ON
            Entity_refs_Ref.Ref_id = Ref.id
        WHERE
          Entity.i       IS NOT NULL AND
          Ref.string = 'foo'
        GROUP BY Entity.i, Entity.id, Ref.string
        ORDER BY Entity_int_avg;
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
        AttrOneManString("Ns", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Ns", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, Seq(0, 8))

        Save(
          Entity(
            INSERT INTO Ns (
              string,
              int
            ) VALUES (?, ?)
          )
        )
        ----------------------------------------
        */
        // (values are visible in the model elements)

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
        AttrOneManString("Ns", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Ns", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, Seq(0, 8))

        Save(
          Entity(
            INSERT INTO Ns (
              string,
              int
            ) VALUES (?, ?)
          )
        )
        ----------------------------------------
        */
        // (values are visible in the model elements)

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
        AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

        Insert(
          Entity(
            INSERT INTO Ns (
              string,
              int
            ) VALUES (?, ?)
          )
        )

        (a,1)
        (b,2)
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
        AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

        Insert(
          Entity(
            INSERT INTO Ns (
              string,
              int
            ) VALUES (?, ?)
          )
        )

        (a,1)
        (b,2)
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
        AttrOneTacID("Ns", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, Seq(0, 0))
        AttrOneManString("Ns", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, Seq(0, 7))

        Update(
          Entity(
            UPDATE Ns
            SET
              string = ?
            WHERE
              Entity.id IN(1) AND
              Entity.string IS NOT NULL
          )
        )
        ----------------------------------------
        */
        // (values are visible in the model elements)

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
        AttrOneTacID("Ns", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, Seq(0, 0))
        AttrOneManString("Ns", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, Seq(0, 7))

        Update(
          Entity(
            UPDATE Ns
            SET
              string = ?
            WHERE
              Entity.id IN(1) AND
              Entity.string IS NOT NULL
          )
        )
        ----------------------------------------
        */
        // (values are visible in the model elements)

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
        AttrOneTacID("Ns", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, Seq(0, 0))

        Delete(
          Ns (
            DELETE FROM Ns WHERE id IN (1)
          )
        )
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
        AttrOneTacID("Ns", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, Seq(0, 0))

        Delete(
          Ns (
            DELETE FROM Ns WHERE id IN (1)
          )
        )
        ----------------------------------------
        */

        // Deletion was inspected and date deleted
        _ <- Entity.string.int.query.get.map(_ ==> List(("b", 2)))
      } yield ()
    }
  }
}
