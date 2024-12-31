package molecule.sql.sqlite.compliance.action.save

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.save._
import molecule.sql.sqlite.setup.Api_sqlite_async

class SaveCardOne extends MUnitSuite {
  SaveCardOne(this, Api_sqlite_async)
}
class SaveCardSeq extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_sqlite_async)
}
class SaveCardSet extends MUnitSuite {
  SaveCardSet(this, Api_sqlite_async)
}
class SaveCardMap extends MUnitSuite {
  SaveCardMap(this, Api_sqlite_async)
}
class SaveRefs extends MUnitSuite {
  SaveRefs(this, Api_sqlite_async)
}
class SaveSemantics extends MUnitSuite {
  SaveSemantics(this, Api_sqlite_async)
}
