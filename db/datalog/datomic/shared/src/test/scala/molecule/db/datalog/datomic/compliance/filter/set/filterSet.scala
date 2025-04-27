package molecule.db.datalog.datomic.compliance.filter.set

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filter.set.*
import molecule.coreTests.spi.filter.set.ref.*
import molecule.coreTests.spi.filter.set.types.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class FilterSet_String_Test extends Test {
  FilterSet_String_(this, Api_datomic_async)
}
class FilterSet_IntTest extends Test {
  FilterSet_Int(this, Api_datomic_async)
}
class FilterSet_Long_Test extends Test {
  FilterSet_Long_(this, Api_datomic_async)
}
class FilterSet_Float_Test extends Test {
  FilterSet_Float_(this, Api_datomic_async)
}
class FilterSet_Double_Test extends Test {
  FilterSet_Double_(this, Api_datomic_async)
}
class FilterSet_BooleanTest extends Test {
  FilterSet_Boolean(this, Api_datomic_async)
}
class FilterSet_BigInt_Test extends Test {
  FilterSet_BigInt_(this, Api_datomic_async)
}
class FilterSet_BigDecimal_Test extends Test {
  FilterSet_BigDecimal_(this, Api_datomic_async)
}
class FilterSet_Date_Test extends Test {
  FilterSet_Date_(this, Api_datomic_async)
}
class FilterSet_Duration_Test extends Test {
  FilterSet_Duration_(this, Api_datomic_async)
}
class FilterSet_Instant_Test extends Test {
  FilterSet_Instant_(this, Api_datomic_async)
}
class FilterSet_LocalDate_Test extends Test {
  FilterSet_LocalDate_(this, Api_datomic_async)
}
class FilterSet_LocalTime_Test extends Test {
  FilterSet_LocalTime_(this, Api_datomic_async)
}
class FilterSet_LocalDateTime_Test extends Test {
  FilterSet_LocalDateTime_(this, Api_datomic_async)
}
class FilterSet_OffsetTime_Test extends Test {
  FilterSet_OffsetTime_(this, Api_datomic_async)
}
class FilterSet_OffsetDateTime_Test extends Test {
  FilterSet_OffsetDateTime_(this, Api_datomic_async)
}
class FilterSet_ZonedDateTime_Test extends Test {
  FilterSet_ZonedDateTime_(this, Api_datomic_async)
}
class FilterSet_UUID_Test extends Test {
  FilterSet_UUID_(this, Api_datomic_async)
}
class FilterSet_URI_Test extends Test {
  FilterSet_URI_(this, Api_datomic_async)
}
class FilterSet_Byte_Test extends Test {
  FilterSet_Byte_(this, Api_datomic_async)
}
class FilterSet_Short_Test extends Test {
  FilterSet_Short_(this, Api_datomic_async)
}
class FilterSet_Char_Test extends Test {
  FilterSet_Char_(this, Api_datomic_async)
}

class FilterSet_refTest extends Test {
  FilterSet_ref(this, Api_datomic_async)
}

class SetSemanticsTest extends Test {
  SetSemantics(this, Api_datomic_async)
}

class FilterRefSet_Card1RefTest extends Test {
  FilterRefSet_Card1Ref(this, Api_datomic_async)
}
class FilterRefSet_Card2RefTest extends Test {
  FilterRefSet_Card2Ref(this, Api_datomic_async)
}
