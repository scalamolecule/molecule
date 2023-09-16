package molecule.coreTests.test.filterAttr.set

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait CrossNs extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  val a = (1, Set(1, 2), Set(1, 2, 3), 2)
  val b = (2, Set(2, 3), Set(2, 3), 3)
  val c = (3, Set(4), Set(3), 4)

  override lazy val tests = Tests {

    "equal (apply) - Sets that match other Sets" - types { implicit conn =>
      for {
        _ <- Ns.int.ints.Ref.ints.int.insert(a, b, c).transact
        _ <- Ns.int.ints(Ref.ints_).Ref.ints.int.query.get.map(_ ==> List(b))
        _ <- Ns.int.ints.Ref.ints(Ns.ints_).int.query.get.map(_ ==> List(b))
      } yield ()
    }


    "not equal - Sets that don't match other Sets" - types { implicit conn =>
      for {
        _ <- Ns.int.ints.Ref.ints.int.insert(a, b, c).transact
        _ <- Ns.int.ints.not(Ref.ints_).Ref.ints.int.query.get.map(_ ==> List(a, c))
        _ <- Ns.int.ints.Ref.ints.not(Ns.ints_).int.query.get.map(_ ==> List(a, c))
      } yield ()
    }


    "has - Sets that contain all values of other Set" - types { implicit conn =>
      for {
        _ <- Ns.int.ints.Ref.ints.int.insert(a, b, c).transact
        _ <- Ns.int.ints.has(Ref.ints_).Ref.ints.int.query.get.map(_ ==> List(b))
        _ <- Ns.int.ints.Ref.ints.has(Ns.ints_).int.query.get.map(_ ==> List(a, b))
      } yield ()
    }

    "has - Sets that contain value of other attribute" - types { implicit conn =>
      for {
        _ <- Ns.int.ints.Ref.ints.int.insert(a, b, c).transact
        _ <- Ns.int.ints.has(Ref.int_).Ref.ints.int.query.get.map(_ ==> List(a, b, c))
        _ <- Ns.int.ints.Ref.ints.has(Ns.int_).int.query.get.map(_ ==> List(a, b, c))
      } yield ()
    }


    "hasNo - Sets that don't contain any values of other Set" - types { implicit conn =>
      for {
        _ <- Ns.int.ints.Ref.ints.int.insert(a, b, c).transact
        _ <- Ns.int.ints.hasNo(Ref.ints_).Ref.ints.int.query.get.map(_ ==> List(c))
        _ <- Ns.int.ints.Ref.ints.hasNo(Ns.ints_).int.query.get.map(_ ==> List(c))
      } yield ()
    }

    "hasNo - Sets that don't contain value of other attribute" - types { implicit conn =>
      for {
        _ <- Ns.int.ints.Ref.ints.int.insert(a, b, c).transact
        _ <- Ns.int.ints.hasNo(Ref.int_).Ref.ints.int.query.get.map(_ ==> List())
        _ <- Ns.int.ints.Ref.ints.hasNo(Ns.int_).int.query.get.map(_ ==> List())
      } yield ()
    }
  }
}
