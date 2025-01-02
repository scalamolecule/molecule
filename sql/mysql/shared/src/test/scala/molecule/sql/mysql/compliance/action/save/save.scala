package molecule.sql.mysql.compliance.action.save

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.save._
import molecule.sql.mysql.setup.Api_mysql_async

class SaveCardOne extends Test {
  SaveCardOne(this, Api_mysql_async)
}
class SaveCardSeq extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_mysql_async)
}
class SaveCardSet extends Test {
  SaveCardSet(this, Api_mysql_async)
}
class SaveCardMap extends Test {
  SaveCardMap(this, Api_mysql_async)
}
class SaveRefs extends Test {
  SaveRefs(this, Api_mysql_async)
}
class SaveSemantics extends Test {
  SaveSemantics(this, Api_mysql_async)
}
