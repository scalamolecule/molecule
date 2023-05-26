package molecule.sql.jdbc.test.aggr

import molecule.coreTests.test.aggr.set.number._
import molecule.sql.jdbc.setup.CoreTestAsync

object AggrSetNum_Int extends AggrSetNum_Int with CoreTestAsync
object AggrSetNum_Long extends AggrSetNum_Long_ with CoreTestAsync
object AggrSetNum_Float extends AggrSetNum_Float_ with CoreTestAsync
object AggrSetNum_Double extends AggrSetNum_Double_ with CoreTestAsync
object AggrSetNum_BigInt extends AggrSetNum_BigInt_ with CoreTestAsync
object AggrSetNum_BigDecimal extends AggrSetNum_BigDecimal_ with CoreTestAsync
object AggrSetNum_Byte extends AggrSetNum_Byte_ with CoreTestAsync
object AggrSetNum_Short extends AggrSetNum_Short_ with CoreTestAsync
object AggrSetNum_ref extends AggrSetNum_ref_ with CoreTestAsync
