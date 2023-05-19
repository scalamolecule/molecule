package molecule.coreTests

import molecule.core.api.Connection
import molecule.core.util.Executor._
import molecule.coreTests.api.CoreTestsApiAsync
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import utest._
import scala.language.implicitConversions

object Adhoc extends CoreTestsApiAsync {

  def aa(types: (Connection => Any) => Any)
//        (implicit api: saveApiAsync[Any])
  : Any = {
    types { implicit conn =>
      for {
        _ <- Ns.int(3).save.transact
        _ <- Ns.int.query.get.map(_ ==> List(4))
      } yield ()
    }
  }
}
