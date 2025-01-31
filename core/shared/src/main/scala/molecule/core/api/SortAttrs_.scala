// GENERATED CODE ********************************
package molecule.core.api

import molecule.base.error.ExecutionError


trait SortAttrsOps_1[A, t, Ns[_, _]] {
  protected def _sort(sort: String): Ns[A, t]
  protected def _dynsort(i: Int): Ns[A, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_1[A, t, Ns[_, _]] extends SortAttrsOps_1[A, t, Ns] {
  def a1: Ns[A, t] = _sort("a1")
  def a2: Ns[A, t] = _sort("a2")
  def a3: Ns[A, t] = _sort("a3")
  def a4: Ns[A, t] = _sort("a4")
  def a5: Ns[A, t] = _sort("a5")
  def d1: Ns[A, t] = _sort("d1")
  def d2: Ns[A, t] = _sort("d2")
  def d3: Ns[A, t] = _sort("d3")
  def d4: Ns[A, t] = _sort("d4")
  def d5: Ns[A, t] = _sort("d5")
  def sort(i: Int): Ns[A, t] = _dynsort(i)
}

trait SortAttrsOps_2[A, B, t, Ns[_, _, _]] {
  protected def _sort(sort: String): Ns[A, B, t]
  protected def _dynsort(i: Int): Ns[A, B, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_2[A, B, t, Ns[_, _, _]] extends SortAttrsOps_2[A, B, t, Ns] {
  def a1: Ns[A, B, t] = _sort("a1")
  def a2: Ns[A, B, t] = _sort("a2")
  def a3: Ns[A, B, t] = _sort("a3")
  def a4: Ns[A, B, t] = _sort("a4")
  def a5: Ns[A, B, t] = _sort("a5")
  def d1: Ns[A, B, t] = _sort("d1")
  def d2: Ns[A, B, t] = _sort("d2")
  def d3: Ns[A, B, t] = _sort("d3")
  def d4: Ns[A, B, t] = _sort("d4")
  def d5: Ns[A, B, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, t] = _dynsort(i)
}

trait SortAttrsOps_3[A, B, C, t, Ns[_, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, t]
  protected def _dynsort(i: Int): Ns[A, B, C, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_3[A, B, C, t, Ns[_, _, _, _]] extends SortAttrsOps_3[A, B, C, t, Ns] {
  def a1: Ns[A, B, C, t] = _sort("a1")
  def a2: Ns[A, B, C, t] = _sort("a2")
  def a3: Ns[A, B, C, t] = _sort("a3")
  def a4: Ns[A, B, C, t] = _sort("a4")
  def a5: Ns[A, B, C, t] = _sort("a5")
  def d1: Ns[A, B, C, t] = _sort("d1")
  def d2: Ns[A, B, C, t] = _sort("d2")
  def d3: Ns[A, B, C, t] = _sort("d3")
  def d4: Ns[A, B, C, t] = _sort("d4")
  def d5: Ns[A, B, C, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, t] = _dynsort(i)
}

trait SortAttrsOps_4[A, B, C, D, t, Ns[_, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_4[A, B, C, D, t, Ns[_, _, _, _, _]] extends SortAttrsOps_4[A, B, C, D, t, Ns] {
  def a1: Ns[A, B, C, D, t] = _sort("a1")
  def a2: Ns[A, B, C, D, t] = _sort("a2")
  def a3: Ns[A, B, C, D, t] = _sort("a3")
  def a4: Ns[A, B, C, D, t] = _sort("a4")
  def a5: Ns[A, B, C, D, t] = _sort("a5")
  def d1: Ns[A, B, C, D, t] = _sort("d1")
  def d2: Ns[A, B, C, D, t] = _sort("d2")
  def d3: Ns[A, B, C, D, t] = _sort("d3")
  def d4: Ns[A, B, C, D, t] = _sort("d4")
  def d5: Ns[A, B, C, D, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, t] = _dynsort(i)
}

trait SortAttrsOps_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_5[A, B, C, D, E, t, Ns[_, _, _, _, _, _]] extends SortAttrsOps_5[A, B, C, D, E, t, Ns] {
  def a1: Ns[A, B, C, D, E, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, t] = _dynsort(i)
}

trait SortAttrsOps_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_6[A, B, C, D, E, F, t, Ns[_, _, _, _, _, _, _]] extends SortAttrsOps_6[A, B, C, D, E, F, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, t] = _dynsort(i)
}

trait SortAttrsOps_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_7[A, B, C, D, E, F, G, t, Ns[_, _, _, _, _, _, _, _]] extends SortAttrsOps_7[A, B, C, D, E, F, G, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, t] = _dynsort(i)
}

trait SortAttrsOps_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_8[A, B, C, D, E, F, G, H, t, Ns[_, _, _, _, _, _, _, _, _]] extends SortAttrsOps_8[A, B, C, D, E, F, G, H, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, t] = _dynsort(i)
}

trait SortAttrsOps_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, I, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, I, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_9[A, B, C, D, E, F, G, H, I, t, Ns[_, _, _, _, _, _, _, _, _, _]] extends SortAttrsOps_9[A, B, C, D, E, F, G, H, I, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, I, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, I, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, I, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, I, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, I, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, I, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, I, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, I, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, I, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, I, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, I, t] = _dynsort(i)
}

trait SortAttrsOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, I, J, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_10[A, B, C, D, E, F, G, H, I, J, t, Ns[_, _, _, _, _, _, _, _, _, _, _]] extends SortAttrsOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, I, J, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, I, J, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, I, J, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, I, J, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, I, J, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, I, J, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, I, J, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, I, J, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, I, J, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, I, J, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, t] = _dynsort(i)
}

trait SortAttrsOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, I, J, K, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _]] extends SortAttrsOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, t] = _dynsort(i)
}

trait SortAttrsOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends SortAttrsOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, t] = _dynsort(i)
}

trait SortAttrsOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends SortAttrsOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _dynsort(i)
}

trait SortAttrsOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends SortAttrsOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _dynsort(i)
}

trait SortAttrsOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends SortAttrsOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _dynsort(i)
}

trait SortAttrsOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends SortAttrsOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _dynsort(i)
}

trait SortAttrsOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends SortAttrsOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _dynsort(i)
}

trait SortAttrsOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends SortAttrsOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _dynsort(i)
}

trait SortAttrsOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends SortAttrsOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _dynsort(i)
}

trait SortAttrsOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends SortAttrsOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _dynsort(i)
}

trait SortAttrsOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends SortAttrsOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _dynsort(i)
}

trait SortAttrsOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] {
  protected def _sort(sort: String): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t]
  protected def _dynsort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = {
    i match {
      case 0     => _sort("")
      case 1     => _sort("a1")
      case -1    => _sort("d1")
      case 2     => _sort("a2")
      case -2    => _sort("d2")
      case 3     => _sort("a3")
      case -3    => _sort("d3")
      case 4     => _sort("a4")
      case -4    => _sort("d4")
      case 5     => _sort("a5")
      case -5    => _sort("d5")
      case other => throw ExecutionError(
        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $other"
      )
    }
  }
}
trait SortAttrs_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends SortAttrsOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns] {
  def a1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _sort("a1")
  def a2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _sort("a2")
  def a3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _sort("a3")
  def a4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _sort("a4")
  def a5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _sort("a5")
  def d1: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _sort("d1")
  def d2: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _sort("d2")
  def d3: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _sort("d3")
  def d4: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _sort("d4")
  def d5: Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _sort("d5")
  def sort(i: Int): Ns[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _dynsort(i)
}