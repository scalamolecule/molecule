package molecule.db.compliance.test.filter.one

import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterRefOne(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Card-one ref" - refs { implicit conn =>
    for {
      _ <- A.i.B.i.insert(
        (1, 1),
        (2, 2),
        (3, 3),
      ).transact

      _ <- A.i_.B.i.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- A.i_.B.i(2).query.get.map(_ ==> List(2))
      _ <- A.i_.B.i.not(2).a1.query.get.map(_ ==> List(1, 3))
      _ <- A.i_.B.i.<(2).query.get.map(_ ==> List(1))
      _ <- A.i_.B.i.>(2).query.get.map(_ ==> List(3))
      _ <- A.i_.B.i.<=(2).a1.query.get.map(_ ==> List(1, 2))
      _ <- A.i_.B.i.>=(2).a1.query.get.map(_ ==> List(2, 3))

      _ <- A.i.a1.B.i_.query.get.map(_ ==> List(1, 2, 3))
      _ <- A.i.a1.B.i_(2).query.get.map(_ ==> List(2))
      _ <- A.i.a1.B.i_.not(2).query.get.map(_ ==> List(1, 3))
      _ <- A.i.a1.B.i_.<(2).query.get.map(_ ==> List(1))
      _ <- A.i.a1.B.i_.>(2).query.get.map(_ ==> List(3))
      _ <- A.i.a1.B.i_.<=(2).query.get.map(_ ==> List(1, 2))
      _ <- A.i.a1.B.i_.>=(2).query.get.map(_ ==> List(2, 3))

      _ <- A.i_.B.i_?.a1.query.get.map(_ ==> List(Some(1), Some(2), Some(3)))
      _ <- A.i_.B.i_?(Some(2)).query.get.map(_ ==> List(Some(2)))
    } yield ()
  }


  "Card-set ref" - refs { implicit conn =>
    for {
      _ <- A.i.Bb.i.insert(
        (1, 1),
        (2, 2),
        (3, 3),
      ).transact

      _ <- A.i_.Bb.i.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- A.i_.Bb.i(2).query.get.map(_ ==> List(2))
      _ <- A.i_.Bb.i.not(2).a1.query.get.map(_ ==> List(1, 3))
      _ <- A.i_.Bb.i.<(2).query.get.map(_ ==> List(1))
      _ <- A.i_.Bb.i.>(2).query.get.map(_ ==> List(3))
      _ <- A.i_.Bb.i.<=(2).a1.query.get.map(_ ==> List(1, 2))
      _ <- A.i_.Bb.i.>=(2).a1.query.get.map(_ ==> List(2, 3))

      _ <- A.i.a1.Bb.i_.query.get.map(_ ==> List(1, 2, 3))
      _ <- A.i.a1.Bb.i_(2).query.get.map(_ ==> List(2))
      _ <- A.i.a1.Bb.i_.not(2).query.get.map(_ ==> List(1, 3))
      _ <- A.i.a1.Bb.i_.<(2).query.get.map(_ ==> List(1))
      _ <- A.i.a1.Bb.i_.>(2).query.get.map(_ ==> List(3))
      _ <- A.i.a1.Bb.i_.<=(2).query.get.map(_ ==> List(1, 2))
      _ <- A.i.a1.Bb.i_.>=(2).query.get.map(_ ==> List(2, 3))

      _ <- A.i_.Bb.i_?.a1.query.get.map(_ ==> List(Some(1), Some(2), Some(3)))
      _ <- A.i_.Bb.i_?(Some(2)).query.get.map(_ ==> List(Some(2)))
    } yield ()
  }
}
