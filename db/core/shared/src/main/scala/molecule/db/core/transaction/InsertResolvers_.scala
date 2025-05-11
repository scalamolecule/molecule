// GENERATED CODE ********************************
package molecule.db.core.transaction

import molecule.db.core.ast.Element

trait InsertResolvers_ {

  protected def resolve(
    elements: List[Element],
    resolvers: List[Product => Unit],
    tplIndex: Int,
    prevRefs: List[String]
  ): List[Product => Unit]

  def getResolver(elements: List[Element]): Product => Unit = {
    val resolvers: List[Product => Unit] = resolve(elements, Nil, 0, Nil)
    resolvers.length match {
      case 1  => resolve1(resolvers)
      case 2  => resolve2(resolvers)
      case 3  => resolve3(resolvers)
      case 4  => resolve4(resolvers)
      case 5  => resolve5(resolvers)
      case 6  => resolve6(resolvers)
      case 7  => resolve7(resolvers)
      case 8  => resolve8(resolvers)
      case 9  => resolve9(resolvers)
      case 10 => resolve10(resolvers)
      case 11 => resolve11(resolvers)
      case 12 => resolve12(resolvers)
      case 13 => resolve13(resolvers)
      case 14 => resolve14(resolvers)
      case 15 => resolve15(resolvers)
      case 16 => resolve16(resolvers)
      case 17 => resolve17(resolvers)
      case 18 => resolve18(resolvers)
      case 19 => resolve19(resolvers)
      case 20 => resolve20(resolvers)
      case 21 => resolve21(resolvers)
      case 22 => resolve22(resolvers)
    }
  }

  final private def resolve1(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1) = resolvers
    (tpl: Product) => {
      r1(tpl)
    }
  }

  final private def resolve2(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
    }
  }

  final private def resolve3(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
    }
  }

  final private def resolve4(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
    }
  }

  final private def resolve5(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
    }
  }

  final private def resolve6(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
    }
  }

  final private def resolve7(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
    }
  }

  final private def resolve8(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
    }
  }

  final private def resolve9(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8, r9) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
      r9(tpl)
    }
  }

  final private def resolve10(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
      r9(tpl)
      r10(tpl)
    }
  }

  final private def resolve11(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
      r9(tpl)
      r10(tpl)
      r11(tpl)
    }
  }

  final private def resolve12(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
      r9(tpl)
      r10(tpl)
      r11(tpl)
      r12(tpl)
    }
  }

  final private def resolve13(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
      r9(tpl)
      r10(tpl)
      r11(tpl)
      r12(tpl)
      r13(tpl)
    }
  }

  final private def resolve14(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
      r9(tpl)
      r10(tpl)
      r11(tpl)
      r12(tpl)
      r13(tpl)
      r14(tpl)
    }
  }

  final private def resolve15(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
      r9(tpl)
      r10(tpl)
      r11(tpl)
      r12(tpl)
      r13(tpl)
      r14(tpl)
      r15(tpl)
    }
  }

  final private def resolve16(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
      r9(tpl)
      r10(tpl)
      r11(tpl)
      r12(tpl)
      r13(tpl)
      r14(tpl)
      r15(tpl)
      r16(tpl)
    }
  }

  final private def resolve17(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
      r9(tpl)
      r10(tpl)
      r11(tpl)
      r12(tpl)
      r13(tpl)
      r14(tpl)
      r15(tpl)
      r16(tpl)
      r17(tpl)
    }
  }

  final private def resolve18(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
      r9(tpl)
      r10(tpl)
      r11(tpl)
      r12(tpl)
      r13(tpl)
      r14(tpl)
      r15(tpl)
      r16(tpl)
      r17(tpl)
      r18(tpl)
    }
  }

  final private def resolve19(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
      r9(tpl)
      r10(tpl)
      r11(tpl)
      r12(tpl)
      r13(tpl)
      r14(tpl)
      r15(tpl)
      r16(tpl)
      r17(tpl)
      r18(tpl)
      r19(tpl)
    }
  }

  final private def resolve20(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
      r9(tpl)
      r10(tpl)
      r11(tpl)
      r12(tpl)
      r13(tpl)
      r14(tpl)
      r15(tpl)
      r16(tpl)
      r17(tpl)
      r18(tpl)
      r19(tpl)
      r20(tpl)
    }
  }

  final private def resolve21(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
      r9(tpl)
      r10(tpl)
      r11(tpl)
      r12(tpl)
      r13(tpl)
      r14(tpl)
      r15(tpl)
      r16(tpl)
      r17(tpl)
      r18(tpl)
      r19(tpl)
      r20(tpl)
      r21(tpl)
    }
  }

  final private def resolve22(
    resolvers: List[Product => Unit]
  ): Product => Unit = {
    val List(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
      r4(tpl)
      r5(tpl)
      r6(tpl)
      r7(tpl)
      r8(tpl)
      r9(tpl)
      r10(tpl)
      r11(tpl)
      r12(tpl)
      r13(tpl)
      r14(tpl)
      r15(tpl)
      r16(tpl)
      r17(tpl)
      r18(tpl)
      r19(tpl)
      r20(tpl)
      r21(tpl)
      r22(tpl)
    }
  }
}