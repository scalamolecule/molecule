package molecule.datomic.test.api

import molecule.base.error._
import molecule.coreTests.dataModels.core.dsl.Types._
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

      test("Error handling") {
        import molecule.coreTests.dataModels.core.dsl.Validation.Type

        Type.string("a").save.transact.flip.map {
          case ValidationErrors(errorMap) => assertTrue(
            errorMap.head._2.head ==
              s"""Type.string with value `a` doesn't satisfy validation:
                 |  _ > "b"
                 |""".stripMargin
          )
        } && Type.string.insert("a").transact.flip.map {
          case InsertErrors(errors, _) => assertTrue(
            errors.head._2.head.errors.head ==
              s"""Type.string with value `a` doesn't satisfy validation:
                 |  _ > "b"
                 |""".stripMargin
          )
        } && Type.string("c").save.transact.flatMap { txReport =>
          val eid = txReport.eids.head
          Type(eid).string("a").update.transact.flip.map {
            case ValidationErrors(errorMap) => assertTrue(
              errorMap.head._2.head ==
                s"""Type.string with value `a` doesn't satisfy validation:
                   |  _ > "b"
                   |""".stripMargin
            )
          }
        }
      }.provide(validation.orDie),

      test("Inspection") {
        for {
          eids <- Ns.int.insert(1, 2).transact.map(_.eids) // Need data for update and delete
          _ <- Ns.int.insert(1, 2).inspect
          _ <- Ns.int(3).save.inspect
          _ <- Ns.int.query.inspect
          _ <- Ns.int.query.get
          _ <- Ns(eids(0)).int(10).update.inspect
          _ <- Ns(eids(1)).delete.inspect
        } yield assertTrue(true)
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
        import molecule.coreTests.dataModels.core.dsl.Unique.Unique
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
      }.provide(unique.orDie),

      test("Subscription") {
        var intermediaryResults = List.empty[List[Int]]
        for {
          // Initial data (not pushed)
          _ <- Ns.i(1).save.transact

          // Start subscription in separate thread
          _ <- Ns.i.query.subscribe { freshResult =>
            intermediaryResults = intermediaryResults :+ freshResult
          }
          // Wait for subscription thread to startup to propagate first result
          _ <- delay(500)(())
          //          _ <- TestClock.adjust(500.milliseconds) // doesn't work on first run on JS...

          // Make changes to generate new results to be pushed
          _ <- Ns.i(2).save.transact
          _ <- Ns.i(3).save.transact

          // Not affecting subscription since it doesn't mach subscription query (Ns.i)
          _ <- Ns.string("foo").save.transact

          // Wait for subscription thread to propagate last result to client
          _ <- delay(50)(())
          //          _ <- TestClock.adjust(50.milliseconds) // doesn't work on first run on JS...
        } yield {
          assertTrue(intermediaryResults == List(
            List(1, 2), // query result after 2 was added
            List(1, 2, 3) // query result after 3 was added
          ))
        }
      }.provide(types.orDie),

      // todo: Make more zio-idiomatic. There's some concurrency issue with setting the Ref...

      //      test("Subscription") {
      //        for {
      //          r <- zio.Ref.make(List.empty[List[Int]])
      //          // _ <- r.getAndUpdate(_ :+ List(42)) // this works fine
      //          _ <- Ns.i(1).save.transact
      //          // start subscription in separate thread
      //          _ <- Ns.i.query.subscribe { freshResult =>
      //            println("####### " + freshResult)
      //            // todo: why is the ref not updated?
      //            r.getAndUpdate(_ :+ freshResult) // in another thread/fiber?
      //          }
      //          _ <- Ns.i(2).save.transact
      //          _ <- Ns.i(3).save.transact
      //          // Allow subscription thread to catch up
      //          _ <- TestClock.adjust(200.milliseconds)
      //          intermediaryResults <- r.get
      //        } yield {
      //          assertTrue(intermediaryResults == List(
      //            List(1, 2), // query result after 2 was added
      //            List(1, 2, 3) // query result after 3 was added
      //          ))
      //        }
      //      }.provide(types.orDie),

    ) @@ sequential
}
