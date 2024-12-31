package molecule.sql.mysql.compliance.filter.map

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filter.map._
import molecule.coreTests.spi.filter.map.types._
import molecule.sql.mysql.setup.Api_mysql_async

class MapSemantics extends Test {
  MapSemantics(this, Api_mysql_async)
}

class FilterMap_String_ extends Test {
  FilterMap_String_(this, Api_mysql_async)
}
class FilterMap_Int extends Test {
  FilterMap_Int(this, Api_mysql_async)
}
class FilterMap_Long_ extends Test {
  FilterMap_Long_(this, Api_mysql_async)
}
class FilterMap_Float_ extends Test {
  FilterMap_Float_(this, Api_mysql_async)
}
class FilterMap_Double_ extends Test {
  FilterMap_Double_(this, Api_mysql_async)
}
class FilterMap_Boolean extends Test {
  FilterMap_Boolean(this, Api_mysql_async)
}
class FilterMap_BigInt_ extends Test {
  FilterMap_BigInt_(this, Api_mysql_async)
}
class FilterMap_BigDecimal_ extends Test {
  FilterMap_BigDecimal_(this, Api_mysql_async)
}
class FilterMap_Date_ extends Test {
  FilterMap_Date_(this, Api_mysql_async)
}
class FilterMap_Duration_ extends Test {
  FilterMap_Duration_(this, Api_mysql_async)
}
class FilterMap_Instant_ extends Test {
  FilterMap_Instant_(this, Api_mysql_async)
}
class FilterMap_LocalDate_ extends Test {
  FilterMap_LocalDate_(this, Api_mysql_async)
}
class FilterMap_LocalTime_ extends Test {
  FilterMap_LocalTime_(this, Api_mysql_async)
}
class FilterMap_LocalDateTime_ extends Test {
  FilterMap_LocalDateTime_(this, Api_mysql_async)
}
class FilterMap_OffsetTime_ extends Test {
  FilterMap_OffsetTime_(this, Api_mysql_async)
}
class FilterMap_OffsetDateTime_ extends Test {
  FilterMap_OffsetDateTime_(this, Api_mysql_async)
}
class FilterMap_ZonedDateTime_ extends Test {
  FilterMap_ZonedDateTime_(this, Api_mysql_async)
}
class FilterMap_UUID_ extends Test {
  FilterMap_UUID_(this, Api_mysql_async)
}
class FilterMap_URI_ extends Test {
  FilterMap_URI_(this, Api_mysql_async)
}
class FilterMap_Byte_ extends Test {
  FilterMap_Byte_(this, Api_mysql_async)
}
class FilterMap_Short_ extends Test {
  FilterMap_Short_(this, Api_mysql_async)
}
class FilterMap_Char_ extends Test {
  FilterMap_Char_(this, Api_mysql_async)
}
