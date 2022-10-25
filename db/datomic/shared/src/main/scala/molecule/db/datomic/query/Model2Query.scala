package molecule.db.datomic.query

import java.util
import java.util.{List => jList}
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.util.fns
import scala.annotation.tailrec
import scala.collection.mutable.{ArrayBuffer, ListBuffer}


class Model2Query[Tpl](elements: Seq[Element]) {

  // Query optimization weights
  final protected val wGround         = 1
  final protected val wEqOne          = 2
  final protected val wEqMany         = 3
  final protected val wRange          = 4
  final protected val wNeqOne         = 5
  final protected val wFulltextSearch = 6
  final protected val wClauseOne      = 7
  final protected val wClauseMany     = 8
  final protected val wClause         = 9

  final protected val sortIds   = new ArrayBuffer[String]
  final protected val find      = new ArrayBuffer[String]
  final protected val widh      = new ArrayBuffer[String]
  final protected val in        = new ArrayBuffer[String]
  final protected val where     = new ArrayBuffer[(String, Int)]
  final protected val rules     = new ArrayBuffer[String]
  final protected val ins       = new ArrayBuffer[AnyRef]
  final protected val resolvers = new ArrayBuffer[AnyRef => AnyRef]

  final lazy protected val query   : String      = renderQuery(true)
  final lazy protected val queryRaw: String      = renderQuery(false)
  final lazy protected val inputs  : Seq[AnyRef] = renderRules ++ ins

  final lazy protected val row2tpl: jList[AnyRef] => Tpl = {
    resolvers.length match {
      case 2 =>
        val r0 = resolvers(0)
        val r1 = resolvers.apply(1)
        (row: jList[AnyRef]) => {
          (
            r0(row.get(0)),
            r1(row.get(1))
            ).asInstanceOf[Tpl]
        }

    }
  }

  final protected def renderQuery(optimized: Boolean): String = {
    resolve(List(vv), elements)
    val find1       = (sortIds ++ find).map(v => if (v.startsWith("?")) v else s"\n        $v").mkString(" ").trim
    val widh1       = if (widh.isEmpty) "" else widh.distinct.mkString("\n :with  ", " ", "")
    val hasRules    = rules.nonEmpty
    val r           = if (hasRules) "% " else ""
    val in1         = if (!hasRules && in.isEmpty) "" else in.mkString("\n :in    $ " + r, " ", "")
    val where1      = where.distinct
    val clausePairs = if (optimized) where1.sortBy(_._2) else where1
    def query(clauses: ArrayBuffer[(String, Int)]) = {
      val where1 = clauses.map(_._1).mkString("\n        ")
      s"""[:find  $find1 $widh1 $in1
         | :where $where1]""".stripMargin
    }
    val q = query(clausePairs)
        println("Query: ---------------------------------------\n" + q)
    q
  }

  final protected def renderRules: Seq[String] = {
    if (rules.isEmpty) Nil else {
      val res = Seq(rules.mkString("[\n  ", "\n  ", "\n]"))
      //      println("Rules:\n" + res.head)
      res
    }
  }

  // Datomic entity id variable
  type Var = String

