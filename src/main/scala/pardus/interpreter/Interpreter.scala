package pardus.interpreter

import Types._

object Interpreter {

  def maxArgs(stm: Stm): Int = stm match {
    case Stm.CompoundStm(left, right) => maxArgs(left).max(maxArgs(right))
    case Stm.AssignStm(_, exp) => maxArgsE(exp)
    case Stm.PrintStm(exprs) => exprs.map(maxArgsE).foldLeft(0)(_ max _)
  }

  def maxArgsE(exp: Exp): Int = exp match {
    case Exp.IdExp(_) => 0
    case Exp.NumExp(_) => 0
    case Exp.OpExp(l,_,r) => maxArgsE(l).max(maxArgsE(r))
    case Exp.EseqExp(stm, exp) => maxArgs(stm).max(maxArgsE(exp))
  }

  def interp(stm: Stm): Unit = ???
  def interpStm(stm: Stm, table: Table): Table = stm match {
    case Stm.CompoundStm(left, right) =>
      val nextTable = interpStm(left, table)
      interpStm(right, nextTable)
    case Stm.AssignStm(id, exp) =>
      val (value, nextTable) = interpExp(exp, table)
      table.update(id, value)
    case Stm.PrintStm(Nil) => table
    case Stm.PrintStm(e::Nil) =>
      val (value, nextTable) = interpExp(e, table)
      print(value)
      nextTable
    case Stm.PrintStm(e::es) =>
      val nextTable = interpStm(Stm.PrintStm(e::Nil), table)
      interpStm(Stm.PrintStm(es), nextTable)
  }
  def interpExp(exp: Exp, table: Table): (Int, Table) = exp match {
    case Exp.IdExp(id) => (table.lookup(id), table)
    case Exp.NumExp(i) => (i, table)
    case Exp.OpExp(left, op, right) => interpOp(left, op, right, table)
    case Exp.EseqExp(stm, exp) =>
      val nextTable = interpStm(stm, table)
      interpExp(exp, nextTable)
  }

  def interpOp(left: Exp, op: BinOp, right: Exp, table: Table): (Int, Table) = {
    val (leftVal, nextTable) = interpExp(left, table)
    val (rightVal, nextNextTable) = interpExp(right, nextTable)
    op match {
      case BinOp.Plus => (leftVal + rightVal, nextNextTable)
      case BinOp.Minus => (leftVal - rightVal, nextNextTable)
      case BinOp.Times => (leftVal * rightVal, nextNextTable)
      case BinOp.Div => (leftVal / rightVal , nextNextTable)
    }
  }
}
