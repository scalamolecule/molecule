package molecule.boilerplate.util

import scribe.format._
import scribe.{Level, Logging}

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

  val logLevel = sys.props.get("log") match {
    case Some("trace") => Level.Trace
    case Some("debug") => Level.Debug
    case Some("warn")  => Level.Warn
    case Some("error") => Level.Error
    case _             => Level.Warn
  }

  scribe.Logger.root
    .clearHandlers()
    .clearModifiers()
    //    .withHandler(formatter = logFormatter, minimumLevel = Some(Level.Trace))
    //    .withHandler(formatter = logFormatter, minimumLevel = Some(Level.Debug))
    .withHandler(formatter = logFormatter, minimumLevel = Some(Level.Warn))
    //    .withHandler(formatter = logFormatter, minimumLevel = Some(Level.Error))
    //    .withHandler(formatter = logFormatter, minimumLevel = Some(logLevel))
    .replace()
}
