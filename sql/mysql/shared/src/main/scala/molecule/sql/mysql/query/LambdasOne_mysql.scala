package molecule.sql.mysql.query

import java.util.Date
import molecule.sql.core.query.{LambdasOne, SqlQueryBase}

trait LambdasOne_mysql extends LambdasOne { self: SqlQueryBase =>


  override protected lazy val sql2oneDate: (Row, Int) => Date =
    (row: Row, paramIndex: Int) => new Date(row.getLong(paramIndex))

  override protected lazy val sql2oneOptDate: (Row, Int) => Option[Date] =
    (row: Row, paramIndex: Int) => {
      val v = row.getLong(paramIndex)
      if (row.wasNull()) Option.empty[Date] else Some(new Date(v))
    }

  override protected lazy val sql2oneDateOrNull: (Row, Int) => Any = {
    (row: Row, paramIndex: Int) => {
      val v = row.getLong(paramIndex);
      if (row.wasNull()) null else new Date(v)
    }
  }
}