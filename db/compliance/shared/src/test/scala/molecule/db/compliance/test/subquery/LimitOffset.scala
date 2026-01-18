package molecule.db.compliance.test.subquery

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class LimitOffset(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Correlated subquery with limit/offset" - types {
    for {
      _ <- Entity.s.Refs.*(Ref.i).insert(
        ("a", List(1, 2, 3)),
        ("b", List(4, 5)),
        ("c", List()),
      ).transact


      // Correlated - highest Ref.i per entity (just 1-1 correlation)
      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_)).query.get.map(_ ==> List(
        ("a", 3),
        ("a", 2),
        ("a", 1),
        ("b", 5),
        ("b", 4),
      ))

      // limit(3) - all in this case

      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.limit(3)).query.get.map(_ ==> List(
        ("a", 3),
        ("a", 2),
        ("a", 1),
        ("b", 5),
        ("b", 4),
      ))
      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.offset(1).limit(3)).query.get.map(_ ==> List(
        // ("a", 3),
        ("a", 2),
        ("a", 1),
        // ("b", 5),
        ("b", 4),
      ))
      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.offset(2).limit(3)).query.get.map(_ ==> List(
        // ("a", 3),
        // ("a", 2),
        ("a", 1),
        // ("b", 5),
        // ("b", 4),
      ))


      // limit(2)

      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.limit(2)).query.get.map(_ ==> List(
        ("a", 3),
        ("a", 2),
        // ("a", 1),
        ("b", 5),
        ("b", 4),
      ))
      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.offset(1).limit(2)).query.get.map(_ ==> List(
        // ("a", 3),
        ("a", 2),
        ("a", 1),
        // ("b", 5),
        ("b", 4),
      ))
      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.offset(2).limit(2)).query.get.map(_ ==> List(
        // ("a", 3),
        // ("a", 2),
        ("a", 1),
        // ("b", 5),
        // ("b", 4),
      ))


      // limit(1)

      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.limit(1)).query.get.map(_ ==> List(
        ("a", 3),
        // ("a", 2),
        // ("a", 1),
        ("b", 5),
        // ("b", 4),
      ))
      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.offset(1).limit(1)).query.get.map(_ ==> List(
        // ("a", 3),
        ("a", 2),
        // ("a", 1),
        // ("b", 5),
        ("b", 4),
      ))
      _ <- Entity.s.a1.join(Ref.i.d1.entity_(Entity.id_).query.offset(2).limit(1)).query.get.map(_ ==> List(
        // ("a", 3),
        // ("a", 2),
        ("a", 1),
        // ("b", 5),
        // ("b", 4),
      ))
    } yield ()
  }
}