  private val vars              = Array("?a", "?b", "?c", "?d", "?e", "?f", "?g", "?h", "?i", "?j", "?k", "?l", "?m", "?n", "?o", "?p", "?q", "?r", "?s", "?t", "?u", "?v", "?w", "?x", "?y", "?z", "?aa", "?ab", "?ac", "?ad", "?ae", "?af", "?ag", "?ah", "?ai", "?aj", "?ak", "?al", "?am", "?an", "?ao", "?ap", "?aq", "?ar", "?as", "?at", "?au", "?av", "?aw", "?ax", "?ay", "?az", "?ba", "?bb", "?bc", "?bd", "?be", "?bf", "?bg", "?bh", "?bi", "?bj", "?bk", "?bl", "?bm", "?bn", "?bo", "?bp", "?bq", "?br", "?bs", "?bt", "?bu", "?bv", "?bw", "?bx", "?by", "?bz", "?ca", "?cb", "?cc", "?cd", "?ce", "?cf", "?cg", "?ch", "?ci", "?cj", "?ck", "?cl", "?cm", "?cn", "?co", "?cp", "?cq", "?cr", "?cs", "?ct", "?cu", "?cv", "?cw", "?cx", "?cy", "?cz", "?da", "?db", "?dc", "?dd", "?de", "?df", "?dg", "?dh", "?di", "?dj", "?dk", "?dl", "?dm", "?dn", "?do", "?dp", "?dq", "?dr", "?ds", "?dt", "?du", "?dv", "?dw", "?dx", "?dy", "?dz", "?ea", "?eb", "?ec", "?ed", "?ee", "?ef", "?eg", "?eh", "?ei", "?ej", "?ek", "?el", "?em", "?en", "?eo", "?ep", "?eq", "?er", "?es", "?et", "?eu", "?ev", "?ew", "?ex", "?ey", "?ez", "?fa", "?fb", "?fc", "?fd", "?fe", "?ff", "?fg", "?fh", "?fi", "?fj", "?fk", "?fl", "?fm", "?fn", "?fo", "?fp", "?fq", "?fr", "?fs", "?ft", "?fu", "?fv", "?fw", "?fx", "?fy", "?fz", "?ga", "?gb", "?gc", "?gd", "?ge", "?gf", "?gg", "?gh", "?gi", "?gj", "?gk", "?gl", "?gm", "?gn", "?go", "?gp", "?gq", "?gr", "?gs", "?gt", "?gu", "?gv", "?gw", "?gx", "?gy", "?gz", "?ha", "?hb", "?hc", "?hd", "?he", "?hf", "?hg", "?hh", "?hi", "?hj", "?hk", "?hl", "?hm", "?hn", "?ho", "?hp", "?hq", "?hr", "?hs", "?ht", "?hu", "?hv", "?hw", "?hx", "?hy", "?hz", "?ia", "?ib", "?ic", "?id", "?ie", "?if", "?ig", "?ih", "?ii", "?ij", "?ik", "?il", "?im", "?in", "?io", "?ip", "?iq", "?ir", "?is", "?it", "?iu", "?iv", "?iw", "?ix", "?iy", "?iz", "?ja", "?jb", "?jc", "?jd", "?je", "?jf", "?jg", "?jh", "?ji", "?jj", "?jk", "?jl", "?jm", "?jn", "?jo", "?jp", "?jq", "?jr", "?js", "?jt", "?ju", "?jv", "?jw", "?jx", "?jy", "?jz", "?ka", "?kb", "?kc", "?kd", "?ke", "?kf", "?kg", "?kh", "?ki", "?kj", "?kk", "?kl", "?km", "?kn", "?ko", "?kp", "?kq", "?kr", "?ks", "?kt", "?ku", "?kv", "?kw", "?kx", "?ky", "?kz", "?la", "?lb", "?lc", "?ld", "?le", "?lf", "?lg", "?lh", "?li", "?lj", "?lk", "?ll", "?lm", "?ln", "?lo", "?lp", "?lq", "?lr", "?ls", "?lt", "?lu", "?lv", "?lw", "?lx", "?ly", "?lz", "?ma", "?mb", "?mc", "?md", "?me", "?mf", "?mg", "?mh", "?mi", "?mj", "?mk", "?ml", "?mm", "?mn", "?mo", "?mp", "?mq", "?mr", "?ms", "?mt", "?mu", "?mv", "?mw", "?mx", "?my", "?mz", "?na", "?nb", "?nc", "?nd", "?ne", "?nf", "?ng", "?nh", "?ni", "?nj", "?nk", "?nl", "?nm", "?nn", "?no", "?np", "?nq", "?nr", "?ns", "?nt", "?nu", "?nv", "?nw", "?nx", "?ny", "?nz", "?oa", "?ob", "?oc", "?od", "?oe", "?of", "?og", "?oh", "?oi", "?oj", "?ok", "?ol", "?om", "?on", "?oo", "?op", "?oq", "?or", "?os", "?ot", "?ou", "?ov", "?ow", "?ox", "?oy", "?oz", "?pa", "?pb", "?pc", "?pd", "?pe", "?pf", "?pg", "?ph", "?pi", "?pj", "?pk", "?pl", "?pm", "?pn", "?po", "?pp", "?pq", "?pr", "?ps", "?pt", "?pu", "?pv", "?pw", "?px", "?py", "?pz", "?qa", "?qb", "?qc", "?qd", "?qe", "?qf", "?qg", "?qh", "?qi", "?qj", "?qk", "?ql", "?qm", "?qn", "?qo", "?qp", "?qq", "?qr", "?qs", "?qt", "?qu", "?qv", "?qw", "?qx", "?qy", "?qz", "?ra", "?rb", "?rc", "?rd", "?re", "?rf", "?rg", "?rh", "?ri", "?rj", "?rk", "?rl", "?rm", "?rn", "?ro", "?rp", "?rq", "?rr", "?rs", "?rt", "?ru", "?rv", "?rw", "?rx", "?ry", "?rz", "?sa", "?sb", "?sc", "?sd", "?se", "?sf", "?sg", "?sh", "?si", "?sj", "?sk", "?sl", "?sm", "?sn", "?so", "?sp", "?sq", "?sr", "?ss", "?st", "?su", "?sv", "?sw", "?sx", "?sy", "?sz", "?ta", "?tb", "?tc", "?td", "?te", "?tf", "?tg", "?th", "?ti", "?tj", "?tk", "?tl", "?tm", "?tn", "?to", "?tp", "?tq", "?tr", "?ts", "?tt", "?tu", "?tv", "?tw", "?tx", "?ty", "?tz", "?ua", "?ub", "?uc", "?ud", "?ue", "?uf", "?ug", "?uh", "?ui", "?uj", "?uk", "?ul", "?um", "?un", "?uo", "?up", "?uq", "?ur", "?us", "?ut", "?uu", "?uv", "?uw", "?ux", "?uy", "?uz", "?va", "?vb", "?vc", "?vd", "?ve", "?vf", "?vg", "?vh", "?vi", "?vj", "?vk", "?vl", "?vm", "?vn", "?vo", "?vp", "?vq", "?vr", "?vs", "?vt", "?vu", "?vv", "?vw", "?vx", "?vy", "?vz", "?wa", "?wb", "?wc", "?wd", "?we", "?wf", "?wg", "?wh", "?wi", "?wj", "?wk", "?wl", "?wm", "?wn", "?wo", "?wp", "?wq", "?wr", "?ws", "?wt", "?wu", "?wv", "?ww", "?wx", "?wy", "?wz", "?xa", "?xb", "?xc", "?xd", "?xe", "?xf", "?xg", "?xh", "?xi", "?xj", "?xk", "?xl", "?xm", "?xn", "?xo", "?xp", "?xq", "?xr", "?xs", "?xt", "?xu", "?xv", "?xw", "?xx", "?xy", "?xz", "?ya", "?yb", "?yc", "?yd", "?ye", "?yf", "?yg", "?yh", "?yi", "?yj", "?yk", "?yl", "?ym", "?yn", "?yo", "?yp", "?yq", "?yr", "?ys", "?yt", "?yu", "?yv", "?yw", "?yx", "?yy", "?yz", "?za", "?zb", "?zc", "?zd", "?ze", "?zf", "?zg", "?zh", "?zi", "?zj", "?zk", "?zl", "?zm", "?zn", "?zo", "?zp", "?zq", "?zr", "?zs", "?zt", "?zu", "?zv", "?zw", "?zx", "?zy", "?zz")
  private var varIndex: Int     = -1
  private var addTxVar: Boolean = false

