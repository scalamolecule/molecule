package molecule.sql.h2.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation.any._
import molecule.sql.h2.setup.Api_h2_async

class Aggr_String_Test extends Test {
  Aggr_String_(this, Api_h2_async)
}
class Aggr_IntTest extends Test {
  Aggr_Int(this, Api_h2_async)
}
class Aggr_Long_Test extends Test {
  Aggr_Long_(this, Api_h2_async)
}
class Aggr_Float_Test extends Test {
  Aggr_Float_(this, Api_h2_async)
}
class Aggr_Double_Test extends Test {
  Aggr_Double_(this, Api_h2_async)
}
class Aggr_BooleanTest extends Test {
  Aggr_Boolean(this, Api_h2_async)
}
class Aggr_BigInt_Test extends Test {
  Aggr_BigInt_(this, Api_h2_async)
}
class Aggr_BigDecimal_Test extends Test {
  Aggr_BigDecimal_(this, Api_h2_async)
}
class Aggr_Date_Test extends Test {
  Aggr_Date_(this, Api_h2_async)
}
class Aggr_Duration_Test extends Test {
  Aggr_Duration_(this, Api_h2_async)
}
class Aggr_Instant_Test extends Test {
  Aggr_Instant_(this, Api_h2_async)
}
class Aggr_LocalDate_Test extends Test {
  Aggr_LocalDate_(this, Api_h2_async)
}
class Aggr_LocalTime_Test extends Test {
  Aggr_LocalTime_(this, Api_h2_async)
}
class Aggr_LocalDateTime_Test extends Test {
  Aggr_LocalDateTime_(this, Api_h2_async)
}
class Aggr_OffsetTime_Test extends Test {
  Aggr_OffsetTime_(this, Api_h2_async)
}
class Aggr_OffsetDateTime_Test extends Test {
  Aggr_OffsetDateTime_(this, Api_h2_async)
}
class Aggr_ZonedDateTime_Test extends Test {
  Aggr_ZonedDateTime_(this, Api_h2_async)
}
class Aggr_UUID_Test extends Test {
  Aggr_UUID_(this, Api_h2_async)
}
class Aggr_URI_Test extends Test {
  Aggr_URI_(this, Api_h2_async)
}
class Aggr_Byte_Test extends Test {
  Aggr_Byte_(this, Api_h2_async)
}
class Aggr_Short_Test extends Test {
  Aggr_Short_(this, Api_h2_async)
}
class Aggr_Char_Test extends Test {
  Aggr_Char_(this, Api_h2_async)
}

class Aggr_ref_Test extends Test {
  Aggr_ref_(this, Api_h2_async)
}
