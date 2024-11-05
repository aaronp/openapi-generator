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


        /** PetData a data transfer object, primarily for simple json serialisation.
  * It has no validation - there may be nulls, values out of range, etc
  */
case class PetData(
  id: Long = 0 ,
  category: CategoryData = null ,
  name: String,
  photoUrls: Seq[String],
  tags: Seq[TagData] = Nil ,
/* pet status in the store */
  status: Pet.StatusEnum = null 
  

) derives RW {

  def asJsonString: String = asJson.toString()

  def asJson : ujson.Value = {
    val jason = writeJs(this)
    jason
  }

  def validationErrors(path : Seq[Field], failFast : Boolean) : Seq[ValidationError] = {
    val errors = scala.collection.mutable.ListBuffer[ValidationError]()
        // ================== id validation ==================
        
        
        
        

        // ================== category validation ==================
        
        
        
        
        // validating category
        if (errors.isEmpty || !failFast) {
            if category != null then errors ++= category.validationErrors(path :+ Pet.Fields.category, failFast)
        }

        // ================== name validation ==================
        
        
        
        

        // ================== photoUrls validation ==================
        
        
        
        

        // ================== tags validation ==================
        
        
        
        
        if (errors.isEmpty || !failFast) {
            if (tags != null) {
                tags.zipWithIndex.foreach {
                    case (value, i) if errors.isEmpty || !failFast =>
                      errors ++= value.validationErrors(
                        path :+ Pet.Fields.tags :+ Field(i.toString),
                        failFast)
                    case (value, i) =>
                }
            }
        }
        

        // ================== status validation ==================
        
        
        
        

    errors.toSeq
  }

  /**
   * @return the validated model within a Try (if successful)
   */
  def validated(failFast : Boolean = false) : scala.util.Try[Pet] = {
    validationErrors(Vector(), failFast) match {
      case Seq() => Success(asModel)
      case first +: theRest => Failure(ValidationErrors(first, theRest))
    }
  }

  /** use 'validated' to check validation */
  def asModel : Pet = {
    Pet(
        id = Option(id) /* 1 */,
        category = Option(category).map(_.asModel) /* 4 */,
        name = name /* 2 */,
        photoUrls = photoUrls /* 2 */,
        tags = tags.map(_.asModel) /* 5 */,
        status = Option(status) /* 1 */
    
    )
  }
}

object PetData {

  def fromJson(jason : ujson.Value) : PetData = try {
        val data = read[PetData](jason)
        data
    } catch {
      case NonFatal(e) => sys.error(s"Error creating PetData from json '$jason': $e")
  }

  def fromJsonString(jason : String) : PetData = {
        val parsed = try {
           read[ujson.Value](jason)
        } catch {
          case NonFatal(e) => sys.error(s"Error parsing json '$jason': $e")
        }
        fromJson(parsed)
  }

  def manyFromJsonString(jason : String) : Seq[PetData] = try {
        read[List[PetData]](jason)
    } catch {
        case NonFatal(e) => sys.error(s"Error parsing json '$jason' as list: $e")
    }

  def manyFromJsonStringValidated(jason : String, failFast : Boolean = false) : Try[Seq[Pet]] = {
      Try(manyFromJsonString(jason)).flatMap { list =>
        list.zipWithIndex.foldLeft(Try(Vector[Pet]())) {
          case (Success(list), (next, i)) => 
            next.validated(failFast) match {
              case Success(ok) => Success(list :+ ok)
              case Failure(err) => Failure(new Exception(s"Validation error on element $i: ${err.getMessage}", err))
            }
          case (fail, _)  => fail
        }
      }
    }

  def mapFromJsonString(jason : String) : Map[String, PetData] = try {
        read[Map[String, PetData]](jason)
    } catch {
        case NonFatal(e) => sys.error(s"Error parsing json '$jason' as map: $e")
    }


  def mapFromJsonStringValidated(jason : String, failFast : Boolean = false) : Try[Map[String, Pet]] = {
     Try(mapFromJsonString(jason)).flatMap { map =>
       map.foldLeft(Try(Map[String, Pet]())) {
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

