package molecule.boilerplate.api.expression

trait ExprBase {
  type X = DummyImplicit

  def mapK[t](keys: Seq[String]): Map[String, t] = {
    keys.map(k => k -> null.asInstanceOf[t]).toMap
  }
  def mapV[t](vs: Seq[t]): Map[String, t] = {
    vs.zipWithIndex.map { case (v, i) => s"_k$i" -> v }.toMap
  }
}
