package molecule.db.sql.mysql.compliance.filter.one

import molecule.core.setup.MUnit
import molecule.db.compliance.test.filter.one.types.*
import molecule.db.compliance.test.filter.one.{FilterOneSpecial_String, FilterOne_Enum, FilterOne_id, FilterRefOne}
import molecule.db.sql.mysql.setup.Api_mysql_async

class FilterOne_String_Test extends MUnit {
  FilterOne_String_(this, Api_mysql_async)
}
class FilterOne_IntTest extends MUnit {
  FilterOne_Int(this, Api_mysql_async)
}
class FilterOne_Long_Test extends MUnit {
  FilterOne_Long_(this, Api_mysql_async)
}
class FilterOne_Float_Test extends MUnit {
  FilterOne_Float_(this, Api_mysql_async)
}
class FilterOne_Double_Test extends MUnit {
  FilterOne_Double_(this, Api_mysql_async)
}
class FilterOne_BooleanTest extends MUnit {
  FilterOne_Boolean(this, Api_mysql_async)
}
class FilterOne_BigInt_Test extends MUnit {
  FilterOne_BigInt_(this, Api_mysql_async)
}
class FilterOne_BigDecimal_Test extends MUnit {
  FilterOne_BigDecimal_(this, Api_mysql_async)
}
class FilterOne_Date_Test extends MUnit {
  FilterOne_Date_(this, Api_mysql_async)
}
class FilterOne_Duration_Test extends MUnit {
  FilterOne_Duration_(this, Api_mysql_async)
}
class FilterOne_Instant_Test extends MUnit {
  FilterOne_Instant_(this, Api_mysql_async)
}
class FilterOne_LocalDate_Test extends MUnit {
  FilterOne_LocalDate_(this, Api_mysql_async)
}
class FilterOne_LocalTime_Test extends MUnit {
  FilterOne_LocalTime_(this, Api_mysql_async)
}
class FilterOne_LocalDateTime_Test extends MUnit {
  FilterOne_LocalDateTime_(this, Api_mysql_async)
}
class FilterOne_OffsetTime_Test extends MUnit {
  FilterOne_OffsetTime_(this, Api_mysql_async)
}
class FilterOne_OffsetDateTime_Test extends MUnit {
  FilterOne_OffsetDateTime_(this, Api_mysql_async)
}
class FilterOne_ZonedDateTime_Test extends MUnit {
  FilterOne_ZonedDateTime_(this, Api_mysql_async)
}
class FilterOne_UUID_Test extends MUnit {
  FilterOne_UUID_(this, Api_mysql_async)
}
class FilterOne_URI_Test extends MUnit {
  FilterOne_URI_(this, Api_mysql_async)
}
class FilterOne_Byte_Test extends MUnit {
  FilterOne_Byte_(this, Api_mysql_async)
}
class FilterOne_Short_Test extends MUnit {
  FilterOne_Short_(this, Api_mysql_async)
}
class FilterOne_Char_Test extends MUnit {
  FilterOne_Char_(this, Api_mysql_async)
}

class FilterOne_refTest extends MUnit {
  FilterOne_ref(this, Api_mysql_async)
}

class FilterOne_EnumTest extends MUnit {
  FilterOne_Enum(this, Api_mysql_async)
}
class FilterOne_idTest extends MUnit {
  FilterOne_id(this, Api_mysql_async)
}
class FilterOneSpecial_StringTest extends MUnit {
  FilterOneSpecial_String(this, Api_mysql_async)
}
class FilterRefOneTest extends MUnit {
  FilterRefOne(this, Api_mysql_async)
}
