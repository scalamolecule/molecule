package molecule.sql.h2.compliance.transaction.save

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.save._
import molecule.sql.h2.setup.Api_h2_async

class SaveCardOne extends MUnitSuite {
  SaveCardOne(this, Api_h2_async)
}
class SaveCardSeq extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_h2_async)
}
class SaveCardSet extends MUnitSuite {
  SaveCardSet(this, Api_h2_async)
}
class SaveCardMap extends MUnitSuite {
  SaveCardMap(this, Api_h2_async)
}
class SaveRefs extends MUnitSuite {
  SaveRefs(this, Api_h2_async)
}
class SaveSemantics extends MUnitSuite {
  SaveSemantics(this, Api_h2_async)
}
