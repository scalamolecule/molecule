package molecule.sql.h2.compliance.action.insert

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.insert.*
import molecule.sql.h2.setup.Api_h2_async

class InsertCardOneTest extends Test {
  InsertCardOne(this, Api_h2_async)
}
class InsertCardSetTest extends Test {
  InsertCardSet(this, Api_h2_async)
}
class InsertCardMapTest extends Test {
  InsertCardMap(this, Api_h2_async)
}
class InsertCardSeqTest extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_h2_async)
}
class InsertRefsTest extends Test {
  InsertRefs(this, Api_h2_async)
}
class InsertSemanticsTest extends Test {
  InsertSemantics(this, Api_h2_async)
}
