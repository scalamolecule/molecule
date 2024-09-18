package molecule.coreTests.spi.filter.one.special

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOneSpecial_Number extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Int" - types { implicit conn =>
      for {
        _ <- Ns.i.int.insert(
          (1, 1),
          (2, 2),
          (3, 3),
          (4, 4),
          (5, 5),
          (6, 6),
          (7, 7),
          (8, 8),
          (9, 9),
        ).transact

        // Mandatory

        _ <- Ns.int.%(2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.int.%(2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.int.%(3, 0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.int.%(3, 1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.int.%(3, 2).query.get.map(_ ==> List(2, 5, 8))

        _ <- Ns.int.even.query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.int.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))

        // Tacit

        _ <- Ns.i.int_.%(2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.int_.%(2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.i.int_.%(3, 0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.i.int_.%(3, 1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.i.int_.%(3, 2).query.get.map(_ ==> List(2, 5, 8))

        _ <- Ns.i.int_.even.query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.int_.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))

        // Complex filtering with multiple tacit filters
        _ <- Ns.i.a1.int_.>(2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
        _ <- Ns.i.a1.int_.>(2).int_.<=(8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
        _ <- Ns.i.a1.int_.>(2).int_.<=(8).int_.not(4, 5).query.get.map(_ ==> List(3, 6, 7, 8))
        _ <- Ns.i.a1.int_.>(2).int_.<=(8).int_.not(4, 5).int_.odd.query.get.map(_ ==> List(3, 7))
      } yield ()
    }

    "Int ref" - types { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Types._
      for {
        _ <- Ns.i.Ref.int.insert(
          (1, 1),
          (2, 2),
          (3, 3),
          (4, 4),
          (5, 5),
          (6, 6),
          (7, 7),
          (8, 8),
          (9, 9),
        ).transact

        // Mandatory

        _ <- Ns.Ref.int.%(2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.Ref.int.%(2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.Ref.int.%(3, 0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.Ref.int.%(3, 1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.Ref.int.%(3, 2).query.get.map(_ ==> List(2, 5, 8))

        _ <- Ns.Ref.int.even.query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.Ref.int.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))

        // Tacit

        _ <- Ns.i.Ref.int_.%(2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.Ref.int_.%(2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.i.Ref.int_.%(3, 0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.i.Ref.int_.%(3, 1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.i.Ref.int_.%(3, 2).query.get.map(_ ==> List(2, 5, 8))

        _ <- Ns.i.Ref.int_.even.query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.Ref.int_.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))

        // Complex filtering with multiple tacit filters
        _ <- Ns.i.a1.Ref.int_.>(2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
        _ <- Ns.i.a1.Ref.int_.>(2).int_.<=(8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
        _ <- Ns.i.a1.Ref.int_.>(2).int_.<=(8).int_.not(4, 5).query.get.map(_ ==> List(3, 6, 7, 8))
        _ <- Ns.i.a1.Ref.int_.>(2).int_.<=(8).int_.not(4, 5).int_.odd.query.get.map(_ ==> List(3, 7))
      } yield ()
    }


    "Long" - types { implicit conn =>
      for {
        _ <- Ns.i.long.insert(
          (1, 1),
          (2, 2),
          (3, 3),
          (4, 4),
          (5, 5),
          (6, 6),
          (7, 7),
          (8, 8),
          (9, 9),
        ).transact

        // Mandatory

        _ <- Ns.long.%(2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.long.%(2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.long.%(3, 0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.long.%(3, 1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.long.%(3, 2).query.get.map(_ ==> List(2, 5, 8))

        _ <- Ns.long.even.query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.long.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))

        // Tacit

        _ <- Ns.i.long_.%(2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.long_.%(2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.i.long_.%(3, 0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.i.long_.%(3, 1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.i.long_.%(3, 2).query.get.map(_ ==> List(2, 5, 8))

        _ <- Ns.i.long_.even.query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.long_.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))

        // Complex filtering with multiple tacit filters
        _ <- Ns.i.a1.long_.>(2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
        _ <- Ns.i.a1.long_.>(2).long_.<=(8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
        _ <- Ns.i.a1.long_.>(2).long_.<=(8).long_.not(4, 5).query.get.map(_ ==> List(3, 6, 7, 8))
        _ <- Ns.i.a1.long_.>(2).long_.<=(8).long_.not(4, 5).long_.odd.query.get.map(_ ==> List(3, 7))
      } yield ()
    }


    "BigInt" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInt.insert(
          (1, 1),
          (2, 2),
          (3, 3),
          (4, 4),
          (5, 5),
          (6, 6),
          (7, 7),
          (8, 8),
          (9, 9),
        ).transact

        // Mandatory

        _ <- Ns.bigInt.%(2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.bigInt.%(2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.bigInt.%(3, 0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.bigInt.%(3, 1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.bigInt.%(3, 2).query.get.map(_ ==> List(2, 5, 8))

        _ <- Ns.bigInt.even.query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.bigInt.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))

        // Tacit

        _ <- Ns.i.bigInt_.%(2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.bigInt_.%(2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.i.bigInt_.%(3, 0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.i.bigInt_.%(3, 1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.i.bigInt_.%(3, 2).query.get.map(_ ==> List(2, 5, 8))

        _ <- Ns.i.bigInt_.even.query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.bigInt_.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))

        // Complex filtering with multiple tacit filters
        _ <- Ns.i.a1.bigInt_.>(2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
        _ <- Ns.i.a1.bigInt_.>(2).bigInt_.<=(8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
        _ <- Ns.i.a1.bigInt_.>(2).bigInt_.<=(8).bigInt_.not(4, 5).query.get.map(_ ==> List(3, 6, 7, 8))
        _ <- Ns.i.a1.bigInt_.>(2).bigInt_.<=(8).bigInt_.not(4, 5).bigInt_.odd.query.get.map(_ ==> List(3, 7))
      } yield ()
    }


    "Byte" - types { implicit conn =>
      for {
        _ <- Ns.i.byte.insert(
          (1, 1),
          (2, 2),
          (3, 3),
          (4, 4),
          (5, 5),
          (6, 6),
          (7, 7),
          (8, 8),
          (9, 9),
        ).transact

        // Mandatory

        _ <- Ns.byte.%(2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.byte.%(2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.byte.%(3, 0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.byte.%(3, 1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.byte.%(3, 2).query.get.map(_ ==> List(2, 5, 8))

        _ <- Ns.byte.even.query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.byte.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))

        // Tacit

        _ <- Ns.i.byte_.%(2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.byte_.%(2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.i.byte_.%(3, 0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.i.byte_.%(3, 1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.i.byte_.%(3, 2).query.get.map(_ ==> List(2, 5, 8))

        _ <- Ns.i.byte_.even.query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.byte_.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))

        // Complex filtering with multiple tacit filters
        _ <- Ns.i.a1.byte_.>(byte2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
        _ <- Ns.i.a1.byte_.>(byte2).byte_.<=(byte8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
        _ <- Ns.i.a1.byte_.>(byte2).byte_.<=(byte8).byte_.not(byte4, byte5).query.get.map(_ ==> List(3, 6, 7, 8))
        _ <- Ns.i.a1.byte_.>(byte2).byte_.<=(byte8).byte_.not(byte4, byte5).byte_.odd.query.get.map(_ ==> List(3, 7))
      } yield ()
    }


    "Short" - types { implicit conn =>
      for {
        _ <- Ns.i.short.insert(
          (1, 1),
          (2, 2),
          (3, 3),
          (4, 4),
          (5, 5),
          (6, 6),
          (7, 7),
          (8, 8),
          (9, 9),
        ).transact

        // Mandatory

        _ <- Ns.short.%(2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.short.%(2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.short.%(3, 0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.short.%(3, 1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.short.%(3, 2).query.get.map(_ ==> List(2, 5, 8))

        _ <- Ns.short.even.query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.short.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))

        // Tacit

        _ <- Ns.i.short_.%(2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.short_.%(2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))

        _ <- Ns.i.short_.%(3, 0).query.get.map(_ ==> List(3, 6, 9))
        _ <- Ns.i.short_.%(3, 1).query.get.map(_ ==> List(1, 4, 7))
        _ <- Ns.i.short_.%(3, 2).query.get.map(_ ==> List(2, 5, 8))

        _ <- Ns.i.short_.even.query.get.map(_ ==> List(2, 4, 6, 8))
        _ <- Ns.i.short_.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))

        // Complex filtering with multiple tacit filters
        _ <- Ns.i.a1.short_.>(short2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
        _ <- Ns.i.a1.short_.>(short2).short_.<=(short8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
        _ <- Ns.i.a1.short_.>(short2).short_.<=(short8).short_.not(short4, short5).query.get.map(_ ==> List(3, 6, 7, 8))
        _ <- Ns.i.a1.short_.>(short2).short_.<=(short8).short_.not(short4, short5).short_.odd.query.get.map(_ ==> List(3, 7))
      } yield ()
    }
  }
}
