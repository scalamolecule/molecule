package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.{CoreTestSuite, CoreTestSuiteBase}
import molecule.coreTests.util.Array2List
import utest._
import scala.concurrent.Future

trait FilterSeq_ByteArray extends CoreTestSuiteBase with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory/tacit" - {

      "attr" - types { implicit conn =>
        val a = (1, Array(byte1, byte2))
        val b = (2, Array(byte2, byte3, byte3))
        for {
          _ <- Ns.i.byteArray.insert(List(a, b)).transact

          _ <- Ns.i.a1.byteArray.query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.byteArray_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }

      "apply (equal)" - types { implicit conn =>
        val a = (1, Array(byte1, byte2))
        val b = (2, Array(byte2, byte3, byte3))
        for {
          _ <- Ns.i.byteArray.insert(List(a, b)).transact

          // Exact matches
          _ <- Ns.i.byteArray(Array(byte1)).query.get.map(_ ==> Nil)
          _ <- Ns.i.byteArray(Array(byte1, byte2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.byteArray(Array(byte1, byte2, byte3)).query.get.map(_ ==> Nil)

          // Empty Byte array matches nothing
          _ <- Ns.i.byteArray(Array.empty[Byte]).query.get.map(_ ==> Nil)
          _ <- Ns.i.byteArray_(Array.empty[Byte]).query.get.map(_ ==> Nil)

          // Applying nothing matches nothing
          _ <- Ns.i.byteArray().query.get.map(_ ==> Nil)
          _ <- Ns.i.byteArray_().query.get.map(_ ==> Nil)
        } yield ()
      }

      "not equal" - types { implicit conn =>
        val a = (1, Array(byte1, byte2))
        val b = (2, Array(byte2, byte3, byte3))
        for {
          _ <- Ns.i.byteArray.insert(List(a, b)).transact

          // NOT semantics
          _ <- Ns.i.a1.byteArray.not(Array(byte1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteArray.not(Array(byte1, byte2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.byteArray.not(Array(byte1, byte2, byte3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Seqs
          _ <- Ns.i.a1.byteArray.not(Array.empty[Byte]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Array(byte1, byte2)))
        val b = (2, Some(Array(byte2, byte3, byte3)))
        val c = (3, None)
        for {
          _ <- Ns.i.byteArray_?.insert(a, b, c).transact

          _ <- Ns.i.a1.byteArray_?.query.get.map(_ ==> List(a, b, c))
        } yield ()
      }

      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Array(byte1, byte2)))
        val b = (2, Some(Array(byte2, byte3, byte3)))
        val c = (3, None)
        for {
          _ <- Ns.i.byteArray_?.insert(a, b, c).transact

          _ <- Ns.i.a1.byteArray_?(Some(Array(byte1))).query.get.map(_ ==> Nil)
          _ <- Ns.i.a1.byteArray_?(Some(Array(byte1, byte2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteArray_?(Some(Array(byte1, byte2, byte3))).query.get.map(_ ==> Nil)

          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.byteArray_?(Some(Array.empty[Byte])).query.get.map(_ ==> Nil)

          // None matches non-asserted values
          _ <- Ns.i.a1.byteArray_?(Option.empty[Array[Byte]]).query.get.map(_ ==> Array(c))
        } yield ()
      }
    }
  }
}