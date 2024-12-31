package molecule.sql.sqlite.compliance.action.insert

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.insert._
import molecule.sql.sqlite.setup.Api_sqlite_async

class InsertCardOne extends MUnitSuite {
  InsertCardOne(this, Api_sqlite_async)
}
class InsertCardSet extends MUnitSuite {
  InsertCardSet(this, Api_sqlite_async)
}
class InsertCardMap extends MUnitSuite {
  InsertCardMap(this, Api_sqlite_async)
}
class InsertCardSeq extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_sqlite_async)
}
class InsertRefs extends MUnitSuite {
  InsertRefs(this, Api_sqlite_async)
}
class InsertSemantics extends MUnitSuite {
  InsertSemantics(this, Api_sqlite_async)
}
