// Special case for byte Arrays!
//

package molecule.coreTests.spi.crud.update.seq.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._
import scala.concurrent.Future

trait UpdateByteArrayOps_Byte extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.byteSeq(Array(byte1, byte2, byte2)).save.transact.map(_.id)
        _ <- Ns.byteSeq.query.get.map(_.head ==> Array(byte1, byte2, byte2))

        // Applying Array of values replaces previous Array
        _ <- Ns(id).byteSeq(Array(byte3, byte4, byte4)).update.transact
        _ <- Ns.byteSeq.query.get.map(_.head ==> Array(byte3, byte4, byte4))

        // Applying empty Array of values deletes previous Array - todo...
        _ <- Ns(id).byteSeq(Array.empty[Byte]).update.transact
        _ <- Ns.byteSeq.query.get.map(_ ==> Nil)

        id <- Ns.byteSeq(Array(byte1, byte2, byte2)).save.transact.map(_.id)
        // Applying empty value deletes previous Array
        _ <- Ns(id).byteSeq().update.transact
        _ <- Ns.byteSeq.query.get.map(_ ==> Nil)
      } yield ()
    }


//    "add" - types { implicit conn =>
//      for {
//        id <- Ns.byteSeq(Array(byte1)).save.transact.map(_.id)
//
//        // Operations on byte arrays not allowed
//        _ <- Future(compileError("Ns(id).byteSeq.add(byte2)"))
//
//      } yield ()
//    }
//
//
//    "remove" - types { implicit conn =>
//      for {
//        id <- Ns.byteSeq(Array(byte1, byte2)).save.transact.map(_.id)
//
//        // Operations on byte arrays not allowed
//        _ <- Future(compileError("Ns(id).byteSeq.remove(byte2)"))
//      } yield ()
//    }
  }
}
