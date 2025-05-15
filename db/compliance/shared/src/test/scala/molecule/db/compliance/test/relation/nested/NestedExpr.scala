package molecule.db.compliance.test.relation.nested

import molecule.db.base.error.ModelError
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*


case class NestedExpr(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Expressions in nested" - refs { implicit conn =>
    for {
      _ <- A.i.Bb.*(B.i).insert(List((1, List(1, 2, 3)))).transact

      _ <- A.i_.Bb.*(B.i.a1).query.get.map(_ ==> List(List(1, 2, 3)))
      _ <- A.i_.Bb.*(B.i(1).a1).query.get.map(_ ==> List(List(1)))
      _ <- A.i_.Bb.*(B.i(1, 2).a1).query.get.map(_ ==> List(List(1, 2)))
      _ <- A.i_.Bb.*(B.i.not(1).a1).query.get.map(_ ==> List(List(2, 3)))
      _ <- A.i_.Bb.*(B.i.not(1, 2).a1).query.get.map(_ ==> List(List(3)))
      _ <- A.i_.Bb.*(B.i.<(2).a1).query.get.map(_ ==> List(List(1)))
      _ <- A.i_.Bb.*(B.i.<=(2).a1).query.get.map(_ ==> List(List(1, 2)))
      _ <- A.i_.Bb.*(B.i.>(2).a1).query.get.map(_ ==> List(List(3)))
      _ <- A.i_.Bb.*(B.i.>=(2).a1).query.get.map(_ ==> List(List(2, 3)))
    } yield ()
  }


  "Expression inside optional nested" - refs { implicit conn =>
    if (database == "datomic") {
      // Seems not possible to add expressions inside Datomic nested pulls
      for {
        _ <- A.i.Bb.*(B.i).insert(List((1, List(1, 2, 3)))).transact

        // Expression before optional nested ok
        _ <- A.i(1).Bb.*?(B.i).query.get.map(_ ==> List((1, List(1, 2, 3))))

        // Expressions inside optional nested not allowed

        _ <- A.i_.Bb.*?(B.i(1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Expressions not allowed in optional nested queries (B.i)."
          }

        _ <- A.i_.Bb.*?(B.i.<(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Expressions not allowed in optional nested queries (B.i)."
          }

        _ <- A.i_.Bb.*?(B.i.<=(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Expressions not allowed in optional nested queries (B.i)."
          }

        _ <- A.i_.Bb.*?(B.i.>(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Expressions not allowed in optional nested queries (B.i)."
          }

        _ <- A.i_.Bb.*?(B.i.>=(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Expressions not allowed in optional nested queries (B.i)."
          }
      } yield ()

    } else {
      // In SQL, expressions in optional nested queries is ok
      for {
        _ <- A.i.Bb.*(B.i).insert(List((1, List(1, 2, 3)))).transact

        _ <- A.i(1).Bb.*?(B.i.a1).query.get.map(_ ==> List((1, List(1, 2, 3))))
        _ <- A.i_.Bb.*?(B.i(1)).query.get.map(_ ==> List(List(1)))
        _ <- A.i_.Bb.*?(B.i.<(2)).query.get.map(_ ==> List(List(1)))
        _ <- A.i_.Bb.*?(B.i.<=(2).a1).query.get.map(_ ==> List(List(1, 2)))
        _ <- A.i_.Bb.*?(B.i.>(2)).query.get.map(_ ==> List(List(3)))
        _ <- A.i_.Bb.*?(B.i.>=(2)).query.get.map(_ ==> List(List(2, 3)))
      } yield ()
    }
  }
}
