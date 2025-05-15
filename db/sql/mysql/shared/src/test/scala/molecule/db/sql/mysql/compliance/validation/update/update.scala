package molecule.db.sql.mysql.compliance.validation.update

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.update.*
import molecule.db.sql.mysql.setup.Api_mysql_async

class TypesOneTest extends Test {
  TypesOne(this, Api_mysql_async)
}
class TypesOneOptTest extends Test {
  TypesOneOpt(this, Api_mysql_async)
}
class TypesSeqTest extends Test {
  TypesSeq(this, Api_mysql_async)
}
class TypesSeqOptTest extends Test {
  TypesSeqOpt(this, Api_mysql_async)
}
class TypesSetTest extends Test {
  TypesSet(this, Api_mysql_async)
}
class TypesSetOptTest extends Test {
  TypesSetOpt(this, Api_mysql_async)
}
