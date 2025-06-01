// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import java.time.LocalTime
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_LocalTime_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.localTime.insert(localTime1, localTime2, localTime3).transact

      eq = Entity.localTime(?).query
      _ <- eq(localTime1).get.map(_ ==> List(localTime1))
      _ <- eq(localTime2).get.map(_ ==> List(localTime2))
      _ <- eq(localTime3).get.map(_ ==> List(localTime3))

      ne = Entity.localTime.not(?).query
      _ <- ne(localTime1).get.map(_ ==> List(localTime2, localTime3))
      _ <- ne(localTime2).get.map(_ ==> List(localTime1, localTime3))
      _ <- ne(localTime3).get.map(_ ==> List(localTime1, localTime2))

      lt = Entity.localTime.<(?).query
      _ <- lt(localTime1).get.map(_ ==> List())
      _ <- lt(localTime2).get.map(_ ==> List(localTime1))
      _ <- lt(localTime3).get.map(_ ==> List(localTime1, localTime2))

      le = Entity.localTime.<=(?).query
      _ <- le(localTime1).get.map(_ ==> List(localTime1))
      _ <- le(localTime2).get.map(_ ==> List(localTime1, localTime2))
      _ <- le(localTime3).get.map(_ ==> List(localTime1, localTime2, localTime3))

      gt = Entity.localTime.>(?).query
      _ <- gt(localTime1).get.map(_ ==> List(localTime2, localTime3))
      _ <- gt(localTime2).get.map(_ ==> List(localTime3))
      _ <- gt(localTime3).get.map(_ ==> List())

      ge = Entity.localTime.>=(?).query
      _ <- ge(localTime1).get.map(_ ==> List(localTime1, localTime2, localTime3))
      _ <- ge(localTime2).get.map(_ ==> List(localTime2, localTime3))
      _ <- ge(localTime3).get.map(_ ==> List(localTime3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.localTime.insert((1, localTime1), (2, localTime2), (3, localTime3)).transact

      eq = Entity.i.localTime_(?).query
      _ <- eq(localTime1).get.map(_ ==> List(1))
      _ <- eq(localTime2).get.map(_ ==> List(2))
      _ <- eq(localTime3).get.map(_ ==> List(3))

      ne = Entity.i.localTime_.not(?).query
      _ <- ne(localTime1).get.map(_ ==> List(2, 3))
      _ <- ne(localTime2).get.map(_ ==> List(1, 3))
      _ <- ne(localTime3).get.map(_ ==> List(1, 2))

      lt = Entity.i.localTime_.<(?).query
      _ <- lt(localTime1).get.map(_ ==> List())
      _ <- lt(localTime2).get.map(_ ==> List(1))
      _ <- lt(localTime3).get.map(_ ==> List(1, 2))

      le = Entity.i.localTime_.<=(?).query
      _ <- le(localTime1).get.map(_ ==> List(1))
      _ <- le(localTime2).get.map(_ ==> List(1, 2))
      _ <- le(localTime3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.localTime_.>(?).query
      _ <- gt(localTime1).get.map(_ ==> List(2, 3))
      _ <- gt(localTime2).get.map(_ ==> List(3))
      _ <- gt(localTime3).get.map(_ ==> List())

      ge = Entity.i.localTime_.>=(?).query
      _ <- ge(localTime1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(localTime2).get.map(_ ==> List(2, 3))
      _ <- ge(localTime3).get.map(_ ==> List(3))
    } yield ()
  }
}
