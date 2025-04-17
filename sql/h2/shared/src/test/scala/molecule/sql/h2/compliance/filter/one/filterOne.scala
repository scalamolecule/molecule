package molecule.sql.h2.compliance.filter.one

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filter.one._
import molecule.coreTests.spi.filter.one.types._
import molecule.sql.h2.setup.Api_h2_async

class FilterOne_String_Test extends Test {
  FilterOne_String_(this, Api_h2_async)
}
class FilterOne_IntTest extends Test {
  FilterOne_Int(this, Api_h2_async)
}
class FilterOne_Long_Test extends Test {
  FilterOne_Long_(this, Api_h2_async)
}
class FilterOne_Float_Test extends Test {
  FilterOne_Float_(this, Api_h2_async)
}
class FilterOne_Double_Test extends Test {
  FilterOne_Double_(this, Api_h2_async)
}
class FilterOne_BooleanTest extends Test {
  FilterOne_Boolean(this, Api_h2_async)
}
class FilterOne_BigInt_Test extends Test {
  FilterOne_BigInt_(this, Api_h2_async)
}
class FilterOne_BigDecimal_Test extends Test {
  FilterOne_BigDecimal_(this, Api_h2_async)
}
class FilterOne_Date_Test extends Test {
  FilterOne_Date_(this, Api_h2_async)
}
class FilterOne_Duration_Test extends Test {
  FilterOne_Duration_(this, Api_h2_async)
}
class FilterOne_Instant_Test extends Test {
  FilterOne_Instant_(this, Api_h2_async)
}
class FilterOne_LocalDate_Test extends Test {
  FilterOne_LocalDate_(this, Api_h2_async)
}
class FilterOne_LocalTime_Test extends Test {
  FilterOne_LocalTime_(this, Api_h2_async)
}
class FilterOne_LocalDateTime_Test extends Test {
  FilterOne_LocalDateTime_(this, Api_h2_async)
}
class FilterOne_OffsetTime_Test extends Test {
  FilterOne_OffsetTime_(this, Api_h2_async)
}
class FilterOne_OffsetDateTime_Test extends Test {
  FilterOne_OffsetDateTime_(this, Api_h2_async)
}
class FilterOne_ZonedDateTime_Test extends Test {
  FilterOne_ZonedDateTime_(this, Api_h2_async)
}
class FilterOne_UUID_Test extends Test {
  FilterOne_UUID_(this, Api_h2_async)
}
class FilterOne_URI_Test extends Test {
  FilterOne_URI_(this, Api_h2_async)
}
class FilterOne_Byte_Test extends Test {
  FilterOne_Byte_(this, Api_h2_async)
}
class FilterOne_Short_Test extends Test {
  FilterOne_Short_(this, Api_h2_async)
}
class FilterOne_Char_Test extends Test {
  FilterOne_Char_(this, Api_h2_async)
}

class FilterOne_refTest extends Test {
  FilterOne_ref(this, Api_h2_async)
}
class FilterOne_idTest extends Test {
  FilterOne_id(this, Api_h2_async)
}

class FilterOneSpecial_StringTest extends Test {
  FilterOneSpecial_String(this, Api_h2_async)
}

class FilterRefOneTest extends Test {
  FilterRefOne(this, Api_h2_async)
}
