package molecule.sql.jdbc.test

import molecule.core.util.Executor._
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
import scala.annotation.nowarn
import scala.language.implicitConversions

object AdhocJdbcJVM extends JdbcTestSuite {

  val a = (1, Set(0, 1, 2), Set(1, 2, 3))
  val b = (2, Set(2, 3), Set(2, 3))
  val c = (3, Set(4), Set(3))

  @nowarn // (Allow pattern matching results without warnings)
  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
//        _ <- Ns.i(1).save.transact
//        _ <- Ns.i.query.get.map(_ ==> List(1))

        _ <- Ns.i.ii.ints.insert(a, b, c).transact

        _ <- Ns.i.ii.has(Set(2, 3)).ints.query.i.get.map(_ ==> List(b))
        _ <- Ns.i.ii.has(Ns.ints).query.i.get.map(_ ==> List(b))
        _ <- Ns.i.ii.has(Ns.ints).query.get.map(_ ==> List(b))
//        _ <- Ns.i.ii.has(Ns.ints_).query.get.map(_ ==> List((2, Set(2, 3)))) // Ns.ii
//        _ <- Ns.i.ii_.has(Ns.ints).query.get.map(_ ==> List((2, Set(2, 3)))) // Ref.ints
//        _ <- Ns.i.ii_.has(Ns.ints_).query.get.map(_ ==> List(2))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))


      } yield ()
    }


    "unique" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Uniques._
      for {
        _ <- Uniques.i(1).save.transact

      } yield ()
    }


    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        _ <- Require.int1.errorMsg.insert(
    //          (1, 2),
    //          (2, 2),
    //          (3, 2),
    //        ).transact
    //
    //        _ <- Variables.int1.errorMsg.query.inspect
    //        _ <- Variables.int1.<(Variables.errorMsg).query.inspect
    //        _ <- Variables.int1.<(Variables.errorMsg).query.get.map(_ ==> List())
    //
    //
    //      } yield ()
    //    }
  }
}

/*
// Possible Map attribute key/value accessors?

//  val name   = Map("en" -> "hello", "de" -> "hello", "da" -> "hej")
//  val name_k = Set("en", "de", "da") // unique keys
//  val name_v = Seq("hello", "hej") // non-unique values
//
//  val name_
//  val name_k_
//  val name_v_
//
//  val name_?
//  val name_k_?
//  val name_v_?
 */