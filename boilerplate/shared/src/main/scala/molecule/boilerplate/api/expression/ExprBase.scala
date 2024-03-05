package molecule.boilerplate.api.expression

import scala.reflect.ClassTag

trait ExprBase {
  type X = DummyImplicit
  type Y = DummyImplicit

  protected def abs2sets[t](pairs: Seq[(t, t)]): Seq[Set[t]] = {
    val (from, to) = pairs.unzip
    from.map(Set(_)) ++ to.map(Set(_))
  }

  protected def abs2arrays[t: ClassTag](pairs: Seq[(t, t)]): Seq[Array[t]] = {
    val (from, to) = pairs.unzip
    from.map(Array(_)) ++ to.map(Array(_))
  }

  protected def abs2maps[t](kvPairs: Seq[(String, t, t)]): Seq[Map[String, t]] = {
    val (key, from, to) = kvPairs.unzip3
    key.zip(from).map(Map(_)) ++ key.zip(to).map(Map(_))
  }
}
