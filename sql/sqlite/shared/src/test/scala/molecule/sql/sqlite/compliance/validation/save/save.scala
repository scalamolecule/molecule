package molecule.sql.sqlite.compliance.validation.save

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.insert.FormatConstants
import molecule.coreTests.spi.validation.save._
import molecule.sql.sqlite.setup.Api_sqlite_async

class FormatConstants extends MUnitSuite {
  FormatConstants(this, Api_sqlite_async)
}

class FormatVariables extends MUnitSuite {
  FormatVariables(this, Api_sqlite_async)
}
class Semantics extends MUnitSuite {
  Semantics(this, Api_sqlite_async)
}
class TypesOne extends MUnitSuite {
  TypesOne(this, Api_sqlite_async)
}
class TypesOneOpt extends MUnitSuite {
  TypesOneOpt(this, Api_sqlite_async)
}
class TypesSeq extends MUnitSuite {
  TypesSeq(this, Api_sqlite_async)
}
class TypesSeqOpt extends MUnitSuite {
  TypesSeqOpt(this, Api_sqlite_async)
}
class TypesSet extends MUnitSuite {
  TypesSet(this, Api_sqlite_async)
}
class TypesSetOpt extends MUnitSuite {
  TypesSetOpt(this, Api_sqlite_async)
}
