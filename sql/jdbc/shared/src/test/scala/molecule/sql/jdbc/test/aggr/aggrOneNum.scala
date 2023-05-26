package molecule.sql.jdbc.test.aggr

import molecule.coreTests.test.aggr.one.number._
import molecule.sql.jdbc.setup.CoreTestAsync

object AggrOneNum_Int extends AggrOneNum_Int with CoreTestAsync
object AggrOneNum_Long extends AggrOneNum_Long_ with CoreTestAsync
object AggrOneNum_Float extends AggrOneNum_Float_ with CoreTestAsync
object AggrOneNum_Double extends AggrOneNum_Double_ with CoreTestAsync
object AggrOneNum_BigInt extends AggrOneNum_BigInt_ with CoreTestAsync
object AggrOneNum_BigDecimal extends AggrOneNum_BigDecimal_ with CoreTestAsync
object AggrOneNum_Byte extends AggrOneNum_Byte_ with CoreTestAsync
object AggrOneNum_Short extends AggrOneNum_Short_ with CoreTestAsync
object AggrOneNum_ref extends AggrOneNum_ref_ with CoreTestAsync
