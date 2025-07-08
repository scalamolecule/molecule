package molecule.db.sql.h2.compliance.validation.insert

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.insert.*
import molecule.db.sql.h2.setup.Api_h2_async

class FormatConstantsTest extends MUnit {
  FormatConstants(this, Api_h2_async)
}

class FormatVariablesTest extends MUnit {
  FormatVariables(this, Api_h2_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_h2_async)
}
class SemanticsTest extends MUnit {
  Semantics(this, Api_h2_async)
}
class TypesOneTest extends MUnit {
  TypesOne(this, Api_h2_async)
}
class TypesOneOptTest extends MUnit {
  TypesOneOpt(this, Api_h2_async)
}
class TypesSeqTest extends MUnit {
  TypesSeq(this, Api_h2_async)
}
class TypesSeqOptTest extends MUnit {
  TypesSeqOpt(this, Api_h2_async)
}
class TypesSetTest extends MUnit {
  TypesSet(this, Api_h2_async)
}
class TypesSetOptTest extends MUnit {
  TypesSetOpt(this, Api_h2_async)
}
