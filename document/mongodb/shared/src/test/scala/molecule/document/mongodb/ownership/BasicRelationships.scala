package molecule.document.mongodb.ownership

import molecule.core.util.Executor._
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.language.implicitConversions


object BasicRelationships extends TestSuite_mongodb {


  override lazy val tests = Tests {

    "Flat embedded" - {

      "one-one" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.OwnB.i.insert(2, 3).i.transact
          _ <- A.i.OwnB.i.query.i.get.map(_ ==> List((2, 3)))
        } yield ()
      }

      "one-set" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.OwnB.ii.insert(List((2, Set(3, 4)))).i.transact
          _ <- A.i.OwnB.ii.query.i.get.map(_ ==> List((2, Set(3, 4))))
        } yield ()
      }

      "many-one" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.OwnBb.i.insert(2, 3).i.transact
          _ <- A.i.OwnBb.i.query.i.get.map(_ ==> List((2, 3)))
        } yield ()
      }

      "many-set" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.OwnBb.ii.insert(List((2, Set(3, 4)))).i.transact
          _ <- A.i.OwnBb.ii.query.i.get.map(_ ==> List((2, Set(3, 4))))
        } yield ()
      }
    }


    "Flat referenced" - {

      "one-one" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.B.i.insert(2, 3).i.transact
          _ <- A.i.B.i.query.i.get.map(_ ==> List((2, 3)))
        } yield ()
      }

      "one-set" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.B.ii.insert(List((2, Set(3, 4)))).i.transact
          _ <- A.i.B.ii.query.i.get.map(_ ==> List((2, Set(3, 4))))
        } yield ()
      }

      "many-one" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.Bb.i.insert(2, 3).i.transact
          _ <- A.i.Bb.i.query.i.get.map(_ ==> List((2, 3)))
        } yield ()
      }

      "many-set" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.Bb.ii.insert(List((2, Set(3, 4)))).i.transact
          _ <- A.i.Bb.ii.query.i.get.map(_ ==> List((2, Set(3, 4))))
        } yield ()
      }
    }


    "Nested embedded" - {

      "one" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.OwnBb.*(B.i).insert(2, List(3, 4)).i.transact
          _ <- A.i.OwnBb.*(B.i).query.i.get.map(_ ==> List((2, List(3, 4))))
        } yield ()
      }

      "set" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.OwnBb.*(B.ii).insert(List((2, List(Set(3, 4))))).i.transact
          _ <- A.i.OwnBb.*(B.ii).query.i.get.map(_ ==> List((2, List(Set(3, 4)))))
        } yield ()
      }
    }


    "Nested referenced" - {

      "one" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.Bb.*(B.i).insert(2, List(3, 4)).i.transact
          _ <- A.i.Bb.*(B.i).query.i.get.map(_ ==> List((2, List(3, 4))))
        } yield ()
      }

      "set" - refs { implicit conn =>
        import molecule.coreTests.dataModels.core.dsl.Refs._
        for {
          _ <- A.i.Bb.*(B.ii).insert(List((2, List(Set(3, 4))))).i.transact
          _ <- A.i.Bb.*(B.ii).query.i.get.map(_ ==> List((2, List(Set(3, 4)))))
        } yield ()
      }
    }
  }
}