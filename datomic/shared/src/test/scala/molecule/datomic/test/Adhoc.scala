package molecule.datomic.test

import molecule.core.util.Executor._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._
import scala.language.implicitConversions
import molecule.coreTests.dataModels.core.dsl.Types._

object Adhoc extends DatomicTestSuite {
  //  // See Zio-http route
  //  abstract class RouteDecode[A](f: String => A) {
  //    def unapply(a: String): Option[A] =
  //      try {
  //        Option(f(a))
  //      } catch {
  //        case _: Throwable => None
  //      }
  //  }
  //
  //  object boolean extends RouteDecode(_.toBoolean)
  //  object byte extends RouteDecode(_.toByte)
  //  object short extends RouteDecode(_.toShort)
  //  object int extends RouteDecode(_.toInt)
  //  object long extends RouteDecode(_.toLong)
  //  object float extends RouteDecode(_.toFloat)
  //  object double extends RouteDecode(_.toDouble)
  ////  object uuid extends RouteDecode(str => UUID.fromString(str))
  ////  object date extends RouteDecode(str => LocalDate.parse(str))
  ////  object time extends RouteDecode(str => LocalDateTime.parse(str))
  ////  object Person {
  ////    lazy val name = RouteDecode(str => LocalDateTime.parse(str))
  ////  }
  //
  //
  ////Person.name(hej)
  //
  //  int(hej)

  //  object int {
  //    implicit class sync2api[Tpl](z: DatomicQueryApi[Tpl]) {
  //      def go: Int = 7
  //    }
  //  }
  //  object str {
  //    implicit class sync2api[Tpl](z: DatomicQueryApi[Tpl]) {
  //      def go: String = "hello"
  //    }
  //  }

  lazy val tests = Tests {

    "types" - types { implicit conn =>
      for {
        List(a, b) <- Ns.int.insert(1, 2).transact.map(_.eids)
        _ <- Ns.int(3).save.transact
        _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3))
        _ <- Ns(a).int(10).update.transact
        _ <- Ns(b).delete.transact
        _ <- Ns.int.query.get.map(_ ==> List(3, 10))
      } yield ()
    }



    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //        _ <- Ns.i.Tx(R2.i).insert(1, 2).transact
    //          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
    //          err ==>
    //            """Missing applied value for attribute:
    //              |AttrOneManInt("R2", "i", V, Seq(), None, None, None)""".stripMargin
    //        }
    //      } yield ()
    //    }
  }

}
