package molecule.db.common.util

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

object Executor {

  // Enable stack traces back to calling code causing asynchronous exceptions.
  // Without it we get meaningless stack traces pointing only to the exception.
  // https://medium.com/wix-engineering/writing-async-app-in-scala-part-3-threading-model-ef9e9033bd33
  implicit val global: ExecutionContextExecutor = ExecutionContext.fromExecutor(
    (runnable: Runnable) => runnable.run()
  )

  // This requires tedious imports:
  //  import scala.concurrent.{ExecutionContextExecutor, Future}
  //  import molecule.db.common.util.Executor.given ExecutionContextExecutor
  //
  //  given ExecutionContextExecutor = ExecutionContext.fromExecutor(
  //    (runnable: Runnable) => runnable.run()
  //  )
}
