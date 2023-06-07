// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set.compare

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSetCompare_String_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, Set(string1, string2))
      val b = (2, Set(string2, string3, string4))
      for {
        _ <- Ns.i.strings.insert(List(a, b)).transact

        // <
        _ <- Ns.i.a1.strings.hasLt(string0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.strings.hasLt(string1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.strings.hasLt(string2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.strings.hasLt(string3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.strings.hasLe(string0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.strings.hasLe(string1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.strings.hasLe(string2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings.hasLe(string3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.strings.hasGt(string0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings.hasGt(string1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings.hasGt(string2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.strings.hasGt(string3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.strings.hasGe(string0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings.hasGe(string1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings.hasGe(string2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings.hasGe(string3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b) = (1, 2)
      for {
        _ <- Ns.i.strings.insert(List(
          (a, Set(string1, string2)),
          (b, Set(string2, string3, string4))
        )).transact

        // <
        _ <- Ns.i.a1.strings_.hasLt(string0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.strings_.hasLt(string1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.strings_.hasLt(string2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.strings_.hasLt(string3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.strings_.hasLe(string0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.strings_.hasLe(string1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.strings_.hasLe(string2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_.hasLe(string3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.strings_.hasGt(string0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_.hasGt(string1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_.hasGt(string2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.strings_.hasGt(string3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.strings_.hasGe(string0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_.hasGe(string1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_.hasGe(string2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_.hasGe(string3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(Set(string1, string2)))
      val b = (2, Some(Set(string2, string3, string4)))
      val c = (3, None)
      for {
        _ <- Ns.i.strings_?.insert(a, b, c).transact

        // <
        _ <- Ns.i.a1.strings_?.hasLt(Some(string0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.strings_?.hasLt(Some(string1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.strings_?.hasLt(Some(string2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.strings_?.hasLt(Some(string3)).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.strings_?.hasLe(Some(string0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.strings_?.hasLe(Some(string1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.strings_?.hasLe(Some(string2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_?.hasLe(Some(string3)).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.strings_?.hasGt(Some(string0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_?.hasGt(Some(string1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_?.hasGt(Some(string2)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.strings_?.hasGt(Some(string3)).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.strings_?.hasGe(Some(string0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_?.hasGe(Some(string1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_?.hasGe(Some(string2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_?.hasGe(Some(string3)).query.get.map(_ ==> List(b))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.strings_?.hasLt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_?.hasGt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_?.hasLe(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.strings_?.hasGe(None).query.get.map(_ ==> List(a, b))
      } yield ()
    }
  }
}