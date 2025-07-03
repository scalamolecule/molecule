package molecule.db.compliance.test.filterAttr.one

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Semantics(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Compare to filter attribute of same entity" - types { implicit conn =>
    for {
      _ <- Entity.i.int.insert(
        (1, 1),
        (2, 3)
      ).transact

      // Compare Entity.i to filter attribute Entity.int_
      _ <- Entity.i(Entity.int_).query.get.map(_ ==> List(1))
    } yield ()
  }


  "Only tacit filter attributes allowed" - types { implicit conn =>
    // Tacit filter attribute allowed
    Entity.i(Entity.int_)

    // Mandatory and optional filter attributes not allowed
    compileErrors("Entity.i(Entity.int)")
    compileErrors("Entity.i(Entity.int_?)")
  }


  "No operations on filter attributes allowed" - types { implicit conn =>
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


  "Allowed card-one comparisons with filter attribute" - types { implicit conn =>
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

  "Allowed card Set comparisons with filter attribute" - types { implicit conn =>
    Entity.iSet.has(Entity.int_)
    Entity.iSet.hasNo(Entity.int_)

    Entity.s.iSet_.has(Entity.int_)
    Entity.s.iSet_.hasNo(Entity.int_)
  }

  "Allowed card Seq comparisons with filter attribute" - types { implicit conn =>
    Entity.iSeq.has(Entity.int_)
    Entity.iSeq.hasNo(Entity.int_)

    Entity.s.iSeq_.has(Entity.int_)
    Entity.s.iSeq_.hasNo(Entity.int_)
  }


  "Byte arrays not allowed to filter" - types { implicit conn =>
    compileErrors("Entity.byteArray(Ref.byteArray_).Ref.byteArray")
    compileErrors("Entity.byteArray.not(Ref.byteArray_).Ref.byteArray")
    compileErrors("Entity.s.byteArray_(Ref.byteArray_).Ref.byteArray")
    compileErrors("Entity.s.byteArray_.not(Ref.byteArray_).Ref.byteArray")
  }


  "Missing filter attributes" - types { implicit conn =>
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


  "Can't filter by same attribute" - types { implicit conn =>
    for {
      _ <- Entity.s.i(Entity.i_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't filter by the same attribute `Entity.i`"
        }
    } yield ()
  }
}
