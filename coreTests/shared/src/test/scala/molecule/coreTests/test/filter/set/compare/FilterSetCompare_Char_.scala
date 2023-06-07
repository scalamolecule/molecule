// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set.compare

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSetCompare_Char_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, Set(char1, char2))
      val b = (2, Set(char2, char3, char4))
      for {
        _ <- Ns.i.chars.insert(List(a, b)).transact

        // <
        _ <- Ns.i.a1.chars.hasLt(char0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.chars.hasLt(char1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.chars.hasLt(char2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.chars.hasLt(char3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.chars.hasLe(char0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.chars.hasLe(char1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.chars.hasLe(char2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars.hasLe(char3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.chars.hasGt(char0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars.hasGt(char1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars.hasGt(char2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.chars.hasGt(char3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.chars.hasGe(char0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars.hasGe(char1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars.hasGe(char2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars.hasGe(char3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b) = (1, 2)
      for {
        _ <- Ns.i.chars.insert(List(
          (a, Set(char1, char2)),
          (b, Set(char2, char3, char4))
        )).transact

        // <
        _ <- Ns.i.a1.chars_.hasLt(char0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.chars_.hasLt(char1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.chars_.hasLt(char2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.chars_.hasLt(char3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.chars_.hasLe(char0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.chars_.hasLe(char1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.chars_.hasLe(char2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_.hasLe(char3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.chars_.hasGt(char0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_.hasGt(char1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_.hasGt(char2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.chars_.hasGt(char3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.chars_.hasGe(char0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_.hasGe(char1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_.hasGe(char2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_.hasGe(char3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(Set(char1, char2)))
      val b = (2, Some(Set(char2, char3, char4)))
      val c = (3, None)
      for {
        _ <- Ns.i.chars_?.insert(a, b, c).transact

        // <
        _ <- Ns.i.a1.chars_?.hasLt(Some(char0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.chars_?.hasLt(Some(char1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.chars_?.hasLt(Some(char2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.chars_?.hasLt(Some(char3)).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.chars_?.hasLe(Some(char0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.chars_?.hasLe(Some(char1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.chars_?.hasLe(Some(char2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_?.hasLe(Some(char3)).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.chars_?.hasGt(Some(char0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_?.hasGt(Some(char1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_?.hasGt(Some(char2)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.chars_?.hasGt(Some(char3)).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.chars_?.hasGe(Some(char0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_?.hasGe(Some(char1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_?.hasGe(Some(char2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_?.hasGe(Some(char3)).query.get.map(_ ==> List(b))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.chars_?.hasLt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_?.hasGt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_?.hasLe(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.chars_?.hasGe(None).query.get.map(_ ==> List(a, b))
      } yield ()
    }
  }
}