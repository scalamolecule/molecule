package molecule.datalog.datomic.compliance.filter.set

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filter.set._
import molecule.coreTests.spi.filter.set.ref._
import molecule.coreTests.spi.filter.set.types._
import molecule.datalog.datomic.setup.Api_datomic_async

class FilterSet_String_ extends Test {
  FilterSet_String_(this, Api_datomic_async)
}
class FilterSet_Int extends Test {
  FilterSet_Int(this, Api_datomic_async)
}
class FilterSet_Long_ extends Test {
  FilterSet_Long_(this, Api_datomic_async)
}
class FilterSet_Float_ extends Test {
  FilterSet_Float_(this, Api_datomic_async)
}
class FilterSet_Double_ extends Test {
  FilterSet_Double_(this, Api_datomic_async)
}
class FilterSet_Boolean extends Test {
  FilterSet_Boolean(this, Api_datomic_async)
}
class FilterSet_BigInt_ extends Test {
  FilterSet_BigInt_(this, Api_datomic_async)
}
class FilterSet_BigDecimal_ extends Test {
  FilterSet_BigDecimal_(this, Api_datomic_async)
}
class FilterSet_Date_ extends Test {
  FilterSet_Date_(this, Api_datomic_async)
}
class FilterSet_Duration_ extends Test {
  FilterSet_Duration_(this, Api_datomic_async)
}
class FilterSet_Instant_ extends Test {
  FilterSet_Instant_(this, Api_datomic_async)
}
class FilterSet_LocalDate_ extends Test {
  FilterSet_LocalDate_(this, Api_datomic_async)
}
class FilterSet_LocalTime_ extends Test {
  FilterSet_LocalTime_(this, Api_datomic_async)
}
class FilterSet_LocalDateTime_ extends Test {
  FilterSet_LocalDateTime_(this, Api_datomic_async)
}
class FilterSet_OffsetTime_ extends Test {
  FilterSet_OffsetTime_(this, Api_datomic_async)
}
class FilterSet_OffsetDateTime_ extends Test {
  FilterSet_OffsetDateTime_(this, Api_datomic_async)
}
class FilterSet_ZonedDateTime_ extends Test {
  FilterSet_ZonedDateTime_(this, Api_datomic_async)
}
class FilterSet_UUID_ extends Test {
  FilterSet_UUID_(this, Api_datomic_async)
}
class FilterSet_URI_ extends Test {
  FilterSet_URI_(this, Api_datomic_async)
}
class FilterSet_Byte_ extends Test {
  FilterSet_Byte_(this, Api_datomic_async)
}
class FilterSet_Short_ extends Test {
  FilterSet_Short_(this, Api_datomic_async)
}
class FilterSet_Char_ extends Test {
  FilterSet_Char_(this, Api_datomic_async)
}

class FilterSet_ref extends Test {
  FilterSet_ref(this, Api_datomic_async)
}

class SetSemantics extends Test {
  SetSemantics(this, Api_datomic_async)
}

class FilterRefSet_Card1Ref extends Test {
  FilterRefSet_Card1Ref(this, Api_datomic_async)
}
class FilterRefSet_Card2Ref extends Test {
  FilterRefSet_Card2Ref(this, Api_datomic_async)
}
