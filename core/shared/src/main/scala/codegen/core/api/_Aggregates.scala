package codegen.core.api

import codegen.CoreGenBase

object _Aggregates extends CoreGenBase("Aggregates", "/api") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.api
       |
       |import molecule.core.api.Keywords.*
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val pad = "   " * (arity - 1) + (if(arity < 10) " " else "  ")
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Entity[${`_, _`}]] {
         |  protected def _aggrInt   (kw: Kw                ): Entity[${`A..U, `}Int   , Int   ] & SortAttrs_$arity[${`A..U, `}Int   , Int   , Entity]
         |  protected def _aggrT     (kw: Kw                ): Entity[${`A..U, `}t     , t     ] & SortAttrs_$arity[${`A..U, `}t     , t     , Entity]
         |  protected def _aggrDouble(kw: Kw                ): Entity[${`A..U, `}Double, Double] & SortAttrs_$arity[${`A..U, `}Double, Double, Entity]
         |  protected def _aggrSet   (kw: Kw, n: Option[Int]): Entity[${`A..U, `}Set[t], t     ]
         |  protected def _aggrDist  (kw: Kw                ): Entity[${`A..U, `}Set[$V], t     ]
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Entity[${`_, _`}]] extends ${fileName}Ops_$arity[${`A..V`}, t, Entity] {
         |  def apply(kw: count)        : Entity[${`A..U, `}Int   , Int   ] & SortAttrs_$arity[${`A..U, `}Int   , Int   , Entity] = _aggrInt(kw)
         |  def apply(kw: countDistinct): Entity[${`A..U, `}Int   , Int   ] & SortAttrs_$arity[${`A..U, `}Int   , Int   , Entity] = _aggrInt(kw)
         |  def apply(kw: sum)          : Entity[${`A..U, `}t     , t     ] & SortAttrs_$arity[${`A..U, `}t     , t     , Entity] = _aggrT(kw)
         |  def apply(kw: min)          : Entity[${`A..U, `}t     , t     ] & SortAttrs_$arity[${`A..U, `}t     , t     , Entity] = _aggrT(kw)
         |  def apply(kw: max)          : Entity[${`A..U, `}t     , t     ] & SortAttrs_$arity[${`A..U, `}t     , t     , Entity] = _aggrT(kw)
         |  def apply(kw: sample)       : Entity[${`A..U, `}t     , t     ] & SortAttrs_$arity[${`A..U, `}t     , t     , Entity] = _aggrT(kw)
         |  def apply(kw: median)       : Entity[${`A..U, `}Double, Double] & SortAttrs_$arity[${`A..U, `}Double, Double, Entity] = _aggrDouble(kw)
         |  def apply(kw: avg)          : Entity[${`A..U, `}Double, Double] & SortAttrs_$arity[${`A..U, `}Double, Double, Entity] = _aggrDouble(kw)
         |  def apply(kw: variance)     : Entity[${`A..U, `}Double, Double] & SortAttrs_$arity[${`A..U, `}Double, Double, Entity] = _aggrDouble(kw)
         |  def apply(kw: stddev)       : Entity[${`A..U, `}Double, Double] & SortAttrs_$arity[${`A..U, `}Double, Double, Entity] = _aggrDouble(kw)
         |  def apply(kw: mins)         : Entity[${`A..U, `}Set[t], t     ] $pad                                     = _aggrSet(kw, Some(kw.n))
         |  def apply(kw: maxs)         : Entity[${`A..U, `}Set[t], t     ] $pad                                     = _aggrSet(kw, Some(kw.n))
         |  def apply(kw: samples)      : Entity[${`A..U, `}Set[t], t     ] $pad                                     = _aggrSet(kw, Some(kw.n))
         |  def apply(kw: distinct)     : Entity[${`A..U, `}Set[$V], t     ] $pad                                     = _aggrDist(kw)
         |}""".stripMargin
  }
}
