package molecule.sql.sqlite.compliance.action.insert

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.insert._
import molecule.sql.sqlite.setup.Api_sqlite_async

class InsertCardOne extends Test {
  InsertCardOne(this, Api_sqlite_async)
}
class InsertCardSet extends Test {
  InsertCardSet(this, Api_sqlite_async)
}
class InsertCardMap extends Test {
  InsertCardMap(this, Api_sqlite_async)
}
class InsertCardSeq extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_sqlite_async)
}
class InsertRefs extends Test {
  InsertRefs(this, Api_sqlite_async)
}
class InsertSemantics extends Test {
  InsertSemantics(this, Api_sqlite_async)
}
