package molecule.db.sql.postgres.compliance.filter.set

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filter.set.SetSemantics
import molecule.db.compliance.test.filter.set.ref.{FilterRefSet_Card1Ref, FilterRefSet_Card2Ref}
import molecule.db.compliance.test.filter.set.types.{FilterSet_BigDecimal_, FilterSet_BigInt_, FilterSet_Boolean, FilterSet_Byte_, FilterSet_Char_, FilterSet_Date_, FilterSet_Double_, FilterSet_Duration_, FilterSet_Float_, FilterSet_Instant_, FilterSet_Int, FilterSet_LocalDateTime_, FilterSet_LocalDate_, FilterSet_LocalTime_, FilterSet_Long_, FilterSet_OffsetDateTime_, FilterSet_OffsetTime_, FilterSet_Short_, FilterSet_String_, FilterSet_URI_, FilterSet_UUID_, FilterSet_ZonedDateTime_, FilterSet_ref}
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class FilterSet_String_Test extends Test {
  FilterSet_String_(this, Api_postgres_async)
}
class FilterSet_IntTest extends Test {
  FilterSet_Int(this, Api_postgres_async)
}
class FilterSet_Long_Test extends Test {
  FilterSet_Long_(this, Api_postgres_async)
}
class FilterSet_Float_Test extends Test {
  FilterSet_Float_(this, Api_postgres_async)
}
class FilterSet_Double_Test extends Test {
  FilterSet_Double_(this, Api_postgres_async)
}
class FilterSet_BooleanTest extends Test {
  FilterSet_Boolean(this, Api_postgres_async)
}
class FilterSet_BigInt_Test extends Test {
  FilterSet_BigInt_(this, Api_postgres_async)
}
class FilterSet_BigDecimal_Test extends Test {
  FilterSet_BigDecimal_(this, Api_postgres_async)
}
class FilterSet_Date_Test extends Test {
  FilterSet_Date_(this, Api_postgres_async)
}
class FilterSet_Duration_Test extends Test {
  FilterSet_Duration_(this, Api_postgres_async)
}
class FilterSet_Instant_Test extends Test {
  FilterSet_Instant_(this, Api_postgres_async)
}
class FilterSet_LocalDate_Test extends Test {
  FilterSet_LocalDate_(this, Api_postgres_async)
}
class FilterSet_LocalTime_Test extends Test {
  FilterSet_LocalTime_(this, Api_postgres_async)
}
class FilterSet_LocalDateTime_Test extends Test {
  FilterSet_LocalDateTime_(this, Api_postgres_async)
}
class FilterSet_OffsetTime_Test extends Test {
  FilterSet_OffsetTime_(this, Api_postgres_async)
}
class FilterSet_OffsetDateTime_Test extends Test {
  FilterSet_OffsetDateTime_(this, Api_postgres_async)
}
class FilterSet_ZonedDateTime_Test extends Test {
  FilterSet_ZonedDateTime_(this, Api_postgres_async)
}
class FilterSet_UUID_Test extends Test {
  FilterSet_UUID_(this, Api_postgres_async)
}
class FilterSet_URI_Test extends Test {
  FilterSet_URI_(this, Api_postgres_async)
}
class FilterSet_Byte_Test extends Test {
  FilterSet_Byte_(this, Api_postgres_async)
}
class FilterSet_Short_Test extends Test {
  FilterSet_Short_(this, Api_postgres_async)
}
class FilterSet_Char_Test extends Test {
  FilterSet_Char_(this, Api_postgres_async)
}

class FilterSet_refTest extends Test {
  FilterSet_ref(this, Api_postgres_async)
}

class SetSemanticsTest extends Test {
  SetSemantics(this, Api_postgres_async)
}

class FilterRefSet_Card1RefTest extends Test {
  FilterRefSet_Card1Ref(this, Api_postgres_async)
}
class FilterRefSet_Card2RefTest extends Test {
  FilterRefSet_Card2Ref(this, Api_postgres_async)
}
