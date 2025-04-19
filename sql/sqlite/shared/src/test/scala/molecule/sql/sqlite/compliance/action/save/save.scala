package molecule.sql.sqlite.compliance.action.save

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.save.*
import molecule.sql.sqlite.setup.Api_sqlite_async

class SaveCardOneTest extends Test {
  SaveCardOne(this, Api_sqlite_async)
}
class SaveCardSeqTest extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_sqlite_async)
}
class SaveCardSetTest extends Test {
  SaveCardSet(this, Api_sqlite_async)
}
class SaveCardMapTest extends Test {
  SaveCardMap(this, Api_sqlite_async)
}
class SaveRefsTest extends Test {
  SaveRefs(this, Api_sqlite_async)
}
class SaveSemanticsTest extends Test {
  SaveSemantics(this, Api_sqlite_async)
}
