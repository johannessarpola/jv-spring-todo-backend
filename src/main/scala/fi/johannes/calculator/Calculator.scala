package fi.johannes.calculator

import scala.language.{implicitConversions, postfixOps}

/**
  * Created by developer
  * on 26.01.17.
  */
class Calculator {

  /**
    * Calculate expression
    *
    * @param line expression
    * @return result
    */
  def calculate(line: String) : BigDecimal = {
    var expression = line.replace(" ", "").trim
    // if first char is minus, append zero to the head
    if (expression.indexOf(Calculator.MINUS.alias) == 0) {
      expression = 0 + expression
    }
    recursiveCalc(expression)
  }

  /**
    * Recursive parsing and calculating expression
    *
    * @param line expression
    * @return result
    */
  private def recursiveCalc(line: String): BigDecimal = {
    if (line.isNumber) {
      return BigDecimal(line)
    }

    var index: Int = -1
    val operation = Calculator.supportOperations()
      .find(o => {
        index = line.lastIndexOf(o.alias)
        index != -1
      })
      .getOrElse {
        throw new UnsupportedOperationException("Can't understand: " + line)
      }

    val left = line.substring(0, index)
    val right = line.substring(index + operation.alias.length, line.length)

    operation.use(recursiveCalc(left), recursiveCalc(right))
  }

  /**
    * Implicit class expression helper, contains service functions
    *
    * @param line expression
    */
  implicit final class LineExpressionHelper(line: String) {
    def isNumber: Boolean = line.matches("\\d+\\.?\\d*")
  }
}

object Calculator {

  case class Operation(alias: String,
                       priority: Int,
                       use:(BigDecimal, BigDecimal) => BigDecimal)

  /**
    * Supported operations with priority
    */
  val PLUS        = Operation("+", priority = 1, _+_)
  val MINUS       = Operation("-", priority = 1, _-_)
  val MULTIPLY    = Operation("*", priority = 2, _*_)
  val DIVIDE      = Operation("/", priority = 3, _/_)

  def supportOperations(): Array[Operation] = {
    Array(PLUS, MINUS, MULTIPLY, DIVIDE).sortBy(_.priority)
  }
}