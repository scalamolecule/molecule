package molecule.db.compliance.test.filter.seq.types

import molecule.db.compliance.setup.*
import molecule.db.base.error.ModelError
import molecule.db.compliance.setup.{DbProviders, MUnitSuiteWithArrays, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*

case class FilterSeq_ByteArray(
  suite: MUnitSuiteWithArrays,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory: attr" - types { implicit conn =>
    val a = (1, Array(byte1, byte2))
    val b = (2, Array(byte2, byte3, byte3))
    for {
      _ <- Entity.i.byteArray.insert(List(a, b)).transact
      _ <- Entity.i.a1.byteArray.query.get.map(_ ==> List(a, b))
    } yield ()
  }

  "Mandatory: equal" - types { implicit conn =>
    val a = (1, Array(byte1, byte2))
    val b = (2, Array(byte2, byte3, byte3))
    for {
      _ <- Entity.i.byteArray.insert(List(a, b)).transact

      // Exact matches
      _ <- Entity.i.byteArray(Array(byte1)).query.get.map(_ ==> Nil)
      _ <- Entity.i.byteArray(Array(byte1, byte2)).query.get.map(_ ==> List(a))
      _ <- Entity.i.byteArray(Array(byte1, byte2, byte3)).query.get.map(_ ==> Nil)

      // Empty Byte array matches nothing
      _ <- Entity.i.byteArray(Array.empty[Byte]).query.get.map(_ ==> Nil)

      // Applying nothing matches nothing
      _ <- Entity.i.byteArray().query.get.map(_ ==> Nil)
    } yield ()
  }

  "Mandatory: not equal" - types { implicit conn =>
    val a = (1, Array(byte1, byte2))
    val b = (2, Array(byte2, byte3, byte3))
    for {
      _ <- Entity.i.byteArray.insert(List(a, b)).transact

      // NOT semantics
      _ <- Entity.i.a1.byteArray.not(Array(byte1)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteArray.not(Array(byte1, byte2)).query.get.map(_ ==> List(b)) // exclude exact match
      _ <- Entity.i.a1.byteArray.not(Array(byte1, byte2, byte3)).query.get.map(_ ==> List(a, b))

      // Negating empty byte array matches all
      _ <- Entity.i.a1.byteArray.not(Array.empty[Byte]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: attr" - types { implicit conn =>
    val a = (1, Array(byte1, byte2))
    val b = (2, Array(byte2, byte3, byte3))
    for {
      _ <- Entity.i.byteArray.insert(List(a, b)).transact
      _ <- Entity.i.a1.byteArray_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  "Tacit: equal" - types { implicit conn =>
    val a = (1, Array(byte1, byte2))
    val b = (2, Array(byte2, byte3, byte3))
    for {
      _ <- Entity.i.byteArray.insert(List(a, b)).transact

      // Exact matches
      _ <- Entity.i.byteArray_(Array(byte1)).query.get.map(_ ==> Nil)
      _ <- Entity.i.byteArray_(Array(byte1, byte2)).query.get.map(_ ==> List(1))
      _ <- Entity.i.byteArray_(Array(byte1, byte2, byte3)).query.get.map(_ ==> Nil)

      // Empty Byte array matches nothing
      _ <- Entity.i.byteArray_(Array.empty[Byte]).query.get.map(_ ==> Nil)

      // Applying nothing matches nothing
      _ <- Entity.i.byteArray_().query.get.map(_ ==> Nil)
    } yield ()
  }

  "Tacit: not equal" - types { implicit conn =>
    val a = (1, Array(byte1, byte2))
    val b = (2, Array(byte2, byte3, byte3))
    for {
      _ <- Entity.i.byteArray.insert(List(a, b)).transact

      // NOT semantics
      _ <- Entity.i.a1.byteArray_.not(Array(byte1)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteArray_.not(Array(byte1, byte2)).query.get.map(_ ==> List(2)) // exclude exact match
      _ <- Entity.i.a1.byteArray_.not(Array(byte1, byte2, byte3)).query.get.map(_ ==> List(1, 2))

      // Negating empty byte array matches all
      _ <- Entity.i.a1.byteArray_.not(Array.empty[Byte]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional: attr" - types { implicit conn =>
    val a = (1, Some(Array(byte1, byte2)))
    val b = (2, Some(Array(byte2, byte3, byte3)))
    val c = (3, None)
    for {
      _ <- Entity.i.byteArray_?.insert(a, b, c).transact
      _ <- Entity.i.a1.byteArray_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }

  "Optional: no filters on optional byte arrays" - types { implicit conn =>
    val a = (1, Some(Array(byte1, byte2)))
    val b = (2, Some(Array(byte2, byte3, byte3)))
    val c = (3, None)
    for {
      _ <- Entity.i.byteArray_?.insert(a, b, c).transact

      _ <- Entity.i.a1.byteArray_?(Some(Array(byte1))).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Byte arrays (Entity.byteArray) can only be retrieved as-is. Filters not allowed."
        }

      _ <- Entity.i.a1.byteArray_?(Option.empty[Array[Byte]]).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Byte arrays (Entity.byteArray) can only be retrieved as-is. Filters not allowed."
        }
    } yield ()
  }
}