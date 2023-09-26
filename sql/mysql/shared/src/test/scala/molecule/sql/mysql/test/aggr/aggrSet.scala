package molecule.sql.mysql.test.aggr

import molecule.coreTests.test.aggr.set.any._
import molecule.sql.mysql.setup.TestAsync_mysql

object AggrSet_String extends AggrSet_String_ with TestAsync_mysql
object AggrSet_Int extends AggrSet_Int with TestAsync_mysql
object AggrSet_Long extends AggrSet_Long_ with TestAsync_mysql
object AggrSet_Float extends AggrSet_Float_ with TestAsync_mysql
object AggrSet_Double extends AggrSet_Double_ with TestAsync_mysql
object AggrSet_Boolean extends AggrSet_Boolean with TestAsync_mysql
object AggrSet_BigInt extends AggrSet_BigInt_ with TestAsync_mysql
object AggrSet_BigDecimal extends AggrSet_BigDecimal_ with TestAsync_mysql
object AggrSet_Date extends AggrSet_Date_ with TestAsync_mysql
object AggrSet_UUID extends AggrSet_UUID_ with TestAsync_mysql
object AggrSet_URI extends AggrSet_URI_ with TestAsync_mysql
object AggrSet_Byte extends AggrSet_Byte_ with TestAsync_mysql
object AggrSet_Short extends AggrSet_Short_ with TestAsync_mysql
object AggrSet_Char extends AggrSet_Char_ with TestAsync_mysql
object AggrSet_ref extends AggrSet_ref with TestAsync_mysql
