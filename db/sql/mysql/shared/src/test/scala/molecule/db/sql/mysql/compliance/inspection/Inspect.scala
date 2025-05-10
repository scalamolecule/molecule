package molecule.db.sql.mysql.compliance.inspection

import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.db.sql
import molecule.db.sql.mysql.async.*
import molecule.db.sql.mysql.setup.DbProviders_mysql
//import scala.language.implicitConversions

class Test_Inspect extends Test with DbProviders_mysql with TestUtils {

  /*
      Notice how both the attribute names `string` and `int` are transparently
      renamed to `string_` and `int_` in queries and transactions to avoid
      collision with mysql keywords.
   */

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

        SELECT DISTINCT
          Entity.string_,
          Entity.int_
        FROM Entity
        WHERE
          Entity.string_ IS NOT NULL AND
          Entity.int_    IS NOT NULL;
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
        AttrOneManString("Entity", "string_", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Entity", "int_", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

        SELECT DISTINCT
          Entity.string_,
          Entity.int_
        FROM Entity
        WHERE
          Entity.string_ IS NOT NULL AND
          Entity.int_    IS NOT NULL;
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

        SELECT DISTINCT
          Entity.id,
          Entity.i,
          AVG(Entity.int_) Entity_int__avg,
          Ref.string_
        FROM Entity
          INNER JOIN Entity_refs_Ref ON
            Entity.id = Entity_refs_Ref.Entity_id
          INNER JOIN Ref ON
            Entity_refs_Ref.Ref_id = Ref.id
        WHERE
          Entity.i        IS NOT NULL AND
          Ref.string_ = 'foo'
        GROUP BY Entity.i, Entity.id, Ref.string_
        ORDER BY Entity_int__avg;
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

        Save(
          Entity(
            INSERT INTO Entity (
              string_,
              int_
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
        AttrOneManString("Entity", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Entity", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, Seq(0, 8))

        Save(
          Entity(
            INSERT INTO Entity (
              string_,
              int_
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
        AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

        Insert(
          Entity(
            INSERT INTO Entity (
              string_,
              int_
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
        AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 7))
        AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 8))

        Insert(
          Entity(
            INSERT INTO Entity (
              string_,
              int_
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
        AttrOneTacID("Entity", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, Seq(0, 0))
        AttrOneManString("Entity", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, Seq(0, 7))

        Update(
          Entity(
            UPDATE Entity
            SET
              string_ = ?
            WHERE
              Entity.id IN(1) AND
              Entity.string_ IS NOT NULL
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
        AttrOneTacID("Entity", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, Seq(0, 0))
        AttrOneManString("Entity", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, Seq(0, 7))

        Update(
          Entity(
            UPDATE Entity
            SET
              string_ = ?
            WHERE
              Entity.id IN(1) AND
              Entity.string_ IS NOT NULL
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
        AttrOneTacID("Entity", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, Seq(0, 0))

        Delete(
          Entity (
            DELETE FROM Entity WHERE id IN (1)
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
        AttrOneTacID("Entity", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, Seq(0, 0))

        Delete(
          Entity (
            DELETE FROM Entity WHERE id IN (1)
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
