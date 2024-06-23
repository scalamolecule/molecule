package molecule.sql.h2.query

import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.sql.core.query.{ResolveExprMap, SqlQueryBase}

trait ResolveExprMap_h2
  extends ResolveExprMap
    with LambdasMap_h2 { self: SqlQueryBase =>

}