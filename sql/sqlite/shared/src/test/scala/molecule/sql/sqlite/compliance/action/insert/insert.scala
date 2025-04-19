package molecule.sql.sqlite.compliance.action.insert

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.insert.*
import molecule.sql.sqlite.setup.Api_sqlite_async

class InsertCardOneTest extends Test {
  InsertCardOne(this, Api_sqlite_async)
}
class InsertCardSetTest extends Test {
  InsertCardSet(this, Api_sqlite_async)
}
class InsertCardMapTest extends Test {
  InsertCardMap(this, Api_sqlite_async)
}
class InsertCardSeqTest extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_sqlite_async)
}
class InsertRefsTest extends Test {
  InsertRefs(this, Api_sqlite_async)
}
class InsertSemanticsTest extends Test {
  InsertSemantics(this, Api_sqlite_async)
}
