package molecule.sql.sqlite.query

import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.sql.core.query.{LambdasMap, SqlQueryBase}

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


  override protected def sql2mapOpt[T](
    row: RS, paramIndex: Int, json2map: String => Map[String, T]
  ): Option[Map[String, T]] = {
    val json = row.getString(paramIndex)
    if (!row.wasNull() && json.nonEmpty)
      Some(json2map(json))
    else
      None
  }
}
