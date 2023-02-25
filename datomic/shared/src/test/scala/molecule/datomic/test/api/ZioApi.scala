package molecule.datomic.test.api

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.dataModels.core.dsl.Unique.Unique
import molecule.datomic.setup.DatomicZioSpec
import molecule.datomic.zio._
import zio._
import zio.test.TestAspect._
import zio.test._
import scala.annotation.nowarn

object ZioApi extends DatomicZioSpec {

  @nowarn override def spec: Spec[TestEnvironment with Scope, Any] =
    suite("Molecule ZIO api")(
      test("Crud actions") {
        for {
          eids <- Ns.int.insert(1, 2).transact.map(_.eids)
          _ <- Ns.int(3).save.transact
          a <- Ns.int.query.get
          _ <- Ns(eids(0)).int(10).update.transact
          _ <- Ns(eids(1)).delete.transact
          b <- Ns.int.query.get
        } yield {
          assertTrue(eids.size == 2) &&
            assertTrue(a == List(1, 2, 3)) &&
            assertTrue(b == List(3, 10))
        }
      }.provide(types.orDie),

      test("Offset query")(
        for {
          _ <- Ns.int.insert(1, 2, 3).transact
          a <- Ns.int.query.get
          b <- Ns.int.query.limit(2).get
          c <- Ns.int.query.offset(1).get
          d <- Ns.int.query.offset(1).limit(1).get
        } yield
          assertTrue(a == List(1, 2, 3)) &&
            assertTrue(b == List(1, 2)) &&
            assertTrue(c == (List(2, 3), 3, true)) &&
            assertTrue(d == (List(2), 3, true))
      ).provide(types.orDie),

      test("Cursor query") {
        val query = Unique.int.a1.query
        for {
          _ <- Unique.int.insert(1, 2, 3, 4, 5).transact
          c1 <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
          c2 <- query.from(c1).limit(2).get.map { case (List(3, 4), c, true) => c }
          c3 <- query.from(c2).limit(2).get.map { case (List(5), c, false) => c }
          c2 <- query.from(c3).limit(-2).get.map { case (List(3, 4), c, true) => c }
          res <- query.from(c2).limit(-2).get
        } yield {
          assertTrue(res._1 == List(1, 2) && !res._3)
        }
      }.provide(unique.orDie)

    ) @@ sequential
}
