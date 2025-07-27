package molecule.db.compliance.test.bind

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.Entity
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import scala.annotation.nowarn

@nowarn
case class Semantics(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Basic" - types {
    for {
      _ <- Entity.int.insert(1, 2, 3).transact

      // Make an "input molecule" that expects an input value for `int`.
      // Input molecules can be cached by databases and thus
      // improve performance if the structurally same query is used repeatedly.
      biggerThan = Entity.int.>(?).query

      // Then bind values to `int` and re-use the query:
      _ <- biggerThan(1).get.map(_ ==> List(2, 3))
      _ <- biggerThan(2).get.map(_ ==> List(3))
    } yield ()
  }

  "Runtime input type checking" - types {
    for {
      _ <- Entity.int.insert(1, 2, 3).transact

      eq = Entity.int(?).query

      // Expects Int, not String
      _ <- eq("1").get.map(_ ==> "Unexpected success").recover {
        case ModelError(error) =>
          error ==> "First bind value `1` is of type String but should be of type Int."
      }
    } yield ()
  }


  "Mutations don't support bind params" - types {
    interceptMessage[ModelError](
      "Save action does not support bind parameters."
    )(Entity.i(?).save)

    interceptMessage[ModelError](
      "Insert action does not support bind parameters."
    )(Entity.i(?).insert)

    interceptMessage[ModelError](
      "Update action does not support bind parameters."
    )(Entity(42).i(?).update)

    interceptMessage[ModelError](
      "Upsert action does not support bind parameters."
    )(Entity(42).i(?).upsert)
  }


  "Query offset" - types {
    for {
      _ <- Entity.int.insert(1, 2, 3).transact

      _ <- Entity.int.a1.query.offset(0).get.map(_ ==> (List(1, 2, 3), 3, false))
      _ <- Entity.int.a1.query.offset(1).get.map(_ ==> (List(2, 3), 3, false))
      _ <- Entity.int.a1.query.offset(2).get.map(_ ==> (List(3), 3, false))
      _ <- Entity.int.a1.query.offset(3).get.map(_ ==> (List(), 3, false))

      offset0gt = Entity.int.>(?).query.offset(0)
      _ <- offset0gt(0).get.map(_ ==> (List(1, 2, 3), 3, false))
      _ <- offset0gt(1).get.map(_ ==> (List(2, 3), 2, false))
      _ <- offset0gt(2).get.map(_ ==> (List(3), 1, false))
      _ <- offset0gt(3).get.map(_ ==> (List(), 0, false))

      offset1gt = Entity.int.>(?).query.offset(1)
      _ <- offset1gt(0).get.map(_ ==> (List(2, 3), 3, false))
      _ <- offset1gt(1).get.map(_ ==> (List(3), 2, false))
      _ <- offset1gt(2).get.map(_ ==> (List(), 1, false))
      _ <- offset1gt(3).get.map(_ ==> (List(), 0, false))
    } yield ()
  }

  "Query cursor" - types {
    val query   = Entity.int.a1.query
    val queryGt = Entity.int.>(?).a1.query
    for {
      _ <- Entity.int.insert(1, 2, 3, 4, 5).transact

      // Without bind
      c1 <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
      c2 <- query.from(c1).limit(2).get.map { case (List(3, 4), c, true) => c }
      _ <- query.from(c2).limit(2).get.map { case (List(5), c, false) => c }

      // With bind selecting int values greater than input (1)
      c1 <- queryGt(1).from("").limit(2).get.map { case (List(2, 3), c, true) => c }
      _ <- queryGt(1).from(c1).limit(2).get.map { case (List(4, 5), c, false) => c }
    } yield ()
  }


  // (Up to 22 inputs)
  "Multiple bindings in correct order" - types {
    for {
      _ <- Entity.i.int.string.insert(
        (1, 1, "a"),
        (2, 2, "b"),
      ).transact

      multiple = Entity.i.int_(?).string_(?).query
      _ <- multiple(1, "a").get.map(_ ==> List(1))
      _ <- multiple(2, "b").get.map(_ ==> List(2))
      _ <- multiple(2, "x").get.map(_ ==> List())
    } yield ()
  }
}
