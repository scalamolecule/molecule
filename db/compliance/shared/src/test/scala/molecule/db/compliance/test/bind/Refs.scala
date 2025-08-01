package molecule.db.compliance.test.bind

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Refs(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Card-one ref" - refs {
    for {
      _ <- A.i.B.i.insert(
        (1, 1),
        (2, 2),
        (3, 3),
      ).transact

      eq = A.i_.B.i(?).a1.query
      _ <- eq(2).get.map(_ ==> List(2))
      // Same as this shortened version
      _ <- A.i_.B.i(?).a1.query(2).get.map(_ ==> List(2))

      _ <- A.i_.B.i.not(?).a1.query(2).get.map(_ ==> List(1, 3))
      _ <- A.i_.B.i.<(?).a1.query(2).get.map(_ ==> List(1))
      _ <- A.i_.B.i.>(?).a1.query(2).get.map(_ ==> List(3))
      _ <- A.i_.B.i.<=(?).a1.query(2).get.map(_ ==> List(1, 2))
      _ <- A.i_.B.i.>=(?).a1.query(2).get.map(_ ==> List(2, 3))

      _ <- A.i.a1.B.i_(?).query(2).get.map(_ ==> List(2))
      _ <- A.i.a1.B.i_.not(?).query(2).get.map(_ ==> List(1, 3))
      _ <- A.i.a1.B.i_.<(?).query(2).get.map(_ ==> List(1))
      _ <- A.i.a1.B.i_.>(?).query(2).get.map(_ ==> List(3))
      _ <- A.i.a1.B.i_.<=(?).query(2).get.map(_ ==> List(1, 2))
      _ <- A.i.a1.B.i_.>=(?).query(2).get.map(_ ==> List(2, 3))
    } yield ()
  }


  "Card-set ref" - refs {
    for {
      _ <- A.i.Bb.i.insert(
        (1, 1),
        (2, 2),
        (3, 3),
      ).transact

      _ <- A.i_.Bb.i(?).a1.query(2).get.map(_ ==> List(2))
      _ <- A.i_.Bb.i.not(?).a1.query(2).get.map(_ ==> List(1, 3))
      _ <- A.i_.Bb.i.<(?).a1.query(2).get.map(_ ==> List(1))
      _ <- A.i_.Bb.i.>(?).a1.query(2).get.map(_ ==> List(3))
      _ <- A.i_.Bb.i.<=(?).a1.query(2).get.map(_ ==> List(1, 2))
      _ <- A.i_.Bb.i.>=(?).a1.query(2).get.map(_ ==> List(2, 3))

      _ <- A.i.a1.Bb.i_(?).query(2).get.map(_ ==> List(2))
      _ <- A.i.a1.Bb.i_.not(?).query(2).get.map(_ ==> List(1, 3))
      _ <- A.i.a1.Bb.i_.<(?).query(2).get.map(_ ==> List(1))
      _ <- A.i.a1.Bb.i_.>(?).query(2).get.map(_ ==> List(3))
      _ <- A.i.a1.Bb.i_.<=(?).query(2).get.map(_ ==> List(1, 2))
      _ <- A.i.a1.Bb.i_.>=(?).query(2).get.map(_ ==> List(2, 3))
    } yield ()
  }
}
