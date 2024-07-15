package molecule.sql.postgres.transaction

import java.sql.Connection
import molecule.sql.core.transaction.op.DbOps
import scala.collection.mutable.ListBuffer

class DbOps_postgres extends DbOps {

//  override def getIds(sqlConn: Connection, table: String, ps: PS): List[Long] = {
//    val getPrevId = sqlConn.prepareStatement(
//      s"select max(id) from $table"
//    ).executeQuery()
//    getPrevId.next()
//    val prevId = getPrevId.getLong(1)
//    getPrevId.close()
//
//    // Execute incoming batch of prepared statements
//    ps.executeBatch()
//    ps.close()
//
//    // Since SQlite doesn't allow us to get ps.getGeneratedKeys after an
//    // executeBatch(), we get the affected ids by brute force with a query instead.
//    val getNewIds = sqlConn.prepareStatement(
//      s"select id from $table where id > $prevId order by id asc"
//    ).executeQuery()
//
//    val ids       = ListBuffer.empty[Long]
//    while (getNewIds.next()) {
//      ids += getNewIds.getLong(1)
//    }
//    getNewIds.close()
//
//    ids.toList
//  }


  override def insertStmt(
    table: String, cols: Iterable[String], placeHolders: Iterable[String]
  ): String = {
    val columns           = cols.mkString(",\n  ")
//    val inputPlaceholders = placeHolders.map(s => s"$s::jsonb").mkString(", ")
    val inputPlaceholders = placeHolders.map(s => s"$s").mkString(", ")
//    val inputPlaceholders = cols.map {
//      case (_, castExt) => s"?$castExt"
//    }.mkString(", ")


    if (cols.nonEmpty) {
      s"""INSERT INTO $table (
         |  $columns
         |) VALUES ($inputPlaceholders)""".stripMargin
    } else {
      s"INSERT INTO $table (id) VALUES (DEFAULT)"
    }
  }

}
