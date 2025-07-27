package molecule.db.postgresql.compliance.inspection

import molecule.core.dataModel.{AttrOneManInt, AttrOneManString, DataModel, V}
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.postgresql.async.*
import molecule.db.postgresql.setup.DbProviders_postgresql


class Test_Inspect extends MUnit with DbProviders_postgresql with TestUtils {


  "Inspect without fetching" - types {
    for {
      _ <- Entity.string("a").int(1).save.transact

      // Inspect query without returning data
      _ <- Entity.string.int.query.inspect.map(_ ==>
        """========================================
          |QUERY:
          |DataModel(
          |  List(
          |    AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 10)),
          |    AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 11))
          |  ),
          |  Set(10, 11), 0, 0, Nil
          |)
          |
          |SELECT DISTINCT
          |  Entity.string,
          |  Entity.int
          |FROM Entity
          |WHERE
          |  Entity.string IS NOT NULL AND
          |  Entity.int    IS NOT NULL;
          |----------------------------------------
          |""".stripMargin
      )

      // Or get the DataModel
      _ = Entity.string.int.query.dataModel ==> DataModel(
        List(
          AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 10)),
          AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 11))
        ),
        Set(10, 11), 0, 0
      )

      // Return query result and print the above inspection output to console
      _ <- Entity.string.int.query.i.get.map(_ ==> List(("a", 1)))
    } yield ()
  }


  "Inspect without saving" - types {
    for {
      // Inspect save action without saving
      _ <- Entity.string("a").int(1).save.inspect.map(_ ==> {
        if (platform == "jvm") {
          """========================================
            |SAVE:
            |DataModel(
            |  List(
            |    AttrOneManString("Entity", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, false, List(0, 10)),
            |    AttrOneManInt("Entity", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, false, List(0, 11))
            |  ),
            |  Set(10, 11), 0, 0, Nil
            |)
            |
            |Save(
            |  Entity(
            |    INSERT INTO Entity (
            |      string,
            |      int
            |    ) VALUES (?, ?)
            |  )
            |)
            |----------------------------------------
            |""".stripMargin
        } else {
          """========================================
            |SAVE:
            |DataModel(
            |  List(
            |    AttrOneManString("Entity", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, false, List(0, 10)),
            |    AttrOneManInt("Entity", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, false, List(0, 11))
            |  ),
            |  Set(10, 11), 0, 0, Nil
            |)
            |----------------------------------------
            |""".stripMargin
        }
      })

      // Save action was inspected without saving
      _ <- Entity.string.int.query.get.map(_ ==> Nil)
    } yield ()
  }

  "Inspect and save" - types {
    for {
      // Save data and print inspection
      _ <- Entity.string("a").int(1).save.i.transact

      // Save action was inspected and data saved
      _ <- Entity.string.int.query.get.map(_ ==> List(("a", 1)))
    } yield ()
  }


  "Inspect without inserting" - types {
    for {
      // Inspect insert action without inserting
      _ <- Entity.string.int.insert(("a", 1), ("b", 2)).inspect.map(_ ==> {
        if (platform == "jvm") {
          """========================================
            |INSERT:
            |DataModel(
            |  List(
            |    AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 10)),
            |    AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 11))
            |  ),
            |  Set(10, 11), 0, 0, Nil
            |)
            |
            |Insert(
            |  Entity(
            |    INSERT INTO Entity (
            |      string,
            |      int
            |    ) VALUES (?, ?)
            |  )
            |)
            |
            |(a,1)
            |(b,2)
            |----------------------------------------
            |""".stripMargin
        } else {
          """========================================
            |INSERT:
            |DataModel(
            |  List(
            |    AttrOneManString("Entity", "string", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 10)),
            |    AttrOneManInt("Entity", "int", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 11))
            |  ),
            |  Set(10, 11), 0, 0, Nil
            |)
            |----------------------------------------
            |""".stripMargin
        }
      })

      // Insert action was inspected without inserting
      _ <- Entity.string.int.query.get.map(_ ==> Nil)
    } yield ()
  }

  "Inspect and insert" - types {
    for {
      // Insert data and print inspection
      _ <- Entity.string.int.insert(("a", 1), ("b", 2)).i.transact

      // Insert action was inspected and data inserted
      _ <- Entity.string.int.a1.query.get.map(_ ==> List(("a", 1), ("b", 2)))
    } yield ()
  }


  "Inspect without updating" - types {
    for {
      id <- Entity.string("a").int(1).save.transact.map(_.id)

      // Inspect update action without updating
      _ <- Entity(id).string("ZZZ").update.inspect.map(_ ==> {
        if (platform == "jvm") {
          """========================================
            |UPDATE:
            |DataModel(
            |  List(
            |    AttrOneTacID("Entity", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, false, List(0, 0)),
            |    AttrOneManString("Entity", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, false, List(0, 10))
            |  ),
            |  Set(10), 0, 0, Nil
            |)
            |
            |Update(
            |  Entity(
            |    UPDATE Entity
            |    SET
            |      string = ?::text
            |    WHERE
            |      Entity.id IN(1) AND
            |      Entity.string IS NOT NULL
            |  )
            |)
            |----------------------------------------
            |""".stripMargin
        } else {
          """========================================
            |UPDATE:
            |DataModel(
            |  List(
            |    AttrOneTacID("Entity", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, false, List(0, 0)),
            |    AttrOneManString("Entity", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, false, List(0, 10))
            |  ),
            |  Set(10), 0, 0, Nil
            |)
            |----------------------------------------
            |""".stripMargin
        }
      })

      // Update was inspected without updating
      _ <- Entity.string.int.query.get.map(_ ==> List(("a", 1)))
    } yield ()
  }

  "Inspect and update" - types {
    for {
      id <- Entity.string("a").int(1).save.transact.map(_.id)

      // Update data and print inspection
      _ <- Entity(id).string("ZZZ").update.i.transact

      // Update was inspected and date updated
      _ <- Entity.string.int.query.get.map(_ ==> List(("ZZZ", 1)))
    } yield ()
  }


  "Inspect without deleting" - types {
    for {
      case List(a, b) <- Entity.string.int.insert(("a", 1), ("b", 2)).transact.map(_.ids)

      // Inspect delete action without deleting
      _ <- Entity(a).delete.inspect.map(_ ==> {
        if (platform == "jvm") {
          """========================================
            |DELETE:
            |DataModel(
            |  List(
            |    AttrOneTacID("Entity", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, false, List(0, 0))
            |  ),
            |  Set(), 0, 0, Nil
            |)
            |
            |Delete(
            |  Entity (
            |    DELETE FROM Entity WHERE id IN (1)
            |  )
            |)
            |----------------------------------------
            |""".stripMargin
        } else {
          """========================================
            |DELETE:
            |DataModel(
            |  List(
            |    AttrOneTacID("Entity", "id", Eq, Seq(1L), None, None, Nil, Nil, None, None, false, List(0, 0))
            |  ),
            |  Set(), 0, 0, Nil
            |)
            |----------------------------------------
            |""".stripMargin
        }
      })

      // Deletion was inspected without deleting
      _ <- Entity.string.int.a1.query.get.map(_ ==> List(("a", 1), ("b", 2)))
    } yield ()
  }

  "Inspect and delete" - types {
    for {
      case List(a, b) <- Entity.string.int.insert(("a", 1), ("b", 2)).transact.map(_.ids)

      // Delete data and print inspection
      _ <- Entity(a).delete.i.transact

      // Deletion was inspected and date deleted
      _ <- Entity.string.int.query.get.map(_ ==> List(("b", 2)))
    } yield ()
  }
}
