package molecule.db.sql.mariadb.compliance.bind

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.bind.*
import molecule.db.compliance.test.bind.types.*
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class SemanticsTest extends Test {
  Semantics(this, Api_mariadb_async)
}

class RefsTest extends Test {
  Refs(this, Api_mariadb_async)
}

class NestedTest extends Test {
  Nested(this, Api_mariadb_async)
}

class StringOpsTest extends Test {
  StringOps(this, Api_mariadb_async)
}


class Bind_String_Test extends Test {
  Bind_String_(this, Api_mariadb_async)
}
class Bind_IntTest extends Test {
  Bind_Int(this, Api_mariadb_async)
}
class Bind_Long_Test extends Test {
  Bind_Long_(this, Api_mariadb_async)
}
class Bind_Float_Test extends Test {
  Bind_Float_(this, Api_mariadb_async)
}
class Bind_Double_Test extends Test {
  Bind_Double_(this, Api_mariadb_async)
}
class Bind_BooleanTest extends Test {
  Bind_Boolean(this, Api_mariadb_async)
}
class Bind_BigInt_Test extends Test {
  Bind_BigInt_(this, Api_mariadb_async)
}
class Bind_BigDecimal_Test extends Test {
  Bind_BigDecimal_(this, Api_mariadb_async)
}
class Bind_Date_Test extends Test {
  Bind_Date_(this, Api_mariadb_async)
}
class Bind_Duration_Test extends Test {
  Bind_Duration_(this, Api_mariadb_async)
}
class Bind_Instant_Test extends Test {
  Bind_Instant_(this, Api_mariadb_async)
}
class Bind_LocalDate_Test extends Test {
  Bind_LocalDate_(this, Api_mariadb_async)
}
class Bind_LocalTime_Test extends Test {
  Bind_LocalTime_(this, Api_mariadb_async)
}
class Bind_LocalDateTime_Test extends Test {
  Bind_LocalDateTime_(this, Api_mariadb_async)
}
class Bind_OffsetTime_Test extends Test {
  Bind_OffsetTime_(this, Api_mariadb_async)
}
class Bind_OffsetDateTime_Test extends Test {
  Bind_OffsetDateTime_(this, Api_mariadb_async)
}
class Bind_ZonedDateTime_Test extends Test {
  Bind_ZonedDateTime_(this, Api_mariadb_async)
}
class Bind_UUID_Test extends Test {
  Bind_UUID_(this, Api_mariadb_async)
}
class Bind_URI_Test extends Test {
  Bind_URI_(this, Api_mariadb_async)
}
class Bind_Byte_Test extends Test {
  Bind_Byte_(this, Api_mariadb_async)
}
class Bind_Short_Test extends Test {
  Bind_Short_(this, Api_mariadb_async)
}
class Bind_Char_Test extends Test {
  Bind_Char_(this, Api_mariadb_async)
}

class Bind_Ref_Test extends Test {
  Bind_Ref(this, Api_mariadb_async)
}
