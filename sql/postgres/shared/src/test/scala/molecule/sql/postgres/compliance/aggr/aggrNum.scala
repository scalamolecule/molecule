package molecule.sql.postgres.compliance.aggr

import molecule.coreTests.spi.aggr.number.{AggrNum_BigDecimal_, AggrNum_BigInt_, AggrNum_Byte_, AggrNum_Double_, AggrNum_Float_, AggrNum_Int, AggrNum_Long_, AggrNum_Short_}
import molecule.sql.postgres.setup.TestAsync_postgres

object AggrOneNum_Int extends AggrNum_Int with TestAsync_postgres
object AggrOneNum_Long extends AggrNum_Long_ with TestAsync_postgres
object AggrOneNum_Float extends AggrNum_Float_ with TestAsync_postgres
object AggrOneNum_Double extends AggrNum_Double_ with TestAsync_postgres
object AggrOneNum_BigInt extends AggrNum_BigInt_ with TestAsync_postgres
object AggrOneNum_BigDecimal extends AggrNum_BigDecimal_ with TestAsync_postgres
object AggrOneNum_Byte extends AggrNum_Byte_ with TestAsync_postgres
object AggrOneNum_Short extends AggrNum_Short_ with TestAsync_postgres
