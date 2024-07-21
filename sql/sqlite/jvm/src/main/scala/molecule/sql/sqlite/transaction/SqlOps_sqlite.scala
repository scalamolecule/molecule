package molecule.sql.sqlite.transaction

import java.sql.Connection
import molecule.sql.core.transaction.strategy.SqlOps
import scala.collection.mutable.ListBuffer

class SqlOps_sqlite extends SqlOps {

//  lazy override val defaultValues = "DEFAULT VALUES"

  // Since SQlite doesn't allow us to get ps.getGeneratedKeys after an
  // executeBatch(), we get the affected ids by brute force with a query instead.
  override def getIds(
    sqlConn: Connection, table: String, ps: PS
  ): List[Long] = {
    val getPrevId = sqlConn.prepareStatement(
      s"select max(id) from $table"
    ).executeQuery()
    getPrevId.next()
    val prevId = getPrevId.getLong(1)
    getPrevId.close()

    // Execute incoming batch of prepared statements
    ps.executeBatch()
    ps.close()

    val getNewIds = sqlConn.prepareStatement(
      s"select id from $table where id > $prevId order by id asc"
    ).executeQuery()

    val ids = ListBuffer.empty[Long]
    while (getNewIds.next()) {
      ids += getNewIds.getLong(1)
    }
    getNewIds.close()

    ids.toList
  }


//  override def insertStmt(
//    table: String, cols: Iterable[String], placeHolders: Iterable[String]
//  ): String = {
//    val columns           = cols.mkString(",\n  ")
//    val inputPlaceholders = placeHolders.mkString(", ")
//    if (cols.nonEmpty) {
//      s"""INSERT INTO $table (
//         |  $columns
//         |) VALUES ($inputPlaceholders)""".stripMargin
//    } else {
//      s"INSERT INTO $table DEFAULT VALUES"
//    }
//  }

}
