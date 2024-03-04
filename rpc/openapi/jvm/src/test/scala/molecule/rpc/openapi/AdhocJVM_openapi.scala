package molecule.rpc.openapi

import molecule.rpc.openapi.dsl.Starwars._
import molecule.core.util.Executor._
import molecule.rpc.openapi.async._
import molecule.rpc.openapi.setup.TestSuite_openapi
import utest._
import scala.language.implicitConversions

object AdhocJVM_openapi extends TestSuite_openapi {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      for {

        _ <- Future(42)


      } yield ()
    }
  }
}