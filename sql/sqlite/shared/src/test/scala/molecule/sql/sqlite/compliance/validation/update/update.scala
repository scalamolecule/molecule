package molecule.sql.sqlite.compliance.validation.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.update._
import molecule.sql.sqlite.setup.Api_sqlite_async

class TypesOne extends Test {
  TypesOne(this, Api_sqlite_async)
}
class TypesOneOpt extends Test {
  TypesOneOpt(this, Api_sqlite_async)
}
class TypesSeq extends Test {
  TypesSeq(this, Api_sqlite_async)
}
class TypesSeqOpt extends Test {
  TypesSeqOpt(this, Api_sqlite_async)
}
class TypesSet extends Test {
  TypesSet(this, Api_sqlite_async)
}
class TypesSetOpt extends Test {
  TypesSetOpt(this, Api_sqlite_async)
}
