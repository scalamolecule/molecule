package molecule.coreTests.spi.filter.one

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Refs._
import molecule.coreTests.setup._

case class FilterRefOne(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

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


  "Card-one ref owned" - refs { implicit conn =>
    for {
      _ <- A.i.OwnB.i.insert(
        (1, 1),
        (2, 2),
        (3, 3),
      ).transact

      _ <- A.i_.OwnB.i.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- A.i_.OwnB.i(2).query.get.map(_ ==> List(2))
      _ <- A.i_.OwnB.i.not(2).a1.query.get.map(_ ==> List(1, 3))
      _ <- A.i_.OwnB.i.<(2).query.get.map(_ ==> List(1))
      _ <- A.i_.OwnB.i.>(2).query.get.map(_ ==> List(3))
      _ <- A.i_.OwnB.i.<=(2).a1.query.get.map(_ ==> List(1, 2))
      _ <- A.i_.OwnB.i.>=(2).a1.query.get.map(_ ==> List(2, 3))

      _ <- A.i.a1.OwnB.i_.query.get.map(_ ==> List(1, 2, 3))
      _ <- A.i.a1.OwnB.i_(2).query.get.map(_ ==> List(2))
      _ <- A.i.a1.OwnB.i_.not(2).query.get.map(_ ==> List(1, 3))
      _ <- A.i.a1.OwnB.i_.<(2).query.get.map(_ ==> List(1))
      _ <- A.i.a1.OwnB.i_.>(2).query.get.map(_ ==> List(3))
      _ <- A.i.a1.OwnB.i_.<=(2).query.get.map(_ ==> List(1, 2))
      _ <- A.i.a1.OwnB.i_.>=(2).query.get.map(_ ==> List(2, 3))

      _ <- A.i_.OwnB.i_?.a1.query.get.map(_ ==> List(Some(1), Some(2), Some(3)))
      _ <- A.i_.OwnB.i_?(Some(2)).query.get.map(_ ==> List(Some(2)))
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


  "Card-set ref owned" - refs { implicit conn =>
    for {
      _ <- A.i.OwnBb.i.insert(
        (1, 1),
        (2, 2),
        (3, 3),
      ).transact

      _ <- A.i_.OwnBb.i.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- A.i_.OwnBb.i(2).query.get.map(_ ==> List(2))
      _ <- A.i_.OwnBb.i.not(2).a1.query.get.map(_ ==> List(1, 3))
      _ <- A.i_.OwnBb.i.<(2).query.get.map(_ ==> List(1))
      _ <- A.i_.OwnBb.i.>(2).query.get.map(_ ==> List(3))
      _ <- A.i_.OwnBb.i.<=(2).a1.query.get.map(_ ==> List(1, 2))
      _ <- A.i_.OwnBb.i.>=(2).a1.query.get.map(_ ==> List(2, 3))

      _ <- A.i.a1.OwnBb.i_.query.get.map(_ ==> List(1, 2, 3))
      _ <- A.i.a1.OwnBb.i_(2).query.get.map(_ ==> List(2))
      _ <- A.i.a1.OwnBb.i_.not(2).query.get.map(_ ==> List(1, 3))
      _ <- A.i.a1.OwnBb.i_.<(2).query.get.map(_ ==> List(1))
      _ <- A.i.a1.OwnBb.i_.>(2).query.get.map(_ ==> List(3))
      _ <- A.i.a1.OwnBb.i_.<=(2).query.get.map(_ ==> List(1, 2))
      _ <- A.i.a1.OwnBb.i_.>=(2).query.get.map(_ ==> List(2, 3))

      _ <- A.i_.OwnBb.i_?.a1.query.get.map(_ ==> List(Some(1), Some(2), Some(3)))
      _ <- A.i_.OwnBb.i_?(Some(2)).query.get.map(_ ==> List(Some(2)))
    } yield ()
  }
}
