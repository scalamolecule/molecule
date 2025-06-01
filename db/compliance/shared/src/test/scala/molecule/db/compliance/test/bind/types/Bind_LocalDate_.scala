// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import java.time.LocalDate
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_LocalDate_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.localDate.insert(localDate1, localDate2, localDate3).transact

      eq = Entity.localDate(?).a1.query
      _ <- eq(localDate1).get.map(_ ==> List(localDate1))
      _ <- eq(localDate2).get.map(_ ==> List(localDate2))
      _ <- eq(localDate3).get.map(_ ==> List(localDate3))

      ne = Entity.localDate.not(?).a1.query
      _ <- ne(localDate1).get.map(_ ==> List(localDate2, localDate3))
      _ <- ne(localDate2).get.map(_ ==> List(localDate1, localDate3))
      _ <- ne(localDate3).get.map(_ ==> List(localDate1, localDate2))

      lt = Entity.localDate.<(?).a1.query
      _ <- lt(localDate1).get.map(_ ==> List())
      _ <- lt(localDate2).get.map(_ ==> List(localDate1))
      _ <- lt(localDate3).get.map(_ ==> List(localDate1, localDate2))

      le = Entity.localDate.<=(?).a1.query
      _ <- le(localDate1).get.map(_ ==> List(localDate1))
      _ <- le(localDate2).get.map(_ ==> List(localDate1, localDate2))
      _ <- le(localDate3).get.map(_ ==> List(localDate1, localDate2, localDate3))

      gt = Entity.localDate.>(?).a1.query
      _ <- gt(localDate1).get.map(_ ==> List(localDate2, localDate3))
      _ <- gt(localDate2).get.map(_ ==> List(localDate3))
      _ <- gt(localDate3).get.map(_ ==> List())

      ge = Entity.localDate.>=(?).a1.query
      _ <- ge(localDate1).get.map(_ ==> List(localDate1, localDate2, localDate3))
      _ <- ge(localDate2).get.map(_ ==> List(localDate2, localDate3))
      _ <- ge(localDate3).get.map(_ ==> List(localDate3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.localDate.insert((1, localDate1), (2, localDate2), (3, localDate3)).transact

      eq = Entity.i.a1.localDate_(?).query
      _ <- eq(localDate1).get.map(_ ==> List(1))
      _ <- eq(localDate2).get.map(_ ==> List(2))
      _ <- eq(localDate3).get.map(_ ==> List(3))

      ne = Entity.i.a1.localDate_.not(?).query
      _ <- ne(localDate1).get.map(_ ==> List(2, 3))
      _ <- ne(localDate2).get.map(_ ==> List(1, 3))
      _ <- ne(localDate3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.localDate_.<(?).query
      _ <- lt(localDate1).get.map(_ ==> List())
      _ <- lt(localDate2).get.map(_ ==> List(1))
      _ <- lt(localDate3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.localDate_.<=(?).query
      _ <- le(localDate1).get.map(_ ==> List(1))
      _ <- le(localDate2).get.map(_ ==> List(1, 2))
      _ <- le(localDate3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.localDate_.>(?).query
      _ <- gt(localDate1).get.map(_ ==> List(2, 3))
      _ <- gt(localDate2).get.map(_ ==> List(3))
      _ <- gt(localDate3).get.map(_ ==> List())

      ge = Entity.i.a1.localDate_.>=(?).query
      _ <- ge(localDate1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(localDate2).get.map(_ ==> List(2, 3))
      _ <- ge(localDate3).get.map(_ ==> List(3))
    } yield ()
  }
}
