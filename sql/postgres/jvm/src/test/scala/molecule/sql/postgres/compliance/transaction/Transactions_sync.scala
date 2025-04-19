package molecule.sql.postgres.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.*
import molecule.sql.postgres.setup.Api_postgres_sync


class Transactions_syncTest extends Test {
  Transactions_sync(this, Api_postgres_sync)


  //  test("hej") {
  //
  //
  //    val schema = ValidationSchema_postgres.schemaData.head
  //
  //    val db = DbConnection_postgres
  //
  //    val sqlConn = db.reusedSqlConn
  //
  //    sqlConn.setAutoCommit(false)
  //
  //    {
  //      val stmt = sqlConn.createStatement
  //      stmt.execute(schema)
  //      val rs = stmt.executeQuery(
  //        """SELECT table_name
  //          |FROM information_schema.tables
  //          |WHERE table_name LIKE 't%';""".stripMargin
  //      )
  //      rs.next()
  //      val a = rs.getString(1)
  //      println(a)
  //
  //      rs.next()
  //      val b = rs.getString(1)
  //      println(b)
  //      stmt.close()
  //    }
  //
  //    {
  //      val stmt = sqlConn.createStatement
  //      stmt.execute(
  //        """INSERT INTO Type (
  //          |      int
  //          |    ) VALUES (1);""".stripMargin
  //      )
  //      val rs = stmt.executeQuery(
  //        """SELECT int FROM Type;""".stripMargin
  //      )
  //      rs.next()
  //      val a = rs.getInt(1)
  //      println(a)
  ////    sqlConn.commit()
  //
  //    }
  //
  //    sqlConn.rollback()
  //
  //    sqlConn.commit()
  //
  //    {
  //      val stmt = sqlConn.createStatement
  ////      stmt.execute(
  ////        """INSERT INTO Type (
  ////          |      int
  ////          |    ) VALUES (1);""".stripMargin
  ////      )
  //      val rs = stmt.executeQuery(
  //        """SELECT int FROM Type;""".stripMargin
  //      )
  //      rs.next()
  //      val a = rs.getInt(1)
  //      println(a)
  //
  //    }
  //
  //
  ////    {
  ////      val stmt = sqlConn.createStatement
  ////      stmt.execute(schema)
  ////      val rs = stmt.executeQuery(
  ////        """SELECT table_name
  ////          |FROM information_schema.tables
  ////          |WHERE table_name LIKE 't%';""".stripMargin
  ////      )
  ////      rs.next()
  ////      val a = rs.getString(1)
  ////      println(a)
  ////
  ////      rs.next()
  ////      val b = rs.getString(1)
  ////      println(b)
  ////      stmt.close()
  ////    }
  //
  //    assert(true)
  //  }
}
