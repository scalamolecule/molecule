package molecule.boilerplate.generators.expression

import molecule.boilerplate.generators._Template

object GenExprMapTac extends _Template("exprMapT", "expression") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.markers.argKindMarkers._
       |
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |trait ${Name}_$arity[Attr, t${`, A..N`}, $nsIn] {
         |  def apply  ()                                                                   : $nsOut          = ???
         |  def apply  (pair : (String, t), pairs: (String, t)*)                            : $nsOut with Vs  = ???
         |  def apply  (pairs: Iterable[(String, t)])                                       : $nsOut with CVs = ???
         |  def apply  (map  : Map[String, t], maps: Map[String, t]*)                       : $nsOut with Cs  = ???
         |  def apply  (maps : Map[String, t])                                              : $nsOut with CCs = ???
         |  def not    (pair : (String, t), pairs: (String, t)*)                            : $nsOut with Vs  = ???
         |  def not    (pairs: Iterable[(String, t)])                                       : $nsOut with CVs = ???
         |  def not    (map  : Map[String, t], maps: Map[String, t]*)                       : $nsOut with Cs  = ???
         |  def not    (maps : Seq[Map[String, t]])                                         : $nsOut with CCs = ???
         |  def ==     (map  : Map[String, t])                                              : $nsOut with Cs  = ???
         |  def ==     (map  : Map[String, t], maps: Map[String, t]*)                       : $nsOut with Cs  = ???
         |  def ==     (maps : Seq[Map[String, t]])                                         : $nsOut with CCs = ???
         |  def !=     (map  : Map[String, t])                                              : $nsOut with Cs  = ???
         |  def !=     (map  : Map[String, t], maps: Map[String, t]*)                       : $nsOut with Cs  = ???
         |  def !=     (maps : Seq[Map[String, t]])                                         : $nsOut with CCs = ???
         |  def <      (upper: t)                                                           : $nsOut          = ???
         |  def <=     (upper: t)                                                           : $nsOut          = ???
         |  def >      (lower: t)                                                           : $nsOut          = ???
         |  def >=     (lower: t)                                                           : $nsOut          = ???
         |  def assert (pair : (String, t), pairs: (String, t)*)                            : $nsOut          = ???
         |  def assert (pairs: Seq[(String, t)])                                            : $nsOut          = ???
         |  def replace(ab   : ((String, t), (String, t)), abs: ((String, t), (String, t))*): $nsOut          = ???
         |  def replace(abs  : Seq[((String, t), (String, t))])                             : $nsOut          = ???
         |  def retract(map  : (String, t), maps: (String, t)*)                             : $nsOut          = ???
         |  def retract(maps : Seq[Map[String, t]])                                         : $nsOut          = ???
         |  def k      (key  : String, keys: String*)                                       : $nsOut with Vs  = ???
         |  def k      (key  : Seq[String])                                                 : $nsOut with CVs = ???
         |}""".stripMargin
  }
}
