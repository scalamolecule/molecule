package molecule.db.sql.mariadb.compliance.inspection

import molecule.core.dataModel.{AttrOneManInt, AttrOneManString, DataModel, V}
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.core.util.Executor.*
import molecule.db.sql.mariadb.async.*
import molecule.db.sql.mariadb.setup.DbProviders_mariadb


class Test_Inspect extends MUnit with DbProviders_mariadb with TestUtils {


  "Inspect without fetching" - types { implicit conn =>
    for {
      _ <- Entity.string("a").int(1).save.transact

      // Inspect query without returning data
      _ <- Entity.string.int.query.inspect.map(_ ==>
        """========================================
          |QUERY:
          |DataModel(
          |  List(
          |    AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 7)),
          |    AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 8))
          |  ),
          |  Set(7, 8), 0, 0
          |)
          |
          |SELECT DISTINCT
          |  Entity.string,
          |  Entity.int_
          |FROM Entity
          |WHERE
          |  Entity.string IS NOT NULL AND
          |  Entity.int_   IS NOT NULL;
          |----------------------------------------
          |""".stripMargin
      )
      /*
          Notice how the attribute name `int` is transparently
          renamed to `int_` in queries and transactions to avoid
          collision with mariadb keywords.
       */

      // Or get the DataModel
      _ = Entity.string.int.query.dataModel ==> DataModel(
        List(
          AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 7)),
          AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 8))
        ),
        Set(7, 8), 0, 0
      )

      // Return query result and print the above inspection output to console
      _ <- Entity.string.int.query.i.get.map(_ ==> List(("a", 1)))
    } yield ()
  }


  "Inspect without saving" - types { implicit conn =>
    for {
      // Inspect save action without saving
      _ <- Entity.string("a").int(1).save.inspect.map(_ ==>
        """========================================
          |SAVE:
          |DataModel(
          |  List(
          |    AttrOneManString("Entity", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, false, List(0, 7)),
          |    AttrOneManInt("Entity", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, false, List(0, 8))
          |  ),
          |  Set(7, 8), 0, 0
          |)
          |
          |Save(
          |  Entity(
          |    INSERT INTO Entity (
          |      string,
          |      int_
          |    ) VALUES (?, ?)
          |  )
          |)
          |----------------------------------------
          |""".stripMargin
      )

      // Save action was inspected without saving
      _ <- Entity.string.int.query.get.map(_ ==> Nil)
    } yield ()
  }

  "Inspect and save" - types { implicit conn =>
    for {
      // Save data and print inspection
      _ <- Entity.string("a").int(1).save.i.transact

      // Save action was inspected and data saved
      _ <- Entity.string.int.query.get.map(_ ==> List(("a", 1)))
    } yield ()
  }


  "Inspect without inserting" - types { implicit conn =>
    for {
      // Inspect insert action without inserting
      _ <- Entity.string.int.insert(("a", 1), ("b", 2)).inspect.map(_ ==>
        """========================================
          |INSERT:
          |DataModel(
          |  List(
          |    AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 7)),
          |    AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 8))
          |  ),
          |  Set(7, 8), 0, 0
          |)
          |
          |Insert(
          |  Entity(
          |    INSERT INTO Entity (
          |      string,
          |      int_
          |    ) VALUES (?, ?)
          |  )
          |)
          |
          |(a,1)
          |(b,2)
          |----------------------------------------
          |""".stripMargin
      )

      // Insert action was inspected without inserting
      _ <- Entity.string.int.query.get.map(_ ==> Nil)
    } yield ()
  }

  "Inspect and insert" - types { implicit conn =>
    for {
      // Insert data and print inspection
      _ <- Entity.string.int.insert(("a", 1), ("b", 2)).i.transact

      // Insert action was inspected and data inserted
      _ <- Entity.string.int.a1.query.get.map(_ ==> List(("a", 1), ("b", 2)))
    } yield ()
  }


  "Inspect without updating" - types { implicit conn =>
    for {
      id <- Entity.string("a").int(1).save.transact.map(_.id)

      // Inspect update action without updating
      _ <- Entity(id).string("ZZZ").update.inspect.map(_ ==>
        """========================================
          |UPDATE:
          |DataModel(
          |  List(
          |    AttrOneTacID("Entity", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, false, List(0, 0)),
          |    AttrOneManString("Entity", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, false, List(0, 7))
          |  ),
          |  Set(7), 0, 0
          |)
          |
          |Update(
          |  Entity(
          |    UPDATE Entity
          |    SET
          |      string = ?
          |    WHERE
          |      Entity.id IN(1) AND
          |      Entity.string IS NOT NULL
          |  )
          |)
          |----------------------------------------
          |""".stripMargin
      )

      // Update was inspected without updating
      _ <- Entity.string.int.query.get.map(_ ==> List(("a", 1)))
    } yield ()
  }

  "Inspect and update" - types { implicit conn =>
    for {
      id <- Entity.string("a").int(1).save.transact.map(_.id)

      // Update data and print inspection
      _ <- Entity(id).string("ZZZ").update.i.transact

      // Update was inspected and date updated
      _ <- Entity.string.int.query.get.map(_ ==> List(("ZZZ", 1)))
    } yield ()
  }


  "Inspect without deleting" - types { implicit conn =>
    for {
      List(a, b) <- Entity.string.int.insert(("a", 1), ("b", 2)).transact.map(_.ids)

      // Inspect delete action without deleting
      _ <- Entity(a).delete.inspect.map(_ ==>
        """========================================
          |DELETE:
          |DataModel(
          |  List(
          |    AttrOneTacID("Entity", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, false, List(0, 0))
          |  ),
          |  Set(), 0, 0
          |)
          |
          |Delete(
          |  Entity (
          |    DELETE FROM Entity WHERE id IN (1)
          |  )
          |)
          |----------------------------------------
          |""".stripMargin
      )

      // Deletion was inspected without deleting
      _ <- Entity.string.int.a1.query.get.map(_ ==> List(("a", 1), ("b", 2)))
    } yield ()
  }

  "Inspect and delete" - types { implicit conn =>
    for {
      List(a, b) <- Entity.string.int.insert(("a", 1), ("b", 2)).transact.map(_.ids)

      // Delete data and print inspection
      _ <- Entity(a).delete.i.transact

      // Deletion was inspected and date deleted
      _ <- Entity.string.int.query.get.map(_ ==> List(("b", 2)))
    } yield ()
  }
}