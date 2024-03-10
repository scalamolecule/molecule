// Special case for byte Arrays!
//

package molecule.coreTests.spi.crud.update.array.ops

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateArrayOps_Byte extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.byteArray(Array(byte1, byte2, byte2)).save.transact.map(_.id)
        _ <- Ns.byteArray.query.get.map(_.head ==> Array(byte1, byte2, byte2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).byteArray(Array(byte3, byte4, byte4)).update.transact
        _ <- Ns.byteArray.query.get.map(_.head ==> Array(byte3, byte4, byte4))

        // Applying empty Array of values deletes previous Array
        _ <- Ns(id).byteArray(Seq.empty[Byte]).update.transact
        _ <- Ns.byteArray.query.get.map(_ ==> Nil)

        id <- Ns.byteArray(Array(byte1, byte2, byte2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).byteArray().update.transact
        _ <- Ns.byteArray.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.byteArray(Array(byte1)).save.transact.map(_.id)

        // Operations on byte arrays not allowed
        _ <- Ns(id).byteArray.add(byte2).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Operations on byte arrays (Ns.byteArray) not allowed."
          }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.byteArray(Array(byte1, byte2)).save.transact.map(_.id)

        // Operations on byte arrays not allowed
        _ <- Ns(id).byteArray.remove(byte2).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Operations on byte arrays (Ns.byteArray) not allowed."
          }
      } yield ()
    }
  }
}
