package molecule.rest

import molecule.rest.dsl.Starwars._
import molecule.core.util.Executor._
import molecule.rest.async._
import molecule.rest.setup.TestSuite_rest
import utest._
import scala.language.implicitConversions

object AdhocJVM_rest extends TestSuite_rest {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      for {


        _ <- Future(42)



      } yield ()
    }
  }
}