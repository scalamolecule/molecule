package molecule.db.postgresql.compliance.filter.set

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.filter.set.ref.{FilterRefSet_Card1Ref, FilterRefSet_Card2Ref}
import molecule.db.compliance.test.filter.set.types.*
import molecule.db.compliance.test.filter.set.{FilterSet_Enum, SetSemantics}
import molecule.db.postgresql.setup.Api_postgresql_async

class FilterSet_String_Test extends MUnit {
  FilterSet_String_(this, Api_postgresql_async)
}
class FilterSet_IntTest extends MUnit {
  FilterSet_Int(this, Api_postgresql_async)
}
class FilterSet_Long_Test extends MUnit {
  FilterSet_Long_(this, Api_postgresql_async)
}
class FilterSet_Float_Test extends MUnit {
  FilterSet_Float_(this, Api_postgresql_async)
}
class FilterSet_Double_Test extends MUnit {
  FilterSet_Double_(this, Api_postgresql_async)
}
class FilterSet_BooleanTest extends MUnit {
  FilterSet_Boolean(this, Api_postgresql_async)
}
class FilterSet_BigInt_Test extends MUnit {
  FilterSet_BigInt_(this, Api_postgresql_async)
}
class FilterSet_BigDecimal_Test extends MUnit {
  FilterSet_BigDecimal_(this, Api_postgresql_async)
}
class FilterSet_Date_Test extends MUnit {
  FilterSet_Date_(this, Api_postgresql_async)
}
class FilterSet_Duration_Test extends MUnit {
  FilterSet_Duration_(this, Api_postgresql_async)
}
class FilterSet_Instant_Test extends MUnit {
  FilterSet_Instant_(this, Api_postgresql_async)
}
class FilterSet_LocalDate_Test extends MUnit {
  FilterSet_LocalDate_(this, Api_postgresql_async)
}
class FilterSet_LocalTime_Test extends MUnit {
  FilterSet_LocalTime_(this, Api_postgresql_async)
}
class FilterSet_LocalDateTime_Test extends MUnit {
  FilterSet_LocalDateTime_(this, Api_postgresql_async)
}
class FilterSet_OffsetTime_Test extends MUnit {
  FilterSet_OffsetTime_(this, Api_postgresql_async)
}
class FilterSet_OffsetDateTime_Test extends MUnit {
  FilterSet_OffsetDateTime_(this, Api_postgresql_async)
}
class FilterSet_ZonedDateTime_Test extends MUnit {
  FilterSet_ZonedDateTime_(this, Api_postgresql_async)
}
class FilterSet_UUID_Test extends MUnit {
  FilterSet_UUID_(this, Api_postgresql_async)
}
class FilterSet_URI_Test extends MUnit {
  FilterSet_URI_(this, Api_postgresql_async)
}
class FilterSet_Byte_Test extends MUnit {
  FilterSet_Byte_(this, Api_postgresql_async)
}
class FilterSet_Short_Test extends MUnit {
  FilterSet_Short_(this, Api_postgresql_async)
}
class FilterSet_Char_Test extends MUnit {
  FilterSet_Char_(this, Api_postgresql_async)
}

class FilterSet_refTest extends MUnit {
  FilterSet_ref(this, Api_postgresql_async)
}

class FilterSet_EnumTest extends MUnit {
  FilterSet_Enum(this, Api_postgresql_async)
}
class SetSemanticsTest extends MUnit {
  SetSemantics(this, Api_postgresql_async)
}

class FilterRefSet_Card1RefTest extends MUnit {
  FilterRefSet_Card1Ref(this, Api_postgresql_async)
}
class FilterRefSet_Card2RefTest extends MUnit {
  FilterRefSet_Card2Ref(this, Api_postgresql_async)
}
