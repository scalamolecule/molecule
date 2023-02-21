package molecule.core.util

import scala.concurrent.ExecutionContext

object Executor {
  implicit def global: ExecutionContext = ExecutionContext.global
}
