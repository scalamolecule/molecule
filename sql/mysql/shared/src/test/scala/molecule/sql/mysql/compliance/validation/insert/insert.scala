package molecule.sql.mysql.compliance.validation.insert

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.insert._
import molecule.sql.mysql.setup.Api_mysql_async

class FormatConstants extends MUnitSuite {
  FormatConstants(this, Api_mysql_async)
}


class FormatVariables extends MUnitSuite {
  FormatVariables(this, Api_mysql_async)
}
class Nested extends MUnitSuite {
  Nested(this, Api_mysql_async)
}
class Semantics extends MUnitSuite {
  Semantics(this, Api_mysql_async)
}
class TypesOne extends MUnitSuite {
  TypesOne(this, Api_mysql_async)
}
class TypesOneOpt extends MUnitSuite {
  TypesOneOpt(this, Api_mysql_async)
}
class TypesSeq extends MUnitSuite {
  TypesSeq(this, Api_mysql_async)
}
class TypesSeqOpt extends MUnitSuite {
  TypesSeqOpt(this, Api_mysql_async)
}
class TypesSet extends MUnitSuite {
  TypesSet(this, Api_mysql_async)
}
class TypesSetOpt extends MUnitSuite {
  TypesSetOpt(this, Api_mysql_async)
}
