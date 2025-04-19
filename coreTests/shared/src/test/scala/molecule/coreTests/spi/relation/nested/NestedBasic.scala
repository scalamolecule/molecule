package molecule.coreTests.spi.relation.nested

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Refs.*
import molecule.coreTests.setup.*


case class NestedBasic(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory/optional rows" - refs { implicit conn =>
    for {
      _ <- A.s.Bb.*(B.i).insert(
        ("a", List(1, 2)),
        ("b", Nil),
      ).transact

      // Mandatory nested data
      _ <- A.s.Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("a", List(1, 2)),
      ))

      // Optional nested data
      _ <- A.s.a1.Bb.*?(B.i.a1).query.get.map(_ ==> List(
        ("a", List(1, 2)),
        ("b", Nil),
      ))
    } yield ()
  }


  "Nested: one" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs.*
    for {
      _ <- A.i.Bb.*(B.i).insert(2, List(3, 4)).transact
      _ <- A.i.Bb.*(B.i.a1).query.get.map(_ ==> List((2, List(3, 4))))
    } yield ()
  }

  "Nested: set" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs.*
    for {
      _ <- A.i.Bb.*(B.iSet).insert(List((2, List(Set(3, 4))))).transact
      _ <- A.i.Bb.*(B.iSet).query.get.map(_ ==> List((2, List(Set(3, 4)))))
    } yield ()
  }


  "Nested owned: one" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs.*
    for {
      _ <- A.i.OwnBb.*(B.i).insert(2, List(3, 4)).transact
      _ <- A.i.OwnBb.*(B.i.a1).query.get.map(_ ==> List((2, List(3, 4))))
    } yield ()
  }

  "Nested owned: set" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs.*
    for {
      _ <- A.i.OwnBb.*(B.iSet).insert(List((2, List(Set(3, 4))))).transact
      _ <- A.i.OwnBb.*(B.iSet).query.get.map(_ ==> List((2, List(Set(3, 4)))))
    } yield ()
  }
}
