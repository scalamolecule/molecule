package molecule.db.sql.postgres.compliance.filter.seq

import molecule.db.compliance.setup.{MUnitSuiteWithArrays, Test}
import molecule.db.compliance.test.filter.seq.SeqSemantics
import molecule.db.compliance.test.filter.seq.ref.{FilterRefSeq_Card1Ref, FilterRefSeq_Card2Ref}
import molecule.db.compliance.test.filter.seq.types.{FilterSeq_BigDecimal_, FilterSeq_BigInt_, FilterSeq_Boolean, FilterSeq_ByteArray, FilterSeq_Char_, FilterSeq_Date_, FilterSeq_Double_, FilterSeq_Duration_, FilterSeq_Float_, FilterSeq_Instant_, FilterSeq_Int, FilterSeq_LocalDateTime_, FilterSeq_LocalDate_, FilterSeq_LocalTime_, FilterSeq_Long_, FilterSeq_OffsetDateTime_, FilterSeq_OffsetTime_, FilterSeq_Short_, FilterSeq_String_, FilterSeq_URI_, FilterSeq_UUID_, FilterSeq_ZonedDateTime_}
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class FilterSeq_String_Test extends Test {
  FilterSeq_String_(this, Api_postgres_async)
}
class FilterSeq_IntTest extends Test {
  FilterSeq_Int(this, Api_postgres_async)
}
class FilterSeq_Long_Test extends Test {
  FilterSeq_Long_(this, Api_postgres_async)
}
class FilterSeq_Float_Test extends Test {
  FilterSeq_Float_(this, Api_postgres_async)
}
class FilterSeq_Double_Test extends Test {
  FilterSeq_Double_(this, Api_postgres_async)
}
class FilterSeq_BooleanTest extends Test {
  FilterSeq_Boolean(this, Api_postgres_async)
}
class FilterSeq_BigInt_Test extends Test {
  FilterSeq_BigInt_(this, Api_postgres_async)
}
class FilterSeq_BigDecimal_Test extends Test {
  FilterSeq_BigDecimal_(this, Api_postgres_async)
}
class FilterSeq_Date_Test extends Test {
  FilterSeq_Date_(this, Api_postgres_async)
}
class FilterSeq_Duration_Test extends Test {
  FilterSeq_Duration_(this, Api_postgres_async)
}
class FilterSeq_Instant_Test extends Test {
  FilterSeq_Instant_(this, Api_postgres_async)
}
class FilterSeq_LocalDate_Test extends Test {
  FilterSeq_LocalDate_(this, Api_postgres_async)
}
class FilterSeq_LocalTime_Test extends Test {
  FilterSeq_LocalTime_(this, Api_postgres_async)
}
class FilterSeq_LocalDateTime_Test extends Test {
  FilterSeq_LocalDateTime_(this, Api_postgres_async)
}
class FilterSeq_OffsetTime_Test extends Test {
  FilterSeq_OffsetTime_(this, Api_postgres_async)
}
class FilterSeq_OffsetDateTime_Test extends Test {
  FilterSeq_OffsetDateTime_(this, Api_postgres_async)
}
class FilterSeq_ZonedDateTime_Test extends Test {
  FilterSeq_ZonedDateTime_(this, Api_postgres_async)
}
class FilterSeq_UUID_Test extends Test {
  FilterSeq_UUID_(this, Api_postgres_async)
}
class FilterSeq_URI_Test extends Test {
  FilterSeq_URI_(this, Api_postgres_async)
}
class FilterSeq_Byte_Test extends MUnitSuiteWithArrays {
  FilterSeq_ByteArray(this, Api_postgres_async)
}
class FilterSeq_Short_Test extends Test {
  FilterSeq_Short_(this, Api_postgres_async)
}
class FilterSeq_Char_Test extends Test {
  FilterSeq_Char_(this, Api_postgres_async)
}

class SeqSemanticsTest extends Test {
  SeqSemantics(this, Api_postgres_async)
}


class FilterRefSeq_Card1RefTest extends Test {
  FilterRefSeq_Card1Ref(this, Api_postgres_async)
}
class FilterRefSeq_Card2RefTest extends Test {
  FilterRefSeq_Card2Ref(this, Api_postgres_async)
}
