package app.orchard.tv

import android.app.Application
import android.os.PowerManager
import androidx.core.content.getSystemService
import app.orchard.tv.guard.HomeGuardService

/**
 * If the firmware kills the process during standby and something revives it, the standby cover
 * dies with the old process. While the screen is dark, put it straight back up: on wake the
 * launcher draws under it, invisible. With the screen on, the guard's own events decide.
 */
class AerialApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (getSystemService<PowerManager>()?.isInteractive == false) {
            HomeGuardService.coverStockLauncher(this, HomeGuardService.standbyCoverMs())
        }
    }
}
