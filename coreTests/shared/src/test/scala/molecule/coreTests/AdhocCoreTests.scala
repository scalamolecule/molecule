package molecule.coreTests

import molecule.core.api.{ApiAsync2, ApiAsyncGateway}
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions

trait AdhocCoreTests extends TestSuite with CoreTestSuite with ApiAsyncGateway { self: ApiAsync2  =>

  override  val tests = Tests {

    "types core" - types { implicit conn =>
      for {
//        _ <- Ns.i(3).save.transact
        _ <- Ns.i.insert.apply(3).transact
//        _ <- Ns.i.query.get.map(_ ==> List(3))
//        _ <- Future(1)
      } yield ()
    }

    "types core2" - types { implicit conn =>
      for {
//        _ <- Ns.i(3).save.transact
        _ <- Ns.i.insert.apply(3).transact
//        _ <- Ns.i.query.get.map(_ ==> List(3))
//        _ <- Future(1)
      } yield ()
    }

//        "basic" -  {
//          1 ==> 2
//        }
  }
}
