package molecule.db.mariadb.compliance.filter.map

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.filter.map.MapSemantics
import molecule.db.compliance.test.filter.map.types.*
import molecule.db.mariadb.setup.Api_mariadb_async

class MapSemanticsTest extends MUnit {
  MapSemantics(this, Api_mariadb_async)
}

class FilterMap_String_Test extends MUnit {
  FilterMap_String_(this, Api_mariadb_async)
}
class FilterMap_IntTest extends MUnit {
  FilterMap_Int(this, Api_mariadb_async)
}
class FilterMap_Long_Test extends MUnit {
  FilterMap_Long_(this, Api_mariadb_async)
}
class FilterMap_Float_Test extends MUnit {
  FilterMap_Float_(this, Api_mariadb_async)
}
class FilterMap_Double_Test extends MUnit {
  FilterMap_Double_(this, Api_mariadb_async)
}
class FilterMap_BooleanTest extends MUnit {
  FilterMap_Boolean(this, Api_mariadb_async)
}
class FilterMap_BigInt_Test extends MUnit {
  FilterMap_BigInt_(this, Api_mariadb_async)
}
class FilterMap_BigDecimal_Test extends MUnit {
  FilterMap_BigDecimal_(this, Api_mariadb_async)
}
class FilterMap_Date_Test extends MUnit {
  FilterMap_Date_(this, Api_mariadb_async)
}
class FilterMap_Duration_Test extends MUnit {
  FilterMap_Duration_(this, Api_mariadb_async)
}
class FilterMap_Instant_Test extends MUnit {
  FilterMap_Instant_(this, Api_mariadb_async)
}
class FilterMap_LocalDate_Test extends MUnit {
  FilterMap_LocalDate_(this, Api_mariadb_async)
}
class FilterMap_LocalTime_Test extends MUnit {
  FilterMap_LocalTime_(this, Api_mariadb_async)
}
class FilterMap_LocalDateTime_Test extends MUnit {
  FilterMap_LocalDateTime_(this, Api_mariadb_async)
}
class FilterMap_OffsetTime_Test extends MUnit {
  FilterMap_OffsetTime_(this, Api_mariadb_async)
}
class FilterMap_OffsetDateTime_Test extends MUnit {
  FilterMap_OffsetDateTime_(this, Api_mariadb_async)
}
class FilterMap_ZonedDateTime_Test extends MUnit {
  FilterMap_ZonedDateTime_(this, Api_mariadb_async)
}
class FilterMap_UUID_Test extends MUnit {
  FilterMap_UUID_(this, Api_mariadb_async)
}
class FilterMap_URI_Test extends MUnit {
  FilterMap_URI_(this, Api_mariadb_async)
}
class FilterMap_Byte_Test extends MUnit {
  FilterMap_Byte_(this, Api_mariadb_async)
}
class FilterMap_Short_Test extends MUnit {
  FilterMap_Short_(this, Api_mariadb_async)
}
class FilterMap_Char_Test extends MUnit {
  FilterMap_Char_(this, Api_mariadb_async)
}
