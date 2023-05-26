package molecule.sql.jdbc.test

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
import scala.language.implicitConversions
import molecule.core.util.Executor._


object AdhocJdbcAsync extends JdbcTestSuite {

  override val tests = Tests {
    "types" - types { implicit conn =>
      for {
        _ <- Ns
          .i(3)
          .string(string3)
          .int(int3)
          .long(long3)
          .float(float3)
          .save.transact
//        _ <- Ns.i(3).save.transact
//        _ <- Ns.i.insert(3).transact
        _ <- Ns.i.query.get.map(_ ==> List(3))
      } yield ()
    }
  }
}
