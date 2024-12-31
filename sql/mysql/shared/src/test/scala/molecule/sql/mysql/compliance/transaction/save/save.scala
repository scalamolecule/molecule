package molecule.sql.mysql.compliance.transaction.save

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.save._
import molecule.sql.mysql.setup.Api_mysql_async

class SaveCardOne extends MUnitSuite {
  SaveCardOne(this, Api_mysql_async)
}
class SaveCardSeq extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_mysql_async)
}
class SaveCardSet extends MUnitSuite {
  SaveCardSet(this, Api_mysql_async)
}
class SaveCardMap extends MUnitSuite {
  SaveCardMap(this, Api_mysql_async)
}
class SaveRefs extends MUnitSuite {
  SaveRefs(this, Api_mysql_async)
}
class SaveSemantics extends MUnitSuite {
  SaveSemantics(this, Api_mysql_async)
}
