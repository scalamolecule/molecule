package molecule.datalog.datomic.compliance.validation.save

import molecule.coreTests.spi.validation.save._
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_FormatConstants  extends FormatConstants with Test_datomic_async
object Test_FormatVariables extends FormatVariables with Test_datomic_async
object Semantics extends Semantics with Test_datomic_async
object TypesOne extends TypesOne with Test_datomic_async
object TypesOneOpt extends TypesOneOpt with Test_datomic_async
object TypesSeq extends TypesSeq with Test_datomic_async
object TypesSeqOpt extends TypesSeqOpt with Test_datomic_async
object TypesSet extends TypesSet with Test_datomic_async
object TypesSetOpt extends TypesSetOpt with Test_datomic_async
