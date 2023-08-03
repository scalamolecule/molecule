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

object AdhocJdbcJVMsync extends JdbcTestSuite with BaseHelpers {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._

      //        _ <- Ns.i(1).save.transact
      //        _ <- Ns.i.query.get.map(_ ==> List(1))
      //        _ <- Ns.i.query.inspect

      //      val x = Ns.ii(Set(1, 2)).save.transact
      //      val x = Ns.ii(1).save.transact

      def printQuery(q: String)(implicit conn: Conn): Unit = {
        val c             = conn.asInstanceOf[JdbcConn_jvm].sqlConn
        val statement     = c.createStatement()
        val resultSet     = statement.executeQuery(q)
        val rsmd          = resultSet.getMetaData
        val columnsNumber = rsmd.getColumnCount
        println("--------------")
        println(q)
        while (resultSet.next) {
          var i = 1
          while (i <= columnsNumber) {
            val col         = rsmd.getColumnName(i)
            val columnValue = resultSet.getString(i)
            if (columnValue != null)
              println(col + padS(55, col) + columnValue)
            i += 1
          }
          println("--------------")
        }
      }

      //      Ns.i.insert(4, 3, 2).transact

      Ns.i.int.insert(
        (1, 1),
        (1, 2),
        (1, 3),
        (2, 4),
        (2, 5),
        (2, 6),
      ).transact

      Ns.i.int(min(2)).query.get ==> List(
        (1, Set(1, 2)),
        (2, Set(4, 5))
      )

      Ns.i.int(max(2)).query.get ==> List(
        (1, Set(2, 3)),
        (2, Set(5, 6))
      )


      Ns.i.int(min(2)).int(max(2)).query.get ==> List(
        (1, Set(1, 2), Set(2, 3)),
        (2, Set(4, 5), Set(5, 6))
      )

      printQuery(
        """SELECT DISTINCT
          |  s, i,
          |  ARRAY_SLICE(
          |    ARRAY_AGG(Ns.int order by Ns.int)
          |      FILTER (WHERE Ns.int IS NOT NULL),
          |    1,
          |    LEAST(
          |      2,
          |      ARRAY_LENGTH(
          |        ARRAY_AGG(Ns.int)
          |          FILTER (WHERE Ns.int IS NOT NULL)
          |      )
          |    )
          |  )
          |FROM Ns
          |WHERE
          |  Ns.s   IS NOT NULL AND
          |  Ns.i   IS NOT NULL AND
          |  Ns.int IS NOT NULL
          |group by s, i
          |""".stripMargin
      )

      //      Ns.i(min(2)).query.get ==> List(Set(1, 2))



      //      Ns.int.insert(List(int1, int2, int3)).transact
      //        _ <- Ns.int(min).query.get.map(_ ==> List(int1))
      //        _ <- Ns.int(min(1)).query.get.map(_ ==> List(Set(int1)))
      //      Ns.int(min(2)).query.get.map(_ ==> List(Set(int1, int2)))
    }

  }
}
