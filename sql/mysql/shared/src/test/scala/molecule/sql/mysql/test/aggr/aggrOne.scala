package molecule.sql.mysql.test.aggr

import molecule.coreTests.test.aggr.one.AggrOne_id
import molecule.coreTests.test.aggr.one.any._
import molecule.sql.mysql.setup.TestAsync_mysql

object AggrOne_String extends AggrOne_String_ with TestAsync_mysql
object AggrOne_Int extends AggrOne_Int with TestAsync_mysql
object AggrOne_Long extends AggrOne_Long_ with TestAsync_mysql
object AggrOne_Float extends AggrOne_Float_ with TestAsync_mysql
object AggrOne_Double extends AggrOne_Double_ with TestAsync_mysql
object AggrOne_Boolean extends AggrOne_Boolean with TestAsync_mysql
object AggrOne_BigInt extends AggrOne_BigInt_ with TestAsync_mysql
object AggrOne_BigDecimal extends AggrOne_BigDecimal_ with TestAsync_mysql
object AggrOne_Date extends AggrOne_Date_ with TestAsync_mysql
object AggrOne_UUID extends AggrOne_UUID_ with TestAsync_mysql
object AggrOne_URI extends AggrOne_URI_ with TestAsync_mysql
object AggrOne_Byte extends AggrOne_Byte_ with TestAsync_mysql
object AggrOne_Short extends AggrOne_Short_ with TestAsync_mysql
object AggrOne_Char extends AggrOne_Char_ with TestAsync_mysql
object AggrOne_ref extends AggrOne_ref_ with TestAsync_mysql

object AggrOne_id extends AggrOne_id with TestAsync_mysql
