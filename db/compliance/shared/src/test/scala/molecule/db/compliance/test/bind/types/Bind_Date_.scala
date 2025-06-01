// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import java.util.Date
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_Date_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.date.insert(date1, date2, date3).transact

      eq = Entity.date(?).query
      _ <- eq(date1).get.map(_ ==> List(date1))
      _ <- eq(date2).get.map(_ ==> List(date2))
      _ <- eq(date3).get.map(_ ==> List(date3))

      ne = Entity.date.not(?).query
      _ <- ne(date1).get.map(_ ==> List(date2, date3))
      _ <- ne(date2).get.map(_ ==> List(date1, date3))
      _ <- ne(date3).get.map(_ ==> List(date1, date2))

      lt = Entity.date.<(?).query
      _ <- lt(date1).get.map(_ ==> List())
      _ <- lt(date2).get.map(_ ==> List(date1))
      _ <- lt(date3).get.map(_ ==> List(date1, date2))

      le = Entity.date.<=(?).query
      _ <- le(date1).get.map(_ ==> List(date1))
      _ <- le(date2).get.map(_ ==> List(date1, date2))
      _ <- le(date3).get.map(_ ==> List(date1, date2, date3))

      gt = Entity.date.>(?).query
      _ <- gt(date1).get.map(_ ==> List(date2, date3))
      _ <- gt(date2).get.map(_ ==> List(date3))
      _ <- gt(date3).get.map(_ ==> List())

      ge = Entity.date.>=(?).query
      _ <- ge(date1).get.map(_ ==> List(date1, date2, date3))
      _ <- ge(date2).get.map(_ ==> List(date2, date3))
      _ <- ge(date3).get.map(_ ==> List(date3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.date.insert((1, date1), (2, date2), (3, date3)).transact

      eq = Entity.i.date_(?).query
      _ <- eq(date1).get.map(_ ==> List(1))
      _ <- eq(date2).get.map(_ ==> List(2))
      _ <- eq(date3).get.map(_ ==> List(3))

      ne = Entity.i.date_.not(?).query
      _ <- ne(date1).get.map(_ ==> List(2, 3))
      _ <- ne(date2).get.map(_ ==> List(1, 3))
      _ <- ne(date3).get.map(_ ==> List(1, 2))

      lt = Entity.i.date_.<(?).query
      _ <- lt(date1).get.map(_ ==> List())
      _ <- lt(date2).get.map(_ ==> List(1))
      _ <- lt(date3).get.map(_ ==> List(1, 2))

      le = Entity.i.date_.<=(?).query
      _ <- le(date1).get.map(_ ==> List(1))
      _ <- le(date2).get.map(_ ==> List(1, 2))
      _ <- le(date3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.date_.>(?).query
      _ <- gt(date1).get.map(_ ==> List(2, 3))
      _ <- gt(date2).get.map(_ ==> List(3))
      _ <- gt(date3).get.map(_ ==> List())

      ge = Entity.i.date_.>=(?).query
      _ <- ge(date1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(date2).get.map(_ ==> List(2, 3))
      _ <- ge(date3).get.map(_ ==> List(3))
    } yield ()
  }
}
