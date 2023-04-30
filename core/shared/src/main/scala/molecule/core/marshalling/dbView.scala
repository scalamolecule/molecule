package molecule.core.marshalling

import java.util.Date
import molecule.core.action.Action

object dbView {

  sealed trait PointInTime
  case class TxDate(d: Date) extends PointInTime
  case class TxLong(t: Long) extends PointInTime

  sealed trait DbView
  case class AsOf(tx: PointInTime) extends DbView
  case class Since(tx: PointInTime) extends DbView
  case class With(actions: Seq[Action]) extends DbView
}
