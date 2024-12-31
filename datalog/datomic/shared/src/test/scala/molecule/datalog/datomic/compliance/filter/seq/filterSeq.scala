package molecule.datalog.datomic.compliance.filter.seq

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.filter.seq.SeqSemantics
import molecule.coreTests.spi.filter.seq.ref._
import molecule.coreTests.spi.filter.seq.types._
import molecule.datalog.datomic.setup._

class FilterSeq_String_ extends MUnitSuite {
  FilterSeq_String_(this, Api_datomic_async)
}
class FilterSeq_Int extends MUnitSuite {
  FilterSeq_Int(this, Api_datomic_async)
}
class FilterSeq_Long_ extends MUnitSuite {
  FilterSeq_Long_(this, Api_datomic_async)
}
class FilterSeq_Float_ extends MUnitSuite {
  FilterSeq_Float_(this, Api_datomic_async)
}
class FilterSeq_Double_ extends MUnitSuite {
  FilterSeq_Double_(this, Api_datomic_async)
}
class FilterSeq_Boolean extends MUnitSuite {
  FilterSeq_Boolean(this, Api_datomic_async)
}
class FilterSeq_BigInt_ extends MUnitSuite {
  FilterSeq_BigInt_(this, Api_datomic_async)
}
class FilterSeq_BigDecimal_ extends MUnitSuite {
  FilterSeq_BigDecimal_(this, Api_datomic_async)
}
class FilterSeq_Date_ extends MUnitSuite {
  FilterSeq_Date_(this, Api_datomic_async)
}
class FilterSeq_Duration_ extends MUnitSuite {
  FilterSeq_Duration_(this, Api_datomic_async)
}
class FilterSeq_Instant_ extends MUnitSuite {
  FilterSeq_Instant_(this, Api_datomic_async)
}
class FilterSeq_LocalDate_ extends MUnitSuite {
  FilterSeq_LocalDate_(this, Api_datomic_async)
}
class FilterSeq_LocalTime_ extends MUnitSuite {
  FilterSeq_LocalTime_(this, Api_datomic_async)
}
class FilterSeq_LocalDateTime_ extends MUnitSuite {
  FilterSeq_LocalDateTime_(this, Api_datomic_async)
}
class FilterSeq_OffsetTime_ extends MUnitSuite {
  FilterSeq_OffsetTime_(this, Api_datomic_async)
}
class FilterSeq_OffsetDateTime_ extends MUnitSuite {
  FilterSeq_OffsetDateTime_(this, Api_datomic_async)
}
class FilterSeq_ZonedDateTime_ extends MUnitSuite {
  FilterSeq_ZonedDateTime_(this, Api_datomic_async)
}
class FilterSeq_UUID_ extends MUnitSuite {
  FilterSeq_UUID_(this, Api_datomic_async)
}
class FilterSeq_URI_ extends MUnitSuite {
  FilterSeq_URI_(this, Api_datomic_async)
}
class FilterSeq_Byte_ extends MUnitSuiteWithArrays {
  FilterSeq_ByteArray(this, Api_datomic_async)
}
class FilterSeq_Short_ extends MUnitSuite {
  FilterSeq_Short_(this, Api_datomic_async)
}
class FilterSeq_Char_ extends MUnitSuite {
  FilterSeq_Char_(this, Api_datomic_async)
}

class SeqSemantics extends MUnitSuite {
  SeqSemantics(this, Api_datomic_async)
}


class FilterRefSeq_Card1Ref extends MUnitSuite {
  FilterRefSeq_Card1Ref(this, Api_datomic_async)
}
class FilterRefSeq_Card2Ref extends MUnitSuite {
  FilterRefSeq_Card2Ref(this, Api_datomic_async)
}
