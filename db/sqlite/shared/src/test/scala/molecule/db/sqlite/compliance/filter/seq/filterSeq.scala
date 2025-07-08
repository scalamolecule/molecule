package molecule.db.sqlite.compliance.filter.seq

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.filter.seq.ref.{FilterRefSeq_Card1Ref, FilterRefSeq_Card2Ref}
import molecule.db.compliance.test.filter.seq.types.*
import molecule.db.compliance.test.filter.seq.{FilterSeq_Enum, SeqSemantics}
import molecule.db.sqlite.setup.Api_sqlite_async

class FilterSeq_String_Test extends MUnit {
  FilterSeq_String_(this, Api_sqlite_async)
}
class FilterSeq_IntTest extends MUnit {
  FilterSeq_Int(this, Api_sqlite_async)
}
class FilterSeq_Long_Test extends MUnit {
  FilterSeq_Long_(this, Api_sqlite_async)
}
class FilterSeq_Float_Test extends MUnit {
  FilterSeq_Float_(this, Api_sqlite_async)
}
class FilterSeq_Double_Test extends MUnit {
  FilterSeq_Double_(this, Api_sqlite_async)
}
class FilterSeq_BooleanTest extends MUnit {
  FilterSeq_Boolean(this, Api_sqlite_async)
}
class FilterSeq_BigInt_Test extends MUnit {
  FilterSeq_BigInt_(this, Api_sqlite_async)
}
class FilterSeq_BigDecimal_Test extends MUnit {
  FilterSeq_BigDecimal_(this, Api_sqlite_async)
}
class FilterSeq_Date_Test extends MUnit {
  FilterSeq_Date_(this, Api_sqlite_async)
}
class FilterSeq_Duration_Test extends MUnit {
  FilterSeq_Duration_(this, Api_sqlite_async)
}
class FilterSeq_Instant_Test extends MUnit {
  FilterSeq_Instant_(this, Api_sqlite_async)
}
class FilterSeq_LocalDate_Test extends MUnit {
  FilterSeq_LocalDate_(this, Api_sqlite_async)
}
class FilterSeq_LocalTime_Test extends MUnit {
  FilterSeq_LocalTime_(this, Api_sqlite_async)
}
class FilterSeq_LocalDateTime_Test extends MUnit {
  FilterSeq_LocalDateTime_(this, Api_sqlite_async)
}
class FilterSeq_OffsetTime_Test extends MUnit {
  FilterSeq_OffsetTime_(this, Api_sqlite_async)
}
class FilterSeq_OffsetDateTime_Test extends MUnit {
  FilterSeq_OffsetDateTime_(this, Api_sqlite_async)
}
class FilterSeq_ZonedDateTime_Test extends MUnit {
  FilterSeq_ZonedDateTime_(this, Api_sqlite_async)
}
class FilterSeq_UUID_Test extends MUnit {
  FilterSeq_UUID_(this, Api_sqlite_async)
}
class FilterSeq_URI_Test extends MUnit {
  FilterSeq_URI_(this, Api_sqlite_async)
}
class FilterSeq_Byte_Test extends MUnit_arrays {
  FilterSeq_ByteArray(this, Api_sqlite_async)
}
class FilterSeq_Short_Test extends MUnit {
  FilterSeq_Short_(this, Api_sqlite_async)
}
class FilterSeq_Char_Test extends MUnit {
  FilterSeq_Char_(this, Api_sqlite_async)
}

class FilterSeq_EnumTest extends MUnit {
  FilterSeq_Enum(this, Api_sqlite_async)
}
class SeqSemanticsTest extends MUnit {
  SeqSemantics(this, Api_sqlite_async)
}

class FilterRefSeq_Card1RefTest extends MUnit {
  FilterRefSeq_Card1Ref(this, Api_sqlite_async)
}
class FilterRefSeq_Card2RefTest extends MUnit {
  FilterRefSeq_Card2Ref(this, Api_sqlite_async)
}
