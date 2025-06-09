// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_OffsetTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.offsetTime.insert(offsetTime1, offsetTime2, offsetTime3).transact

      eq = Entity.offsetTime(?).a1.query
      _ <- eq(offsetTime1).get.map(_ ==> List(offsetTime1))
      _ <- eq(offsetTime2).get.map(_ ==> List(offsetTime2))
      _ <- eq(offsetTime3).get.map(_ ==> List(offsetTime3))

      ne = Entity.offsetTime.not(?).a1.query
      _ <- ne(offsetTime1).get.map(_ ==> List(offsetTime2, offsetTime3))
      _ <- ne(offsetTime2).get.map(_ ==> List(offsetTime1, offsetTime3))
      _ <- ne(offsetTime3).get.map(_ ==> List(offsetTime1, offsetTime2))

      lt = Entity.offsetTime.<(?).a1.query
      _ <- lt(offsetTime1).get.map(_ ==> List())
      _ <- lt(offsetTime2).get.map(_ ==> List(offsetTime1))
      _ <- lt(offsetTime3).get.map(_ ==> List(offsetTime1, offsetTime2))

      le = Entity.offsetTime.<=(?).a1.query
      _ <- le(offsetTime1).get.map(_ ==> List(offsetTime1))
      _ <- le(offsetTime2).get.map(_ ==> List(offsetTime1, offsetTime2))
      _ <- le(offsetTime3).get.map(_ ==> List(offsetTime1, offsetTime2, offsetTime3))

      gt = Entity.offsetTime.>(?).a1.query
      _ <- gt(offsetTime1).get.map(_ ==> List(offsetTime2, offsetTime3))
      _ <- gt(offsetTime2).get.map(_ ==> List(offsetTime3))
      _ <- gt(offsetTime3).get.map(_ ==> List())

      ge = Entity.offsetTime.>=(?).a1.query
      _ <- ge(offsetTime1).get.map(_ ==> List(offsetTime1, offsetTime2, offsetTime3))
      _ <- ge(offsetTime2).get.map(_ ==> List(offsetTime2, offsetTime3))
      _ <- ge(offsetTime3).get.map(_ ==> List(offsetTime3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetTime.insert((1, offsetTime1), (2, offsetTime2), (3, offsetTime3)).transact

      eq = Entity.i.a1.offsetTime_(?).query
      _ <- eq(offsetTime1).get.map(_ ==> List(1))
      _ <- eq(offsetTime2).get.map(_ ==> List(2))
      _ <- eq(offsetTime3).get.map(_ ==> List(3))

      ne = Entity.i.a1.offsetTime_.not(?).query
      _ <- ne(offsetTime1).get.map(_ ==> List(2, 3))
      _ <- ne(offsetTime2).get.map(_ ==> List(1, 3))
      _ <- ne(offsetTime3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.offsetTime_.<(?).query
      _ <- lt(offsetTime1).get.map(_ ==> List())
      _ <- lt(offsetTime2).get.map(_ ==> List(1))
      _ <- lt(offsetTime3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.offsetTime_.<=(?).query
      _ <- le(offsetTime1).get.map(_ ==> List(1))
      _ <- le(offsetTime2).get.map(_ ==> List(1, 2))
      _ <- le(offsetTime3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.offsetTime_.>(?).query
      _ <- gt(offsetTime1).get.map(_ ==> List(2, 3))
      _ <- gt(offsetTime2).get.map(_ ==> List(3))
      _ <- gt(offsetTime3).get.map(_ ==> List())

      ge = Entity.i.a1.offsetTime_.>=(?).query
      _ <- ge(offsetTime1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(offsetTime2).get.map(_ ==> List(2, 3))
      _ <- ge(offsetTime3).get.map(_ ==> List(3))
    } yield ()
  }
}
