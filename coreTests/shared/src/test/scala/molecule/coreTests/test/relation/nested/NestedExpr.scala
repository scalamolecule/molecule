package molecule.coreTests.test.relation.nested

import molecule.base.error.ModelError
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._


trait NestedExpr extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>


  override lazy val tests = Tests {

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


    "Expression inside optional nested not allowed" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i).insert(List((1, List(1, 2, 3)))).transact

        // Expression before optional nested ok
        _ <- A.i(1).Bb.*?(B.i).query.get.map(_ ==> List((1, List(1, 2, 3))))

        // Expressions inside optional nested not allowed

        _ <- A.i_.Bb.*?(B.i(1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Expressions not allowed in optional nested data structure. Found:\n  " +
            """AttrOneManInt("B", "i", Eq, Seq(1), None, None, Nil, Nil, None, None)"""
        }

        _ <- A.i_.Bb.*?(B.i.<(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Expressions not allowed in optional nested data structure. Found:\n  " +
            """AttrOneManInt("B", "i", Lt, Seq(2), None, None, Nil, Nil, None, None)"""
        }

        _ <- A.i_.Bb.*?(B.i.<=(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Expressions not allowed in optional nested data structure. Found:\n  " +
            """AttrOneManInt("B", "i", Le, Seq(2), None, None, Nil, Nil, None, None)"""
        }

        _ <- A.i_.Bb.*?(B.i.>(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Expressions not allowed in optional nested data structure. Found:\n  " +
            """AttrOneManInt("B", "i", Gt, Seq(2), None, None, Nil, Nil, None, None)"""
        }

        _ <- A.i_.Bb.*?(B.i.>=(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Expressions not allowed in optional nested data structure. Found:\n  " +
            """AttrOneManInt("B", "i", Ge, Seq(2), None, None, Nil, Nil, None, None)"""
        }
      } yield ()
    }
  }
}