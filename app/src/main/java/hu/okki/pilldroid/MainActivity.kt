package hu.okki.pilldroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import hu.okki.pilldroid.alarm.scheduleNextAlarm
import hu.okki.pilldroid.alarm.scheduleWorker
import hu.okki.pilldroid.repository.MedicationRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController, AppBarConfiguration(navController.graph))
    }

    override fun onPause() {
        super.onPause()
        MedicationRepository.getInstance(applicationContext).saveAll()
        scheduleNextAlarm(applicationContext)
        scheduleWorker(applicationContext)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                val navController = findNavController(R.id.nav_host_fragment)
                navController.navigate(R.id.aboutFragment)
                return true
            }
            R.id.action_help -> {
                val helpUrl = Uri.parse(getString(R.string.app_url_help))
                val navIntent = Intent(Intent.ACTION_VIEW, helpUrl)
                startActivity(navIntent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

}