  def vv: String = {
    varIndex += 1
    vars(varIndex)
  }

  def tx: String = {
    if (addTxVar) {
      addTxVar = false
      " ?tx"
    } else ""
  }

//  def resolveQuery(model: Model): List[Var] = {
//    resolve(List(vv), model.elements)
//  }


  @tailrec
  final protected def resolve(es: List[Var], elements: Seq[Element]): List[Var] = elements match {
    case element :: tail => element match {
      case a: AtomOneMan => resolve(atomMan(es, a), tail)
      case a: AtomOneOpt => resolve(atomOpt(es, a), tail)

      case other =>
        throw MoleculeException("StmtsBuilder.res: Unexpected element: " + other)
    }
    case Nil             => es
  }

  private def atomMan(es: List[Var], atom: AtomOneMan): List[Var] = {
    val (e, a) = (es.last, s":${atom.ns}/${atom.attr}")
    atom match {
      case AtomOneManString(_, _, op, vs, _, _, sort) => atomMan(es, e, a, op, vs)
      case AtomOneManInt(_, _, op, vs, _, _, sort)    => atomMan(es, e, a, op, vs)
      case other                                      => throw MoleculeException("StmtsBuilder.res: Unexpected element: " + other)
    }
  }

  final protected def atomMan[T](es: List[Var], e: Var, a: String, op: Op, vs: Seq[T]): List[Var] = {
    op match {
      case V =>
        val v = vv
        find += v
        where += s"[$e $a $v$tx]" -> wClause
        resolvers += identity

      case other => throw MoleculeException("StmtsBuilder.res: Unexpected element: " + other)
    }
    es
  }
  final protected def atomManString(es: List[Var], e: Var, a: String, op: Op, vs: Seq[String]): List[Var] = {
    op match {
      case V =>
        val v = vv
        find += v
        where += s"[$e $a $v$tx]" -> wClause
        resolvers += ((v: AnyRef) => v)

      case other => throw MoleculeException("StmtsBuilder.res: Unexpected element: " + other)
    }
    es
  }

  final protected def atomManInt(es: List[Var], e: Var, a: String, op: Op, vs: Seq[Int]): List[Var] = {
    op match {
      case V =>
        val v = vv
        find += v
        where += s"[$e $a $v$tx]" -> wClause
        resolvers += ((v: AnyRef) => v)

      case other => throw MoleculeException("StmtsBuilder.res: Unexpected element: " + other)
    }
    es
  }


  private def atomOpt(es: List[Var], a: AtomOneOpt): List[Var] = a match {
    case _: AtomOneOptString => es
    case _: AtomOneOptInt    => es
    case other               => throw MoleculeException("StmtsBuilder.res: Unexpected element: " + other)
  }

}