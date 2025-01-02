package molecule.sql.sqlite.compliance.action.save

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.save._
import molecule.sql.sqlite.setup.Api_sqlite_async

class SaveCardOne extends Test {
  SaveCardOne(this, Api_sqlite_async)
}
class SaveCardSeq extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_sqlite_async)
}
class SaveCardSet extends Test {
  SaveCardSet(this, Api_sqlite_async)
}
class SaveCardMap extends Test {
  SaveCardMap(this, Api_sqlite_async)
}
class SaveRefs extends Test {
  SaveRefs(this, Api_sqlite_async)
}
class SaveSemantics extends Test {
  SaveSemantics(this, Api_sqlite_async)
}
