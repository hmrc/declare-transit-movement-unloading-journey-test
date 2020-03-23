package ctc.utils

trait UrlHelper {

  def convertToUrlSlug(text: String, preserveCase: Boolean = false): String

  def getFullUrl(slug: String, preserveCase: Boolean = false): String

}

object UrlHelperWithHyphens extends UrlHelper {

  override def convertToUrlSlug(text: String, preserveCase: Boolean = false): String = {
    val url = text.replace(" ", "-").trim

    preserveCase match {
      case true => url
      case _    => url.toLowerCase
    }
  }

  override def getFullUrl(slug: String, preserveCase: Boolean = false): String = {
    val convertedSlug = convertToUrlSlug(slug, preserveCase)
    s"${Configuration.settings.applicationsBaseUrl}/$convertedSlug"
  }

}
