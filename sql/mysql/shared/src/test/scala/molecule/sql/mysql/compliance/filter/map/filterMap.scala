package molecule.sql.mysql.compliance.filter.map

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filter.map.*
import molecule.coreTests.spi.filter.map.types.*
import molecule.sql.mysql.setup.Api_mysql_async

class MapSemanticsTest extends Test {
  MapSemantics(this, Api_mysql_async)
}

class FilterMap_String_Test extends Test {
  FilterMap_String_(this, Api_mysql_async)
}
class FilterMap_IntTest extends Test {
  FilterMap_Int(this, Api_mysql_async)
}
class FilterMap_Long_Test extends Test {
  FilterMap_Long_(this, Api_mysql_async)
}
class FilterMap_Float_Test extends Test {
  FilterMap_Float_(this, Api_mysql_async)
}
class FilterMap_Double_Test extends Test {
  FilterMap_Double_(this, Api_mysql_async)
}
class FilterMap_BooleanTest extends Test {
  FilterMap_Boolean(this, Api_mysql_async)
}
class FilterMap_BigInt_Test extends Test {
  FilterMap_BigInt_(this, Api_mysql_async)
}
class FilterMap_BigDecimal_Test extends Test {
  FilterMap_BigDecimal_(this, Api_mysql_async)
}
class FilterMap_Date_Test extends Test {
  FilterMap_Date_(this, Api_mysql_async)
}
class FilterMap_Duration_Test extends Test {
  FilterMap_Duration_(this, Api_mysql_async)
}
class FilterMap_Instant_Test extends Test {
  FilterMap_Instant_(this, Api_mysql_async)
}
class FilterMap_LocalDate_Test extends Test {
  FilterMap_LocalDate_(this, Api_mysql_async)
}
class FilterMap_LocalTime_Test extends Test {
  FilterMap_LocalTime_(this, Api_mysql_async)
}
class FilterMap_LocalDateTime_Test extends Test {
  FilterMap_LocalDateTime_(this, Api_mysql_async)
}
class FilterMap_OffsetTime_Test extends Test {
  FilterMap_OffsetTime_(this, Api_mysql_async)
}
class FilterMap_OffsetDateTime_Test extends Test {
  FilterMap_OffsetDateTime_(this, Api_mysql_async)
}
class FilterMap_ZonedDateTime_Test extends Test {
  FilterMap_ZonedDateTime_(this, Api_mysql_async)
}
class FilterMap_UUID_Test extends Test {
  FilterMap_UUID_(this, Api_mysql_async)
}
class FilterMap_URI_Test extends Test {
  FilterMap_URI_(this, Api_mysql_async)
}
class FilterMap_Byte_Test extends Test {
  FilterMap_Byte_(this, Api_mysql_async)
}
class FilterMap_Short_Test extends Test {
  FilterMap_Short_(this, Api_mysql_async)
}
class FilterMap_Char_Test extends Test {
  FilterMap_Char_(this, Api_mysql_async)
}
