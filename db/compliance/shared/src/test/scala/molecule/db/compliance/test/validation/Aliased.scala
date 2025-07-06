package molecule.db.compliance.test.validation

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Validation.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*


case class Aliased(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Aliased attribute name" - validation { implicit conn =>
    for {
      _ <- Person.name("Bob").tpe("easy-going").save.transact
      _ <- Person.name.tpe.query.get.map(_ ==> List(("Bob", "easy-going")))

      // Note how 'type' (and not the alias 'tpe') is used in the query
      _ <- Person.name.tpe.query.inspect.map(_ ==>
        s"""========================================
           |QUERY:
           |DataModel(
           |  List(
           |    AttrOneManString("Person", "name", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 1)),
           |    AttrOneManString("Person", "type", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 2))
           |  ),
           |  Set(1, 2), 0, 0, Nil
           |)
           |
           |SELECT DISTINCT
           |  Person.name,
           |  Person.type
           |FROM Person
           |WHERE
           |  Person.name IS NOT NULL AND
           |  Person.type IS NOT NULL;
           |----------------------------------------
           |""".stripMargin
      )
    } yield ()
  }
}
