package molecule.sql.jdbc.test.sort

import molecule.coreTests.test.sort._
import molecule.sql.jdbc.setup.CoreTestAsync

object SortAggr extends SortAggr with CoreTestAsync
object SortBasics extends SortBasics with CoreTestAsync
object SortComposites extends SortComposites with CoreTestAsync
object SortDynamic extends SortDynamic with CoreTestAsync
object SortExprOpt extends SortExprOpt with CoreTestAsync
object SortNested extends SortNested with CoreTestAsync
object SortTxData extends SortTxData with CoreTestAsync

