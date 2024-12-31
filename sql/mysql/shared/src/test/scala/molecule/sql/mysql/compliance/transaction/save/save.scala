package molecule.sql.mysql.compliance.transaction.save

import molecule.coreTests.setup.{Test, MUnitSuiteWithArrays}
import molecule.coreTests.spi.transaction.save._
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
