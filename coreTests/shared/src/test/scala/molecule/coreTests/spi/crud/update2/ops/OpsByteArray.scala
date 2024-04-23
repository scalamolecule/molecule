// Special case for byte Arrays!
//

package molecule.coreTests.spi.crud.update2.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuiteBase
import molecule.coreTests.util.Array2List
import utest._
import scala.concurrent.Future

trait OpsByteArray extends CoreTestSuiteBase with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.byteArray(Array(byte1, byte2, byte2)).save.transact.map(_.id)
        _ <- Ns.byteArray.query.get.map(_.head ==> Array(byte1, byte2, byte2))

        // Applying Byte Array replaces previous Array
        _ <- Ns(id).byteArray(Array(byte3, byte4, byte4)).update.transact
        _ <- Ns.byteArray.query.get.map(_.head ==> Array(byte3, byte4, byte4))

        // Applying empty Byte Array deletes previous Array
        _ <- Ns(id).byteArray(Array.empty[Byte]).update.transact
        _ <- Ns.byteArray.query.get.map(_ ==> Nil)

        id <- Ns.byteArray(Array(byte1, byte2, byte2)).save.transact.map(_.id)
        // Applying empty value deletes previous Byte Array
        _ <- Ns(id).byteArray().update.transact
        _ <- Ns.byteArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.byteArray(Array(byte1)).save.transact.map(_.id)

        // Operations on byte arrays not allowed
        _ <- Future(compileError("Ns(id).byteArray.add(byte2)"))

      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.byteArray(Array(byte1, byte2)).save.transact.map(_.id)

        // Operations on byte arrays not allowed
        _ <- Future(compileError("Ns(id).byteArray.remove(byte2)"))
      } yield ()
    }
  }
}
