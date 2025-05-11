package molecule.db.core.util

import org.scalajs.macrotaskexecutor.MacrotaskExecutor
import scala.concurrent.ExecutionContext

object Executor {
  implicit def global: ExecutionContext = MacrotaskExecutor.Implicits.global
}
