// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import java.time.OffsetTime
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_OffsetTime_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.offsetTime.insert(offsetTime1, offsetTime2, offsetTime3).transact

      eq = Entity.offsetTime(?).query
      _ <- eq(offsetTime1).get.map(_ ==> List(offsetTime1))
      _ <- eq(offsetTime2).get.map(_ ==> List(offsetTime2))
      _ <- eq(offsetTime3).get.map(_ ==> List(offsetTime3))

      ne = Entity.offsetTime.not(?).query
      _ <- ne(offsetTime1).get.map(_ ==> List(offsetTime2, offsetTime3))
      _ <- ne(offsetTime2).get.map(_ ==> List(offsetTime1, offsetTime3))
      _ <- ne(offsetTime3).get.map(_ ==> List(offsetTime1, offsetTime2))

      lt = Entity.offsetTime.<(?).query
      _ <- lt(offsetTime1).get.map(_ ==> List())
      _ <- lt(offsetTime2).get.map(_ ==> List(offsetTime1))
      _ <- lt(offsetTime3).get.map(_ ==> List(offsetTime1, offsetTime2))

      le = Entity.offsetTime.<=(?).query
      _ <- le(offsetTime1).get.map(_ ==> List(offsetTime1))
      _ <- le(offsetTime2).get.map(_ ==> List(offsetTime1, offsetTime2))
      _ <- le(offsetTime3).get.map(_ ==> List(offsetTime1, offsetTime2, offsetTime3))

      gt = Entity.offsetTime.>(?).query
      _ <- gt(offsetTime1).get.map(_ ==> List(offsetTime2, offsetTime3))
      _ <- gt(offsetTime2).get.map(_ ==> List(offsetTime3))
      _ <- gt(offsetTime3).get.map(_ ==> List())

      ge = Entity.offsetTime.>=(?).query
      _ <- ge(offsetTime1).get.map(_ ==> List(offsetTime1, offsetTime2, offsetTime3))
      _ <- ge(offsetTime2).get.map(_ ==> List(offsetTime2, offsetTime3))
      _ <- ge(offsetTime3).get.map(_ ==> List(offsetTime3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetTime.insert((1, offsetTime1), (2, offsetTime2), (3, offsetTime3)).transact

      eq = Entity.i.offsetTime_(?).query
      _ <- eq(offsetTime1).get.map(_ ==> List(1))
      _ <- eq(offsetTime2).get.map(_ ==> List(2))
      _ <- eq(offsetTime3).get.map(_ ==> List(3))

      ne = Entity.i.offsetTime_.not(?).query
      _ <- ne(offsetTime1).get.map(_ ==> List(2, 3))
      _ <- ne(offsetTime2).get.map(_ ==> List(1, 3))
      _ <- ne(offsetTime3).get.map(_ ==> List(1, 2))

      lt = Entity.i.offsetTime_.<(?).query
      _ <- lt(offsetTime1).get.map(_ ==> List())
      _ <- lt(offsetTime2).get.map(_ ==> List(1))
      _ <- lt(offsetTime3).get.map(_ ==> List(1, 2))

      le = Entity.i.offsetTime_.<=(?).query
      _ <- le(offsetTime1).get.map(_ ==> List(1))
      _ <- le(offsetTime2).get.map(_ ==> List(1, 2))
      _ <- le(offsetTime3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.offsetTime_.>(?).query
      _ <- gt(offsetTime1).get.map(_ ==> List(2, 3))
      _ <- gt(offsetTime2).get.map(_ ==> List(3))
      _ <- gt(offsetTime3).get.map(_ ==> List())

      ge = Entity.i.offsetTime_.>=(?).query
      _ <- ge(offsetTime1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(offsetTime2).get.map(_ ==> List(2, 3))
      _ <- ge(offsetTime3).get.map(_ ==> List(3))
    } yield ()
  }
}
