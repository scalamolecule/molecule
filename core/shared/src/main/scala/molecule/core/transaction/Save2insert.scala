package molecule.core.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.action.Save
import molecule.core.transaction.ops.SaveOps
import molecule.core.util.ModelUtils
import scala.annotation.tailrec

object Save2insert {

  def hydrate(save: Save): (List[Element], Product) = {
    val (insertElements, list, count) = es2list(save.elements, Nil, new Array[Any](22), 0)
    (insertElements, list2tpl(list, count))
  }

  @tailrec
  final def es2list(es1: List[Element], es2: List[Element], vs: Array[Any], i: Int): (List[Element], Array[Any], Int) = {
    es1 match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != Eq) {
            throw ModelError(s"Missing applied value for attribute ${a.ns}.${a.attr}")
          }
          a match {
            case a: AttrOne  =>
              a match {
                case a: AttrOneMan => a match {
                  case a: AttrOneManString     => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrOneManInt        => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrOneManLong       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrOneManFloat      => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrOneManDouble     => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrOneManBoolean    => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrOneManBigInt     => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrOneManBigDecimal => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrOneManDate       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrOneManUUID       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrOneManURI        => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrOneManByte       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrOneManShort      => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrOneManChar       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                }
                case a: AttrOneOpt => a match {
                  case a: AttrOneOptString     => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrOneOptInt        => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrOneOptLong       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrOneOptFloat      => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrOneOptDouble     => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrOneOptBoolean    => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrOneOptBigInt     => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrOneOptBigDecimal => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrOneOptDate       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrOneOptUUID       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrOneOptURI        => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrOneOptByte       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrOneOptShort      => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrOneOptChar       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                }
                case a: AttrOneTac => throw new Exception("Can't save tacit attribute value. Found: " + a)
              }
            case at: AttrSet =>
              at match {
                case a: AttrSetMan => a match {
                  case a: AttrSetManString     => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrSetManInt        => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrSetManLong       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrSetManFloat      => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrSetManDouble     => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrSetManBoolean    => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrSetManBigInt     => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrSetManBigDecimal => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrSetManDate       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrSetManUUID       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrSetManURI        => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrSetManByte       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrSetManShort      => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                  case a: AttrSetManChar       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = Nil), vs, i + 1)
                }
                case a: AttrSetOpt => a match {
                  case a: AttrSetOptString     => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrSetOptInt        => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrSetOptLong       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrSetOptFloat      => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrSetOptDouble     => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrSetOptBoolean    => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrSetOptBigInt     => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrSetOptBigDecimal => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrSetOptDate       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrSetOptUUID       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrSetOptURI        => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrSetOptByte       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrSetOptShort      => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                  case a: AttrSetOptChar       => vs(i) = a.vs.head; es2list(tail, es2 :+ a.copy(op = V, vs = None), vs, i + 1)
                }
                case a: AttrSetTac => throw new Exception("Can't save tacit attribute value. Found: " + a)
              }
            case a           => throw new Exception("Attribute family not implemented for " + a)
          }

        case Ref(ns, refAttr, refNs, _, _) => es2list(tail, es2, vs, i)
        case BackRef(backRefNs, _)         => es2list(tail, es2, vs, i)
        case _: Nested                     => throw ModelError(
          "Nested data structure not allowed in save molecule. Please use insert instead."
        )
        case _: NestedOpt                  => throw ModelError(
          "Optional nested data structure not allowed in save molecule. Please use insert instead."
        )
        case Composite(compositeElements)  =>
          //          val (insertElements, list, count) = es2list(compositeElements, Nil, new Array[Any](22), 0)
          //          resolve(compositeElements ++ tail)
          //          es2list(tail, es2, vs, i)
          ???

        case TxData(txElements) =>
          //          handleTxData()
          //          resolve(txElements) // tail is empty (no more attributes possible after Tx)
          ???
      }
      case Nil             => (es2, vs, i)
    }
  }

  private def list2tpl(x: Array[Any], count: Int): Product = {
    count match {
      case 1  => Tuple1(x.head)
      case 2  => (x.head, x(1))
      case 3  => (x.head, x(1), x(2))
      case 4  => (x.head, x(1), x(2), x(3))
      case 5  => (x.head, x(1), x(2), x(3), x(4))
      case 6  => (x.head, x(1), x(2), x(3), x(4), x(5))
      case 7  => (x.head, x(1), x(2), x(3), x(4), x(5), x(6))
      case 8  => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7))
      case 9  => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8))
      case 10 => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9))
      case 11 => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10))
      case 12 => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10), x(11))
      case 13 => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10), x(11), x(12))
      case 14 => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10), x(11), x(12), x(13))
      case 15 => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10), x(11), x(12), x(13), x(14))
      case 16 => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10), x(11), x(12), x(13), x(14), x(15))
      case 17 => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10), x(11), x(12), x(13), x(14), x(15), x(16))
      case 18 => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10), x(11), x(12), x(13), x(14), x(15), x(16), x(17))
      case 19 => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10), x(11), x(12), x(13), x(14), x(15), x(16), x(17), x(18))
      case 20 => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10), x(11), x(12), x(13), x(14), x(15), x(16), x(17), x(18), x(19))
      case 21 => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10), x(11), x(12), x(13), x(14), x(15), x(16), x(17), x(18), x(19), x(20))
      case 22 => (x.head, x(1), x(2), x(3), x(4), x(5), x(6), x(7), x(8), x(9), x(10), x(11), x(12), x(13), x(14), x(15), x(16), x(17), x(18), x(19), x(20), x(21))
    }

    //    list match {
    //      case List(a)                                                                => Tuple1(a)
    //      case List(a, b)                                                             => (a, b)
    //      case List(a, b, c)                                                          => (a, b, c)
    //      case List(a, b, c, d)                                                       => (a, b, c, d)
    //      case List(a, b, c, d, e)                                                    => (a, b, c, d, e)
    //      case List(a, b, c, d, e, f)                                                 => (a, b, c, d, e, f)
    //      case List(a, b, c, d, e, f, g)                                              => (a, b, c, d, e, f, g)
    //      case List(a, b, c, d, e, f, g, h)                                           => (a, b, c, d, e, f, g, h)
    //      case List(a, b, c, d, e, f, g, h, i)                                        => (a, b, c, d, e, f, g, h, i)
    //      case List(a, b, c, d, e, f, g, h, i, j)                                     => (a, b, c, d, e, f, g, h, i, j)
    //      case List(a, b, c, d, e, f, g, h, i, j, k)                                  => (a, b, c, d, e, f, g, h, i, j, k)
    //      case List(a, b, c, d, e, f, g, h, i, j, k, l)                               => (a, b, c, d, e, f, g, h, i, j, k, l)
    //      case List(a, b, c, d, e, f, g, h, i, j, k, l, m)                            => (a, b, c, d, e, f, g, h, i, j, k, l, m)
    //      case List(a, b, c, d, e, f, g, h, i, j, k, l, m, n)                         => (a, b, c, d, e, f, g, h, i, j, k, l, m, n)
    //      case List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)                      => (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)
    //      case List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)                   => (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)
    //      case List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)                => (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)
    //      case List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)             => (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r)
    //      case List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s)          => (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s)
    //      case List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)       => (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t)
    //      case List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u)    => (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u)
    //      case List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v) => (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)
    //    }
  }
}