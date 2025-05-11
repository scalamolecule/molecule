package molecule.db.datalog.datomic.compliance.filter.one

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filter.one.{FilterOneSpecial_String, FilterOne_id, FilterRefOne}
import molecule.db.compliance.test.filter.one.types.{FilterOne_BigDecimal_, FilterOne_BigInt_, FilterOne_Boolean, FilterOne_Byte_, FilterOne_Char_, FilterOne_Date_, FilterOne_Double_, FilterOne_Duration_, FilterOne_Float_, FilterOne_Instant_, FilterOne_Int, FilterOne_LocalDateTime_, FilterOne_LocalDate_, FilterOne_LocalTime_, FilterOne_Long_, FilterOne_OffsetDateTime_, FilterOne_OffsetTime_, FilterOne_Short_, FilterOne_String_, FilterOne_URI_, FilterOne_UUID_, FilterOne_ZonedDateTime_, FilterOne_ref}
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class FilterOne_String_Test extends Test {
  FilterOne_String_(this, Api_datomic_async)
}
class FilterOne_IntTest extends Test {
  FilterOne_Int(this, Api_datomic_async)
}
class FilterOne_Long_Test extends Test {
  FilterOne_Long_(this, Api_datomic_async)
}
class FilterOne_Float_Test extends Test {
  FilterOne_Float_(this, Api_datomic_async)
}
class FilterOne_Double_Test extends Test {
  FilterOne_Double_(this, Api_datomic_async)
}
class FilterOne_BooleanTest extends Test {
  FilterOne_Boolean(this, Api_datomic_async)
}
class FilterOne_BigInt_Test extends Test {
  FilterOne_BigInt_(this, Api_datomic_async)
}
class FilterOne_BigDecimal_Test extends Test {
  FilterOne_BigDecimal_(this, Api_datomic_async)
}
class FilterOne_Date_Test extends Test {
  FilterOne_Date_(this, Api_datomic_async)
}
class FilterOne_Duration_Test extends Test {
  FilterOne_Duration_(this, Api_datomic_async)
}
class FilterOne_Instant_Test extends Test {
  FilterOne_Instant_(this, Api_datomic_async)
}
class FilterOne_LocalDate_Test extends Test {
  FilterOne_LocalDate_(this, Api_datomic_async)
}
class FilterOne_LocalTime_Test extends Test {
  FilterOne_LocalTime_(this, Api_datomic_async)
}
class FilterOne_LocalDateTime_Test extends Test {
  FilterOne_LocalDateTime_(this, Api_datomic_async)
}
class FilterOne_OffsetTime_Test extends Test {
  FilterOne_OffsetTime_(this, Api_datomic_async)
}
class FilterOne_OffsetDateTime_Test extends Test {
  FilterOne_OffsetDateTime_(this, Api_datomic_async)
}
class FilterOne_ZonedDateTime_Test extends Test {
  FilterOne_ZonedDateTime_(this, Api_datomic_async)
}
class FilterOne_UUID_Test extends Test {
  FilterOne_UUID_(this, Api_datomic_async)
}
class FilterOne_URI_Test extends Test {
  FilterOne_URI_(this, Api_datomic_async)
}
class FilterOne_Byte_Test extends Test {
  FilterOne_Byte_(this, Api_datomic_async)
}
class FilterOne_Short_Test extends Test {
  FilterOne_Short_(this, Api_datomic_async)
}
class FilterOne_Char_Test extends Test {
  FilterOne_Char_(this, Api_datomic_async)
}

class FilterOne_refTest extends Test {
  FilterOne_ref(this, Api_datomic_async)
}
class FilterOne_idTest extends Test {
  FilterOne_id(this, Api_datomic_async)
}

class FilterOneSpecial_StringTest extends Test {
  FilterOneSpecial_String(this, Api_datomic_async)
}

class FilterRefOneTest extends Test {
  FilterRefOne(this, Api_datomic_async)
}

