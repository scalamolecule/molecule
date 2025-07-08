package molecule.db.sqlite.query

import molecule.db.common.query.{LambdasMap, SqlQueryBase}

trait LambdasMap_sqlite extends LambdasMap { self: SqlQueryBase =>


  override protected def sqlJson2map[T](
    row: RS, paramIndex: Int, json2map: String => Map[String, T]
  ): Map[String, T] = {
    val json = row.getString(paramIndex)
    if (row.wasNull()) {
      Map.empty[String, T]
    } else {
      json2map(json)
    }
  }
}
