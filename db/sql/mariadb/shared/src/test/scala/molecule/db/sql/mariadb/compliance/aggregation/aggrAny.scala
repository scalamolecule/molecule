package molecule.db.sql.mariadb.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.any.{Aggr_BigDecimal_, Aggr_BigInt_, Aggr_Boolean, Aggr_Byte_, Aggr_Char_, Aggr_Date_, Aggr_Double_, Aggr_Duration_, Aggr_Float_, Aggr_Instant_, Aggr_Int, Aggr_LocalDateTime_, Aggr_LocalDate_, Aggr_LocalTime_, Aggr_Long_, Aggr_OffsetDateTime_, Aggr_OffsetTime_, Aggr_Short_, Aggr_String_, Aggr_URI_, Aggr_UUID_, Aggr_ZonedDateTime_, Aggr_ref_}
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class Aggr_String_Test extends Test {
  Aggr_String_(this, Api_mariadb_async)
}
class Aggr_IntTest extends Test {
  Aggr_Int(this, Api_mariadb_async)
}
class Aggr_Long_Test extends Test {
  Aggr_Long_(this, Api_mariadb_async)
}
class Aggr_Float_Test extends Test {
  Aggr_Float_(this, Api_mariadb_async)
}
class Aggr_Double_Test extends Test {
  Aggr_Double_(this, Api_mariadb_async)
}
class Aggr_BooleanTest extends Test {
  Aggr_Boolean(this, Api_mariadb_async)
}
class Aggr_BigInt_Test extends Test {
  Aggr_BigInt_(this, Api_mariadb_async)
}
class Aggr_BigDecimal_Test extends Test {
  Aggr_BigDecimal_(this, Api_mariadb_async)
}
class Aggr_Date_Test extends Test {
  Aggr_Date_(this, Api_mariadb_async)
}
class Aggr_Duration_Test extends Test {
  Aggr_Duration_(this, Api_mariadb_async)
}
class Aggr_Instant_Test extends Test {
  Aggr_Instant_(this, Api_mariadb_async)
}
class Aggr_LocalDate_Test extends Test {
  Aggr_LocalDate_(this, Api_mariadb_async)
}
class Aggr_LocalTime_Test extends Test {
  Aggr_LocalTime_(this, Api_mariadb_async)
}
class Aggr_LocalDateTime_Test extends Test {
  Aggr_LocalDateTime_(this, Api_mariadb_async)
}
class Aggr_OffsetTime_Test extends Test {
  Aggr_OffsetTime_(this, Api_mariadb_async)
}
class Aggr_OffsetDateTime_Test extends Test {
  Aggr_OffsetDateTime_(this, Api_mariadb_async)
}
class Aggr_ZonedDateTime_Test extends Test {
  Aggr_ZonedDateTime_(this, Api_mariadb_async)
}
class Aggr_UUID_Test extends Test {
  Aggr_UUID_(this, Api_mariadb_async)
}
class Aggr_URI_Test extends Test {
  Aggr_URI_(this, Api_mariadb_async)
}
class Aggr_Byte_Test extends Test {
  Aggr_Byte_(this, Api_mariadb_async)
}
class Aggr_Short_Test extends Test {
  Aggr_Short_(this, Api_mariadb_async)
}
class Aggr_Char_Test extends Test {
  Aggr_Char_(this, Api_mariadb_async)
}

class Aggr_ref_Test extends Test {
  Aggr_ref_(this, Api_mariadb_async)
}
