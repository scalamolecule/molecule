package molecule.db.datalog.datomic.compliance.validation.save

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.save.*
import molecule.db.datalog.datomic.setup.Api_datomic_async

class FormatConstantsTest extends MUnit {
  FormatConstants(this, Api_datomic_async)
}
class FormatVariablesTest extends MUnit {
  FormatVariables(this, Api_datomic_async)
}
class SemanticsTest extends MUnit {
  Semantics(this, Api_datomic_async)
}
class TypesOneTest extends MUnit {
  TypesOne(this, Api_datomic_async)
}
class TypesOneOptTest extends MUnit {
  TypesOneOpt(this, Api_datomic_async)
}
class TypesSeqTest extends MUnit {
  TypesSeq(this, Api_datomic_async)
}
class TypesSeqOptTest extends MUnit {
  TypesSeqOpt(this, Api_datomic_async)
}
class TypesSetTest extends MUnit {
  TypesSet(this, Api_datomic_async)
}
class TypesSetOptTest extends MUnit {
  TypesSetOpt(this, Api_datomic_async)
}
