package molecule.datalog.datomic.test.aggr

import molecule.coreTests.test.aggr.set.any._
import molecule.datalog.datomic.setup.CoreTestAsync

object AggrSet_String extends AggrSet_String_ with CoreTestAsync
object AggrSet_Int extends AggrSet_Int with CoreTestAsync
object AggrSet_Long extends AggrSet_Long_ with CoreTestAsync
object AggrSet_Float extends AggrSet_Float_ with CoreTestAsync
object AggrSet_Double extends AggrSet_Double_ with CoreTestAsync
object AggrSet_Boolean extends AggrSet_Boolean with CoreTestAsync
object AggrSet_BigInt extends AggrSet_BigInt_ with CoreTestAsync
object AggrSet_BigDecimal extends AggrSet_BigDecimal_ with CoreTestAsync
object AggrSet_Date extends AggrSet_Date_ with CoreTestAsync
object AggrSet_UUID extends AggrSet_UUID_ with CoreTestAsync
object AggrSet_URI extends AggrSet_URI_ with CoreTestAsync
object AggrSet_Byte extends AggrSet_Byte_ with CoreTestAsync
object AggrSet_Short extends AggrSet_Short_ with CoreTestAsync
object AggrSet_Char extends AggrSet_Char_ with CoreTestAsync
object AggrSet_ref extends AggrSet_ref with CoreTestAsync
