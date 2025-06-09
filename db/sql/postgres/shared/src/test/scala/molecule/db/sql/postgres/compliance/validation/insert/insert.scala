package molecule.db.sql.postgres.compliance.validation.insert

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.insert.*
import molecule.db.sql.postgres.setup.Api_postgres_async

class FormatConstantsTest extends MUnit {
  FormatConstants(this, Api_postgres_async)
}
class FormatVariablesTest extends MUnit {
  FormatVariables(this, Api_postgres_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_postgres_async)
}
class SemanticsTest extends MUnit {
  Semantics(this, Api_postgres_async)
}
class TypesOneTest extends MUnit {
  TypesOne(this, Api_postgres_async)
}
class TypesOneOptTest extends MUnit {
  TypesOneOpt(this, Api_postgres_async)
}
class TypesSeqTest extends MUnit {
  TypesSeq(this, Api_postgres_async)
}
class TypesSeqOptTest extends MUnit {
  TypesSeqOpt(this, Api_postgres_async)
}
class TypesSetTest extends MUnit {
  TypesSet(this, Api_postgres_async)
}
class TypesSetOptTest extends MUnit {
  TypesSetOpt(this, Api_postgres_async)
}
