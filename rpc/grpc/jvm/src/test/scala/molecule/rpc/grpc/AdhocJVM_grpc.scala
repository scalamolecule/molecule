package molecule.rpc.grpc

import molecule.rpc.grpc.dsl.Starwars._
import molecule.core.util.Executor._
import molecule.rpc.grpc.async._
import molecule.rpc.grpc.setup.TestSuite_grpc
import utest._
import scala.language.implicitConversions

object AdhocJVM_grpc extends TestSuite_grpc {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      for {

        _ <- Future(42)

      } yield ()
    }
  }
}