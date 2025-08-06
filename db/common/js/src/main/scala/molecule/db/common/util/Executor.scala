package molecule.db.common.util

import scala.concurrent.ExecutionContext
import org.scalajs.macrotaskexecutor.MacrotaskExecutor

object Executor {
  implicit def global: ExecutionContext = MacrotaskExecutor.Implicits.global
}
