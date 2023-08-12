package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _Aggregates extends BoilerplateGenBase("Aggregates", "/api") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api
       |
       |import molecule.boilerplate.api.Keywords._
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val pad = "   " * (arity - 1) + (if(arity < 10) "" else " ")
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns[${`_, _`}]] {
         |  protected def _aggrInt   (kw: Kw                ): Ns[${`A..U, `}Int   , Int   ] with SortAttrs_$arity[${`A..U, `}Int   , Int   , Ns]
         |  protected def _aggrDouble(kw: Kw                ): Ns[${`A..U, `}Double, Double] with SortAttrs_$arity[${`A..U, `}Double, Double, Ns]
         |  protected def _aggrDist  (kw: Kw                ): Ns[${`A..U, `}Set[$V], t     ]
         |  protected def _aggrSet   (kw: Kw, n: Option[Int]): Ns[${`A..U, `}Set[t], t     ]
         |  protected def _aggrTsort (kw: Kw                ): Ns[${`A..V`}     , t     ] with SortAttrs_$arity[${`A..V`}     , t     , Ns]
         |  protected def _aggrT     (kw: Kw                ): Ns[${`A..V`}     , t     ]
         |}
         |
         |trait $fileName_$arity[${`A..V`}, t, Ns[${`_, _`}]] extends ${fileName}Ops_$arity[${`A..V`}, t, Ns] {
         |  def apply(kw: count)        : Ns[${`A..U, `}Int   , Int   ] with SortAttrs_$arity[${`A..U, `}Int   , Int   , Ns] = _aggrInt(kw)
         |  def apply(kw: countDistinct): Ns[${`A..U, `}Int   , Int   ] with SortAttrs_$arity[${`A..U, `}Int   , Int   , Ns] = _aggrInt(kw)
         |  def apply(kw: min)          : Ns[${`A..V`}     , t     ] with SortAttrs_$arity[${`A..V`}     , t     , Ns] = _aggrTsort(kw)
         |  def apply(kw: max)          : Ns[${`A..V`}     , t     ] with SortAttrs_$arity[${`A..V`}     , t     , Ns] = _aggrTsort(kw)
         |  def apply(kw: sum)          : Ns[${`A..V`}     , t     ] with SortAttrs_$arity[${`A..V`}     , t     , Ns] = _aggrTsort(kw)
         |  def apply(kw: rand)         : Ns[${`A..V`}     , t     ] $pad                                     = _aggrT(kw)
         |  def apply(kw: sample)       : Ns[${`A..V`}     , t     ] $pad                                     = _aggrT(kw)
         |  def apply(kw: distinct)     : Ns[${`A..U, `}Set[$V], t     ] $pad                                     = _aggrDist(kw)
         |  def apply(kw: mins)         : Ns[${`A..U, `}Set[t], t     ] $pad                                     = _aggrSet(kw, Some(kw.n))
         |  def apply(kw: maxs)         : Ns[${`A..U, `}Set[t], t     ] $pad                                     = _aggrSet(kw, Some(kw.n))
         |  def apply(kw: rands)        : Ns[${`A..U, `}Set[t], t     ] $pad                                     = _aggrSet(kw, Some(kw.n))
         |  def apply(kw: samples)      : Ns[${`A..U, `}Set[t], t     ] $pad                                     = _aggrSet(kw, Some(kw.n))
         |  def apply(kw: median)       : Ns[${`A..U, `}Double, Double] with SortAttrs_$arity[${`A..U, `}Double, Double, Ns] = _aggrDouble(kw)
         |  def apply(kw: avg)          : Ns[${`A..U, `}Double, Double] with SortAttrs_$arity[${`A..U, `}Double, Double, Ns] = _aggrDouble(kw)
         |  def apply(kw: variance)     : Ns[${`A..U, `}Double, Double] with SortAttrs_$arity[${`A..U, `}Double, Double, Ns] = _aggrDouble(kw)
         |  def apply(kw: stddev)       : Ns[${`A..U, `}Double, Double] with SortAttrs_$arity[${`A..U, `}Double, Double, Ns] = _aggrDouble(kw)
         |}""".stripMargin
  }
}
