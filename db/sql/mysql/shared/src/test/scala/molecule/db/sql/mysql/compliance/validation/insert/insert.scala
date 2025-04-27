package molecule.db.sql.mysql.compliance.validation.insert

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.insert.*
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class FormatConstantsTest extends Test {
  FormatConstants(this, Api_mysql_async)
}


class FormatVariablesTest extends Test {
  FormatVariables(this, Api_mysql_async)
}
class NestedTest extends Test {
  Nested(this, Api_mysql_async)
}
class SemanticsTest extends Test {
  Semantics(this, Api_mysql_async)
}
class TypesOneTest extends Test {
  TypesOne(this, Api_mysql_async)
}
class TypesOneOptTest extends Test {
  TypesOneOpt(this, Api_mysql_async)
}
class TypesSeqTest extends Test {
  TypesSeq(this, Api_mysql_async)
}
class TypesSeqOptTest extends Test {
  TypesSeqOpt(this, Api_mysql_async)
}
class TypesSetTest extends Test {
  TypesSet(this, Api_mysql_async)
}
class TypesSetOptTest extends Test {
  TypesSetOpt(this, Api_mysql_async)
}
