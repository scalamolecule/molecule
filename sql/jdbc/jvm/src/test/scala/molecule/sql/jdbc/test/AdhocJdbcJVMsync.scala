package molecule.sql.jdbc.test

import java.sql._
import molecule.base.util.BaseHelpers
import molecule.core.spi.Conn
import molecule.core.util.Executor._
import molecule.sql.jdbc.facade.JdbcConn_jvm
import molecule.sql.jdbc.sync._
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
import scala.collection.immutable.Set
import scala.language.implicitConversions

object AdhocJdbcJVMsync extends JdbcTestSuite {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._

      //        _ <- Ns.i(1).save.transact
      //        _ <- Ns.i.query.get.map(_ ==> List(1))
      //        _ <- Ns.i.query.inspect



      val id = Ns.int(1).save.transact.id
      Ns.int.query.get ==> List(1)

      Ns(id).int(2).update.transact
      Ns.int.query.get ==> List(2)


      //      val List(a, b) = Ns.int.insert(1, 2).transact.ids
      //      Ns.int(3).save.transact
      //      Ns.int.query.get ==> List(1, 2, 3)
      //      Ns(a).int(10).update.transact
      //      Ns(b).delete.transact
      //      Ns.int.query.get ==> List(3, 10)


      //      rawSelect(
      //        """SELECT DISTINCT
      //          |  s, i,
      //          |  ARRAY_SLICE(
      //          |    ARRAY_AGG(Ns.int order by Ns.int)
      //          |      FILTER (WHERE Ns.int IS NOT NULL),
      //          |    1,
      //          |    LEAST(
      //          |      2,
      //          |      ARRAY_LENGTH(
      //          |        ARRAY_AGG(Ns.int)
      //          |          FILTER (WHERE Ns.int IS NOT NULL)
      //          |      )
      //          |    )
      //          |  )
      //          |FROM Ns
      //          |WHERE
      //          |  Ns.s   IS NOT NULL AND
      //          |  Ns.i   IS NOT NULL AND
      //          |  Ns.int IS NOT NULL
      //          |group by s, i
      //          |""".stripMargin
      //      )

      //      Ns.i(min(2)).query.get ==> List(Set(1, 2))


      //      Ns.int.insert(List(int1, int2, int3)).transact
      //        _ <- Ns.int(min).query.get.map(_ ==> List(int1))
      //        _ <- Ns.int(min(1)).query.get.map(_ ==> List(Set(int1)))
      //      Ns.int(min(2)).query.get.map(_ ==> List(Set(int1, int2)))
    }
  }
}
