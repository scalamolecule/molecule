package molecule.db.compliance.test.filterAttr.one

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Semantics(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Compare to filter attribute of same entity" - types {
    for {
      _ <- Entity.i.int.insert(
        (1, 1),
        (2, 3)
      ).transact

      // Compare Entity.i to filter attribute Entity.int_
      _ <- Entity.i(Entity.int_).query.get.map(_ ==> List(1))
    } yield ()
  }


  "Only tacit filter attributes allowed" - types {
    for {
      _ <- Entity.s.i.Ref.i.s.insert(
        ("a", 1, 2, "x"),
        ("b", 4, 3, "y"),
      ).transact

      _ <- Entity.s.i.>(Ref.i).Ref.i.s
        .query.i.get.map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attribute Ref.i should be tacit."
        }

      // Note that when a compared mandatory attribute (Ref.i) points to no outer attribute,
      // a subquery is build instead with no correlation to the outer query.
      _ <- Entity.s.i.>(Ref.i).query.get.map(_ ==> List(
        ("b", 4, 2), // Ref.i == 2
        ("b", 4, 3), // Ref.i == 3
      ))
    } yield ()
  }


  "No operations on filter attributes allowed" - types {
    compileErrors("Entity.i(Entity.int_(3))")
    compileErrors("Entity.i(Entity.int_.not(3))")
    compileErrors("Entity.i(Entity.int_.>(3))")
    compileErrors("Entity.i(Entity.int_.>=(3))")
    compileErrors("Entity.i(Entity.int_.<(3))")
    compileErrors("Entity.i(Entity.int_.<=(3))")

    compileErrors("Entity.i(Ref.int_(3)).Ref.int_")
    compileErrors("Entity.i(Ref.int_.not(3)).Ref.int_")
    compileErrors("Entity.i(Ref.int_.>(3)).Ref.int_")
    compileErrors("Entity.i(Ref.int_.>=(3)).Ref.int_")
    compileErrors("Entity.i(Ref.int_.<(3)).Ref.int_")
    compileErrors("Entity.i(Ref.int_.<=(3)).Ref.int_")

    // Add operation to explicit filter attribute instead
    Entity.i(Entity.int_).int_(3)
    Entity.i(Entity.int_).int_.not(3)
    Entity.i(Entity.int_).int_.>(3)
    Entity.i(Entity.int_).int_.>=(3)
    Entity.i(Entity.int_).int_.<(3)
    Entity.i(Entity.int_).int_.<=(3)

    // or Ref filter attribute...
    Entity.i(Ref.int_).Ref.int_(3)
    Entity.i(Ref.int_).Ref.int_.not(3)
    Entity.i(Ref.int_).Ref.int_.>(3)
    Entity.i(Ref.int_).Ref.int_.>=(3)
    Entity.i(Ref.int_).Ref.int_.<(3)
    Entity.i(Ref.int_).Ref.int_.<=(3)
  }


  "Allowed card-one comparisons with filter attribute" - types {
    // Equal to
    Entity.i.apply(Entity.int_)
    // same as
    Entity.i(Entity.int_)

    Entity.i.not(Entity.int_)
    Entity.i.<(Entity.int_)
    Entity.i.<=(Entity.int_)
    Entity.i.>(Entity.int_)
    Entity.i.>=(Entity.int_)

    // Tacit left side attribute
    Entity.s.i_(Entity.int_)
    Entity.s.i_.not(Entity.int_)
    Entity.s.i_.<(Entity.int_)
    Entity.s.i_.<=(Entity.int_)
    Entity.s.i_.>(Entity.int_)
    Entity.s.i_.>=(Entity.int_)
  }

  "Allowed card Set comparisons with filter attribute" - types {
    Entity.iSet.has(Entity.int_)
    Entity.iSet.hasNo(Entity.int_)

    Entity.s.iSet_.has(Entity.int_)
    Entity.s.iSet_.hasNo(Entity.int_)
  }

  "Allowed card Seq comparisons with filter attribute" - types {
    Entity.iSeq.has(Entity.int_)
    Entity.iSeq.hasNo(Entity.int_)

    Entity.s.iSeq_.has(Entity.int_)
    Entity.s.iSeq_.hasNo(Entity.int_)
  }


  "Byte arrays not allowed to filter" - types {
    compileErrors("Entity.byteArray(Ref.byteArray_).Ref.byteArray")
    compileErrors("Entity.byteArray.not(Ref.byteArray_).Ref.byteArray")
    compileErrors("Entity.s.byteArray_(Ref.byteArray_).Ref.byteArray")
    compileErrors("Entity.s.byteArray_.not(Ref.byteArray_).Ref.byteArray")
  }


  "Missing filter attributes" - types {
    for {
      _ <- Entity.i.Ref.int.insert(
        (1, 1),
        (2, 3)
      ).transact

      _ <- Entity.int(Ref.int_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please add missing filter attribute Ref.int"
        }

      // Filter attribute
      _ <- Entity.i(Ref.int_).Ref.int.query.get.map(_ ==> List((1, 1)))
    } yield ()
  }


  "Can't filter by same attribute" - types {
    for {
      _ <- Entity.s.i(Entity.i_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't filter by the same attribute `Entity.i`"
        }
    } yield ()
  }
}
