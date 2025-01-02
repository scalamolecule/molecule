package molecule.sql.mariadb.compliance.filter.seq

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.filter.seq._
import molecule.coreTests.spi.filter.seq.ref._
import molecule.coreTests.spi.filter.seq.types._
import molecule.sql.mariadb.setup._

class FilterSeq_String_ extends Test {
  FilterSeq_String_(this, Api_mariadb_async)
}
class FilterSeq_Int extends Test {
  FilterSeq_Int(this, Api_mariadb_async)
}
class FilterSeq_Long_ extends Test {
  FilterSeq_Long_(this, Api_mariadb_async)
}
class FilterSeq_Float_ extends Test {
  FilterSeq_Float_(this, Api_mariadb_async)
}
class FilterSeq_Double_ extends Test {
  FilterSeq_Double_(this, Api_mariadb_async)
}
class FilterSeq_Boolean extends Test {
  FilterSeq_Boolean(this, Api_mariadb_async)
}
class FilterSeq_BigInt_ extends Test {
  FilterSeq_BigInt_(this, Api_mariadb_async)
}
class FilterSeq_BigDecimal_ extends Test {
  FilterSeq_BigDecimal_(this, Api_mariadb_async)
}
class FilterSeq_Date_ extends Test {
  FilterSeq_Date_(this, Api_mariadb_async)
}
class FilterSeq_Duration_ extends Test {
  FilterSeq_Duration_(this, Api_mariadb_async)
}
class FilterSeq_Instant_ extends Test {
  FilterSeq_Instant_(this, Api_mariadb_async)
}
class FilterSeq_LocalDate_ extends Test {
  FilterSeq_LocalDate_(this, Api_mariadb_async)
}
class FilterSeq_LocalTime_ extends Test {
  FilterSeq_LocalTime_(this, Api_mariadb_async)
}
class FilterSeq_LocalDateTime_ extends Test {
  FilterSeq_LocalDateTime_(this, Api_mariadb_async)
}
class FilterSeq_OffsetTime_ extends Test {
  FilterSeq_OffsetTime_(this, Api_mariadb_async)
}
class FilterSeq_OffsetDateTime_ extends Test {
  FilterSeq_OffsetDateTime_(this, Api_mariadb_async)
}
class FilterSeq_ZonedDateTime_ extends Test {
  FilterSeq_ZonedDateTime_(this, Api_mariadb_async)
}
class FilterSeq_UUID_ extends Test {
  FilterSeq_UUID_(this, Api_mariadb_async)
}
class FilterSeq_URI_ extends Test {
  FilterSeq_URI_(this, Api_mariadb_async)
}
class FilterSeq_Byte_ extends MUnitSuiteWithArrays {
  FilterSeq_ByteArray(this, Api_mariadb_async)
}
class FilterSeq_Short_ extends Test {
  FilterSeq_Short_(this, Api_mariadb_async)
}
class FilterSeq_Char_ extends Test {
  FilterSeq_Char_(this, Api_mariadb_async)
}

class SeqSemantics extends Test {
  SeqSemantics(this, Api_mariadb_async)
}


class FilterRefSeq_Card1Ref extends Test {
  FilterRefSeq_Card1Ref(this, Api_mariadb_async)
}
class FilterRefSeq_Card2Ref extends Test {
  FilterRefSeq_Card2Ref(this, Api_mariadb_async)
}
