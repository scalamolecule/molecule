package molecule.db.sql.mariadb.compliance.validation.save

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.save.*
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class FormatConstantsTest extends Test {
  FormatConstants(this, Api_mariadb_async)
}
class FormatVariablesTest extends Test {
  FormatVariables(this, Api_mariadb_async)
}
class SemanticsTest extends Test {
  Semantics(this, Api_mariadb_async)
}
class TypesOneTest extends Test {
  TypesOne(this, Api_mariadb_async)
}
class TypesOneOptTest extends Test {
  TypesOneOpt(this, Api_mariadb_async)
}
class TypesSeqTest extends Test {
  TypesSeq(this, Api_mariadb_async)
}
class TypesSeqOptTest extends Test {
  TypesSeqOpt(this, Api_mariadb_async)
}
class TypesSetTest extends Test {
  TypesSet(this, Api_mariadb_async)
}
class TypesSetOptTest extends Test {
  TypesSetOpt(this, Api_mariadb_async)
}
