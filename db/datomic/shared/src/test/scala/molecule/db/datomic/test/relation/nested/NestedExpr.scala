package molecule.db.datomic.test.relation.nested

import molecule.base.util.exceptions.MoleculeError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic.async._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedExpr extends DatomicTestSuite {


  lazy val tests = Tests {

    "Expressions in nested" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i).insert(List((1, List(1, 2, 3)))).transact

        _ <- Ns.i_.Rs1.*(R1.i.a1).query.get.map(_ ==> List(List(1, 2, 3)))
        _ <- Ns.i_.Rs1.*(R1.i(1).a1).query.get.map(_ ==> List(List(1)))
        _ <- Ns.i_.Rs1.*(R1.i(1, 2).a1).query.get.map(_ ==> List(List(1, 2)))
        _ <- Ns.i_.Rs1.*(R1.i.not(1).a1).query.get.map(_ ==> List(List(2, 3)))
        _ <- Ns.i_.Rs1.*(R1.i.not(1, 2).a1).query.get.map(_ ==> List(List(3)))
        _ <- Ns.i_.Rs1.*(R1.i.<(2).a1).query.get.map(_ ==> List(List(1)))
        _ <- Ns.i_.Rs1.*(R1.i.<=(2).a1).query.get.map(_ ==> List(List(1, 2)))
        _ <- Ns.i_.Rs1.*(R1.i.>(2).a1).query.get.map(_ ==> List(List(3)))
        _ <- Ns.i_.Rs1.*(R1.i.>=(2).a1).query.get.map(_ ==> List(List(2, 3)))
      } yield ()
    }


    "Expression inside optional nested not allowed" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i).insert(List((1, List(1, 2, 3)))).transact

        // Expression before optional nested ok
        _ <- Ns.i(1).Rs1.*?(R1.i).query.get.map(_ ==> List((1, List(1, 2, 3))))

        // Expressions inside optional nested not allowed

        _ <- Ns.i_.Rs1.*?(R1.i(1)).query.get
          .map(_ ==> "Unexpected success 1").recover { case MoleculeError(err, _) =>
          err ==> "Expressions not allowed in optional nested data structure. Found:\n" +
            """AttrOneManInt("R1", "i", Appl, Seq(1), None, None, None)"""
        }

        _ <- Ns.i_.Rs1.*?(R1.i.<(2)).query.get
          .map(_ ==> "Unexpected success 2").recover { case MoleculeError(err, _) =>
          err ==> "Expressions not allowed in optional nested data structure. Found:\n" +
            """AttrOneManInt("R1", "i", Lt, Seq(2), None, None, None)"""
        }

        _ <- Ns.i_.Rs1.*?(R1.i.<=(2)).query.get
          .map(_ ==> "Unexpected success 3").recover { case MoleculeError(err, _) =>
          err ==> "Expressions not allowed in optional nested data structure. Found:\n" +
            """AttrOneManInt("R1", "i", Le, Seq(2), None, None, None)"""
        }

        _ <- Ns.i_.Rs1.*?(R1.i.>(2)).query.get
          .map(_ ==> "Unexpected success 4").recover { case MoleculeError(err, _) =>
          err ==> "Expressions not allowed in optional nested data structure. Found:\n" +
            """AttrOneManInt("R1", "i", Gt, Seq(2), None, None, None)"""
        }

        _ <- Ns.i_.Rs1.*?(R1.i.>=(2)).query.get
          .map(_ ==> "Unexpected success 5").recover { case MoleculeError(err, _) =>
          err ==> "Expressions not allowed in optional nested data structure. Found:\n" +
            """AttrOneManInt("R1", "i", Ge, Seq(2), None, None, None)"""
        }
      } yield ()
    }
  }
}
