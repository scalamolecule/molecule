package molecule.db.sqlite.compliance.validation.insert

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.validation.insert.*
import molecule.db.sqlite.setup.Api_sqlite_async

class FormatConstantsTest extends MUnit {
  FormatConstants(this, Api_sqlite_async)
}

class FormatVariablesTest extends MUnit {
  FormatVariables(this, Api_sqlite_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_sqlite_async)
}
class SemanticsTest extends MUnit {
  Semantics(this, Api_sqlite_async)
}
class TypesOneTest extends MUnit {
  TypesOne(this, Api_sqlite_async)
}
class TypesOneOptTest extends MUnit {
  TypesOneOpt(this, Api_sqlite_async)
}
class TypesSeqTest extends MUnit {
  TypesSeq(this, Api_sqlite_async)
}
class TypesSeqOptTest extends MUnit {
  TypesSeqOpt(this, Api_sqlite_async)
}
class TypesSetTest extends MUnit {
  TypesSet(this, Api_sqlite_async)
}
class TypesSetOptTest extends MUnit {
  TypesSetOpt(this, Api_sqlite_async)
}
