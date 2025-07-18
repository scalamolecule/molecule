package molecule.db.common.util

import java.util.Date
import molecule.base.util.DateHandling

object fns extends DateHandling {

  def str2date(s: String): Date = super.str2date(s)
  def date2str(d: Date): String = super.date2str(d)

  def partEntity(v: String): Array[String] = v.split("_") match {
    case Array(ns)       => Array("db.part/user", ns)
    case Array(part, ns) => Array(part, ns)
  }

  def live(nsFull: String): Boolean = !nsFull.startsWith("-") && !nsFull.startsWith(":-")
}
