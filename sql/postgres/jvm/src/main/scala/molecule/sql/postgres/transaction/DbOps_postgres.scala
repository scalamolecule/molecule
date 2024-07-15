package molecule.sql.postgres.transaction

import java.sql.Connection
import molecule.sql.core.transaction.op.DbOps
import scala.collection.mutable.ListBuffer

class DbOps_postgres extends DbOps {


  override def insertStmt(
    table: String, cols: Iterable[String], placeHolders: Iterable[String]
  ): String = {
    val columns           = cols.mkString(",\n  ")
    val inputPlaceholders = placeHolders.map(s => s"$s").mkString(", ")
    if (cols.nonEmpty) {
      s"""INSERT INTO $table (
         |  $columns
         |) VALUES ($inputPlaceholders)""".stripMargin
    } else {
      s"INSERT INTO $table (id) VALUES (DEFAULT)"
    }
  }

}
