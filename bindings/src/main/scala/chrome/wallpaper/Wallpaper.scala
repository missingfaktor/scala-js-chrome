package chrome.wallpaper

import chrome.ChromeAPI
import chrome.permissions.APIPermission
import chrome.utils.ErrorHandling._
import chrome.wallpaper.bindings.WallpaperDetails

import scala.concurrent.{Future, Promise}
import scala.scalajs.js

object Wallpaper extends ChromeAPI {

  val requiredPermissions: Set[APIPermission] = Set(APIPermission.Wallpaper)

  def setWallpaper(details: WallpaperDetails): Future[Option[Any]] = {
    val promise = Promise[Option[Any]]()
    bindings.Wallpaper.setWallpaper(details, (thumbnail: js.UndefOr[js.Any]) => {
      promise.complete(lastErrorOrValue(thumbnail.toOption))
    })
    promise.future
  }

}
