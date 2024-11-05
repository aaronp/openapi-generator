/**
 * OpenAPI Petstore
 * This is a sample server Petstore server. For this sample, you can use the api key `special-key` to test the authorization filters.
 *
 * OpenAPI spec version: 1.0.0
 *
 * Contact: team@openapitools.org
 *
 * NOTE: This class is auto generated by OpenAPI Generator.
 *
 * https://openapi-generator.tech
 */

// this model was generated using modelData.mustache
package sample.cask.model
import scala.util.control.NonFatal
import scala.util.*

// see https://com-lihaoyi.github.io/upickle/
import upickle.default.{ReadWriter => RW, macroRW}
import upickle.default.*


        /** ApiResponseData a data transfer object, primarily for simple json serialisation.
  * It has no validation - there may be nulls, values out of range, etc
  */
case class ApiResponseData(
  code: Int = 0 ,
  `type`: String = "" ,
  message: String = "" 
  

) derives RW {

  def asJsonString: String = asJson.toString()

  def asJson : ujson.Value = {
    val jason = writeJs(this)
    jason
  }

  def validationErrors(path : Seq[Field], failFast : Boolean) : Seq[ValidationError] = {
    val errors = scala.collection.mutable.ListBuffer[ValidationError]()
        // ================== code validation ==================
        
        
        
        

        // ================== `type` validation ==================
        
        
        
        

        // ================== message validation ==================
        
        
        
        

    errors.toSeq
  }

  /**
   * @return the validated model within a Try (if successful)
   */
  def validated(failFast : Boolean = false) : scala.util.Try[ApiResponse] = {
    validationErrors(Vector(), failFast) match {
      case Seq() => Success(asModel)
      case first +: theRest => Failure(ValidationErrors(first, theRest))
    }
  }

  /** use 'validated' to check validation */
  def asModel : ApiResponse = {
    ApiResponse(
        code = Option(code) /* 1 */,
        `type` = Option(`type`) /* 1 */,
        message = Option(message) /* 1 */
    
    )
  }
}

object ApiResponseData {

  def fromJson(jason : ujson.Value) : ApiResponseData = try {
        val data = read[ApiResponseData](jason)
        data
    } catch {
      case NonFatal(e) => sys.error(s"Error creating ApiResponseData from json '$jason': $e")
  }

  def fromJsonString(jason : String) : ApiResponseData = {
        val parsed = try {
           read[ujson.Value](jason)
        } catch {
          case NonFatal(e) => sys.error(s"Error parsing json '$jason': $e")
        }
        fromJson(parsed)
  }

  def manyFromJsonString(jason : String) : Seq[ApiResponseData] = try {
        read[List[ApiResponseData]](jason)
    } catch {
        case NonFatal(e) => sys.error(s"Error parsing json '$jason' as list: $e")
    }

  def manyFromJsonStringValidated(jason : String, failFast : Boolean = false) : Try[Seq[ApiResponse]] = {
      Try(manyFromJsonString(jason)).flatMap { list =>
        list.zipWithIndex.foldLeft(Try(Vector[ApiResponse]())) {
          case (Success(list), (next, i)) => 
            next.validated(failFast) match {
              case Success(ok) => Success(list :+ ok)
              case Failure(err) => Failure(new Exception(s"Validation error on element $i: ${err.getMessage}", err))
            }
          case (fail, _)  => fail
        }
      }
    }

  def mapFromJsonString(jason : String) : Map[String, ApiResponseData] = try {
        read[Map[String, ApiResponseData]](jason)
    } catch {
        case NonFatal(e) => sys.error(s"Error parsing json '$jason' as map: $e")
    }


  def mapFromJsonStringValidated(jason : String, failFast : Boolean = false) : Try[Map[String, ApiResponse]] = {
     Try(mapFromJsonString(jason)).flatMap { map =>
       map.foldLeft(Try(Map[String, ApiResponse]())) {
         case (Success(map), (key, next)) =>
           next.validated(failFast) match {
             case Success(ok) => Success(map.updated(key, ok))
             case Failure(err) => Failure(new Exception(s"Validation error on element $key: ${err.getMessage}", err))
           }
         case (fail, _) => fail
       }
     }
  }
}

