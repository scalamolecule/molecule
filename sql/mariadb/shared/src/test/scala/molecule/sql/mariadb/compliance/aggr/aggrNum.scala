package molecule.sql.mariadb.compliance.aggr

import molecule.coreTests.spi.aggr.number._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object AggrNum_Int extends AggrNum_Int with TestAsync_mariadb
object AggrNum_Long extends AggrNum_Long_ with TestAsync_mariadb
object AggrNum_Float extends AggrNum_Float_ with TestAsync_mariadb
object AggrNum_Double extends AggrNum_Double_ with TestAsync_mariadb
object AggrNum_BigInt extends AggrNum_BigInt_ with TestAsync_mariadb
object AggrNum_BigDecimal extends AggrNum_BigDecimal_ with TestAsync_mariadb
object AggrNum_Byte extends AggrNum_Byte_ with TestAsync_mariadb
object AggrNum_Short extends AggrNum_Short_ with TestAsync_mariadb


