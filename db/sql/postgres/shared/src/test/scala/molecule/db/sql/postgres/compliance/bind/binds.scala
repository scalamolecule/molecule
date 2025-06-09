package molecule.db.sql.postgres.compliance.bind

import molecule.core.setup.MUnit
import molecule.db.compliance.test.bind.*
import molecule.db.compliance.test.bind.types.*
import molecule.db.sql.postgres.setup.Api_postgres_async

class SemanticsTest extends MUnit {
  Semantics(this, Api_postgres_async)
}

class RefsTest extends MUnit {
  Refs(this, Api_postgres_async)
}

class NestedTest extends MUnit {
  Nested(this, Api_postgres_async)
}

class StringOpsTest extends MUnit {
  StringOps(this, Api_postgres_async)
}


class Bind_String_Test extends MUnit {
  Bind_String_(this, Api_postgres_async)
}
class Bind_IntTest extends MUnit {
  Bind_Int(this, Api_postgres_async)
}
class Bind_Long_Test extends MUnit {
  Bind_Long_(this, Api_postgres_async)
}
class Bind_Float_Test extends MUnit {
  Bind_Float_(this, Api_postgres_async)
}
class Bind_Double_Test extends MUnit {
  Bind_Double_(this, Api_postgres_async)
}
class Bind_BooleanTest extends MUnit {
  Bind_Boolean(this, Api_postgres_async)
}
class Bind_BigInt_Test extends MUnit {
  Bind_BigInt_(this, Api_postgres_async)
}
class Bind_BigDecimal_Test extends MUnit {
  Bind_BigDecimal_(this, Api_postgres_async)
}
class Bind_Date_Test extends MUnit {
  Bind_Date_(this, Api_postgres_async)
}
class Bind_Duration_Test extends MUnit {
  Bind_Duration_(this, Api_postgres_async)
}
class Bind_Instant_Test extends MUnit {
  Bind_Instant_(this, Api_postgres_async)
}
class Bind_LocalDate_Test extends MUnit {
  Bind_LocalDate_(this, Api_postgres_async)
}
class Bind_LocalTime_Test extends MUnit {
  Bind_LocalTime_(this, Api_postgres_async)
}
class Bind_LocalDateTime_Test extends MUnit {
  Bind_LocalDateTime_(this, Api_postgres_async)
}
class Bind_OffsetTime_Test extends MUnit {
  Bind_OffsetTime_(this, Api_postgres_async)
}
class Bind_OffsetDateTime_Test extends MUnit {
  Bind_OffsetDateTime_(this, Api_postgres_async)
}
class Bind_ZonedDateTime_Test extends MUnit {
  Bind_ZonedDateTime_(this, Api_postgres_async)
}
class Bind_UUID_Test extends MUnit {
  Bind_UUID_(this, Api_postgres_async)
}
class Bind_URI_Test extends MUnit {
  Bind_URI_(this, Api_postgres_async)
}
class Bind_Byte_Test extends MUnit {
  Bind_Byte_(this, Api_postgres_async)
}
class Bind_Short_Test extends MUnit {
  Bind_Short_(this, Api_postgres_async)
}
class Bind_Char_Test extends MUnit {
  Bind_Char_(this, Api_postgres_async)
}

class Bind_Ref_Test extends MUnit {
  Bind_Ref(this, Api_postgres_async)
}
