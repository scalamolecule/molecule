package molecule.sql.mysql.compliance.aggr

import molecule.coreTests.spi.aggr.number._
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_AggrNum_Int extends AggrNum_Int with TestAsync_mysql
object Test_AggrNum_Long extends AggrNum_Long_ with TestAsync_mysql
object Test_AggrNum_Float extends AggrNum_Float_ with TestAsync_mysql
object Test_AggrNum_Double extends AggrNum_Double_ with TestAsync_mysql
object Test_AggrNum_BigInt extends AggrNum_BigInt_ with TestAsync_mysql
object Test_AggrNum_BigDecimal extends AggrNum_BigDecimal_ with TestAsync_mysql
object Test_AggrNum_Byte extends AggrNum_Byte_ with TestAsync_mysql
object Test_AggrNum_Short extends AggrNum_Short_ with TestAsync_mysql


