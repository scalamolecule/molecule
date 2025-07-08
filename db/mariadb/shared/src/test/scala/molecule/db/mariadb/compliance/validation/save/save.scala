package molecule.db.mariadb.compliance.validation.save

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.validation.save.*
import molecule.db.mariadb.setup.Api_mariadb_async

class FormatConstantsTest extends MUnit {
  FormatConstants(this, Api_mariadb_async)
}
class FormatVariablesTest extends MUnit {
  FormatVariables(this, Api_mariadb_async)
}
class SemanticsTest extends MUnit {
  Semantics(this, Api_mariadb_async)
}
class TypesOneTest extends MUnit {
  TypesOne(this, Api_mariadb_async)
}
class TypesOneOptTest extends MUnit {
  TypesOneOpt(this, Api_mariadb_async)
}
class TypesSeqTest extends MUnit {
  TypesSeq(this, Api_mariadb_async)
}
class TypesSeqOptTest extends MUnit {
  TypesSeqOpt(this, Api_mariadb_async)
}
class TypesSetTest extends MUnit {
  TypesSet(this, Api_mariadb_async)
}
class TypesSetOptTest extends MUnit {
  TypesSetOpt(this, Api_mariadb_async)
}
