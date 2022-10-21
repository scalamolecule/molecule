package molecule.core.api

trait Insert {
  def _insertOp(tpls: Seq[Product]): InsertOps
}

object Insert  {

  trait Insert_1[A] extends Insert {
    def apply(a: A, as: A*): InsertOps = _insertOp((a +: as).map(a => Tuple1(a)))
    def apply(tpls: Seq[A]): InsertOps = _insertOp(tpls.map(a => Tuple1(a)))
  }

  trait Insert_2[A, B] extends Insert {
    def apply(a: A, b: B)                : InsertOps = _insertOp(Seq((a, b)))
    def apply(tpl: (A, B))               : InsertOps = _insertOp(Seq(tpl))
    def apply(tpl: (A, B), more: (A, B)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B)])         : InsertOps = _insertOp(tpls)
//    def apply(tpl: (A, B), more: (A, B)*): InsertOps = _insertOp(tpl +: more)
  }

  trait Insert_3[A, B, C] extends Insert {
    def apply(a: A, b: B, c: C)                : InsertOps = _insertOp(Seq((a, b)))
    def apply(tpl: (A, B, C))                  : InsertOps = _insertOp(Seq(tpl))
    def apply(tpl: (A, B, C), more: (A, B, C)*): InsertOps = _insertOp(tpl +: more)
    def apply(tpls: Seq[(A, B, C)])            : InsertOps = _insertOp(tpls)
  }
}
