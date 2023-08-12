package molecule.sql.jdbc.test

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.sql.jdbc.setup.JdbcZioSpec
import molecule.sql.jdbc.zio._
import zio.Scope
import zio.test.TestAspect._
import zio.test.{Spec, TestEnvironment, assertTrue}
import scala.language.implicitConversions

object AdhocJdbcZio extends JdbcZioSpec {

  override def spec: Spec[TestEnvironment with Scope, Any] =
    suite("Datomic")(
      test("zio") {
        for {
          _ <- Ns.i(1).save.transact
          result <- Ns.i.query.get

        } yield {
          assertTrue(result == List(1))
        }
      }.provide(types.orDie),
    ) @@ sequential
}