package molecule.boilerplate.util

import scribe._
import scribe.format._

trait MoleculeLogging extends Logging {

  // See scribe.format.Formatter
  val logFormatter = Formatter.fromBlocks(
    groupBySecond(
      cyan(bold(dateFull)),
      string(" ["),
      bold(levelColoredPaddedRight),
      string("]"),
      space,
      green(position),
      newLine,
    ),
    multiLine(messages),
    mdc,
  )

  scribe.Logger.root
    .clearHandlers()
    .clearModifiers()
    .withHandler(formatter = logFormatter, minimumLevel = Some(Level.Trace))
    .replace()
}
