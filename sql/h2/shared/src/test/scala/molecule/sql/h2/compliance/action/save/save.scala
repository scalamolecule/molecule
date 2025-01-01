package molecule.sql.h2.compliance.action.save

import molecule.coreTests.setup.{Test, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.save._
import molecule.sql.h2.setup.Api_h2_async

class SaveCardOne extends Test {
  SaveCardOne(this, Api_h2_async)
}
class SaveCardSeq extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_h2_async)
}
class SaveCardSet extends Test {
  SaveCardSet(this, Api_h2_async)
}
class SaveCardMap extends Test {
  SaveCardMap(this, Api_h2_async)
}
class SaveRefs extends Test {
  SaveRefs(this, Api_h2_async)
}
class SaveSemantics extends Test {
  SaveSemantics(this, Api_h2_async)
}
