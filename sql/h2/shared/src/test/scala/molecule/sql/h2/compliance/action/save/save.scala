package molecule.sql.h2.compliance.action.save

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.save._
import molecule.sql.h2.setup.Api_h2_async

class SaveCardOneTest extends Test {
  SaveCardOne(this, Api_h2_async)
}
class SaveCardSeqTest extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_h2_async)
}
class SaveCardSetTest extends Test {
  SaveCardSet(this, Api_h2_async)
}
class SaveCardMapTest extends Test {
  SaveCardMap(this, Api_h2_async)
}
class SaveRefsTest extends Test {
  SaveRefs(this, Api_h2_async)
}
class SaveSemanticsTest extends Test {
  SaveSemantics(this, Api_h2_async)
}
