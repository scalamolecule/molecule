package molecule.sql.sqlite.compliance.validation.update

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.update._
import molecule.sql.sqlite.setup.Api_sqlite_async

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
